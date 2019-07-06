package com.google.android.gms.internal.measurement;

final class ee implements Runnable {
    private final /* synthetic */ zzfa a;
    private final /* synthetic */ zziy b;

    ee(zziy zziy, zzfa zzfa) {
        this.b = zziy;
        this.a = zzfa;
    }

    public final void run() {
        synchronized (this.b) {
            this.b.zzarb = false;
            if (!this.b.zzaqv.B()) {
                this.b.zzaqv.r().w().a("Connected to service");
                this.b.zzaqv.a(this.a);
            }
        }
    }
}
