package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkw extends zzacd<zzkw> {
    private static volatile zzkw[] zzawp;
    private Integer zzaux;
    private long[] zzawq;

    public zzkw() {
        this.zzaux = null;
        this.zzawq = zzacm.zzbzt;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkw[] zzmb() {
        if (zzawp == null) {
            synchronized (zzach.zzbzn) {
                if (zzawp == null) {
                    zzawp = new zzkw[0];
                }
            }
        }
        return zzawp;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkw)) {
            return false;
        }
        zzkw zzkw = (zzkw) obj;
        if (this.zzaux == null) {
            if (zzkw.zzaux != null) {
                return false;
            }
        } else if (!this.zzaux.equals(zzkw.zzaux)) {
            return false;
        }
        if (!zzach.equals(this.zzawq, zzkw.zzawq)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkw.zzbzd == null || zzkw.zzbzd.isEmpty() : this.zzbzd.equals(zzkw.zzbzd);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.zzaux == null ? 0 : this.zzaux.hashCode())) * 31) + zzach.hashCode(this.zzawq)) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzaux != null) {
            zza += zzacb.zzf(1, this.zzaux.intValue());
        }
        if (this.zzawq == null || this.zzawq.length <= 0) {
            return zza;
        }
        int i = 0;
        for (long zzat : this.zzawq) {
            i += zzacb.zzat(zzat);
        }
        return zza + i + (this.zzawq.length * 1);
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzaux != null) {
            zzacb.zze(1, this.zzaux.intValue());
        }
        if (this.zzawq != null && this.zzawq.length > 0) {
            for (long zzb : this.zzawq) {
                zzacb.zzb(2, zzb);
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
            if (zzvl == 8) {
                this.zzaux = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 16) {
                int zzb = zzacm.zzb(zzaca, 16);
                int length = this.zzawq == null ? 0 : this.zzawq.length;
                long[] jArr = new long[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzawq, 0, jArr, 0, length);
                }
                while (length < jArr.length - 1) {
                    jArr[length] = zzaca.zzvo();
                    zzaca.zzvl();
                    length++;
                }
                jArr[length] = zzaca.zzvo();
                this.zzawq = jArr;
            } else if (zzvl == 18) {
                int zzaf = zzaca.zzaf(zzaca.zzvn());
                int position = zzaca.getPosition();
                int i = 0;
                while (zzaca.zzvr() > 0) {
                    zzaca.zzvo();
                    i++;
                }
                zzaca.zzam(position);
                int length2 = this.zzawq == null ? 0 : this.zzawq.length;
                long[] jArr2 = new long[(i + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzawq, 0, jArr2, 0, length2);
                }
                while (length2 < jArr2.length) {
                    jArr2[length2] = zzaca.zzvo();
                    length2++;
                }
                this.zzawq = jArr2;
                zzaca.zzal(zzaf);
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
