package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzat extends zzga<zzat> {
    private static volatile zzat[] zzgx;
    public String name;
    public Long zzgk;
    public zzas[] zzgp;
    private Boolean zzgy;
    public Long zzgz;
    public zzau[] zzha;
    public zzat[] zzhb;
    public zzav[] zzhc;

    private static zzat[] zzbb() {
        if (zzgx == null) {
            synchronized (zzge.zzta) {
                if (zzgx == null) {
                    zzgx = new zzat[0];
                }
            }
        }
        return zzgx;
    }

    public zzat() {
        this.name = null;
        this.zzgy = null;
        this.zzgk = null;
        this.zzgz = null;
        this.zzha = zzau.zzbc();
        this.zzhb = zzbb();
        this.zzhc = zzav.zzbd();
        this.zzgp = zzas.zzba();
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzat)) {
            return false;
        }
        zzat zzat = (zzat) obj;
        if (this.name == null) {
            if (zzat.name != null) {
                return false;
            }
        } else if (!this.name.equals(zzat.name)) {
            return false;
        }
        if (this.zzgy == null) {
            if (zzat.zzgy != null) {
                return false;
            }
        } else if (!this.zzgy.equals(zzat.zzgy)) {
            return false;
        }
        if (this.zzgk == null) {
            if (zzat.zzgk != null) {
                return false;
            }
        } else if (!this.zzgk.equals(zzat.zzgk)) {
            return false;
        }
        if (this.zzgz == null) {
            if (zzat.zzgz != null) {
                return false;
            }
        } else if (!this.zzgz.equals(zzat.zzgz)) {
            return false;
        }
        if (!zzge.equals((Object[]) this.zzha, (Object[]) zzat.zzha) || !zzge.equals((Object[]) this.zzhb, (Object[]) zzat.zzhb) || !zzge.equals((Object[]) this.zzhc, (Object[]) zzat.zzhc) || !zzge.equals((Object[]) this.zzgp, (Object[]) zzat.zzgp)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzat.zzss == null || zzat.zzss.isEmpty();
        }
        return this.zzss.equals(zzat.zzss);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((((((((((((getClass().getName().hashCode() + 527) * 31) + (this.name == null ? 0 : this.name.hashCode())) * 31) + (this.zzgy == null ? 0 : this.zzgy.hashCode())) * 31) + (this.zzgk == null ? 0 : this.zzgk.hashCode())) * 31) + (this.zzgz == null ? 0 : this.zzgz.hashCode())) * 31) + zzge.hashCode((Object[]) this.zzha)) * 31) + zzge.hashCode((Object[]) this.zzhb)) * 31) + zzge.hashCode((Object[]) this.zzhc)) * 31) + zzge.hashCode((Object[]) this.zzgp)) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i = this.zzss.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.name != null) {
            zzfy.zza(1, this.name);
        }
        if (this.zzgy != null) {
            boolean booleanValue = this.zzgy.booleanValue();
            zzfy.zzb(2, 0);
            zzfy.zzk(booleanValue ? (byte) 1 : 0);
        }
        if (this.zzgk != null) {
            zzfy.zzi(4, this.zzgk.longValue());
        }
        if (this.zzgz != null) {
            zzfy.zzi(5, this.zzgz.longValue());
        }
        if (this.zzha != null && this.zzha.length > 0) {
            for (zzau zzau : this.zzha) {
                if (zzau != null) {
                    zzfy.zza(6, (zzgg) zzau);
                }
            }
        }
        if (this.zzhb != null && this.zzhb.length > 0) {
            for (zzat zzat : this.zzhb) {
                if (zzat != null) {
                    zzfy.zza(7, (zzgg) zzat);
                }
            }
        }
        if (this.zzhc != null && this.zzhc.length > 0) {
            for (zzav zzav : this.zzhc) {
                if (zzav != null) {
                    zzfy.zza(8, (zzgg) zzav);
                }
            }
        }
        if (this.zzgp != null && this.zzgp.length > 0) {
            for (zzas zzas : this.zzgp) {
                if (zzas != null) {
                    zzfy.zza(9, (zzgg) zzas);
                }
            }
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.name != null) {
            zzax += zzfy.zzb(1, this.name);
        }
        if (this.zzgy != null) {
            this.zzgy.booleanValue();
            zzax += zzfy.zzz(2) + 1;
        }
        if (this.zzgk != null) {
            zzax += zzfy.zzd(4, this.zzgk.longValue());
        }
        if (this.zzgz != null) {
            zzax += zzfy.zzd(5, this.zzgz.longValue());
        }
        if (this.zzha != null && this.zzha.length > 0) {
            int i = zzax;
            for (zzau zzau : this.zzha) {
                if (zzau != null) {
                    i += zzfy.zzb(6, (zzgg) zzau);
                }
            }
            zzax = i;
        }
        if (this.zzhb != null && this.zzhb.length > 0) {
            int i2 = zzax;
            for (zzat zzat : this.zzhb) {
                if (zzat != null) {
                    i2 += zzfy.zzb(7, (zzgg) zzat);
                }
            }
            zzax = i2;
        }
        if (this.zzhc != null && this.zzhc.length > 0) {
            int i3 = zzax;
            for (zzav zzav : this.zzhc) {
                if (zzav != null) {
                    i3 += zzfy.zzb(8, (zzgg) zzav);
                }
            }
            zzax = i3;
        }
        if (this.zzgp != null && this.zzgp.length > 0) {
            for (zzas zzas : this.zzgp) {
                if (zzas != null) {
                    zzax += zzfy.zzb(9, (zzgg) zzas);
                }
            }
        }
        return zzax;
    }

    public final /* synthetic */ zzgg zza(zzfx zzfx) throws IOException {
        while (true) {
            int zzbs = zzfx.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs != 10) {
                boolean z = true;
                if (zzbs == 16) {
                    if (zzfx.zzck() == 0) {
                        z = false;
                    }
                    this.zzgy = Boolean.valueOf(z);
                } else if (zzbs == 32) {
                    this.zzgk = Long.valueOf(zzfx.zzcl());
                } else if (zzbs == 40) {
                    this.zzgz = Long.valueOf(zzfx.zzcl());
                } else if (zzbs == 50) {
                    int zzb = zzgj.zzb(zzfx, 50);
                    int length = this.zzha == null ? 0 : this.zzha.length;
                    zzau[] zzauArr = new zzau[(zzb + length)];
                    if (length != 0) {
                        System.arraycopy(this.zzha, 0, zzauArr, 0, length);
                    }
                    while (length < zzauArr.length - 1) {
                        zzauArr[length] = new zzau();
                        zzfx.zza((zzgg) zzauArr[length]);
                        zzfx.zzbs();
                        length++;
                    }
                    zzauArr[length] = new zzau();
                    zzfx.zza((zzgg) zzauArr[length]);
                    this.zzha = zzauArr;
                } else if (zzbs == 58) {
                    int zzb2 = zzgj.zzb(zzfx, 58);
                    int length2 = this.zzhb == null ? 0 : this.zzhb.length;
                    zzat[] zzatArr = new zzat[(zzb2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.zzhb, 0, zzatArr, 0, length2);
                    }
                    while (length2 < zzatArr.length - 1) {
                        zzatArr[length2] = new zzat();
                        zzfx.zza((zzgg) zzatArr[length2]);
                        zzfx.zzbs();
                        length2++;
                    }
                    zzatArr[length2] = new zzat();
                    zzfx.zza((zzgg) zzatArr[length2]);
                    this.zzhb = zzatArr;
                } else if (zzbs == 66) {
                    int zzb3 = zzgj.zzb(zzfx, 66);
                    int length3 = this.zzhc == null ? 0 : this.zzhc.length;
                    zzav[] zzavArr = new zzav[(zzb3 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.zzhc, 0, zzavArr, 0, length3);
                    }
                    while (length3 < zzavArr.length - 1) {
                        zzavArr[length3] = new zzav();
                        zzfx.zza((zzgg) zzavArr[length3]);
                        zzfx.zzbs();
                        length3++;
                    }
                    zzavArr[length3] = new zzav();
                    zzfx.zza((zzgg) zzavArr[length3]);
                    this.zzhc = zzavArr;
                } else if (zzbs == 74) {
                    int zzb4 = zzgj.zzb(zzfx, 74);
                    int length4 = this.zzgp == null ? 0 : this.zzgp.length;
                    zzas[] zzasArr = new zzas[(zzb4 + length4)];
                    if (length4 != 0) {
                        System.arraycopy(this.zzgp, 0, zzasArr, 0, length4);
                    }
                    while (length4 < zzasArr.length - 1) {
                        zzasArr[length4] = new zzas();
                        zzfx.zza((zzgg) zzasArr[length4]);
                        zzfx.zzbs();
                        length4++;
                    }
                    zzasArr[length4] = new zzas();
                    zzfx.zza((zzgg) zzasArr[length4]);
                    this.zzgp = zzasArr;
                } else if (!super.zza(zzfx, zzbs)) {
                    return this;
                }
            } else {
                this.name = zzfx.readString();
            }
        }
    }
}
