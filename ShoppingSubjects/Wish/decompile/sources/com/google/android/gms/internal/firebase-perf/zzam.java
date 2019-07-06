package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzam extends zzga<zzam> {
    public String zzfx;
    public String zzfy;
    public zzal zzfz;
    private zzaj zzga;
    public Integer zzgb;
    public zzan[] zzgc;

    public zzam() {
        this.zzfx = null;
        this.zzfy = null;
        this.zzfz = null;
        this.zzga = null;
        this.zzgb = null;
        this.zzgc = zzan.zzay();
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzam)) {
            return false;
        }
        zzam zzam = (zzam) obj;
        if (this.zzfx == null) {
            if (zzam.zzfx != null) {
                return false;
            }
        } else if (!this.zzfx.equals(zzam.zzfx)) {
            return false;
        }
        if (this.zzfy == null) {
            if (zzam.zzfy != null) {
                return false;
            }
        } else if (!this.zzfy.equals(zzam.zzfy)) {
            return false;
        }
        if (this.zzfz == null) {
            if (zzam.zzfz != null) {
                return false;
            }
        } else if (!this.zzfz.equals(zzam.zzfz)) {
            return false;
        }
        if (this.zzga == null) {
            if (zzam.zzga != null) {
                return false;
            }
        } else if (!this.zzga.equals(zzam.zzga)) {
            return false;
        }
        if (this.zzgb == null) {
            if (zzam.zzgb != null) {
                return false;
            }
        } else if (!this.zzgb.equals(zzam.zzgb)) {
            return false;
        }
        if (!zzge.equals((Object[]) this.zzgc, (Object[]) zzam.zzgc)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzam.zzss == null || zzam.zzss.isEmpty();
        }
        return this.zzss.equals(zzam.zzss);
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3 = 0;
        int hashCode = ((((getClass().getName().hashCode() + 527) * 31) + (this.zzfx == null ? 0 : this.zzfx.hashCode())) * 31) + (this.zzfy == null ? 0 : this.zzfy.hashCode());
        zzal zzal = this.zzfz;
        int i4 = hashCode * 31;
        if (zzal == null) {
            i = 0;
        } else {
            i = zzal.hashCode();
        }
        int i5 = i4 + i;
        zzaj zzaj = this.zzga;
        int i6 = i5 * 31;
        if (zzaj == null) {
            i2 = 0;
        } else {
            i2 = zzaj.hashCode();
        }
        int intValue = (((((i6 + i2) * 31) + (this.zzgb == null ? 0 : this.zzgb.intValue())) * 31) + zzge.hashCode((Object[]) this.zzgc)) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i3 = this.zzss.hashCode();
        }
        return intValue + i3;
    }

    public final void zza(zzfy zzfy2) throws IOException {
        if (this.zzfx != null) {
            zzfy2.zza(1, this.zzfx);
        }
        if (this.zzfy != null) {
            zzfy2.zza(2, this.zzfy);
        }
        if (this.zzfz != null) {
            zzfy2.zza(3, (zzgg) this.zzfz);
        }
        if (this.zzga != null) {
            zzfy2.zze(4, this.zzga);
        }
        if (this.zzgb != null) {
            zzfy2.zzc(5, this.zzgb.intValue());
        }
        if (this.zzgc != null && this.zzgc.length > 0) {
            for (zzan zzan : this.zzgc) {
                if (zzan != null) {
                    zzfy2.zza(6, (zzgg) zzan);
                }
            }
        }
        super.zza(zzfy2);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.zzfx != null) {
            zzax += zzfy.zzb(1, this.zzfx);
        }
        if (this.zzfy != null) {
            zzax += zzfy.zzb(2, this.zzfy);
        }
        if (this.zzfz != null) {
            zzax += zzfy.zzb(3, (zzgg) this.zzfz);
        }
        if (this.zzga != null) {
            zzax += zzbt.zzc(4, (zzdt) this.zzga);
        }
        if (this.zzgb != null) {
            zzax += zzfy.zzg(5, this.zzgb.intValue());
        }
        if (this.zzgc != null && this.zzgc.length > 0) {
            for (zzan zzan : this.zzgc) {
                if (zzan != null) {
                    zzax += zzfy.zzb(6, (zzgg) zzan);
                }
            }
        }
        return zzax;
    }

    /* access modifiers changed from: private */
    /* renamed from: zzb */
    public final zzam zza(zzfx zzfx2) throws IOException {
        int zzck;
        while (true) {
            int zzbs = zzfx2.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs == 10) {
                this.zzfx = zzfx2.readString();
            } else if (zzbs == 18) {
                this.zzfy = zzfx2.readString();
            } else if (zzbs == 26) {
                if (this.zzfz == null) {
                    this.zzfz = new zzal();
                }
                zzfx2.zza((zzgg) this.zzfz);
            } else if (zzbs == 34) {
                this.zzga = (zzaj) zzfx2.zza(zzaj.zzau());
            } else if (zzbs == 40) {
                try {
                    zzck = zzfx2.zzck();
                    if (zzck < 0 || zzck > 3) {
                        StringBuilder sb = new StringBuilder(55);
                        sb.append(zzck);
                        sb.append(" is not a valid enum ApplicationProcessState");
                    } else {
                        this.zzgb = Integer.valueOf(zzck);
                    }
                } catch (IllegalArgumentException unused) {
                    zzfx2.zzax(zzfx2.getPosition());
                    zza(zzfx2, zzbs);
                }
            } else if (zzbs == 50) {
                int zzb = zzgj.zzb(zzfx2, 50);
                int length = this.zzgc == null ? 0 : this.zzgc.length;
                zzan[] zzanArr = new zzan[(zzb + length)];
                if (length != 0) {
                    System.arraycopy(this.zzgc, 0, zzanArr, 0, length);
                }
                while (length < zzanArr.length - 1) {
                    zzanArr[length] = new zzan();
                    zzfx2.zza((zzgg) zzanArr[length]);
                    zzfx2.zzbs();
                    length++;
                }
                zzanArr[length] = new zzan();
                zzfx2.zza((zzgg) zzanArr[length]);
                this.zzgc = zzanArr;
            } else if (!super.zza(zzfx2, zzbs)) {
                return this;
            }
        }
        StringBuilder sb2 = new StringBuilder(55);
        sb2.append(zzck);
        sb2.append(" is not a valid enum ApplicationProcessState");
        throw new IllegalArgumentException(sb2.toString());
    }
}
