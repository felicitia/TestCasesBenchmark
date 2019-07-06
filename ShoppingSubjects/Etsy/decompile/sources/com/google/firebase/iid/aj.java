package com.google.firebase.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.f;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class aj {
    private static aj a;
    /* access modifiers changed from: private */
    public final Context b;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService c;
    private al d = new al(this);
    private int e = 1;

    @VisibleForTesting
    private aj(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.c = scheduledExecutorService;
        this.b = context.getApplicationContext();
    }

    private final synchronized int a() {
        int i;
        i = this.e;
        this.e = i + 1;
        return i;
    }

    private final synchronized <T> f<T> a(e<T> eVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(eVar);
            StringBuilder sb = new StringBuilder(9 + String.valueOf(valueOf).length());
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.d.a((e) eVar)) {
            this.d = new al(this);
            this.d.a((e) eVar);
        }
        return eVar.b.a();
    }

    public static synchronized aj a(Context context) {
        aj ajVar;
        synchronized (aj.class) {
            if (a == null) {
                a = new aj(context, Executors.newSingleThreadScheduledExecutor());
            }
            ajVar = a;
        }
        return ajVar;
    }

    public final f<Bundle> a(int i, Bundle bundle) {
        return a((e<T>) new f<T>(a(), 1, bundle));
    }
}
