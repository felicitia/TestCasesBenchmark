package com.google.android.gms.internal.measurement;

import android.content.Intent;

final /* synthetic */ class ek implements Runnable {
    private final ej a;
    private final int b;
    private final aq c;
    private final Intent d;

    ek(ej ejVar, int i, aq aqVar, Intent intent) {
        this.a = ejVar;
        this.b = i;
        this.c = aqVar;
        this.d = intent;
    }

    public final void run() {
        this.a.a(this.b, this.c, this.d);
    }
}
