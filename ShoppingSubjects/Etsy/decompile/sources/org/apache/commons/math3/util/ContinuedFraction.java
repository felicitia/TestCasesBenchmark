package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.ConvergenceException;
import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public abstract class ContinuedFraction {
    private static final double DEFAULT_EPSILON = 1.0E-8d;

    /* access modifiers changed from: protected */
    public abstract double getA(int i, double d);

    /* access modifiers changed from: protected */
    public abstract double getB(int i, double d);

    protected ContinuedFraction() {
    }

    public double evaluate(double d) throws ConvergenceException {
        return evaluate(d, 1.0E-8d, Integer.MAX_VALUE);
    }

    public double evaluate(double d, double d2) throws ConvergenceException {
        return evaluate(d, d2, Integer.MAX_VALUE);
    }

    public double evaluate(double d, int i) throws ConvergenceException, MaxCountExceededException {
        return evaluate(d, 1.0E-8d, i);
    }

    public double evaluate(double d, double d2, int i) throws ConvergenceException, MaxCountExceededException {
        double d3;
        double d4 = d;
        int i2 = i;
        double a = getA(0, d4);
        if (Precision.equals(a, 0.0d, 1.0E-50d)) {
            a = 1.0E-50d;
        }
        double d5 = 0.0d;
        double d6 = d3;
        int i3 = 1;
        while (i3 < i2) {
            double a2 = getA(i3, d4);
            double b = getB(i3, d4);
            double d7 = (d5 * b) + a2;
            if (Precision.equals(d7, 0.0d, 1.0E-50d)) {
                d7 = 1.0E-50d;
            }
            d3 = a2 + (b / d3);
            if (Precision.equals(d3, 0.0d, 1.0E-50d)) {
                d3 = 1.0E-50d;
            }
            d5 = 1.0d / d7;
            double d8 = d3 * d5;
            d6 *= d8;
            if (Double.isInfinite(d6)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_INFINITY_DIVERGENCE, Double.valueOf(d));
            } else if (Double.isNaN(d6)) {
                throw new ConvergenceException(LocalizedFormats.CONTINUED_FRACTION_NAN_DIVERGENCE, Double.valueOf(d));
            } else if (FastMath.abs(d8 - 1.0d) < d2) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 < i2) {
            return d6;
        }
        throw new MaxCountExceededException(LocalizedFormats.NON_CONVERGENT_CONTINUED_FRACTION, Integer.valueOf(i), Double.valueOf(d));
    }
}
