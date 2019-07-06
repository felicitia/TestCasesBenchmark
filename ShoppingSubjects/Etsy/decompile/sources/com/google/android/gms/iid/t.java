package com.google.android.gms.iid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.util.Log;
import com.google.android.gms.tasks.f;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public final class t {
    /* access modifiers changed from: private */
    public final Context a;
    /* access modifiers changed from: private */
    public final ScheduledExecutorService b;
    private v c;
    private int d;

    public t(Context context) {
        this(context, Executors.newSingleThreadScheduledExecutor());
    }

    @VisibleForTesting
    private t(Context context, ScheduledExecutorService scheduledExecutorService) {
        this.c = new v(this);
        this.d = 1;
        this.a = context.getApplicationContext();
        this.b = scheduledExecutorService;
    }

    private final synchronized int a() {
        int i;
        i = this.d;
        this.d = i + 1;
        return i;
    }

    private final synchronized <T> f<T> a(ab<T> abVar) {
        if (Log.isLoggable("MessengerIpcClient", 3)) {
            String valueOf = String.valueOf(abVar);
            StringBuilder sb = new StringBuilder(9 + String.valueOf(valueOf).length());
            sb.append("Queueing ");
            sb.append(valueOf);
            Log.d("MessengerIpcClient", sb.toString());
        }
        if (!this.c.a((ab) abVar)) {
            this.c = new v(this);
            this.c.a((ab) abVar);
        }
        return abVar.b.a();
    }

    public final f<Bundle> a(int i, Bundle bundle) {
        return a((ab<T>) new b<T>(a(), 1, bundle));
    }
}
