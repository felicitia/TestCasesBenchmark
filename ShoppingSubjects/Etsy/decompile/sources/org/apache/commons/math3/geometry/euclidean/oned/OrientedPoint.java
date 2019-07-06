package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.Vector;
import org.apache.commons.math3.geometry.partitioning.Hyperplane;

public class OrientedPoint implements Hyperplane<Euclidean1D> {
    private boolean direct;
    private Vector1D location;

    public OrientedPoint copySelf() {
        return this;
    }

    public OrientedPoint(Vector1D vector1D, boolean z) {
        this.location = vector1D;
        this.direct = z;
    }

    public double getOffset(Vector<Euclidean1D> vector) {
        double x = ((Vector1D) vector).getX() - this.location.getX();
        return this.direct ? x : -x;
    }

    public SubOrientedPoint wholeHyperplane() {
        return new SubOrientedPoint(this, null);
    }

    public IntervalsSet wholeSpace() {
        return new IntervalsSet();
    }

    public boolean sameOrientationAs(Hyperplane<Euclidean1D> hyperplane) {
        return !(((OrientedPoint) hyperplane).direct ^ this.direct);
    }

    public Vector1D getLocation() {
        return this.location;
    }

    public boolean isDirect() {
        return this.direct;
    }

    public void revertSelf() {
        this.direct = !this.direct;
    }
}
