package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class kq implements Runnable {
    private final kt a;
    private final Future b;

    kq(kt ktVar, Future future) {
        this.a = ktVar;
        this.b = future;
    }

    public final void run() {
        kt ktVar = this.a;
        Future future = this.b;
        if (ktVar.isCancelled()) {
            future.cancel(true);
        }
    }
}
