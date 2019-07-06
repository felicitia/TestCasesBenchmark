package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;

final /* synthetic */ class kw implements Runnable {
    private final Executor a;
    private final Runnable b;

    kw(Executor executor, Runnable runnable) {
        this.a = executor;
        this.b = runnable;
    }

    public final void run() {
        this.a.execute(this.b);
    }
}
