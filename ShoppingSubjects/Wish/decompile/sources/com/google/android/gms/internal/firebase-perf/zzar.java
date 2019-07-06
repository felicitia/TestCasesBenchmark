package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzar extends zzga<zzar> {
    public zzam zzbi;
    public zzat zzgr;
    public zzap zzgs;
    private zzah zzgt;

    public zzar() {
        this.zzbi = null;
        this.zzgr = null;
        this.zzgs = null;
        this.zzgt = null;
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzar)) {
            return false;
        }
        zzar zzar = (zzar) obj;
        if (this.zzbi == null) {
            if (zzar.zzbi != null) {
                return false;
            }
        } else if (!this.zzbi.equals(zzar.zzbi)) {
            return false;
        }
        if (this.zzgr == null) {
            if (zzar.zzgr != null) {
                return false;
            }
        } else if (!this.zzgr.equals(zzar.zzgr)) {
            return false;
        }
        if (this.zzgs == null) {
            if (zzar.zzgs != null) {
                return false;
            }
        } else if (!this.zzgs.equals(zzar.zzgs)) {
            return false;
        }
        if (this.zzgt == null) {
            if (zzar.zzgt != null) {
                return false;
            }
        } else if (!this.zzgt.equals(zzar.zzgt)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzar.zzss == null || zzar.zzss.isEmpty();
        }
        return this.zzss.equals(zzar.zzss);
    }

    public final int hashCode() {
        int i;
        int i2;
        int i3;
        int i4;
        int hashCode = getClass().getName().hashCode() + 527;
        zzam zzam = this.zzbi;
        int i5 = hashCode * 31;
        int i6 = 0;
        if (zzam == null) {
            i = 0;
        } else {
            i = zzam.hashCode();
        }
        int i7 = i5 + i;
        zzat zzat = this.zzgr;
        int i8 = i7 * 31;
        if (zzat == null) {
            i2 = 0;
        } else {
            i2 = zzat.hashCode();
        }
        int i9 = i8 + i2;
        zzap zzap = this.zzgs;
        int i10 = i9 * 31;
        if (zzap == null) {
            i3 = 0;
        } else {
            i3 = zzap.hashCode();
        }
        int i11 = i10 + i3;
        zzah zzah = this.zzgt;
        int i12 = i11 * 31;
        if (zzah == null) {
            i4 = 0;
        } else {
            i4 = zzah.hashCode();
        }
        int i13 = (i12 + i4) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i6 = this.zzss.hashCode();
        }
        return i13 + i6;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.zzbi != null) {
            zzfy.zza(1, (zzgg) this.zzbi);
        }
        if (this.zzgr != null) {
            zzfy.zza(2, (zzgg) this.zzgr);
        }
        if (this.zzgs != null) {
            zzfy.zza(3, (zzgg) this.zzgs);
        }
        if (this.zzgt != null) {
            zzfy.zze(4, this.zzgt);
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.zzbi != null) {
            zzax += zzfy.zzb(1, (zzgg) this.zzbi);
        }
        if (this.zzgr != null) {
            zzax += zzfy.zzb(2, (zzgg) this.zzgr);
        }
        if (this.zzgs != null) {
            zzax += zzfy.zzb(3, (zzgg) this.zzgs);
        }
        return this.zzgt != null ? zzax + zzbt.zzc(4, (zzdt) this.zzgt) : zzax;
    }

    public final /* synthetic */ zzgg zza(zzfx zzfx) throws IOException {
        while (true) {
            int zzbs = zzfx.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs == 10) {
                if (this.zzbi == null) {
                    this.zzbi = new zzam();
                }
                zzfx.zza((zzgg) this.zzbi);
            } else if (zzbs == 18) {
                if (this.zzgr == null) {
                    this.zzgr = new zzat();
                }
                zzfx.zza((zzgg) this.zzgr);
            } else if (zzbs == 26) {
                if (this.zzgs == null) {
                    this.zzgs = new zzap();
                }
                zzfx.zza((zzgg) this.zzgs);
            } else if (zzbs == 34) {
                this.zzgt = (zzah) zzfx.zza(zzah.zzau());
            } else if (!super.zza(zzfx, zzbs)) {
                return this;
            }
        }
    }
}
