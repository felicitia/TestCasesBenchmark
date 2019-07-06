package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.annotation.MainThread;

final class bj implements ServiceConnection {
    final /* synthetic */ bh a;

    private bj(bh bhVar) {
        this.a = bhVar;
    }

    @MainThread
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.a.b.r().i().a("Install Referrer connection returned with null binder");
            return;
        }
        try {
            this.a.a = zzs.zza(iBinder);
            if (this.a.a == null) {
                this.a.b.r().i().a("Install Referrer Service implementation was not found");
                return;
            }
            this.a.b.r().k().a("Install Referrer Service connected");
            this.a.b.q().a((Runnable) new bk(this));
        } catch (Exception e) {
            this.a.b.r().i().a("Exception occurred while calling Install Referrer API", e);
        }
    }

    @MainThread
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.a = null;
        this.a.b.r().k().a("Install Referrer Service disconnected");
    }
}
