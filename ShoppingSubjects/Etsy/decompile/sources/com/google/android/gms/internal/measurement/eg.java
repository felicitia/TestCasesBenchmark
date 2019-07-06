package com.google.android.gms.internal.measurement;

final class eg implements Runnable {
    private final /* synthetic */ zzfa a;
    private final /* synthetic */ zziy b;

    eg(zziy zziy, zzfa zzfa) {
        this.b = zziy;
        this.a = zzfa;
    }

    public final void run() {
        synchronized (this.b) {
            this.b.zzarb = false;
            if (!this.b.zzaqv.B()) {
                this.b.zzaqv.r().v().a("Connected to remote service");
                this.b.zzaqv.a(this.a);
            }
        }
    }
}
