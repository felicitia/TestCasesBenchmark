package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class zzkv extends zzacd<zzkv> {
    public long[] zzawl;
    public long[] zzawm;
    public zzkq[] zzawn;
    private zzkw[] zzawo;

    public zzkv() {
        this.zzawl = zzacm.zzbzt;
        this.zzawm = zzacm.zzbzt;
        this.zzawn = zzkq.zzlx();
        this.zzawo = zzkw.zzmb();
        this.zzbzd = null;
        this.zzbzo = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzkv)) {
            return false;
        }
        zzkv zzkv = (zzkv) obj;
        if (zzach.equals(this.zzawl, zzkv.zzawl) && zzach.equals(this.zzawm, zzkv.zzawm) && zzach.equals((Object[]) this.zzawn, (Object[]) zzkv.zzawn) && zzach.equals((Object[]) this.zzawo, (Object[]) zzkv.zzawo)) {
            return (this.zzbzd == null || this.zzbzd.isEmpty()) ? zzkv.zzbzd == null || zzkv.zzbzd.isEmpty() : this.zzbzd.equals(zzkv.zzbzd);
        }
        return false;
    }

    public final int hashCode() {
        return ((((((((((getClass().getName().hashCode() + 527) * 31) + zzach.hashCode(this.zzawl)) * 31) + zzach.hashCode(this.zzawm)) * 31) + zzach.hashCode((Object[]) this.zzawn)) * 31) + zzach.hashCode((Object[]) this.zzawo)) * 31) + ((this.zzbzd == null || this.zzbzd.isEmpty()) ? 0 : this.zzbzd.hashCode());
    }

    /* access modifiers changed from: protected */
    public final int zza() {
        int zza = super.zza();
        if (this.zzawl != null && this.zzawl.length > 0) {
            int i = 0;
            for (long zzat : this.zzawl) {
                i += zzacb.zzat(zzat);
            }
            zza = zza + i + (this.zzawl.length * 1);
        }
        if (this.zzawm != null && this.zzawm.length > 0) {
            int i2 = 0;
            for (long zzat2 : this.zzawm) {
                i2 += zzacb.zzat(zzat2);
            }
            zza = zza + i2 + (this.zzawm.length * 1);
        }
        if (this.zzawn != null && this.zzawn.length > 0) {
            int i3 = zza;
            for (zzkq zzkq : this.zzawn) {
                if (zzkq != null) {
                    i3 += zzacb.zzb(3, (zzacj) zzkq);
                }
            }
            zza = i3;
        }
        if (this.zzawo != null && this.zzawo.length > 0) {
            for (zzkw zzkw : this.zzawo) {
                if (zzkw != null) {
                    zza += zzacb.zzb(4, (zzacj) zzkw);
                }
            }
        }
        return zza;
    }

    public final void zza(zzacb zzacb) throws IOException {
        if (this.zzawl != null && this.zzawl.length > 0) {
            for (long zza : this.zzawl) {
                zzacb.zza(1, zza);
            }
        }
        if (this.zzawm != null && this.zzawm.length > 0) {
            for (long zza2 : this.zzawm) {
                zzacb.zza(2, zza2);
            }
        }
        if (this.zzawn != null && this.zzawn.length > 0) {
            for (zzkq zzkq : this.zzawn) {
                if (zzkq != null) {
                    zzacb.zza(3, (zzacj) zzkq);
                }
            }
        }
        if (this.zzawo != null && this.zzawo.length > 0) {
            for (zzkw zzkw : this.zzawo) {
                if (zzkw != null) {
                    zzacb.zza(4, (zzacj) zzkw);
                }
            }
        }
        super.zza(zzacb);
    }

    public final /* synthetic */ zzacj zzb(zzaca zzaca) throws IOException {
        int i;
        while (true) {
            int zzvl = zzaca.zzvl();
            if (zzvl == 0) {
                return this;
            }
            if (zzvl != 8) {
                if (zzvl == 10) {
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position = zzaca.getPosition();
                    int i2 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvo();
                        i2++;
                    }
                    zzaca.zzam(position);
                    int length = this.zzawl == null ? 0 : this.zzawl.length;
                    long[] jArr = new long[(i2 + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzawl, 0, jArr, 0, length);
                    }
                    while (length < jArr.length) {
                        jArr[length] = zzaca.zzvo();
                        length++;
                    }
                    this.zzawl = jArr;
                } else if (zzvl == 16) {
                    int zzb = zzacm.zzb(zzaca, 16);
                    int length2 = this.zzawm == null ? 0 : this.zzawm.length;
                    long[] jArr2 = new long[(zzb + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzawm, 0, jArr2, 0, length2);
                    }
                    while (length2 < jArr2.length - 1) {
                        jArr2[length2] = zzaca.zzvo();
                        zzaca.zzvl();
                        length2++;
                    }
                    jArr2[length2] = zzaca.zzvo();
                    this.zzawm = jArr2;
                } else if (zzvl == 18) {
                    i = zzaca.zzaf(zzaca.zzvn());
                    int position2 = zzaca.getPosition();
                    int i3 = 0;
                    while (zzaca.zzvr() > 0) {
                        zzaca.zzvo();
                        i3++;
                    }
                    zzaca.zzam(position2);
                    int length3 = this.zzawm == null ? 0 : this.zzawm.length;
                    long[] jArr3 = new long[(i3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzawm, 0, jArr3, 0, length3);
                    }
                    while (length3 < jArr3.length) {
                        jArr3[length3] = zzaca.zzvo();
                        length3++;
                    }
                    this.zzawm = jArr3;
                } else if (zzvl == 26) {
                    int zzb2 = zzacm.zzb(zzaca, 26);
                    int length4 = this.zzawn == null ? 0 : this.zzawn.length;
                    zzkq[] zzkqArr = new zzkq[(zzb2 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzawn, 0, zzkqArr, 0, length4);
                    }
                    while (length4 < zzkqArr.length - 1) {
                        zzkqArr[length4] = new zzkq();
                        zzaca.zza(zzkqArr[length4]);
                        zzaca.zzvl();
                        length4++;
                    }
                    zzkqArr[length4] = new zzkq();
                    zzaca.zza(zzkqArr[length4]);
                    this.zzawn = zzkqArr;
                } else if (zzvl == 34) {
                    int zzb3 = zzacm.zzb(zzaca, 34);
                    int length5 = this.zzawo == null ? 0 : this.zzawo.length;
                    zzkw[] zzkwArr = new zzkw[(zzb3 + length5)];
                    if (length5 != 0) {
                        System.arraycopy(this.zzawo, 0, zzkwArr, 0, length5);
                    }
                    while (length5 < zzkwArr.length - 1) {
                        zzkwArr[length5] = new zzkw();
                        zzaca.zza(zzkwArr[length5]);
                        zzaca.zzvl();
                        length5++;
                    }
                    zzkwArr[length5] = new zzkw();
                    zzaca.zza(zzkwArr[length5]);
                    this.zzawo = zzkwArr;
                } else if (!super.zza(zzaca, zzvl)) {
                    return this;
                }
                zzaca.zzal(i);
            } else {
                int zzb4 = zzacm.zzb(zzaca, 8);
                int length6 = this.zzawl == null ? 0 : this.zzawl.length;
                long[] jArr4 = new long[(zzb4 + length6)];
                if (length6 != 0) {
                    System.arraycopy(this.zzawl, 0, jArr4, 0, length6);
                }
                while (length6 < jArr4.length - 1) {
                    jArr4[length6] = zzaca.zzvo();
                    zzaca.zzvl();
                    length6++;
                }
                jArr4[length6] = zzaca.zzvo();
                this.zzawl = jArr4;
            }
        }
    }
}
