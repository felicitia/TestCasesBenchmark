package com.google.android.gms.iid;

import android.util.Log;

final class p implements Runnable {
    private final /* synthetic */ n a;
    private final /* synthetic */ zzi b;

    p(zzi zzi, n nVar) {
        this.b = zzi;
        this.a = nVar;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.b.zzbi.handleIntent(this.a.a);
        this.a.a();
    }
}
