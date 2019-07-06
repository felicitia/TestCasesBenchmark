package org.apache.commons.math3.geometry.euclidean.threed;

import java.awt.geom.AffineTransform;
import java.util.Collection;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.euclidean.oned.Euclidean1D;
import org.apache.commons.math3.geometry.euclidean.twod.Euclidean2D;
import org.apache.commons.math3.geometry.euclidean.twod.Line;
import org.apache.commons.math3.geometry.euclidean.twod.SubLine;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;
import org.apache.commons.math3.geometry.partitioning.BoundaryAttribute;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.geometry.partitioning.RegionFactory;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Transform;
import org.apache.commons.math3.util.FastMath;

public class PolyhedronsSet extends AbstractRegion<Euclidean3D, Euclidean2D> {

    private class FacetsContributionVisitor implements BSPTreeVisitor<Euclidean3D> {
        public void visitLeafNode(BSPTree<Euclidean3D> bSPTree) {
        }

        public FacetsContributionVisitor() {
            PolyhedronsSet.this.setSize(0.0d);
            Vector3D vector3D = new Vector3D(0.0d, 0.0d, 0.0d);
            PolyhedronsSet.this.setBarycenter(vector3D);
        }

        public Order visitOrder(BSPTree<Euclidean3D> bSPTree) {
            return Order.MINUS_SUB_PLUS;
        }

        public void visitInternalNode(BSPTree<Euclidean3D> bSPTree) {
            BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
            if (boundaryAttribute.getPlusOutside() != null) {
                addContribution(boundaryAttribute.getPlusOutside(), false);
            }
            if (boundaryAttribute.getPlusInside() != null) {
                addContribution(boundaryAttribute.getPlusInside(), true);
            }
        }

        private void addContribution(SubHyperplane<Euclidean3D> subHyperplane, boolean z) {
            Region remainingRegion = ((SubPlane) subHyperplane).getRemainingRegion();
            double size = remainingRegion.getSize();
            if (Double.isInfinite(size)) {
                PolyhedronsSet.this.setSize(Double.POSITIVE_INFINITY);
                PolyhedronsSet.this.setBarycenter(Vector3D.NaN);
                return;
            }
            Plane plane = (Plane) subHyperplane.getHyperplane();
            Vector3D space = plane.toSpace(remainingRegion.getBarycenter());
            double dotProduct = size * space.dotProduct(plane.getNormal());
            double d = z ? -dotProduct : dotProduct;
            PolyhedronsSet.this.setSize(PolyhedronsSet.this.getSize() + d);
            PolyhedronsSet polyhedronsSet = PolyhedronsSet.this;
            Vector3D vector3D = new Vector3D(1.0d, (Vector3D) PolyhedronsSet.this.getBarycenter(), d, space);
            polyhedronsSet.setBarycenter(vector3D);
        }
    }

    private static class RotationTransform implements Transform<Euclidean3D, Euclidean2D> {
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;
        private Vector3D center;
        private Rotation rotation;

        public RotationTransform(Vector3D vector3D, Rotation rotation2) {
            this.center = vector3D;
            this.rotation = rotation2;
        }

        public Vector3D apply(Vector<Euclidean3D> vector) {
            Vector3D vector3D = new Vector3D(1.0d, this.center, 1.0d, this.rotation.applyTo(((Vector3D) vector).subtract((Vector) this.center)));
            return vector3D;
        }

