package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathIllegalStateException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.MathUtils;

public class Array2DRowFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> implements Serializable {
    private static final long serialVersionUID = 7260756672015356458L;
    private T[][] data;

    public Array2DRowFieldMatrix(Field<T> field) {
        super(field);
    }

    public Array2DRowFieldMatrix(Field<T> field, int i, int i2) throws NotStrictlyPositiveException {
        super(field, i, i2);
        this.data = buildArray(field, i, i2);
    }

    public Array2DRowFieldMatrix(T[][] tArr) throws DimensionMismatchException, NullArgumentException, NoDataException {
        this(extractField(tArr), tArr);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[][] tArr) throws DimensionMismatchException, NullArgumentException, NoDataException {
        super(field);
        copyIn(tArr);
    }

    public Array2DRowFieldMatrix(T[][] tArr, boolean z) throws DimensionMismatchException, NoDataException, NullArgumentException {
        this(extractField(tArr), tArr, z);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[][] tArr, boolean z) throws DimensionMismatchException, NoDataException, NullArgumentException {
        super(field);
        if (z) {
            copyIn(tArr);
            return;
        }
        MathUtils.checkNotNull(tArr);
        int length = tArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int length2 = tArr[0].length;
        if (length2 == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int i = 1; i < length; i++) {
            if (tArr[i].length != length2) {
                throw new DimensionMismatchException(length2, tArr[i].length);
            }
        }
        this.data = tArr;
    }

    public Array2DRowFieldMatrix(T[] tArr) throws NoDataException {
        this(extractField(tArr), tArr);
    }

    public Array2DRowFieldMatrix(Field<T> field, T[] tArr) {
        super(field);
        int length = tArr.length;
        this.data = buildArray(getField(), length, 1);
        for (int i = 0; i < length; i++) {
            this.data[i][0] = tArr[i];
        }
    }

    public FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException {
        return new Array2DRowFieldMatrix(getField(), i, i2);
    }

    public FieldMatrix<T> copy() {
        return new Array2DRowFieldMatrix(getField(), (T[][]) copyOut(), false);
    }

    public Array2DRowFieldMatrix<T> add(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(array2DRowFieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldElement[][] buildArray = buildArray(getField(), rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            T[] tArr2 = array2DRowFieldMatrix.data[i];
            FieldElement[] fieldElementArr = buildArray[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldElementArr[i2] = (FieldElement) tArr[i2].add(tArr2[i2]);
            }
        }
        return new Array2DRowFieldMatrix<>(getField(), (T[][]) buildArray, false);
    }

    public Array2DRowFieldMatrix<T> subtract(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(array2DRowFieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldElement[][] buildArray = buildArray(getField(), rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            T[] tArr2 = array2DRowFieldMatrix.data[i];
            FieldElement[] fieldElementArr = buildArray[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldElementArr[i2] = (FieldElement) tArr[i2].subtract(tArr2[i2]);
            }
        }
        return new Array2DRowFieldMatrix<>(getField(), (T[][]) buildArray, false);
    }

    public Array2DRowFieldMatrix<T> multiply(Array2DRowFieldMatrix<T> array2DRowFieldMatrix) throws DimensionMismatchException {
        checkMultiplicationCompatible(array2DRowFieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = array2DRowFieldMatrix.getColumnDimension();
        int columnDimension2 = getColumnDimension();
        FieldElement[][] buildArray = buildArray(getField(), rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            FieldElement[] fieldElementArr = buildArray[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                FieldElement fieldElement = (FieldElement) getField().getZero();
                for (int i3 = 0; i3 < columnDimension2; i3++) {
                    fieldElement = (FieldElement) fieldElement.add(tArr[i3].multiply(array2DRowFieldMatrix.data[i3][i2]));
                }
                fieldElementArr[i2] = fieldElement;
            }
        }
        return new Array2DRowFieldMatrix<>(getField(), (T[][]) buildArray, false);
    }

    public T[][] getData() {
        return copyOut();
    }

    public T[][] getDataRef() {
        return this.data;
    }

    public void setSubMatrix(T[][] tArr, int i, int i2) throws OutOfRangeException, NullArgumentException, NoDataException, DimensionMismatchException {
        if (this.data != null) {
            super.setSubMatrix(tArr, i, i2);
        } else if (i > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_ROWS_NOT_INITIALIZED_YET, Integer.valueOf(i));
        } else if (i2 > 0) {
            throw new MathIllegalStateException(LocalizedFormats.FIRST_COLUMNS_NOT_INITIALIZED_YET, Integer.valueOf(i2));
        } else if (tArr.length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        } else {
            int length = tArr[0].length;
            if (length == 0) {
                throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
            }
            this.data = buildArray(getField(), tArr.length, length);
            for (int i3 = 0; i3 < this.data.length; i3++) {
                if (tArr[i3].length != length) {
                    throw new DimensionMismatchException(length, tArr[i3].length);
                }
                System.arraycopy(tArr[i3], 0, this.data[i3 + i], i2, length);
            }
        }
    }

    public T getEntry(int i, int i2) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        return this.data[i][i2];
    }

    public void setEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        this.data[i][i2] = t;
    }

