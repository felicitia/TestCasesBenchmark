package com.google.android.gms.iid;

import android.os.Binder;
import android.os.Process;
import android.util.Log;

public final class zzi extends Binder {
    /* access modifiers changed from: private */
    public final zze zzbi;

    zzi(zze zze) {
        this.zzbi = zze;
    }

    public final void zzd(n nVar) {
        if (Binder.getCallingUid() != Process.myUid()) {
            throw new SecurityException("Binding only allowed within app");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "service received new intent via bind strategy");
        }
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "intent being queued for bg execution");
        }
        this.zzbi.zzax.execute(new p(this, nVar));
    }
}
