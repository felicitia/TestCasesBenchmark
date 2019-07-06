package org.apache.commons.math3.geometry.partitioning;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import org.apache.commons.math3.exception.MathInternalError;
import org.apache.commons.math3.geometry.Space;
import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane;

public abstract class AbstractRegion<S extends Space, T extends Space> implements Region<S> {
    private Vector<S> barycenter;
    private double size;
    private BSPTree<S> tree;

    private static class BoundaryBuilder<S extends Space> implements BSPTreeVisitor<S> {
        public void visitLeafNode(BSPTree<S> bSPTree) {
        }

        private BoundaryBuilder() {
        }

        public Order visitOrder(BSPTree<S> bSPTree) {
            return Order.PLUS_MINUS_SUB;
        }

        public void visitInternalNode(BSPTree<S> bSPTree) {
            SubHyperplane subHyperplane;
            SubHyperplane[] subHyperplaneArr;
            SubHyperplane[] subHyperplaneArr2 = (SubHyperplane[]) Array.newInstance(SubHyperplane.class, 2);
            characterize(bSPTree.getPlus(), bSPTree.getCut().copySelf(), subHyperplaneArr2);
            SubHyperplane subHyperplane2 = null;
            if (subHyperplaneArr2[0] != null && !subHyperplaneArr2[0].isEmpty()) {
                SubHyperplane[] subHyperplaneArr3 = (SubHyperplane[]) Array.newInstance(SubHyperplane.class, 2);
                characterize(bSPTree.getMinus(), subHyperplaneArr2[0], subHyperplaneArr3);
                if (subHyperplaneArr3[1] != null && !subHyperplaneArr3[1].isEmpty()) {
                    subHyperplane = subHyperplaneArr3[1];
                    if (subHyperplaneArr2[1] != null && !subHyperplaneArr2[1].isEmpty()) {
                        subHyperplaneArr = (SubHyperplane[]) Array.newInstance(SubHyperplane.class, 2);
                        characterize(bSPTree.getMinus(), subHyperplaneArr2[1], subHyperplaneArr);
                        if (subHyperplaneArr[0] != null && !subHyperplaneArr[0].isEmpty()) {
                            subHyperplane2 = subHyperplaneArr[0];
                        }
                    }
                    bSPTree.setAttribute(new BoundaryAttribute(subHyperplane, subHyperplane2));
                }
            }
            subHyperplane = null;
            subHyperplaneArr = (SubHyperplane[]) Array.newInstance(SubHyperplane.class, 2);
            characterize(bSPTree.getMinus(), subHyperplaneArr2[1], subHyperplaneArr);
            subHyperplane2 = subHyperplaneArr[0];
            bSPTree.setAttribute(new BoundaryAttribute(subHyperplane, subHyperplane2));
        }

