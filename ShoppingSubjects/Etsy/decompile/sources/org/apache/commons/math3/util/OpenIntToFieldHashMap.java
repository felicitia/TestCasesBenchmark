package org.apache.commons.math3.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;
import org.apache.commons.math3.Field;
import org.apache.commons.math3.FieldElement;

public class OpenIntToFieldHashMap<T extends FieldElement<T>> implements Serializable {
    private static final int DEFAULT_EXPECTED_SIZE = 16;
    protected static final byte FREE = 0;
    protected static final byte FULL = 1;
    private static final float LOAD_FACTOR = 0.5f;
    private static final int PERTURB_SHIFT = 5;
    protected static final byte REMOVED = 2;
    private static final int RESIZE_MULTIPLIER = 2;
    private static final long serialVersionUID = -9179080286849120720L;
    /* access modifiers changed from: private */
    public transient int count;
    private final Field<T> field;
    /* access modifiers changed from: private */
    public int[] keys;
    private int mask;
    private final T missingEntries;
    private int size;
    /* access modifiers changed from: private */
    public byte[] states;
    /* access modifiers changed from: private */
    public T[] values;

    public class Iterator {
        private int current;
        private int next;
        private final int referenceCount;

        private Iterator() {
            this.referenceCount = OpenIntToFieldHashMap.this.count;
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
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            } else if (this.current >= 0) {
                return OpenIntToFieldHashMap.this.keys[this.current];
            } else {
                throw new NoSuchElementException();
            }
        }

        public T value() throws ConcurrentModificationException, NoSuchElementException {
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            } else if (this.current >= 0) {
                return OpenIntToFieldHashMap.this.values[this.current];
            } else {
                throw new NoSuchElementException();
            }
        }

        public void advance() throws ConcurrentModificationException, NoSuchElementException {
            byte[] access$400;
            int i;
            if (this.referenceCount != OpenIntToFieldHashMap.this.count) {
                throw new ConcurrentModificationException();
            }
            this.current = this.next;
            do {
                try {
                    access$400 = OpenIntToFieldHashMap.this.states;
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

    public OpenIntToFieldHashMap(Field<T> field2) {
        this(field2, 16, (FieldElement) field2.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field2, T t) {
        this(field2, 16, t);
    }

    public OpenIntToFieldHashMap(Field<T> field2, int i) {
        this(field2, i, (FieldElement) field2.getZero());
    }

    public OpenIntToFieldHashMap(Field<T> field2, int i, T t) {
        this.field = field2;
        int computeCapacity = computeCapacity(i);
        this.keys = new int[computeCapacity];
        this.values = buildArray(computeCapacity);
        this.states = new byte[computeCapacity];
        this.missingEntries = t;
        this.mask = computeCapacity - 1;
    }

    public OpenIntToFieldHashMap(OpenIntToFieldHashMap<T> openIntToFieldHashMap) {
        this.field = openIntToFieldHashMap.field;
        int length = openIntToFieldHashMap.keys.length;
        this.keys = new int[length];
        System.arraycopy(openIntToFieldHashMap.keys, 0, this.keys, 0, length);
        this.values = buildArray(length);
        System.arraycopy(openIntToFieldHashMap.values, 0, this.values, 0, length);
        this.states = new byte[length];
        System.arraycopy(openIntToFieldHashMap.states, 0, this.states, 0, length);
        this.missingEntries = openIntToFieldHashMap.missingEntries;
        this.size = openIntToFieldHashMap.size;
        this.mask = openIntToFieldHashMap.mask;
        this.count = openIntToFieldHashMap.count;
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

    public T get(int i) {
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
        return new Iterator<>();
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

    public T remove(int i) {
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

    private T doRemove(int i) {
        this.keys[i] = 0;
        this.states[i] = 2;
        T t = this.values[i];
        this.values[i] = this.missingEntries;
        this.size--;
        this.count++;
        return t;
    }

    public T put(int i, T t) {
        boolean z;
        int findInsertionIndex = findInsertionIndex(i);
        T t2 = this.missingEntries;
        if (findInsertionIndex < 0) {
            findInsertionIndex = changeIndexSign(findInsertionIndex);
            t2 = this.values[findInsertionIndex];
            z = false;
        } else {
            z = true;
        }
        this.keys[findInsertionIndex] = i;
        this.states[findInsertionIndex] = 1;
        this.values[findInsertionIndex] = t;
        if (z) {
            this.size++;
            if (shouldGrowTable()) {
                growTable();
            }
            this.count++;
        }
        return t2;
    }

    private void growTable() {
        int length = this.states.length;
        int[] iArr = this.keys;
        T[] tArr = this.values;
        byte[] bArr = this.states;
        int i = 2 * length;
        int[] iArr2 = new int[i];
        T[] buildArray = buildArray(i);
        byte[] bArr2 = new byte[i];
        int i2 = i - 1;
        for (int i3 = 0; i3 < length; i3++) {
            if (bArr[i3] == 1) {
                int i4 = iArr[i3];
                int findInsertionIndex = findInsertionIndex(iArr2, bArr2, i4, i2);
                iArr2[findInsertionIndex] = i4;
                buildArray[findInsertionIndex] = tArr[i3];
                bArr2[findInsertionIndex] = 1;
            }
        }
        this.mask = i2;
        this.keys = iArr2;
        this.values = buildArray;
        this.states = bArr2;
    }

    private boolean shouldGrowTable() {
        return ((float) this.size) > ((float) (this.mask + 1)) * LOAD_FACTOR;
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.count = 0;
    }

    private T[] buildArray(int i) {
        return (FieldElement[]) Array.newInstance(this.field.getRuntimeClass(), i);
    }
}
