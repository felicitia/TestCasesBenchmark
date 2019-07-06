package com.google.android.gms.internal.measurement;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement;

final class bn implements Runnable {
    private final /* synthetic */ bu a;
    private final /* synthetic */ long b;
    private final /* synthetic */ Bundle c;
    private final /* synthetic */ Context d;
    private final /* synthetic */ aq e;
    private final /* synthetic */ PendingResult f;

    bn(bl blVar, bu buVar, long j, Bundle bundle, Context context, aq aqVar, PendingResult pendingResult) {
        this.a = buVar;
        this.b = j;
        this.c = bundle;
        this.d = context;
        this.e = aqVar;
        this.f = pendingResult;
    }

    public final void run() {
        long a2 = this.a.c().h.a();
        long j = this.b;
        if (a2 > 0 && (j >= a2 || j <= 0)) {
            j = a2 - 1;
        }
        if (j > 0) {
            this.c.putLong("click_timestamp", j);
        }
        this.c.putString("_cis", "referrer broadcast");
        AppMeasurement.getInstance(this.d).logEventInternal("auto", "_cmp", this.c);
        this.e.w().a("Install campaign recorded");
        if (this.f != null) {
            this.f.finish();
        }
    }
}