        private void characterize(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane, SubHyperplane<S>[] subHyperplaneArr) {
            if (bSPTree.getCut() != null) {
                switch (subHyperplane.side(bSPTree.getCut().getHyperplane())) {
                    case PLUS:
                        characterize(bSPTree.getPlus(), subHyperplane, subHyperplaneArr);
                        return;
                    case MINUS:
                        characterize(bSPTree.getMinus(), subHyperplane, subHyperplaneArr);
                        return;
                    case BOTH:
                        SplitSubHyperplane split = subHyperplane.split(bSPTree.getCut().getHyperplane());
                        characterize(bSPTree.getPlus(), split.getPlus(), subHyperplaneArr);
                        characterize(bSPTree.getMinus(), split.getMinus(), subHyperplaneArr);
                        return;
                    default:
                        throw new MathInternalError();
                }
            } else if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                if (subHyperplaneArr[1] == null) {
                    subHyperplaneArr[1] = subHyperplane;
                } else {
                    subHyperplaneArr[1] = subHyperplaneArr[1].reunite(subHyperplane);
                }
            } else if (subHyperplaneArr[0] == null) {
                subHyperplaneArr[0] = subHyperplane;
            } else {
                subHyperplaneArr[0] = subHyperplaneArr[0].reunite(subHyperplane);
            }
        }
    }

    private static final class Sides {
        private boolean minusFound = false;
        private boolean plusFound = false;

        public void rememberPlusFound() {
            this.plusFound = true;
        }

        public boolean plusFound() {
            return this.plusFound;
        }

        public void rememberMinusFound() {
            this.minusFound = true;
        }

        public boolean minusFound() {
            return this.minusFound;
        }
    }

    public abstract AbstractRegion<S, T> buildNew(BSPTree<S> bSPTree);

    /* access modifiers changed from: protected */
    public abstract void computeGeometricalProperties();

    protected AbstractRegion() {
        this.tree = new BSPTree<>(Boolean.TRUE);
    }

    protected AbstractRegion(BSPTree<S> bSPTree) {
        this.tree = bSPTree;
    }

    protected AbstractRegion(Collection<SubHyperplane<S>> collection) {
        if (collection.size() == 0) {
            this.tree = new BSPTree<>(Boolean.TRUE);
            return;
        }
        TreeSet treeSet = new TreeSet(new Comparator<SubHyperplane<S>>() {
            public int compare(SubHyperplane<S> subHyperplane, SubHyperplane<S> subHyperplane2) {
                if (subHyperplane2.getSize() < subHyperplane.getSize()) {
                    return -1;
                }
                return subHyperplane == subHyperplane2 ? 0 : 1;
            }
        });
        treeSet.addAll(collection);
        this.tree = new BSPTree<>();
        insertCuts(this.tree, treeSet);
        this.tree.visit(new BSPTreeVisitor<S>() {
            public void visitInternalNode(BSPTree<S> bSPTree) {
            }

            public Order visitOrder(BSPTree<S> bSPTree) {
                return Order.PLUS_SUB_MINUS;
            }

            public void visitLeafNode(BSPTree<S> bSPTree) {
                bSPTree.setAttribute(bSPTree == bSPTree.getParent().getPlus() ? Boolean.FALSE : Boolean.TRUE);
            }
        });
    }

    public AbstractRegion(Hyperplane<S>[] hyperplaneArr) {
        if (hyperplaneArr == null || hyperplaneArr.length == 0) {
            this.tree = new BSPTree<>(Boolean.FALSE);
            return;
        }
        this.tree = hyperplaneArr[0].wholeSpace().getTree(false);
        BSPTree<S> bSPTree = this.tree;
        bSPTree.setAttribute(Boolean.TRUE);
        for (Hyperplane<S> insertCut : hyperplaneArr) {
            if (bSPTree.insertCut(insertCut)) {
                bSPTree.setAttribute(null);
                bSPTree.getPlus().setAttribute(Boolean.FALSE);
                bSPTree = bSPTree.getMinus();
                bSPTree.setAttribute(Boolean.TRUE);
            }
        }
    }

    private void insertCuts(BSPTree<S> bSPTree, Collection<SubHyperplane<S>> collection) {
        Hyperplane hyperplane;
        Iterator it = collection.iterator();
        loop0:
        while (true) {
            hyperplane = null;
            while (true) {
                if (hyperplane == null && it.hasNext()) {
                    hyperplane = ((SubHyperplane) it.next()).getHyperplane();
                    if (!bSPTree.insertCut(hyperplane.copySelf())) {
                    }
                }
            }
        }
        if (it.hasNext()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            while (it.hasNext()) {
                SubHyperplane subHyperplane = (SubHyperplane) it.next();
                switch (subHyperplane.side(hyperplane)) {
                    case PLUS:
                        arrayList.add(subHyperplane);
                        break;
                    case MINUS:
                        arrayList2.add(subHyperplane);
                        break;
                    case BOTH:
                        SplitSubHyperplane split = subHyperplane.split(hyperplane);
                        arrayList.add(split.getPlus());
                        arrayList2.add(split.getMinus());
                        break;
                }
            }
            insertCuts(bSPTree.getPlus(), arrayList);
            insertCuts(bSPTree.getMinus(), arrayList2);
        }
    }

    public AbstractRegion<S, T> copySelf() {
        return buildNew(this.tree.copySelf());
    }

    public boolean isEmpty() {
        return isEmpty(this.tree);
    }

    public boolean isEmpty(BSPTree<S> bSPTree) {
        boolean z = true;
        if (bSPTree.getCut() == null) {
            return !((Boolean) bSPTree.getAttribute()).booleanValue();
        }
        if (!isEmpty(bSPTree.getMinus()) || !isEmpty(bSPTree.getPlus())) {
            z = false;
        }
        return z;
    }

    public boolean contains(Region<S> region) {
        return new RegionFactory().difference(region, this).isEmpty();
    }

    public Location checkPoint(Vector<S> vector) {
        return checkPoint(this.tree, vector);
    }

    /* access modifiers changed from: protected */
    public Location checkPoint(BSPTree<S> bSPTree, Vector<S> vector) {
        BSPTree cell = bSPTree.getCell(vector);
        if (cell.getCut() == null) {
            return ((Boolean) cell.getAttribute()).booleanValue() ? Location.INSIDE : Location.OUTSIDE;
        }
        Location checkPoint = checkPoint(cell.getMinus(), vector);
        if (checkPoint != checkPoint(cell.getPlus(), vector)) {
            checkPoint = Location.BOUNDARY;
        }
        return checkPoint;
    }

    public BSPTree<S> getTree(boolean z) {
        if (z && this.tree.getCut() != null && this.tree.getAttribute() == null) {
            this.tree.visit(new BoundaryBuilder());
        }
        return this.tree;
    }

    public double getBoundarySize() {
        BoundarySizeVisitor boundarySizeVisitor = new BoundarySizeVisitor();
        getTree(true).visit(boundarySizeVisitor);
        return boundarySizeVisitor.getSize();
    }

    public double getSize() {
        if (this.barycenter == null) {
            computeGeometricalProperties();
        }
        return this.size;
    }

    /* access modifiers changed from: protected */
    public void setSize(double d) {
        this.size = d;
    }

    public Vector<S> getBarycenter() {
        if (this.barycenter == null) {
            computeGeometricalProperties();
        }
        return this.barycenter;
    }

    /* access modifiers changed from: protected */
    public void setBarycenter(Vector<S> vector) {
        this.barycenter = vector;
    }

    public Side side(Hyperplane<S> hyperplane) {
        Sides sides = new Sides();
        recurseSides(this.tree, hyperplane.wholeHyperplane(), sides);
        return sides.plusFound() ? sides.minusFound() ? Side.BOTH : Side.PLUS : sides.minusFound() ? Side.MINUS : Side.HYPER;
    }

    private void recurseSides(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane, Sides sides) {
        if (bSPTree.getCut() == null) {
            if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
                sides.rememberPlusFound();
                sides.rememberMinusFound();
            }
            return;
        }
        switch (subHyperplane.side(bSPTree.getCut().getHyperplane())) {
            case PLUS:
                if (bSPTree.getCut().side(subHyperplane.getHyperplane()) == Side.PLUS) {
                    if (!isEmpty(bSPTree.getMinus())) {
                        sides.rememberPlusFound();
                    }
                } else if (!isEmpty(bSPTree.getMinus())) {
                    sides.rememberMinusFound();
                }
                if (!sides.plusFound() || !sides.minusFound()) {
                    recurseSides(bSPTree.getPlus(), subHyperplane, sides);
                    break;
                }
            case MINUS:
                if (bSPTree.getCut().side(subHyperplane.getHyperplane()) == Side.PLUS) {
                    if (!isEmpty(bSPTree.getPlus())) {
                        sides.rememberPlusFound();
                    }
                } else if (!isEmpty(bSPTree.getPlus())) {
                    sides.rememberMinusFound();
                }
                if (!sides.plusFound() || !sides.minusFound()) {
                    recurseSides(bSPTree.getMinus(), subHyperplane, sides);
                    break;
                }
            case BOTH:
                SplitSubHyperplane split = subHyperplane.split(bSPTree.getCut().getHyperplane());
                recurseSides(bSPTree.getPlus(), split.getPlus(), sides);
                if (!sides.plusFound() || !sides.minusFound()) {
                    recurseSides(bSPTree.getMinus(), split.getMinus(), sides);
                    break;
                }
            default:
                if (!bSPTree.getCut().getHyperplane().sameOrientationAs(subHyperplane.getHyperplane())) {
                    if (bSPTree.getPlus().getCut() != null || ((Boolean) bSPTree.getPlus().getAttribute()).booleanValue()) {
                        sides.rememberMinusFound();
                    }
                    if (bSPTree.getMinus().getCut() != null || ((Boolean) bSPTree.getMinus().getAttribute()).booleanValue()) {
                        sides.rememberPlusFound();
                        break;
                    }
                } else {
                    if (bSPTree.getPlus().getCut() != null || ((Boolean) bSPTree.getPlus().getAttribute()).booleanValue()) {
                        sides.rememberPlusFound();
                    }
                    if (bSPTree.getMinus().getCut() != null || ((Boolean) bSPTree.getMinus().getAttribute()).booleanValue()) {
                        sides.rememberMinusFound();
                        break;
                    }
                }
        }
    }

    public SubHyperplane<S> intersection(SubHyperplane<S> subHyperplane) {
        return recurseIntersection(this.tree, subHyperplane);
    }

    private SubHyperplane<S> recurseIntersection(BSPTree<S> bSPTree, SubHyperplane<S> subHyperplane) {
        if (bSPTree.getCut() == null) {
            return ((Boolean) bSPTree.getAttribute()).booleanValue() ? subHyperplane.copySelf() : null;
        }
        switch (subHyperplane.side(bSPTree.getCut().getHyperplane())) {
            case PLUS:
                return recurseIntersection(bSPTree.getPlus(), subHyperplane);
            case MINUS:
                return recurseIntersection(bSPTree.getMinus(), subHyperplane);
            case BOTH:
                SplitSubHyperplane split = subHyperplane.split(bSPTree.getCut().getHyperplane());
                SubHyperplane<S> recurseIntersection = recurseIntersection(bSPTree.getPlus(), split.getPlus());
                SubHyperplane<S> recurseIntersection2 = recurseIntersection(bSPTree.getMinus(), split.getMinus());
                if (recurseIntersection == null) {
                    return recurseIntersection2;
                }
                if (recurseIntersection2 == null) {
                    return recurseIntersection;
                }
                return recurseIntersection.reunite(recurseIntersection2);
            default:
                return recurseIntersection(bSPTree.getPlus(), recurseIntersection(bSPTree.getMinus(), subHyperplane));
        }
    }

    public AbstractRegion<S, T> applyTransform(Transform<S, T> transform) {
        return buildNew(recurseTransform(getTree(false), transform));
    }

    private BSPTree<S> recurseTransform(BSPTree<S> bSPTree, Transform<S, T> transform) {
        if (bSPTree.getCut() == null) {
            return new BSPTree<>(bSPTree.getAttribute());
        }
        AbstractSubHyperplane applyTransform = ((AbstractSubHyperplane) bSPTree.getCut()).applyTransform(transform);
        BoundaryAttribute boundaryAttribute = (BoundaryAttribute) bSPTree.getAttribute();
        if (boundaryAttribute != null) {
            AbstractSubHyperplane abstractSubHyperplane = null;
            SubHyperplane applyTransform2 = boundaryAttribute.getPlusOutside() == null ? null : ((AbstractSubHyperplane) boundaryAttribute.getPlusOutside()).applyTransform(transform);
            if (boundaryAttribute.getPlusInside() != null) {
                abstractSubHyperplane = ((AbstractSubHyperplane) boundaryAttribute.getPlusInside()).applyTransform(transform);
            }
            boundaryAttribute = new BoundaryAttribute(applyTransform2, abstractSubHyperplane);
        }
        return new BSPTree<>(applyTransform, recurseTransform(bSPTree.getPlus(), transform), recurseTransform(bSPTree.getMinus(), transform), boundaryAttribute);
    }
}
