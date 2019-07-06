package org.apache.commons.math3.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.apache.commons.math3.analysis.FunctionUtils;
import org.apache.commons.math3.analysis.UnivariateFunction;
import org.apache.commons.math3.analysis.function.Add;
import org.apache.commons.math3.analysis.function.Divide;
import org.apache.commons.math3.analysis.function.Multiply;
import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.exception.MathUnsupportedOperationException;
import org.apache.commons.math3.exception.NotPositiveException;
import org.apache.commons.math3.exception.NumberIsTooSmallException;
import org.apache.commons.math3.exception.OutOfRangeException;
import org.apache.commons.math3.exception.util.LocalizedFormats;
import org.apache.commons.math3.util.FastMath;

public abstract class RealVector {

    protected class Entry {
        private int index;

        public Entry() {
            setIndex(0);
        }

        public double getValue() {
            return RealVector.this.getEntry(getIndex());
        }

        public void setValue(double d) {
            RealVector.this.setEntry(getIndex(), d);
        }

        public int getIndex() {
            return this.index;
        }

        public void setIndex(int i) {
            this.index = i;
        }
    }

    @Deprecated
    protected class SparseEntryIterator implements Iterator<Entry> {
        private Entry current;
        private final int dim;
        private Entry next;

        protected SparseEntryIterator() {
            this.dim = RealVector.this.getDimension();
            this.current = new Entry();
            this.next = new Entry();
            if (this.next.getValue() == 0.0d) {
                advance(this.next);
            }
        }

        /* access modifiers changed from: protected */
        public void advance(Entry entry) {
            if (entry != null) {
                do {
                    entry.setIndex(entry.getIndex() + 1);
                    if (entry.getIndex() >= this.dim) {
                        break;
                    }
                } while (entry.getValue() == 0.0d);
                if (entry.getIndex() >= this.dim) {
                    entry.setIndex(-1);
                }
            }
        }

        public boolean hasNext() {
            return this.next.getIndex() >= 0;
        }

        public Entry next() {
            int index = this.next.getIndex();
            if (index < 0) {
                throw new NoSuchElementException();
            }
            this.current.setIndex(index);
            advance(this.next);
            return this.current;
        }

        public void remove() throws MathUnsupportedOperationException {
            throw new MathUnsupportedOperationException();
        }
    }

    public abstract RealVector append(double d);

    public abstract RealVector append(RealVector realVector);

    public abstract RealVector copy();

    @Deprecated
    public abstract RealVector ebeDivide(RealVector realVector) throws DimensionMismatchException;

    @Deprecated
    public abstract RealVector ebeMultiply(RealVector realVector) throws DimensionMismatchException;

    public abstract int getDimension();

    public abstract double getEntry(int i) throws OutOfRangeException;

    public abstract RealVector getSubVector(int i, int i2) throws NotPositiveException, OutOfRangeException;

    public abstract boolean isInfinite();

    public abstract boolean isNaN();

    public abstract void setEntry(int i, double d) throws OutOfRangeException;

    public abstract void setSubVector(int i, RealVector realVector) throws OutOfRangeException;

