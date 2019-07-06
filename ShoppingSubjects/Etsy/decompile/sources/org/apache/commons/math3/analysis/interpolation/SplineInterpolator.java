package org.apache.commons.math3.analysis.interpolation;

import org.apache.commons.math3.analysis.polynomials.PolynomialFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathArrays;

public class SplineInterpolator implements UnivariateInterpolator {
    public PolynomialSplineFunction interpolate(double[] dArr, double[] dArr2) throws DimensionMismatchException, NumberIsTooSmallException, NonMonotonicSequenceException {
        double[] dArr3 = dArr;
        double[] dArr4 = dArr2;
        if (dArr3.length != dArr4.length) {
            throw new DimensionMismatchException(dArr3.length, dArr4.length);
        } else if (dArr3.length < 3) {
            throw new NumberIsTooSmallException(LocalizedFormats.NUMBER_OF_POINTS, Integer.valueOf(dArr3.length), Integer.valueOf(3), true);
        } else {
            int length = dArr3.length - 1;
            MathArrays.checkOrder(dArr);
            double[] dArr5 = new double[length];
            int i = 0;
            while (i < length) {
                int i2 = i + 1;
                dArr5[i] = dArr3[i2] - dArr3[i];
                i = i2;
            }
            double[] dArr6 = new double[length];
            int i3 = length + 1;
            double[] dArr7 = new double[i3];
            dArr6[0] = 0.0d;
            dArr7[0] = 0.0d;
            int i4 = 1;
            while (i4 < length) {
                int i5 = i4 + 1;
                int i6 = i4 - 1;
                double d = (2.0d * (dArr3[i5] - dArr3[i6])) - (dArr5[i6] * dArr6[i6]);
                dArr6[i4] = dArr5[i4] / d;
                dArr7[i4] = (((3.0d * (((dArr4[i5] * dArr5[i6]) - (dArr4[i4] * (dArr3[i5] - dArr3[i6]))) + (dArr4[i6] * dArr5[i4]))) / (dArr5[i6] * dArr5[i4])) - (dArr5[i6] * dArr7[i6])) / d;
                i4 = i5;
            }
            double[] dArr8 = new double[length];
            double[] dArr9 = new double[i3];
            double[] dArr10 = new double[length];
            dArr7[length] = 0.0d;
            dArr9[length] = 0.0d;
            for (int i7 = length - 1; i7 >= 0; i7--) {
                int i8 = i7 + 1;
                dArr9[i7] = dArr7[i7] - (dArr6[i7] * dArr9[i8]);
                dArr8[i7] = ((dArr4[i8] - dArr4[i7]) / dArr5[i7]) - ((dArr5[i7] * (dArr9[i8] + (dArr9[i7] * 2.0d))) / 3.0d);
                dArr10[i7] = (dArr9[i8] - dArr9[i7]) / (dArr5[i7] * 3.0d);
            }
            PolynomialFunction[] polynomialFunctionArr = new PolynomialFunction[length];
            double[] dArr11 = new double[4];
            for (int i9 = 0; i9 < length; i9++) {
                dArr11[0] = dArr4[i9];
                dArr11[1] = dArr8[i9];
                dArr11[2] = dArr9[i9];
                dArr11[3] = dArr10[i9];
                polynomialFunctionArr[i9] = new PolynomialFunction(dArr11);
            }
            return new PolynomialSplineFunction(dArr3, polynomialFunctionArr);
        }
    }
}
