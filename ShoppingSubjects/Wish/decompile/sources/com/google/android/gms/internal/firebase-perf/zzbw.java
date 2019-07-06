package com.google.android.gms.internal.firebase-perf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbw extends zzaz<Double> implements zzcs<Double>, zzee, RandomAccess {
    private static final zzbw zziv;
    private int size;
    private double[] zziw;

    zzbw() {
        this(new double[10], 0);
    }

    private zzbw(double[] dArr, int i) {
        this.zziw = dArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbj();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zziw, i2, this.zziw, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbw)) {
            return super.equals(obj);
        }
        zzbw zzbw = (zzbw) obj;
        if (this.size != zzbw.size) {
            return false;
        }
        double[] dArr = zzbw.zziw;
        for (int i = 0; i < this.size; i++) {
            if (this.zziw[i] != dArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzco.zzu(Double.doubleToLongBits(this.zziw[i2]));
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(double d) {
        zzc(this.size, d);
    }

    private final void zzc(int i, double d) {
        zzbj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        if (this.size < this.zziw.length) {
            System.arraycopy(this.zziw, i, this.zziw, i + 1, this.size - i);
        } else {
            double[] dArr = new double[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zziw, 0, dArr, 0, i);
            System.arraycopy(this.zziw, i, dArr, i + 1, this.size - i);
            this.zziw = dArr;
        }
        this.zziw[i] = d;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Double> collection) {
        zzbj();
        zzco.checkNotNull(collection);
        if (!(collection instanceof zzbw)) {
            return super.addAll(collection);
        }
        zzbw zzbw = (zzbw) collection;
        if (zzbw.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzbw.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzbw.size;
        if (i > this.zziw.length) {
            this.zziw = Arrays.copyOf(this.zziw, i);
        }
        System.arraycopy(zzbw.zziw, 0, this.zziw, this.size, zzbw.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzbj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Double.valueOf(this.zziw[i]))) {
                System.arraycopy(this.zziw, i + 1, this.zziw, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    private final void zzg(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
    }

    private final String zzh(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        double doubleValue = ((Double) obj).doubleValue();
        zzbj();
        zzg(i);
        double d = this.zziw[i];
        this.zziw[i] = doubleValue;
        return Double.valueOf(d);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbj();
        zzg(i);
        double d = this.zziw[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zziw, i + 1, this.zziw, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Double.valueOf(d);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Double) obj).doubleValue());
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i >= this.size) {
            return new zzbw(Arrays.copyOf(this.zziw, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzg(i);
        return Double.valueOf(this.zziw[i]);
    }

    static {
        zzbw zzbw = new zzbw();
        zziv = zzbw;
        zzbw.zzbi();
    }
}
