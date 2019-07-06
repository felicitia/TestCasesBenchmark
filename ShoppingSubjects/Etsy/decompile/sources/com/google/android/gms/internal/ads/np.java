package com.google.android.gms.internal.ads;

import android.view.View;

final class np implements Runnable {
    private final /* synthetic */ View a;
    private final /* synthetic */ fl b;
    private final /* synthetic */ int c;
    private final /* synthetic */ zzaqx d;

    np(zzaqx zzaqx, View view, fl flVar, int i) {
        this.d = zzaqx;
        this.a = view;
        this.b = flVar;
        this.c = i;
    }

    public final void run() {
        this.d.zza(this.a, this.b, this.c - 1);
    }
}