    public void addToEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        this.data[i][i2] = (FieldElement) this.data[i][i2].add(t);
    }

    public void multiplyEntry(int i, int i2, T t) throws OutOfRangeException {
        checkRowIndex(i);
        checkColumnIndex(i2);
        this.data[i][i2] = (FieldElement) this.data[i][i2].multiply(t);
    }

    public int getRowDimension() {
        if (this.data == null) {
            return 0;
        }
        return this.data.length;
    }

    public int getColumnDimension() {
        if (this.data == null || this.data[0] == null) {
            return 0;
        }
        return this.data[0].length;
    }

    public T[] operate(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new DimensionMismatchException(tArr.length, columnDimension);
        }
        T[] buildArray = buildArray(getField(), rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr2 = this.data[i];
            T t = (FieldElement) getField().getZero();
            for (int i2 = 0; i2 < columnDimension; i2++) {
                t = (FieldElement) t.add(tArr2[i2].multiply(tArr[i2]));
            }
            buildArray[i] = t;
        }
        return buildArray;
    }

    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != rowDimension) {
            throw new DimensionMismatchException(tArr.length, rowDimension);
        }
        T[] buildArray = buildArray(getField(), columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            T t = (FieldElement) getField().getZero();
            for (int i2 = 0; i2 < rowDimension; i2++) {
                t = (FieldElement) t.add(this.data[i2][i].multiply(tArr[i2]));
            }
            buildArray[i] = t;
        }
        return buildArray;
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                tArr[i2] = fieldMatrixChangingVisitor.visit(i, i2, tArr[i2]);
            }
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            T[] tArr = this.data[i];
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i, i2, tArr[i2]);
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            T[] tArr = this.data[i];
            for (int i5 = i3; i5 <= i4; i5++) {
                tArr[i5] = fieldMatrixChangingVisitor.visit(i, i5, tArr[i5]);
            }
            i++;
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            T[] tArr = this.data[i];
            for (int i5 = i3; i5 <= i4; i5++) {
                fieldMatrixPreservingVisitor.visit(i, i5, tArr[i5]);
            }
            i++;
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                T[] tArr = this.data[i2];
                tArr[i] = fieldMatrixChangingVisitor.visit(i2, i, tArr[i]);
            }
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < columnDimension; i++) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i2, i, this.data[i2][i]);
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                T[] tArr = this.data[i5];
                tArr[i3] = fieldMatrixChangingVisitor.visit(i5, i3, tArr[i3]);
            }
            i3++;
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws OutOfRangeException, NumberIsTooSmallException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                fieldMatrixPreservingVisitor.visit(i5, i3, this.data[i5][i3]);
            }
            i3++;
        }
        return fieldMatrixPreservingVisitor.end();
    }

    private T[][] copyOut() {
        int rowDimension = getRowDimension();
        T[][] buildArray = buildArray(getField(), rowDimension, getColumnDimension());
        for (int i = 0; i < rowDimension; i++) {
            System.arraycopy(this.data[i], 0, buildArray[i], 0, this.data[i].length);
        }
        return buildArray;
    }

    private void copyIn(T[][] tArr) throws NullArgumentException, NoDataException, DimensionMismatchException {
        setSubMatrix(tArr, 0, 0);
    }
}
