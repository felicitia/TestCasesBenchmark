package com.google.android.gms.internal.ads;

final class ajf implements Runnable {
    private final amf a;
    private final arb b;
    private final Runnable c;

    public ajf(aho aho, amf amf, arb arb, Runnable runnable) {
        this.a = amf;
        this.b = arb;
        this.c = runnable;
    }

    public final void run() {
        this.a.g();
        if (this.b.c == null) {
            this.a.a(this.b.a);
        } else {
            this.a.a(this.b.c);
        }
        if (this.b.d) {
            this.a.b("intermediate-response");
        } else {
            this.a.c("done");
        }
        if (this.c != null) {
            this.c.run();
        }
    }
}
