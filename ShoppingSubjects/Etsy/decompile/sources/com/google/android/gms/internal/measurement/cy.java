package com.google.android.gms.internal.measurement;

final class cy implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ cs b;

    cy(cs csVar, long j) {
        this.b = csVar;
        this.a = j;
    }

    public final void run() {
        cs csVar = this.b;
        long j = this.a;
        csVar.d();
        csVar.b();
        csVar.w();
        csVar.r().v().a("Resetting analytics data (FE)");
        csVar.k().B();
        if (csVar.t().l(csVar.g().C())) {
            csVar.s().h.a(j);
        }
        boolean y = csVar.q.y();
        if (!csVar.t().h()) {
            csVar.s().d(!y);
        }
        csVar.h().D();
        csVar.b = !y;
    }
}
