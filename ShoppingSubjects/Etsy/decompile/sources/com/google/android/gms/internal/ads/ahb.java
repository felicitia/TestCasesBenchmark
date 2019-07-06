package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class ahb implements BaseConnectionCallbacks {
    private final /* synthetic */ agy a;

    ahb(agy agy) {
        this.a = agy;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.a.b) {
            try {
                if (this.a.c != null) {
                    this.a.e = this.a.c.a();
                }
            } catch (DeadObjectException e) {
                gv.b("Unable to obtain a cache service instance.", e);
                this.a.c();
            }
            this.a.b.notifyAll();
        }
    }

    public final void onConnectionSuspended(int i) {
        synchronized (this.a.b) {
            this.a.e = null;
            this.a.b.notifyAll();
        }
    }
}
