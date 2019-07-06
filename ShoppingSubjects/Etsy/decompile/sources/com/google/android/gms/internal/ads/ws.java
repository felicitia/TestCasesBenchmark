package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class ws extends wa<Double> implements xm<Double>, RandomAccess {
    private static final ws a;
    private double[] b;
    private int c;

    static {
        ws wsVar = new ws();
        a = wsVar;
        wsVar.b();
    }

    ws() {
        this(new double[10], 0);
    }

    private ws(double[] dArr, int i) {
        this.b = dArr;
        this.c = i;
    }

    private final void a(int i, double d) {
        c();
        if (i < 0 || i > this.c) {
            throw new IndexOutOfBoundsException(c(i));
        }
        if (this.c < this.b.length) {
            System.arraycopy(this.b, i, this.b, i + 1, this.c - i);
        } else {
            double[] dArr = new double[(((this.c * 3) / 2) + 1)];
            System.arraycopy(this.b, 0, dArr, 0, i);
            System.arraycopy(this.b, i, dArr, i + 1, this.c - i);
            this.b = dArr;
        }
        this.b[i] = d;
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
            return new ws(Arrays.copyOf(this.b, i), this.c);
        }
        throw new IllegalArgumentException();
    }

    public final void a(double d) {
        a(this.c, d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        a(i, ((Double) obj).doubleValue());
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        c();
        xj.a(collection);
        if (!(collection instanceof ws)) {
            return super.addAll(collection);
        }
        ws wsVar = (ws) collection;
        if (wsVar.c == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.c < wsVar.c) {
            throw new OutOfMemoryError();
        }
        int i = this.c + wsVar.c;
        if (i > this.b.length) {
            this.b = Arrays.copyOf(this.b, i);
        }
        System.arraycopy(wsVar.b, 0, this.b, this.c, wsVar.c);
        this.c = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ws)) {
            return super.equals(obj);
        }
        ws wsVar = (ws) obj;
        if (this.c != wsVar.c) {
            return false;
        }
        double[] dArr = wsVar.b;
        for (int i = 0; i < this.c; i++) {
            if (this.b[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        b(i);
        return Double.valueOf(this.b[i]);
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.c; i2++) {
            i = (i * 31) + xj.a(Double.doubleToLongBits(this.b[i2]));
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        b(i);
        double d = this.b[i];
        if (i < this.c - 1) {
            System.arraycopy(this.b, i + 1, this.b, i, this.c - i);
        }
        this.c--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final boolean remove(Object obj) {
        c();
        for (int i = 0; i < this.c; i++) {
            if (obj.equals(Double.valueOf(this.b[i]))) {
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
        double doubleValue = ((Double) obj).doubleValue();
        c();
        b(i);
        double d = this.b[i];
        this.b[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final int size() {
        return this.c;
    }
}
