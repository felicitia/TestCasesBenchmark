package com.google.android.gms.internal.icing;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcl extends zzbe<Integer> implements zzcr<Integer>, RandomAccess {
    private static final zzcl zzih;
    private int size;
    private int[] zzii;

    static {
        zzcl zzcl = new zzcl();
        zzih = zzcl;
        zzcl.zzp();
    }

    zzcl() {
        this(new int[10], 0);
    }

    private zzcl(int[] iArr, int i) {
        this.zzii = iArr;
        this.size = i;
    }

    private final void zzf(int i) {
        if (i < 0 || i >= this.size) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
    }

    private final String zzg(int i) {
        int i2 = this.size;
        StringBuilder sb = new StringBuilder(35);
        sb.append("Index:");
        sb.append(i);
        sb.append(", Size:");
        sb.append(i2);
        return sb.toString();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzq();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzg(i));
        }
        if (this.size < this.zzii.length) {
            System.arraycopy(this.zzii, i, this.zzii, i + 1, this.size - i);
        } else {
            int[] iArr = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzii, 0, iArr, 0, i);
            System.arraycopy(this.zzii, i, iArr, i + 1, this.size - i);
            this.zzii = iArr;
        }
        this.zzii[i] = intValue;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzq();
        zzcm.checkNotNull(collection);
        if (!(collection instanceof zzcl)) {
            return super.addAll(collection);
        }
        zzcl zzcl = (zzcl) collection;
        if (zzcl.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcl.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcl.size;
        if (i > this.zzii.length) {
            this.zzii = Arrays.copyOf(this.zzii, i);
        }
        System.arraycopy(zzcl.zzii, 0, this.zzii, this.size, zzcl.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcl)) {
            return super.equals(obj);
        }
        zzcl zzcl = (zzcl) obj;
        if (this.size != zzcl.size) {
            return false;
        }
        int[] iArr = zzcl.zzii;
        for (int i = 0; i < this.size; i++) {
            if (this.zzii[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    public final int getInt(int i) {
        zzf(i);
        return this.zzii[i];
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzii[i2];
        }
        return i;
    }

    public final /* synthetic */ Object remove(int i) {
        zzq();
        zzf(i);
        int i2 = this.zzii[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzii, i + 1, this.zzii, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final boolean remove(Object obj) {
        zzq();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzii[i]))) {
                System.arraycopy(this.zzii, i + 1, this.zzii, i, this.size - i);
                this.size--;
                this.modCount++;
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzq();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzii, i2, this.zzii, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        int intValue = ((Integer) obj).intValue();
        zzq();
        zzf(i);
        int i2 = this.zzii[i];
        this.zzii[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final int size() {
        return this.size;
    }

    public final /* synthetic */ zzcr zzh(int i) {
        if (i >= this.size) {
            return new zzcl(Arrays.copyOf(this.zzii, i), this.size);
        }
        throw new IllegalArgumentException();
    }
}
