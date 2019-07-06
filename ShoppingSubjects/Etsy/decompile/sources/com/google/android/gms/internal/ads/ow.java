package com.google.android.gms.internal.ads;

import android.view.View;

final class ow implements Runnable {
    private final /* synthetic */ View a;
    private final /* synthetic */ fl b;
    private final /* synthetic */ int c;
    private final /* synthetic */ ou d;

    ow(ou ouVar, View view, fl flVar, int i) {
        this.d = ouVar;
        this.a = view;
        this.b = flVar;
        this.c = i;
    }

    public final void run() {
        this.d.a(this.a, this.b, this.c - 1);
    }
}
