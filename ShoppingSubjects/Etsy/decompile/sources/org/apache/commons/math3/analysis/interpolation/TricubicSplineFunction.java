package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.TrivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/* compiled from: TricubicSplineInterpolatingFunction */
class TricubicSplineFunction implements TrivariateFunction {
    private static final short N = 4;
    private final double[][][] a = ((double[][][]) Array.newInstance(double.class, new int[]{4, 4, 4}));

    public TricubicSplineFunction(double[] dArr) {
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                for (int i3 = 0; i3 < 4; i3++) {
                    this.a[i][i2][i3] = dArr[(((4 * i3) + i2) * 4) + i];
                }
            }
        }
    }

    public double value(double d, double d2, double d3) throws OutOfRangeException {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        } else if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), Integer.valueOf(0), Integer.valueOf(1));
        } else if (d3 < 0.0d || d3 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d3), Integer.valueOf(0), Integer.valueOf(1));
        } else {
            double d4 = d * d;
            double[] dArr = {1.0d, d, d4, d4 * d};
            double d5 = d2 * d2;
            double[] dArr2 = {1.0d, d2, d5, d5 * d2};
            double d6 = d3 * d3;
            double[] dArr3 = {1.0d, d3, d6, d6 * d3};
            double d7 = 0.0d;
            for (int i = 0; i < 4; i++) {
                for (int i2 = 0; i2 < 4; i2++) {
                    for (int i3 = 0; i3 < 4; i3++) {
                        d7 += this.a[i][i2][i3] * dArr[i] * dArr2[i2] * dArr3[i3];
                    }
                }
            }
            return d7;
        }
    }
}
