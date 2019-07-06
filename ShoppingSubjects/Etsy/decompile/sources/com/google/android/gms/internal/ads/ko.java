package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final /* synthetic */ class ko implements Runnable {
    private final le a;
    private final kt b;
    private final Class c;
    private final kd d;
    private final Executor e;

    ko(le leVar, kt ktVar, Class cls, kd kdVar, Executor executor) {
        this.a = leVar;
        this.b = ktVar;
        this.c = cls;
        this.d = kdVar;
        this.e = executor;
    }

    public final void run() {
        ki.a(this.a, this.b, this.c, this.d, this.e);
    }
}
