package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class amq implements Runnable {
    private final le a;
    private final Future b;

    amq(le leVar, Future future) {
        this.a = leVar;
        this.b = future;
    }

    public final void run() {
        le leVar = this.a;
        Future future = this.b;
        if (leVar.isCancelled()) {
            future.cancel(true);
        }
    }
}
