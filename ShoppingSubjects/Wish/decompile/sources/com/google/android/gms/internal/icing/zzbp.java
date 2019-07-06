package com.google.android.gms.internal.icing;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbp extends zzbo {
    protected final byte[] zzdx;

    zzbp(byte[] bArr) {
        this.zzdx = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbi) || size() != ((zzbi) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbp)) {
            return obj.equals(this);
        }
        zzbp zzbp = (zzbp) obj;
        int zzw = zzw();
        int zzw2 = zzbp.zzw();
        if (zzw == 0 || zzw2 == 0 || zzw == zzw2) {
            return zza(zzbp, 0, size());
        }
        return false;
    }

    public int size() {
        return this.zzdx.length;
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzcm.zza(i, this.zzdx, zzx(), i3);
    }

    public final zzbi zza(int i, int i2) {
        int zzb = zzb(0, i2, size());
        return zzb == 0 ? zzbi.zzdq : new zzbl(this.zzdx, zzx(), zzb);
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzdx, zzx(), size(), charset);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbh zzbh) throws IOException {
        zzbh.zza(this.zzdx, zzx(), size());
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzbi zzbi, int i, int i2) {
        if (i2 > zzbi.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzbi.size()) {
            int size2 = zzbi.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzbi instanceof zzbp)) {
            return zzbi.zza(0, i2).equals(zza(0, i2));
        } else {
            zzbp zzbp = (zzbp) zzbi;
            byte[] bArr = this.zzdx;
            byte[] bArr2 = zzbp.zzdx;
            int zzx = zzx() + i2;
            int zzx2 = zzx();
            int zzx3 = zzbp.zzx();
            while (zzx2 < zzx) {
                if (bArr[zzx2] != bArr2[zzx3]) {
                    return false;
                }
                zzx2++;
                zzx3++;
            }
            return true;
        }
    }

    public byte zzi(int i) {
        return this.zzdx[i];
    }

    public final boolean zzv() {
        int zzx = zzx();
        return zzff.zzc(this.zzdx, zzx, size() + zzx);
    }

    /* access modifiers changed from: protected */
    public int zzx() {
        return 0;
    }
}
