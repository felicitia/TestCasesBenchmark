package com.google.android.gms.internal.ads;

final class cf implements Runnable {
    private final /* synthetic */ bx a;

    cf(bx bxVar) {
        this.a = bxVar;
    }

    public final void run() {
        synchronized (this.a.d) {
            if (this.a.a != null) {
                this.a.c_();
                this.a.a(2, "Timed out waiting for ad response.");
            }
        }
    }
}
