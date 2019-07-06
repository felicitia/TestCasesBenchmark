package org.apache.commons.math3.linear;

import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;

@Deprecated
public class SparseFieldMatrix<T extends FieldElement<T>> extends AbstractFieldMatrix<T> {
    private final int columns;
    private final OpenIntToFieldHashMap<T> entries;
    private final int rows;

    public SparseFieldMatrix(Field<T> field) {
        super(field);
        this.rows = 0;
        this.columns = 0;
        this.entries = new OpenIntToFieldHashMap<>(field);
    }

    public SparseFieldMatrix(Field<T> field, int i, int i2) {
        super(field, i, i2);
        this.rows = i;
        this.columns = i2;
        this.entries = new OpenIntToFieldHashMap<>(field);
    }

    public SparseFieldMatrix(SparseFieldMatrix<T> sparseFieldMatrix) {
        super(sparseFieldMatrix.getField(), sparseFieldMatrix.getRowDimension(), sparseFieldMatrix.getColumnDimension());
        this.rows = sparseFieldMatrix.getRowDimension();
        this.columns = sparseFieldMatrix.getColumnDimension();
        this.entries = new OpenIntToFieldHashMap<>(sparseFieldMatrix.entries);
    }

    public SparseFieldMatrix(FieldMatrix<T> fieldMatrix) {
        super(fieldMatrix.getField(), fieldMatrix.getRowDimension(), fieldMatrix.getColumnDimension());
        this.rows = fieldMatrix.getRowDimension();
        this.columns = fieldMatrix.getColumnDimension();
        this.entries = new OpenIntToFieldHashMap<>(getField());
        for (int i = 0; i < this.rows; i++) {
            for (int i2 = 0; i2 < this.columns; i2++) {
                setEntry(i, i2, fieldMatrix.getEntry(i, i2));
            }
        }
    }

    public void addToEntry(int i, int i2, T t) {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int computeKey = computeKey(i, i2);
        FieldElement fieldElement = (FieldElement) this.entries.get(computeKey).add(t);
        if (((FieldElement) getField().getZero()).equals(fieldElement)) {
            this.entries.remove(computeKey);
        } else {
            this.entries.put(computeKey, fieldElement);
        }
    }

    public FieldMatrix<T> copy() {
        return new SparseFieldMatrix(this);
    }

    public FieldMatrix<T> createMatrix(int i, int i2) {
        return new SparseFieldMatrix(getField(), i, i2);
    }

    public int getColumnDimension() {
        return this.columns;
    }

    public T getEntry(int i, int i2) {
        checkRowIndex(i);
        checkColumnIndex(i2);
        return this.entries.get(computeKey(i, i2));
    }

    public int getRowDimension() {
        return this.rows;
    }

    public void multiplyEntry(int i, int i2, T t) {
        checkRowIndex(i);
        checkColumnIndex(i2);
        int computeKey = computeKey(i, i2);
        FieldElement fieldElement = (FieldElement) this.entries.get(computeKey).multiply(t);
        if (((FieldElement) getField().getZero()).equals(fieldElement)) {
            this.entries.remove(computeKey);
        } else {
            this.entries.put(computeKey, fieldElement);
        }
    }

    public void setEntry(int i, int i2, T t) {
        checkRowIndex(i);
        checkColumnIndex(i2);
        if (((FieldElement) getField().getZero()).equals(t)) {
            this.entries.remove(computeKey(i, i2));
        } else {
            this.entries.put(computeKey(i, i2), t);
        }
    }

    private int computeKey(int i, int i2) {
        return (i * this.columns) + i2;
    }
}
