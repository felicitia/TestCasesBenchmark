package org.apache.commons.math3.linear;

import java.io.Serializable;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap;
import org.apache.commons.math3.util.OpenIntToDoubleHashMap.Iterator;

@Deprecated
public class OpenMapRealVector extends SparseRealVector implements Serializable {
    public static final double DEFAULT_ZERO_TOLERANCE = 1.0E-12d;
    private static final long serialVersionUID = 8772222695580707260L;
    /* access modifiers changed from: private */
    public final OpenIntToDoubleHashMap entries;
    private final double epsilon;
    private final int virtualSize;

    protected class OpenMapEntry extends Entry {
        private final Iterator iter;

        protected OpenMapEntry(Iterator iterator) {
            super();
            this.iter = iterator;
        }

        public double getValue() {
            return this.iter.value();
        }

        public void setValue(double d) {
            OpenMapRealVector.this.entries.put(this.iter.key(), d);
        }

        public int getIndex() {
            return this.iter.key();
        }
    }

    protected class OpenMapSparseIterator implements java.util.Iterator<Entry> {
        private final Entry current;
        private final Iterator iter;

        protected OpenMapSparseIterator() {
            this.iter = OpenMapRealVector.this.entries.iterator();
            this.current = new OpenMapEntry(this.iter);
        }

        public boolean hasNext() {
            return this.iter.hasNext();
        }

        public Entry next() {
            this.iter.advance();
            return this.current;
        }

        public void remove() {
            throw new UnsupportedOperationException("Not supported");
        }
    }

    public OpenMapRealVector() {
        this(0, 1.0E-12d);
    }

    public OpenMapRealVector(int i) {
        this(i, 1.0E-12d);
    }

    public OpenMapRealVector(int i, double d) {
        this.virtualSize = i;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = d;
    }

    protected OpenMapRealVector(OpenMapRealVector openMapRealVector, int i) {
        this.virtualSize = openMapRealVector.getDimension() + i;
        this.entries = new OpenIntToDoubleHashMap(openMapRealVector.entries);
        this.epsilon = openMapRealVector.epsilon;
    }

    public OpenMapRealVector(int i, int i2) {
        this(i, i2, 1.0E-12d);
    }

    public OpenMapRealVector(int i, int i2, double d) {
        this.virtualSize = i;
        this.entries = new OpenIntToDoubleHashMap(i2, 0.0d);
        this.epsilon = d;
    }

    public OpenMapRealVector(double[] dArr) {
        this(dArr, 1.0E-12d);
    }

    public OpenMapRealVector(double[] dArr, double d) {
        this.virtualSize = dArr.length;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = d;
        for (int i = 0; i < dArr.length; i++) {
            double d2 = dArr[i];
            if (!isDefaultValue(d2)) {
                this.entries.put(i, d2);
            }
        }
    }

    public OpenMapRealVector(Double[] dArr) {
        this(dArr, 1.0E-12d);
    }

    public OpenMapRealVector(Double[] dArr, double d) {
        this.virtualSize = dArr.length;
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = d;
        for (int i = 0; i < dArr.length; i++) {
            double doubleValue = dArr[i].doubleValue();
            if (!isDefaultValue(doubleValue)) {
                this.entries.put(i, doubleValue);
            }
        }
    }

    public OpenMapRealVector(OpenMapRealVector openMapRealVector) {
        this.virtualSize = openMapRealVector.getDimension();
        this.entries = new OpenIntToDoubleHashMap(openMapRealVector.getEntries());
        this.epsilon = openMapRealVector.epsilon;
    }

    public OpenMapRealVector(RealVector realVector) {
        this.virtualSize = realVector.getDimension();
        this.entries = new OpenIntToDoubleHashMap(0.0d);
        this.epsilon = 1.0E-12d;
        for (int i = 0; i < this.virtualSize; i++) {
            double entry = realVector.getEntry(i);
            if (!isDefaultValue(entry)) {
                this.entries.put(i, entry);
            }
        }
    }

    private OpenIntToDoubleHashMap getEntries() {
        return this.entries;
    }

    /* access modifiers changed from: protected */
    public boolean isDefaultValue(double d) {
        return FastMath.abs(d) < this.epsilon;
    }

