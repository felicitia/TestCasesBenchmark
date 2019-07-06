package com.google.android.gms.internal.ads;

final class cz implements Runnable {
    private final /* synthetic */ cu a;

    cz(cu cuVar) {
        this.a = cuVar;
    }

    public final void run() {
        if (this.a.l != null) {
            this.a.l.c();
            this.a.l = null;
        }
    }
}
