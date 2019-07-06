package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzav extends zzga<zzav> {
    private static volatile zzav[] zzhf;
    public String key;
    public String value;

    public static zzav[] zzbd() {
        if (zzhf == null) {
            synchronized (zzge.zzta) {
                if (zzhf == null) {
                    zzhf = new zzav[0];
                }
            }
        }
        return zzhf;
    }

    public zzav() {
        this.key = null;
        this.value = null;
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzav)) {
            return false;
        }
        zzav zzav = (zzav) obj;
        if (this.key == null) {
            if (zzav.key != null) {
                return false;
            }
        } else if (!this.key.equals(zzav.key)) {
            return false;
        }
        if (this.value == null) {
            if (zzav.value != null) {
                return false;
            }
        } else if (!this.value.equals(zzav.value)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzav.zzss == null || zzav.zzss.isEmpty();
        }
        return this.zzss.equals(zzav.zzss);
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
