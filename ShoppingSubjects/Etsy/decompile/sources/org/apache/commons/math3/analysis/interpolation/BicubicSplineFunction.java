package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.OutOfRangeException;

/* compiled from: BicubicSplineInterpolatingFunction */
class BicubicSplineFunction implements BivariateFunction {
    private static final short N = 4;
    private final double[][] a = ((double[][]) Array.newInstance(double.class, new int[]{4, 4}));
    private BivariateFunction partialDerivativeX;
    private BivariateFunction partialDerivativeXX;
    private BivariateFunction partialDerivativeXY;
    private BivariateFunction partialDerivativeY;
    private BivariateFunction partialDerivativeYY;

    public BicubicSplineFunction(double[] dArr) {
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                this.a[i][i2] = dArr[(4 * i2) + i];
            }
        }
    }

    public double value(double d, double d2) {
        if (d < 0.0d || d > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d), Integer.valueOf(0), Integer.valueOf(1));
        } else if (d2 < 0.0d || d2 > 1.0d) {
            throw new OutOfRangeException(Double.valueOf(d2), Integer.valueOf(0), Integer.valueOf(1));
        } else {
            double d3 = d * d;
            double[] dArr = {1.0d, d, d3, d3 * d};
            double d4 = d2 * d2;
            return apply(dArr, new double[]{1.0d, d2, d4, d4 * d2}, this.a);
        }
    }

    /* access modifiers changed from: private */
    public double apply(double[] dArr, double[] dArr2, double[][] dArr3) {
        double d = 0.0d;
        int i = 0;
        while (i < 4) {
            double d2 = d;
            for (int i2 = 0; i2 < 4; i2++) {
                d2 += dArr3[i][i2] * dArr[i] * dArr2[i2];
            }
            i++;
            d = d2;
        }
        return d;
    }

    public BivariateFunction partialDerivativeX() {
        if (this.partialDerivativeX == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeX;
    }

    public BivariateFunction partialDerivativeY() {
        if (this.partialDerivativeY == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeY;
    }

    public BivariateFunction partialDerivativeXX() {
        if (this.partialDerivativeXX == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeXX;
    }

    public BivariateFunction partialDerivativeYY() {
        if (this.partialDerivativeYY == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeYY;
    }

    public BivariateFunction partialDerivativeXY() {
        if (this.partialDerivativeXY == null) {
            computePartialDerivatives();
        }
        return this.partialDerivativeXY;
    }

    private void computePartialDerivatives() {
        final double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{4, 4});
        final double[][] dArr2 = (double[][]) Array.newInstance(double.class, new int[]{4, 4});
        final double[][] dArr3 = (double[][]) Array.newInstance(double.class, new int[]{4, 4});
        final double[][] dArr4 = (double[][]) Array.newInstance(double.class, new int[]{4, 4});
        final double[][] dArr5 = (double[][]) Array.newInstance(double.class, new int[]{4, 4});
        for (int i = 0; i < 4; i++) {
            for (int i2 = 0; i2 < 4; i2++) {
                double d = this.a[i][i2];
                dArr[i][i2] = ((double) i) * d;
                double d2 = (double) i2;
                dArr2[i][i2] = d * d2;
                dArr3[i][i2] = ((double) (i - 1)) * dArr[i][i2];
                dArr4[i][i2] = ((double) (i2 - 1)) * dArr2[i][i2];
                dArr5[i][i2] = d2 * dArr[i][i2];
            }
        }
        this.partialDerivativeX = new BivariateFunction() {
            public double value(double d, double d2) {
                double d3 = d2 * d2;
                return BicubicSplineFunction.this.apply(new double[]{0.0d, 1.0d, d, d * d}, new double[]{1.0d, d2, d3, d3 * d2}, dArr);
            }
        };
        this.partialDerivativeY = new BivariateFunction() {
            public double value(double d, double d2) {
                double d3 = d * d;
                return BicubicSplineFunction.this.apply(new double[]{1.0d, d, d3, d3 * d}, new double[]{0.0d, 1.0d, d2, d2 * d2}, dArr2);
            }
        };
        this.partialDerivativeXX = new BivariateFunction() {
            public double value(double d, double d2) {
                double[] dArr = {0.0d, 0.0d, 1.0d, d};
                double d3 = d2 * d2;
                return BicubicSplineFunction.this.apply(dArr, new double[]{1.0d, d2, d3, d3 * d2}, dArr3);
            }
        };
        this.partialDerivativeYY = new BivariateFunction() {
            public double value(double d, double d2) {
                double d3 = d * d;
                return BicubicSplineFunction.this.apply(new double[]{1.0d, d, d3, d3 * d}, new double[]{0.0d, 0.0d, 1.0d, d2}, dArr4);
            }
        };
        this.partialDerivativeXY = new BivariateFunction() {
            public double value(double d, double d2) {
                return BicubicSplineFunction.this.apply(new double[]{0.0d, 1.0d, d, d * d}, new double[]{0.0d, 1.0d, d2, d2 * d2}, dArr5);
            }
        };
    }
}
