package com.facebook.network.connectionclass;

class ExponentialGeometricAverage {
    private int mCount;
    private final int mCutover;
    private final double mDecayConstant;
    private double mValue = -1.0d;

    public ExponentialGeometricAverage(double d) {
        int i;
        this.mDecayConstant = d;
        if (d == 0.0d) {
            i = Integer.MAX_VALUE;
        } else {
            i = (int) Math.ceil(1.0d / d);
        }
        this.mCutover = i;
    }

    public void addMeasurement(double d) {
        double d2 = 1.0d - this.mDecayConstant;
        if (this.mCount > this.mCutover) {
            this.mValue = Math.exp((d2 * Math.log(this.mValue)) + (this.mDecayConstant * Math.log(d)));
        } else if (this.mCount > 0) {
            double d3 = (d2 * ((double) this.mCount)) / (((double) this.mCount) + 1.0d);
            this.mValue = Math.exp((d3 * Math.log(this.mValue)) + ((1.0d - d3) * Math.log(d)));
        } else {
            this.mValue = d;
        }
        this.mCount++;
    }

    public double getAverage() {
        return this.mValue;
    }
}
