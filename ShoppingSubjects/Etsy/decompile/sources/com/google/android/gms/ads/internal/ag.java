package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzjj;
import java.lang.ref.WeakReference;

final class ag implements Runnable {
    private final /* synthetic */ WeakReference a;
    private final /* synthetic */ af b;

    ag(af afVar, WeakReference weakReference) {
        this.b = afVar;
        this.a = weakReference;
    }

    public final void run() {
        this.b.d = false;
        zza zza = (zza) this.a.get();
        if (zza != null) {
            zzjj a2 = this.b.c;
            if (zza.zzc(a2)) {
                zza.zzb(a2);
            } else {
                gv.d("Ad is not visible. Not refreshing ad.");
                zza.zzvv.b(a2);
            }
        }
    }
}
