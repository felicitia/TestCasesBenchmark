package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class BlockFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
    public static final int BLOCK_SIZE = 36;
    private static final long serialVersionUID = -4602336630143123183L;
    private final int blockColumns;
    private final int blockRows;
    private final T[][] blocks;
    private final int columns;
    private final int rows;

    public BlockFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        super(field, i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = ((i + 36) - 1) / 36;
        this.blockColumns = ((i2 + 36) - 1) / 36;
        this.blocks = createBlocksLayout(field, i, i2);
    }

    public BlockFieldMatrix(T[][] tArr) throws DimensionMismatchException {
        this(tArr.length, tArr[0].length, toBlocksLayout(tArr), false);
    }

    public BlockFieldMatrix(int i, int i2, T[][] tArr, boolean z) throws DimensionMismatchException, NotStrictlyPositiveException {
        super(extractField(tArr), i, i2);
        this.rows = i;
        this.columns = i2;
        this.blockRows = ((i + 36) - 1) / 36;
        this.blockColumns = ((i2 + 36) - 1) / 36;
        if (z) {
            this.blocks = buildArray(getField(), this.blockRows * this.blockColumns, -1);
        } else {
            this.blocks = tArr;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < this.blockRows) {
            int blockHeight = blockHeight(i3);
            int i5 = i4;
            int i6 = 0;
            while (i6 < this.blockColumns) {
                if (tArr[i5].length != blockWidth(i6) * blockHeight) {
                    throw new DimensionMismatchException(tArr[i5].length, blockHeight * blockWidth(i6));
                }
                if (z) {
                    this.blocks[i5] = (FieldElement[]) tArr[i5].clone();
                }
                i6++;
                i5++;
            }
            i3++;
            i4 = i5;
        }
    }

    public static <T extends FieldElement<T>> T[][] toBlocksLayout(T[][] tArr) throws DimensionMismatchException {
        T[][] tArr2 = tArr;
        int i = 0;
        int length = tArr2.length;
        int length2 = tArr2[0].length;
        int i2 = ((length + 36) - 1) / 36;
        int i3 = ((length2 + 36) - 1) / 36;
        for (T[] length3 : tArr2) {
            int length4 = length3.length;
            if (length4 != length2) {
                throw new DimensionMismatchException(length2, length4);
            }
        }
        Field extractField = extractField(tArr);
        T[][] buildArray = buildArray(extractField, i2 * i3, -1);
        int i4 = 0;
        int i5 = 0;
        while (i4 < i2) {
            int i6 = i4 * 36;
            int min = FastMath.min(i6 + 36, length);
            int i7 = min - i6;
            int i8 = i5;
            int i9 = i;
            while (i9 < i3) {
                int i10 = i9 * 36;
                int min2 = FastMath.min(i10 + 36, length2) - i10;
                T[] buildArray2 = buildArray(extractField, i7 * min2);
                buildArray[i8] = buildArray2;
                int i11 = length;
                int i12 = length2;
                int i13 = i6;
                int i14 = 0;
                while (i13 < min) {
                    int i15 = i2;
                    System.arraycopy(tArr2[i13], i10, buildArray2, i14, min2);
                    i14 += min2;
                    i13++;
                    i2 = i15;
                }
                int i16 = i2;
                i8++;
                i9++;
                length = i11;
                length2 = i12;
            }
            int i17 = length;
            int i18 = length2;
            int i19 = i2;
            i4++;
            i5 = i8;
            i = 0;
        }
        return buildArray;
    }

    public static <T extends FieldElement<T>> T[][] createBlocksLayout(Field<T> field, int i, int i2) {
        int i3 = ((i + 36) - 1) / 36;
        int i4 = ((i2 + 36) - 1) / 36;
        T[][] buildArray = buildArray(field, i3 * i4, -1);
        int i5 = 0;
        int i6 = 0;
        while (i5 < i3) {
            int i7 = i5 * 36;
            int min = FastMath.min(i7 + 36, i) - i7;
            int i8 = i6;
            for (int i9 = 0; i9 < i4; i9++) {
                int i10 = i9 * 36;
                buildArray[i8] = buildArray(field, (FastMath.min(i10 + 36, i2) - i10) * min);
                i8++;
            }
            i5++;
            i6 = i8;
        }
        return buildArray;
    }

    public FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new BlockFieldMatrix(getField(), i, i2);
    }

    public FieldMatrix<T> copy() {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        for (int i = 0; i < this.blocks.length; i++) {
            System.arraycopy(this.blocks[i], 0, blockFieldMatrix.blocks[i], 0, this.blocks[i].length);
        }
        return blockFieldMatrix;
    }

    public FieldMatrix<T> add(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        FieldMatrix<T> fieldMatrix2 = fieldMatrix;
        try {
            return add((BlockFieldMatrix) fieldMatrix2);
        } catch (ClassCastException unused) {
            checkAdditionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
            int i = 0;
            int i2 = 0;
            while (i < blockFieldMatrix.blockRows) {
                int i3 = i2;
                for (int i4 = 0; i4 < blockFieldMatrix.blockColumns; i4++) {
                    T[] tArr = blockFieldMatrix.blocks[i3];
                    T[] tArr2 = this.blocks[i3];
                    int i5 = i * 36;
                    int min = FastMath.min(i5 + 36, this.rows);
                    int i6 = i4 * 36;
                    int min2 = FastMath.min(i6 + 36, this.columns);
                    int i7 = 0;
                    while (i5 < min) {
                        int i8 = i7;
                        for (int i9 = i6; i9 < min2; i9++) {
                            tArr[i8] = (FieldElement) tArr2[i8].add(fieldMatrix2.getEntry(i5, i9));
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
            return blockFieldMatrix;
        }
    }

    public BlockFieldMatrix<T> add(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix2.blocks.length; i++) {
            T[] tArr = blockFieldMatrix2.blocks[i];
            T[] tArr2 = this.blocks[i];
            T[] tArr3 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = (FieldElement) tArr2[i2].add(tArr3[i2]);
            }
        }
        return blockFieldMatrix2;
    }

    public FieldMatrix<T> subtract(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        FieldMatrix<T> fieldMatrix2 = fieldMatrix;
        try {
            return subtract((BlockFieldMatrix) fieldMatrix2);
        } catch (ClassCastException unused) {
            checkSubtractionCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
            int i = 0;
            int i2 = 0;
            while (i < blockFieldMatrix.blockRows) {
                int i3 = i2;
                for (int i4 = 0; i4 < blockFieldMatrix.blockColumns; i4++) {
                    T[] tArr = blockFieldMatrix.blocks[i3];
                    T[] tArr2 = this.blocks[i3];
                    int i5 = i * 36;
                    int min = FastMath.min(i5 + 36, this.rows);
                    int i6 = i4 * 36;
                    int min2 = FastMath.min(i6 + 36, this.columns);
                    int i7 = 0;
                    while (i5 < min) {
                        int i8 = i7;
                        for (int i9 = i6; i9 < min2; i9++) {
                            tArr[i8] = (FieldElement) tArr2[i8].subtract(fieldMatrix2.getEntry(i5, i9));
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
            return blockFieldMatrix;
        }
    }

    public BlockFieldMatrix<T> subtract(BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(blockFieldMatrix);
        BlockFieldMatrix<T> blockFieldMatrix2 = new BlockFieldMatrix<>(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix2.blocks.length; i++) {
            T[] tArr = blockFieldMatrix2.blocks[i];
            T[] tArr2 = this.blocks[i];
            T[] tArr3 = blockFieldMatrix.blocks[i];
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = (FieldElement) tArr2[i2].subtract(tArr3[i2]);
            }
        }
        return blockFieldMatrix2;
    }

    public FieldMatrix<T> scalarAdd(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix.blocks.length; i++) {
            T[] tArr = blockFieldMatrix.blocks[i];
            T[] tArr2 = this.blocks[i];
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = (FieldElement) tArr2[i2].add(t);
            }
        }
        return blockFieldMatrix;
    }

    public FieldMatrix<T> scalarMultiply(T t) {
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, this.columns);
        for (int i = 0; i < blockFieldMatrix.blocks.length; i++) {
            T[] tArr = blockFieldMatrix.blocks[i];
            T[] tArr2 = this.blocks[i];
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = (FieldElement) tArr2[i2].multiply(t);
            }
        }
        return blockFieldMatrix;
    }

    public FieldMatrix<T> multiply(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        BlockFieldMatrix blockFieldMatrix = this;
        FieldMatrix<T> fieldMatrix2 = fieldMatrix;
        try {
            return blockFieldMatrix.multiply((BlockFieldMatrix) fieldMatrix2);
        } catch (ClassCastException unused) {
            checkMultiplicationCompatible(fieldMatrix);
            BlockFieldMatrix blockFieldMatrix2 = new BlockFieldMatrix(getField(), blockFieldMatrix.rows, fieldMatrix.getColumnDimension());
            FieldElement fieldElement = (FieldElement) getField().getZero();
            int i = 0;
            int i2 = 0;
            while (i < blockFieldMatrix2.blockRows) {
                int i3 = i * 36;
                int min = FastMath.min(i3 + 36, blockFieldMatrix.rows);
                int i4 = i2;
                int i5 = 0;
                while (i5 < blockFieldMatrix2.blockColumns) {
                    int i6 = i5 * 36;
                    int min2 = FastMath.min(i6 + 36, fieldMatrix.getColumnDimension());
                    T[] tArr = blockFieldMatrix2.blocks[i4];
                    int i7 = 0;
                    while (i7 < blockFieldMatrix.blockColumns) {
                        int blockWidth = blockFieldMatrix.blockWidth(i7);
                        T[] tArr2 = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i) + i7];
                        int i8 = i7 * 36;
                        int i9 = i3;
                        int i10 = 0;
                        while (i9 < min) {
                            int i11 = (i9 - i3) * blockWidth;
                            FieldElement fieldElement2 = fieldElement;
                            int i12 = i11 + blockWidth;
                            int i13 = i3;
                            int i14 = i6;
                            while (i14 < min2) {
                                int i15 = min;
                                int i16 = i6;
                                int i17 = min2;
                                int i18 = i8;
                                int i19 = i11;
                                FieldElement fieldElement3 = fieldElement2;
                                while (i19 < i12) {
                                    fieldElement3 = (FieldElement) fieldElement3.add(tArr2[i19].multiply(fieldMatrix2.getEntry(i18, i14)));
                                    i18++;
                                    i19++;
                                    i12 = i12;
                                    tArr2 = tArr2;
                                }
                                int i20 = i12;
                                T[] tArr3 = tArr2;
                                tArr[i10] = (FieldElement) tArr[i10].add(fieldElement3);
                                i10++;
                                i14++;
                                min = i15;
                                i6 = i16;
                                min2 = i17;
                                i12 = i20;
                            }
                            T[] tArr4 = tArr2;
                            int i21 = min;
                            int i22 = i6;
                            int i23 = min2;
                            i9++;
                            fieldElement = fieldElement2;
                            i3 = i13;
                        }
                        FieldElement fieldElement4 = fieldElement;
                        int i24 = i3;
                        int i25 = min;
                        int i26 = i6;
                        int i27 = min2;
                        i7++;
                        blockFieldMatrix = this;
                    }
                    FieldElement fieldElement5 = fieldElement;
                    int i28 = i3;
                    int i29 = min;
                    i4++;
                    i5++;
                    blockFieldMatrix = this;
                }
                FieldElement fieldElement6 = fieldElement;
                i++;
                i2 = i4;
                blockFieldMatrix = this;
            }
            return blockFieldMatrix2;
        }
    }

    public BlockFieldMatrix<T> multiply(BlockFieldMatrix<T> blockFieldMatrix) throws DimensionMismatchException {
        int i;
        BlockFieldMatrix blockFieldMatrix2 = this;
        BlockFieldMatrix<T> blockFieldMatrix3 = blockFieldMatrix;
        checkMultiplicationCompatible(blockFieldMatrix);
        BlockFieldMatrix blockFieldMatrix4 = new BlockFieldMatrix(getField(), blockFieldMatrix2.rows, blockFieldMatrix3.columns);
        FieldElement fieldElement = (FieldElement) getField().getZero();
        int i2 = 0;
        int i3 = 0;
        while (i2 < blockFieldMatrix4.blockRows) {
            int i4 = i2 * 36;
            int min = FastMath.min(i4 + 36, blockFieldMatrix2.rows);
            int i5 = i3;
            int i6 = 0;
            while (i6 < blockFieldMatrix4.blockColumns) {
                int blockWidth = blockFieldMatrix4.blockWidth(i6);
                int i7 = blockWidth + blockWidth;
                int i8 = i7 + blockWidth;
                int i9 = i8 + blockWidth;
                T[] tArr = blockFieldMatrix4.blocks[i5];
                int i10 = 0;
                while (i10 < blockFieldMatrix2.blockColumns) {
                    int blockWidth2 = blockFieldMatrix2.blockWidth(i10);
                    FieldElement fieldElement2 = fieldElement;
                    BlockFieldMatrix blockFieldMatrix5 = blockFieldMatrix4;
                    T[] tArr2 = blockFieldMatrix2.blocks[(blockFieldMatrix2.blockColumns * i2) + i10];
                    T[] tArr3 = blockFieldMatrix3.blocks[(blockFieldMatrix3.blockColumns * i10) + i6];
                    int i11 = i4;
                    int i12 = 0;
                    while (i11 < min) {
                        int i13 = (i11 - i4) * blockWidth2;
                        int i14 = i13 + blockWidth2;
                        int i15 = blockWidth2;
                        int i16 = 0;
                        while (i16 < blockWidth) {
                            int i17 = i16;
                            int i18 = i4;
                            int i19 = min;
                            FieldElement fieldElement3 = fieldElement2;
                            int i20 = i13;
                            while (true) {
                                i = i2;
                                if (i20 >= i14 - 3) {
                                    break;
                                }
                                fieldElement3 = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement3.add(tArr2[i20].multiply(tArr3[i17]))).add(tArr2[i20 + 1].multiply(tArr3[i17 + blockWidth]))).add(tArr2[i20 + 2].multiply(tArr3[i17 + i7]))).add(tArr2[i20 + 3].multiply(tArr3[i17 + i8]));
                                i20 += 4;
                                i17 += i9;
                                i2 = i;
                                i6 = i6;
                            }
                            int i21 = i6;
                            while (i20 < i14) {
                                fieldElement3 = (FieldElement) fieldElement3.add(tArr2[i20].multiply(tArr3[i17]));
                                i17 += blockWidth;
                                i20++;
                            }
                            tArr[i12] = (FieldElement) tArr[i12].add(fieldElement3);
                            i12++;
                            i16++;
                            i4 = i18;
                            min = i19;
                            i2 = i;
                            i6 = i21;
                        }
                        int i22 = i2;
                        int i23 = i6;
                        int i24 = i4;
                        int i25 = min;
                        i11++;
                        blockWidth2 = i15;
                        BlockFieldMatrix<T> blockFieldMatrix6 = blockFieldMatrix;
                    }
                    int i26 = i2;
                    int i27 = i6;
                    int i28 = i4;
                    int i29 = min;
                    i10++;
                    fieldElement = fieldElement2;
                    blockFieldMatrix4 = blockFieldMatrix5;
                    blockFieldMatrix2 = this;
                    blockFieldMatrix3 = blockFieldMatrix;
                }
                BlockFieldMatrix blockFieldMatrix7 = blockFieldMatrix4;
                FieldElement fieldElement4 = fieldElement;
                int i30 = i2;
                int i31 = i4;
                int i32 = min;
                i5++;
                i6++;
                blockFieldMatrix2 = this;
                blockFieldMatrix3 = blockFieldMatrix;
            }
            BlockFieldMatrix blockFieldMatrix8 = blockFieldMatrix4;
            FieldElement fieldElement5 = fieldElement;
            i2++;
            i3 = i5;
            blockFieldMatrix2 = this;
            blockFieldMatrix3 = blockFieldMatrix;
        }
        return blockFieldMatrix4;
    }

    public T[][] getData() {
        T[][] buildArray = buildArray(getField(), getRowDimension(), getColumnDimension());
        int i = this.columns - ((this.blockColumns - 1) * 36);
        for (int i2 = 0; i2 < this.blockRows; i2++) {
            int i3 = i2 * 36;
            int min = FastMath.min(i3 + 36, this.rows);
            int i4 = 0;
            int i5 = 0;
            while (i3 < min) {
                T[] tArr = buildArray[i3];
                int i6 = 0;
                int i7 = this.blockColumns * i2;
                int i8 = 0;
                while (i8 < this.blockColumns - 1) {
                    int i9 = i7 + 1;
                    System.arraycopy(this.blocks[i7], i4, tArr, i6, 36);
                    i6 += 36;
                    i8++;
                    i7 = i9;
                }
                System.arraycopy(this.blocks[i7], i5, tArr, i6, i);
                i4 += 36;
                i5 += i;
                i3++;
            }
        }
        return buildArray;
    }

    public FieldMatrix<T> getSubMatrix(int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5;
        int i6;
        int i7;
        checkSubMatrixIndex(i, i2, i3, i4);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), (i2 - i) + 1, (i4 - i3) + 1);
        int i8 = i % 36;
        int i9 = i3 / 36;
        int i10 = i3 % 36;
        int i11 = i / 36;
        int i12 = 0;
        while (i12 < blockFieldMatrix.blockRows) {
            int blockHeight = blockFieldMatrix.blockHeight(i12);
            int i13 = i9;
            int i14 = 0;
            while (i14 < blockFieldMatrix.blockColumns) {
                int blockWidth = blockFieldMatrix.blockWidth(i14);
                T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i12) + i14];
                int i15 = (this.blockColumns * i11) + i13;
                int blockWidth2 = blockWidth(i13);
                int i16 = blockHeight + i8;
                int i17 = i16 - 36;
                int i18 = blockWidth + i10;
                int i19 = i18 - 36;
                if (i17 <= 0) {
                    i7 = i13;
                    i6 = i14;
                    i5 = i12;
                    if (i19 > 0) {
                        int blockWidth3 = blockWidth(i7 + 1);
                        int i20 = i8;
                        int i21 = i16;
                        T[] tArr2 = tArr;
                        int i22 = blockWidth;
                        copyBlockPart(this.blocks[i15], blockWidth2, i20, i21, i10, 36, tArr2, i22, 0, 0);
                        copyBlockPart(this.blocks[i15 + 1], blockWidth3, i20, i21, 0, i19, tArr2, i22, 0, blockWidth - i19);
                    } else {
                        copyBlockPart(this.blocks[i15], blockWidth2, i8, i16, i10, i18, tArr, blockWidth, 0, 0);
                    }
                } else if (i19 > 0) {
                    int blockWidth4 = blockWidth(i13 + 1);
                    int i23 = i8;
                    T[] tArr3 = tArr;
                    i7 = i13;
                    int i24 = blockWidth;
                    i6 = i14;
                    i5 = i12;
                    copyBlockPart(this.blocks[i15], blockWidth2, i23, 36, i10, 36, tArr3, i24, 0, 0);
                    int i25 = blockWidth - i19;
                    copyBlockPart(this.blocks[i15 + 1], blockWidth4, i23, 36, 0, i19, tArr3, i24, 0, i25);
                    int i26 = i17;
                    int i27 = blockHeight - i17;
                    copyBlockPart(this.blocks[i15 + this.blockColumns], blockWidth2, 0, i26, i10, 36, tArr3, i24, i27, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns + 1], blockWidth4, 0, i26, 0, i19, tArr3, i24, i27, i25);
                } else {
                    i7 = i13;
                    i6 = i14;
                    i5 = i12;
                    int i28 = blockWidth2;
                    int i29 = i10;
                    int i30 = i18;
                    T[] tArr4 = tArr;
                    int i31 = blockWidth;
                    copyBlockPart(this.blocks[i15], i28, i8, 36, i29, i30, tArr4, i31, 0, 0);
                    copyBlockPart(this.blocks[i15 + this.blockColumns], i28, 0, i17, i29, i30, tArr4, i31, blockHeight - i17, 0);
                }
                i13 = i7 + 1;
                i14 = i6 + 1;
                i12 = i5;
            }
            i11++;
            i12++;
        }
        return blockFieldMatrix;
    }

    private void copyBlockPart(T[] tArr, int i, int i2, int i3, int i4, int i5, T[] tArr2, int i6, int i7, int i8) {
        int i9 = i5 - i4;
        int i10 = (i2 * i) + i4;
        int i11 = (i7 * i6) + i8;
        while (i2 < i3) {
            System.arraycopy(tArr, i10, tArr2, i11, i9);
            i10 += i;
            i11 += i6;
            i2++;
        }
    }

    public void setSubMatrix(T[][] tArr, int i, int i2) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        BlockFieldMatrix blockFieldMatrix = this;
        T[][] tArr2 = tArr;
        int i3 = i;
        int i4 = i2;
        MathUtils.checkNotNull(tArr);
        int length = tArr2[0].length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        int length2 = (tArr2.length + i3) - 1;
        int i5 = (i4 + length) - 1;
        blockFieldMatrix.checkSubMatrixIndex(i3, length2, i4, i5);
        for (T[] tArr3 : tArr2) {
            if (tArr3.length != length) {
                throw new DimensionMismatchException(length, tArr3.length);
            }
        }
        int i6 = i3 / 36;
        int i7 = (length2 + 36) / 36;
        int i8 = i4 / 36;
        int i9 = (i5 + 36) / 36;
        while (i6 < i7) {
            int blockHeight = blockFieldMatrix.blockHeight(i6);
            int i10 = i6 * 36;
            int max = FastMath.max(i3, i10);
            int min = FastMath.min(length2 + 1, blockHeight + i10);
            int i11 = i8;
            while (i11 < i9) {
                int blockWidth = blockFieldMatrix.blockWidth(i11);
                int i12 = i11 * 36;
                int max2 = FastMath.max(i4, i12);
                int i13 = i7;
                int i14 = length2;
                int min2 = FastMath.min(i5 + 1, i12 + blockWidth) - max2;
                int i15 = i5;
                T[] tArr4 = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i6) + i11];
                int i16 = max;
                while (i16 < min) {
                    System.arraycopy(tArr2[i16 - i3], max2 - i4, tArr4, ((i16 - i10) * blockWidth) + (max2 - i12), min2);
                    i16++;
                    tArr2 = tArr;
                    i3 = i;
                }
                i11++;
                i7 = i13;
                length2 = i14;
                i5 = i15;
                blockFieldMatrix = this;
                tArr2 = tArr;
                i3 = i;
            }
            int i17 = i7;
            int i18 = length2;
            int i19 = i5;
            i6++;
            blockFieldMatrix = this;
            tArr2 = tArr;
            i3 = i;
        }
    }

    public FieldMatrix<T> getRowMatrix(int i) throws OutOfRangeException {
        checkRowIndex(i);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), 1, this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < this.blockColumns; i6++) {
            int blockWidth = blockWidth(i6);
            T[] tArr2 = this.blocks[(this.blockColumns * i2) + i6];
            int length = tArr.length - i4;
            if (blockWidth > length) {
                int i7 = i3 * blockWidth;
                System.arraycopy(tArr2, i7, tArr, i4, length);
                i5++;
                tArr = blockFieldMatrix.blocks[i5];
                int i8 = blockWidth - length;
                System.arraycopy(tArr2, i7, tArr, 0, i8);
                i4 = i8;
            } else {
                System.arraycopy(tArr2, i3 * blockWidth, tArr, i4, blockWidth);
                i4 += blockWidth;
            }
        }
        return blockFieldMatrix;
    }

    public void setRowMatrix(int i, FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRowMatrix(i, (BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            super.setRowMatrix(i, fieldMatrix);
        }
    }

    public void setRowMatrix(int i, BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (blockFieldMatrix.getRowDimension() == 1 && blockFieldMatrix.getColumnDimension() == columnDimension) {
            int i2 = i / 36;
            int i3 = i - (i2 * 36);
            T[] tArr = blockFieldMatrix.blocks[0];
            int i4 = 0;
            int i5 = 0;
            for (int i6 = 0; i6 < this.blockColumns; i6++) {
                int blockWidth = blockWidth(i6);
                T[] tArr2 = this.blocks[(this.blockColumns * i2) + i6];
                int length = tArr.length - i4;
                if (blockWidth > length) {
                    int i7 = i3 * blockWidth;
                    System.arraycopy(tArr, i4, tArr2, i7, length);
                    i5++;
                    tArr = blockFieldMatrix.blocks[i5];
                    int i8 = blockWidth - length;
                    System.arraycopy(tArr, 0, tArr2, i7, i8);
                    i4 = i8;
                } else {
                    System.arraycopy(tArr, i4, tArr2, i3 * blockWidth, blockWidth);
                    i4 += blockWidth;
                }
            }
            return;
        }
        throw new MatrixDimensionMismatchException(blockFieldMatrix.getRowDimension(), blockFieldMatrix.getColumnDimension(), 1, columnDimension);
    }

    public FieldMatrix<T> getColumnMatrix(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), this.rows, 1);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        T[] tArr = blockFieldMatrix.blocks[0];
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            T[] tArr2 = this.blocks[(this.blockColumns * i4) + i2];
            int i7 = i5;
            int i8 = 0;
            while (i8 < blockHeight) {
                if (i6 >= tArr.length) {
                    i7++;
                    tArr = blockFieldMatrix.blocks[i7];
                    i6 = 0;
                }
                int i9 = i6 + 1;
                tArr[i6] = tArr2[(i8 * blockWidth) + i3];
                i8++;
                i6 = i9;
            }
            i4++;
            i5 = i7;
        }
        return blockFieldMatrix;
    }

    public void setColumnMatrix(int i, FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setColumnMatrix(i, (BlockFieldMatrix) fieldMatrix);
        } catch (ClassCastException unused) {
            super.setColumnMatrix(i, fieldMatrix);
        }
    }

    /* access modifiers changed from: 0000 */
    public void setColumnMatrix(int i, BlockFieldMatrix<T> blockFieldMatrix) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (blockFieldMatrix.getRowDimension() == rowDimension && blockFieldMatrix.getColumnDimension() == 1) {
            int i2 = i / 36;
            int i3 = i - (i2 * 36);
            int blockWidth = blockWidth(i2);
            T[] tArr = blockFieldMatrix.blocks[0];
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            while (i4 < this.blockRows) {
                int blockHeight = blockHeight(i4);
                T[] tArr2 = this.blocks[(this.blockColumns * i4) + i2];
                int i7 = i5;
                int i8 = 0;
                while (i8 < blockHeight) {
                    if (i6 >= tArr.length) {
                        i7++;
                        tArr = blockFieldMatrix.blocks[i7];
                        i6 = 0;
                    }
                    int i9 = i6 + 1;
                    tArr2[(i8 * blockWidth) + i3] = tArr[i6];
                    i8++;
                    i6 = i9;
                }
                i4++;
                i5 = i7;
            }
            return;
        }
        throw new MatrixDimensionMismatchException(blockFieldMatrix.getRowDimension(), blockFieldMatrix.getColumnDimension(), rowDimension, 1);
    }

    public FieldVector<T> getRowVector(int i) throws OutOfRangeException {
        checkRowIndex(i);
        FieldElement[] buildArray = buildArray(getField(), this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, buildArray, i4, blockWidth);
            i4 += blockWidth;
        }
        return new ArrayFieldVector(getField(), (T[]) buildArray, false);
    }

    public void setRowVector(int i, FieldVector<T> fieldVector) throws MatrixDimensionMismatchException, OutOfRangeException {
        try {
            setRow(i, ((ArrayFieldVector) fieldVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setRowVector(i, fieldVector);
        }
    }

    public FieldVector<T> getColumnVector(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        FieldElement[] buildArray = buildArray(getField(), this.rows);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            T[] tArr = this.blocks[(this.blockColumns * i4) + i2];
            int i6 = i5;
            int i7 = 0;
            while (i7 < blockHeight) {
                int i8 = i6 + 1;
                buildArray[i6] = tArr[(i7 * blockWidth) + i3];
                i7++;
                i6 = i8;
            }
            i4++;
            i5 = i6;
        }
        return new ArrayFieldVector(getField(), (T[]) buildArray, false);
    }

    public void setColumnVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        try {
            setColumn(i, ((ArrayFieldVector) fieldVector).getDataRef());
        } catch (ClassCastException unused) {
            super.setColumnVector(i, fieldVector);
        }
    }

    public T[] getRow(int i) throws OutOfRangeException {
        checkRowIndex(i);
        T[] buildArray = buildArray(getField(), this.columns);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, buildArray, i4, blockWidth);
            i4 += blockWidth;
        }
        return buildArray;
    }

    public void setRow(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new MatrixDimensionMismatchException(1, tArr.length, 1, columnDimension);
        }
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int i4 = 0;
        for (int i5 = 0; i5 < this.blockColumns; i5++) {
            int blockWidth = blockWidth(i5);
            System.arraycopy(tArr, i4, this.blocks[(this.blockColumns * i2) + i5], i3 * blockWidth, blockWidth);
            i4 += blockWidth;
        }
    }

    public T[] getColumn(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        T[] buildArray = buildArray(getField(), this.rows);
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            T[] tArr = this.blocks[(this.blockColumns * i4) + i2];
            int i6 = i5;
            int i7 = 0;
            while (i7 < blockHeight) {
                int i8 = i6 + 1;
                buildArray[i6] = tArr[(i7 * blockWidth) + i3];
                i7++;
                i6 = i8;
            }
            i4++;
            i5 = i6;
        }
        return buildArray;
    }

    public void setColumn(int i, T[] tArr) throws MatrixDimensionMismatchException, OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (tArr.length != rowDimension) {
            throw new MatrixDimensionMismatchException(tArr.length, 1, rowDimension, 1);
        }
        int i2 = i / 36;
        int i3 = i - (i2 * 36);
        int blockWidth = blockWidth(i2);
        int i4 = 0;
        int i5 = 0;
        while (i4 < this.blockRows) {
            int blockHeight = blockHeight(i4);
            T[] tArr2 = this.blocks[(this.blockColumns * i4) + i2];
            int i6 = i5;
            int i7 = 0;
            while (i7 < blockHeight) {
                int i8 = i6 + 1;
                tArr2[(i7 * blockWidth) + i3] = tArr[i6];
                i7++;
                i6 = i8;
            }
            i4++;
            i5 = i6;
        }
    }

    public T getEntry(int i, int i2) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        return this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36))];
    }

    public void setEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        this.blocks[(i3 * this.blockColumns) + i4][((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36))] = t;
    }

    public void addToEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        int blockWidth = ((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36));
        T[] tArr = this.blocks[(i3 * this.blockColumns) + i4];
        tArr[blockWidth] = (FieldElement) tArr[blockWidth].add(t);
    }

    public void multiplyEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int i3 = i / 36;
        int i4 = i2 / 36;
        int blockWidth = ((i - (i3 * 36)) * blockWidth(i4)) + (i2 - (i4 * 36));
        T[] tArr = this.blocks[(i3 * this.blockColumns) + i4];
        tArr[blockWidth] = (FieldElement) tArr[blockWidth].multiply(t);
    }

    public FieldMatrix<T> transpose() {
        int rowDimension = getRowDimension();
        BlockFieldMatrix blockFieldMatrix = new BlockFieldMatrix(getField(), getColumnDimension(), rowDimension);
        int i = 0;
        int i2 = 0;
        while (i < this.blockColumns) {
            int i3 = i2;
            for (int i4 = 0; i4 < this.blockRows; i4++) {
                T[] tArr = blockFieldMatrix.blocks[i3];
                T[] tArr2 = this.blocks[(this.blockColumns * i4) + i];
                int i5 = i * 36;
                int min = FastMath.min(i5 + 36, this.columns);
                int i6 = i4 * 36;
                int min2 = FastMath.min(i6 + 36, this.rows);
                int i7 = 0;
                int i8 = i5;
                while (i8 < min) {
                    int i9 = min - i5;
                    int i10 = i8 - i5;
                    int i11 = i7;
                    for (int i12 = i6; i12 < min2; i12++) {
                        tArr[i11] = tArr2[i10];
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
        return blockFieldMatrix;
    }

    public int getRowDimension() {
        return this.rows;
    }

    public int getColumnDimension() {
        return this.columns;
    }

    public T[] operate(T[] tArr) throws DimensionMismatchException {
        BlockFieldMatrix blockFieldMatrix = this;
        T[] tArr2 = tArr;
        if (tArr2.length != blockFieldMatrix.columns) {
            throw new DimensionMismatchException(tArr2.length, blockFieldMatrix.columns);
        }
        T[] buildArray = buildArray(getField(), blockFieldMatrix.rows);
        FieldElement fieldElement = (FieldElement) getField().getZero();
        int i = 0;
        while (i < blockFieldMatrix.blockRows) {
            int i2 = i * 36;
            int min = FastMath.min(i2 + 36, blockFieldMatrix.rows);
            int i3 = 0;
            while (i3 < blockFieldMatrix.blockColumns) {
                T[] tArr3 = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i) + i3];
                int i4 = i3 * 36;
                int min2 = FastMath.min(i4 + 36, blockFieldMatrix.columns);
                int i5 = i2;
                int i6 = 0;
                while (i5 < min) {
                    FieldElement fieldElement2 = fieldElement;
                    int i7 = i6;
                    int i8 = i4;
                    while (i8 < min2 - 3) {
                        fieldElement2 = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement2.add(tArr3[i7].multiply(tArr2[i8]))).add(tArr3[i7 + 1].multiply(tArr2[i8 + 1]))).add(tArr3[i7 + 2].multiply(tArr2[i8 + 2]))).add(tArr3[i7 + 3].multiply(tArr2[i8 + 3]));
                        i7 += 4;
                        i8 += 4;
                    }
                    while (i8 < min2) {
                        int i9 = i7 + 1;
                        T t = tArr3[i7];
                        fieldElement2 = (FieldElement) fieldElement2.add(t.multiply(tArr2[i8]));
                        i8++;
                        i7 = i9;
                    }
                    buildArray[i5] = (FieldElement) buildArray[i5].add(fieldElement2);
                    i5++;
                    i6 = i7;
                }
                i3++;
                blockFieldMatrix = this;
            }
            i++;
            blockFieldMatrix = this;
        }
        return buildArray;
    }

    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int i;
        BlockFieldMatrix blockFieldMatrix = this;
        T[] tArr2 = tArr;
        if (tArr2.length != blockFieldMatrix.rows) {
            throw new DimensionMismatchException(tArr2.length, blockFieldMatrix.rows);
        }
        T[] buildArray = buildArray(getField(), blockFieldMatrix.columns);
        FieldElement fieldElement = (FieldElement) getField().getZero();
        int i2 = 0;
        while (i2 < blockFieldMatrix.blockColumns) {
            int blockWidth = blockFieldMatrix.blockWidth(i2);
            int i3 = blockWidth + blockWidth;
            int i4 = i3 + blockWidth;
            int i5 = i4 + blockWidth;
            int i6 = i2 * 36;
            int min = FastMath.min(i6 + 36, blockFieldMatrix.columns);
            int i7 = 0;
            while (i7 < blockFieldMatrix.blockRows) {
                T[] tArr3 = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i7) + i2];
                int i8 = i7 * 36;
                int min2 = FastMath.min(i8 + 36, blockFieldMatrix.rows);
                int i9 = i6;
                while (i9 < min) {
                    int i10 = i9 - i6;
                    FieldElement fieldElement2 = fieldElement;
                    int i11 = i8;
                    while (true) {
                        i = i6;
                        if (i11 >= min2 - 3) {
                            break;
                        }
                        fieldElement = (FieldElement) ((FieldElement) ((FieldElement) ((FieldElement) fieldElement.add(tArr3[i10].multiply(tArr2[i11]))).add(tArr3[i10 + blockWidth].multiply(tArr2[i11 + 1]))).add(tArr3[i10 + i3].multiply(tArr2[i11 + 2]))).add(tArr3[i10 + i4].multiply(tArr2[i11 + 3]));
                        i10 += i5;
                        i11 += 4;
                        i6 = i;
                        min = min;
                    }
                    int i12 = min;
                    while (i11 < min2) {
                        fieldElement = (FieldElement) fieldElement.add(tArr3[i10].multiply(tArr2[i11]));
                        i10 += blockWidth;
                        i11++;
                    }
                    buildArray[i9] = (FieldElement) buildArray[i9].add(fieldElement);
                    i9++;
                    fieldElement = fieldElement2;
                    i6 = i;
                    min = i12;
                }
                FieldElement fieldElement3 = fieldElement;
                int i13 = i6;
                int i14 = min;
                i7++;
                blockFieldMatrix = this;
            }
            FieldElement fieldElement4 = fieldElement;
            i2++;
            blockFieldMatrix = this;
        }
        return buildArray;
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        fieldMatrixChangingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int i = 0; i < this.blockRows; i++) {
            int i2 = i * 36;
            int min = FastMath.min(i2 + 36, this.rows);
            for (int i3 = i2; i3 < min; i3++) {
                for (int i4 = 0; i4 < this.blockColumns; i4++) {
                    int blockWidth = blockWidth(i4);
                    int i5 = i4 * 36;
                    int min2 = FastMath.min(i5 + 36, this.columns);
                    T[] tArr = this.blocks[(this.blockColumns * i) + i4];
                    int i6 = (i3 - i2) * blockWidth;
                    while (i5 < min2) {
                        tArr[i6] = fieldMatrixChangingVisitor.visit(i3, i5, tArr[i6]);
                        i6++;
                        i5++;
                    }
                }
            }
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        fieldMatrixPreservingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        for (int i = 0; i < this.blockRows; i++) {
            int i2 = i * 36;
            int min = FastMath.min(i2 + 36, this.rows);
            for (int i3 = i2; i3 < min; i3++) {
                for (int i4 = 0; i4 < this.blockColumns; i4++) {
                    int blockWidth = blockWidth(i4);
                    int i5 = i4 * 36;
                    int min2 = FastMath.min(i5 + 36, this.columns);
                    T[] tArr = this.blocks[(this.blockColumns * i) + i4];
                    int i6 = (i3 - i2) * blockWidth;
                    while (i5 < min2) {
                        fieldMatrixPreservingVisitor.visit(i3, i5, tArr[i6]);
                        i6++;
                        i5++;
                    }
                }
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        checkSubMatrixIndex(i5, i6, i7, i8);
        fieldMatrixChangingVisitor.start(this.rows, this.columns, i5, i6, i7, i8);
        int i9 = i5 / 36;
        while (true) {
            int i10 = 1;
            if (i9 < (i6 / 36) + 1) {
                int i11 = i9 * 36;
                int i12 = i9 + 1;
                int min = FastMath.min(i12 * 36, 1 + i6);
                for (int max = FastMath.max(i5, i11); max < min; max++) {
                    int i13 = i7 / 36;
                    while (i13 < (i8 / 36) + i10) {
                        int blockWidth = blockWidth(i13);
                        int i14 = i13 * 36;
                        int max2 = FastMath.max(i7, i14);
                        int i15 = i13 + 1;
                        int i16 = i12;
                        int i17 = min;
                        int min2 = FastMath.min(i15 * 36, i10 + i8);
                        T[] tArr = this.blocks[(this.blockColumns * i9) + i13];
                        int i18 = (((max - i11) * blockWidth) + max2) - i14;
                        while (max2 < min2) {
                            tArr[i18] = fieldMatrixChangingVisitor.visit(max, max2, tArr[i18]);
                            i18++;
                            max2++;
                        }
                        FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor2 = fieldMatrixChangingVisitor;
                        i13 = i15;
                        i12 = i16;
                        min = i17;
                        i10 = 1;
                    }
                    FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor3 = fieldMatrixChangingVisitor;
                    int i19 = i10;
                    int i20 = i12;
                    int i21 = min;
                }
                FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor4 = fieldMatrixChangingVisitor;
                i9 = i12;
            } else {
                FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor5 = fieldMatrixChangingVisitor;
                return fieldMatrixChangingVisitor.end();
            }
        }
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        checkSubMatrixIndex(i5, i6, i7, i8);
        fieldMatrixPreservingVisitor.start(this.rows, this.columns, i5, i6, i7, i8);
        int i9 = i5 / 36;
        while (true) {
            int i10 = 1;
            if (i9 < (i6 / 36) + 1) {
                int i11 = i9 * 36;
                int i12 = i9 + 1;
                int min = FastMath.min(i12 * 36, 1 + i6);
                for (int max = FastMath.max(i5, i11); max < min; max++) {
                    int i13 = i7 / 36;
                    while (i13 < (i8 / 36) + i10) {
                        int blockWidth = blockWidth(i13);
                        int i14 = i13 * 36;
                        int max2 = FastMath.max(i7, i14);
                        int i15 = i13 + 1;
                        int i16 = i12;
                        int i17 = min;
                        int min2 = FastMath.min(i15 * 36, i10 + i8);
                        T[] tArr = this.blocks[(this.blockColumns * i9) + i13];
                        int i18 = (((max - i11) * blockWidth) + max2) - i14;
                        while (max2 < min2) {
                            fieldMatrixPreservingVisitor.visit(max, max2, tArr[i18]);
                            i18++;
                            max2++;
                        }
                        FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor2 = fieldMatrixPreservingVisitor;
                        i13 = i15;
                        i12 = i16;
                        min = i17;
                        i10 = 1;
                    }
                    FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor3 = fieldMatrixPreservingVisitor;
                    int i19 = i10;
                    int i20 = i12;
                    int i21 = min;
                }
                FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor4 = fieldMatrixPreservingVisitor;
                i9 = i12;
            } else {
                FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor5 = fieldMatrixPreservingVisitor;
                return fieldMatrixPreservingVisitor.end();
            }
        }
    }

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        fieldMatrixChangingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int i = 0;
        int i2 = 0;
        while (i < this.blockRows) {
            int i3 = i * 36;
            int min = FastMath.min(i3 + 36, this.rows);
            int i4 = i2;
            for (int i5 = 0; i5 < this.blockColumns; i5++) {
                int i6 = i5 * 36;
                int min2 = FastMath.min(i6 + 36, this.columns);
                T[] tArr = this.blocks[i4];
                int i7 = 0;
                int i8 = i3;
                while (i8 < min) {
                    int i9 = i7;
                    for (int i10 = i6; i10 < min2; i10++) {
                        tArr[i9] = fieldMatrixChangingVisitor.visit(i8, i10, tArr[i9]);
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
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        fieldMatrixPreservingVisitor.start(this.rows, this.columns, 0, this.rows - 1, 0, this.columns - 1);
        int i = 0;
        int i2 = 0;
        while (i < this.blockRows) {
            int i3 = i * 36;
            int min = FastMath.min(i3 + 36, this.rows);
            int i4 = i2;
            for (int i5 = 0; i5 < this.blockColumns; i5++) {
                int i6 = i5 * 36;
                int min2 = FastMath.min(i6 + 36, this.columns);
                T[] tArr = this.blocks[i4];
                int i7 = 0;
                int i8 = i3;
                while (i8 < min) {
                    int i9 = i7;
                    for (int i10 = i6; i10 < min2; i10++) {
                        fieldMatrixPreservingVisitor.visit(i8, i10, tArr[i9]);
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
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix blockFieldMatrix = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        blockFieldMatrix.checkSubMatrixIndex(i5, i6, i7, i8);
        fieldMatrixChangingVisitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, i5, i6, i7, i8);
        int i9 = i5 / 36;
        while (true) {
            if (i9 < (i6 / 36) + 1) {
                int i10 = i9 * 36;
                int max = FastMath.max(i5, i10);
                int i11 = i9 + 1;
                int min = FastMath.min(i11 * 36, 1 + i6);
                int i12 = i7 / 36;
                for (int i13 = 1; i12 < (i8 / 36) + i13; i13 = 1) {
                    int blockWidth = blockFieldMatrix.blockWidth(i12);
                    int i14 = i12 * 36;
                    int max2 = FastMath.max(i7, i14);
                    int i15 = i12 + 1;
                    int i16 = max;
                    int i17 = i11;
                    int min2 = FastMath.min(i15 * 36, i13 + i8);
                    T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i9) + i12];
                    int i18 = i16;
                    while (i18 < min) {
                        int i19 = (((i18 - i10) * blockWidth) + max2) - i14;
                        int i20 = max2;
                        while (i20 < min2) {
                            int i21 = i9;
                            tArr[i19] = fieldMatrixChangingVisitor.visit(i18, i20, tArr[i19]);
                            i19++;
                            i20++;
                            i9 = i21;
                        }
                        int i22 = i9;
                        FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor2 = fieldMatrixChangingVisitor;
                        i18++;
                        i9 = i22;
                    }
                    int i23 = i9;
                    FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor3 = fieldMatrixChangingVisitor;
                    i12 = i15;
                    max = i16;
                    i11 = i17;
                    i9 = i23;
                    blockFieldMatrix = this;
                }
                FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor4 = fieldMatrixChangingVisitor;
                i9 = i11;
            } else {
                FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor5 = fieldMatrixChangingVisitor;
                return fieldMatrixChangingVisitor.end();
            }
        }
    }

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        BlockFieldMatrix blockFieldMatrix = this;
        int i5 = i;
        int i6 = i2;
        int i7 = i3;
        int i8 = i4;
        blockFieldMatrix.checkSubMatrixIndex(i5, i6, i7, i8);
        fieldMatrixPreservingVisitor.start(blockFieldMatrix.rows, blockFieldMatrix.columns, i5, i6, i7, i8);
        int i9 = i5 / 36;
        while (true) {
            if (i9 < (i6 / 36) + 1) {
                int i10 = i9 * 36;
                int max = FastMath.max(i5, i10);
                int i11 = i9 + 1;
                int min = FastMath.min(i11 * 36, 1 + i6);
                int i12 = i7 / 36;
                for (int i13 = 1; i12 < (i8 / 36) + i13; i13 = 1) {
                    int blockWidth = blockFieldMatrix.blockWidth(i12);
                    int i14 = i12 * 36;
                    int max2 = FastMath.max(i7, i14);
                    int i15 = i12 + 1;
                    int i16 = max;
                    int i17 = i11;
                    int min2 = FastMath.min(i15 * 36, i13 + i8);
                    T[] tArr = blockFieldMatrix.blocks[(blockFieldMatrix.blockColumns * i9) + i12];
                    int i18 = i16;
                    while (i18 < min) {
                        int i19 = (((i18 - i10) * blockWidth) + max2) - i14;
                        int i20 = max2;
                        while (i20 < min2) {
                            int i21 = i9;
                            fieldMatrixPreservingVisitor.visit(i18, i20, tArr[i19]);
                            i19++;
                            i20++;
                            i9 = i21;
                        }
                        int i22 = i9;
                        FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor2 = fieldMatrixPreservingVisitor;
                        i18++;
                        i9 = i22;
                    }
                    int i23 = i9;
                    FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor3 = fieldMatrixPreservingVisitor;
                    i12 = i15;
                    max = i16;
                    i11 = i17;
                    i9 = i23;
                    blockFieldMatrix = this;
                }
                FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor4 = fieldMatrixPreservingVisitor;
                i9 = i11;
            } else {
                FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor5 = fieldMatrixPreservingVisitor;
                return fieldMatrixPreservingVisitor.end();
            }
        }
    }

    private int blockHeight(int i) {
        if (i == this.blockRows - 1) {
            return this.rows - (i * 36);
        }
        return 36;
    }

    private int blockWidth(int i) {
        if (i == this.blockColumns - 1) {
            return this.columns - (i * 36);
        }
        return 36;
    }
}
