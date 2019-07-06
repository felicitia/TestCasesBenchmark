package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.os;

final /* synthetic */ class bi implements os {
    private final ga a;
    private final Runnable b;

    bi(ga gaVar, Runnable runnable) {
        this.a = gaVar;
        this.b = runnable;
    }

    public final void a() {
        ga gaVar = this.a;
        Runnable runnable = this.b;
        if (!gaVar.m) {
            ao.e();
            hd.a(runnable);
        }
    }
}
