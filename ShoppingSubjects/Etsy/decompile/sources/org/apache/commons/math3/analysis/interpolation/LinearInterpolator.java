package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class LinearInterpolator implements UnivariateInterpolator {
    public PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        if (dArr.length != dArr2.length) {
            throw new DimensionMismatchException(dArr.length, dArr2.length);
        } else if (dArr.length < 2) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(dArr.length), Integer.valueOf(2), true);
        } else {
            int length = dArr.length - 1;
            MathArrays.checkOrder(dArr);
            double[] dArr3 = new double[length];
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                dArr3[i] = (dArr2[i2] - dArr2[i]) / (dArr[i2] - dArr[i]);
                i = i2;
            }
            PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[length];
            double[] dArr4 = new double[2];
            for (int i3 = 0; i3 < length; i3++) {
                dArr4[0] = dArr2[i3];
                dArr4[1] = dArr3[i3];
                polynomialFunctionArr[i3] = new PolynomialFunction(dArr4);
            }
            return new PolynomialSplineFunction(dArr, polynomialFunctionArr);
        }
    }
}
