package org.apache.commons.math3.util;

import org.apache.commons.math3.exception.DimensionMismatchException;
import org.apache.commons.math3.exception.NotStrictlyPositiveException;
import org.apache.commons.math3.exception.OutOfRangeException;

public class MultidimensionalCounter implements Iterable<Integer> {
    /* access modifiers changed from: private */
    public final int dimension;
    /* access modifiers changed from: private */
    public final int last = (this.dimension - 1);
    /* access modifiers changed from: private */
    public final int[] size;
    private final int totalSize;
    private final int[] uniCounterOffset = new int[this.dimension];

    public class Iterator implements java.util.Iterator<Integer> {
        private int count = -1;
        private final int[] counter = new int[MultidimensionalCounter.this.dimension];

        Iterator() {
            this.counter[MultidimensionalCounter.this.last] = -1;
        }

        public boolean hasNext() {
            for (int i = 0; i < MultidimensionalCounter.this.dimension; i++) {
                if (this.counter[i] != MultidimensionalCounter.this.size[i] - 1) {
                    return true;
                }
            }
            return false;
        }

        public Integer next() {
            int access$100 = MultidimensionalCounter.this.last;
            while (true) {
                if (access$100 >= 0) {
                    if (this.counter[access$100] != MultidimensionalCounter.this.size[access$100] - 1) {
                        int[] iArr = this.counter;
                        iArr[access$100] = iArr[access$100] + 1;
                        break;
                    }
                    this.counter[access$100] = 0;
                    access$100--;
                } else {
                    break;
                }
            }
            int i = this.count + 1;
            this.count = i;
            return Integer.valueOf(i);
        }

        public int getCount() {
            return this.count;
        }

        public int[] getCounts() {
            return MathArrays.copyOf(this.counter);
        }

        public int getCount(int i) {
            return this.counter[i];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public MultidimensionalCounter(int... iArr) throws NotStrictlyPositiveException {
        this.dimension = iArr.length;
        this.size = MathArrays.copyOf(iArr);
        int i = iArr[this.last];
        int i2 = 0;
        while (i2 < this.last) {
            int i3 = i2 + 1;
            int i4 = 1;
            for (int i5 = i3; i5 < this.dimension; i5++) {
                i4 *= iArr[i5];
            }
            this.uniCounterOffset[i2] = i4;
            i *= iArr[i2];
            i2 = i3;
        }
        this.uniCounterOffset[this.last] = 0;
        if (i <= 0) {
            throw new NotStrictlyPositiveException(Integer.valueOf(i));
        }
        this.totalSize = i;
    }

    public Iterator iterator() {
        return new Iterator();
    }

    public int getDimension() {
        return this.dimension;
    }

    public int[] getCounts(int i) throws OutOfRangeException {
        if (i < 0 || i >= this.totalSize) {
            throw new OutOfRangeException(Integer.valueOf(i), Integer.valueOf(0), Integer.valueOf(this.totalSize));
        }
        int[] iArr = new int[this.dimension];
        int i2 = 0;
        for (int i3 = 0; i3 < this.last; i3++) {
            int i4 = this.uniCounterOffset[i3];
            int i5 = 0;
            while (i2 <= i) {
                i2 += i4;
                i5++;
            }
            i2 -= i4;
            iArr[i3] = i5 - 1;
        }
        iArr[this.last] = i - i2;
        return iArr;
    }

    public int getCount(int... iArr) throws OutOfRangeException, DimensionMismatchException {
        if (iArr.length != this.dimension) {
            throw new DimensionMismatchException(iArr.length, this.dimension);
        }
        int i = 0;
        for (int i2 = 0; i2 < this.dimension; i2++) {
            int i3 = iArr[i2];
            if (i3 < 0 || i3 >= this.size[i2]) {
                throw new OutOfRangeException(Integer.valueOf(i3), Integer.valueOf(0), Integer.valueOf(this.size[i2] - 1));
            }
            i += this.uniCounterOffset[i2] * iArr[i2];
        }
        return i + iArr[this.last];
    }

    public int getSize() {
        return this.totalSize;
    }

    public int[] getSizes() {
        return MathArrays.copyOf(this.size);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dimension; i++) {
            sb.append("[");
            sb.append(getCount(i));
            sb.append("]");
        }
        return sb.toString();
    }
}
