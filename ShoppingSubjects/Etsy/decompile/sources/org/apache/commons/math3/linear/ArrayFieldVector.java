package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.ZeroException;
import org.apache.commons.math3.exception.util.LocalizedFormats;

public class ArrayFieldVector<T extends FieldElement<T>> implements Serializable, FieldVector<T> {
    private static final long serialVersionUID = 7648186910365927050L;
    private T[] data;
    private final Field<T> field;

    public ArrayFieldVector(Field<T> field2) {
        this(field2, 0);
    }

    public ArrayFieldVector(Field<T> field2, int i) {
        this.field = field2;
        this.data = buildArray(i);
        Arrays.fill(this.data, field2.getZero());
    }

    public ArrayFieldVector(int i, T t) {
        this(t.getField(), i);
        Arrays.fill(this.data, t);
    }

    public ArrayFieldVector(T[] tArr) throws NullArgumentException, ZeroException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        try {
            this.field = tArr[0].getField();
            this.data = (FieldElement[]) tArr.clone();
        } catch (ArrayIndexOutOfBoundsException unused) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        }
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr) throws NullArgumentException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        this.field = field2;
        this.data = (FieldElement[]) tArr.clone();
    }

    public ArrayFieldVector(T[] tArr, boolean z) throws NullArgumentException, ZeroException {
        if (tArr == null) {
            throw new NullArgumentException();
        } else if (tArr.length == 0) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        } else {
            this.field = tArr[0].getField();
            if (z) {
                tArr = (FieldElement[]) tArr.clone();
            }
            this.data = tArr;
        }
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr, boolean z) throws NullArgumentException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        this.field = field2;
        if (z) {
            tArr = (FieldElement[]) tArr.clone();
        }
        this.data = tArr;
    }

    public ArrayFieldVector(T[] tArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        int i3 = i + i2;
        if (tArr.length < i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(tArr.length), true);
        }
        this.field = tArr[0].getField();
        this.data = buildArray(i2);
        System.arraycopy(tArr, i, this.data, 0, i2);
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        if (tArr == null) {
            throw new NullArgumentException();
        }
        int i3 = i + i2;
        if (tArr.length < i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(tArr.length), true);
        }
        this.field = field2;
        this.data = buildArray(i2);
        System.arraycopy(tArr, i, this.data, 0, i2);
    }

    public ArrayFieldVector(FieldVector<T> fieldVector) throws NullArgumentException {
        if (fieldVector == null) {
            throw new NullArgumentException();
        }
        this.field = fieldVector.getField();
        this.data = buildArray(fieldVector.getDimension());
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = fieldVector.getEntry(i);
        }
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector) throws NullArgumentException {
        if (arrayFieldVector == null) {
            throw new NullArgumentException();
        }
        this.field = arrayFieldVector.getField();
        this.data = (FieldElement[]) arrayFieldVector.data.clone();
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, boolean z) throws NullArgumentException {
        if (arrayFieldVector == null) {
            throw new NullArgumentException();
        }
        this.field = arrayFieldVector.getField();
        this.data = z ? (FieldElement[]) arrayFieldVector.data.clone() : arrayFieldVector.data;
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, ArrayFieldVector<T> arrayFieldVector2) throws NullArgumentException {
        if (arrayFieldVector == null || arrayFieldVector2 == null) {
            throw new NullArgumentException();
        }
        this.field = arrayFieldVector.getField();
        this.data = buildArray(arrayFieldVector.data.length + arrayFieldVector2.data.length);
        System.arraycopy(arrayFieldVector.data, 0, this.data, 0, arrayFieldVector.data.length);
        System.arraycopy(arrayFieldVector2.data, 0, this.data, arrayFieldVector.data.length, arrayFieldVector2.data.length);
    }

    public ArrayFieldVector(ArrayFieldVector<T> arrayFieldVector, T[] tArr) throws NullArgumentException {
        if (arrayFieldVector == null || tArr == null) {
            throw new NullArgumentException();
        }
        this.field = arrayFieldVector.getField();
        this.data = buildArray(arrayFieldVector.data.length + tArr.length);
        System.arraycopy(arrayFieldVector.data, 0, this.data, 0, arrayFieldVector.data.length);
        System.arraycopy(tArr, 0, this.data, arrayFieldVector.data.length, tArr.length);
    }

    public ArrayFieldVector(T[] tArr, ArrayFieldVector<T> arrayFieldVector) throws NullArgumentException {
        if (tArr == null || arrayFieldVector == null) {
            throw new NullArgumentException();
        }
        this.field = arrayFieldVector.getField();
        this.data = buildArray(tArr.length + arrayFieldVector.data.length);
        System.arraycopy(tArr, 0, this.data, 0, tArr.length);
        System.arraycopy(arrayFieldVector.data, 0, this.data, tArr.length, arrayFieldVector.data.length);
    }

    public ArrayFieldVector(T[] tArr, T[] tArr2) throws NullArgumentException, ZeroException {
        if (tArr == null || tArr2 == null) {
            throw new NullArgumentException();
        } else if (tArr.length + tArr2.length == 0) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        } else {
            this.data = buildArray(tArr.length + tArr2.length);
            System.arraycopy(tArr, 0, this.data, 0, tArr.length);
            System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
            this.field = this.data[0].getField();
        }
    }

    public ArrayFieldVector(Field<T> field2, T[] tArr, T[] tArr2) throws NullArgumentException, ZeroException {
        if (tArr == null || tArr2 == null) {
            throw new NullArgumentException();
        } else if (tArr.length + tArr2.length == 0) {
            throw new ZeroException(LocalizedFormats.VECTOR_MUST_HAVE_AT_LEAST_ONE_ELEMENT, new Object[0]);
        } else {
            this.data = buildArray(tArr.length + tArr2.length);
            System.arraycopy(tArr, 0, this.data, 0, tArr.length);
            System.arraycopy(tArr2, 0, this.data, tArr.length, tArr2.length);
            this.field = field2;
        }
    }

    private T[] buildArray(int i) {
        return (FieldElement[]) Array.newInstance(this.field.getRuntimeClass(), i);
    }

    public Field<T> getField() {
        return this.field;
    }

    public FieldVector<T> copy() {
        return new ArrayFieldVector(this, true);
    }

    public FieldVector<T> add(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return add((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] buildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                buildArray[i] = (FieldElement) this.data[i].add(fieldVector.getEntry(i));
            }
            return new ArrayFieldVector(this.field, (T[]) buildArray, false);
        }
    }

    public ArrayFieldVector<T> add(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].add(arrayFieldVector.data[i]);
        }
        return new ArrayFieldVector<>(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> subtract(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return subtract((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] buildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                buildArray[i] = (FieldElement) this.data[i].subtract(fieldVector.getEntry(i));
            }
            return new ArrayFieldVector(this.field, (T[]) buildArray, false);
        }
    }

    public ArrayFieldVector<T> subtract(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].subtract(arrayFieldVector.data[i]);
        }
        return new ArrayFieldVector<>(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> mapAdd(T t) throws NullArgumentException {
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].add(t);
        }
        return new ArrayFieldVector(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> mapAddToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = (FieldElement) this.data[i].add(t);
        }
        return this;
    }

    public FieldVector<T> mapSubtract(T t) throws NullArgumentException {
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].subtract(t);
        }
        return new ArrayFieldVector(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> mapSubtractToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = (FieldElement) this.data[i].subtract(t);
        }
        return this;
    }

    public FieldVector<T> mapMultiply(T t) throws NullArgumentException {
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].multiply(t);
        }
        return new ArrayFieldVector(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> mapMultiplyToSelf(T t) throws NullArgumentException {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = (FieldElement) this.data[i].multiply(t);
        }
        return this;
    }

    public FieldVector<T> mapDivide(T t) throws NullArgumentException, MathArithmeticException {
        if (t == null) {
            throw new NullArgumentException();
        }
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].divide(t);
        }
        return new ArrayFieldVector(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> mapDivideToSelf(T t) throws NullArgumentException, MathArithmeticException {
        if (t == null) {
            throw new NullArgumentException();
        }
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = (FieldElement) this.data[i].divide(t);
        }
        return this;
    }

    public FieldVector<T> mapInv() throws MathArithmeticException {
        FieldElement[] buildArray = buildArray(this.data.length);
        FieldElement fieldElement = (FieldElement) this.field.getOne();
        int i = 0;
        while (i < this.data.length) {
            try {
                buildArray[i] = (FieldElement) fieldElement.divide(this.data[i]);
                i++;
            } catch (MathArithmeticException unused) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
        return new ArrayFieldVector(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> mapInvToSelf() throws MathArithmeticException {
        FieldElement fieldElement = (FieldElement) this.field.getOne();
        int i = 0;
        while (i < this.data.length) {
            try {
                this.data[i] = (FieldElement) fieldElement.divide(this.data[i]);
                i++;
            } catch (MathArithmeticException unused) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
        return this;
    }

    public FieldVector<T> ebeMultiply(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return ebeMultiply((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] buildArray = buildArray(this.data.length);
            for (int i = 0; i < this.data.length; i++) {
                buildArray[i] = (FieldElement) this.data[i].multiply(fieldVector.getEntry(i));
            }
            return new ArrayFieldVector(this.field, (T[]) buildArray, false);
        }
    }

    public ArrayFieldVector<T> ebeMultiply(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] buildArray = buildArray(this.data.length);
        for (int i = 0; i < this.data.length; i++) {
            buildArray[i] = (FieldElement) this.data[i].multiply(arrayFieldVector.data[i]);
        }
        return new ArrayFieldVector<>(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> ebeDivide(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        try {
            return ebeDivide((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            FieldElement[] buildArray = buildArray(this.data.length);
            int i = 0;
            while (i < this.data.length) {
                try {
                    buildArray[i] = (FieldElement) this.data[i].divide(fieldVector.getEntry(i));
                    i++;
                } catch (MathArithmeticException unused2) {
                    throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
                }
            }
            return new ArrayFieldVector(this.field, (T[]) buildArray, false);
        }
    }

    public ArrayFieldVector<T> ebeDivide(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException, MathArithmeticException {
        checkVectorDimensions(arrayFieldVector.data.length);
        FieldElement[] buildArray = buildArray(this.data.length);
        int i = 0;
        while (i < this.data.length) {
            try {
                buildArray[i] = (FieldElement) this.data[i].divide(arrayFieldVector.data[i]);
                i++;
            } catch (MathArithmeticException unused) {
                throw new MathArithmeticException(LocalizedFormats.INDEX, Integer.valueOf(i));
            }
        }
        return new ArrayFieldVector<>(this.field, (T[]) buildArray, false);
    }

    public T[] getData() {
        return (FieldElement[]) this.data.clone();
    }

    public T[] getDataRef() {
        return this.data;
    }

    public T dotProduct(FieldVector<T> fieldVector) throws DimensionMismatchException {
        try {
            return dotProduct((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            checkVectorDimensions(fieldVector);
            T t = (FieldElement) this.field.getZero();
            for (int i = 0; i < this.data.length; i++) {
                t = (FieldElement) t.add(this.data[i].multiply(fieldVector.getEntry(i)));
            }
            return t;
        }
    }

    public T dotProduct(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException {
        checkVectorDimensions(arrayFieldVector.data.length);
        T t = (FieldElement) this.field.getZero();
        for (int i = 0; i < this.data.length; i++) {
            t = (FieldElement) t.add(this.data[i].multiply(arrayFieldVector.data[i]));
        }
        return t;
    }

    public FieldVector<T> projection(FieldVector<T> fieldVector) throws DimensionMismatchException, MathArithmeticException {
        return fieldVector.mapMultiply((FieldElement) dotProduct(fieldVector).divide(fieldVector.dotProduct(fieldVector)));
    }

    public ArrayFieldVector<T> projection(ArrayFieldVector<T> arrayFieldVector) throws DimensionMismatchException, MathArithmeticException {
        return (ArrayFieldVector) arrayFieldVector.mapMultiply((FieldElement) dotProduct(arrayFieldVector).divide(arrayFieldVector.dotProduct(arrayFieldVector)));
    }

    public FieldMatrix<T> outerProduct(FieldVector<T> fieldVector) {
        try {
            return outerProduct((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            int length = this.data.length;
            int dimension = fieldVector.getDimension();
            Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, dimension);
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < dimension; i2++) {
                    array2DRowFieldMatrix.setEntry(i, i2, (FieldElement) this.data[i].multiply(fieldVector.getEntry(i2)));
                }
            }
            return array2DRowFieldMatrix;
        }
    }

    public FieldMatrix<T> outerProduct(ArrayFieldVector<T> arrayFieldVector) {
        int length = this.data.length;
        int length2 = arrayFieldVector.data.length;
        Array2DRowFieldMatrix array2DRowFieldMatrix = new Array2DRowFieldMatrix(this.field, length, length2);
        for (int i = 0; i < length; i++) {
            for (int i2 = 0; i2 < length2; i2++) {
                array2DRowFieldMatrix.setEntry(i, i2, (FieldElement) this.data[i].multiply(arrayFieldVector.data[i2]));
            }
        }
        return array2DRowFieldMatrix;
    }

    public T getEntry(int i) {
        return this.data[i];
    }

    public int getDimension() {
        return this.data.length;
    }

    public FieldVector<T> append(FieldVector<T> fieldVector) {
        try {
            return append((ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            return new ArrayFieldVector(this, new ArrayFieldVector<>(fieldVector));
        }
    }

    public ArrayFieldVector<T> append(ArrayFieldVector<T> arrayFieldVector) {
        return new ArrayFieldVector<>(this, arrayFieldVector);
    }

    public FieldVector<T> append(T t) {
        FieldElement[] buildArray = buildArray(this.data.length + 1);
        System.arraycopy(this.data, 0, buildArray, 0, this.data.length);
        buildArray[this.data.length] = t;
        return new ArrayFieldVector(this.field, (T[]) buildArray, false);
    }

    public FieldVector<T> getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        ArrayFieldVector arrayFieldVector = new ArrayFieldVector(this.field, i2);
        try {
            System.arraycopy(this.data, i, arrayFieldVector.data, 0, i2);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + i2) - 1);
        }
        return arrayFieldVector;
    }

    public void setEntry(int i, T t) {
        try {
            this.data[i] = t;
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
        }
    }

    public void setSubVector(int i, FieldVector<T> fieldVector) throws OutOfRangeException {
        try {
            set(i, (ArrayFieldVector) fieldVector);
        } catch (ClassCastException unused) {
            int i2 = i;
            while (i2 < fieldVector.getDimension() + i) {
                try {
                    this.data[i2] = fieldVector.getEntry(i2 - i);
                    i2++;
                } catch (IndexOutOfBoundsException unused2) {
                    checkIndex(i);
                    checkIndex((i + fieldVector.getDimension()) - 1);
                    return;
                }
            }
        }
    }

    public void set(int i, ArrayFieldVector<T> arrayFieldVector) throws OutOfRangeException {
        try {
            System.arraycopy(arrayFieldVector.data, 0, this.data, i, arrayFieldVector.data.length);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + arrayFieldVector.data.length) - 1);
        }
    }

    public void set(T t) {
        Arrays.fill(this.data, t);
    }

    public T[] toArray() {
        return (FieldElement[]) this.data.clone();
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(FieldVector<T> fieldVector) throws DimensionMismatchException {
        checkVectorDimensions(fieldVector.getDimension());
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(int i) throws DimensionMismatchException {
        if (this.data.length != i) {
            throw new DimensionMismatchException(this.data.length, i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        try {
            FieldVector fieldVector = (FieldVector) obj;
            if (this.data.length != fieldVector.getDimension()) {
                return false;
            }
            for (int i = 0; i < this.data.length; i++) {
                if (!this.data[i].equals(fieldVector.getEntry(i))) {
                    return false;
                }
            }
            return true;
        } catch (ClassCastException unused) {
            return false;
        }
    }

    public int hashCode() {
        int i = 3542;
        for (T hashCode : this.data) {
            i ^= hashCode.hashCode();
        }
        return i;
    }

    private void checkIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getDimension()) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(getDimension() - 1));
        }
    }
}
