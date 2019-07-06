package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class ai extends h<Integer> implements ao<Integer>, RandomAccess {
    private static final ai a;
    private int[] b;
    private int c;

    static {
        ai aiVar = new ai();
        a = aiVar;
        aiVar.b();
    }

    ai() {
        this(new int[10], 0);
    }

    private ai(int[] iArr, int i) {
        this.b = iArr;
        this.c = i;
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

    public final /* synthetic */ ao a(int i) {
        if (i >= this.c) {
            return new ai(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(d(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            int[] iArr = new int[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, iArr, 0, i);
            System.arraycopy(this.b, i, iArr, i + 1, this.c - i);
            this.b = iArr;
        }
        this.b[i] = intValue;
        this.c++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        c();
        aj.a(collection);
        if (!(collection instanceof ai)) {
            return super.addAll(collection);
        }
        ai aiVar = (ai) collection;
        if (aiVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < aiVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + aiVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(aiVar.b, 0, this.b, this.c, aiVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final int b(int i) {
        c(i);
        return this.b[i];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ai)) {
            return super.equals(obj);
        }
        ai aiVar = (ai) obj;
        if (this.c != aiVar.c) {
            return false;
        }
        int[] iArr = aiVar.b;
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(b(i));
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + this.b[i2];
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        c(i);
        int i2 = this.b[i];
        if (i < this.c - 1) {
            System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
        }
        this.c--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.c; i++) {
            if (obj.equals(Integer.valueOf(this.b[i]))) {
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
        int intValue = ((Integer) obj).intValue();
        c();
        c(i);
        int i2 = this.b[i];
        this.b[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.c;
    }
}
