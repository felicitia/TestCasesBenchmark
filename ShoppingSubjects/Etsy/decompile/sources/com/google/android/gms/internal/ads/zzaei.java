package com.google.android.gms.internal.ads;

import java.lang.ref.WeakReference;

@bu
public final class zzaei extends zzaer {
    private final WeakReference<cj> zzcen;

    public zzaei(cj cjVar) {
        this.zzcen = new WeakReference<>(cjVar);
    }

    public final void zza(zzaej zzaej) {
        cj cjVar = (cj) this.zzcen.get();
        if (cjVar != null) {
            cjVar.a(zzaej);
        }
    }
}
