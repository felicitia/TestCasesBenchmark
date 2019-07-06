package com.google.android.gms.internal.firebase-perf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzdh extends zzaz<Long> implements zzcs<Long>, zzee, RandomAccess {
    private static final zzdh zzob;
    private int size;
    private long[] zzoc;

    zzdh() {
        this(new long[10], 0);
    }

    private zzdh(long[] jArr, int i) {
        this.zzoc = jArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbj();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzoc, i2, this.zzoc, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzdh)) {
            return super.equals(obj);
        }
        zzdh zzdh = (zzdh) obj;
        if (this.size != zzdh.size) {
            return false;
        }
        long[] jArr = zzdh.zzoc;
        for (int i = 0; i < this.size; i++) {
            if (this.zzoc[i] != jArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzco.zzu(this.zzoc[i2]);
        }
        return i;
    }

    public final long getLong(int i) {
        zzg(i);
        return this.zzoc[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzv(long j) {
        zzk(this.size, j);
    }

    private final void zzk(int i, long j) {
        zzbj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        if (this.size < this.zzoc.length) {
            System.arraycopy(this.zzoc, i, this.zzoc, i + 1, this.size - i);
        } else {
            long[] jArr = new long[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzoc, 0, jArr, 0, i);
            System.arraycopy(this.zzoc, i, jArr, i + 1, this.size - i);
            this.zzoc = jArr;
        }
        this.zzoc[i] = j;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Long> collection) {
        zzbj();
        zzco.checkNotNull(collection);
        if (!(collection instanceof zzdh)) {
            return super.addAll(collection);
        }
        zzdh zzdh = (zzdh) collection;
        if (zzdh.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzdh.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzdh.size;
        if (i > this.zzoc.length) {
            this.zzoc = Arrays.copyOf(this.zzoc, i);
        }
        System.arraycopy(zzdh.zzoc, 0, this.zzoc, this.size, zzdh.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzbj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Long.valueOf(this.zzoc[i]))) {
                System.arraycopy(this.zzoc, i + 1, this.zzoc, i, this.size - i);
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
        long longValue = ((Long) obj).longValue();
        zzbj();
        zzg(i);
        long j = this.zzoc[i];
        this.zzoc[i] = longValue;
        return Long.valueOf(j);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbj();
        zzg(i);
        long j = this.zzoc[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzoc, i + 1, this.zzoc, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Long.valueOf(j);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzk(i, ((Long) obj).longValue());
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i >= this.size) {
            return new zzdh(Arrays.copyOf(this.zzoc, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Long.valueOf(getLong(i));
    }

    static {
        zzdh zzdh = new zzdh();
        zzob = zzdh;
        zzdh.zzbi();
    }
}
