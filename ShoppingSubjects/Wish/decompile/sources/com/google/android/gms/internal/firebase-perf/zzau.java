package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzau extends zzga<zzau> {
    private static volatile zzau[] zzhd;
    public String key;
    public Long zzhe;

    public static zzau[] zzbc() {
        if (zzhd == null) {
            synchronized (zzge.zzta) {
                if (zzhd == null) {
                    zzhd = new zzau[0];
                }
            }
        }
        return zzhd;
    }

    public zzau() {
        this.key = null;
        this.zzhe = null;
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzau)) {
            return false;
        }
        zzau zzau = (zzau) obj;
        if (this.key == null) {
            if (zzau.key != null) {
                return false;
            }
        } else if (!this.key.equals(zzau.key)) {
            return false;
        }
        if (this.zzhe == null) {
            if (zzau.zzhe != null) {
                return false;
            }
        } else if (!this.zzhe.equals(zzau.zzhe)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzau.zzss == null || zzau.zzss.isEmpty();
        }
        return this.zzss.equals(zzau.zzss);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.zzhe == null ? 0 : this.zzhe.hashCode())) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i = this.zzss.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.key != null) {
            zzfy.zza(1, this.key);
        }
        if (this.zzhe != null) {
            zzfy.zzi(2, this.zzhe.longValue());
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.key != null) {
            zzax += zzfy.zzb(1, this.key);
        }
        return this.zzhe != null ? zzax + zzfy.zzd(2, this.zzhe.longValue()) : zzax;
    }

    public final /* synthetic */ zzgg zza(zzfx zzfx) throws IOException {
        while (true) {
            int zzbs = zzfx.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs == 10) {
                this.key = zzfx.readString();
            } else if (zzbs == 16) {
                this.zzhe = Long.valueOf(zzfx.zzcl());
            } else if (!super.zza(zzfx, zzbs)) {
                return this;
            }
        }
    }
}
