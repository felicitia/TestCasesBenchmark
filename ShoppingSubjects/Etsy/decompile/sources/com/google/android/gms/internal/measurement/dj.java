package com.google.android.gms.internal.measurement;

final class dj implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ cs b;

    dj(cs csVar, long j) {
        this.b = csVar;
        this.a = j;
    }

    public final void run() {
        this.b.s().l.a(this.a);
        this.b.r().v().a("Session timeout duration set", Long.valueOf(this.a));
    }
}
