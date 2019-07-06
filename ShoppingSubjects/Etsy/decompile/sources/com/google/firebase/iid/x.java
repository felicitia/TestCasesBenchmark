package com.google.firebase.iid;

import android.util.Log;

final class x implements Runnable {
    private final /* synthetic */ v a;
    private final /* synthetic */ zzf b;

    x(zzf zzf, v vVar) {
        this.b = zzf;
        this.a = vVar;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.b.zzw.zzd(this.a.a);
        this.a.a();
    }
}
