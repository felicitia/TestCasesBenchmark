package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final /* synthetic */ class kn implements Runnable {
    private final Future a;

    kn(Future future) {
        this.a = future;
    }

    public final void run() {
        Future future = this.a;
        if (!future.isDone()) {
            future.cancel(true);
        }
    }
}
