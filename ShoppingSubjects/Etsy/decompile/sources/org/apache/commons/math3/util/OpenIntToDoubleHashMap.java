package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class OpenIntToDoubleHashMap implements Serializable {
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int PERTURB_SHIFT = 5;
    protected static final byte REMOVED = 2;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final long serialVersionUID = -3646337053166149105L;
    /* access modifiers changed from: private */
    public transient int count;
    /* access modifiers changed from: private */
    public int[] keys;
    private int mask;
    private final double missingEntries;
    private int size;
    /* access modifiers changed from: private */
    public byte[] states;
    /* access modifiers changed from: private */
    public double[] values;

    public class Iterator {
        private int current;
        private int next;
        private final int referenceCount;

        private Iterator() {
            this.referenceCount = OpenIntToDoubleHashMap.this.count;
            this.next = -1;
            try {
                advance();
            } catch (NoSuchElementException unused) {
            }
        }

        public boolean hasNext() {
            return this.next >= 0;
        }

        public int key() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            } else if (this.current >= 0) {
                return OpenIntToDoubleHashMap.this.keys[this.current];
            } else {
                throw new NoSuchElementException();
            }
        }

        public double value() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            } else if (this.current >= 0) {
                return OpenIntToDoubleHashMap.this.values[this.current];
            } else {
                throw new NoSuchElementException();
            }
        }

        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            byte[] access$400;
            int i;
            if (this.referenceCount != OpenIntToDoubleHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            this.current = this.next;
            do {
                try {
                    access$400 = OpenIntToDoubleHashMap.this.states;
                    i = this.next + 1;
                    this.next = i;
                } catch (ArrayIndexOutOfBoundsException unused) {
                    this.next = -2;
                    if (this.current < 0) {
                        throw new NoSuchElementException();
                    }
                    return;
                }
            } while (access$400[i] != 1);
        }
    }

    private static int changeIndexSign(int i) {
        return (-i) - 1;
    }

    private static int hashOf(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    private static int perturb(int i) {
        return i & Integer.MAX_VALUE;
    }

    private static int probe(int i, int i2) {
        return (i2 << 2) + i2 + i + 1;
    }

    public OpenIntToDoubleHashMap() {
        this(16, Double.NaN);
    }

    public OpenIntToDoubleHashMap(double d) {
        this(16, d);
    }

    public OpenIntToDoubleHashMap(int i) {
        this(i, Double.NaN);
    }

    public OpenIntToDoubleHashMap(int i, double d) {
        int computeCapacity = computeCapacity(i);
        this.keys = new int[computeCapacity];
        this.values = new double[computeCapacity];
        this.states = new byte[computeCapacity];
        this.missingEntries = d;
        this.mask = computeCapacity - 1;
    }

    public OpenIntToDoubleHashMap(OpenIntToDoubleHashMap openIntToDoubleHashMap) {
        int length = openIntToDoubleHashMap.keys.length;
        this.keys = new int[length];
        System.arraycopy(openIntToDoubleHashMap.keys, 0, this.keys, 0, length);
        this.values = new double[length];
        System.arraycopy(openIntToDoubleHashMap.values, 0, this.values, 0, length);
        this.states = new byte[length];
        System.arraycopy(openIntToDoubleHashMap.states, 0, this.states, 0, length);
        this.missingEntries = openIntToDoubleHashMap.missingEntries;
        this.size = openIntToDoubleHashMap.size;
        this.mask = openIntToDoubleHashMap.mask;
        this.count = openIntToDoubleHashMap.count;
    }

    private static int computeCapacity(int i) {
        if (i == 0) {
            return 1;
        }
        int ceil = (int) FastMath.ceil((double) (((float) i) / LOAD_FACTOR));
        if (Integer.highestOneBit(ceil) == ceil) {
            return ceil;
        }
        return nextPowerOfTwo(ceil);
    }

    private static int nextPowerOfTwo(int i) {
        return Integer.highestOneBit(i) << 1;
    }

    public double get(int i) {
        int hashOf = hashOf(i);
        int i2 = this.mask & hashOf;
        if (containsKey(i, i2)) {
            return this.values[i2];
        }
        if (this.states[i2] == 0) {
            return this.missingEntries;
        }
        int perturb = perturb(hashOf);
        int i3 = i2;
        while (this.states[i2] != 0) {
            i3 = probe(perturb, i3);
            i2 = this.mask & i3;
            if (containsKey(i, i2)) {
                return this.values[i2];
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    public boolean containsKey(int i) {
        int hashOf = hashOf(i);
        int i2 = this.mask & hashOf;
        if (containsKey(i, i2)) {
            return true;
        }
        if (this.states[i2] == 0) {
            return false;
        }
        int perturb = perturb(hashOf);
        int i3 = i2;
        while (this.states[i2] != 0) {
            i3 = probe(perturb, i3);
            i2 = this.mask & i3;
            if (containsKey(i, i2)) {
                return true;
            }
            perturb >>= 5;
        }
        return false;
    }

    public Iterator iterator() {
        return new Iterator();
    }

    private int findInsertionIndex(int i) {
        return findInsertionIndex(this.keys, this.states, i, this.mask);
    }

    private static int findInsertionIndex(int[] iArr, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int hashOf = hashOf(i);
        int i6 = hashOf & i2;
        if (bArr[i6] == 0) {
            return i6;
        }
        if (bArr[i6] == 1 && iArr[i6] == i) {
            return changeIndexSign(i6);
        }
        int perturb = perturb(hashOf);
        if (bArr[i6] == 1) {
            do {
                i6 = probe(perturb, i6);
                i5 = i6 & i2;
                perturb >>= 5;
                if (bArr[i5] != 1) {
                    break;
                }
            } while (iArr[i5] != i);
            int i7 = i5;
            i3 = perturb;
            i4 = i6;
            i6 = i7;
        } else {
            i3 = perturb;
            i4 = i6;
        }
        if (bArr[i6] == 0) {
            return i6;
        }
        if (bArr[i6] == 1) {
            return changeIndexSign(i6);
        }
        while (true) {
            i4 = probe(i3, i4);
            int i8 = i4 & i2;
            if (bArr[i8] == 0) {
                return i6;
            }
            if (bArr[i8] == 1 && iArr[i8] == i) {
                return changeIndexSign(i8);
            }
            i3 >>= 5;
        }
    }

    public int size() {
        return this.size;
    }

    public double remove(int i) {
        int hashOf = hashOf(i);
        int i2 = this.mask & hashOf;
        if (containsKey(i, i2)) {
            return doRemove(i2);
        }
        if (this.states[i2] == 0) {
            return this.missingEntries;
        }
        int perturb = perturb(hashOf);
        int i3 = i2;
        while (this.states[i2] != 0) {
            i3 = probe(perturb, i3);
            i2 = this.mask & i3;
            if (containsKey(i, i2)) {
                return doRemove(i2);
            }
            perturb >>= 5;
        }
        return this.missingEntries;
    }

    private boolean containsKey(int i, int i2) {
        return (i != 0 || this.states[i2] == 1) && this.keys[i2] == i;
    }

    private double doRemove(int i) {
        this.keys[i] = 0;
        this.states[i] = 2;
        double d = this.values[i];
        this.values[i] = this.missingEntries;
        this.size--;
        this.count++;
        return d;
    }

    public double put(int i, double d) {
        double d2;
        boolean z;
        int findInsertionIndex = findInsertionIndex(i);
        double d3 = this.missingEntries;
        if (findInsertionIndex < 0) {
            findInsertionIndex = changeIndexSign(findInsertionIndex);
            d2 = this.values[findInsertionIndex];
            z = false;
        } else {
            d2 = d3;
            z = true;
        }
        this.keys[findInsertionIndex] = i;
        this.states[findInsertionIndex] = 1;
        this.values[findInsertionIndex] = d;
        if (z) {
            this.size++;
            if (shouldGrowTable()) {
                growTable();
            }
            this.count++;
        }
        return d2;
    }

    private void growTable() {
        int length = this.states.length;
        int[] iArr = this.keys;
        double[] dArr = this.values;
        byte[] bArr = this.states;
        int i = 2 * length;
        int[] iArr2 = new int[i];
        double[] dArr2 = new double[i];
        byte[] bArr2 = new byte[i];
        int i2 = i - 1;
        for (int i3 = 0; i3 < length; i3++) {
            if (bArr[i3] == 1) {
                int i4 = iArr[i3];
                int findInsertionIndex = findInsertionIndex(iArr2, bArr2, i4, i2);
                iArr2[findInsertionIndex] = i4;
                dArr2[findInsertionIndex] = dArr[i3];
                bArr2[findInsertionIndex] = 1;
            }
        }
        this.mask = i2;
        this.keys = iArr2;
        this.values = dArr2;
        this.states = bArr2;
    }

    private boolean shouldGrowTable() {
        return ((float) this.size) > ((float) (this.mask + 1)) * LOAD_FACTOR;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count = 0;
    }
}
