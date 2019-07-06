package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;
import java.nio.charset.Charset;

class zzbm extends zzbl {
    protected final byte[] zzhw;

    zzbm(byte[] bArr) {
        if (bArr == null) {
            throw new NullPointerException();
        }
        this.zzhw = bArr;
    }

    /* access modifiers changed from: protected */
    public int zzbp() {
        return 0;
    }

    public byte zzj(int i) {
        return this.zzhw[i];
    }

    public int size() {
        return this.zzhw.length;
    }

    public final zzbd zza(int i, int i2) {
        int zzb = zzb(0, i2, size());
        if (zzb == 0) {
            return zzbd.zzho;
        }
        return new zzbh(this.zzhw, zzbp(), zzb);
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzbc zzbc) throws IOException {
        zzbc.zza(this.zzhw, zzbp(), size());
    }

    /* access modifiers changed from: protected */
    public final String zza(Charset charset) {
        return new String(this.zzhw, zzbp(), size(), charset);
    }

    public final boolean zzbn() {
        int zzbp = zzbp();
        return zzfj.zzf(this.zzhw, zzbp, size() + zzbp);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzbd) || size() != ((zzbd) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzbm)) {
            return obj.equals(this);
        }
        zzbm zzbm = (zzbm) obj;
        int zzbo = zzbo();
        int zzbo2 = zzbm.zzbo();
        if (zzbo == 0 || zzbo2 == 0 || zzbo == zzbo2) {
            return zza(zzbm, 0, size());
        }
        return false;
    }

    /* access modifiers changed from: 0000 */
    public final boolean zza(zzbd zzbd, int i, int i2) {
        if (i2 > zzbd.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 > zzbd.size()) {
            int size2 = zzbd.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        } else if (!(zzbd instanceof zzbm)) {
            return zzbd.zza(0, i2).equals(zza(0, i2));
        } else {
            zzbm zzbm = (zzbm) zzbd;
            byte[] bArr = this.zzhw;
            byte[] bArr2 = zzbm.zzhw;
            int zzbp = zzbp() + i2;
            int zzbp2 = zzbp();
            int zzbp3 = zzbm.zzbp();
            while (zzbp2 < zzbp) {
                if (bArr[zzbp2] != bArr2[zzbp3]) {
                    return false;
                }
                zzbp2++;
                zzbp3++;
            }
            return true;
        }
    }

    /* access modifiers changed from: protected */
    public final int zza(int i, int i2, int i3) {
        return zzco.zza(i, this.zzhw, zzbp(), i3);
    }
}
