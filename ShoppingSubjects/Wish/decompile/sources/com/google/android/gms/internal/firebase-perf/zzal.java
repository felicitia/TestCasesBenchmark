package com.google.android.gms.internal.firebase-perf;

import java.io.IOException;

public final class zzal extends zzga<zzal> {
    public String packageName;
    public String versionName;
    public String zzfw;

    public zzal() {
        this.packageName = null;
        this.zzfw = null;
        this.versionName = null;
        this.zzss = null;
        this.zztb = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzal)) {
            return false;
        }
        zzal zzal = (zzal) obj;
        if (this.packageName == null) {
            if (zzal.packageName != null) {
                return false;
            }
        } else if (!this.packageName.equals(zzal.packageName)) {
            return false;
        }
        if (this.zzfw == null) {
            if (zzal.zzfw != null) {
                return false;
            }
        } else if (!this.zzfw.equals(zzal.zzfw)) {
            return false;
        }
        if (this.versionName == null) {
            if (zzal.versionName != null) {
                return false;
            }
        } else if (!this.versionName.equals(zzal.versionName)) {
            return false;
        }
        if (this.zzss == null || this.zzss.isEmpty()) {
            return zzal.zzss == null || zzal.zzss.isEmpty();
        }
        return this.zzss.equals(zzal.zzss);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (((((((getClass().getName().hashCode() + 527) * 31) + (this.packageName == null ? 0 : this.packageName.hashCode())) * 31) + (this.zzfw == null ? 0 : this.zzfw.hashCode())) * 31) + (this.versionName == null ? 0 : this.versionName.hashCode())) * 31;
        if (this.zzss != null && !this.zzss.isEmpty()) {
            i = this.zzss.hashCode();
        }
        return hashCode + i;
    }

    public final void zza(zzfy zzfy) throws IOException {
        if (this.packageName != null) {
            zzfy.zza(1, this.packageName);
        }
        if (this.zzfw != null) {
            zzfy.zza(2, this.zzfw);
        }
        if (this.versionName != null) {
            zzfy.zza(3, this.versionName);
        }
        super.zza(zzfy);
    }

    /* access modifiers changed from: protected */
    public final int zzax() {
        int zzax = super.zzax();
        if (this.packageName != null) {
            zzax += zzfy.zzb(1, this.packageName);
        }
        if (this.zzfw != null) {
            zzax += zzfy.zzb(2, this.zzfw);
        }
        return this.versionName != null ? zzax + zzfy.zzb(3, this.versionName) : zzax;
    }

    public final /* synthetic */ zzgg zza(zzfx zzfx) throws IOException {
        while (true) {
            int zzbs = zzfx.zzbs();
            if (zzbs == 0) {
                return this;
            }
            if (zzbs == 10) {
                this.packageName = zzfx.readString();
            } else if (zzbs == 18) {
                this.zzfw = zzfx.readString();
            } else if (zzbs == 26) {
                this.versionName = zzfx.readString();
            } else if (!super.zza(zzfx, zzbs)) {
                return this;
            }
        }
    }
}
