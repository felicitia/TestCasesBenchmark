package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.partitioning.AbstractSubHyperplane;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;
import org.apache.commons.math3.geometry.partitioning.Region;
import org.apache.commons.math3.geometry.partitioning.Side;
import org.apache.commons.math3.geometry.partitioning.SubHyperplane.SplitSubHyperplane;

public class SubOrientedPoint extends AbstractSubHyperplane<Euclidean1D, Euclidean1D> {
    public double getSize() {
        return 0.0d;
    }

    public SubOrientedPoint(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> region) {
        super(hyperplane, region);
    }

    /* access modifiers changed from: protected */
    public AbstractSubHyperplane<Euclidean1D, Euclidean1D> buildNew(Hyperplane<Euclidean1D> hyperplane, Region<Euclidean1D> region) {
        return new SubOrientedPoint(hyperplane, region);
    }

    public Side side(Hyperplane<Euclidean1D> hyperplane) {
        double offset = hyperplane.getOffset(((OrientedPoint) getHyperplane()).getLocation());
        if (offset < -1.0E-10d) {
            return Side.MINUS;
        }
        return offset > 1.0E-10d ? Side.PLUS : Side.HYPER;
    }

    public SplitSubHyperplane<Euclidean1D> split(Hyperplane<Euclidean1D> hyperplane) {
        return hyperplane.getOffset(((OrientedPoint) getHyperplane()).getLocation()) < -1.0E-10d ? new SplitSubHyperplane<>(null, this) : new SplitSubHyperplane<>(this, null);
    }
}
