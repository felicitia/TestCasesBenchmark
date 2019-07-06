package org.apache.commons.math3.geometry.euclidean.oned;

import org.apache.commons.math3.geometry.partitioning.Region.Location;

public class Interval {
    private final double lower;
    private final double upper;

    public Interval(double d, double d2) {
        this.lower = d;
        this.upper = d2;
    }

    public double getInf() {
        return this.lower;
    }

    @Deprecated
    public double getLower() {
        return getInf();
    }

    public double getSup() {
        return this.upper;
    }

    @Deprecated
    public double getUpper() {
        return getSup();
    }

    public double getSize() {
        return this.upper - this.lower;
    }

    @Deprecated
    public double getLength() {
        return getSize();
    }

    public double getBarycenter() {
        return 0.5d * (this.lower + this.upper);
    }

    @Deprecated
    public double getMidPoint() {
        return getBarycenter();
    }

    public Location checkPoint(double d, double d2) {
        if (d < this.lower - d2 || d > this.upper + d2) {
            return Location.OUTSIDE;
        }
        if (d <= this.lower + d2 || d >= this.upper - d2) {
            return Location.BOUNDARY;
        }
        return Location.INSIDE;
    }
}