        public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane) hyperplane).rotate(this.center, this.rotation);
        }

        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> subHyperplane, Hyperplane<Euclidean3D> hyperplane, Hyperplane<Euclidean3D> hyperplane2) {
            Hyperplane<Euclidean3D> hyperplane3 = hyperplane;
            if (hyperplane3 != this.cachedOriginal) {
                Plane plane = (Plane) hyperplane3;
                Plane plane2 = (Plane) hyperplane2;
                Vector3D origin = plane.getOrigin();
                Vector3D space = plane.toSpace((Vector) new Vector2D(1.0d, 0.0d));
                Vector3D space2 = plane.toSpace((Vector) new Vector2D(0.0d, 1.0d));
                Vector2D subSpace = plane2.toSubSpace((Vector) apply((Vector) origin));
                Vector2D subSpace2 = plane2.toSubSpace((Vector) apply((Vector) space));
                Vector2D subSpace3 = plane2.toSubSpace((Vector) apply((Vector) space2));
                AffineTransform affineTransform = r5;
                AffineTransform affineTransform2 = new AffineTransform(subSpace2.getX() - subSpace.getX(), subSpace2.getY() - subSpace.getY(), subSpace3.getX() - subSpace.getX(), subSpace3.getY() - subSpace.getY(), subSpace.getX(), subSpace.getY());
                this.cachedOriginal = plane;
                this.cachedTransform = Line.getTransform(affineTransform);
            }
            return ((SubLine) subHyperplane).applyTransform(this.cachedTransform);
        }
    }

    private static class TranslationTransform implements Transform<Euclidean3D, Euclidean2D> {
        private Plane cachedOriginal;
        private Transform<Euclidean2D, Euclidean1D> cachedTransform;
        private Vector3D translation;

        public TranslationTransform(Vector3D vector3D) {
            this.translation = vector3D;
        }

        public Vector3D apply(Vector<Euclidean3D> vector) {
            Vector3D vector3D = new Vector3D(1.0d, (Vector3D) vector, 1.0d, this.translation);
            return vector3D;
        }

        public Plane apply(Hyperplane<Euclidean3D> hyperplane) {
            return ((Plane) hyperplane).translate(this.translation);
        }

        public SubHyperplane<Euclidean2D> apply(SubHyperplane<Euclidean2D> subHyperplane, Hyperplane<Euclidean3D> hyperplane, Hyperplane<Euclidean3D> hyperplane2) {
            if (hyperplane != this.cachedOriginal) {
                Plane plane = (Plane) hyperplane;
                Vector2D subSpace = ((Plane) hyperplane2).toSubSpace((Vector) apply((Vector) plane.getOrigin()));
                AffineTransform translateInstance = AffineTransform.getTranslateInstance(subSpace.getX(), subSpace.getY());
                this.cachedOriginal = plane;
                this.cachedTransform = Line.getTransform(translateInstance);
            }
            return ((SubLine) subHyperplane).applyTransform(this.cachedTransform);
        }
    }

    public PolyhedronsSet() {
    }

    public PolyhedronsSet(BSPTree<Euclidean3D> bSPTree) {
        super(bSPTree);
    }

    public PolyhedronsSet(Collection<SubHyperplane<Euclidean3D>> collection) {
        super(collection);
    }

    public PolyhedronsSet(double d, double d2, double d3, double d4, double d5, double d6) {
        super(buildBoundary(d, d2, d3, d4, d5, d6));
    }

    private static BSPTree<Euclidean3D> buildBoundary(double d, double d2, double d3, double d4, double d5, double d6) {
        Vector3D vector3D = new Vector3D(d, 0.0d, 0.0d);
        Plane plane = new Plane(vector3D, Vector3D.MINUS_I);
        Vector3D vector3D2 = new Vector3D(d2, 0.0d, 0.0d);
        Plane plane2 = new Plane(vector3D2, Vector3D.PLUS_I);
        Vector3D vector3D3 = new Vector3D(0.0d, d3, 0.0d);
        Plane plane3 = new Plane(vector3D3, Vector3D.MINUS_J);
        Vector3D vector3D4 = new Vector3D(0.0d, d4, 0.0d);
        Plane plane4 = new Plane(vector3D4, Vector3D.PLUS_J);
        Vector3D vector3D5 = new Vector3D(0.0d, 0.0d, d5);
        Plane plane5 = new Plane(vector3D5, Vector3D.MINUS_K);
        Vector3D vector3D6 = new Vector3D(0.0d, 0.0d, d6);
        Plane plane6 = new Plane(vector3D6, Vector3D.PLUS_K);
        return new RegionFactory().buildConvex(plane, plane2, plane3, plane4, plane5, plane6).getTree(false);
    }

    public PolyhedronsSet buildNew(BSPTree<Euclidean3D> bSPTree) {
        return new PolyhedronsSet(bSPTree);
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        getTree(true).visit(new FacetsContributionVisitor());
        if (getSize() < 0.0d) {
            setSize(Double.POSITIVE_INFINITY);
            setBarycenter(Vector3D.NaN);
            return;
        }
        setSize(getSize() / 3.0d);
        setBarycenter(new Vector3D(1.0d / (4.0d * getSize()), (Vector3D) getBarycenter()));
    }

    public SubHyperplane<Euclidean3D> firstIntersection(Vector3D vector3D, Line line) {
        return recurseFirstIntersection(getTree(true), vector3D, line);
    }

    private SubHyperplane<Euclidean3D> recurseFirstIntersection(BSPTree<Euclidean3D> bSPTree, Vector3D vector3D, Line line) {
        SubHyperplane cut = bSPTree.getCut();
        if (cut == null) {
            return null;
        }
        BSPTree minus = bSPTree.getMinus();
        BSPTree plus = bSPTree.getPlus();
        Plane plane = (Plane) cut.getHyperplane();
        double offset = plane.getOffset((Vector<Euclidean3D>) vector3D);
        boolean z = FastMath.abs(offset) < 1.0E-10d;
        if (offset >= 0.0d) {
            BSPTree bSPTree2 = plus;
            plus = minus;
            minus = bSPTree2;
        }
        if (z) {
            SubHyperplane<Euclidean3D> boundaryFacet = boundaryFacet(vector3D, bSPTree);
            if (boundaryFacet != null) {
                return boundaryFacet;
            }
        }
        SubHyperplane<Euclidean3D> recurseFirstIntersection = recurseFirstIntersection(minus, vector3D, line);
        if (recurseFirstIntersection != null) {
            return recurseFirstIntersection;
        }
        if (!z) {
            Vector3D intersection = plane.intersection(line);
            if (intersection != null) {
                SubHyperplane<Euclidean3D> boundaryFacet2 = boundaryFacet(intersection, bSPTree);
                if (boundaryFacet2 != null) {
                    return boundaryFacet2;
                }
            }
        }
        return recurseFirstIntersection(plus, vector3D, line);
    }

    private SubHyperplane<Euclidean3D> boundaryFacet(Vector3D vector3D, BSPTree<Euclidean3D> bSPTree) {
        Vector2D subSpace = ((Plane) bSPTree.getCut().getHyperplane()).toSubSpace((Vector) vector3D);
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute.getPlusOutside() != null && ((SubPlane) boundaryAttribute.getPlusOutside()).getRemainingRegion().checkPoint(subSpace) == Location.INSIDE) {
            return boundaryAttribute.getPlusOutside();
        }
        if (boundaryAttribute.getPlusInside() == null || ((SubPlane) boundaryAttribute.getPlusInside()).getRemainingRegion().checkPoint(subSpace) != Location.INSIDE) {
            return null;
        }
        return boundaryAttribute.getPlusInside();
    }

    public PolyhedronsSet rotate(Vector3D vector3D, Rotation rotation) {
        return (PolyhedronsSet) applyTransform(new RotationTransform(vector3D, rotation));
    }

    public PolyhedronsSet translate(Vector3D vector3D) {
        return (PolyhedronsSet) applyTransform(new TranslationTransform(vector3D));
    }
}
