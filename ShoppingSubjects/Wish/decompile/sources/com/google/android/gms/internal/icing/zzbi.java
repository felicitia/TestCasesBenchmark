package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Iterator;

public abstract class zzbi implements Serializable, Iterable<Byte> {
    public static final zzbi zzdq = new zzbp(zzcm.zzij);
    private static final zzbm zzdr = (zzbf.zzr() ? new zzbq(null) : new zzbk(null));
    private int zzds = 0;

    zzbi() {
    }

    static int zzb(int i, int i2, int i3) {
        int i4 = i2 - i;
        if ((i | i2 | i4 | (i3 - i2)) >= 0) {
            return i4;
        }
        if (i < 0) {
            StringBuilder sb = new StringBuilder(32);
            sb.append("Beginning index: ");
            sb.append(i);
            sb.append(" < 0");
            throw new IndexOutOfBoundsException(sb.toString());
        } else if (i2 < i) {
            StringBuilder sb2 = new StringBuilder(66);
            sb2.append("Beginning index larger than ending index: ");
            sb2.append(i);
            sb2.append(", ");
            sb2.append(i2);
            throw new IndexOutOfBoundsException(sb2.toString());
        } else {
            StringBuilder sb3 = new StringBuilder(37);
            sb3.append("End index: ");
            sb3.append(i2);
            sb3.append(" >= ");
            sb3.append(i3);
            throw new IndexOutOfBoundsException(sb3.toString());
        }
    }

    public static zzbi zzg(String str) {
        return new zzbp(str.getBytes(zzcm.UTF_8));
    }

    static zzbn zzj(int i) {
        return new zzbn(i, null);
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzds;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzds = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new zzbj(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    /* access modifiers changed from: protected */
    public abstract int zza(int i, int i2, int i3);

    public abstract zzbi zza(int i, int i2);

    /* access modifiers changed from: protected */
    public abstract String zza(Charset charset);

    /* access modifiers changed from: 0000 */
    public abstract void zza(zzbh zzbh) throws IOException;

    public abstract byte zzi(int i);

    public final String zzu() {
        return size() == 0 ? "" : zza(zzcm.UTF_8);
    }

    public abstract boolean zzv();

    /* access modifiers changed from: protected */
    public final int zzw() {
        return this.zzds;
    }
}
