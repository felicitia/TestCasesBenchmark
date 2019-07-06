package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.util.FastMath;

public class QRDecomposition {
    private RealMatrix cachedH;
    private RealMatrix cachedQ;
    private RealMatrix cachedQT;
    private RealMatrix cachedR;
    private double[][] qrt;
    private double[] rDiag;
    private final double threshold;

    private static class Solver implements DecompositionSolver {
        private final double[][] qrt;
        private final double[] rDiag;
        private final double threshold;

        private Solver(double[][] dArr, double[] dArr2, double d) {
            this.qrt = dArr;
            this.rDiag = dArr2;
            this.threshold = d;
        }

        public boolean isNonSingular() {
            for (double abs : this.rDiag) {
                if (FastMath.abs(abs) <= this.threshold) {
                    return false;
                }
            }
            return true;
        }

        public RealVector solve(RealVector realVector) {
            int length = this.qrt.length;
            int length2 = this.qrt[0].length;
            if (realVector.getDimension() != length2) {
                throw new DimensionMismatchException(realVector.getDimension(), length2);
            } else if (!isNonSingular()) {
                throw new SingularMatrixException();
            } else {
                double[] dArr = new double[length];
                double[] array = realVector.toArray();
                for (int i = 0; i < FastMath.min(length2, length); i++) {
                    double[] dArr2 = this.qrt[i];
                    double d = 0.0d;
                    for (int i2 = i; i2 < length2; i2++) {
                        d += array[i2] * dArr2[i2];
                    }
                    double d2 = d / (this.rDiag[i] * dArr2[i]);
                    for (int i3 = i; i3 < length2; i3++) {
                        array[i3] = array[i3] + (dArr2[i3] * d2);
                    }
                }
                for (int length3 = this.rDiag.length - 1; length3 >= 0; length3--) {
                    array[length3] = array[length3] / this.rDiag[length3];
                    double d3 = array[length3];
                    double[] dArr3 = this.qrt[length3];
                    dArr[length3] = d3;
                    for (int i4 = 0; i4 < length3; i4++) {
                        array[i4] = array[i4] - (dArr3[i4] * d3);
                    }
                }
                return new ArrayRealVector(dArr, false);
            }
        }

        public RealMatrix solve(RealMatrix realMatrix) {
            double d;
            int length = this.qrt.length;
            int length2 = this.qrt[0].length;
            if (realMatrix.getRowDimension() != length2) {
                throw new DimensionMismatchException(realMatrix.getRowDimension(), length2);
            } else if (!isNonSingular()) {
                throw new SingularMatrixException();
            } else {
                int columnDimension = realMatrix.getColumnDimension();
                int i = ((columnDimension + 52) - 1) / 52;
                double[][] createBlocksLayout = BlockRealMatrix.createBlocksLayout(length, columnDimension);
                double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{realMatrix.getRowDimension(), 52});
                double[] dArr2 = new double[52];
                int i2 = 0;
                while (i2 < i) {
                    int i3 = i2 * 52;
                    int min = FastMath.min(i3 + 52, columnDimension);
                    int i4 = min - i3;
                    int i5 = min - 1;
                    int i6 = i4;
                    realMatrix.copySubMatrix(0, length2 - 1, i3, i5, dArr);
                    int i7 = 0;
                    while (true) {
                        d = 1.0d;
                        if (i7 >= FastMath.min(length2, length)) {
                            break;
                        }
                        double[] dArr3 = this.qrt[i7];
                        double d2 = 1.0d / (this.rDiag[i7] * dArr3[i7]);
                        int i8 = length;
                        Arrays.fill(dArr2, 0, i6, 0.0d);
                        int i9 = i7;
                        while (i9 < length2) {
                            double d3 = dArr3[i9];
                            double[] dArr4 = dArr[i9];
                            int i10 = columnDimension;
                            for (int i11 = 0; i11 < i6; i11++) {
                                dArr2[i11] = dArr2[i11] + (dArr4[i11] * d3);
                            }
                            i9++;
                            columnDimension = i10;
                        }
                        int i12 = columnDimension;
                        for (int i13 = 0; i13 < i6; i13++) {
                            dArr2[i13] = dArr2[i13] * d2;
                        }
                        for (int i14 = i7; i14 < length2; i14++) {
                            double d4 = dArr3[i14];
                            double[] dArr5 = dArr[i14];
                            for (int i15 = 0; i15 < i6; i15++) {
                                dArr5[i15] = dArr5[i15] + (dArr2[i15] * d4);
                            }
                        }
                        i7++;
                        length = i8;
                        columnDimension = i12;
                    }
                    int i16 = length;
                    int i17 = columnDimension;
                    int length3 = this.rDiag.length - 1;
                    while (length3 >= 0) {
                        int i18 = length3 / 52;
                        int i19 = i18 * 52;
                        double d5 = d / this.rDiag[length3];
                        double[] dArr6 = dArr[length3];
                        double[] dArr7 = createBlocksLayout[(i18 * i) + i2];
                        int i20 = (length3 - i19) * i6;
                        int i21 = 0;
                        while (i21 < i6) {
                            dArr6[i21] = dArr6[i21] * d5;
                            int i22 = i20 + 1;
                            dArr7[i20] = dArr6[i21];
                            i21++;
                            i20 = i22;
                        }
                        double[] dArr8 = this.qrt[length3];
                        for (int i23 = 0; i23 < length3; i23++) {
                            double d6 = dArr8[i23];
                            double[] dArr9 = dArr[i23];
                            for (int i24 = 0; i24 < i6; i24++) {
                                dArr9[i24] = dArr9[i24] - (dArr6[i24] * d6);
                            }
                        }
                        length3--;
                        d = 1.0d;
                    }
                    i2++;
                    length = i16;
                    columnDimension = i17;
                }
                return new BlockRealMatrix(length, columnDimension, createBlocksLayout, false);
            }
        }

