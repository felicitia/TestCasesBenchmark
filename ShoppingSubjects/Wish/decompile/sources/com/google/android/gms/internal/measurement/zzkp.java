package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkp extends zzacd<zzkp> {
    private static volatile zzkp[] zzaus;
    public Integer zzate;
    public zzkv zzaut;
    public zzkv zzauu;
    public Boolean zzauv;

    public zzkp() {
        this.zzate = null;
        this.zzaut = null;
        this.zzauu = null;
        this.zzauv = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public static zzkp[] zzlw() {
        if (zzaus == null) {
            synchronized (zzach.zzbzn) {
                if (zzaus == null) {
                    zzaus = new zzkp[0];
                }
            }
        }
        return zzaus;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkp)) {
            return false;
        }
        zzkp zzkp = (zzkp) obj;
        if (this.zzate == null) {
            if (zzkp.zzate != null) {
                return false;
            }
        } else if (!this.zzate.equals(zzkp.zzate)) {
            return false;
        }
        if (this.zzaut == null) {
            if (zzkp.zzaut != null) {
                return false;
            }
        } else if (!this.zzaut.equals(zzkp.zzaut)) {
            return false;
        }
        if (this.zzauu == null) {
            if (zzkp.zzauu != null) {
                return false;
            }
        } else if (!this.zzauu.equals(zzkp.zzauu)) {
            return false;
        }
        if (this.zzauv == null) {
            if (zzkp.zzauv != null) {
                return false;
            }
        } else if (!this.zzauv.equals(zzkp.zzauv)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkp.zzbzd == null || zzkp.zzbzd.isEmpty() : this.zzbzd.equals(zzkp.zzbzd);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((getClass().getName().hashCode() + 527) * 31) + (this.zzate == null ? 0 : this.zzate.hashCode());
        zzkv zzkv = this.zzaut;
        int hashCode2 = (hashCode * 31) + (zzkv == null ? 0 : zzkv.hashCode());
        zzkv zzkv2 = this.zzauu;
        int hashCode3 = ((((hashCode2 * 31) + (zzkv2 == null ? 0 : zzkv2.hashCode())) * 31) + (this.zzauv == null ? 0 : this.zzauv.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode3 + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzate != null) {
            zza += zzacb.zzf(1, this.zzate.intValue());
        }
        if (this.zzaut != null) {
            zza += zzacb.zzb(2, (zzacj) this.zzaut);
        }
        if (this.zzauu != null) {
            zza += zzacb.zzb(3, (zzacj) this.zzauu);
        }
        if (this.zzauv == null) {
            return zza;
        }
        this.zzauv.booleanValue();
        return zza + zzacb.zzaq(4) + 1;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzate != null) {
            zzacb.zze(1, this.zzate.intValue());
        }
        if (this.zzaut != null) {
            zzacb.zza(2, (zzacj) this.zzaut);
        }
        if (this.zzauu != null) {
            zzacb.zza(3, (zzacj) this.zzauu);
        }
        if (this.zzauv != null) {
            zzacb.zza(4, this.zzauv.booleanValue());
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        zzkv zzkv;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl != 8) {
                if (zzvl == 18) {
                    if (this.zzaut == null) {
                        this.zzaut = new zzkv();
                    }
                    zzkv = this.zzaut;
                } else if (zzvl == 26) {
                    if (this.zzauu == null) {
                        this.zzauu = new zzkv();
                    }
                    zzkv = this.zzauu;
                } else if (zzvl == 32) {
                    this.zzauv = Boolean.valueOf(zzaca.zzvm());
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zza(zzkv);
            } else {
                this.zzate = Integer.valueOf(zzaca.zzvn());
            }
        }
    }
}
