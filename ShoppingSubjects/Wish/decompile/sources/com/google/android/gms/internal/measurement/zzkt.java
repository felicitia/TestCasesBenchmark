package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkt extends zzacd<zzkt> {
    public zzku[] zzavf;

    public zzkt() {
        this.zzavf = zzku.zzma();
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkt)) {
            return false;
        }
        zzkt zzkt = (zzkt) obj;
        if (!zzach.equals((Object[]) this.zzavf, (Object[]) zzkt.zzavf)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkt.zzbzd == null || zzkt.zzbzd.isEmpty() : this.zzbzd.equals(zzkt.zzbzd);
    }

    public final int hashCode() {
        return ((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode((Object[]) this.zzavf)) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzavf != null && this.zzavf.length > 0) {
            for (zzku zzku : this.zzavf) {
                if (zzku != null) {
                    zza += zzacb.zzb(1, (zzacj) zzku);
                }
            }
        }
        return zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzavf != null && this.zzavf.length > 0) {
            for (zzku zzku : this.zzavf) {
                if (zzku != null) {
                    zzacb.zza(1, (zzacj) zzku);
                }
            }
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 10) {
                int zzb = zzacm.zzb(zzaca, 10);
                int length = this.zzavf == null ? 0 : this.zzavf.length;
                zzku[] zzkuArr = new zzku[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzavf, 0, zzkuArr, 0, length);
                }
                while (length < zzkuArr.length - 1) {
                    zzkuArr[length] = new zzku();
                    zzaca.zza(zzkuArr[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkuArr[length] = new zzku();
                zzaca.zza(zzkuArr[length]);
                this.zzavf = zzkuArr;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
