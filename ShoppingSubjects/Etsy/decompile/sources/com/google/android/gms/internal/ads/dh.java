package com.google.android.gms.internal.ads;

import java.util.concurrent.Future;

final class dh implements Runnable {
    private final /* synthetic */ Future a;

    dh(zzafn zzafn, Future future) {
        this.a = future;
    }

    public final void run() {
        if (!this.a.isDone()) {
            this.a.cancel(true);
        }
    }
}
