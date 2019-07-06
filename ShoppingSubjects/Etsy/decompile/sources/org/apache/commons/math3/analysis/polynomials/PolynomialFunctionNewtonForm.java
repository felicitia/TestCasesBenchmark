package org.apache.commons.math3.analysis.polynomials;

import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class PolynomialFunctionNewtonForm implements UnivariateDifferentiableFunction {
    private final double[] a;
    private final double[] c;
    private double[] coefficients;
    private boolean coefficientsComputed = false;

    public PolynomialFunctionNewtonForm(double[] dArr, double[] dArr2) {
        verifyInputArray(dArr, dArr2);
        this.a = new double[dArr.length];
        this.c = new double[dArr2.length];
        System.arraycopy(dArr, 0, this.a, 0, dArr.length);
        System.arraycopy(dArr2, 0, this.c, 0, dArr2.length);
    }

    public double value(double d) {
        return evaluate(this.a, this.c, d);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        verifyInputArray(this.a, this.c);
        int length = this.c.length;
        DerivativeStructure derivativeStructure2 = new DerivativeStructure(derivativeStructure.getFreeParameters(), derivativeStructure.getOrder(), this.a[length]);
        for (int i = length - 1; i >= 0; i--) {
            derivativeStructure2 = derivativeStructure.subtract(this.c[i]).multiply(derivativeStructure2).add(this.a[i]);
        }
        return derivativeStructure2;
    }

    public int degree() {
        return this.c.length;
    }

    public double[] getNewtonCoefficients() {
        double[] dArr = new double[this.a.length];
        System.arraycopy(this.a, 0, dArr, 0, this.a.length);
        return dArr;
    }

    public double[] getCenters() {
        double[] dArr = new double[this.c.length];
        System.arraycopy(this.c, 0, dArr, 0, this.c.length);
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
        verifyInputArray(dArr, dArr2);
        int length = dArr2.length;
        double d2 = dArr[length];
        for (int i = length - 1; i >= 0; i--) {
            d2 = dArr[i] + ((d - dArr2[i]) * d2);
        }
        return d2;
    }

    /* access modifiers changed from: protected */
    public void computeCoefficients() {
        int degree = degree();
        this.coefficients = new double[(degree + 1)];
        for (int i = 0; i <= degree; i++) {
            this.coefficients[i] = 0.0d;
        }
        this.coefficients[0] = this.a[degree];
        for (int i2 = degree - 1; i2 >= 0; i2--) {
            for (int i3 = degree - i2; i3 > 0; i3--) {
                this.coefficients[i3] = this.coefficients[i3 - 1] - (this.c[i2] * this.coefficients[i3]);
            }
            this.coefficients[0] = this.a[i2] - (this.c[i2] * this.coefficients[0]);
        }
        this.coefficientsComputed = true;
    }

    protected static void verifyInputArray(double[] dArr, double[] dArr2) {
        if (dArr.length == 0 || dArr2.length == 0) {
            throw new NoDataException(LocalizedFormats.EMPTY_POLYNOMIALS_COEFFICIENTS_ARRAY);
        } else if (dArr.length != dArr2.length + 1) {
            throw new DimensionMismatchException(LocalizedFormats.ARRAY_SIZES_SHOULD_HAVE_DIFFERENCE_1, dArr.length, dArr2.length);
        }
    }
}
