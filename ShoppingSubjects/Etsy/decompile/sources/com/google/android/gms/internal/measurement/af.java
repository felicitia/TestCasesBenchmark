package com.google.android.gms.internal.measurement;

final class af implements Runnable {
    private final /* synthetic */ cq a;
    private final /* synthetic */ ae b;

    af(ae aeVar, cq cqVar) {
        this.b = aeVar;
        this.a = cqVar;
    }

    public final void run() {
        this.a.u();
        if (v.a()) {
            this.a.q().a((Runnable) this);
            return;
        }
        boolean b2 = this.b.b();
        this.b.d = 0;
        if (b2) {
            this.b.a();
        }
    }
}
