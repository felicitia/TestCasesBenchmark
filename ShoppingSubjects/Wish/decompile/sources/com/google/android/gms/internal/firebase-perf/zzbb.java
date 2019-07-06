package com.google.android.gms.internal.firebase-perf;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzbb extends zzaz<Boolean> implements zzcs<Boolean>, zzee, RandomAccess {
    private static final zzbb zzhm;
    private int size;
    private boolean[] zzhn;

    zzbb() {
        this(new boolean[10], 0);
    }

    private zzbb(boolean[] zArr, int i) {
        this.zzhn = zArr;
        this.size = i;
    }

    /* access modifiers changed from: protected */
    public final void removeRange(int i, int i2) {
        zzbj();
        if (i2 < i) {
            throw new IndexOutOfBoundsException("toIndex < fromIndex");
        }
        System.arraycopy(this.zzhn, i2, this.zzhn, i, this.size - i2);
        this.size -= i2 - i;
        this.modCount++;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbb)) {
            return super.equals(obj);
        }
        zzbb zzbb = (zzbb) obj;
        if (this.size != zzbb.size) {
            return false;
        }
        boolean[] zArr = zzbb.zzhn;
        for (int i = 0; i < this.size; i++) {
            if (this.zzhn[i] != zArr[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < this.size; i2++) {
            i = (i * 31) + zzco.zzf(this.zzhn[i2]);
        }
        return i;
    }

    public final int size() {
        return this.size;
    }

    public final void addBoolean(boolean z) {
        zza(this.size, z);
    }

    private final void zza(int i, boolean z) {
        zzbj();
        if (i < 0 || i > this.size) {
            throw new IndexOutOfBoundsException(zzh(i));
        }
        if (this.size < this.zzhn.length) {
            System.arraycopy(this.zzhn, i, this.zzhn, i + 1, this.size - i);
        } else {
            boolean[] zArr = new boolean[(((this.size * 3) / 2) + 1)];
            System.arraycopy(this.zzhn, 0, zArr, 0, i);
            System.arraycopy(this.zzhn, i, zArr, i + 1, this.size - i);
            this.zzhn = zArr;
        }
        this.zzhn[i] = z;
        this.size++;
        this.modCount++;
    }

    public final boolean addAll(Collection<? extends Boolean> collection) {
        zzbj();
        zzco.checkNotNull(collection);
        if (!(collection instanceof zzbb)) {
            return super.addAll(collection);
        }
        zzbb zzbb = (zzbb) collection;
        if (zzbb.size == 0) {
            return false;
        }
        if (Integer.MAX_VALUE - this.size < zzbb.size) {
            throw new OutOfMemoryError();
        }
        int i = this.size + zzbb.size;
        if (i > this.zzhn.length) {
            this.zzhn = Arrays.copyOf(this.zzhn, i);
        }
        System.arraycopy(zzbb.zzhn, 0, this.zzhn, this.size, zzbb.size);
        this.size = i;
        this.modCount++;
        return true;
    }

    public final boolean remove(Object obj) {
        zzbj();
        for (int i = 0; i < this.size; i++) {
            if (obj.equals(Boolean.valueOf(this.zzhn[i]))) {
                System.arraycopy(this.zzhn, i + 1, this.zzhn, i, this.size - i);
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
        boolean booleanValue = ((Boolean) obj).booleanValue();
        zzbj();
        zzg(i);
        boolean z = this.zzhn[i];
        this.zzhn[i] = booleanValue;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ Object remove(int i) {
        zzbj();
        zzg(i);
        boolean z = this.zzhn[i];
        if (i < this.size - 1) {
            System.arraycopy(this.zzhn, i + 1, this.zzhn, i, this.size - i);
        }
        this.size--;
        this.modCount++;
        return Boolean.valueOf(z);
    }

    public final /* synthetic */ void add(int i, Object obj) {
        zza(i, ((Boolean) obj).booleanValue());
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i >= this.size) {
            return new zzbb(Arrays.copyOf(this.zzhn, i), this.size);
        }
        throw new IllegalArgumentException();
    }

    public final /* synthetic */ Object get(int i) {
        zzg(i);
        return Boolean.valueOf(this.zzhn[i]);
    }

    static {
        zzbb zzbb = new zzbb();
        zzhm = zzbb;
        zzbb.zzbi();
    }
}
