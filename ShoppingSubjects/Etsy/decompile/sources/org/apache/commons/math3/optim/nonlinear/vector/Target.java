package org.apache.commons.math3.optim.nonlinear.vector;

import org.apache.commons.math3.optim.OptimizationData;

public class Target implements OptimizationData {
    private final double[] target;

    public Target(double[] dArr) {
        this.target = (double[]) dArr.clone();
    }

    public double[] getTarget() {
        return (double[]) this.target.clone();
    }
}