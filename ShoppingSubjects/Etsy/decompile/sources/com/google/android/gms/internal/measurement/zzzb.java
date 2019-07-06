package com.google.android.gms.internal.measurement;

import java.io.Serializable;
import java.util.Iterator;

public abstract class zzzb implements Serializable, Iterable<Byte> {
    private static final gw a = (gt.a() ? new gx(null) : new gv(null));
    public static final zzzb zzbte = new zzzh(hb.b);
    private int zzbqf = 0;

    zzzb() {
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

    public static zzzb zzfn(String str) {
        return new zzzh(str.getBytes(hb.a));
    }

    public abstract boolean equals(Object obj);

    public final int hashCode() {
        int i = this.zzbqf;
        if (i == 0) {
            int size = size();
            i = zza(size, 0, size);
            if (i == 0) {
                i = 1;
            }
            this.zzbqf = i;
        }
        return i;
    }

    public /* synthetic */ Iterator iterator() {
        return new gu(this);
    }

    public abstract int size();

    public final String toString() {
        return String.format("<ByteString@%s size=%d>", new Object[]{Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(size())});
    }

    /* access modifiers changed from: protected */
    public abstract int zza(int i, int i2, int i3);

    public abstract byte zzae(int i);

    public abstract zzzb zzb(int i, int i2);

    /* access modifiers changed from: protected */
    public final int zztm() {
        return this.zzbqf;
    }
}
