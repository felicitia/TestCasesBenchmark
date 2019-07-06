package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.Precision;

class HessenbergTransformer {
    private RealMatrix cachedH;
    private RealMatrix cachedP;
    private RealMatrix cachedPt;
    private final double[][] householderVectors;
    private final double[] ort;

    public HessenbergTransformer(RealMatrix realMatrix) {
        if (!realMatrix.isSquare()) {
            throw new NonSquareMatrixException(realMatrix.getRowDimension(), realMatrix.getColumnDimension());
        }
        int rowDimension = realMatrix.getRowDimension();
        this.householderVectors = realMatrix.getData();
        this.ort = new double[rowDimension];
        this.cachedP = null;
        this.cachedPt = null;
        this.cachedH = null;
        transform();
    }

    public RealMatrix getP() {
        if (this.cachedP == null) {
            int length = this.householderVectors.length;
            int i = length - 1;
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length, length});
            int i2 = 0;
            while (i2 < length) {
                int i3 = 0;
                while (i3 < length) {
                    dArr[i2][i3] = i2 == i3 ? 1.0d : 0.0d;
                    i3++;
                }
                i2++;
            }
            for (int i4 = i - 1; i4 >= 1; i4--) {
                int i5 = i4 - 1;
                if (this.householderVectors[i4][i5] != 0.0d) {
                    for (int i6 = i4 + 1; i6 <= i; i6++) {
                        this.ort[i6] = this.householderVectors[i6][i5];
                    }
                    for (int i7 = i4; i7 <= i; i7++) {
                        double d = 0.0d;
                        for (int i8 = i4; i8 <= i; i8++) {
                            d += this.ort[i8] * dArr[i8][i7];
                        }
                        double d2 = (d / this.ort[i4]) / this.householderVectors[i4][i5];
                        for (int i9 = i4; i9 <= i; i9++) {
                            double[] dArr2 = dArr[i9];
                            dArr2[i7] = dArr2[i7] + (this.ort[i9] * d2);
                        }
                    }
                }
            }
            this.cachedP = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedP;
    }

    public RealMatrix getPT() {
        if (this.cachedPt == null) {
            this.cachedPt = getP().transpose();
        }
        return this.cachedPt;
    }

    public RealMatrix getH() {
        if (this.cachedH == null) {
            int length = this.householderVectors.length;
            double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{length, length});
            for (int i = 0; i < length; i++) {
                if (i > 0) {
                    int i2 = i - 1;
                    dArr[i][i2] = this.householderVectors[i][i2];
                }
                for (int i3 = i; i3 < length; i3++) {
                    dArr[i][i3] = this.householderVectors[i][i3];
                }
            }
            this.cachedH = MatrixUtils.createRealMatrix(dArr);
        }
        return this.cachedH;
    }

    /* access modifiers changed from: 0000 */
    public double[][] getHouseholderVectorsRef() {
        return this.householderVectors;
    }

    private void transform() {
        int length = this.householderVectors.length;
        int i = length - 1;
        for (int i2 = 1; i2 <= i - 1; i2++) {
            double d = 0.0d;
            double d2 = 0.0d;
            for (int i3 = i2; i3 <= i; i3++) {
                d2 += FastMath.abs(this.householderVectors[i3][i2 - 1]);
            }
            if (!Precision.equals(d2, 0.0d)) {
                double d3 = 0.0d;
                for (int i4 = i; i4 >= i2; i4--) {
                    this.ort[i4] = this.householderVectors[i4][i2 - 1] / d2;
                    d3 += this.ort[i4] * this.ort[i4];
                }
                double sqrt = this.ort[i2] > 0.0d ? -FastMath.sqrt(d3) : FastMath.sqrt(d3);
                double d4 = d3 - (this.ort[i2] * sqrt);
                this.ort[i2] = this.ort[i2] - sqrt;
                int i5 = i2;
                while (i5 < length) {
                    double d5 = d;
                    for (int i6 = i; i6 >= i2; i6--) {
                        d5 += this.ort[i6] * this.householderVectors[i6][i5];
                    }
                    double d6 = d5 / d4;
                    for (int i7 = i2; i7 <= i; i7++) {
                        double[] dArr = this.householderVectors[i7];
                        dArr[i5] = dArr[i5] - (this.ort[i7] * d6);
                    }
                    i5++;
                    d = 0.0d;
                }
                for (int i8 = 0; i8 <= i; i8++) {
                    double d7 = 0.0d;
                    for (int i9 = i; i9 >= i2; i9--) {
                        d7 += this.ort[i9] * this.householderVectors[i8][i9];
                    }
                    double d8 = d7 / d4;
                    for (int i10 = i2; i10 <= i; i10++) {
                        double[] dArr2 = this.householderVectors[i8];
                        dArr2[i10] = dArr2[i10] - (this.ort[i10] * d8);
                    }
                }
                this.ort[i2] = this.ort[i2] * d2;
                this.householderVectors[i2][i2 - 1] = d2 * sqrt;
            }
        }
    }
}
