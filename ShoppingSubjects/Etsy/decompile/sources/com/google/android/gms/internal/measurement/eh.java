package com.google.android.gms.internal.measurement;

import android.content.ComponentName;
import android.content.Context;

final class eh implements Runnable {
    private final /* synthetic */ zziy a;

    eh(zziy zziy) {
        this.a = zziy;
    }

    public final void run() {
        dq dqVar = this.a.zzaqv;
        Context n = this.a.zzaqv.n();
        this.a.zzaqv.u();
        dqVar.a(new ComponentName(n, "com.google.android.gms.measurement.AppMeasurementService"));
    }
}
