package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;

class az extends BroadcastReceiver {
    @VisibleForTesting
    private static final String a = "com.google.android.gms.internal.measurement.az";
    /* access modifiers changed from: private */
    public final ey b;
    private boolean c;
    private boolean d;

    az(ey eyVar) {
        Preconditions.checkNotNull(eyVar);
        this.b = eyVar;
    }

    @WorkerThread
    public final void a() {
        this.b.i();
        this.b.q().d();
        if (!this.c) {
            this.b.n().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.d = this.b.c().f();
            this.b.r().w().a("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.d));
            this.c = true;
        }
    }

    @WorkerThread
    public final void b() {
        this.b.i();
        this.b.q().d();
        this.b.q().d();
        if (this.c) {
            this.b.r().w().a("Unregistering connectivity change receiver");
            this.c = false;
            this.d = false;
            try {
                this.b.n().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.b.r().h_().a("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.b.i();
        String action = intent.getAction();
        this.b.r().w().a("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean f = this.b.c().f();
            if (this.d != f) {
                this.d = f;
                this.b.q().a((Runnable) new ba(this, f));
            }
            return;
        }
        this.b.r().i().a("NetworkBroadcastReceiver received unknown action", action);
    }
}
