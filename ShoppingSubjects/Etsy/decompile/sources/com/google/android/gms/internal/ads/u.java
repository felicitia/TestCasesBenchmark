package com.google.android.gms.internal.ads;

final class u implements Runnable {
    private final /* synthetic */ t a;

    u(t tVar) {
        this.a = tVar;
    }

    public final void run() {
        if (this.a.h.get()) {
            gv.c("Timed out waiting for WebView to finish loading.");
            this.a.b();
        }
    }
}
