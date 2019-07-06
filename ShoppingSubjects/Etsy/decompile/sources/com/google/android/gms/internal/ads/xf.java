package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class xf extends wa<Float> implements xm<Float>, RandomAccess {
    private static final xf a;
    private float[] b;
    private int c;

    static {
        xf xfVar = new xf();
        a = xfVar;
        xfVar.b();
    }

    xf() {
        this(new float[10], 0);
    }

    private xf(float[] fArr, int i) {
        this.b = fArr;
        this.c = i;
    }

    private final void a(int i, float f) {
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(c(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            float[] fArr = new float[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, fArr, 0, i);
            System.arraycopy(this.b, i, fArr, i + 1, this.c - i);
            this.b = fArr;
        }
        this.b[i] = f;
        this.c++;
        this.modCount++;
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

    public final /* synthetic */ xm a(int i) {
        if (i >= this.c) {
            return new xf(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final void a(float f) {
        a(this.c, f);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        a(i, ((Float) obj).floatValue());
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        c();
        xj.a(collection);
        if (!(collection instanceof xf)) {
            return super.addAll(collection);
        }
        xf xfVar = (xf) collection;
        if (xfVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < xfVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + xfVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(xfVar.b, 0, this.b, this.c, xfVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof xf)) {
            return super.equals(obj);
        }
        xf xfVar = (xf) obj;
        if (this.c != xfVar.c) {
            return false;
        }
        float[] fArr = xfVar.b;
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        b(i);
        return Float.valueOf(this.b[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.b[i2]);
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        b(i);
        float f = this.b[i];
        if (i < this.c - 1) {
            System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
        }
        this.c--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.c; i++) {
            if (obj.equals(Float.valueOf(this.b[i]))) {
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
        float floatValue = ((Float) obj).floatValue();
        c();
        b(i);
        float f = this.b[i];
        this.b[i] = floatValue;
        return Float.valueOf(f);
    }

    public final int size() {
        return this.c;
    }
}
