package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class ahc implements BaseOnConnectionFailedListener {
    private final /* synthetic */ agy a;

    ahc(agy agy) {
        this.a = agy;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (this.a.b) {
            this.a.e = null;
            if (this.a.c != null) {
                this.a.c = null;
            }
            this.a.b.notifyAll();
        }
    }
}
