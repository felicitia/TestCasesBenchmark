package org.apache.commons.math3.analysis.integration.gauss;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathArrays.OrderDirection;
import org.apache.commons.math3.util.Pair;

public class GaussIntegrator {
    private final double[] points;
    private final double[] weights;

    public GaussIntegrator(double[] dArr, double[] dArr2) throws NonMonotonicSequenceException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        }
        MathArrays.checkOrder(dArr, OrderDirection.INCREASING, true, true);
        this.points = (double[]) dArr.clone();
        this.weights = (double[]) dArr2.clone();
    }

    public GaussIntegrator(Pair<double[], double[]> pair) throws NonMonotonicSequenceException {
        this((double[]) pair.getFirst(), (double[]) pair.getSecond());
    }

    public double integrate(UnivariateFunction univariateFunction) {
        double d = 0.0d;
        int i = 0;
        double d2 = 0.0d;
        while (i < this.points.length) {
            double value = (this.weights[i] * univariateFunction.value(this.points[i])) - d;
            double d3 = d2 + value;
            i++;
            double d4 = d3;
            d = (d3 - d2) - value;
            d2 = d4;
        }
        return d2;
    }

    public int getNumberOfPoints() {
        return this.points.length;
    }
}
