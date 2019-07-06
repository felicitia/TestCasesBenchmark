package org.apache.commons.math3.analysis.interpolation;

import java.lang.reflect.Array;
import org.apache.commons.math3.analysis.BivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NonMonotonicSequenceException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.util.MathArrays;

public class BicubicSplineInterpolatingFunction implements BivariateFunction {
    private static final double[][] AINV = {new double[]{1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{-3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 3.0d, 0.0d, 0.0d, -2.0d, -1.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 2.0d, -2.0d, 0.0d, 0.0d, 1.0d, 1.0d, 0.0d, 0.0d}, new double[]{-3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, -3.0d, 0.0d, 3.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, -2.0d, 0.0d, -1.0d, 0.0d}, new double[]{9.0d, -9.0d, -9.0d, 9.0d, 6.0d, 3.0d, -6.0d, -3.0d, 6.0d, -6.0d, 3.0d, -3.0d, 4.0d, 2.0d, 2.0d, 1.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -3.0d, -3.0d, 3.0d, 3.0d, -4.0d, 4.0d, -2.0d, 2.0d, -2.0d, -2.0d, -1.0d, -1.0d}, new double[]{2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d}, new double[]{0.0d, 0.0d, 0.0d, 0.0d, 2.0d, 0.0d, -2.0d, 0.0d, 0.0d, 0.0d, 0.0d, 0.0d, 1.0d, 0.0d, 1.0d, 0.0d}, new double[]{-6.0d, 6.0d, 6.0d, -6.0d, -4.0d, -2.0d, 4.0d, 2.0d, -3.0d, 3.0d, -3.0d, 3.0d, -2.0d, -1.0d, -2.0d, -1.0d}, new double[]{4.0d, -4.0d, -4.0d, 4.0d, 2.0d, 2.0d, -2.0d, -2.0d, 2.0d, -2.0d, 2.0d, -2.0d, 1.0d, 1.0d, 1.0d, 1.0d}};
    private BivariateFunction[][][] partialDerivatives = null;
    private final BicubicSplineFunction[][] splines;
    private final double[] xval;
    private final double[] yval;

    public BicubicSplineInterpolatingFunction(double[] dArr, double[] dArr2, double[][] dArr3, double[][] dArr4, double[][] dArr5, double[][] dArr6) throws DimensionMismatchException, NoDataException, NonMonotonicSequenceException {
        double[][] dArr7 = dArr3;
        double[][] dArr8 = dArr4;
        double[][] dArr9 = dArr5;
        double[][] dArr10 = dArr6;
        int length = dArr.length;
        int length2 = dArr2.length;
        if (length == 0 || length2 == 0 || dArr7.length == 0 || dArr7[0].length == 0) {
            throw new NoDataException();
        } else if (length != dArr7.length) {
            throw new DimensionMismatchException(length, dArr7.length);
        } else if (length != dArr8.length) {
            throw new DimensionMismatchException(length, dArr8.length);
        } else if (length != dArr9.length) {
            throw new DimensionMismatchException(length, dArr9.length);
        } else if (length != dArr10.length) {
            throw new DimensionMismatchException(length, dArr10.length);
        } else {
            MathArrays.checkOrder(dArr);
            MathArrays.checkOrder(dArr2);
            this.xval = (double[]) dArr.clone();
            this.yval = (double[]) dArr2.clone();
            char c = 1;
            int i = length - 1;
            int i2 = length2 - 1;
            this.splines = (BicubicSplineFunction[][]) Array.newInstance(BicubicSplineFunction.class, new int[]{i, i2});
            int i3 = 0;
            while (i3 < i) {
                if (dArr7[i3].length != length2) {
                    throw new DimensionMismatchException(dArr7[i3].length, length2);
                } else if (dArr8[i3].length != length2) {
                    throw new DimensionMismatchException(dArr8[i3].length, length2);
                } else if (dArr9[i3].length != length2) {
                    throw new DimensionMismatchException(dArr9[i3].length, length2);
                } else if (dArr10[i3].length != length2) {
                    throw new DimensionMismatchException(dArr10[i3].length, length2);
                } else {
                    int i4 = i3 + 1;
                    int i5 = 0;
                    while (i5 < i2) {
                        int i6 = i5 + 1;
                        double[] dArr11 = new double[16];
                        dArr11[0] = dArr7[i3][i5];
                        dArr11[c] = dArr7[i4][i5];
                        dArr11[2] = dArr7[i3][i6];
                        dArr11[3] = dArr7[i4][i6];
                        dArr11[4] = dArr8[i3][i5];
                        dArr11[5] = dArr8[i4][i5];
                        dArr11[6] = dArr8[i3][i6];
                        dArr11[7] = dArr8[i4][i6];
                        dArr11[8] = dArr9[i3][i5];
                        dArr11[9] = dArr9[i4][i5];
                        dArr11[10] = dArr9[i3][i6];
                        dArr11[11] = dArr9[i4][i6];
                        dArr11[12] = dArr10[i3][i5];
                        dArr11[13] = dArr10[i4][i5];
                        dArr11[14] = dArr10[i3][i6];
                        dArr11[15] = dArr10[i4][i6];
                        this.splines[i3][i5] = new BicubicSplineFunction(computeSplineCoefficients(dArr11));
                        i5 = i6;
                        c = 1;
                    }
                    i3 = i4;
                }
            }
        }
    }

    public double value(double d, double d2) throws OutOfRangeException {
        int searchIndex = searchIndex(d, this.xval);
        if (searchIndex == -1) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(this.xval[0]), Double.valueOf(this.xval[this.xval.length - 1]));
        }
        int searchIndex2 = searchIndex(d2, this.yval);
        if (searchIndex2 == -1) {
            throw new OutOfRangeException(Double.valueOf(d2), Double.valueOf(this.yval[0]), Double.valueOf(this.yval[this.yval.length - 1]));
        }
        return this.splines[searchIndex][searchIndex2].value((d - this.xval[searchIndex]) / (this.xval[searchIndex + 1] - this.xval[searchIndex]), (d2 - this.yval[searchIndex2]) / (this.yval[searchIndex2 + 1] - this.yval[searchIndex2]));
    }

    public double partialDerivativeX(double d, double d2) throws OutOfRangeException {
        return partialDerivative(0, d, d2);
    }

    public double partialDerivativeY(double d, double d2) throws OutOfRangeException {
        return partialDerivative(1, d, d2);
    }

    public double partialDerivativeXX(double d, double d2) throws OutOfRangeException {
        return partialDerivative(2, d, d2);
    }

    public double partialDerivativeYY(double d, double d2) throws OutOfRangeException {
        return partialDerivative(3, d, d2);
    }

    public double partialDerivativeXY(double d, double d2) throws OutOfRangeException {
        return partialDerivative(4, d, d2);
    }

    private double partialDerivative(int i, double d, double d2) throws OutOfRangeException {
        if (this.partialDerivatives == null) {
            computePartialDerivatives();
        }
        int searchIndex = searchIndex(d, this.xval);
        if (searchIndex == -1) {
            throw new OutOfRangeException(Double.valueOf(d), Double.valueOf(this.xval[0]), Double.valueOf(this.xval[this.xval.length - 1]));
        }
        int searchIndex2 = searchIndex(d2, this.yval);
        if (searchIndex2 == -1) {
            throw new OutOfRangeException(Double.valueOf(d2), Double.valueOf(this.yval[0]), Double.valueOf(this.yval[this.yval.length - 1]));
        }
        return this.partialDerivatives[i][searchIndex][searchIndex2].value((d - this.xval[searchIndex]) / (this.xval[searchIndex + 1] - this.xval[searchIndex]), (d2 - this.yval[searchIndex2]) / (this.yval[searchIndex2 + 1] - this.yval[searchIndex2]));
    }

    private void computePartialDerivatives() {
        int length = this.xval.length - 1;
        int length2 = this.yval.length - 1;
        this.partialDerivatives = (BivariateFunction[][][]) Array.newInstance(BivariateFunction.class, new int[]{5, length, length2});
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2++) {
                BicubicSplineFunction bicubicSplineFunction = this.splines[i][i2];
                this.partialDerivatives[0][i][i2] = bicubicSplineFunction.partialDerivativeX();
                this.partialDerivatives[1][i][i2] = bicubicSplineFunction.partialDerivativeY();
                this.partialDerivatives[2][i][i2] = bicubicSplineFunction.partialDerivativeXX();
                this.partialDerivatives[3][i][i2] = bicubicSplineFunction.partialDerivativeYY();
                this.partialDerivatives[4][i][i2] = bicubicSplineFunction.partialDerivativeXY();
            }
        }
    }

    private int searchIndex(double d, double[] dArr) {
        if (d < dArr[0]) {
            return -1;
        }
        int length = dArr.length;
        for (int i = 1; i < length; i++) {
            if (d <= dArr[i]) {
                return i - 1;
            }
        }
        return -1;
    }

    private double[] computeSplineCoefficients(double[] dArr) {
        double[] dArr2 = new double[16];
        for (int i = 0; i < 16; i++) {
            double[] dArr3 = AINV[i];
            double d = 0.0d;
            for (int i2 = 0; i2 < 16; i2++) {
                d += dArr3[i2] * dArr[i2];
            }
            dArr2[i] = d;
        }
        return dArr2;
    }
}
