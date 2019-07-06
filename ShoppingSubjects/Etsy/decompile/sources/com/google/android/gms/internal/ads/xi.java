package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class xi extends wa<Integer> implements xm<Integer>, RandomAccess {
    private static final xi a;
    private int[] b;
    private int c;

    static {
        xi xiVar = new xi();
        a = xiVar;
        xiVar.b();
    }

    xi() {
        this(new int[10], 0);
    }

    private xi(int[] iArr, int i) {
        this.b = iArr;
        this.c = i;
    }

    private final void a(int i, int i2) {
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(e(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            int[] iArr = new int[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, iArr, 0, i);
            System.arraycopy(this.b, i, iArr, i + 1, this.c - i);
            this.b = iArr;
        }
        this.b[i] = i2;
        this.c++;
        this.modCount++;
    }

    private final void d(int i) {
        if (i < 0 || i >= this.c) {
            throw new IndexOutOfBoundsException(e(i));
        }
    }

    private final String e(int i) {
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
            return new xi(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        a(i, ((Integer) obj).intValue());
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        c();
        xj.a(collection);
        if (!(collection instanceof xi)) {
            return super.addAll(collection);
        }
        xi xiVar = (xi) collection;
        if (xiVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < xiVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + xiVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(xiVar.b, 0, this.b, this.c, xiVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final int b(int i) {
        d(i);
        return this.b[i];
    }

    public final void c(int i) {
        a(this.c, i);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xi)) {
            return super.equals(obj);
        }
        xi xiVar = (xi) obj;
        if (this.c != xiVar.c) {
            return false;
        }
        int[] iArr = xiVar.b;
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
        d(i);
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
        d(i);
        int i2 = this.b[i];
        this.b[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.c;
    }
}
