package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class BlockRealMatrix extends AbstractRealMatrix implements Serializable {
    public static final int BLOCK_SIZE = 52;
    private static final long serialVersionUID = 4991895511313664478L;
    private final int blockColumns;
    private final int blockRows;
    private final double[][] blocks;
    private final int columns;
    private final int rows;

    public BlockRealMatrix(int i, int i2) throws NotStrictlyPositiveException {
        super(i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = ((i + 52) - 1) / 52;
        this.blockColumns = ((i2 + 52) - 1) / 52;
        this.blocks = createBlocksLayout(i, i2);
    }

    public BlockRealMatrix(double[][] dArr) throws DimensionMismatchException, NotStrictlyPositiveException {
        this(dArr.length, dArr[0].length, toBlocksLayout(dArr), false);
    }

    public BlockRealMatrix(int i, int i2, double[][] dArr, boolean z) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = ((i + 52) - 1) / 52;
        this.blockColumns = ((i2 + 52) - 1) / 52;
        if (z) {
            this.blocks = new double[(this.blockRows * this.blockColumns)][];
        } else {
            this.blocks = dArr;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.blockRows) {
            int blockHeight = blockHeight(i3);
            int i5 = i4;
            int i6 = 0;
            while (i6 < this.blockColumns) {
                if (dArr[i5].length != blockWidth(i6) * blockHeight) {
                    throw new DimensionMismatchException(dArr[i5].length, blockHeight * blockWidth(i6));
                }
                if (z) {
                    this.blocks[i5] = (double[]) dArr[i5].clone();
                }
                i6++;
                i5++;
            }
            i3++;
            i4 = i5;
        }
    }

    public static double[][] toBlocksLayout(double[][] dArr) throws DimensionMismatchException {
        double[][] dArr2 = dArr;
        int i = 0;
        int length = dArr2.length;
        int length2 = dArr2[0].length;
        int i2 = ((length + 52) - 1) / 52;
        int i3 = ((length2 + 52) - 1) / 52;
        for (double[] length3 : dArr2) {
            int length4 = length3.length;
            if (length4 != length2) {
                throw new DimensionMismatchException(length2, length4);
            }
        }
        double[][] dArr3 = new double[(i2 * i3)][];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = i4 * 52;
            int min = FastMath.min(i6 + 52, length);
            int i7 = min - i6;
            int i8 = i5;
            int i9 = i;
            while (i9 < i3) {
                int i10 = i9 * 52;
                int min2 = FastMath.min(i10 + 52, length2) - i10;
                double[] dArr4 = new double[(i7 * min2)];
                dArr3[i8] = dArr4;
                int i11 = length;
                int i12 = i;
                int i13 = i6;
                while (i13 < min) {
                    int i14 = length2;
                    System.arraycopy(dArr2[i13], i10, dArr4, i12, min2);
                    i12 += min2;
                    i13++;
                    length2 = i14;
                }
                int i15 = length2;
                i8++;
                i9++;
                length = i11;
                i = 0;
            }
            int i16 = length;
            int i17 = length2;
            i4++;
            i5 = i8;
            i = 0;
        }
        return dArr3;
    }

    public static double[][] createBlocksLayout(int i, int i2) {
        int i3 = ((i + 52) - 1) / 52;
        int i4 = ((i2 + 52) - 1) / 52;
        double[][] dArr = new double[(i3 * i4)][];
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            int i7 = i5 * 52;
            int min = FastMath.min(i7 + 52, i) - i7;
            int i8 = i6;
            for (int i9 = 0; i9 < i4; i9++) {
                int i10 = i9 * 52;
                dArr[i8] = new double[((FastMath.min(i10 + 52, i2) - i10) * min)];
                i8++;
            }
            i5++;
            i6 = i8;
        }
        return dArr;
    }

    public BlockRealMatrix createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new BlockRealMatrix(i, i2);
    }

    public BlockRealMatrix copy() {
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < this.blocks.length; i++) {
            System.arraycopy(this.blocks[i], 0, blockRealMatrix.blocks[i], 0, this.blocks[i].length);
        }
        return blockRealMatrix;
    }

    public BlockRealMatrix add(RealMatrix realMatrix) throws MatrixDimensionMismatchException {
        RealMatrix realMatrix2 = realMatrix;
        try {
            return add((BlockRealMatrix) realMatrix2);
        } catch (ClassCastException unused) {
            MatrixUtils.checkAdditionCompatible(this, realMatrix);
            BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, this.columns);
            int i = 0;
            int i2 = 0;
            while (i < blockRealMatrix.blockRows) {
                int i3 = i2;
                for (int i4 = 0; i4 < blockRealMatrix.blockColumns; i4++) {
                    double[] dArr = blockRealMatrix.blocks[i3];
                    double[] dArr2 = this.blocks[i3];
                    int i5 = i * 52;
                    int min = FastMath.min(i5 + 52, this.rows);
                    int i6 = i4 * 52;
                    int min2 = FastMath.min(i6 + 52, this.columns);
                    int i7 = 0;
                    while (i5 < min) {
                        int i8 = i7;
                        for (int i9 = i6; i9 < min2; i9++) {
                            dArr[i8] = dArr2[i8] + realMatrix2.getEntry(i5, i9);
                            i8++;
                        }
                        i5++;
                        i7 = i8;
                    }
                    i3++;
                }
                i++;
                i2 = i3;
            }
            return blockRealMatrix;
        }
    }

    public BlockRealMatrix add(BlockRealMatrix blockRealMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkAdditionCompatible(this, blockRealMatrix);
        BlockRealMatrix blockRealMatrix2 = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < blockRealMatrix2.blocks.length; i++) {
            double[] dArr = blockRealMatrix2.blocks[i];
            double[] dArr2 = this.blocks[i];
            double[] dArr3 = blockRealMatrix.blocks[i];
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = dArr2[i2] + dArr3[i2];
            }
        }
        return blockRealMatrix2;
    }

    public BlockRealMatrix subtract(RealMatrix realMatrix) throws MatrixDimensionMismatchException {
        RealMatrix realMatrix2 = realMatrix;
        try {
            return subtract((BlockRealMatrix) realMatrix2);
        } catch (ClassCastException unused) {
            MatrixUtils.checkSubtractionCompatible(this, realMatrix);
            BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, this.columns);
            int i = 0;
            int i2 = 0;
            while (i < blockRealMatrix.blockRows) {
                int i3 = i2;
                for (int i4 = 0; i4 < blockRealMatrix.blockColumns; i4++) {
                    double[] dArr = blockRealMatrix.blocks[i3];
                    double[] dArr2 = this.blocks[i3];
                    int i5 = i * 52;
                    int min = FastMath.min(i5 + 52, this.rows);
                    int i6 = i4 * 52;
                    int min2 = FastMath.min(i6 + 52, this.columns);
                    int i7 = 0;
                    while (i5 < min) {
                        int i8 = i7;
                        for (int i9 = i6; i9 < min2; i9++) {
                            dArr[i8] = dArr2[i8] - realMatrix2.getEntry(i5, i9);
                            i8++;
                        }
                        i5++;
                        i7 = i8;
                    }
                    i3++;
                }
                i++;
                i2 = i3;
            }
            return blockRealMatrix;
        }
    }

    public BlockRealMatrix subtract(BlockRealMatrix blockRealMatrix) throws MatrixDimensionMismatchException {
        MatrixUtils.checkSubtractionCompatible(this, blockRealMatrix);
        BlockRealMatrix blockRealMatrix2 = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < blockRealMatrix2.blocks.length; i++) {
            double[] dArr = blockRealMatrix2.blocks[i];
            double[] dArr2 = this.blocks[i];
            double[] dArr3 = blockRealMatrix.blocks[i];
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = dArr2[i2] - dArr3[i2];
            }
        }
        return blockRealMatrix2;
    }

    public BlockRealMatrix scalarAdd(double d) {
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < blockRealMatrix.blocks.length; i++) {
            double[] dArr = blockRealMatrix.blocks[i];
            double[] dArr2 = this.blocks[i];
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = dArr2[i2] + d;
            }
        }
        return blockRealMatrix;
    }

    public RealMatrix scalarMultiply(double d) {
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, this.columns);
        for (int i = 0; i < blockRealMatrix.blocks.length; i++) {
            double[] dArr = blockRealMatrix.blocks[i];
            double[] dArr2 = this.blocks[i];
            for (int i2 = 0; i2 < dArr.length; i2++) {
                dArr[i2] = dArr2[i2] * d;
            }
        }
        return blockRealMatrix;
    }

    public BlockRealMatrix multiply(RealMatrix realMatrix) throws DimensionMismatchException {
        BlockRealMatrix blockRealMatrix = this;
        RealMatrix realMatrix2 = realMatrix;
        try {
            return blockRealMatrix.multiply((BlockRealMatrix) realMatrix2);
        } catch (ClassCastException unused) {
            MatrixUtils.checkMultiplicationCompatible(this, realMatrix);
            BlockRealMatrix blockRealMatrix2 = new BlockRealMatrix(blockRealMatrix.rows, realMatrix.getColumnDimension());
            int i = 0;
            int i2 = 0;
            while (i < blockRealMatrix2.blockRows) {
                int i3 = i * 52;
                int min = FastMath.min(i3 + 52, blockRealMatrix.rows);
                int i4 = i2;
                int i5 = 0;
                while (i5 < blockRealMatrix2.blockColumns) {
                    int i6 = i5 * 52;
                    int min2 = FastMath.min(i6 + 52, realMatrix.getColumnDimension());
                    double[] dArr = blockRealMatrix2.blocks[i4];
                    int i7 = 0;
                    while (i7 < blockRealMatrix.blockColumns) {
                        int blockWidth = blockRealMatrix.blockWidth(i7);
                        double[] dArr2 = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * i) + i7];
                        int i8 = i7 * 52;
                        int i9 = i3;
                        int i10 = 0;
                        while (i9 < min) {
                            int i11 = (i9 - i3) * blockWidth;
                            int i12 = i11 + blockWidth;
                            int i13 = i3;
                            int i14 = i6;
                            while (i14 < min2) {
                                double d = 0.0d;
                                int i15 = min;
                                int i16 = i6;
                                int i17 = i8;
                                for (int i18 = i11; i18 < i12; i18++) {
                                    d += dArr2[i18] * realMatrix2.getEntry(i17, i14);
                                    i17++;
                                }
                                dArr[i10] = dArr[i10] + d;
                                i10++;
                                i14++;
                                min = i15;
                                i6 = i16;
                            }
                            int i19 = min;
                            int i20 = i6;
                            i9++;
                            i3 = i13;
                        }
                        int i21 = i3;
                        int i22 = min;
                        int i23 = i6;
                        i7++;
                        blockRealMatrix = this;
                    }
                    int i24 = i3;
                    int i25 = min;
                    i4++;
                    i5++;
                    blockRealMatrix = this;
                }
                i++;
                i2 = i4;
                blockRealMatrix = this;
            }
            return blockRealMatrix2;
        }
    }

    public BlockRealMatrix multiply(BlockRealMatrix blockRealMatrix) throws DimensionMismatchException {
        int i;
        BlockRealMatrix blockRealMatrix2 = this;
        BlockRealMatrix blockRealMatrix3 = blockRealMatrix;
        MatrixUtils.checkMultiplicationCompatible(this, blockRealMatrix);
        BlockRealMatrix blockRealMatrix4 = new BlockRealMatrix(blockRealMatrix2.rows, blockRealMatrix3.columns);
        int i2 = 0;
        int i3 = 0;
        while (i2 < blockRealMatrix4.blockRows) {
            int i4 = i2 * 52;
            int min = FastMath.min(i4 + 52, blockRealMatrix2.rows);
            int i5 = i3;
            int i6 = 0;
            while (i6 < blockRealMatrix4.blockColumns) {
                int blockWidth = blockRealMatrix4.blockWidth(i6);
                int i7 = blockWidth + blockWidth;
                int i8 = i7 + blockWidth;
                int i9 = i8 + blockWidth;
                double[] dArr = blockRealMatrix4.blocks[i5];
                int i10 = 0;
                while (i10 < blockRealMatrix2.blockColumns) {
                    int blockWidth2 = blockRealMatrix2.blockWidth(i10);
                    BlockRealMatrix blockRealMatrix5 = blockRealMatrix4;
                    double[] dArr2 = blockRealMatrix2.blocks[(blockRealMatrix2.blockColumns * i2) + i10];
                    double[] dArr3 = blockRealMatrix3.blocks[(blockRealMatrix3.blockColumns * i10) + i6];
                    int i11 = i4;
                    int i12 = 0;
                    while (i11 < min) {
                        int i13 = (i11 - i4) * blockWidth2;
                        int i14 = i13 + blockWidth2;
                        int i15 = i4;
                        int i16 = 0;
                        while (i16 < blockWidth) {
                            double d = 0.0d;
                            int i17 = i16;
                            int i18 = min;
                            int i19 = i13;
                            while (true) {
                                i = blockWidth2;
                                if (i19 >= i14 - 3) {
                                    break;
                                }
                                d += (dArr2[i19] * dArr3[i17]) + (dArr2[i19 + 1] * dArr3[i17 + blockWidth]) + (dArr2[i19 + 2] * dArr3[i17 + i7]) + (dArr2[i19 + 3] * dArr3[i17 + i8]);
                                i19 += 4;
                                i17 += i9;
                                blockWidth2 = i;
                            }
                            while (i19 < i14) {
                                d += dArr2[i19] * dArr3[i17];
                                i17 += blockWidth;
                                i19++;
                            }
                            dArr[i12] = dArr[i12] + d;
                            i12++;
                            i16++;
                            min = i18;
                            blockWidth2 = i;
                        }
                        int i20 = min;
                        int i21 = blockWidth2;
                        i11++;
                        i4 = i15;
                        BlockRealMatrix blockRealMatrix6 = blockRealMatrix;
                    }
                    int i22 = i4;
                    int i23 = min;
                    i10++;
                    blockRealMatrix4 = blockRealMatrix5;
                    blockRealMatrix2 = this;
                    blockRealMatrix3 = blockRealMatrix;
                }
                BlockRealMatrix blockRealMatrix7 = blockRealMatrix4;
                int i24 = i4;
                int i25 = min;
                i5++;
                i6++;
                blockRealMatrix2 = this;
                blockRealMatrix3 = blockRealMatrix;
            }
            BlockRealMatrix blockRealMatrix8 = blockRealMatrix4;
            i2++;
            i3 = i5;
            blockRealMatrix2 = this;
            blockRealMatrix3 = blockRealMatrix;
        }
        return blockRealMatrix4;
    }

    public double[][] getData() {
        double[][] dArr = (double[][]) Array.newInstance(double.class, new int[]{getRowDimension(), getColumnDimension()});
        int i = this.columns - ((this.blockColumns - 1) * 52);
        for (int i2 = 0; i2 < this.blockRows; i2++) {
            int i3 = i2 * 52;
            int min = FastMath.min(i3 + 52, this.rows);
            int i4 = 0;
            int i5 = 0;
            while (i3 < min) {
                double[] dArr2 = dArr[i3];
                int i6 = 0;
                int i7 = this.blockColumns * i2;
                int i8 = 0;
                while (i8 < this.blockColumns - 1) {
                    int i9 = i7 + 1;
                    System.arraycopy(this.blocks[i7], i4, dArr2, i6, 52);
                    i6 += 52;
                    i8++;
                    i7 = i9;
                }
                System.arraycopy(this.blocks[i7], i5, dArr2, i6, i);
                i4 += 52;
                i5 += i;
                i3++;
            }
        }
        return dArr;
    }

    public double getNorm() {
        double[] dArr = new double[52];
        double d = 0.0d;
        double d2 = 0.0d;
        int i = 0;
        while (i < this.blockColumns) {
            int blockWidth = blockWidth(i);
            Arrays.fill(dArr, 0, blockWidth, d);
            int i2 = 0;
            while (i2 < this.blockRows) {
                int blockHeight = blockHeight(i2);
                double[] dArr2 = this.blocks[(this.blockColumns * i2) + i];
                int i3 = 0;
                while (i3 < blockWidth) {
                    double d3 = d;
                    for (int i4 = 0; i4 < blockHeight; i4++) {
                        d3 += FastMath.abs(dArr2[(i4 * blockWidth) + i3]);
                    }
                    dArr[i3] = dArr[i3] + d3;
                    i3++;
                    d = 0.0d;
                }
                i2++;
                d = 0.0d;
            }
            for (int i5 = 0; i5 < blockWidth; i5++) {
                d2 = FastMath.max(d2, dArr[i5]);
            }
            i++;
            d = 0.0d;
        }
        return d2;
    }

    public double getFrobeniusNorm() {
        double d = 0.0d;
        int i = 0;
        while (i < this.blocks.length) {
            double d2 = d;
            for (double d3 : this.blocks[i]) {
                d2 += d3 * d3;
            }
            i++;
            d = d2;
        }
        return FastMath.sqrt(d);
    }

    public BlockRealMatrix getSubMatrix(int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5;
        int i6;
        int i7;
        MatrixUtils.checkSubMatrixIndex(this, i, i2, i3, i4);
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix((i2 - i) + 1, (i4 - i3) + 1);
        int i8 = i % 52;
        int i9 = i3 / 52;
        int i10 = i3 % 52;
        int i11 = i / 52;
        int i12 = 0;
        while (i12 < blockRealMatrix.blockRows) {
            int blockHeight = blockRealMatrix.blockHeight(i12);
            int i13 = i9;
            int i14 = 0;
            while (i14 < blockRealMatrix.blockColumns) {
                int blockWidth = blockRealMatrix.blockWidth(i14);
                double[] dArr = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * i12) + i14];
                int i15 = (this.blockColumns * i11) + i13;
                int blockWidth2 = blockWidth(i13);
                int i16 = blockHeight + i8;
                int i17 = i16 - 52;
                int i18 = blockWidth + i10;
                int i19 = i18 - 52;
                if (i17 <= 0) {
                    i7 = i13;
                    i6 = i14;
                    i5 = i12;
                    if (i19 > 0) {
                        int blockWidth3 = blockWidth(i7 + 1);
                        int i20 = i8;
                        int i21 = i16;
                        double[] dArr2 = dArr;
                        int i22 = blockWidth;
                        copyBlockPart(this.blocks[i15], blockWidth2, i20, i21, i10, 52, dArr2, i22, 0, 0);
                        copyBlockPart(this.blocks[i15 + 1], blockWidth3, i20, i21, 0, i19, dArr2, i22, 0, blockWidth - i19);
                    } else {
                        copyBlockPart(this.blocks[i15], blockWidth2, i8, i16, i10, i18, dArr, blockWidth, 0, 0);
                    }
                } else if (i19 > 0) {
                    int blockWidth4 = blockWidth(i13 + 1);
                    int i23 = i8;
                    double[] dArr3 = dArr;
                    i7 = i13;
                    int i24 = blockWidth;
                    i6 = i14;
                    i5 = i12;
                    copyBlockPart(this.blocks[i15], blockWidth2, i23, 52, i10, 52, dArr3, i24, 0, 0);
                    int i25 = blockWidth - i19;
                    copyBlockPart(this.blocks[i15 + 1], blockWidth4, i23, 52, 0, i19, dArr3, i24, 0, i25);
                    int i26 = i17;
                    int i27 = blockHeight - i17;
                    copyBlockPart(this.blocks[i15 + this.blockColumns], blockWidth2, 0, i26, i10, 52, dArr3, i24, i27, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns + 1], blockWidth4, 0, i26, 0, i19, dArr3, i24, i27, i25);
                } else {
                    i7 = i13;
                    i6 = i14;
                    i5 = i12;
                    int i28 = blockWidth2;
                    int i29 = i10;
                    int i30 = i18;
                    double[] dArr4 = dArr;
                    int i31 = blockWidth;
                    copyBlockPart(this.blocks[i15], i28, i8, 52, i29, i30, dArr4, i31, 0, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns], i28, 0, i17, i29, i30, dArr4, i31, blockHeight - i17, 0);
                }
                i13 = i7 + 1;
                i14 = i6 + 1;
                i12 = i5;
            }
            i11++;
            i12++;
        }
        return blockRealMatrix;
    }

    private void copyBlockPart(double[] dArr, int i, int i2, int i3, int i4, int i5, double[] dArr2, int i6, int i7, int i8) {
        int i9 = i5 - i4;
        int i10 = (i2 * i) + i4;
        int i11 = (i7 * i6) + i8;
        while (i2 < i3) {
            System.arraycopy(dArr, i10, dArr2, i11, i9);
            i10 += i;
            i11 += i6;
            i2++;
        }
    }

    public void setSubMatrix(double[][] dArr, int i, int i2) throws OutOfRangeException, NoDataException, NullArgumentException, DimensionMismatchException {
        BlockRealMatrix blockRealMatrix = this;
        double[][] dArr2 = dArr;
        int i3 = i;
        int i4 = i2;
        MathUtils.checkNotNull(dArr);
        int length = dArr2[0].length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int length2 = (dArr2.length + i3) - 1;
        int i5 = (i4 + length) - 1;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, i3, length2, i4, i5);
        for (double[] dArr3 : dArr2) {
            if (dArr3.length != length) {
                throw new DimensionMismatchException(length, dArr3.length);
            }
        }
        int i6 = i3 / 52;
        int i7 = (length2 + 52) / 52;
        int i8 = i4 / 52;
        int i9 = (i5 + 52) / 52;
        while (i6 < i7) {
            int blockHeight = blockRealMatrix.blockHeight(i6);
            int i10 = i6 * 52;
            int max = FastMath.max(i3, i10);
            int min = FastMath.min(length2 + 1, blockHeight + i10);
            int i11 = i8;
            while (i11 < i9) {
                int blockWidth = blockRealMatrix.blockWidth(i11);
                int i12 = i11 * 52;
                int max2 = FastMath.max(i4, i12);
                int i13 = i7;
                int i14 = length2;
                int min2 = FastMath.min(i5 + 1, i12 + blockWidth) - max2;
                int i15 = i5;
                double[] dArr4 = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * i6) + i11];
                int i16 = max;
                while (i16 < min) {
                    System.arraycopy(dArr2[i16 - i3], max2 - i4, dArr4, ((i16 - i10) * blockWidth) + (max2 - i12), min2);
                    i16++;
                    dArr2 = dArr;
                    i3 = i;
                }
                i11++;
                i7 = i13;
                length2 = i14;
                i5 = i15;
                blockRealMatrix = this;
                dArr2 = dArr;
                i3 = i;
            }
            int i17 = i7;
            int i18 = length2;
            int i19 = i5;
            i6++;
            blockRealMatrix = this;
            dArr2 = dArr;
            i3 = i;
        }
    }

    public BlockRealMatrix getRowMatrix(int i) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(1, this.columns);
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        double[] dArr = blockRealMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockColumns; i6++) {
            int blockWidth = blockWidth(i6);
            double[] dArr2 = this.blocks[(this.blockColumns * i2) + i6];
            int length = dArr.length - i4;
            if (blockWidth > length) {
                int i7 = i3 * blockWidth;
                System.arraycopy(dArr2, i7, dArr, i4, length);
                i5++;
                dArr = blockRealMatrix.blocks[i5];
                int i8 = blockWidth - length;
                System.arraycopy(dArr2, i7, dArr, 0, i8);
                i4 = i8;
            } else {
                System.arraycopy(dArr2, i3 * blockWidth, dArr, i4, blockWidth);
                i4 += blockWidth;
            }
        }
        return blockRealMatrix;
    }

    public void setRowMatrix(int i, RealMatrix realMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRowMatrix(i, (BlockRealMatrix) realMatrix);
        } catch (ClassCastException unused) {
            super.setRowMatrix(i, realMatrix);
        }
    }

    public void setRowMatrix(int i, BlockRealMatrix blockRealMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        if (blockRealMatrix.getRowDimension() == 1 && blockRealMatrix.getColumnDimension() == columnDimension) {
            int i2 = i / 52;
            int i3 = i - (i2 * 52);
            double[] dArr = blockRealMatrix.blocks[0];
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int blockWidth = blockWidth(i6);
                double[] dArr2 = this.blocks[(this.blockColumns * i2) + i6];
                int length = dArr.length - i4;
                if (blockWidth > length) {
                    int i7 = i3 * blockWidth;
                    System.arraycopy(dArr, i4, dArr2, i7, length);
                    i5++;
                    dArr = blockRealMatrix.blocks[i5];
                    int i8 = blockWidth - length;
                    System.arraycopy(dArr, 0, dArr2, i7, i8);
                    i4 = i8;
                } else {
                    System.arraycopy(dArr, i4, dArr2, i3 * blockWidth, blockWidth);
                    i4 += blockWidth;
                }
            }
            return;
        }
        throw new MatrixDimensionMismatchException(blockRealMatrix.getRowDimension(), blockRealMatrix.getColumnDimension(), 1, columnDimension);
    }

    public BlockRealMatrix getColumnMatrix(int i) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, i);
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(this.rows, 1);
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int blockWidth = blockWidth(i2);
        double[] dArr = blockRealMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            double[] dArr2 = this.blocks[(this.blockColumns * i4) + i2];
            int i7 = i5;
            int i8 = 0;
            while (i8 < blockHeight) {
                if (i6 >= dArr.length) {
                    i7++;
                    dArr = blockRealMatrix.blocks[i7];
                    i6 = 0;
                }
                int i9 = i6 + 1;
                dArr[i6] = dArr2[(i8 * blockWidth) + i3];
                i8++;
                i6 = i9;
            }
            i4++;
            i5 = i7;
        }
        return blockRealMatrix;
    }

    public void setColumnMatrix(int i, RealMatrix realMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumnMatrix(i, (BlockRealMatrix) realMatrix);
        } catch (ClassCastException unused) {
            super.setColumnMatrix(i, realMatrix);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setColumnMatrix(int i, BlockRealMatrix blockRealMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        BlockRealMatrix blockRealMatrix2 = blockRealMatrix;
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        if (blockRealMatrix.getRowDimension() == rowDimension && blockRealMatrix.getColumnDimension() == 1) {
            int i2 = i / 52;
            int i3 = i - (i2 * 52);
            int blockWidth = blockWidth(i2);
            double[] dArr = blockRealMatrix2.blocks[0];
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < this.blockRows) {
                int blockHeight = blockHeight(i4);
                double[] dArr2 = this.blocks[(this.blockColumns * i4) + i2];
                int i7 = i5;
                int i8 = 0;
                while (i8 < blockHeight) {
                    if (i6 >= dArr.length) {
                        i7++;
                        dArr = blockRealMatrix2.blocks[i7];
                        i6 = 0;
                    }
                    int i9 = i6 + 1;
                    dArr2[(i8 * blockWidth) + i3] = dArr[i6];
                    i8++;
                    i6 = i9;
                }
                i4++;
                i5 = i7;
            }
            return;
        }
        throw new MatrixDimensionMismatchException(blockRealMatrix.getRowDimension(), blockRealMatrix.getColumnDimension(), rowDimension, 1);
    }

    public RealVector getRowVector(int i) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        double[] dArr = new double[this.columns];
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, dArr, i4, blockWidth);
            i4 += blockWidth;
        }
        return new ArrayRealVector(dArr, false);
    }

    public void setRowVector(int i, RealVector realVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setRow(i, ((ArrayRealVector) realVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setRowVector(i, realVector);
        }
    }

    public RealVector getColumnVector(int i) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, i);
        double[] dArr = new double[this.rows];
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            double[] dArr2 = this.blocks[(this.blockColumns * i4) + i2];
            int i6 = i5;
            int i7 = 0;
            while (i7 < blockHeight) {
                int i8 = i6 + 1;
                dArr[i6] = dArr2[(i7 * blockWidth) + i3];
                i7++;
                i6 = i8;
            }
            i4++;
            i5 = i6;
        }
        return new ArrayRealVector(dArr, false);
    }

    public void setColumnVector(int i, RealVector realVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(i, ((ArrayRealVector) realVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setColumnVector(i, realVector);
        }
    }

    public double[] getRow(int i) throws OutOfRangeException {
        MatrixUtils.checkRowIndex(this, i);
        double[] dArr = new double[this.columns];
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, dArr, i4, blockWidth);
            i4 += blockWidth;
        }
        return dArr;
    }

    public void setRow(int i, double[] dArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkRowIndex(this, i);
        int columnDimension = getColumnDimension();
        if (dArr.length != columnDimension) {
            throw new MatrixDimensionMismatchException(1, dArr.length, 1, columnDimension);
        }
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(dArr, i4, this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, blockWidth);
            i4 += blockWidth;
        }
    }

    public double[] getColumn(int i) throws OutOfRangeException {
        MatrixUtils.checkColumnIndex(this, i);
        double[] dArr = new double[this.rows];
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            double[] dArr2 = this.blocks[(this.blockColumns * i4) + i2];
            int i6 = i5;
            int i7 = 0;
            while (i7 < blockHeight) {
                int i8 = i6 + 1;
                dArr[i6] = dArr2[(i7 * blockWidth) + i3];
                i7++;
                i6 = i8;
            }
            i4++;
            i5 = i6;
        }
        return dArr;
    }

    public void setColumn(int i, double[] dArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        MatrixUtils.checkColumnIndex(this, i);
        int rowDimension = getRowDimension();
        if (dArr.length != rowDimension) {
            throw new MatrixDimensionMismatchException(dArr.length, 1, rowDimension, 1);
        }
        int i2 = i / 52;
        int i3 = i - (i2 * 52);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            double[] dArr2 = this.blocks[(this.blockColumns * i4) + i2];
            int i6 = i5;
            int i7 = 0;
            while (i7 < blockHeight) {
                int i8 = i6 + 1;
                dArr2[(i7 * blockWidth) + i3] = dArr[i6];
                i7++;
                i6 = i8;
            }
            i4++;
            i5 = i6;
        }
    }

    public double getEntry(int i, int i2) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        int i3 = i / 52;
        int i4 = i2 / 52;
        return this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 52)) * blockWidth(i4)) + (i2 - (i4 * 52))];
    }

    public void setEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        int i3 = i / 52;
        int i4 = i2 / 52;
        this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 52)) * blockWidth(i4)) + (i2 - (i4 * 52))] = d;
    }

    public void addToEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        int i3 = i / 52;
        int i4 = i2 / 52;
        int blockWidth = ((i - (i3 * 52)) * blockWidth(i4)) + (i2 - (i4 * 52));
        double[] dArr = this.blocks[(i3 * this.blockColumns) + i4];
        dArr[blockWidth] = dArr[blockWidth] + d;
    }

    public void multiplyEntry(int i, int i2, double d) throws OutOfRangeException {
        MatrixUtils.checkMatrixIndex(this, i, i2);
        int i3 = i / 52;
        int i4 = i2 / 52;
        int blockWidth = ((i - (i3 * 52)) * blockWidth(i4)) + (i2 - (i4 * 52));
        double[] dArr = this.blocks[(i3 * this.blockColumns) + i4];
        dArr[blockWidth] = dArr[blockWidth] * d;
    }

    public BlockRealMatrix transpose() {
        BlockRealMatrix blockRealMatrix = new BlockRealMatrix(getColumnDimension(), getRowDimension());
        int i = 0;
        int i2 = 0;
        while (i < this.blockColumns) {
            int i3 = i2;
            for (int i4 = 0; i4 < this.blockRows; i4++) {
                double[] dArr = blockRealMatrix.blocks[i3];
                double[] dArr2 = this.blocks[(this.blockColumns * i4) + i];
                int i5 = i * 52;
                int min = FastMath.min(i5 + 52, this.columns);
                int i6 = i4 * 52;
                int min2 = FastMath.min(i6 + 52, this.rows);
                int i7 = 0;
                int i8 = i5;
                while (i8 < min) {
                    int i9 = min - i5;
                    int i10 = i8 - i5;
                    int i11 = i7;
                    for (int i12 = i6; i12 < min2; i12++) {
                        dArr[i11] = dArr2[i10];
                        i11++;
                        i10 += i9;
                    }
                    i8++;
                    i7 = i11;
                }
                i3++;
            }
            i++;
            i2 = i3;
        }
        return blockRealMatrix;
    }

    public int getRowDimension() {
        return this.rows;
    }

    public int getColumnDimension() {
        return this.columns;
    }

    public double[] operate(double[] dArr) throws DimensionMismatchException {
        double[] dArr2 = dArr;
        if (dArr2.length != this.columns) {
            throw new DimensionMismatchException(dArr2.length, this.columns);
        }
        double[] dArr3 = new double[this.rows];
        for (int i = 0; i < this.blockRows; i++) {
            int i2 = i * 52;
            int min = FastMath.min(i2 + 52, this.rows);
            for (int i3 = 0; i3 < this.blockColumns; i3++) {
                double[] dArr4 = this.blocks[(this.blockColumns * i) + i3];
                int i4 = i3 * 52;
                int min2 = FastMath.min(i4 + 52, this.columns);
                int i5 = i2;
                int i6 = 0;
                while (i5 < min) {
                    double d = 0.0d;
                    int i7 = i6;
                    int i8 = i4;
                    while (i8 < min2 - 3) {
                        d += (dArr4[i7] * dArr2[i8]) + (dArr4[i7 + 1] * dArr2[i8 + 1]) + (dArr4[i7 + 2] * dArr2[i8 + 2]) + (dArr4[i7 + 3] * dArr2[i8 + 3]);
                        i7 += 4;
                        i8 += 4;
                    }
                    while (i8 < min2) {
                        int i9 = i7 + 1;
                        double d2 = dArr4[i7];
                        d += d2 * dArr2[i8];
                        i8++;
                        i7 = i9;
                    }
                    dArr3[i5] = dArr3[i5] + d;
                    i5++;
                    i6 = i7;
                }
            }
        }
        return dArr3;
    }

    public double[] preMultiply(double[] dArr) throws DimensionMismatchException {
        BlockRealMatrix blockRealMatrix = this;
        double[] dArr2 = dArr;
        if (dArr2.length != blockRealMatrix.rows) {
            throw new DimensionMismatchException(dArr2.length, blockRealMatrix.rows);
        }
        double[] dArr3 = new double[blockRealMatrix.columns];
        int i = 0;
        while (i < blockRealMatrix.blockColumns) {
            int blockWidth = blockRealMatrix.blockWidth(i);
            int i2 = blockWidth + blockWidth;
            int i3 = i2 + blockWidth;
            int i4 = i3 + blockWidth;
            int i5 = i * 52;
            int min = FastMath.min(i5 + 52, blockRealMatrix.columns);
            int i6 = 0;
            while (i6 < blockRealMatrix.blockRows) {
                double[] dArr4 = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * i6) + i];
                int i7 = i6 * 52;
                int min2 = FastMath.min(i7 + 52, blockRealMatrix.rows);
                int i8 = i5;
                while (i8 < min) {
                    int i9 = i8 - i5;
                    double d = 0.0d;
                    int i10 = i7;
                    while (i10 < min2 - 3) {
                        d += (dArr4[i9] * dArr2[i10]) + (dArr4[i9 + blockWidth] * dArr2[i10 + 1]) + (dArr4[i9 + i2] * dArr2[i10 + 2]) + (dArr4[i9 + i3] * dArr2[i10 + 3]);
                        i9 += i4;
                        i10 += 4;
                    }
                    while (i10 < min2) {
                        d += dArr4[i9] * dArr2[i10];
                        i9 += blockWidth;
                        i10++;
                    }
                    dArr3[i8] = dArr3[i8] + d;
                    i8++;
                }
                i6++;
                blockRealMatrix = this;
            }
            i++;
            blockRealMatrix = this;
        }
        return dArr3;
    }

    public double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        realMatrixChangingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int i = 0; i < this.blockRows; i++) {
            int i2 = i * 52;
            int min = FastMath.min(i2 + 52, this.rows);
            for (int i3 = i2; i3 < min; i3++) {
                for (int i4 = 0; i4 < this.blockColumns; i4++) {
                    int blockWidth = blockWidth(i4);
                    int i5 = i4 * 52;
                    int min2 = FastMath.min(i5 + 52, this.columns);
                    double[] dArr = this.blocks[(this.blockColumns * i) + i4];
                    int i6 = (i3 - i2) * blockWidth;
                    while (i5 < min2) {
                        dArr[i6] = realMatrixChangingVisitor.visit(i3, i5, dArr[i6]);
                        i6++;
                        i5++;
                    }
                }
            }
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        realMatrixPreservingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int i = 0; i < this.blockRows; i++) {
            int i2 = i * 52;
            int min = FastMath.min(i2 + 52, this.rows);
            for (int i3 = i2; i3 < min; i3++) {
                for (int i4 = 0; i4 < this.blockColumns; i4++) {
                    int blockWidth = blockWidth(i4);
                    int i5 = i4 * 52;
                    int min2 = FastMath.min(i5 + 52, this.columns);
                    double[] dArr = this.blocks[(this.blockColumns * i) + i4];
                    int i6 = (i3 - i2) * blockWidth;
                    while (i5 < min2) {
                        realMatrixPreservingVisitor.visit(i3, i5, dArr[i6]);
                        i6++;
                        i5++;
                    }
                }
            }
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInRowOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        MatrixUtils.checkSubMatrixIndex(this, i5, i6, i7, i8);
        realMatrixChangingVisitor.start(this.rows, this.columns, i5, i6, i7, i8);
        int i9 = i5 / 52;
        while (true) {
            int i10 = 1;
            if (i9 < (i6 / 52) + 1) {
                int i11 = i9 * 52;
                int i12 = i9 + 1;
                int min = FastMath.min(i12 * 52, 1 + i6);
                for (int max = FastMath.max(i5, i11); max < min; max++) {
                    int i13 = i7 / 52;
                    while (i13 < (i8 / 52) + i10) {
                        int blockWidth = blockWidth(i13);
                        int i14 = i13 * 52;
                        int max2 = FastMath.max(i7, i14);
                        int i15 = i13 + 1;
                        int i16 = i12;
                        int i17 = min;
                        int min2 = FastMath.min(i15 * 52, i10 + i8);
                        double[] dArr = this.blocks[(this.blockColumns * i9) + i13];
                        int i18 = (((max - i11) * blockWidth) + max2) - i14;
                        while (max2 < min2) {
                            dArr[i18] = realMatrixChangingVisitor.visit(max, max2, dArr[i18]);
                            i18++;
                            max2++;
                        }
                        RealMatrixChangingVisitor realMatrixChangingVisitor2 = realMatrixChangingVisitor;
                        i13 = i15;
                        i12 = i16;
                        min = i17;
                        i10 = 1;
                    }
                    RealMatrixChangingVisitor realMatrixChangingVisitor3 = realMatrixChangingVisitor;
                    int i19 = i10;
                    int i20 = i12;
                    int i21 = min;
                }
                RealMatrixChangingVisitor realMatrixChangingVisitor4 = realMatrixChangingVisitor;
                i9 = i12;
            } else {
                RealMatrixChangingVisitor realMatrixChangingVisitor5 = realMatrixChangingVisitor;
                return realMatrixChangingVisitor.end();
            }
        }
    }

    public double walkInRowOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        MatrixUtils.checkSubMatrixIndex(this, i5, i6, i7, i8);
        realMatrixPreservingVisitor.start(this.rows, this.columns, i5, i6, i7, i8);
        int i9 = i5 / 52;
        while (true) {
            int i10 = 1;
            if (i9 < (i6 / 52) + 1) {
                int i11 = i9 * 52;
                int i12 = i9 + 1;
                int min = FastMath.min(i12 * 52, 1 + i6);
                for (int max = FastMath.max(i5, i11); max < min; max++) {
                    int i13 = i7 / 52;
                    while (i13 < (i8 / 52) + i10) {
                        int blockWidth = blockWidth(i13);
                        int i14 = i13 * 52;
                        int max2 = FastMath.max(i7, i14);
                        int i15 = i13 + 1;
                        int i16 = i12;
                        int i17 = min;
                        int min2 = FastMath.min(i15 * 52, i10 + i8);
                        double[] dArr = this.blocks[(this.blockColumns * i9) + i13];
                        int i18 = (((max - i11) * blockWidth) + max2) - i14;
                        while (max2 < min2) {
                            realMatrixPreservingVisitor.visit(max, max2, dArr[i18]);
                            i18++;
                            max2++;
                        }
                        RealMatrixPreservingVisitor realMatrixPreservingVisitor2 = realMatrixPreservingVisitor;
                        i13 = i15;
                        i12 = i16;
                        min = i17;
                        i10 = 1;
                    }
                    RealMatrixPreservingVisitor realMatrixPreservingVisitor3 = realMatrixPreservingVisitor;
                    int i19 = i10;
                    int i20 = i12;
                    int i21 = min;
                }
                RealMatrixPreservingVisitor realMatrixPreservingVisitor4 = realMatrixPreservingVisitor;
                i9 = i12;
            } else {
                RealMatrixPreservingVisitor realMatrixPreservingVisitor5 = realMatrixPreservingVisitor;
                return realMatrixPreservingVisitor.end();
            }
        }
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor realMatrixChangingVisitor) {
        realMatrixChangingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int i = 0;
        int i2 = 0;
        while (i < this.blockRows) {
            int i3 = i * 52;
            int min = FastMath.min(i3 + 52, this.rows);
            int i4 = i2;
            for (int i5 = 0; i5 < this.blockColumns; i5++) {
                int i6 = i5 * 52;
                int min2 = FastMath.min(i6 + 52, this.columns);
                double[] dArr = this.blocks[i4];
                int i7 = 0;
                int i8 = i3;
                while (i8 < min) {
                    int i9 = i7;
                    for (int i10 = i6; i10 < min2; i10++) {
                        dArr[i9] = realMatrixChangingVisitor.visit(i8, i10, dArr[i9]);
                        i9++;
                    }
                    i8++;
                    i7 = i9;
                }
                i4++;
            }
            i++;
            i2 = i4;
        }
        return realMatrixChangingVisitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor) {
        realMatrixPreservingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int i = 0;
        int i2 = 0;
        while (i < this.blockRows) {
            int i3 = i * 52;
            int min = FastMath.min(i3 + 52, this.rows);
            int i4 = i2;
            for (int i5 = 0; i5 < this.blockColumns; i5++) {
                int i6 = i5 * 52;
                int min2 = FastMath.min(i6 + 52, this.columns);
                double[] dArr = this.blocks[i4];
                int i7 = 0;
                int i8 = i3;
                while (i8 < min) {
                    int i9 = i7;
                    for (int i10 = i6; i10 < min2; i10++) {
                        realMatrixPreservingVisitor.visit(i8, i10, dArr[i9]);
                        i9++;
                    }
                    i8++;
                    i7 = i9;
                }
                i4++;
            }
            i++;
            i2 = i4;
        }
        return realMatrixPreservingVisitor.end();
    }

    public double walkInOptimizedOrder(RealMatrixChangingVisitor realMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        BlockRealMatrix blockRealMatrix = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, i5, i6, i7, i8);
        realMatrixChangingVisitor.start(blockRealMatrix.rows, blockRealMatrix.columns, i5, i6, i7, i8);
        int i9 = i5 / 52;
        while (true) {
            if (i9 < (i6 / 52) + 1) {
                int i10 = i9 * 52;
                int max = FastMath.max(i5, i10);
                int i11 = i9 + 1;
                int min = FastMath.min(i11 * 52, 1 + i6);
                int i12 = i7 / 52;
                for (int i13 = 1; i12 < (i8 / 52) + i13; i13 = 1) {
                    int blockWidth = blockRealMatrix.blockWidth(i12);
                    int i14 = i12 * 52;
                    int max2 = FastMath.max(i7, i14);
                    int i15 = i12 + 1;
                    int i16 = max;
                    int i17 = i11;
                    int min2 = FastMath.min(i15 * 52, i13 + i8);
                    double[] dArr = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * i9) + i12];
                    int i18 = i16;
                    while (i18 < min) {
                        int i19 = (((i18 - i10) * blockWidth) + max2) - i14;
                        int i20 = max2;
                        while (i20 < min2) {
                            int i21 = i9;
                            int i22 = i10;
                            dArr[i19] = realMatrixChangingVisitor.visit(i18, i20, dArr[i19]);
                            i19++;
                            i20++;
                            i9 = i21;
                            i10 = i22;
                        }
                        int i23 = i9;
                        int i24 = i10;
                        RealMatrixChangingVisitor realMatrixChangingVisitor2 = realMatrixChangingVisitor;
                        i18++;
                        i10 = i24;
                    }
                    int i25 = i10;
                    RealMatrixChangingVisitor realMatrixChangingVisitor3 = realMatrixChangingVisitor;
                    i12 = i15;
                    max = i16;
                    i11 = i17;
                    i10 = i25;
                    blockRealMatrix = this;
                }
                RealMatrixChangingVisitor realMatrixChangingVisitor4 = realMatrixChangingVisitor;
                i9 = i11;
            } else {
                RealMatrixChangingVisitor realMatrixChangingVisitor5 = realMatrixChangingVisitor;
                return realMatrixChangingVisitor.end();
            }
        }
    }

    public double walkInOptimizedOrder(RealMatrixPreservingVisitor realMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        BlockRealMatrix blockRealMatrix = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        MatrixUtils.checkSubMatrixIndex(blockRealMatrix, i5, i6, i7, i8);
        realMatrixPreservingVisitor.start(blockRealMatrix.rows, blockRealMatrix.columns, i5, i6, i7, i8);
        int i9 = i5 / 52;
        while (true) {
            if (i9 < (i6 / 52) + 1) {
                int i10 = i9 * 52;
                int max = FastMath.max(i5, i10);
                int i11 = i9 + 1;
                int min = FastMath.min(i11 * 52, 1 + i6);
                int i12 = i7 / 52;
                for (int i13 = 1; i12 < (i8 / 52) + i13; i13 = 1) {
                    int blockWidth = blockRealMatrix.blockWidth(i12);
                    int i14 = i12 * 52;
                    int max2 = FastMath.max(i7, i14);
                    int i15 = i12 + 1;
                    int i16 = max;
                    int i17 = i11;
                    int min2 = FastMath.min(i15 * 52, i13 + i8);
                    double[] dArr = blockRealMatrix.blocks[(blockRealMatrix.blockColumns * i9) + i12];
                    int i18 = i16;
                    while (i18 < min) {
                        int i19 = (((i18 - i10) * blockWidth) + max2) - i14;
                        int i20 = max2;
                        while (i20 < min2) {
                            int i21 = i9;
                            int i22 = i10;
                            realMatrixPreservingVisitor.visit(i18, i20, dArr[i19]);
                            i19++;
                            i20++;
                            i9 = i21;
                            i10 = i22;
                        }
                        int i23 = i9;
                        int i24 = i10;
                        RealMatrixPreservingVisitor realMatrixPreservingVisitor2 = realMatrixPreservingVisitor;
                        i18++;
                        i10 = i24;
                    }
                    int i25 = i10;
                    RealMatrixPreservingVisitor realMatrixPreservingVisitor3 = realMatrixPreservingVisitor;
                    i12 = i15;
                    max = i16;
                    i11 = i17;
                    i10 = i25;
                    blockRealMatrix = this;
                }
                RealMatrixPreservingVisitor realMatrixPreservingVisitor4 = realMatrixPreservingVisitor;
                i9 = i11;
            } else {
                RealMatrixPreservingVisitor realMatrixPreservingVisitor5 = realMatrixPreservingVisitor;
                return realMatrixPreservingVisitor.end();
            }
        }
    }

    private int blockHeight(int i) {
        if (i == this.blockRows - 1) {
            return this.rows - (i * 52);
        }
        return 52;
    }

    private int blockWidth(int i) {
        if (i == this.blockColumns - 1) {
            return this.columns - (i * 52);
        }
        return 52;
    }
}
