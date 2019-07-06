package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;

public class BicubicSplineInterpolator implements BivariateGridInterpolator {
    private int nextIndex(int i, int i2) {
        int i3 = i + 1;
        return i3 < i2 ? i3 : i3 - 1;
    }

    private int previousIndex(int i) {
        int i2 = i - 1;
        if (i2 >= 0) {
            return i2;
        }
        return 0;
    }

    public BicubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[][] dArr3) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException {
        double[] dArr4 = dArr;
        double[] dArr5 = dArr2;
        double[][] dArr6 = dArr3;
        if (dArr4.length == 0 || dArr5.length == 0 || dArr6.length == 0) {
            throw new NoDataException();
        } else if (dArr4.length != dArr6.length) {
            throw new DimensionMismatchException(dArr4.length, dArr6.length);
        } else {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            int length = dArr4.length;
            int length2 = dArr5.length;
            double[][] dArr7 = (double[][]) Array.newInstance(double.class, new int[]{length2, length});
            for (int i = 0; i < length; i++) {
                if (dArr6[i].length != length2) {
                    throw new DimensionMismatchException(dArr6[i].length, length2);
                }
                for (int i2 = 0; i2 < length2; i2++) {
                    dArr7[i2][i] = dArr6[i][i2];
                }
            }
            SplineInterpolator splineInterpolator = new SplineInterpolator();
            PolynomialSplineFunction[] polynomialSplineFunctionArr = new PolynomialSplineFunction[length2];
            for (int i3 = 0; i3 < length2; i3++) {
                polynomialSplineFunctionArr[i3] = splineInterpolator.interpolate(dArr4, dArr7[i3]);
            }
            PolynomialSplineFunction[] polynomialSplineFunctionArr2 = new PolynomialSplineFunction[length];
            for (int i4 = 0; i4 < length; i4++) {
                polynomialSplineFunctionArr2[i4] = splineInterpolator.interpolate(dArr5, dArr6[i4]);
            }
            double[][] dArr8 = (double[][]) Array.newInstance(double.class, new int[]{length, length2});
            for (int i5 = 0; i5 < length2; i5++) {
                UnivariateFunction derivative = polynomialSplineFunctionArr[i5].derivative();
                for (int i6 = 0; i6 < length; i6++) {
                    dArr8[i6][i5] = derivative.value(dArr4[i6]);
                }
            }
            double[][] dArr9 = (double[][]) Array.newInstance(double.class, new int[]{length, length2});
            for (int i7 = 0; i7 < length; i7++) {
                UnivariateFunction derivative2 = polynomialSplineFunctionArr2[i7].derivative();
                for (int i8 = 0; i8 < length2; i8++) {
                    dArr9[i7][i8] = derivative2.value(dArr5[i8]);
                }
            }
            double[][] dArr10 = (double[][]) Array.newInstance(double.class, new int[]{length, length2});
            for (int i9 = 0; i9 < length; i9++) {
                int nextIndex = nextIndex(i9, length);
                int previousIndex = previousIndex(i9);
                for (int i10 = 0; i10 < length2; i10++) {
                    int nextIndex2 = nextIndex(i10, length2);
                    int previousIndex2 = previousIndex(i10);
                    dArr10[i9][i10] = (((dArr6[nextIndex][nextIndex2] - dArr6[nextIndex][previousIndex2]) - dArr6[previousIndex][nextIndex2]) + dArr6[previousIndex][previousIndex2]) / ((dArr4[nextIndex] - dArr4[previousIndex]) * (dArr5[nextIndex2] - dArr5[previousIndex2]));
                }
            }
            BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction = new BicubicSplineInterpolatingFunction(dArr4, dArr5, dArr6, dArr8, dArr9, dArr10);
            return bicubicSplineInterpolatingFunction;
        }
    }
}
