package com.google.android.gms.internal.firebase-perf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcj extends zzaz<Float> implements zzcs<Float>, zzee, RandomAccess {
    private static final zzcj zzly;
    private int size;
    private float[] zzlz;

    zzcj() {
        this(new float[10], 0);
    }

    private zzcj(float[] fArr, int i) {
        this.zzlz = fArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbj();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzlz, i2, this.zzlz, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcj)) {
            return super.equals(obj);
        }
        zzcj zzcj = (zzcj) obj;
        if (this.size != zzcj.size) {
            return false;
        }
        float[] fArr = zzcj.zzlz;
        for (int i = 0; i < this.size; i++) {
            if (this.zzlz[i] != fArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + Float.floatToIntBits(this.zzlz[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void zzc(float f) {
        zzc(this.size, f);
    }

    private final void zzc(int i, float f) {
        zzbj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        if (this.size < this.zzlz.length) {
            System.arraycopy(this.zzlz, i, this.zzlz, i + 1, this.size - i);
        } else {
            float[] fArr = new float[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzlz, 0, fArr, 0, i);
            System.arraycopy(this.zzlz, i, fArr, i + 1, this.size - i);
            this.zzlz = fArr;
        }
        this.zzlz[i] = f;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Float> collection) {
        zzbj();
        zzco.checkNotNull(collection);
        if (!(collection instanceof zzcj)) {
            return super.addAll(collection);
        }
        zzcj zzcj = (zzcj) collection;
        if (zzcj.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcj.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcj.size;
        if (i > this.zzlz.length) {
            this.zzlz = Arrays.copyOf(this.zzlz, i);
        }
        System.arraycopy(zzcj.zzlz, 0, this.zzlz, this.size, zzcj.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzbj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Float.valueOf(this.zzlz[i]))) {
                System.arraycopy(this.zzlz, i + 1, this.zzlz, i, this.size - i);
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
        float floatValue = ((Float) obj).floatValue();
        zzbj();
        zzg(i);
        float f = this.zzlz[i];
        this.zzlz[i] = floatValue;
        return Float.valueOf(f);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbj();
        zzg(i);
        float f = this.zzlz[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzlz, i + 1, this.zzlz, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Float.valueOf(f);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzc(i, ((Float) obj).floatValue());
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i >= this.size) {
            return new zzcj(Arrays.copyOf(this.zzlz, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzg(i);
        return Float.valueOf(this.zzlz[i]);
    }

    static {
        zzcj zzcj = new zzcj();
        zzly = zzcj;
        zzcj.zzbi();
    }
}
