package com.google.android.gms.internal.ads;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.BaseGmsClient.BaseConnectionCallbacks;

final class amo implements BaseConnectionCallbacks {
    final /* synthetic */ amm a;
    private final /* synthetic */ le b;
    private final /* synthetic */ zzsg c;

    amo(amm amm, le leVar, zzsg zzsg) {
        this.a = amm;
        this.b = leVar;
        this.c = zzsg;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        synchronized (this.a.d) {
            if (!this.a.b) {
                this.a.b = true;
                aml d = this.a.a;
                if (d != null) {
                    this.b.a(new amq(this.b, hb.a((Runnable) new amp(this, d, this.b, this.c))), kz.b);
                }
            }
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}
