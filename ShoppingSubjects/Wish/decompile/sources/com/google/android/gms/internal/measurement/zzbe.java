package com.google.android.gms.internal.measurement;

public final class zzbe extends zzar {
    private final zzu zzse = new zzu();

    zzbe(zzat zzat) {
        super(zzat);
    }

    /* access modifiers changed from: protected */
    public final void zzac() {
        zzbw().zzz().zzb(this.zzse);
        zzde zzca = zzca();
        String zzaf = zzca.zzaf();
        if (zzaf != null) {
            this.zzse.setAppName(zzaf);
        }
        String zzag = zzca.zzag();
        if (zzag != null) {
            this.zzse.setAppVersion(zzag);
        }
    }

    public final zzu zzdb() {
        zzch();
        return this.zzse;
    }
}
