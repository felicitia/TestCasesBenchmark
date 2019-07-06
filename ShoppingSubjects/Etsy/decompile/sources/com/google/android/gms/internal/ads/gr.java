package com.google.android.gms.internal.ads;

final class gr implements Runnable {
    private final /* synthetic */ gq a;

    gr(gq gqVar) {
        this.a = gqVar;
    }

    public final void run() {
        this.a.b = Thread.currentThread();
        this.a.a();
    }
}
