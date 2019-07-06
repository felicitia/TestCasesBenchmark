package org.apache.commons.math3.geometry.euclidean.twod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.oned.Interval;
import org.apache.commons.math3.geometry.euclidean.oned.IntervalsSet;
import org.apache.commons.math3.geometry.euclidean.oned.Vector1D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.utilities.AVLTree;
import org.apache.commons.math3.geometry.partitioning.utilities.AVLTree.Node;
import org.apache.commons.math3.geometry.partitioning.utilities.OrderedTuple;
import org.apache.commons.math3.util.FastMath;

public class PolygonsSet extends AbstractRegion<Euclidean2D, Euclidean1D> {
    private Vector2D[][] vertices;

    private static class ComparableSegment extends Segment implements Comparable<ComparableSegment> {
        private OrderedTuple sortingKey;

        public ComparableSegment(Vector2D vector2D, Vector2D vector2D2, Line line) {
            OrderedTuple orderedTuple;
            super(vector2D, vector2D2, line);
            if (vector2D == null) {
                orderedTuple = new OrderedTuple(Double.NEGATIVE_INFINITY, Double.NEGATIVE_INFINITY);
            } else {
                orderedTuple = new OrderedTuple(vector2D.getX(), vector2D.getY());
            }
            this.sortingKey = orderedTuple;
        }

        public ComparableSegment(Vector2D vector2D, double d, double d2) {
            super(null, null, null);
            this.sortingKey = new OrderedTuple(vector2D.getX() + d, vector2D.getY() + d2);
        }

        public int compareTo(ComparableSegment comparableSegment) {
            return this.sortingKey.compareTo(comparableSegment.sortingKey);
        }

