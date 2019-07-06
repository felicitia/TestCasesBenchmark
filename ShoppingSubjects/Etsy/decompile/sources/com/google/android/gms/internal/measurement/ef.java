package com.google.android.gms.internal.measurement;

import android.content.ComponentName;

final class ef implements Runnable {
    private final /* synthetic */ ComponentName a;
    private final /* synthetic */ zziy b;

    ef(zziy zziy, ComponentName componentName) {
        this.b = zziy;
        this.a = componentName;
    }

    public final void run() {
        this.b.zzaqv.a(this.a);
    }
}
