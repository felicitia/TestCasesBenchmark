package com.google.android.gms.internal.firebase-perf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzcn extends zzaz<Integer> implements zzcs<Integer>, zzee, RandomAccess {
    private static final zzcn zzmx;
    private int size;
    private int[] zzmy;

    zzcn() {
        this(new int[10], 0);
    }

    private zzcn(int[] iArr, int i) {
        this.zzmy = iArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbj();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzmy, i2, this.zzmy, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcn)) {
            return super.equals(obj);
        }
        zzcn zzcn = (zzcn) obj;
        if (this.size != zzcn.size) {
            return false;
        }
        int[] iArr = zzcn.zzmy;
        for (int i = 0; i < this.size; i++) {
            if (this.zzmy[i] != iArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + this.zzmy[i2];
        }
        return i;
    }

    public final int getInt(int i) {
        zzg(i);
        return this.zzmy[i];
    }

    public final int size() {
        return this.size;
    }

    public final void zzak(int i) {
        zzo(this.size, i);
    }

    private final void zzo(int i, int i2) {
        zzbj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        if (this.size < this.zzmy.length) {
            System.arraycopy(this.zzmy, i, this.zzmy, i + 1, this.size - i);
        } else {
            int[] iArr = new int[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzmy, 0, iArr, 0, i);
            System.arraycopy(this.zzmy, i, iArr, i + 1, this.size - i);
            this.zzmy = iArr;
        }
        this.zzmy[i] = i2;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Integer> collection) {
        zzbj();
        zzco.checkNotNull(collection);
        if (!(collection instanceof zzcn)) {
            return super.addAll(collection);
        }
        zzcn zzcn = (zzcn) collection;
        if (zzcn.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzcn.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzcn.size;
        if (i > this.zzmy.length) {
            this.zzmy = Arrays.copyOf(this.zzmy, i);
        }
        System.arraycopy(zzcn.zzmy, 0, this.zzmy, this.size, zzcn.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzbj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Integer.valueOf(this.zzmy[i]))) {
                System.arraycopy(this.zzmy, i + 1, this.zzmy, i, this.size - i);
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
        int intValue = ((Integer) obj).intValue();
        zzbj();
        zzg(i);
        int i2 = this.zzmy[i];
        this.zzmy[i] = intValue;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbj();
        zzg(i);
        int i2 = this.zzmy[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzmy, i + 1, this.zzmy, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Integer.valueOf(i2);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zzo(i, ((Integer) obj).intValue());
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i >= this.size) {
            return new zzcn(Arrays.copyOf(this.zzmy, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        return Integer.valueOf(getInt(i));
    }

    static {
        zzcn zzcn = new zzcn();
        zzmx = zzcn;
        zzcn.zzbi();
    }
}
