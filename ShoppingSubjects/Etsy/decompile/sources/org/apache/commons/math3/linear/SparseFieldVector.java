package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.OpenIntToFieldHashMap;
import org.apache.commons.math3.util.OpenIntToFieldHashMap.Iterator;

@Deprecated
public class SparseFieldVector<T extends FieldElement<T>> implements Serializable, FieldVector<T> {
    private static final long serialVersionUID = 7841233292190413362L;
    private final OpenIntToFieldHashMap<T> entries;
    private final Field<T> field;
    private final int virtualSize;

    public SparseFieldVector(Field<T> field2) {
        this(field2, 0);
    }

    public SparseFieldVector(Field<T> field2, int i) {
        this.field = field2;
        this.virtualSize = i;
        this.entries = new OpenIntToFieldHashMap<>(field2);
    }

    protected SparseFieldVector(SparseFieldVector<T> sparseFieldVector, int i) {
        this.field = sparseFieldVector.field;
        this.virtualSize = sparseFieldVector.getDimension() + i;
        this.entries = new OpenIntToFieldHashMap<>(sparseFieldVector.entries);
    }

    public SparseFieldVector(Field<T> field2, int i, int i2) {
        this.field = field2;
        this.virtualSize = i;
        this.entries = new OpenIntToFieldHashMap<>(field2, i2);
    }

    public SparseFieldVector(Field<T> field2, T[] tArr) {
        this.field = field2;
        this.virtualSize = tArr.length;
        this.entries = new OpenIntToFieldHashMap<>(field2);
        for (int i = 0; i < tArr.length; i++) {
            this.entries.put(i, tArr[i]);
        }
    }

    public SparseFieldVector(SparseFieldVector<T> sparseFieldVector) {
        this.field = sparseFieldVector.field;
        this.virtualSize = sparseFieldVector.getDimension();
        this.entries = new OpenIntToFieldHashMap<>(sparseFieldVector.getEntries());
    }

    private OpenIntToFieldHashMap<T> getEntries() {
        return this.entries;
    }

