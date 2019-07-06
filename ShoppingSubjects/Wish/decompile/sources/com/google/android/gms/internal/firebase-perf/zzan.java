package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzan extends zzga<zzan> {
    private static volatile zzan[] zzgd;
    public String key;
    public String value;

    public static zzan[] zzay() {
        if (zzgd == null) {
            synchronized (zzge.zzta) {
                if (zzgd == null) {
                    zzgd = new zzan[0];
                }
            }
        }
        return zzgd;
    }

    public zzan() {
        this.key = null;
        this.value = null;
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzan)) {
            return false;
        }
        zzan zzan = (zzan) obj;
        if (this.key == null) {
            if (zzan.key != null) {
                return false;
            }
        } else if (!this.key.equals(zzan.key)) {
            return false;
        }
        if (this.value == null) {
            if (zzan.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzan.value)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzan.zzss == null || zzan.zzss.isEmpty();
        }
        return this.zzss.equals(zzan.zzss);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + (this.key == null ? 0 : this.key.hashCode())) * 31) + (this.value == null ? 0 : this.value.hashCode())) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i = this.zzss.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.key != null) {
            zzfy.zza(1, this.key);
        }
        if (this.value != null) {
            zzfy.zza(2, this.value);
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.key != null) {
            zzax += zzfy.zzb(1, this.key);
        }
        return this.value != null ? zzax + zzfy.zzb(2, this.value) : zzax;
    }

    public final /* synthetic */ zzgg zza(zzfx zzfx) throws IOException {
        while (true) {
            int zzbs = zzfx.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs == 10) {
                this.key = zzfx.readString();
            } else if (zzbs == 18) {
                this.value = zzfx.readString();
            } else if (!super.zza(zzfx, zzbs)) {
                return this;
            }
        }
    }
}
