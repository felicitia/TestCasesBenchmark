package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathArrays;
import org.apache.commons.math3.util.MathArrays.OrderDirection;

public class PolynomialFunctionLagrangeForm implements UnivariateFunction {
    private double[] coefficients;
    private boolean coefficientsComputed = false;
    private final double[] x;
    private final double[] y;

    public PolynomialFunctionLagrangeForm(double[] dArr, double[] dArr2) {
        this.x = new double[dArr.length];
        this.y = new double[dArr2.length];
        System.arraycopy(dArr, 0, this.x, 0, dArr.length);
        System.arraycopy(dArr2, 0, this.y, 0, dArr2.length);
        if (!verifyInterpolationArray(dArr, dArr2, false)) {
            MathArrays.sortInPlace(this.x, this.y);
            verifyInterpolationArray(this.x, this.y, true);
        }
    }

    public double value(double d) {
        return evaluateInternal(this.x, this.y, d);
    }

    public int degree() {
        return this.x.length - 1;
    }

    public double[] getInterpolatingPoints() {
        double[] dArr = new double[this.x.length];
        System.arraycopy(this.x, 0, dArr, 0, this.x.length);
        return dArr;
    }

    public double[] getInterpolatingValues() {
        double[] dArr = new double[this.y.length];
        System.arraycopy(this.y, 0, dArr, 0, this.y.length);
        return dArr;
    }

    public double[] getCoefficients() {
        if (!this.coefficientsComputed) {
            computeCoefficients();
        }
        double[] dArr = new double[this.coefficients.length];
        System.arraycopy(this.coefficients, 0, dArr, 0, this.coefficients.length);
        return dArr;
    }

    public static double evaluate(double[] dArr, double[] dArr2, double d) {
        if (verifyInterpolationArray(dArr, dArr2, false)) {
            return evaluateInternal(dArr, dArr2, d);
        }
        double[] dArr3 = new double[dArr.length];
        double[] dArr4 = new double[dArr2.length];
        System.arraycopy(dArr, 0, dArr3, 0, dArr.length);
        System.arraycopy(dArr2, 0, dArr4, 0, dArr2.length);
        MathArrays.sortInPlace(dArr3, dArr4);
        verifyInterpolationArray(dArr3, dArr4, true);
        return evaluateInternal(dArr3, dArr4, d);
    }

    private static double evaluateInternal(double[] dArr, double[] dArr2, double d) {
        int i;
        double d2;
        double[] dArr3 = dArr;
        int length = dArr3.length;
        double[] dArr4 = new double[length];
        double[] dArr5 = new double[length];
        double d3 = Double.POSITIVE_INFINITY;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            dArr4[i3] = dArr2[i3];
            dArr5[i3] = dArr2[i3];
            double abs = FastMath.abs(d - dArr3[i3]);
            if (abs < d3) {
                i2 = i3;
                d3 = abs;
            }
        }
        double d4 = dArr2[i2];
        for (int i4 = 1; i4 < length; i4++) {
            int i5 = 0;
            while (true) {
                i = length - i4;
                if (i5 >= i) {
                    break;
                }
                int i6 = i4 + i5;
                double d5 = dArr3[i6] - d;
                double d6 = dArr3[i5] - dArr3[i6];
                int i7 = i5 + 1;
                double d7 = (dArr4[i7] - dArr5[i5]) / d6;
                dArr4[i5] = (dArr3[i5] - d) * d7;
                dArr5[i5] = d5 * d7;
                i5 = i7;
            }
            if (((double) i2) < 0.5d * ((double) (i + 1))) {
                d2 = dArr4[i2];
            } else {
                i2--;
                d2 = dArr5[i2];
            }
            d4 += d2;
        }
        return d4;
    }

    /* access modifiers changed from: protected */
    public void computeCoefficients() {
        int degree = degree() + 1;
        this.coefficients = new double[degree];
        int i = 0;
        for (int i2 = 0; i2 < degree; i2++) {
            this.coefficients[i2] = 0.0d;
        }
        double[] dArr = new double[(degree + 1)];
        dArr[0] = 1.0d;
        int i3 = 0;
        while (i3 < degree) {
            for (int i4 = i3; i4 > 0; i4--) {
                dArr[i4] = dArr[i4 - 1] - (dArr[i4] * this.x[i3]);
            }
            dArr[0] = dArr[0] * (-this.x[i3]);
            i3++;
            dArr[i3] = 1.0d;
        }
        double[] dArr2 = new double[degree];
        int i5 = 0;
        while (i5 < degree) {
            double d = 1.0d;
            for (int i6 = i; i6 < degree; i6++) {
                if (i5 != i6) {
                    d *= this.x[i5] - this.x[i6];
                }
            }
            double d2 = this.y[i5] / d;
            int i7 = degree - 1;
            dArr2[i7] = dArr[degree];
            double[] dArr3 = this.coefficients;
            dArr3[i7] = dArr3[i7] + (dArr2[i7] * d2);
            for (int i8 = degree - 2; i8 >= 0; i8--) {
                int i9 = i8 + 1;
                dArr2[i8] = dArr[i9] + (dArr2[i9] * this.x[i5]);
                double[] dArr4 = this.coefficients;
                dArr4[i8] = dArr4[i8] + (dArr2[i8] * d2);
            }
            i5++;
            i = 0;
        }
        this.coefficientsComputed = true;
    }

    public static boolean verifyInterpolationArray(double[] dArr, double[] dArr2, boolean z) {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length >= 2) {
            return MathArrays.checkOrder(dArr, OrderDirection.INCREASING, true, z);
        } else {
            throw new NumberIsTooSmallException(LocalizedFormats.WRONG_NUMBER_OF_POINTS, Integer.valueOf(2), Integer.valueOf(dArr.length), true);
        }
    }
}