        public boolean equals(Object obj) {
            boolean z = true;
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof ComparableSegment)) {
                return false;
            }
            if (compareTo((ComparableSegment) obj) != 0) {
                z = false;
            }
            return z;
        }

        public int hashCode() {
            return ((getStart().hashCode() ^ getEnd().hashCode()) ^ getLine().hashCode()) ^ this.sortingKey.hashCode();
        }
    }

    private static class Edge {
        private final Vertex end;
        private final Line line;
        private BSPTree<Euclidean2D> node = null;
        private final Vertex start;

        public Edge(Vertex vertex, Vertex vertex2, Line line2) {
            this.start = vertex;
            this.end = vertex2;
            this.line = line2;
            vertex.setOutgoing(this);
            vertex2.setIncoming(this);
        }

        public Vertex getStart() {
            return this.start;
        }

        public Vertex getEnd() {
            return this.end;
        }

        public Line getLine() {
            return this.line;
        }

        public void setNode(BSPTree<Euclidean2D> bSPTree) {
            this.node = bSPTree;
        }

        public BSPTree<Euclidean2D> getNode() {
            return this.node;
        }

        public Vertex split(Line line2) {
            Vertex vertex = new Vertex(this.line.intersection(line2));
            vertex.bindWith(line2);
            Edge edge = new Edge(this.start, vertex, this.line);
            Edge edge2 = new Edge(vertex, this.end, this.line);
            edge.node = this.node;
            edge2.node = this.node;
            return vertex;
        }
    }

    private static class SegmentsBuilder implements BSPTreeVisitor<Euclidean2D> {
        private AVLTree<ComparableSegment> sorted = new AVLTree<>();

        public void visitLeafNode(BSPTree<Euclidean2D> bSPTree) {
        }

        public Order visitOrder(BSPTree<Euclidean2D> bSPTree) {
            return Order.MINUS_SUB_PLUS;
        }

        public void visitInternalNode(BSPTree<Euclidean2D> bSPTree) {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute.getPlusOutside() != null) {
                addContribution(boundaryAttribute.getPlusOutside(), false);
            }
            if (boundaryAttribute.getPlusInside() != null) {
                addContribution(boundaryAttribute.getPlusInside(), true);
            }
        }

        private void addContribution(SubHyperplane<Euclidean2D> subHyperplane, boolean z) {
            AbstractSubHyperplane abstractSubHyperplane = (AbstractSubHyperplane) subHyperplane;
            Line line = (Line) subHyperplane.getHyperplane();
            for (Interval interval : ((IntervalsSet) abstractSubHyperplane.getRemainingRegion()).asList()) {
                Vector2D vector2D = null;
                Vector2D space = Double.isInfinite(interval.getInf()) ? null : line.toSpace((Vector) new Vector1D(interval.getInf()));
                if (!Double.isInfinite(interval.getSup())) {
                    vector2D = line.toSpace((Vector) new Vector1D(interval.getSup()));
                }
                if (z) {
                    this.sorted.insert(new ComparableSegment(vector2D, space, line.getReverse()));
                } else {
                    this.sorted.insert(new ComparableSegment(space, vector2D, line));
                }
            }
        }

        public AVLTree<ComparableSegment> getSorted() {
            return this.sorted;
        }
    }

    private static class Vertex {
        private Edge incoming = null;
        private final List<Line> lines = new ArrayList();
        private final Vector2D location;
        private Edge outgoing = null;

        public Vertex(Vector2D vector2D) {
            this.location = vector2D;
        }

        public Vector2D getLocation() {
            return this.location;
        }

        public void bindWith(Line line) {
            this.lines.add(line);
        }

        public Line sharedLineWith(Vertex vertex) {
            for (Line line : this.lines) {
                Iterator it = vertex.lines.iterator();
                while (true) {
                    if (it.hasNext()) {
                        if (line == ((Line) it.next())) {
                            return line;
                        }
                    }
                }
            }
            return null;
        }

        public void setIncoming(Edge edge) {
            this.incoming = edge;
            bindWith(edge.getLine());
        }

        public Edge getIncoming() {
            return this.incoming;
        }

        public void setOutgoing(Edge edge) {
            this.outgoing = edge;
            bindWith(edge.getLine());
        }

        public Edge getOutgoing() {
            return this.outgoing;
        }
    }

    public PolygonsSet() {
    }

    public PolygonsSet(BSPTree<Euclidean2D> bSPTree) {
        super(bSPTree);
    }

    public PolygonsSet(Collection<SubHyperplane<Euclidean2D>> collection) {
        super(collection);
    }

    public PolygonsSet(double d, double d2, double d3, double d4) {
        super((Hyperplane<S>[]) boxBoundary(d, d2, d3, d4));
    }

    public PolygonsSet(double d, Vector2D... vector2DArr) {
        super(verticesToTree(d, vector2DArr));
    }

    private static Line[] boxBoundary(double d, double d2, double d3, double d4) {
        Vector2D vector2D = new Vector2D(d, d3);
        Vector2D vector2D2 = new Vector2D(d, d4);
        Vector2D vector2D3 = new Vector2D(d2, d3);
        Vector2D vector2D4 = new Vector2D(d2, d4);
        return new Line[]{new Line(vector2D, vector2D3), new Line(vector2D3, vector2D4), new Line(vector2D4, vector2D2), new Line(vector2D2, vector2D)};
    }

    private static BSPTree<Euclidean2D> verticesToTree(double d, Vector2D... vector2DArr) {
        int length = vector2DArr.length;
        if (length == 0) {
            return new BSPTree<>(Boolean.TRUE);
        }
        Vertex[] vertexArr = new Vertex[length];
        for (int i = 0; i < length; i++) {
            vertexArr[i] = new Vertex(vector2DArr[i]);
        }
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        while (i2 < length) {
            Vertex vertex = vertexArr[i2];
            i2++;
            Vertex vertex2 = vertexArr[i2 % length];
            Line sharedLineWith = vertex.sharedLineWith(vertex2);
            if (sharedLineWith == null) {
                sharedLineWith = new Line(vertex.getLocation(), vertex2.getLocation());
            }
            arrayList.add(new Edge(vertex, vertex2, sharedLineWith));
            for (Vertex vertex3 : vertexArr) {
                if (!(vertex3 == vertex || vertex3 == vertex2 || FastMath.abs(sharedLineWith.getOffset((Vector<Euclidean2D>) vertex3.getLocation())) > d)) {
                    vertex3.bindWith(sharedLineWith);
                }
            }
        }
        BSPTree<Euclidean2D> bSPTree = new BSPTree<>();
        insertEdges(d, bSPTree, arrayList);
        return bSPTree;
    }

    private static void insertEdges(double d, BSPTree<Euclidean2D> bSPTree, List<Edge> list) {
        Edge edge;
        double d2 = d;
        BSPTree<Euclidean2D> bSPTree2 = bSPTree;
        int i = 0;
        loop0:
        while (true) {
            int i2 = i;
            edge = null;
            while (edge == null && i2 < list.size()) {
                i = i2 + 1;
                Edge edge2 = (Edge) list.get(i2);
                if (edge2.getNode() == null && bSPTree2.insertCut(edge2.getLine())) {
                    edge2.setNode(bSPTree2);
                    Edge edge3 = edge2;
                    i2 = i;
                    edge = edge3;
                }
            }
        }
        List<Edge> list2 = list;
        if (edge == null) {
            BSPTree parent = bSPTree.getParent();
            if (parent == null || bSPTree2 == parent.getMinus()) {
                bSPTree2.setAttribute(Boolean.TRUE);
            } else {
                bSPTree2.setAttribute(Boolean.FALSE);
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Edge edge4 : list) {
            if (edge4 != edge) {
                double offset = edge.getLine().getOffset((Vector<Euclidean2D>) edge4.getStart().getLocation());
                double offset2 = edge.getLine().getOffset((Vector<Euclidean2D>) edge4.getEnd().getLocation());
                Side side = FastMath.abs(offset) <= d2 ? Side.HYPER : offset < 0.0d ? Side.MINUS : Side.PLUS;
                Side side2 = FastMath.abs(offset2) <= d2 ? Side.HYPER : offset2 < 0.0d ? Side.MINUS : Side.PLUS;
                switch (side) {
                    case PLUS:
                        if (side2 != Side.MINUS) {
                            arrayList.add(edge4);
                            break;
                        } else {
                            Vertex split = edge4.split(edge.getLine());
                            arrayList2.add(split.getOutgoing());
                            arrayList.add(split.getIncoming());
                            break;
                        }
                    case MINUS:
                        if (side2 != Side.PLUS) {
                            arrayList2.add(edge4);
                            break;
                        } else {
                            Vertex split2 = edge4.split(edge.getLine());
                            arrayList2.add(split2.getIncoming());
                            arrayList.add(split2.getOutgoing());
                            break;
                        }
                    default:
                        if (side2 != Side.PLUS) {
                            if (side2 != Side.MINUS) {
                                break;
                            } else {
                                arrayList2.add(edge4);
                                break;
                            }
                        } else {
                            arrayList.add(edge4);
                            break;
                        }
                }
            }
        }
        if (!arrayList.isEmpty()) {
            insertEdges(d2, bSPTree.getPlus(), arrayList);
        } else {
            bSPTree.getPlus().setAttribute(Boolean.FALSE);
        }
        if (!arrayList2.isEmpty()) {
            insertEdges(d2, bSPTree.getMinus(), arrayList2);
        } else {
            bSPTree.getMinus().setAttribute(Boolean.TRUE);
        }
    }

    public PolygonsSet buildNew(BSPTree<Euclidean2D> bSPTree) {
        return new PolygonsSet(bSPTree);
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        Vector2D[][] vertices2 = getVertices();
        if (vertices2.length == 0) {
            BSPTree tree = getTree(false);
            if (tree.getCut() != null || !((Boolean) tree.getAttribute()).booleanValue()) {
                setSize(0.0d);
                setBarycenter(new Vector2D(0.0d, 0.0d));
                return;
            }
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter(Vector2D.NaN);
        } else if (vertices2[0][0] == null) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter(Vector2D.NaN);
        } else {
            int length = vertices2.length;
            int i = 0;
            double d = 0.0d;
            double d2 = 0.0d;
            double d3 = 0.0d;
            while (i < length) {
                Vector2D[] vector2DArr = vertices2[i];
                double x = vector2DArr[vector2DArr.length - 1].getX();
                double y = vector2DArr[vector2DArr.length - 1].getY();
                int length2 = vector2DArr.length;
                double d4 = d3;
                double d5 = d2;
                double d6 = d;
                int i2 = 0;
                while (i2 < length2) {
                    Vector2D vector2D = vector2DArr[i2];
                    double x2 = vector2D.getX();
                    double y2 = vector2D.getY();
                    double d7 = (x * y2) - (y * x2);
                    d6 += d7;
                    d5 += (x + x2) * d7;
                    d4 += d7 * (y + y2);
                    i2++;
                    x = x2;
                    y = y2;
                }
                i++;
                d = d6;
                d2 = d5;
                d3 = d4;
            }
            if (d < 0.0d) {
                setSize(Double.POSITIVE_INFINITY);
                setBarycenter(Vector2D.NaN);
                return;
            }
            setSize(d / 2.0d);
            double d8 = 3.0d * d;
            setBarycenter(new Vector2D(d2 / d8, d3 / d8));
        }
    }

    public Vector2D[][] getVertices() {
        Iterator it;
        Iterator it2;
        int i;
        if (this.vertices == null) {
            int i2 = 0;
            if (getTree(false).getCut() == null) {
                this.vertices = new Vector2D[0][];
            } else {
                SegmentsBuilder segmentsBuilder = new SegmentsBuilder();
                getTree(true).visit(segmentsBuilder);
                AVLTree sorted = segmentsBuilder.getSorted();
                ArrayList arrayList = new ArrayList();
                while (!sorted.isEmpty()) {
                    List followLoop = followLoop(sorted.getSmallest(), sorted);
                    if (followLoop != null) {
                        arrayList.add(followLoop);
                    }
                }
                this.vertices = new Vector2D[arrayList.size()][];
                Iterator it3 = arrayList.iterator();
                int i3 = 0;
                while (it3.hasNext()) {
                    List<ComparableSegment> list = (List) it3.next();
                    Vector2D vector2D = null;
                    if (list.size() < 2) {
                        Line line = ((ComparableSegment) list.get(i2)).getLine();
                        Vector2D[][] vector2DArr = this.vertices;
                        int i4 = i3 + 1;
                        Vector2D[] vector2DArr2 = new Vector2D[3];
                        vector2DArr2[i2] = null;
                        vector2DArr2[1] = line.toSpace((Vector) new Vector1D(-3.4028234663852886E38d));
                        vector2DArr2[2] = line.toSpace((Vector) new Vector1D(3.4028234663852886E38d));
                        vector2DArr[i3] = vector2DArr2;
                        it = it3;
                        i3 = i4;
                    } else if (((ComparableSegment) list.get(i2)).getStart() == null) {
                        Vector2D[] vector2DArr3 = new Vector2D[(list.size() + 2)];
                        int i5 = i2;
                        for (ComparableSegment comparableSegment : list) {
                            if (i5 == 0) {
                                double x = comparableSegment.getLine().toSubSpace((Vector) comparableSegment.getEnd()).getX();
                                it2 = it3;
                                double max = x - FastMath.max(1.0d, FastMath.abs(x / 2.0d));
                                int i6 = i5 + 1;
                                vector2DArr3[i5] = vector2D;
                                i5 = i6 + 1;
                                vector2DArr3[i6] = comparableSegment.getLine().toSpace((Vector) new Vector1D(max));
                            } else {
                                it2 = it3;
                            }
                            if (i5 < vector2DArr3.length - 1) {
                                i = i5 + 1;
                                vector2DArr3[i5] = comparableSegment.getEnd();
                            } else {
                                i = i5;
                            }
                            if (i == vector2DArr3.length - 1) {
                                double x2 = comparableSegment.getLine().toSubSpace((Vector) comparableSegment.getStart()).getX();
                                int i7 = i + 1;
                                vector2DArr3[i] = comparableSegment.getLine().toSpace((Vector) new Vector1D(x2 + FastMath.max(1.0d, FastMath.abs(x2 / 2.0d))));
                                i5 = i7;
                            } else {
                                i5 = i;
                            }
                            it3 = it2;
                            vector2D = null;
                        }
                        it = it3;
                        int i8 = i3 + 1;
                        this.vertices[i3] = vector2DArr3;
                        i3 = i8;
                    } else {
                        it = it3;
                        Vector2D[] vector2DArr4 = new Vector2D[list.size()];
                        int i9 = 0;
                        for (ComparableSegment start : list) {
                            int i10 = i9 + 1;
                            vector2DArr4[i9] = start.getStart();
                            i9 = i10;
                        }
                        int i11 = i3 + 1;
                        this.vertices[i3] = vector2DArr4;
                        i3 = i11;
                    }
                    it3 = it;
                    i2 = 0;
                }
            }
        }
        return (Vector2D[][]) this.vertices.clone();
    }

    private List<ComparableSegment> followLoop(Node node, AVLTree<ComparableSegment> aVLTree) {
        ArrayList arrayList = new ArrayList();
        ComparableSegment comparableSegment = (ComparableSegment) node.getElement();
        arrayList.add(comparableSegment);
        Vector2D start = comparableSegment.getStart();
        Vector2D end = comparableSegment.getEnd();
        node.delete();
        boolean z = comparableSegment.getStart() == null;
        while (end != null && (z || start.distance(end) > 1.0E-10d)) {
            Vector2D vector2D = end;
            ComparableSegment comparableSegment2 = new ComparableSegment(vector2D, -1.0E-10d, -1.0E-10d);
            ComparableSegment comparableSegment3 = r4;
            ComparableSegment comparableSegment4 = new ComparableSegment(vector2D, 1.0E-10d, 1.0E-10d);
            Node notSmaller = aVLTree.getNotSmaller(comparableSegment2);
            ComparableSegment comparableSegment5 = null;
            Node node2 = null;
            double d = Double.POSITIVE_INFINITY;
            while (notSmaller != null && ((ComparableSegment) notSmaller.getElement()).compareTo(comparableSegment3) <= 0) {
                ComparableSegment comparableSegment6 = (ComparableSegment) notSmaller.getElement();
                double distance = end.distance(comparableSegment6.getStart());
                if (distance < d) {
                    node2 = notSmaller;
                    comparableSegment5 = comparableSegment6;
                    d = distance;
                }
                notSmaller = notSmaller.getNext();
            }
            if (d > 1.0E-10d) {
                return null;
            }
            end = comparableSegment5.getEnd();
            arrayList.add(comparableSegment5);
            node2.delete();
        }
        if (arrayList.size() == 2 && !z) {
            return null;
        }
        if (end != null || z) {
            return arrayList;
        }
        throw new MathInternalError();
    }
}
