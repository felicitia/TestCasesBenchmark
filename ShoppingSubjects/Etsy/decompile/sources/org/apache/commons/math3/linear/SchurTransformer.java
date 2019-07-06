package org.apache.commons.math3.linear;

import org.apache.commons.math3.exception.MaxCountExceededException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class SchurTransformer {
    private static final int MAX_ITERATIONS = 100;
    private RealMatrix cachedP;
    private RealMatrix cachedPt;
    private RealMatrix cachedT;
    private final double epsilon = Precision.EPSILON;
    private final double[][] matrixP;
    private final double[][] matrixT;

    private static class ShiftInfo {
        double exShift;
        double w;
        double x;
        double y;

        private ShiftInfo() {
        }
    }

    public SchurTransformer(RealMatrix realMatrix) {
        if (!realMatrix.isSquare()) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        }
        HessenbergTransformer hessenbergTransformer = new HessenbergTransformer(realMatrix);
        this.matrixT = hessenbergTransformer.getH().getData();
        this.matrixP = hessenbergTransformer.getP().getData();
        this.cachedT = null;
        this.cachedP = null;
        this.cachedPt = null;
        transform();
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            this.cachedP = MatrixUtils.createRealMatrix(this.matrixP);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getT() {
        if (this.cachedT == null) {
            this.cachedT = MatrixUtils.createRealMatrix(this.matrixT);
        }
        return this.cachedT;
    }

    private void transform() {
        int i;
        int length = this.matrixT.length;
        double norm = getNorm();
        ShiftInfo shiftInfo = new ShiftInfo();
        int i2 = length - 1;
        int i3 = i2;
        int i4 = 0;
        while (i3 >= 0) {
            int findSmallSubDiagonalElement = findSmallSubDiagonalElement(i3, norm);
            if (findSmallSubDiagonalElement == i3) {
                this.matrixT[i3][i3] = this.matrixT[i3][i3] + shiftInfo.exShift;
                i3--;
                i = length;
            } else {
                int i5 = i3 - 1;
                if (findSmallSubDiagonalElement == i5) {
                    double d = (this.matrixT[i5][i5] - this.matrixT[i3][i3]) / 2.0d;
                    double d2 = (d * d) + (this.matrixT[i3][i5] * this.matrixT[i5][i3]);
                    double[] dArr = this.matrixT[i3];
                    int i6 = length;
                    dArr[i3] = dArr[i3] + shiftInfo.exShift;
                    double[] dArr2 = this.matrixT[i5];
                    dArr2[i5] = dArr2[i5] + shiftInfo.exShift;
                    if (d2 >= 0.0d) {
                        double sqrt = FastMath.sqrt(FastMath.abs(d2));
                        double d3 = d >= 0.0d ? d + sqrt : d - sqrt;
                        double d4 = this.matrixT[i3][i5];
                        double abs = FastMath.abs(d4) + FastMath.abs(d3);
                        double d5 = d4 / abs;
                        double d6 = d3 / abs;
                        double sqrt2 = FastMath.sqrt((d5 * d5) + (d6 * d6));
                        double d7 = d5 / sqrt2;
                        double d8 = d6 / sqrt2;
                        i = i6;
                        for (int i7 = i5; i7 < i; i7++) {
                            double d9 = this.matrixT[i5][i7];
                            this.matrixT[i5][i7] = (d8 * d9) + (this.matrixT[i3][i7] * d7);
                            this.matrixT[i3][i7] = (this.matrixT[i3][i7] * d8) - (d9 * d7);
                        }
                        for (int i8 = 0; i8 <= i3; i8++) {
                            double d10 = this.matrixT[i8][i5];
                            this.matrixT[i8][i5] = (d8 * d10) + (this.matrixT[i8][i3] * d7);
                            this.matrixT[i8][i3] = (this.matrixT[i8][i3] * d8) - (d10 * d7);
                        }
                        for (int i9 = 0; i9 <= i2; i9++) {
                            double d11 = this.matrixP[i9][i5];
                            this.matrixP[i9][i5] = (d8 * d11) + (this.matrixP[i9][i3] * d7);
                            this.matrixP[i9][i3] = (this.matrixP[i9][i3] * d8) - (d11 * d7);
                        }
                    } else {
                        i = i6;
                    }
                    i3 -= 2;
                } else {
                    i = length;
                    computeShift(findSmallSubDiagonalElement, i3, i4, shiftInfo);
                    int i10 = i4 + 1;
                    if (i10 > 100) {
                        throw new MaxCountExceededException(LocalizedFormats.CONVERGENCE_FAILED, Integer.valueOf(100), new Object[0]);
                    }
                    double[] dArr3 = new double[3];
                    performDoubleQRStep(findSmallSubDiagonalElement, initQRStep(findSmallSubDiagonalElement, i3, shiftInfo, dArr3), i3, shiftInfo, dArr3);
                    i4 = i10;
                    length = i;
                }
            }
            i4 = 0;
            length = i;
        }
    }

    private double getNorm() {
        double d = 0.0d;
        for (int i = 0; i < this.matrixT.length; i++) {
            for (int max = FastMath.max(i - 1, 0); max < this.matrixT.length; max++) {
                d += FastMath.abs(this.matrixT[i][max]);
            }
        }
        return d;
    }

    private int findSmallSubDiagonalElement(int i, double d) {
        while (i > 0) {
            int i2 = i - 1;
            double abs = FastMath.abs(this.matrixT[i2][i2]) + FastMath.abs(this.matrixT[i][i]);
            if (abs == 0.0d) {
                abs = d;
            }
            if (FastMath.abs(this.matrixT[i][i2]) < this.epsilon * abs) {
                break;
            }
            i--;
        }
        return i;
    }

    private void computeShift(int i, int i2, int i3, ShiftInfo shiftInfo) {
        int i4 = i2;
        int i5 = i3;
        ShiftInfo shiftInfo2 = shiftInfo;
        shiftInfo2.x = this.matrixT[i4][i4];
        shiftInfo2.w = 0.0d;
        shiftInfo2.y = 0.0d;
        if (i < i4) {
            int i6 = i4 - 1;
            shiftInfo2.y = this.matrixT[i6][i6];
            shiftInfo2.w = this.matrixT[i4][i6] * this.matrixT[i6][i4];
        }
        if (i5 == 10) {
            shiftInfo2.exShift += shiftInfo2.x;
            for (int i7 = 0; i7 <= i4; i7++) {
                double[] dArr = this.matrixT[i7];
                dArr[i7] = dArr[i7] - shiftInfo2.x;
            }
            int i8 = i4 - 1;
            double abs = FastMath.abs(this.matrixT[i4][i8]) + FastMath.abs(this.matrixT[i8][i4 - 2]);
            double d = 0.75d * abs;
            shiftInfo2.x = d;
            shiftInfo2.y = d;
            shiftInfo2.w = -0.4375d * abs * abs;
        }
        if (i5 == 30) {
            double d2 = (shiftInfo2.y - shiftInfo2.x) / 2.0d;
            double d3 = (d2 * d2) + shiftInfo2.w;
            if (d3 > 0.0d) {
                double sqrt = FastMath.sqrt(d3);
                if (shiftInfo2.y < shiftInfo2.x) {
                    sqrt = -sqrt;
                }
                double d4 = shiftInfo2.x - (shiftInfo2.w / (((shiftInfo2.y - shiftInfo2.x) / 2.0d) + sqrt));
                for (int i9 = 0; i9 <= i4; i9++) {
                    double[] dArr2 = this.matrixT[i9];
                    dArr2[i9] = dArr2[i9] - d4;
                }
                shiftInfo2.exShift += d4;
                shiftInfo2.w = 0.964d;
                shiftInfo2.y = 0.964d;
                shiftInfo2.x = 0.964d;
            }
        }
    }

    private int initQRStep(int i, int i2, ShiftInfo shiftInfo, double[] dArr) {
        int i3 = i;
        ShiftInfo shiftInfo2 = shiftInfo;
        int i4 = i2 - 2;
        while (i4 >= i3) {
            double d = this.matrixT[i4][i4];
            double d2 = shiftInfo2.x - d;
            double d3 = shiftInfo2.y - d;
            int i5 = i4;
            int i6 = i5 + 1;
            dArr[0] = (((d2 * d3) - shiftInfo2.w) / this.matrixT[i6][i5]) + this.matrixT[i5][i6];
            dArr[1] = ((this.matrixT[i6][i6] - d) - d2) - d3;
            dArr[2] = this.matrixT[i5 + 2][i6];
            int i7 = i5;
            if (i7 == i3) {
                return i7;
            }
            int i8 = i7 - 1;
            int i9 = i6;
            if (FastMath.abs(this.matrixT[i7][i8]) * (FastMath.abs(dArr[1]) + FastMath.abs(dArr[2])) < this.epsilon * FastMath.abs(dArr[0]) * (FastMath.abs(this.matrixT[i8][i8]) + FastMath.abs(d) + FastMath.abs(this.matrixT[i9][i9]))) {
                return i7;
            }
            i4 = i7 - 1;
            i3 = i;
            shiftInfo2 = shiftInfo;
        }
        return i4;
    }

    private void performDoubleQRStep(int i, int i2, int i3, ShiftInfo shiftInfo, double[] dArr) {
        int i4;
        int i5;
        double d;
        double d2;
        int i6;
        int i7 = i2;
        int i8 = i3;
        ShiftInfo shiftInfo2 = shiftInfo;
        boolean z = false;
        int i9 = 2;
        boolean z2 = true;
        int length = this.matrixT.length;
        double d3 = dArr[0];
        double d4 = dArr[1];
        double d5 = dArr[2];
        int i10 = i7;
        while (true) {
            int i11 = i8 - 1;
            if (i10 > i11) {
                i4 = i9;
                break;
            }
            boolean z3 = i10 != i11 ? z2 : z;
            if (i10 != i7) {
                int i12 = i10 - 1;
                double d6 = this.matrixT[i10][i12];
                double d7 = this.matrixT[i10 + 1][i12];
                double d8 = z3 ? this.matrixT[i10 + 2][i12] : 0.0d;
                shiftInfo2.x = FastMath.abs(d6) + FastMath.abs(d7) + FastMath.abs(d8);
                double d9 = d8;
                if (!Precision.equals(shiftInfo2.x, 0.0d, this.epsilon)) {
                    d3 = d6 / shiftInfo2.x;
                    d4 = d7 / shiftInfo2.x;
                    d5 = d9 / shiftInfo2.x;
                } else {
                    d3 = d6;
                    d4 = d7;
                    d5 = d9;
                }
            }
            if (shiftInfo2.x == 0.0d) {
                i4 = 2;
                break;
            }
            double sqrt = FastMath.sqrt((d3 * d3) + (d4 * d4) + (d5 * d5));
            if (d3 < 0.0d) {
                sqrt = -sqrt;
            }
            if (sqrt != 0.0d) {
                if (i10 != i7) {
                    d2 = d5;
                    d = d4;
                    this.matrixT[i10][i10 - 1] = (-sqrt) * shiftInfo2.x;
                    int i13 = i;
                } else {
                    d = d4;
                    d2 = d5;
                    if (i != i7) {
                        int i14 = i10 - 1;
                        this.matrixT[i10][i14] = -this.matrixT[i10][i14];
                    }
                }
                d3 += sqrt;
                shiftInfo2.x = d3 / sqrt;
                shiftInfo2.y = d / sqrt;
                double d10 = d2 / sqrt;
                d4 = d / d3;
                double d11 = d2 / d3;
                int i15 = i10;
                while (i15 < length) {
                    int i16 = i10 + 1;
                    d3 = this.matrixT[i10][i15] + (this.matrixT[i16][i15] * d4);
                    if (z3) {
                        int i17 = i10 + 2;
                        d3 += this.matrixT[i17][i15] * d11;
                        i6 = length;
                        this.matrixT[i17][i15] = this.matrixT[i17][i15] - (d3 * d10);
                    } else {
                        i6 = length;
                    }
                    this.matrixT[i10][i15] = this.matrixT[i10][i15] - (shiftInfo2.x * d3);
                    this.matrixT[i16][i15] = this.matrixT[i16][i15] - (shiftInfo2.y * d3);
                    i15++;
                    length = i6;
                    int i18 = i;
                }
                i5 = length;
                int i19 = 0;
                while (true) {
                    if (i19 > FastMath.min(i3, i10 + 3)) {
                        break;
                    }
                    int i20 = i10 + 1;
                    double d12 = (shiftInfo2.x * this.matrixT[i19][i10]) + (shiftInfo2.y * this.matrixT[i19][i20]);
                    if (z3) {
                        int i21 = i10 + 2;
                        d12 += this.matrixT[i19][i21] * d10;
                        this.matrixT[i19][i21] = this.matrixT[i19][i21] - (d12 * d11);
                    }
                    d3 = d12;
                    this.matrixT[i19][i10] = this.matrixT[i19][i10] - d3;
                    this.matrixT[i19][i20] = this.matrixT[i19][i20] - (d3 * d4);
                    i19++;
                }
                int length2 = this.matrixT.length - 1;
                int i22 = 0;
                while (i22 <= length2) {
                    int i23 = length2;
                    int i24 = i10 + 1;
                    double d13 = (shiftInfo2.x * this.matrixP[i22][i10]) + (shiftInfo2.y * this.matrixP[i22][i24]);
                    if (z3) {
                        int i25 = i10 + 2;
                        d13 += this.matrixP[i22][i25] * d10;
                        this.matrixP[i22][i25] = this.matrixP[i22][i25] - (d13 * d11);
                    }
                    d3 = d13;
                    this.matrixP[i22][i10] = this.matrixP[i22][i10] - d3;
                    this.matrixP[i22][i24] = this.matrixP[i22][i24] - (d3 * d4);
                    i22++;
                    length2 = i23;
                }
                d5 = d11;
            } else {
                i5 = length;
                double d14 = d4;
                double d15 = d5;
            }
            i10++;
            length = i5;
            i7 = i2;
            i8 = i3;
            z = false;
            i9 = 2;
            z2 = true;
        }
        int i26 = i7 + i4;
        int i27 = i3;
        for (int i28 = i26; i28 <= i27; i28++) {
            this.matrixT[i28][i28 - 2] = 0.0d;
            if (i28 > i26) {
                this.matrixT[i28][i28 - 3] = 0.0d;
            }
        }
    }
}
