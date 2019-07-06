package com.google.android.gms.internal.ads;

final class agc implements Runnable {
    private final /* synthetic */ agb a;

    agc(agb agb) {
        this.a = agb;
    }

    public final void run() {
        synchronized (this.a.c) {
            if (!this.a.d || !this.a.e) {
                gv.b("App is still foreground");
            } else {
                this.a.d = false;
                gv.b("App went background");
                for (agd a2 : this.a.f) {
                    try {
                        a2.a(false);
                    } catch (Exception e) {
                        ka.b("", e);
                    }
                }
            }
        }
    }
}
