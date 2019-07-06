package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class bb extends h<Long> implements ao<Long>, RandomAccess {
    private static final bb a;
    private long[] b;
    private int c;

    static {
        bb bbVar = new bb();
        a = bbVar;
        bbVar.b();
    }

    bb() {
        this(new long[10], 0);
    }

    private bb(long[] jArr, int i) {
        this.b = jArr;
        this.c = i;
    }

    private final void c(int i) {
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
    }

    public static bb d() {
        return a;
    }

    private final String d(int i) {
        int i2 = this.c;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ ao a(int i) {
        if (i >= this.c) {
            return new bb(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            long[] jArr = new long[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, jArr, 0, i);
            System.arraycopy(this.b, i, jArr, i + 1, this.c - i);
            this.b = jArr;
        }
        this.b[i] = longValue;
        this.c++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        c();
        aj.a(collection);
        if (!(collection instanceof bb)) {
            return super.addAll(collection);
        }
        bb bbVar = (bb) collection;
        if (bbVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < bbVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + bbVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(bbVar.b, 0, this.b, this.c, bbVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final long b(int i) {
        c(i);
        return this.b[i];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof bb)) {
            return super.equals(obj);
        }
        bb bbVar = (bb) obj;
        if (this.c != bbVar.c) {
            return false;
        }
        long[] jArr = bbVar.b;
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(b(i));
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + aj.a(this.b[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        c(i);
        long j = this.b[i];
        if (i < this.c - 1) {
            System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
        }
        this.c--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.c; i++) {
            if (obj.equals(Long.valueOf(this.b[i]))) {
                System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
                this.c--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        c();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.b, i2, this.b, i, this.c - i2);
        this.c -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        long longValue = ((Long) obj).longValue();
        c();
        c(i);
        long j = this.b[i];
        this.b[i] = longValue;
        return Long.valueOf(j);
    }

    public final int size() {
        return this.c;
    }
}
