package org.apache.commons.math3.linear;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NoDataException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.geometry.VectorFormat;

public abstract class AbstractFieldMatrix<T extends FieldElement<T>> implements FieldMatrix<T> {
    private final Field<T> field;

    public abstract void addToEntry(int i, int i2, T t) throws OutOfRangeException;

    public abstract FieldMatrix<T> copy();

    public abstract FieldMatrix<T> createMatrix(int i, int i2) throws NotStrictlyPositiveException;

    public abstract int getColumnDimension();

    public abstract T getEntry(int i, int i2) throws OutOfRangeException;

    public abstract int getRowDimension();

    public abstract void multiplyEntry(int i, int i2, T t) throws OutOfRangeException;

    public abstract void setEntry(int i, int i2, T t) throws OutOfRangeException;

    protected AbstractFieldMatrix() {
        this.field = null;
    }

    protected AbstractFieldMatrix(Field<T> field2) {
        this.field = field2;
    }

    protected AbstractFieldMatrix(Field<T> field2, int i, int i2) throws NotStrictlyPositiveException {
        if (i <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(i));
        } else if (i2 <= 0) {
            throw new NotStrictlyPositiveException(LocalizedFormats.DIMENSION, Integer.valueOf(i2));
        } else {
            this.field = field2;
        }
    }

    protected static <T extends FieldElement<T>> Field<T> extractField(T[][] tArr) throws NoDataException, NullArgumentException {
        if (tArr == null) {
            throw new NullArgumentException();
        } else if (tArr.length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        } else if (tArr[0].length != 0) {
            return tArr[0][0].getField();
        } else {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
    }

    protected static <T extends FieldElement<T>> Field<T> extractField(T[] tArr) throws NoDataException {
        if (tArr.length != 0) {
            return tArr[0].getField();
        }
        throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
    }

    protected static <T extends FieldElement<T>> T[][] buildArray(Field<T> field2, int i, int i2) {
        if (i2 < 0) {
            return (FieldElement[][]) Array.newInstance(((FieldElement[]) Array.newInstance(field2.getRuntimeClass(), 0)).getClass(), i);
        }
        T[][] tArr = (FieldElement[][]) Array.newInstance(field2.getRuntimeClass(), new int[]{i, i2});
        for (T[] fill : tArr) {
            Arrays.fill(fill, field2.getZero());
        }
        return tArr;
    }

    protected static <T extends FieldElement<T>> T[] buildArray(Field<T> field2, int i) {
        T[] tArr = (FieldElement[]) Array.newInstance(field2.getRuntimeClass(), i);
        Arrays.fill(tArr, field2.getZero());
        return tArr;
    }

    public Field<T> getField() {
        return this.field;
    }

    public FieldMatrix<T> add(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        checkAdditionCompatible(fieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).add(fieldMatrix.getEntry(i, i2)));
            }
        }
        return createMatrix;
    }

    public FieldMatrix<T> subtract(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        checkSubtractionCompatible(fieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).subtract(fieldMatrix.getEntry(i, i2)));
            }
        }
        return createMatrix;
    }

    public FieldMatrix<T> scalarAdd(T t) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).add(t));
            }
        }
        return createMatrix;
    }

    public FieldMatrix<T> scalarMultiply(T t) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        FieldMatrix<T> createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                createMatrix.setEntry(i, i2, (FieldElement) getEntry(i, i2).multiply(t));
            }
        }
        return createMatrix;
    }

    public FieldMatrix<T> multiply(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        checkMultiplicationCompatible(fieldMatrix);
        int rowDimension = getRowDimension();
        int columnDimension = fieldMatrix.getColumnDimension();
        int columnDimension2 = getColumnDimension();
        FieldMatrix<T> createMatrix = createMatrix(rowDimension, columnDimension);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                FieldElement fieldElement = (FieldElement) this.field.getZero();
                for (int i3 = 0; i3 < columnDimension2; i3++) {
                    fieldElement = (FieldElement) fieldElement.add(getEntry(i, i3).multiply(fieldMatrix.getEntry(i3, i2)));
                }
                createMatrix.setEntry(i, i2, fieldElement);
            }
        }
        return createMatrix;
    }

    public FieldMatrix<T> preMultiply(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        return fieldMatrix.multiply(this);
    }

    public FieldMatrix<T> power(int i) throws NonSquareMatrixException, NotPositiveException {
        if (i < 0) {
            throw new NotPositiveException(Integer.valueOf(i));
        } else if (!isSquare()) {
            throw new NonSquareMatrixException(getRowDimension(), getColumnDimension());
        } else if (i == 0) {
            return MatrixUtils.createFieldIdentityMatrix(getField(), getRowDimension());
        } else {
            if (i == 1) {
                return copy();
            }
            char[] charArray = Integer.toBinaryString(i - 1).toCharArray();
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < charArray.length; i2++) {
                if (charArray[i2] == '1') {
                    arrayList.add(Integer.valueOf((charArray.length - i2) - 1));
                }
            }
            ArrayList arrayList2 = new ArrayList(charArray.length);
            arrayList2.add(0, copy());
            for (int i3 = 1; i3 < charArray.length; i3++) {
                FieldMatrix fieldMatrix = (FieldMatrix) arrayList2.get(i3 - 1);
                arrayList2.add(i3, fieldMatrix.multiply(fieldMatrix));
            }
            FieldMatrix<T> copy = copy();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                copy = copy.multiply((FieldMatrix) arrayList2.get(((Integer) it.next()).intValue()));
            }
            return copy;
        }
    }

    public T[][] getData() {
        T[][] buildArray = buildArray(this.field, getRowDimension(), getColumnDimension());
        for (int i = 0; i < buildArray.length; i++) {
            T[] tArr = buildArray[i];
            for (int i2 = 0; i2 < tArr.length; i2++) {
                tArr[i2] = getEntry(i, i2);
            }
        }
        return buildArray;
    }

    public FieldMatrix<T> getSubMatrix(int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        FieldMatrix<T> createMatrix = createMatrix((i2 - i) + 1, (i4 - i3) + 1);
        for (int i5 = i; i5 <= i2; i5++) {
            for (int i6 = i3; i6 <= i4; i6++) {
                createMatrix.setEntry(i5 - i, i6 - i3, getEntry(i5, i6));
            }
        }
        return createMatrix;
    }

    public FieldMatrix<T> getSubMatrix(final int[] iArr, final int[] iArr2) throws NoDataException, NullArgumentException, OutOfRangeException {
        checkSubMatrixIndex(iArr, iArr2);
        FieldMatrix<T> createMatrix = createMatrix(iArr.length, iArr2.length);
        createMatrix.walkInOptimizedOrder((FieldMatrixChangingVisitor<T>) new DefaultFieldMatrixChangingVisitor<T>((FieldElement) this.field.getZero()) {
            public T visit(int i, int i2, T t) {
                return AbstractFieldMatrix.this.getEntry(iArr[i], iArr2[i2]);
            }
        });
        return createMatrix;
    }

    public void copySubMatrix(int i, int i2, int i3, int i4, final T[][] tArr) throws MatrixDimensionMismatchException, NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        int i5 = (i2 + 1) - i;
        int i6 = (i4 + 1) - i3;
        if (tArr.length < i5 || tArr[0].length < i6) {
            throw new MatrixDimensionMismatchException(tArr.length, tArr[0].length, i5, i6);
        }
        walkInOptimizedOrder((FieldMatrixPreservingVisitor<T>) new DefaultFieldMatrixPreservingVisitor<T>((FieldElement) this.field.getZero()) {
            private int startColumn;
            private int startRow;

            public void start(int i, int i2, int i3, int i4, int i5, int i6) {
                this.startRow = i3;
                this.startColumn = i5;
            }

            public void visit(int i, int i2, T t) {
                tArr[i - this.startRow][i2 - this.startColumn] = t;
            }
        }, i, i2, i3, i4);
    }

    public void copySubMatrix(int[] iArr, int[] iArr2, T[][] tArr) throws MatrixDimensionMismatchException, NoDataException, NullArgumentException, OutOfRangeException {
        checkSubMatrixIndex(iArr, iArr2);
        if (tArr.length < iArr.length || tArr[0].length < iArr2.length) {
            throw new MatrixDimensionMismatchException(tArr.length, tArr[0].length, iArr.length, iArr2.length);
        }
        for (int i = 0; i < iArr.length; i++) {
            T[] tArr2 = tArr[i];
            for (int i2 = 0; i2 < iArr2.length; i2++) {
                tArr2[i2] = getEntry(iArr[i], iArr2[i2]);
            }
        }
    }

    public void setSubMatrix(T[][] tArr, int i, int i2) throws DimensionMismatchException, OutOfRangeException, NoDataException, NullArgumentException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        int length = tArr.length;
        if (length == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_ROW);
        }
        int length2 = tArr[0].length;
        if (length2 == 0) {
            throw new NoDataException(LocalizedFormats.AT_LEAST_ONE_COLUMN);
        }
        for (int i3 = 1; i3 < length; i3++) {
            if (tArr[i3].length != length2) {
                throw new DimensionMismatchException(length2, tArr[i3].length);
            }
        }
        checkRowIndex(i);
        checkColumnIndex(i2);
        checkRowIndex((length + i) - 1);
        checkColumnIndex((length2 + i2) - 1);
        for (int i4 = 0; i4 < length; i4++) {
            for (int i5 = 0; i5 < length2; i5++) {
                setEntry(i + i4, i2 + i5, tArr[i4][i5]);
            }
        }
    }

    public FieldMatrix<T> getRowMatrix(int i) throws OutOfRangeException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        FieldMatrix<T> createMatrix = createMatrix(1, columnDimension);
        for (int i2 = 0; i2 < columnDimension; i2++) {
            createMatrix.setEntry(0, i2, getEntry(i, i2));
        }
        return createMatrix;
    }

    public void setRowMatrix(int i, FieldMatrix<T> fieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (fieldMatrix.getRowDimension() == 1 && fieldMatrix.getColumnDimension() == columnDimension) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                setEntry(i, i2, fieldMatrix.getEntry(0, i2));
            }
            return;
        }
        throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), 1, columnDimension);
    }

    public FieldMatrix<T> getColumnMatrix(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        FieldMatrix<T> createMatrix = createMatrix(rowDimension, 1);
        for (int i2 = 0; i2 < rowDimension; i2++) {
            createMatrix.setEntry(i2, 0, getEntry(i2, i));
        }
        return createMatrix;
    }

    public void setColumnMatrix(int i, FieldMatrix<T> fieldMatrix) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (fieldMatrix.getRowDimension() == rowDimension && fieldMatrix.getColumnDimension() == 1) {
            for (int i2 = 0; i2 < rowDimension; i2++) {
                setEntry(i2, i, fieldMatrix.getEntry(i2, 0));
            }
            return;
        }
        throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), rowDimension, 1);
    }

    public FieldVector<T> getRowVector(int i) throws OutOfRangeException {
        return new ArrayFieldVector(this.field, (T[]) getRow(i), false);
    }

    public void setRowVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (fieldVector.getDimension() != columnDimension) {
            throw new MatrixDimensionMismatchException(1, fieldVector.getDimension(), 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, fieldVector.getEntry(i2));
        }
    }

    public FieldVector<T> getColumnVector(int i) throws OutOfRangeException {
        return new ArrayFieldVector(this.field, (T[]) getColumn(i), false);
    }

    public void setColumnVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (fieldVector.getDimension() != rowDimension) {
            throw new MatrixDimensionMismatchException(fieldVector.getDimension(), 1, rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, fieldVector.getEntry(i2));
        }
    }

    public T[] getRow(int i) throws OutOfRangeException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        T[] buildArray = buildArray(this.field, columnDimension);
        for (int i2 = 0; i2 < columnDimension; i2++) {
            buildArray[i2] = getEntry(i, i2);
        }
        return buildArray;
    }

    public void setRow(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkRowIndex(i);
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new MatrixDimensionMismatchException(1, tArr.length, 1, columnDimension);
        }
        for (int i2 = 0; i2 < columnDimension; i2++) {
            setEntry(i, i2, tArr[i2]);
        }
    }

    public T[] getColumn(int i) throws OutOfRangeException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        T[] buildArray = buildArray(this.field, rowDimension);
        for (int i2 = 0; i2 < rowDimension; i2++) {
            buildArray[i2] = getEntry(i2, i);
        }
        return buildArray;
    }

    public void setColumn(int i, T[] tArr) throws OutOfRangeException, MatrixDimensionMismatchException {
        checkColumnIndex(i);
        int rowDimension = getRowDimension();
        if (tArr.length != rowDimension) {
            throw new MatrixDimensionMismatchException(tArr.length, 1, rowDimension, 1);
        }
        for (int i2 = 0; i2 < rowDimension; i2++) {
            setEntry(i2, i, tArr[i2]);
        }
    }

    public FieldMatrix<T> transpose() {
        final FieldMatrix<T> createMatrix = createMatrix(getColumnDimension(), getRowDimension());
        walkInOptimizedOrder((FieldMatrixPreservingVisitor<T>) new DefaultFieldMatrixPreservingVisitor<T>((FieldElement) this.field.getZero()) {
            public void visit(int i, int i2, T t) {
                createMatrix.setEntry(i2, i, t);
            }
        });
        return createMatrix;
    }

    public boolean isSquare() {
        return getColumnDimension() == getRowDimension();
    }

    public T getTrace() throws NonSquareMatrixException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (rowDimension != columnDimension) {
            throw new NonSquareMatrixException(rowDimension, columnDimension);
        }
        T t = (FieldElement) this.field.getZero();
        for (int i = 0; i < rowDimension; i++) {
            t = (FieldElement) t.add(getEntry(i, i));
        }
        return t;
    }

    public T[] operate(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != columnDimension) {
            throw new DimensionMismatchException(tArr.length, columnDimension);
        }
        T[] buildArray = buildArray(this.field, rowDimension);
        for (int i = 0; i < rowDimension; i++) {
            T t = (FieldElement) this.field.getZero();
            for (int i2 = 0; i2 < columnDimension; i2++) {
                t = (FieldElement) t.add(getEntry(i, i2).multiply(tArr[i2]));
            }
            buildArray[i] = t;
        }
        return buildArray;
    }

    public FieldVector<T> operate(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return new ArrayFieldVector(this.field, (T[]) operate((T[]) ((ArrayFieldVector) fieldVector).getDataRef()), false);
        } catch (ClassCastException unused) {
            int rowDimension = getRowDimension();
            int columnDimension = getColumnDimension();
            if (fieldVector.getDimension() != columnDimension) {
                throw new DimensionMismatchException(fieldVector.getDimension(), columnDimension);
            }
            FieldElement[] buildArray = buildArray(this.field, rowDimension);
            for (int i = 0; i < rowDimension; i++) {
                FieldElement fieldElement = (FieldElement) this.field.getZero();
                for (int i2 = 0; i2 < columnDimension; i2++) {
                    fieldElement = (FieldElement) fieldElement.add(getEntry(i, i2).multiply(fieldVector.getEntry(i2)));
                }
                buildArray[i] = fieldElement;
            }
            return new ArrayFieldVector(this.field, (T[]) buildArray, false);
        }
    }

    public T[] preMultiply(T[] tArr) throws DimensionMismatchException {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (tArr.length != rowDimension) {
            throw new DimensionMismatchException(tArr.length, rowDimension);
        }
        T[] buildArray = buildArray(this.field, columnDimension);
        for (int i = 0; i < columnDimension; i++) {
            T t = (FieldElement) this.field.getZero();
            for (int i2 = 0; i2 < rowDimension; i2++) {
                t = (FieldElement) t.add(getEntry(i2, i).multiply(tArr[i2]));
            }
            buildArray[i] = t;
        }
        return buildArray;
    }

    public FieldVector<T> preMultiply(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return new ArrayFieldVector(this.field, (T[]) preMultiply((T[]) ((ArrayFieldVector) fieldVector).getDataRef()), false);
        } catch (ClassCastException unused) {
            int rowDimension = getRowDimension();
            int columnDimension = getColumnDimension();
            if (fieldVector.getDimension() != rowDimension) {
                throw new DimensionMismatchException(fieldVector.getDimension(), rowDimension);
            }
            FieldElement[] buildArray = buildArray(this.field, columnDimension);
            for (int i = 0; i < columnDimension; i++) {
                FieldElement fieldElement = (FieldElement) this.field.getZero();
                for (int i2 = 0; i2 < rowDimension; i2++) {
                    fieldElement = (FieldElement) fieldElement.add(getEntry(i2, i).multiply(fieldVector.getEntry(i2)));
                }
                buildArray[i] = fieldElement;
            }
            return new ArrayFieldVector(this.field, (T[]) buildArray, false);
        }
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixChangingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                setEntry(i, i2, fieldMatrixChangingVisitor.visit(i, i2, getEntry(i, i2)));
            }
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        fieldMatrixPreservingVisitor.start(rowDimension, columnDimension, 0, rowDimension - 1, 0, columnDimension - 1);
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                fieldMatrixPreservingVisitor.visit(i, i2, getEntry(i, i2));
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            for (int i5 = i3; i5 <= i4; i5++) {
                setEntry(i, i5, fieldMatrixChangingVisitor.visit(i, i5, getEntry(i, i5)));
            }
            i++;
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInRowOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i <= i2) {
            for (int i5 = i3; i5 <= i4; i5++) {
                fieldMatrixPreservingVisitor.visit(i, i5, getEntry(i, i5));
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
                setEntry(i2, i, fieldMatrixChangingVisitor.visit(i2, i, getEntry(i2, i)));
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
                fieldMatrixPreservingVisitor.visit(i2, i, getEntry(i2, i));
            }
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInColumnOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixChangingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                setEntry(i5, i3, fieldMatrixChangingVisitor.visit(i5, i3, getEntry(i5, i3)));
            }
            i3++;
        }
        return fieldMatrixChangingVisitor.end();
    }

    public T walkInColumnOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkSubMatrixIndex(i, i2, i3, i4);
        fieldMatrixPreservingVisitor.start(getRowDimension(), getColumnDimension(), i, i2, i3, i4);
        while (i3 <= i4) {
            for (int i5 = i; i5 <= i2; i5++) {
                fieldMatrixPreservingVisitor.visit(i5, i3, getEntry(i5, i3));
            }
            i3++;
        }
        return fieldMatrixPreservingVisitor.end();
    }

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor) {
        return walkInRowOrder(fieldMatrixChangingVisitor);
    }

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor) {
        return walkInRowOrder(fieldMatrixPreservingVisitor);
    }

    public T walkInOptimizedOrder(FieldMatrixChangingVisitor<T> fieldMatrixChangingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInRowOrder(fieldMatrixChangingVisitor, i, i2, i3, i4);
    }

    public T walkInOptimizedOrder(FieldMatrixPreservingVisitor<T> fieldMatrixPreservingVisitor, int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInRowOrder(fieldMatrixPreservingVisitor, i, i2, i3, i4);
    }

    public String toString() {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        StringBuffer stringBuffer = new StringBuffer();
        String name = getClass().getName();
        stringBuffer.append(name.substring(name.lastIndexOf(46) + 1));
        stringBuffer.append(VectorFormat.DEFAULT_PREFIX);
        for (int i = 0; i < rowDimension; i++) {
            if (i > 0) {
                stringBuffer.append(",");
            }
            stringBuffer.append(VectorFormat.DEFAULT_PREFIX);
            for (int i2 = 0; i2 < columnDimension; i2++) {
                if (i2 > 0) {
                    stringBuffer.append(",");
                }
                stringBuffer.append(getEntry(i, i2));
            }
            stringBuffer.append(VectorFormat.DEFAULT_SUFFIX);
        }
        stringBuffer.append(VectorFormat.DEFAULT_SUFFIX);
        return stringBuffer.toString();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof FieldMatrix)) {
            return false;
        }
        FieldMatrix fieldMatrix = (FieldMatrix) obj;
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        if (fieldMatrix.getColumnDimension() != columnDimension || fieldMatrix.getRowDimension() != rowDimension) {
            return false;
        }
        for (int i = 0; i < rowDimension; i++) {
            for (int i2 = 0; i2 < columnDimension; i2++) {
                if (!getEntry(i, i2).equals(fieldMatrix.getEntry(i, i2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public int hashCode() {
        int rowDimension = getRowDimension();
        int columnDimension = getColumnDimension();
        int i = ((9999422 + rowDimension) * 31) + columnDimension;
        int i2 = 0;
        while (i2 < rowDimension) {
            int i3 = i;
            int i4 = 0;
            while (i4 < columnDimension) {
                int i5 = i4 + 1;
                i3 = (i3 * 31) + (((11 * (i2 + 1)) + (17 * i5)) * getEntry(i2, i4).hashCode());
                i4 = i5;
            }
            i2++;
            i = i3;
        }
        return i;
    }

    /* access modifiers changed from: protected */
    public void checkRowIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getRowDimension()) {
            throw new OutOfRangeException(LocalizedFormats.ROW_INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(getRowDimension() - 1));
        }
    }

    /* access modifiers changed from: protected */
    public void checkColumnIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getColumnDimension()) {
            throw new OutOfRangeException(LocalizedFormats.COLUMN_INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(getColumnDimension() - 1));
        }
    }

    /* access modifiers changed from: protected */
    public void checkSubMatrixIndex(int i, int i2, int i3, int i4) throws NumberIsTooSmallException, OutOfRangeException {
        checkRowIndex(i);
        checkRowIndex(i2);
        if (i2 < i) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(i2), Integer.valueOf(i), true);
        }
        checkColumnIndex(i3);
        checkColumnIndex(i4);
        if (i4 < i3) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_COLUMN_AFTER_FINAL_COLUMN, Integer.valueOf(i4), Integer.valueOf(i3), true);
        }
    }

    /* access modifiers changed from: protected */
    public void checkSubMatrixIndex(int[] iArr, int[] iArr2) throws NoDataException, NullArgumentException, OutOfRangeException {
        if (iArr == null || iArr2 == null) {
            throw new NullArgumentException();
        } else if (iArr.length == 0 || iArr2.length == 0) {
            throw new NoDataException();
        } else {
            for (int checkRowIndex : iArr) {
                checkRowIndex(checkRowIndex);
            }
            for (int checkColumnIndex : iArr2) {
                checkColumnIndex(checkColumnIndex);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void checkAdditionCompatible(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        if (getRowDimension() != fieldMatrix.getRowDimension() || getColumnDimension() != fieldMatrix.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), getRowDimension(), getColumnDimension());
        }
    }

    /* access modifiers changed from: protected */
    public void checkSubtractionCompatible(FieldMatrix<T> fieldMatrix) throws MatrixDimensionMismatchException {
        if (getRowDimension() != fieldMatrix.getRowDimension() || getColumnDimension() != fieldMatrix.getColumnDimension()) {
            throw new MatrixDimensionMismatchException(fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension(), getRowDimension(), getColumnDimension());
        }
    }

    /* access modifiers changed from: protected */
    public void checkMultiplicationCompatible(FieldMatrix<T> fieldMatrix) throws DimensionMismatchException {
        if (getColumnDimension() != fieldMatrix.getRowDimension()) {
            throw new DimensionMismatchException(fieldMatrix.getRowDimension(), getColumnDimension());
        }
    }
}
