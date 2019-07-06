package org.apache.commons.math3.linear;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NullArgumentException;
import org.apache.commons.math3.exception.NumberIsTooLargeException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.MathUtils;

public class ArrayRealVector extends RealVector implements Serializable {
    private static final RealVectorFormat DEFAULT_FORMAT = RealVectorFormat.getInstance();
    private static final long serialVersionUID = -1097961340710804027L;
    private double[] data;

    public ArrayRealVector() {
        this.data = new double[0];
    }

    public ArrayRealVector(int i) {
        this.data = new double[i];
    }

    public ArrayRealVector(int i, double d) {
        this.data = new double[i];
        Arrays.fill(this.data, d);
    }

    public ArrayRealVector(double[] dArr) {
        this.data = (double[]) dArr.clone();
    }

    public ArrayRealVector(double[] dArr, boolean z) throws NullArgumentException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        if (z) {
            dArr = (double[]) dArr.clone();
        }
        this.data = dArr;
    }

    public ArrayRealVector(double[] dArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int i3 = i + i2;
        if (dArr.length < i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
        }
        this.data = new double[i2];
        System.arraycopy(dArr, i, this.data, 0, i2);
    }

    public ArrayRealVector(Double[] dArr) {
        this.data = new double[dArr.length];
        for (int i = 0; i < dArr.length; i++) {
            this.data[i] = dArr[i].doubleValue();
        }
    }

    public ArrayRealVector(Double[] dArr, int i, int i2) throws NullArgumentException, NumberIsTooLargeException {
        if (dArr == null) {
            throw new NullArgumentException();
        }
        int i3 = i + i2;
        if (dArr.length < i3) {
            throw new NumberIsTooLargeException(Integer.valueOf(i3), Integer.valueOf(dArr.length), true);
        }
        this.data = new double[i2];
        for (int i4 = i; i4 < i3; i4++) {
            this.data[i4 - i] = dArr[i4].doubleValue();
        }
    }

    public ArrayRealVector(RealVector realVector) throws NullArgumentException {
        if (realVector == null) {
            throw new NullArgumentException();
        }
        this.data = new double[realVector.getDimension()];
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = realVector.getEntry(i);
        }
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector) throws NullArgumentException {
        this(arrayRealVector, true);
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, boolean z) {
        this.data = z ? (double[]) arrayRealVector.data.clone() : arrayRealVector.data;
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, ArrayRealVector arrayRealVector2) {
        this.data = new double[(arrayRealVector.data.length + arrayRealVector2.data.length)];
        System.arraycopy(arrayRealVector.data, 0, this.data, 0, arrayRealVector.data.length);
        System.arraycopy(arrayRealVector2.data, 0, this.data, arrayRealVector.data.length, arrayRealVector2.data.length);
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, RealVector realVector) {
        int length = arrayRealVector.data.length;
        int dimension = realVector.getDimension();
        this.data = new double[(length + dimension)];
        System.arraycopy(arrayRealVector.data, 0, this.data, 0, length);
        for (int i = 0; i < dimension; i++) {
            this.data[length + i] = realVector.getEntry(i);
        }
    }

    public ArrayRealVector(RealVector realVector, ArrayRealVector arrayRealVector) {
        int dimension = realVector.getDimension();
        int length = arrayRealVector.data.length;
        this.data = new double[(dimension + length)];
        for (int i = 0; i < dimension; i++) {
            this.data[i] = realVector.getEntry(i);
        }
        System.arraycopy(arrayRealVector.data, 0, this.data, dimension, length);
    }

    public ArrayRealVector(ArrayRealVector arrayRealVector, double[] dArr) {
        int dimension = arrayRealVector.getDimension();
        int length = dArr.length;
        this.data = new double[(dimension + length)];
        System.arraycopy(arrayRealVector.data, 0, this.data, 0, dimension);
        System.arraycopy(dArr, 0, this.data, dimension, length);
    }

    public ArrayRealVector(double[] dArr, ArrayRealVector arrayRealVector) {
        int length = dArr.length;
        int dimension = arrayRealVector.getDimension();
        this.data = new double[(length + dimension)];
        System.arraycopy(dArr, 0, this.data, 0, length);
        System.arraycopy(arrayRealVector.data, 0, this.data, length, dimension);
    }

    public ArrayRealVector(double[] dArr, double[] dArr2) {
        int length = dArr.length;
        int length2 = dArr2.length;
        this.data = new double[(length + length2)];
        System.arraycopy(dArr, 0, this.data, 0, length);
        System.arraycopy(dArr2, 0, this.data, length, length2);
    }

    public ArrayRealVector copy() {
        return new ArrayRealVector(this, true);
    }

    public ArrayRealVector add(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] + dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        Iterator it = realVector.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int index = entry.getIndex();
            dArr3[index] = dArr3[index] + entry.getValue();
        }
        return new ArrayRealVector(dArr3, false);
    }

    public ArrayRealVector subtract(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] - dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        Iterator it = realVector.iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int index = entry.getIndex();
            dArr3[index] = dArr3[index] - entry.getValue();
        }
        return new ArrayRealVector(dArr3, false);
    }

    public ArrayRealVector map(UnivariateFunction univariateFunction) {
        return copy().mapToSelf(univariateFunction);
    }

    public ArrayRealVector mapToSelf(UnivariateFunction univariateFunction) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = univariateFunction.value(this.data[i]);
        }
        return this;
    }

    public RealVector mapAddToSelf(double d) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = this.data[i] + d;
        }
        return this;
    }

    public RealVector mapSubtractToSelf(double d) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = this.data[i] - d;
        }
        return this;
    }

    public RealVector mapMultiplyToSelf(double d) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = this.data[i] * d;
        }
        return this;
    }

    public RealVector mapDivideToSelf(double d) {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = this.data[i] / d;
        }
        return this;
    }

    public ArrayRealVector ebeMultiply(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] * dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        for (int i2 = 0; i2 < this.data.length; i2++) {
            dArr3[i2] = dArr3[i2] * realVector.getEntry(i2);
        }
        return new ArrayRealVector(dArr3, false);
    }

    public ArrayRealVector ebeDivide(RealVector realVector) throws DimensionMismatchException {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = dArr.length;
            checkVectorDimensions(length);
            ArrayRealVector arrayRealVector = new ArrayRealVector(length);
            double[] dArr2 = arrayRealVector.data;
            for (int i = 0; i < length; i++) {
                dArr2[i] = this.data[i] / dArr[i];
            }
            return arrayRealVector;
        }
        checkVectorDimensions(realVector);
        double[] dArr3 = (double[]) this.data.clone();
        for (int i2 = 0; i2 < this.data.length; i2++) {
            dArr3[i2] = dArr3[i2] / realVector.getEntry(i2);
        }
        return new ArrayRealVector(dArr3, false);
    }

    public double[] getDataRef() {
        return this.data;
    }

    public double dotProduct(RealVector realVector) throws DimensionMismatchException {
        if (!(realVector instanceof ArrayRealVector)) {
            return super.dotProduct(realVector);
        }
        double[] dArr = ((ArrayRealVector) realVector).data;
        checkVectorDimensions(dArr.length);
        double d = 0.0d;
        for (int i = 0; i < this.data.length; i++) {
            d += this.data[i] * dArr[i];
        }
        return d;
    }

    public double getNorm() {
        double d = 0.0d;
        for (double d2 : this.data) {
            d += d2 * d2;
        }
        return FastMath.sqrt(d);
    }

    public double getL1Norm() {
        double d = 0.0d;
        for (double abs : this.data) {
            d += FastMath.abs(abs);
        }
        return d;
    }

    public double getLInfNorm() {
        double d = 0.0d;
        for (double abs : this.data) {
            d = FastMath.max(d, FastMath.abs(abs));
        }
        return d;
    }

    public double getDistance(RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        double d = 0.0d;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (i < this.data.length) {
                double d2 = this.data[i] - dArr[i];
                d += d2 * d2;
                i++;
            }
            return FastMath.sqrt(d);
        }
        checkVectorDimensions(realVector);
        while (i < this.data.length) {
            double entry = this.data[i] - realVector.getEntry(i);
            d += entry * entry;
            i++;
        }
        return FastMath.sqrt(d);
    }

    public double getL1Distance(RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        double d = 0.0d;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (i < this.data.length) {
                d += FastMath.abs(this.data[i] - dArr[i]);
                i++;
            }
            return d;
        }
        checkVectorDimensions(realVector);
        while (i < this.data.length) {
            d += FastMath.abs(this.data[i] - realVector.getEntry(i));
            i++;
        }
        return d;
    }

    public double getLInfDistance(RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        double d = 0.0d;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (i < this.data.length) {
                d = FastMath.max(d, FastMath.abs(this.data[i] - dArr[i]));
                i++;
            }
            return d;
        }
        checkVectorDimensions(realVector);
        while (i < this.data.length) {
            d = FastMath.max(d, FastMath.abs(this.data[i] - realVector.getEntry(i)));
            i++;
        }
        return d;
    }

    public RealMatrix outerProduct(RealVector realVector) {
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            int length = this.data.length;
            int length2 = dArr.length;
            RealMatrix createRealMatrix = MatrixUtils.createRealMatrix(length, length2);
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < length2; i2++) {
                    createRealMatrix.setEntry(i, i2, this.data[i] * dArr[i2]);
                }
            }
            return createRealMatrix;
        }
        int length3 = this.data.length;
        int dimension = realVector.getDimension();
        RealMatrix createRealMatrix2 = MatrixUtils.createRealMatrix(length3, dimension);
        for (int i3 = 0; i3 < length3; i3++) {
            for (int i4 = 0; i4 < dimension; i4++) {
                createRealMatrix2.setEntry(i3, i4, this.data[i3] * realVector.getEntry(i4));
            }
        }
        return createRealMatrix2;
    }

    public double getEntry(int i) throws OutOfRangeException {
        try {
            return this.data[i];
        } catch (IndexOutOfBoundsException unused) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(getDimension() - 1));
        }
    }

    public int getDimension() {
        return this.data.length;
    }

    public RealVector append(RealVector realVector) {
        try {
            return new ArrayRealVector(this, (ArrayRealVector) realVector);
        } catch (ClassCastException unused) {
            return new ArrayRealVector(this, realVector);
        }
    }

    public ArrayRealVector append(ArrayRealVector arrayRealVector) {
        return new ArrayRealVector(this, arrayRealVector);
    }

    public RealVector append(double d) {
        double[] dArr = new double[(this.data.length + 1)];
        System.arraycopy(this.data, 0, dArr, 0, this.data.length);
        dArr[this.data.length] = d;
        return new ArrayRealVector(dArr, false);
    }

    public RealVector getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        ArrayRealVector arrayRealVector = new ArrayRealVector(i2);
        try {
            System.arraycopy(this.data, i, arrayRealVector.data, 0, i2);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + i2) - 1);
        }
        return arrayRealVector;
    }

    public void setEntry(int i, double d) throws OutOfRangeException {
        try {
            this.data[i] = d;
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
        }
    }

    public void addToEntry(int i, double d) throws OutOfRangeException {
        try {
            double[] dArr = this.data;
            dArr[i] = dArr[i] + d;
        } catch (IndexOutOfBoundsException unused) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.data.length - 1));
        }
    }

    public void setSubVector(int i, RealVector realVector) throws OutOfRangeException {
        if (realVector instanceof ArrayRealVector) {
            setSubVector(i, ((ArrayRealVector) realVector).data);
            return;
        }
        int i2 = i;
        while (i2 < realVector.getDimension() + i) {
            try {
                this.data[i2] = realVector.getEntry(i2 - i);
                i2++;
            } catch (IndexOutOfBoundsException unused) {
                checkIndex(i);
                checkIndex((i + realVector.getDimension()) - 1);
                return;
            }
        }
    }

    public void setSubVector(int i, double[] dArr) throws OutOfRangeException {
        try {
            System.arraycopy(dArr, 0, this.data, i, dArr.length);
        } catch (IndexOutOfBoundsException unused) {
            checkIndex(i);
            checkIndex((i + dArr.length) - 1);
        }
    }

    public void set(double d) {
        Arrays.fill(this.data, d);
    }

    public double[] toArray() {
        return (double[]) this.data.clone();
    }

    public String toString() {
        return DEFAULT_FORMAT.format(this);
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(int i) throws DimensionMismatchException {
        if (this.data.length != i) {
            throw new DimensionMismatchException(this.data.length, i);
        }
    }

    public boolean isNaN() {
        for (double isNaN : this.data) {
            if (Double.isNaN(isNaN)) {
                return true;
            }
        }
        return false;
    }

    public boolean isInfinite() {
        if (isNaN()) {
            return false;
        }
        for (double isInfinite : this.data) {
            if (Double.isInfinite(isInfinite)) {
                return true;
            }
        }
        return false;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RealVector)) {
            return false;
        }
        RealVector realVector = (RealVector) obj;
        if (this.data.length != realVector.getDimension()) {
            return false;
        }
        if (realVector.isNaN()) {
            return isNaN();
        }
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i] != realVector.getEntry(i)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        if (isNaN()) {
            return 9;
        }
        return MathUtils.hash(this.data);
    }

    public ArrayRealVector combine(double d, double d2, RealVector realVector) throws DimensionMismatchException {
        return copy().combineToSelf(d, d2, realVector);
    }

    public ArrayRealVector combineToSelf(double d, double d2, RealVector realVector) throws DimensionMismatchException {
        int i = 0;
        if (realVector instanceof ArrayRealVector) {
            double[] dArr = ((ArrayRealVector) realVector).data;
            checkVectorDimensions(dArr.length);
            while (i < this.data.length) {
                this.data[i] = (this.data[i] * d) + (dArr[i] * d2);
                i++;
            }
        } else {
            checkVectorDimensions(realVector);
            while (i < this.data.length) {
                this.data[i] = (this.data[i] * d) + (realVector.getEntry(i) * d2);
                i++;
            }
        }
        return this;
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor realVectorPreservingVisitor) {
        realVectorPreservingVisitor.start(this.data.length, 0, this.data.length - 1);
        for (int i = 0; i < this.data.length; i++) {
            realVectorPreservingVisitor.visit(i, this.data[i]);
        }
        return realVectorPreservingVisitor.end();
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor realVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        realVectorPreservingVisitor.start(this.data.length, i, i2);
        while (i <= i2) {
            realVectorPreservingVisitor.visit(i, this.data[i]);
            i++;
        }
        return realVectorPreservingVisitor.end();
    }

    public double walkInOptimizedOrder(RealVectorPreservingVisitor realVectorPreservingVisitor) {
        return walkInDefaultOrder(realVectorPreservingVisitor);
    }

    public double walkInOptimizedOrder(RealVectorPreservingVisitor realVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(realVectorPreservingVisitor, i, i2);
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor realVectorChangingVisitor) {
        realVectorChangingVisitor.start(this.data.length, 0, this.data.length - 1);
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = realVectorChangingVisitor.visit(i, this.data[i]);
        }
        return realVectorChangingVisitor.end();
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor realVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        realVectorChangingVisitor.start(this.data.length, i, i2);
        while (i <= i2) {
            this.data[i] = realVectorChangingVisitor.visit(i, this.data[i]);
            i++;
        }
        return realVectorChangingVisitor.end();
    }

    public double walkInOptimizedOrder(RealVectorChangingVisitor realVectorChangingVisitor) {
        return walkInDefaultOrder(realVectorChangingVisitor);
    }

    public double walkInOptimizedOrder(RealVectorChangingVisitor realVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        return walkInDefaultOrder(realVectorChangingVisitor, i, i2);
    }
}
