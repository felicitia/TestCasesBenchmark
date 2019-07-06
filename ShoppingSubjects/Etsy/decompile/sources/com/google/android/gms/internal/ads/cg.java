package com.google.android.gms.internal.ads;

final class cg implements Runnable {
    private final /* synthetic */ lg a;
    private final /* synthetic */ bx b;

    cg(bx bxVar, lg lgVar) {
        this.b = bxVar;
        this.a = lgVar;
    }

    public final void run() {
        synchronized (this.b.d) {
            this.b.a = this.b.a(this.b.c.j, this.a);
            if (this.b.a == null) {
                this.b.a(0, "Could not start the ad request service.");
                hd.a.removeCallbacks(this.b.i);
            }
        }
    }
}
