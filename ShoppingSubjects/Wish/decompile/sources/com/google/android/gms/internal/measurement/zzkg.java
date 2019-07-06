package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkg extends zzacd<zzkg> {
    private static volatile zzkg[] zzatd;
    public Integer zzate;
    public zzkk[] zzatf;
    public zzkh[] zzatg;
    public Boolean zzath;
    public Boolean zzati;

    public zzkg() {
        this.zzate = null;
        this.zzatf = zzkk.zzlt();
        this.zzatg = zzkh.zzlr();
        this.zzath = null;
        this.zzati = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkg[] zzlq() {
        if (zzatd == null) {
            synchronized (zzach.zzbzn) {
                if (zzatd == null) {
                    zzatd = new zzkg[0];
                }
            }
        }
        return zzatd;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkg)) {
            return false;
        }
        zzkg zzkg = (zzkg) obj;
        if (this.zzate == null) {
            if (zzkg.zzate != null) {
                return false;
            }
        } else if (!this.zzate.equals(zzkg.zzate)) {
            return false;
        }
        if (!zzach.equals((Object[]) this.zzatf, (Object[]) zzkg.zzatf) || !zzach.equals((Object[]) this.zzatg, (Object[]) zzkg.zzatg)) {
            return false;
        }
        if (this.zzath == null) {
            if (zzkg.zzath != null) {
                return false;
            }
        } else if (!this.zzath.equals(zzkg.zzath)) {
            return false;
        }
        if (this.zzati == null) {
            if (zzkg.zzati != null) {
                return false;
            }
        } else if (!this.zzati.equals(zzkg.zzati)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkg.zzbzd == null || zzkg.zzbzd.isEmpty() : this.zzbzd.equals(zzkg.zzbzd);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzate == null ? 0 : this.zzate.hashCode())) * 31) + zzach.hashCode((Object[]) this.zzatf)) * 31) + zzach.hashCode((Object[]) this.zzatg)) * 31) + (this.zzath == null ? 0 : this.zzath.hashCode())) * 31) + (this.zzati == null ? 0 : this.zzati.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzate != null) {
            zza += zzacb.zzf(1, this.zzate.intValue());
        }
        if (this.zzatf != null && this.zzatf.length > 0) {
            int i = zza;
            for (zzkk zzkk : this.zzatf) {
                if (zzkk != null) {
                    i += zzacb.zzb(2, (zzacj) zzkk);
                }
            }
            zza = i;
        }
        if (this.zzatg != null && this.zzatg.length > 0) {
            for (zzkh zzkh : this.zzatg) {
                if (zzkh != null) {
                    zza += zzacb.zzb(3, (zzacj) zzkh);
                }
            }
        }
        if (this.zzath != null) {
            this.zzath.booleanValue();
            zza += zzacb.zzaq(4) + 1;
        }
        if (this.zzati == null) {
            return zza;
        }
        this.zzati.booleanValue();
        return zza + zzacb.zzaq(5) + 1;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzate != null) {
            zzacb.zze(1, this.zzate.intValue());
        }
        if (this.zzatf != null && this.zzatf.length > 0) {
            for (zzkk zzkk : this.zzatf) {
                if (zzkk != null) {
                    zzacb.zza(2, (zzacj) zzkk);
                }
            }
        }
        if (this.zzatg != null && this.zzatg.length > 0) {
            for (zzkh zzkh : this.zzatg) {
                if (zzkh != null) {
                    zzacb.zza(3, (zzacj) zzkh);
                }
            }
        }
        if (this.zzath != null) {
            zzacb.zza(4, this.zzath.booleanValue());
        }
        if (this.zzati != null) {
            zzacb.zza(5, this.zzati.booleanValue());
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
                this.zzate = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 18) {
                int zzb = zzacm.zzb(zzaca, 18);
                int length = this.zzatf == null ? 0 : this.zzatf.length;
                zzkk[] zzkkArr = new zzkk[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzatf, 0, zzkkArr, 0, length);
                }
                while (length < zzkkArr.length - 1) {
                    zzkkArr[length] = new zzkk();
                    zzaca.zza(zzkkArr[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkkArr[length] = new zzkk();
                zzaca.zza(zzkkArr[length]);
                this.zzatf = zzkkArr;
            } else if (zzvl == 26) {
                int zzb2 = zzacm.zzb(zzaca, 26);
                int length2 = this.zzatg == null ? 0 : this.zzatg.length;
                zzkh[] zzkhArr = new zzkh[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzatg, 0, zzkhArr, 0, length2);
                }
                while (length2 < zzkhArr.length - 1) {
                    zzkhArr[length2] = new zzkh();
                    zzaca.zza(zzkhArr[length2]);
                    zzaca.zzvl();
                    length2++;
                }
                zzkhArr[length2] = new zzkh();
                zzaca.zza(zzkhArr[length2]);
                this.zzatg = zzkhArr;
            } else if (zzvl == 32) {
                this.zzath = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 40) {
                this.zzati = Boolean.valueOf(zzaca.zzvm());
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
