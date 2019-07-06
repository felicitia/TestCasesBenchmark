package org.apache.commons.math3.analysis.polynomials;

import java.util.Arrays;
import org.apache.commons.math3.analysis.DifferentiableUnivariateFunction;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.differentiation.DerivativeStructure;
import org.apache.commons.math3.analysis.differentiation.UnivariateDifferentiableFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class PolynomialSplineFunction implements DifferentiableUnivariateFunction, UnivariateDifferentiableFunction {
    private final double[] knots;
    private final int n;
    private final PolynomialFunction[] polynomials;

    public PolynomialSplineFunction(double[] dArr, PolynomialFunction[] polynomialFunctionArr) {
        if (dArr == null || polynomialFunctionArr == null) {
            throw new NullArgumentException();
        } else if (dArr.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.NOT_ENOUGH_POINTS_IN_SPLINE_PARTITION, Integer.valueOf(2), Integer.valueOf(dArr.length), false);
        } else if (dArr.length - 1 != polynomialFunctionArr.length) {
            throw new DimensionMismatchException(polynomialFunctionArr.length, dArr.length);
        } else {
            MathArrays.checkOrder(dArr);
            this.n = dArr.length - 1;
            this.knots = new double[(this.n + 1)];
            System.arraycopy(dArr, 0, this.knots, 0, this.n + 1);
            this.polynomials = new PolynomialFunction[this.n];
            System.arraycopy(polynomialFunctionArr, 0, this.polynomials, 0, this.n);
        }
    }

    public double value(double d) {
        if (d < this.knots[0] || d > this.knots[this.n]) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(this.knots[0]), Double.valueOf(this.knots[this.n]));
        }
        int binarySearch = Arrays.binarySearch(this.knots, d);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        if (binarySearch >= this.polynomials.length) {
            binarySearch--;
        }
        return this.polynomials[binarySearch].value(d - this.knots[binarySearch]);
    }

    public UnivariateFunction derivative() {
        return polynomialSplineDerivative();
    }

    public PolynomialSplineFunction polynomialSplineDerivative() {
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[this.n];
        for (int i = 0; i < this.n; i++) {
            polynomialFunctionArr[i] = this.polynomials[i].polynomialDerivative();
        }
        return new PolynomialSplineFunction(this.knots, polynomialFunctionArr);
    }

    public DerivativeStructure value(DerivativeStructure derivativeStructure) {
        double value = derivativeStructure.getValue();
        if (value < this.knots[0] || value > this.knots[this.n]) {
            throw new OutOfRangeException(Double.valueOf(value), Double.valueOf(this.knots[0]), Double.valueOf(this.knots[this.n]));
        }
        int binarySearch = Arrays.binarySearch(this.knots, value);
        if (binarySearch < 0) {
            binarySearch = (-binarySearch) - 2;
        }
        if (binarySearch >= this.polynomials.length) {
            binarySearch--;
        }
        return this.polynomials[binarySearch].value(derivativeStructure.subtract(this.knots[binarySearch]));
    }

    public int getN() {
        return this.n;
    }

    public PolynomialFunction[] getPolynomials() {
        PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[this.n];
        System.arraycopy(this.polynomials, 0, polynomialFunctionArr, 0, this.n);
        return polynomialFunctionArr;
    }

    public double[] getKnots() {
        double[] dArr = new double[(this.n + 1)];
        System.arraycopy(this.knots, 0, dArr, 0, this.n + 1);
        return dArr;
    }
}
