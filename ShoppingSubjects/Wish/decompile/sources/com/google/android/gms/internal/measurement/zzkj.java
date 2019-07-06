package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkj extends zzacd<zzkj> {
    public Integer zzatw;
    public Boolean zzatx;
    public String zzaty;
    public String zzatz;
    public String zzaua;

    public zzkj() {
        this.zzatw = null;
        this.zzatx = null;
        this.zzaty = null;
        this.zzatz = null;
        this.zzaua = null;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzd */
    public final zzkj zzb(zzaca zzaca) throws IOException {
        int zzvn;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                try {
                    zzvn = zzaca.zzvn();
                    if (zzvn < 0 || zzvn > 4) {
                        StringBuilder sb = new StringBuilder(46);
                        sb.append(zzvn);
                        sb.append(" is not a valid enum ComparisonType");
                    } else {
                        this.zzatw = Integer.valueOf(zzvn);
                    }
                } catch (IllegalArgumentException unused) {
                    zzaca.zzam(zzaca.getPosition());
                    zza(zzaca, zzvl);
                }
            } else if (zzvl == 16) {
                this.zzatx = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 26) {
                this.zzaty = zzaca.readString();
            } else if (zzvl == 34) {
                this.zzatz = zzaca.readString();
            } else if (zzvl == 42) {
                this.zzaua = zzaca.readString();
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(46);
        sb2.append(zzvn);
        sb2.append(" is not a valid enum ComparisonType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkj)) {
            return false;
        }
        zzkj zzkj = (zzkj) obj;
        if (this.zzatw == null) {
            if (zzkj.zzatw != null) {
                return false;
            }
        } else if (!this.zzatw.equals(zzkj.zzatw)) {
            return false;
        }
        if (this.zzatx == null) {
            if (zzkj.zzatx != null) {
                return false;
            }
        } else if (!this.zzatx.equals(zzkj.zzatx)) {
            return false;
        }
        if (this.zzaty == null) {
            if (zzkj.zzaty != null) {
                return false;
            }
        } else if (!this.zzaty.equals(zzkj.zzaty)) {
            return false;
        }
        if (this.zzatz == null) {
            if (zzkj.zzatz != null) {
                return false;
            }
        } else if (!this.zzatz.equals(zzkj.zzatz)) {
            return false;
        }
        if (this.zzaua == null) {
            if (zzkj.zzaua != null) {
                return false;
            }
        } else if (!this.zzaua.equals(zzkj.zzaua)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkj.zzbzd == null || zzkj.zzbzd.isEmpty() : this.zzbzd.equals(zzkj.zzbzd);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzatw == null ? 0 : this.zzatw.intValue())) * 31) + (this.zzatx == null ? 0 : this.zzatx.hashCode())) * 31) + (this.zzaty == null ? 0 : this.zzaty.hashCode())) * 31) + (this.zzatz == null ? 0 : this.zzatz.hashCode())) * 31) + (this.zzaua == null ? 0 : this.zzaua.hashCode())) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzatw != null) {
            zza += zzacb.zzf(1, this.zzatw.intValue());
        }
        if (this.zzatx != null) {
            this.zzatx.booleanValue();
            zza += zzacb.zzaq(2) + 1;
        }
        if (this.zzaty != null) {
            zza += zzacb.zzc(3, this.zzaty);
        }
        if (this.zzatz != null) {
            zza += zzacb.zzc(4, this.zzatz);
        }
        return this.zzaua != null ? zza + zzacb.zzc(5, this.zzaua) : zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzatw != null) {
            zzacb.zze(1, this.zzatw.intValue());
        }
        if (this.zzatx != null) {
            zzacb.zza(2, this.zzatx.booleanValue());
        }
        if (this.zzaty != null) {
            zzacb.zzb(3, this.zzaty);
        }
        if (this.zzatz != null) {
            zzacb.zzb(4, this.zzatz);
        }
        if (this.zzaua != null) {
            zzacb.zzb(5, this.zzaua);
        }
        super.zza(zzacb);
    }
}
