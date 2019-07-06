package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkl extends zzacd<zzkl> {
    public Integer zzaue;
    public String zzauf;
    public Boolean zzaug;
    public String[] zzauh;

    public zzkl() {
        this.zzaue = null;
        this.zzauf = null;
        this.zzaug = null;
        this.zzauh = zzacm.zzbzx;
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: zze */
    public final zzkl zzb(zzaca zzaca) throws IOException {
        int zzvn;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl == 8) {
                try {
                    zzvn = zzaca.zzvn();
                    if (zzvn < 0 || zzvn > 6) {
                        StringBuilder sb = new StringBuilder(41);
                        sb.append(zzvn);
                        sb.append(" is not a valid enum MatchType");
                    } else {
                        this.zzaue = Integer.valueOf(zzvn);
                    }
                } catch (IllegalArgumentException unused) {
                    zzaca.zzam(zzaca.getPosition());
                    zza(zzaca, zzvl);
                }
            } else if (zzvl == 18) {
                this.zzauf = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzaug = Boolean.valueOf(zzaca.zzvm());
            } else if (zzvl == 34) {
                int zzb = zzacm.zzb(zzaca, 34);
                int length = this.zzauh == null ? 0 : this.zzauh.length;
                String[] strArr = new String[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzauh, 0, strArr, 0, length);
                }
                while (length < strArr.length - 1) {
                    strArr[length] = zzaca.readString();
                    zzaca.zzvl();
                    length++;
                }
                strArr[length] = zzaca.readString();
                this.zzauh = strArr;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(41);
        sb2.append(zzvn);
        sb2.append(" is not a valid enum MatchType");
        throw new IllegalArgumentException(sb2.toString());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkl)) {
            return false;
        }
        zzkl zzkl = (zzkl) obj;
        if (this.zzaue == null) {
            if (zzkl.zzaue != null) {
                return false;
            }
        } else if (!this.zzaue.equals(zzkl.zzaue)) {
            return false;
        }
        if (this.zzauf == null) {
            if (zzkl.zzauf != null) {
                return false;
            }
        } else if (!this.zzauf.equals(zzkl.zzauf)) {
            return false;
        }
        if (this.zzaug == null) {
            if (zzkl.zzaug != null) {
                return false;
            }
        } else if (!this.zzaug.equals(zzkl.zzaug)) {
            return false;
        }
        if (!zzach.equals((Object[]) this.zzauh, (Object[]) zzkl.zzauh)) {
            return false;
        }
        return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkl.zzbzd == null || zzkl.zzbzd.isEmpty() : this.zzbzd.equals(zzkl.zzbzd);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzaue == null ? 0 : this.zzaue.intValue())) * 31) + (this.zzauf == null ? 0 : this.zzauf.hashCode())) * 31) + (this.zzaug == null ? 0 : this.zzaug.hashCode())) * 31) + zzach.hashCode((Object[]) this.zzauh)) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzaue != null) {
            zza += zzacb.zzf(1, this.zzaue.intValue());
        }
        if (this.zzauf != null) {
            zza += zzacb.zzc(2, this.zzauf);
        }
        if (this.zzaug != null) {
            this.zzaug.booleanValue();
            zza += zzacb.zzaq(3) + 1;
        }
        if (this.zzauh == null || this.zzauh.length <= 0) {
            return zza;
        }
        int i = 0;
        int i2 = 0;
        for (String str : this.zzauh) {
            if (str != null) {
                i2++;
                i += zzacb.zzfr(str);
            }
        }
        return zza + i + (i2 * 1);
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzaue != null) {
            zzacb.zze(1, this.zzaue.intValue());
        }
        if (this.zzauf != null) {
            zzacb.zzb(2, this.zzauf);
        }
        if (this.zzaug != null) {
            zzacb.zza(3, this.zzaug.booleanValue());
        }
        if (this.zzauh != null && this.zzauh.length > 0) {
            for (String str : this.zzauh) {
                if (str != null) {
                    zzacb.zzb(4, str);
                }
            }
        }
        super.zza(zzacb);
    }
}