        public RealMatrix getInverse() {
            return solve(MatrixUtils.createRealIdentityMatrix(this.rDiag.length));
        }
    }

    public QRDecomposition(RealMatrix realMatrix) {
        this(realMatrix, 0.0d);
    }

    public QRDecomposition(RealMatrix realMatrix, double d) {
        this.threshold = d;
        int rowDimension = realMatrix.getRowDimension();
        int columnDimension = realMatrix.getColumnDimension();
        this.qrt = realMatrix.transpose().getData();
        this.rDiag = new double[FastMath.min(rowDimension, columnDimension)];
        this.cachedQ = null;
        this.cachedQT = null;
        this.cachedR = null;
        this.cachedH = null;
        for (int i = 0; i < FastMath.min(rowDimension, columnDimension); i++) {
            double[] dArr = this.qrt[i];
            double d2 = 0.0d;
            for (int i2 = i; i2 < rowDimension; i2++) {
                double d3 = dArr[i2];
                d2 += d3 * d3;
            }
            double sqrt = dArr[i] > 0.0d ? -FastMath.sqrt(d2) : FastMath.sqrt(d2);
            this.rDiag[i] = sqrt;
            if (sqrt != 0.0d) {
                dArr[i] = dArr[i] - sqrt;
                for (int i3 = i + 1; i3 < columnDimension; i3++) {
                    double[] dArr2 = this.qrt[i3];
                    double d4 = 0.0d;
                    for (int i4 = i; i4 < rowDimension; i4++) {
                        d4 -= dArr2[i4] * dArr[i4];
                    }
                    double d5 = d4 / (dArr[i] * sqrt);
                    for (int i5 = i; i5 < rowDimension; i5++) {
                        dArr2[i5] = dArr2[i5] - (dArr[i5] * d5);
                    }
                }
            }
        }
    }

    public RealMatrix getR() {
        if (this.cachedR == null) {
            int length = this.qrt.length;
            int length2 = this.qrt[0].length;
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length2, length});
            for (int min = FastMath.min(length2, length) - 1; min >= 0; min--) {
                dArr[min][min] = this.rDiag[min];
                for (int i = min + 1; i < length; i++) {
                    dArr[min][i] = this.qrt[i][min];
                }
            }
            this.cachedR = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedR;
    }

    public RealMatrix getQ() {
        if (this.cachedQ == null) {
            this.cachedQ = getQT().transpose();
        }
        return this.cachedQ;
    }

    public RealMatrix getQT() {
        double d;
        if (this.cachedQT == null) {
            int length = this.qrt.length;
            int length2 = this.qrt[0].length;
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length2, length2});
            int i = length2 - 1;
            while (true) {
                d = 1.0d;
                if (i < FastMath.min(length2, length)) {
                    break;
                }
                dArr[i][i] = 1.0d;
                i--;
            }
            int min = FastMath.min(length2, length) - 1;
            while (min >= 0) {
                double[] dArr2 = this.qrt[min];
                dArr[min][min] = d;
                if (dArr2[min] != 0.0d) {
                    for (int i2 = min; i2 < length2; i2++) {
                        double d2 = 0.0d;
                        for (int i3 = min; i3 < length2; i3++) {
                            d2 -= dArr[i2][i3] * dArr2[i3];
                        }
                        double d3 = d2 / (this.rDiag[min] * dArr2[min]);
                        for (int i4 = min; i4 < length2; i4++) {
                            double[] dArr3 = dArr[i2];
                            dArr3[i4] = dArr3[i4] + ((-d3) * dArr2[i4]);
                        }
                    }
                }
                min--;
                d = 1.0d;
            }
            this.cachedQT = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedQT;
    }

    public RealMatrix getH() {
        int i;
        if (this.cachedH == null) {
            int length = this.qrt.length;
            int length2 = this.qrt[0].length;
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length2, length});
            int i2 = 0;
            while (i2 < length2) {
                int i3 = 0;
                while (true) {
                    i = i2 + 1;
                    if (i3 >= FastMath.min(i, length)) {
                        break;
                    }
                    dArr[i2][i3] = this.qrt[i3][i2] / (-this.rDiag[i3]);
                    i3++;
                }
                i2 = i;
            }
            this.cachedH = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedH;
    }

    public DecompositionSolver getSolver() {
        Solver solver = new Solver(this.qrt, this.rDiag, this.threshold);
        return solver;
    }
}
