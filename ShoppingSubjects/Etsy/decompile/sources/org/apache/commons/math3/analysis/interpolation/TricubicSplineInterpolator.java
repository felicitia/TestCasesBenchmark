package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.util.MathArrays;

public class TricubicSplineInterpolator implements TrivariateGridInterpolator {
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

    public TricubicSplineInterpolatingFunction interpolate(double[] dArr, double[] dArr2, double[] dArr3, double[][][] dArr4) throws NoDataException, DimensionMismatchException, NonMonotonicSequenceException {
        double[] dArr5 = dArr;
        double[] dArr6 = dArr2;
        double[] dArr7 = dArr3;
        double[][][] dArr8 = dArr4;
        if (dArr5.length == 0 || dArr6.length == 0 || dArr7.length == 0 || dArr8.length == 0) {
            throw new NoDataException();
        } else if (dArr5.length != dArr8.length) {
            throw new DimensionMismatchException(dArr5.length, dArr8.length);
        } else {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            MathArrays.checkOrder(dArr3);
            int length = dArr5.length;
            int length2 = dArr6.length;
            int length3 = dArr7.length;
            double[][][] dArr9 = (double[][][]) Array.newInstance(double.class, new int[]{length3, length, length2});
            double[][][] dArr10 = (double[][][]) Array.newInstance(double.class, new int[]{length2, length3, length});
            for (int i = 0; i < length; i++) {
                if (dArr8[i].length != length2) {
                    throw new DimensionMismatchException(dArr8[i].length, length2);
                }
                for (int i2 = 0; i2 < length2; i2++) {
                    if (dArr8[i][i2].length != length3) {
                        throw new DimensionMismatchException(dArr8[i][i2].length, length3);
                    }
                    for (int i3 = 0; i3 < length3; i3++) {
                        double d = dArr8[i][i2][i3];
                        dArr9[i3][i][i2] = d;
                        dArr10[i2][i3][i] = d;
                    }
                }
            }
            BicubicSplineInterpolator bicubicSplineInterpolator = new BicubicSplineInterpolator();
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr = new BicubicSplineInterpolatingFunction[length];
            for (int i4 = 0; i4 < length; i4++) {
                bicubicSplineInterpolatingFunctionArr[i4] = bicubicSplineInterpolator.interpolate(dArr6, dArr7, dArr8[i4]);
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr2 = new BicubicSplineInterpolatingFunction[length2];
            for (int i5 = 0; i5 < length2; i5++) {
                bicubicSplineInterpolatingFunctionArr2[i5] = bicubicSplineInterpolator.interpolate(dArr7, dArr5, dArr10[i5]);
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr3 = new BicubicSplineInterpolatingFunction[length3];
            for (int i6 = 0; i6 < length3; i6++) {
                bicubicSplineInterpolatingFunctionArr3[i6] = bicubicSplineInterpolator.interpolate(dArr5, dArr6, dArr9[i6]);
            }
            double[][][] dArr11 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            double[][][] dArr12 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            double[][][] dArr13 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            int i7 = 0;
            while (i7 < length3) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction = bicubicSplineInterpolatingFunctionArr3[i7];
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr4 = bicubicSplineInterpolatingFunctionArr3;
                int i8 = 0;
                while (i8 < length) {
                    double d2 = dArr5[i8];
                    int i9 = 0;
                    while (i9 < length2) {
                        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr5 = bicubicSplineInterpolatingFunctionArr;
                        BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr6 = bicubicSplineInterpolatingFunctionArr2;
                        double d3 = dArr6[i9];
                        dArr11[i8][i9][i7] = bicubicSplineInterpolatingFunction.partialDerivativeX(d2, d3);
                        dArr12[i8][i9][i7] = bicubicSplineInterpolatingFunction.partialDerivativeY(d2, d3);
                        dArr13[i8][i9][i7] = bicubicSplineInterpolatingFunction.partialDerivativeXY(d2, d3);
                        i9++;
                        bicubicSplineInterpolatingFunctionArr = bicubicSplineInterpolatingFunctionArr5;
                        bicubicSplineInterpolatingFunctionArr2 = bicubicSplineInterpolatingFunctionArr6;
                    }
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr7 = bicubicSplineInterpolatingFunctionArr;
                    BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr8 = bicubicSplineInterpolatingFunctionArr2;
                    i8++;
                    double[] dArr14 = dArr3;
                    double[][][] dArr15 = dArr4;
                }
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr9 = bicubicSplineInterpolatingFunctionArr;
                BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr10 = bicubicSplineInterpolatingFunctionArr2;
                i7++;
                bicubicSplineInterpolatingFunctionArr3 = bicubicSplineInterpolatingFunctionArr4;
                double[] dArr16 = dArr3;
                double[][][] dArr17 = dArr4;
            }
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr11 = bicubicSplineInterpolatingFunctionArr;
            BicubicSplineInterpolatingFunction[] bicubicSplineInterpolatingFunctionArr12 = bicubicSplineInterpolatingFunctionArr2;
            double[][][] dArr18 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            double[][][] dArr19 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            int i10 = 0;
            while (i10 < length) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction2 = bicubicSplineInterpolatingFunctionArr11[i10];
                int i11 = 0;
                while (i11 < length2) {
                    double[][][] dArr20 = dArr13;
                    double d4 = dArr6[i11];
                    int i12 = 0;
                    while (i12 < length3) {
                        double d5 = dArr3[i12];
                        dArr18[i10][i11][i12] = bicubicSplineInterpolatingFunction2.partialDerivativeY(d4, d5);
                        dArr19[i10][i11][i12] = bicubicSplineInterpolatingFunction2.partialDerivativeXY(d4, d5);
                        i12++;
                        double[] dArr21 = dArr;
                        double[] dArr22 = dArr2;
                    }
                    double[] dArr23 = dArr3;
                    i11++;
                    dArr13 = dArr20;
                    double[] dArr24 = dArr;
                    dArr6 = dArr2;
                }
                double[][][] dArr25 = dArr13;
                double[] dArr26 = dArr3;
                i10++;
                double[] dArr27 = dArr;
                dArr6 = dArr2;
            }
            double[][][] dArr28 = dArr13;
            double[] dArr29 = dArr3;
            double[][][] dArr30 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            for (int i13 = 0; i13 < length2; i13++) {
                BicubicSplineInterpolatingFunction bicubicSplineInterpolatingFunction3 = bicubicSplineInterpolatingFunctionArr12[i13];
                for (int i14 = 0; i14 < length3; i14++) {
                    double d6 = dArr29[i14];
                    int i15 = 0;
                    while (i15 < length) {
                        double[][][] dArr31 = dArr12;
                        double[][][] dArr32 = dArr19;
                        dArr30[i15][i13][i14] = bicubicSplineInterpolatingFunction3.partialDerivativeXY(d6, dArr[i15]);
                        i15++;
                        dArr12 = dArr31;
                        dArr19 = dArr32;
                    }
                    double[][][] dArr33 = dArr12;
                    double[][][] dArr34 = dArr19;
                    double[] dArr35 = dArr;
                }
                double[][][] dArr36 = dArr12;
                double[][][] dArr37 = dArr19;
                double[] dArr38 = dArr;
            }
            double[][][] dArr39 = dArr12;
            double[][][] dArr40 = dArr19;
            double[] dArr41 = dArr;
            double[][][] dArr42 = (double[][][]) Array.newInstance(double.class, new int[]{length, length2, length3});
            for (int i16 = 0; i16 < length; i16++) {
                int nextIndex = nextIndex(i16, length);
                int previousIndex = previousIndex(i16);
                int i17 = 0;
                while (i17 < length2) {
                    int nextIndex2 = nextIndex(i17, length2);
                    int previousIndex2 = previousIndex(i17);
                    int i18 = length;
                    for (int i19 = 0; i19 < length3; i19++) {
                        int nextIndex3 = nextIndex(i19, length3);
                        int previousIndex3 = previousIndex(i19);
                        double[][][] dArr43 = dArr4;
                        double[] dArr44 = dArr2;
                        dArr42[i16][i17][i19] = (((((((dArr43[nextIndex][nextIndex2][nextIndex3] - dArr43[nextIndex][previousIndex2][nextIndex3]) - dArr43[previousIndex][nextIndex2][nextIndex3]) + dArr43[previousIndex][previousIndex2][nextIndex3]) - dArr43[nextIndex][nextIndex2][previousIndex3]) + dArr43[nextIndex][previousIndex2][previousIndex3]) + dArr43[previousIndex][nextIndex2][previousIndex3]) - dArr43[previousIndex][previousIndex2][previousIndex3]) / (((dArr41[nextIndex] - dArr41[previousIndex]) * (dArr44[nextIndex2] - dArr44[previousIndex2])) * (dArr29[nextIndex3] - dArr29[previousIndex3]));
                    }
                    double[][][] dArr45 = dArr4;
                    double[] dArr46 = dArr2;
                    i17++;
                    length = i18;
                }
                int i20 = length;
                double[][][] dArr47 = dArr4;
                double[] dArr48 = dArr2;
            }
            TricubicSplineInterpolatingFunction tricubicSplineInterpolatingFunction = new TricubicSplineInterpolatingFunction(dArr41, dArr2, dArr29, dArr4, dArr11, dArr39, dArr18, dArr28, dArr30, dArr40, dArr42);
            return tricubicSplineInterpolatingFunction;
        }
    }
}
