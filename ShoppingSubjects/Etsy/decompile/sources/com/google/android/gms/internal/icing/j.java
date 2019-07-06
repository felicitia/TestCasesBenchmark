package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class j extends h<Boolean> implements ao<Boolean>, RandomAccess {
    private static final j a;
    private boolean[] b;
    private int c;

    static {
        j jVar = new j();
        a = jVar;
        jVar.b();
    }

    j() {
        this(new boolean[10], 0);
    }

    private j(boolean[] zArr, int i) {
        this.b = zArr;
        this.c = i;
    }

    private final void b(int i) {
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException(c(i));
        }
    }

    private final String c(int i) {
        int i2 = this.c;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public static j d() {
        return a;
    }

    public final /* synthetic */ ao a(int i) {
        if (i >= this.c) {
            return new j(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        boolean booleanValue = ((Boolean) obj).booleanValue();
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(c(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            boolean[] zArr = new boolean[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, zArr, 0, i);
            System.arraycopy(this.b, i, zArr, i + 1, this.c - i);
            this.b = zArr;
        }
        this.b[i] = booleanValue;
        this.c++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        c();
        aj.a(collection);
        if (!(collection instanceof j)) {
            return super.addAll(collection);
        }
        j jVar = (j) collection;
        if (jVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < jVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + jVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(jVar.b, 0, this.b, this.c, jVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof j)) {
            return super.equals(obj);
        }
        j jVar = (j) obj;
        if (this.c != jVar.c) {
            return false;
        }
        boolean[] zArr = jVar.b;
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        b(i);
        return Boolean.valueOf(this.b[i]);
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
        b(i);
        boolean z = this.b[i];
        if (i < this.c - 1) {
            System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
        }
        this.c--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.c; i++) {
            if (obj.equals(Boolean.valueOf(this.b[i]))) {
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        c();
        b(i);
        boolean z = this.b[i];
        this.b[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final int size() {
        return this.c;
    }
}
