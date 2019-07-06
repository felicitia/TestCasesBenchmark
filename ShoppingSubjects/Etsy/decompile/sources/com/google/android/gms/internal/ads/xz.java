package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class xz extends wa<Long> implements xm<Long>, RandomAccess {
    private static final xz a;
    private long[] b;
    private int c;

    static {
        xz xzVar = new xz();
        a = xzVar;
        xzVar.b();
    }

    xz() {
        this(new long[10], 0);
    }

    private xz(long[] jArr, int i) {
        this.b = jArr;
        this.c = i;
    }

    private final void a(int i, long j) {
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
        this.b[i] = j;
        this.c++;
        this.modCount++;
    }

    private final void c(int i) {
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
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

    public final /* synthetic */ xm a(int i) {
        if (i >= this.c) {
            return new xz(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final void a(long j) {
        a(this.c, j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        a(i, ((Long) obj).longValue());
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        c();
        xj.a(collection);
        if (!(collection instanceof xz)) {
            return super.addAll(collection);
        }
        xz xzVar = (xz) collection;
        if (xzVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < xzVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + xzVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(xzVar.b, 0, this.b, this.c, xzVar.c);
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
        if (!(obj instanceof xz)) {
            return super.equals(obj);
        }
        xz xzVar = (xz) obj;
        if (this.c != xzVar.c) {
            return false;
        }
        long[] jArr = xzVar.b;
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
            i = (i * 31) + xj.a(this.b[i2]);
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
