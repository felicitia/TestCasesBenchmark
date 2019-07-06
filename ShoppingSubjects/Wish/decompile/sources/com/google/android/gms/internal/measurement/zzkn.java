package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkn extends zzacd<zzkn> {
    public String zzafa;
    public Long zzaum;
    private Integer zzaun;
    public zzko[] zzauo;
    public zzkm[] zzaup;
    public zzkg[] zzauq;

    public zzkn() {
        this.zzaum = null;
        this.zzafa = null;
        this.zzaun = null;
        this.zzauo = zzko.zzlv();
        this.zzaup = zzkm.zzlu();
        this.zzauq = zzkg.zzlq();
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkn)) {
            return false;
        }
        zzkn zzkn = (zzkn) obj;
        if (this.zzaum == null) {
            if (zzkn.zzaum != null) {
                return false;
            }
        } else if (!this.zzaum.equals(zzkn.zzaum)) {
            return false;
        }
        if (this.zzafa == null) {
            if (zzkn.zzafa != null) {
                return false;
            }
        } else if (!this.zzafa.equals(zzkn.zzafa)) {
            return false;
        }
        if (this.zzaun == null) {
            if (zzkn.zzaun != null) {
                return false;
            }
        } else if (!this.zzaun.equals(zzkn.zzaun)) {
            return false;
        }
        if (zzach.equals((Object[]) this.zzauo, (Object[]) zzkn.zzauo) && zzach.equals((Object[]) this.zzaup, (Object[]) zzkn.zzaup) && zzach.equals((Object[]) this.zzauq, (Object[]) zzkn.zzauq)) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkn.zzbzd == null || zzkn.zzbzd.isEmpty() : this.zzbzd.equals(zzkn.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.zzaum == null ? 0 : this.zzaum.hashCode())) * 31) + (this.zzafa == null ? 0 : this.zzafa.hashCode())) * 31) + (this.zzaun == null ? 0 : this.zzaun.hashCode())) * 31) + zzach.hashCode((Object[]) this.zzauo)) * 31) + zzach.hashCode((Object[]) this.zzaup)) * 31) + zzach.hashCode((Object[]) this.zzauq)) * 31;
        if (this.zzbzd != null && !this.zzbzd.isEmpty()) {
            i = this.zzbzd.hashCode();
        }
        return hashCode + i;
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzaum != null) {
            zza += zzacb.zzc(1, this.zzaum.longValue());
        }
        if (this.zzafa != null) {
            zza += zzacb.zzc(2, this.zzafa);
        }
        if (this.zzaun != null) {
            zza += zzacb.zzf(3, this.zzaun.intValue());
        }
        if (this.zzauo != null && this.zzauo.length > 0) {
            int i = zza;
            for (zzko zzko : this.zzauo) {
                if (zzko != null) {
                    i += zzacb.zzb(4, (zzacj) zzko);
                }
            }
            zza = i;
        }
        if (this.zzaup != null && this.zzaup.length > 0) {
            int i2 = zza;
            for (zzkm zzkm : this.zzaup) {
                if (zzkm != null) {
                    i2 += zzacb.zzb(5, (zzacj) zzkm);
                }
            }
            zza = i2;
        }
        if (this.zzauq != null && this.zzauq.length > 0) {
            for (zzkg zzkg : this.zzauq) {
                if (zzkg != null) {
                    zza += zzacb.zzb(6, (zzacj) zzkg);
                }
            }
        }
        return zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzaum != null) {
            zzacb.zzb(1, this.zzaum.longValue());
        }
        if (this.zzafa != null) {
            zzacb.zzb(2, this.zzafa);
        }
        if (this.zzaun != null) {
            zzacb.zze(3, this.zzaun.intValue());
        }
        if (this.zzauo != null && this.zzauo.length > 0) {
            for (zzko zzko : this.zzauo) {
                if (zzko != null) {
                    zzacb.zza(4, (zzacj) zzko);
                }
            }
        }
        if (this.zzaup != null && this.zzaup.length > 0) {
            for (zzkm zzkm : this.zzaup) {
                if (zzkm != null) {
                    zzacb.zza(5, (zzacj) zzkm);
                }
            }
        }
        if (this.zzauq != null && this.zzauq.length > 0) {
            for (zzkg zzkg : this.zzauq) {
                if (zzkg != null) {
                    zzacb.zza(6, (zzacj) zzkg);
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
            if (zzvl == 8) {
                this.zzaum = Long.valueOf(zzaca.zzvo());
            } else if (zzvl == 18) {
                this.zzafa = zzaca.readString();
            } else if (zzvl == 24) {
                this.zzaun = Integer.valueOf(zzaca.zzvn());
            } else if (zzvl == 34) {
                int zzb = zzacm.zzb(zzaca, 34);
                int length = this.zzauo == null ? 0 : this.zzauo.length;
                zzko[] zzkoArr = new zzko[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzauo, 0, zzkoArr, 0, length);
                }
                while (length < zzkoArr.length - 1) {
                    zzkoArr[length] = new zzko();
                    zzaca.zza(zzkoArr[length]);
                    zzaca.zzvl();
                    length++;
                }
                zzkoArr[length] = new zzko();
                zzaca.zza(zzkoArr[length]);
                this.zzauo = zzkoArr;
            } else if (zzvl == 42) {
                int zzb2 = zzacm.zzb(zzaca, 42);
                int length2 = this.zzaup == null ? 0 : this.zzaup.length;
                zzkm[] zzkmArr = new zzkm[(zzb2 + length2)];
                if (length2 != 0) {
                    System.arraycopy(this.zzaup, 0, zzkmArr, 0, length2);
                }
                while (length2 < zzkmArr.length - 1) {
                    zzkmArr[length2] = new zzkm();
                    zzaca.zza(zzkmArr[length2]);
                    zzaca.zzvl();
                    length2++;
                }
                zzkmArr[length2] = new zzkm();
                zzaca.zza(zzkmArr[length2]);
                this.zzaup = zzkmArr;
            } else if (zzvl == 50) {
                int zzb3 = zzacm.zzb(zzaca, 50);
                int length3 = this.zzauq == null ? 0 : this.zzauq.length;
                zzkg[] zzkgArr = new zzkg[(zzb3 + length3)];
                if (length3 != 0) {
                    System.arraycopy(this.zzauq, 0, zzkgArr, 0, length3);
                }
                while (length3 < zzkgArr.length - 1) {
                    zzkgArr[length3] = new zzkg();
                    zzaca.zza(zzkgArr[length3]);
                    zzaca.zzvl();
                    length3++;
                }
                zzkgArr[length3] = new zzkg();
                zzaca.zza(zzkgArr[length3]);
                this.zzauq = zzkgArr;
            } else if (!super.zza(zzaca, zzvl)) {
                return this;
            }
        }
    }
}