    public FieldVector<T> add(SparseFieldVector<T> sparseFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(sparseFieldVector.getDimension());
        SparseFieldVector sparseFieldVector2 = (SparseFieldVector) copy();
        Iterator it = sparseFieldVector.getEntries().iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            FieldElement value = it.value();
            if (this.entries.containsKey(key)) {
                sparseFieldVector2.setEntry(key, (FieldElement) this.entries.get(key).add(value));
            } else {
                sparseFieldVector2.setEntry(key, value);
            }
        }
        return sparseFieldVector2;
    }

    public FieldVector<T> append(SparseFieldVector<T> sparseFieldVector) {
        SparseFieldVector sparseFieldVector2 = new SparseFieldVector(this, sparseFieldVector.getDimension());
        Iterator it = sparseFieldVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            sparseFieldVector2.setEntry(it.key() + this.virtualSize, it.value());
        }
        return sparseFieldVector2;
    }

    public FieldVector<T> append(FieldVector<T> fieldVector) {
        if (fieldVector instanceof SparseFieldVector) {
            return append((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this, dimension);
        for (int i = 0; i < dimension; i++) {
            sparseFieldVector.setEntry(this.virtualSize + i, fieldVector.getEntry(i));
        }
        return sparseFieldVector;
    }

    public FieldVector<T> append(T t) {
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this, 1);
        sparseFieldVector.setEntry(this.virtualSize, t);
        return sparseFieldVector;
    }

    public FieldVector<T> copy() {
        return new SparseFieldVector(this);
    }

    public T dotProduct(FieldVector<T> fieldVector) throws DimensionMismatchException {
        checkVectorDimensions(fieldVector.getDimension());
        T t = (FieldElement) this.field.getZero();
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            t = (FieldElement) t.add(fieldVector.getEntry(it.key()).multiply(it.value()));
        }
        return t;
    }

    public FieldVector<T> ebeDivide(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(fieldVector.getDimension());
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this);
        Iterator it = sparseFieldVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            sparseFieldVector.setEntry(it.key(), (FieldElement) it.value().divide(fieldVector.getEntry(it.key())));
        }
        return sparseFieldVector;
    }

    public FieldVector<T> ebeMultiply(FieldVector<T> fieldVector) throws DimensionMismatchException {
        checkVectorDimensions(fieldVector.getDimension());
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this);
        Iterator it = sparseFieldVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            sparseFieldVector.setEntry(it.key(), (FieldElement) it.value().multiply(fieldVector.getEntry(it.key())));
        }
        return sparseFieldVector;
    }

    @Deprecated
    public T[] getData() {
        return toArray();
    }

    public int getDimension() {
        return this.virtualSize;
    }

    public T getEntry(int i) throws OutOfRangeException {
        checkIndex(i);
        return this.entries.get(i);
    }

    public Field<T> getField() {
        return this.field;
    }

    public FieldVector<T> getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        checkIndex(i);
        int i3 = i + i2;
        checkIndex(i3 - 1);
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this.field, i2);
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (key >= i && key < i3) {
                sparseFieldVector.setEntry(key - i, it.value());
            }
        }
        return sparseFieldVector;
    }

    public FieldVector<T> mapAdd(T t) throws NullArgumentException {
        return copy().mapAddToSelf(t);
    }

    public FieldVector<T> mapAddToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, (FieldElement) getEntry(i).add(t));
        }
        return this;
    }

    public FieldVector<T> mapDivide(T t) throws NullArgumentException, MathArithmeticException {
        return copy().mapDivideToSelf(t);
    }

    public FieldVector<T> mapDivideToSelf(T t) throws NullArgumentException, MathArithmeticException {
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            this.entries.put(it.key(), (FieldElement) it.value().divide(t));
        }
        return this;
    }

    public FieldVector<T> mapInv() throws MathArithmeticException {
        return copy().mapInvToSelf();
    }

    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, (FieldElement) ((FieldElement) this.field.getOne()).divide(getEntry(i)));
        }
        return this;
    }

    public FieldVector<T> mapMultiply(T t) throws NullArgumentException {
        return copy().mapMultiplyToSelf(t);
    }

    public FieldVector<T> mapMultiplyToSelf(T t) throws NullArgumentException {
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            this.entries.put(it.key(), (FieldElement) it.value().multiply(t));
        }
        return this;
    }

    public FieldVector<T> mapSubtract(T t) throws NullArgumentException {
        return copy().mapSubtractToSelf(t);
    }

    public FieldVector<T> mapSubtractToSelf(T t) throws NullArgumentException {
        return mapAddToSelf((FieldElement) ((FieldElement) this.field.getZero()).subtract(t));
    }

    public FieldMatrix<T> outerProduct(SparseFieldVector<T> sparseFieldVector) {
        SparseFieldMatrix sparseFieldMatrix = new SparseFieldMatrix(this.field, this.virtualSize, sparseFieldVector.getDimension());
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            Iterator it2 = sparseFieldVector.entries.iterator();
            while (it2.hasNext()) {
                it2.advance();
                sparseFieldMatrix.setEntry(it.key(), it2.key(), (FieldElement) it.value().multiply(it2.value()));
            }
        }
        return sparseFieldMatrix;
    }

    public FieldMatrix<T> outerProduct(FieldVector<T> fieldVector) {
        if (fieldVector instanceof SparseFieldVector) {
            return outerProduct((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        SparseFieldMatrix sparseFieldMatrix = new SparseFieldMatrix(this.field, this.virtualSize, dimension);
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            FieldElement value = it.value();
            for (int i = 0; i < dimension; i++) {
                sparseFieldMatrix.setEntry(key, i, (FieldElement) value.multiply(fieldVector.getEntry(i)));
            }
        }
        return sparseFieldMatrix;
    }

    public FieldVector<T> projection(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(fieldVector.getDimension());
        return fieldVector.mapMultiply((FieldElement) dotProduct(fieldVector).divide(fieldVector.dotProduct(fieldVector)));
    }

    public void set(T t) {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, t);
        }
    }

    public void setEntry(int i, T t) throws OutOfRangeException {
        checkIndex(i);
        this.entries.put(i, t);
    }

    public void setSubVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException {
        checkIndex(i);
        checkIndex((fieldVector.getDimension() + i) - 1);
        int dimension = fieldVector.getDimension();
        for (int i2 = 0; i2 < dimension; i2++) {
            setEntry(i2 + i, fieldVector.getEntry(i2));
        }
    }

    public SparseFieldVector<T> subtract(SparseFieldVector<T> sparseFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(sparseFieldVector.getDimension());
        SparseFieldVector<T> sparseFieldVector2 = (SparseFieldVector) copy();
        Iterator it = sparseFieldVector.getEntries().iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (this.entries.containsKey(key)) {
                sparseFieldVector2.setEntry(key, (FieldElement) this.entries.get(key).subtract(it.value()));
            } else {
                sparseFieldVector2.setEntry(key, (FieldElement) ((FieldElement) this.field.getZero()).subtract(it.value()));
            }
        }
        return sparseFieldVector2;
    }

    public FieldVector<T> subtract(FieldVector<T> fieldVector) throws DimensionMismatchException {
        if (fieldVector instanceof SparseFieldVector) {
            return subtract((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        checkVectorDimensions(dimension);
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this);
        for (int i = 0; i < dimension; i++) {
            if (this.entries.containsKey(i)) {
                sparseFieldVector.setEntry(i, (FieldElement) this.entries.get(i).subtract(fieldVector.getEntry(i)));
            } else {
                sparseFieldVector.setEntry(i, (FieldElement) ((FieldElement) this.field.getZero()).subtract(fieldVector.getEntry(i)));
            }
        }
        return sparseFieldVector;
    }

    public T[] toArray() {
        T[] buildArray = buildArray(this.virtualSize);
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            buildArray[it.key()] = it.value();
        }
        return buildArray;
    }

    private void checkIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getDimension()) {
            throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(getDimension() - 1));
        }
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(int i) throws DimensionMismatchException {
        if (getDimension() != i) {
            throw new DimensionMismatchException(getDimension(), i);
        }
    }

    public FieldVector<T> add(FieldVector<T> fieldVector) throws DimensionMismatchException {
        if (fieldVector instanceof SparseFieldVector) {
            return add((SparseFieldVector) fieldVector);
        }
        int dimension = fieldVector.getDimension();
        checkVectorDimensions(dimension);
        SparseFieldVector sparseFieldVector = new SparseFieldVector(this.field, getDimension());
        for (int i = 0; i < dimension; i++) {
            sparseFieldVector.setEntry(i, (FieldElement) fieldVector.getEntry(i).add(getEntry(i)));
        }
        return sparseFieldVector;
    }

    private T[] buildArray(int i) {
        return (FieldElement[]) Array.newInstance(this.field.getRuntimeClass(), i);
    }

    public int hashCode() {
        int hashCode = (((this.field == null ? 0 : this.field.hashCode()) + 31) * 31) + this.virtualSize;
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            hashCode = (hashCode * 31) + it.value().hashCode();
        }
        return hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SparseFieldVector)) {
            return false;
        }
        SparseFieldVector sparseFieldVector = (SparseFieldVector) obj;
        if (this.field == null) {
            if (sparseFieldVector.field != null) {
                return false;
            }
        } else if (!this.field.equals(sparseFieldVector.field)) {
            return false;
        }
        if (this.virtualSize != sparseFieldVector.virtualSize) {
            return false;
        }
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            if (!sparseFieldVector.getEntry(it.key()).equals(it.value())) {
                return false;
            }
        }
        Iterator it2 = sparseFieldVector.getEntries().iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (!it2.value().equals(getEntry(it2.key()))) {
                return false;
            }
        }
        return true;
    }
}
