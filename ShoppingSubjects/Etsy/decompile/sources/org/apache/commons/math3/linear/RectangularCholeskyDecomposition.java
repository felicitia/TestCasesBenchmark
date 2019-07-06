package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import org.apache.commons.math3.util.FastMath;

public class RectangularCholeskyDecomposition {
    private int rank;
    private final RealMatrix root;

    public RectangularCholeskyDecomposition(RealMatrix realMatrix) throws NonPositiveDefiniteMatrixException {
        this(realMatrix, 0.0d);
    }

    public RectangularCholeskyDecomposition(RealMatrix realMatrix, double d) throws NonPositiveDefiniteMatrixException {
        double d2 = d;
        int rowDimension = realMatrix.getRowDimension();
        double[][] data = realMatrix.getData();
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{rowDimension, rowDimension});
        int[] iArr = new int[rowDimension];
        for (int i = 0; i < rowDimension; i++) {
            iArr[i] = i;
        }
        boolean z = true;
        int i2 = 0;
        while (z) {
            int i3 = i2 + 1;
            int i4 = i2;
            for (int i5 = i3; i5 < rowDimension; i5++) {
                int i6 = iArr[i5];
                int i7 = iArr[i4];
                if (data[i6][i6] > data[i7][i7]) {
                    i4 = i5;
                }
            }
            if (i4 != i2) {
                int i8 = iArr[i2];
                iArr[i2] = iArr[i4];
                iArr[i4] = i8;
                double[] dArr2 = dArr[i2];
                dArr[i2] = dArr[i4];
                dArr[i4] = dArr2;
            }
            int i9 = iArr[i2];
            if (data[i9][i9] > d2) {
                double sqrt = FastMath.sqrt(data[i9][i9]);
                dArr[i2][i2] = sqrt;
                double d3 = 1.0d / sqrt;
                double d4 = 1.0d / data[i9][i9];
                for (int i10 = i3; i10 < rowDimension; i10++) {
                    int i11 = iArr[i10];
                    double d5 = data[i11][i9] * d3;
                    dArr[i10][i2] = d5;
                    double[] dArr3 = data[i11];
                    dArr3[i11] = dArr3[i11] - ((data[i11][i9] * data[i11][i9]) * d4);
                    for (int i12 = i3; i12 < i10; i12++) {
                        int i13 = iArr[i12];
                        double d6 = data[i11][i13] - (dArr[i12][i2] * d5);
                        data[i11][i13] = d6;
                        data[i13][i11] = d6;
                    }
                }
                i2 = i3;
                z = i3 < rowDimension;
            } else if (i2 == 0) {
                NonPositiveDefiniteMatrixException nonPositiveDefiniteMatrixException = new NonPositiveDefiniteMatrixException(data[i9][i9], i9, d2);
                throw nonPositiveDefiniteMatrixException;
            } else {
                for (int i14 = i2; i14 < rowDimension; i14++) {
                    if (data[iArr[i14]][iArr[i14]] < (-d2)) {
                        NonPositiveDefiniteMatrixException nonPositiveDefiniteMatrixException2 = new NonPositiveDefiniteMatrixException(data[iArr[i14]][iArr[i14]], i14, d2);
                        throw nonPositiveDefiniteMatrixException2;
                    }
                }
                z = false;
            }
        }
        this.rank = i2;
        this.root = MatrixUtils.createRealMatrix(rowDimension, i2);
        for (int i15 = 0; i15 < rowDimension; i15++) {
            for (int i16 = 0; i16 < i2; i16++) {
                this.root.setEntry(iArr[i15], i16, dArr[i15][i16]);
            }
        }
    }

    public RealMatrix getRootMatrix() {
        return this.root;
    }

    public int getRank() {
        return this.rank;
    }
}
