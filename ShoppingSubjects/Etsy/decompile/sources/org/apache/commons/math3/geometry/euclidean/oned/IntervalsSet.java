package org.apache.commons.math3.geometry.euclidean.oned;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.math3.geometry.partitioning.AbstractRegion;
import org.apache.commons.math3.geometry.partitioning.BSPTree;
import org.apache.commons.math3.geometry.partitioning.Region.Location;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane;
import org.apache.commons.math3.util.Precision;

public class IntervalsSet extends AbstractRegion<Euclidean1D, Euclidean1D> {
    public IntervalsSet() {
    }

    public IntervalsSet(double d, double d2) {
        super(buildTree(d, d2));
    }

    public IntervalsSet(BSPTree<Euclidean1D> bSPTree) {
        super(bSPTree);
    }

    public IntervalsSet(Collection<SubHyperplane<Euclidean1D>> collection) {
        super(collection);
    }

    private static BSPTree<Euclidean1D> buildTree(double d, double d2) {
        if (!Double.isInfinite(d) || d >= 0.0d) {
            SubOrientedPoint wholeHyperplane = new OrientedPoint(new Vector1D(d), false).wholeHyperplane();
            if (Double.isInfinite(d2) && d2 > 0.0d) {
                return new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
            }
            return new BSPTree<>(wholeHyperplane, new BSPTree(Boolean.FALSE), new BSPTree(new OrientedPoint(new Vector1D(d2), true).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null), null);
        } else if (!Double.isInfinite(d2) || d2 <= 0.0d) {
            return new BSPTree<>(new OrientedPoint(new Vector1D(d2), true).wholeHyperplane(), new BSPTree(Boolean.FALSE), new BSPTree(Boolean.TRUE), null);
        } else {
            return new BSPTree<>(Boolean.TRUE);
        }
    }

    public IntervalsSet buildNew(BSPTree<Euclidean1D> bSPTree) {
        return new IntervalsSet(bSPTree);
    }

    /* access modifiers changed from: protected */
    public void computeGeometricalProperties() {
        double d = 0.0d;
        if (getTree(false).getCut() == null) {
            setBarycenter(Vector1D.NaN);
            if (((Boolean) getTree(false).getAttribute()).booleanValue()) {
                d = Double.POSITIVE_INFINITY;
            }
            setSize(d);
            return;
        }
        double d2 = 0.0d;
        for (Interval interval : asList()) {
            d += interval.getSize();
            d2 += interval.getSize() * interval.getBarycenter();
        }
        setSize(d);
        if (Double.isInfinite(d)) {
            setBarycenter(Vector1D.NaN);
        } else if (d >= Precision.SAFE_MIN) {
            setBarycenter(new Vector1D(d2 / d));
        } else {
            setBarycenter(((OrientedPoint) getTree(false).getCut().getHyperplane()).getLocation());
        }
    }

    public double getInf() {
        BSPTree tree = getTree(false);
        double d = Double.POSITIVE_INFINITY;
        while (tree.getCut() != null) {
            OrientedPoint orientedPoint = (OrientedPoint) tree.getCut().getHyperplane();
            double x = orientedPoint.getLocation().getX();
            tree = orientedPoint.isDirect() ? tree.getMinus() : tree.getPlus();
            d = x;
        }
        if (((Boolean) tree.getAttribute()).booleanValue()) {
            return Double.NEGATIVE_INFINITY;
        }
        return d;
    }

    public double getSup() {
        BSPTree tree = getTree(false);
        double d = Double.NEGATIVE_INFINITY;
        while (tree.getCut() != null) {
            OrientedPoint orientedPoint = (OrientedPoint) tree.getCut().getHyperplane();
            double x = orientedPoint.getLocation().getX();
            tree = orientedPoint.isDirect() ? tree.getPlus() : tree.getMinus();
            d = x;
        }
        if (((Boolean) tree.getAttribute()).booleanValue()) {
            return Double.POSITIVE_INFINITY;
        }
        return d;
    }

    public List<Interval> asList() {
        ArrayList arrayList = new ArrayList();
        recurseList(getTree(false), arrayList, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
        return arrayList;
    }

    private void recurseList(BSPTree<Euclidean1D> bSPTree, List<Interval> list, double d, double d2) {
        List<Interval> list2 = list;
        if (bSPTree.getCut() != null) {
            double d3 = d;
            double d4 = d2;
            OrientedPoint orientedPoint = (OrientedPoint) bSPTree.getCut().getHyperplane();
            Vector1D location = orientedPoint.getLocation();
            double x = location.getX();
            BSPTree minus = orientedPoint.isDirect() ? bSPTree.getMinus() : bSPTree.getPlus();
            BSPTree plus = orientedPoint.isDirect() ? bSPTree.getPlus() : bSPTree.getMinus();
            recurseList(minus, list2, d3, x);
            recurseList(plus, list2, (checkPoint(minus, location) == Location.INSIDE && checkPoint(plus, location) == Location.INSIDE) ? ((Interval) list2.remove(list.size() - 1)).getInf() : x, d4);
        } else if (((Boolean) bSPTree.getAttribute()).booleanValue()) {
            list2.add(new Interval(d, d2));
        }
    }
}
