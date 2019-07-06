package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkh extends zzacd<zzkh> {
    private static volatile zzkh[] zzatj;
    public Integer zzatk;
    public String zzatl;
    public zzki[] zzatm;
    private Boolean zzatn;
    public zzkj zzato;

    public zzkh() {
        this.zzatk = null;
        this.zzatl = null;
        this.zzatm = zzki.zzls();
        this.zzatn = null;
        this.zzato = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkh[] zzlr() {
        if (zzatj == null) {
            synchronized (zzach.zzbzn) {
                if (zzatj == null) {
                    zzatj = new zzkh[0];
                }
            }
        }
        return zzatj;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkh)) {
            return false;
        }
        zzkh zzkh = (zzkh) obj;
        if (this.zzatk == null) {
            if (zzkh.zzatk != null) {
                return false;
            }
        } else if (!this.zzatk.equals(zzkh.zzatk)) {
            return false;
        }
        if (this.zzatl == null) {
            if (zzkh.zzatl != null) {
                return false;
            }
        } else if (!this.zzatl.equals(zzkh.zzatl)) {
            return false;
        }
        if (!zzach.equals((Object[]) this.zzatm, (Object[]) zzkh.zzatm)) {
            return false;
        }
        if (this.zzatn == null) {
            if (zzkh.zzatn != null) {
                return false;
            }
        } else if (!this.zzatn.equals(zzkh.zzatn)) {
            return false;
        }
        if (this.zzato == null) {
            if (zzkh.zzato != null) {
                return false;
            }
        } else if (!this.zzato.equals(zzkh.zzato)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkh.zzbzd == null || zzkh.zzbzd.isEmpty() : this.zzbzd.equals(zzkh.zzbzd);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzatk == null ? 0 : this.zzatk.hashCode())) * 31) + (this.zzatl == null ? 0 : this.zzatl.hashCode())) * 31) + zzach.hashCode((Object[]) this.zzatm)) * 31) + (this.zzatn == null ? 0 : this.zzatn.hashCode());
        zzkj zzkj = this.zzato;
        int hashCode2 = ((hashCode * 31) + (zzkj == null ? 0 : zzkj.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode2 + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzatk != null) {
            zza += zzacb.zzf(1, this.zzatk.intValue());
        }
        if (this.zzatl != null) {
            zza += zzacb.zzc(2, this.zzatl);
        }
        if (this.zzatm != null && this.zzatm.length > 0) {
            for (zzki zzki : this.zzatm) {
                if (zzki != null) {
                    zza += zzacb.zzb(3, (zzacj) zzki);
                }
            }
        }
        if (this.zzatn != null) {
            this.zzatn.booleanValue();
            zza += zzacb.zzaq(4) + 1;
        }
        return this.zzato != null ? zza + zzacb.zzb(5, (zzacj) this.zzato) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzatk != null) {
            zzacb.zze(1, this.zzatk.intValue());
        }
        if (this.zzatl != null) {
            zzacb.zzb(2, this.zzatl);
        }
        if (this.zzatm != null && this.zzatm.length > 0) {
            for (zzki zzki : this.zzatm) {
                if (zzki != null) {
                    zzacb.zza(3, (zzacj) zzki);
                }
            }
        }
        if (this.zzatn != null) {
            zzacb.zza(4, this.zzatn.booleanValue());
        }
        if (this.zzato != null) {
            zzacb.zza(5, (zzacj) this.zzato);
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
                this.zzatk = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 18) {
                this.zzatl = zzaca.readString();
            } else if (zzvl == 26) {
                int zzb = zzacm.zzb(zzaca, 26);
                int length = this.zzatm == null ? 0 : this.zzatm.length;
                zzki[] zzkiArr = new zzki[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzatm, 0, zzkiArr, 0, length);
                }
                while (length < zzkiArr.length - 1) {
                    zzkiArr[length] = new zzki();
                    zzaca.zza(zzkiArr[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkiArr[length] = new zzki();
                zzaca.zza(zzkiArr[length]);
                this.zzatm = zzkiArr;
            } else if (zzvl == 32) {
                this.zzatn = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 42) {
                if (this.zzato == null) {
                    this.zzato = new zzkj();
                }
                zzaca.zza(this.zzato);
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
