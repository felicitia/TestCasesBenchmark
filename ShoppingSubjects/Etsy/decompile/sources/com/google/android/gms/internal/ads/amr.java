package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class amr implements BaseOnConnectionFailedListener {
    private final /* synthetic */ le a;
    private final /* synthetic */ amm b;

    amr(amm amm, le leVar) {
        this.b = amm;
        this.a = leVar;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (this.b.d) {
            this.a.a(new RuntimeException("Connection failed."));
        }
    }
}