    public RealVector add(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return add((OpenMapRealVector) realVector);
        }
        return super.add(realVector);
    }

    public OpenMapRealVector add(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        checkVectorDimensions(openMapRealVector.getDimension());
        boolean z = this.entries.size() > openMapRealVector.entries.size();
        OpenMapRealVector copy = z ? copy() : openMapRealVector.copy();
        Iterator it = (z ? openMapRealVector.entries : this.entries).iterator();
        OpenIntToDoubleHashMap openIntToDoubleHashMap = z ? this.entries : openMapRealVector.entries;
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (openIntToDoubleHashMap.containsKey(key)) {
                copy.setEntry(key, openIntToDoubleHashMap.get(key) + it.value());
            } else {
                copy.setEntry(key, it.value());
            }
        }
        return copy;
    }

    public OpenMapRealVector append(OpenMapRealVector openMapRealVector) {
        OpenMapRealVector openMapRealVector2 = new OpenMapRealVector(this, openMapRealVector.getDimension());
        Iterator it = openMapRealVector.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            openMapRealVector2.setEntry(it.key() + this.virtualSize, it.value());
        }
        return openMapRealVector2;
    }

    public OpenMapRealVector append(RealVector realVector) {
        if (realVector instanceof OpenMapRealVector) {
            return append((OpenMapRealVector) realVector);
        }
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this, realVector.getDimension());
        for (int i = 0; i < realVector.getDimension(); i++) {
            openMapRealVector.setEntry(this.virtualSize + i, realVector.getEntry(i));
        }
        return openMapRealVector;
    }

    public OpenMapRealVector append(double d) {
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this, 1);
        openMapRealVector.setEntry(this.virtualSize, d);
        return openMapRealVector;
    }

    public OpenMapRealVector copy() {
        return new OpenMapRealVector(this);
    }

    @Deprecated
    public double dotProduct(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        return dotProduct(openMapRealVector);
    }

    public OpenMapRealVector ebeDivide(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this);
        int dimension = getDimension();
        for (int i = 0; i < dimension; i++) {
            openMapRealVector.setEntry(i, getEntry(i) / realVector.getEntry(i));
        }
        return openMapRealVector;
    }

    public OpenMapRealVector ebeMultiply(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(this);
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            openMapRealVector.setEntry(it.key(), it.value() * realVector.getEntry(it.key()));
        }
        if (realVector.isNaN() || realVector.isInfinite()) {
            int dimension = getDimension();
            for (int i = 0; i < dimension; i++) {
                double entry = realVector.getEntry(i);
                if (Double.isNaN(entry)) {
                    openMapRealVector.setEntry(i, Double.NaN);
                } else if (Double.isInfinite(entry)) {
                    openMapRealVector.setEntry(i, getEntry(i) * entry);
                }
            }
        }
        return openMapRealVector;
    }

    public OpenMapRealVector getSubVector(int i, int i2) throws NotPositiveException, OutOfRangeException {
        checkIndex(i);
        if (i2 < 0) {
            throw new NotPositiveException(LocalizedFormats.NUMBER_OF_ELEMENTS_SHOULD_BE_POSITIVE, Integer.valueOf(i2));
        }
        int i3 = i + i2;
        checkIndex(i3 - 1);
        OpenMapRealVector openMapRealVector = new OpenMapRealVector(i2);
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (key >= i && key < i3) {
                openMapRealVector.setEntry(key - i, it.value());
            }
        }
        return openMapRealVector;
    }

    public int getDimension() {
        return this.virtualSize;
    }

    public double getDistance(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        checkVectorDimensions(openMapRealVector.getDimension());
        Iterator it = this.entries.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            it.advance();
            double value = it.value() - openMapRealVector.getEntry(it.key());
            d += value * value;
        }
        Iterator it2 = openMapRealVector.getEntries().iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (!this.entries.containsKey(it2.key())) {
                double value2 = it2.value();
                d += value2 * value2;
            }
        }
        return FastMath.sqrt(d);
    }

    public double getDistance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return getDistance((OpenMapRealVector) realVector);
        }
        return super.getDistance(realVector);
    }

    public double getEntry(int i) throws OutOfRangeException {
        checkIndex(i);
        return this.entries.get(i);
    }

    public double getL1Distance(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        checkVectorDimensions(openMapRealVector.getDimension());
        Iterator it = this.entries.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            it.advance();
            d += FastMath.abs(it.value() - openMapRealVector.getEntry(it.key()));
        }
        Iterator it2 = openMapRealVector.getEntries().iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (!this.entries.containsKey(it2.key())) {
                d += FastMath.abs(FastMath.abs(it2.value()));
            }
        }
        return d;
    }

    public double getL1Distance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return getL1Distance((OpenMapRealVector) realVector);
        }
        return super.getL1Distance(realVector);
    }

    private double getLInfDistance(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        checkVectorDimensions(openMapRealVector.getDimension());
        Iterator it = this.entries.iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            it.advance();
            double abs = FastMath.abs(it.value() - openMapRealVector.getEntry(it.key()));
            if (abs > d) {
                d = abs;
            }
        }
        Iterator it2 = openMapRealVector.getEntries().iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (!this.entries.containsKey(it2.key()) && it2.value() > d) {
                d = it2.value();
            }
        }
        return d;
    }

    public double getLInfDistance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return getLInfDistance((OpenMapRealVector) realVector);
        }
        return super.getLInfDistance(realVector);
    }

    public boolean isInfinite() {
        Iterator it = this.entries.iterator();
        boolean z = false;
        while (it.hasNext()) {
            it.advance();
            double value = it.value();
            if (Double.isNaN(value)) {
                return false;
            }
            if (Double.isInfinite(value)) {
                z = true;
            }
        }
        return z;
    }

    public boolean isNaN() {
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            if (Double.isNaN(it.value())) {
                return true;
            }
        }
        return false;
    }

    public OpenMapRealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    public OpenMapRealVector mapAddToSelf(double d) {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, getEntry(i) + d);
        }
        return this;
    }

    public void setEntry(int i, double d) throws OutOfRangeException {
        checkIndex(i);
        if (!isDefaultValue(d)) {
            this.entries.put(i, d);
        } else if (this.entries.containsKey(i)) {
            this.entries.remove(i);
        }
    }

    public void setSubVector(int i, RealVector realVector) throws OutOfRangeException {
        checkIndex(i);
        checkIndex((realVector.getDimension() + i) - 1);
        for (int i2 = 0; i2 < realVector.getDimension(); i2++) {
            setEntry(i2 + i, realVector.getEntry(i2));
        }
    }

    public void set(double d) {
        for (int i = 0; i < this.virtualSize; i++) {
            setEntry(i, d);
        }
    }

    public OpenMapRealVector subtract(OpenMapRealVector openMapRealVector) throws DimensionMismatchException {
        checkVectorDimensions(openMapRealVector.getDimension());
        OpenMapRealVector copy = copy();
        Iterator it = openMapRealVector.getEntries().iterator();
        while (it.hasNext()) {
            it.advance();
            int key = it.key();
            if (this.entries.containsKey(key)) {
                copy.setEntry(key, this.entries.get(key) - it.value());
            } else {
                copy.setEntry(key, -it.value());
            }
        }
        return copy;
    }

    public RealVector subtract(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
        if (realVector instanceof OpenMapRealVector) {
            return subtract((OpenMapRealVector) realVector);
        }
        return super.subtract(realVector);
    }

    public OpenMapRealVector unitVector() throws MathArithmeticException {
        OpenMapRealVector copy = copy();
        copy.unitize();
        return copy;
    }

    public void unitize() throws MathArithmeticException {
        double norm = getNorm();
        if (isDefaultValue(norm)) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            this.entries.put(it.key(), it.value() / norm);
        }
    }

    public double[] toArray() {
        double[] dArr = new double[this.virtualSize];
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            dArr[it.key()] = it.value();
        }
        return dArr;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.epsilon);
        int i = ((((int) (doubleToLongBits ^ (doubleToLongBits >>> 32))) + 31) * 31) + this.virtualSize;
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            long doubleToLongBits2 = Double.doubleToLongBits(it.value());
            i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >> 32)));
        }
        return i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof OpenMapRealVector)) {
            return false;
        }
        OpenMapRealVector openMapRealVector = (OpenMapRealVector) obj;
        if (this.virtualSize != openMapRealVector.virtualSize || Double.doubleToLongBits(this.epsilon) != Double.doubleToLongBits(openMapRealVector.epsilon)) {
            return false;
        }
        Iterator it = this.entries.iterator();
        while (it.hasNext()) {
            it.advance();
            if (Double.doubleToLongBits(openMapRealVector.getEntry(it.key())) != Double.doubleToLongBits(it.value())) {
                return false;
            }
        }
        Iterator it2 = openMapRealVector.getEntries().iterator();
        while (it2.hasNext()) {
            it2.advance();
            if (Double.doubleToLongBits(it2.value()) != Double.doubleToLongBits(getEntry(it2.key()))) {
                return false;
            }
        }
        return true;
    }

    public double getSparsity() {
        return ((double) this.entries.size()) / ((double) getDimension());
    }

    public java.util.Iterator<Entry> sparseIterator() {
        return new OpenMapSparseIterator();
    }
}