    public void addToEntry(int i, double d) throws OutOfRangeException {
        setEntry(i, getEntry(i) + d);
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector.getDimension());
    }

    /* access modifiers changed from: protected */
    public void checkVectorDimensions(int i) throws DimensionMismatchException {
        int dimension = getDimension();
        if (dimension != i) {
            throw new DimensionMismatchException(dimension, i);
        }
    }

    /* access modifiers changed from: protected */
    public void checkIndex(int i) throws OutOfRangeException {
        if (i < 0 || i >= getDimension()) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(getDimension() - 1));
        }
    }

    /* access modifiers changed from: protected */
    public void checkIndices(int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        int dimension = getDimension();
        if (i < 0 || i >= dimension) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(dimension - 1));
        } else if (i2 < 0 || i2 >= dimension) {
            throw new OutOfRangeException(LocalizedFormats.INDEX, Integer.valueOf(i2), Integer.valueOf(0), Integer.valueOf(dimension - 1));
        } else if (i2 < i) {
            throw new NumberIsTooSmallException(LocalizedFormats.INITIAL_ROW_AFTER_FINAL_ROW, Integer.valueOf(i2), Integer.valueOf(i), false);
        }
    }

    public RealVector add(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        RealVector copy = realVector.copy();
        Iterator it = iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int index = entry.getIndex();
            copy.setEntry(index, entry.getValue() + copy.getEntry(index));
        }
        return copy;
    }

    public RealVector subtract(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        RealVector mapMultiply = realVector.mapMultiply(-1.0d);
        Iterator it = iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            int index = entry.getIndex();
            mapMultiply.setEntry(index, entry.getValue() + mapMultiply.getEntry(index));
        }
        return mapMultiply;
    }

    public RealVector mapAdd(double d) {
        return copy().mapAddToSelf(d);
    }

    public RealVector mapAddToSelf(double d) {
        return d != 0.0d ? mapToSelf(FunctionUtils.fix2ndArgument(new Add(), d)) : this;
    }

    public double dotProduct(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        double d = 0.0d;
        for (int i = 0; i < getDimension(); i++) {
            d += getEntry(i) * realVector.getEntry(i);
        }
        return d;
    }

    public double cosine(RealVector realVector) throws DimensionMismatchException, MathArithmeticException {
        double norm = getNorm();
        double norm2 = realVector.getNorm();
        if (norm != 0.0d && norm2 != 0.0d) {
            return dotProduct(realVector) / (norm * norm2);
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public double getDistance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        Iterator it = iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            double value = entry.getValue() - realVector.getEntry(entry.getIndex());
            d += value * value;
        }
        return FastMath.sqrt(d);
    }

    public double getNorm() {
        Iterator it = iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            double value = ((Entry) it.next()).getValue();
            d += value * value;
        }
        return FastMath.sqrt(d);
    }

    public double getL1Norm() {
        Iterator it = iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            d += FastMath.abs(((Entry) it.next()).getValue());
        }
        return d;
    }

    public double getLInfNorm() {
        Iterator it = iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            d = FastMath.max(d, FastMath.abs(((Entry) it.next()).getValue()));
        }
        return d;
    }

    public double getL1Distance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        Iterator it = iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            d += FastMath.abs(entry.getValue() - realVector.getEntry(entry.getIndex()));
        }
        return d;
    }

    public double getLInfDistance(RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        Iterator it = iterator();
        double d = 0.0d;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            d = FastMath.max(FastMath.abs(entry.getValue() - realVector.getEntry(entry.getIndex())), d);
        }
        return d;
    }

    public int getMinIndex() {
        Iterator it = iterator();
        int i = -1;
        double d = Double.POSITIVE_INFINITY;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (entry.getValue() <= d) {
                i = entry.getIndex();
                d = entry.getValue();
            }
        }
        return i;
    }

    public double getMinValue() {
        int minIndex = getMinIndex();
        if (minIndex < 0) {
            return Double.NaN;
        }
        return getEntry(minIndex);
    }

    public int getMaxIndex() {
        Iterator it = iterator();
        int i = -1;
        double d = Double.NEGATIVE_INFINITY;
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            if (entry.getValue() >= d) {
                i = entry.getIndex();
                d = entry.getValue();
            }
        }
        return i;
    }

    public double getMaxValue() {
        int maxIndex = getMaxIndex();
        if (maxIndex < 0) {
            return Double.NaN;
        }
        return getEntry(maxIndex);
    }

    public RealVector mapMultiply(double d) {
        return copy().mapMultiplyToSelf(d);
    }

    public RealVector mapMultiplyToSelf(double d) {
        return mapToSelf(FunctionUtils.fix2ndArgument(new Multiply(), d));
    }

    public RealVector mapSubtract(double d) {
        return copy().mapSubtractToSelf(d);
    }

    public RealVector mapSubtractToSelf(double d) {
        return mapAddToSelf(-d);
    }

    public RealVector mapDivide(double d) {
        return copy().mapDivideToSelf(d);
    }

    public RealVector mapDivideToSelf(double d) {
        return mapToSelf(FunctionUtils.fix2ndArgument(new Divide(), d));
    }

    public RealMatrix outerProduct(RealVector realVector) {
        RealMatrix realMatrix;
        int dimension = getDimension();
        int dimension2 = realVector.getDimension();
        if ((realVector instanceof SparseRealVector) || (this instanceof SparseRealVector)) {
            realMatrix = new OpenMapRealMatrix(dimension, dimension2);
        } else {
            realMatrix = new Array2DRowRealMatrix(dimension, dimension2);
        }
        for (int i = 0; i < dimension; i++) {
            for (int i2 = 0; i2 < dimension2; i2++) {
                realMatrix.setEntry(i, i2, getEntry(i) * realVector.getEntry(i2));
            }
        }
        return realMatrix;
    }

    public RealVector projection(RealVector realVector) throws DimensionMismatchException, MathArithmeticException {
        if (realVector.dotProduct(realVector) != 0.0d) {
            return realVector.mapMultiply(dotProduct(realVector) / realVector.dotProduct(realVector));
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public void set(double d) {
        Iterator it = iterator();
        while (it.hasNext()) {
            ((Entry) it.next()).setValue(d);
        }
    }

    public double[] toArray() {
        int dimension = getDimension();
        double[] dArr = new double[dimension];
        for (int i = 0; i < dimension; i++) {
            dArr[i] = getEntry(i);
        }
        return dArr;
    }

    public RealVector unitVector() throws MathArithmeticException {
        double norm = getNorm();
        if (norm != 0.0d) {
            return mapDivide(norm);
        }
        throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
    }

    public void unitize() throws MathArithmeticException {
        if (getNorm() == 0.0d) {
            throw new MathArithmeticException(LocalizedFormats.ZERO_NORM, new Object[0]);
        }
        mapDivideToSelf(getNorm());
    }

    @Deprecated
    public Iterator<Entry> sparseIterator() {
        return new SparseEntryIterator();
    }

    public Iterator<Entry> iterator() {
        final int dimension = getDimension();
        return new Iterator<Entry>() {
            private Entry e = new Entry();
            private int i = 0;

            public boolean hasNext() {
                return this.i < dimension;
            }

            public Entry next() {
                if (this.i < dimension) {
                    Entry entry = this.e;
                    int i2 = this.i;
                    this.i = i2 + 1;
                    entry.setIndex(i2);
                    return this.e;
                }
                throw new NoSuchElementException();
            }

            public void remove() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }
        };
    }

    public RealVector map(UnivariateFunction univariateFunction) {
        return copy().mapToSelf(univariateFunction);
    }

    public RealVector mapToSelf(UnivariateFunction univariateFunction) {
        Iterator it = iterator();
        while (it.hasNext()) {
            Entry entry = (Entry) it.next();
            entry.setValue(univariateFunction.value(entry.getValue()));
        }
        return this;
    }

    public RealVector combine(double d, double d2, RealVector realVector) throws DimensionMismatchException {
        return copy().combineToSelf(d, d2, realVector);
    }

    public RealVector combineToSelf(double d, double d2, RealVector realVector) throws DimensionMismatchException {
        checkVectorDimensions(realVector);
        for (int i = 0; i < getDimension(); i++) {
            setEntry(i, (getEntry(i) * d) + (realVector.getEntry(i) * d2));
        }
        return this;
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor realVectorPreservingVisitor) {
        int dimension = getDimension();
        realVectorPreservingVisitor.start(dimension, 0, dimension - 1);
        for (int i = 0; i < dimension; i++) {
            realVectorPreservingVisitor.visit(i, getEntry(i));
        }
        return realVectorPreservingVisitor.end();
    }

    public double walkInDefaultOrder(RealVectorPreservingVisitor realVectorPreservingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        realVectorPreservingVisitor.start(getDimension(), i, i2);
        while (i <= i2) {
            realVectorPreservingVisitor.visit(i, getEntry(i));
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
        int dimension = getDimension();
        realVectorChangingVisitor.start(dimension, 0, dimension - 1);
        for (int i = 0; i < dimension; i++) {
            setEntry(i, realVectorChangingVisitor.visit(i, getEntry(i)));
        }
        return realVectorChangingVisitor.end();
    }

    public double walkInDefaultOrder(RealVectorChangingVisitor realVectorChangingVisitor, int i, int i2) throws NumberIsTooSmallException, OutOfRangeException {
        checkIndices(i, i2);
        realVectorChangingVisitor.start(getDimension(), i, i2);
        while (i <= i2) {
            setEntry(i, realVectorChangingVisitor.visit(i, getEntry(i)));
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

    public boolean equals(Object obj) throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    public int hashCode() throws MathUnsupportedOperationException {
        throw new MathUnsupportedOperationException();
    }

    public static RealVector unmodifiableRealVector(RealVector realVector) {
        return new RealVector(realVector) {
            final /* synthetic */ RealVector val$v;

            /* renamed from: org.apache.commons.math3.linear.RealVector$2$UnmodifiableEntry */
            class UnmodifiableEntry extends Entry {
                UnmodifiableEntry() {
                    super();
                }

                public double getValue() {
                    return AnonymousClass2.this.val$v.getEntry(getIndex());
                }

                public void setValue(double d) throws MathUnsupportedOperationException {
                    throw new MathUnsupportedOperationException();
                }
            }

            {
                this.val$v = r1;
            }

            public RealVector mapToSelf(UnivariateFunction univariateFunction) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public RealVector map(UnivariateFunction univariateFunction) {
                return this.val$v.map(univariateFunction);
            }

            public Iterator<Entry> iterator() {
                final Iterator it = this.val$v.iterator();
                return new Iterator<Entry>() {
                    private final UnmodifiableEntry e = new UnmodifiableEntry();

                    public boolean hasNext() {
                        return it.hasNext();
                    }

                    public Entry next() {
                        this.e.setIndex(((Entry) it.next()).getIndex());
                        return this.e;
                    }

                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            public Iterator<Entry> sparseIterator() {
                final Iterator sparseIterator = this.val$v.sparseIterator();
                return new Iterator<Entry>() {
                    private final UnmodifiableEntry e = new UnmodifiableEntry();

                    public boolean hasNext() {
                        return sparseIterator.hasNext();
                    }

                    public Entry next() {
                        this.e.setIndex(((Entry) sparseIterator.next()).getIndex());
                        return this.e;
                    }

                    public void remove() throws MathUnsupportedOperationException {
                        throw new MathUnsupportedOperationException();
                    }
                };
            }

            public RealVector copy() {
                return this.val$v.copy();
            }

            public RealVector add(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.add(realVector);
            }

            public RealVector subtract(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.subtract(realVector);
            }

            public RealVector mapAdd(double d) {
                return this.val$v.mapAdd(d);
            }

            public RealVector mapAddToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public RealVector mapSubtract(double d) {
                return this.val$v.mapSubtract(d);
            }

            public RealVector mapSubtractToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public RealVector mapMultiply(double d) {
                return this.val$v.mapMultiply(d);
            }

            public RealVector mapMultiplyToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public RealVector mapDivide(double d) {
                return this.val$v.mapDivide(d);
            }

            public RealVector mapDivideToSelf(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public RealVector ebeMultiply(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.ebeMultiply(realVector);
            }

            public RealVector ebeDivide(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.ebeDivide(realVector);
            }

            public double dotProduct(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.dotProduct(realVector);
            }

            public double cosine(RealVector realVector) throws DimensionMismatchException, MathArithmeticException {
                return this.val$v.cosine(realVector);
            }

            public double getNorm() {
                return this.val$v.getNorm();
            }

            public double getL1Norm() {
                return this.val$v.getL1Norm();
            }

            public double getLInfNorm() {
                return this.val$v.getLInfNorm();
            }

            public double getDistance(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.getDistance(realVector);
            }

            public double getL1Distance(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.getL1Distance(realVector);
            }

            public double getLInfDistance(RealVector realVector) throws DimensionMismatchException {
                return this.val$v.getLInfDistance(realVector);
            }

            public RealVector unitVector() throws MathArithmeticException {
                return this.val$v.unitVector();
            }

            public void unitize() throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public RealMatrix outerProduct(RealVector realVector) {
                return this.val$v.outerProduct(realVector);
            }

            public double getEntry(int i) throws OutOfRangeException {
                return this.val$v.getEntry(i);
            }

            public void setEntry(int i, double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public void addToEntry(int i, double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public int getDimension() {
                return this.val$v.getDimension();
            }

            public RealVector append(RealVector realVector) {
                return this.val$v.append(realVector);
            }

            public RealVector append(double d) {
                return this.val$v.append(d);
            }

            public RealVector getSubVector(int i, int i2) throws OutOfRangeException, NotPositiveException {
                return this.val$v.getSubVector(i, i2);
            }

            public void setSubVector(int i, RealVector realVector) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public void set(double d) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }

            public double[] toArray() {
                return this.val$v.toArray();
            }

            public boolean isNaN() {
                return this.val$v.isNaN();
            }

            public boolean isInfinite() {
                return this.val$v.isInfinite();
            }

            public RealVector combine(double d, double d2, RealVector realVector) throws DimensionMismatchException {
                return this.val$v.combine(d, d2, realVector);
            }

            public RealVector combineToSelf(double d, double d2, RealVector realVector) throws MathUnsupportedOperationException {
                throw new MathUnsupportedOperationException();
            }
        };
    }
}
