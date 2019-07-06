package com.google.android.gms.internal.measurement;

final class di implements Runnable {
    private final /* synthetic */ long a;
    private final /* synthetic */ cs b;

    di(cs csVar, long j) {
        this.b = csVar;
        this.a = j;
    }

    public final void run() {
        this.b.s().k.a(this.a);
        this.b.r().v().a("Minimum session duration set", Long.valueOf(this.a));
    }
}
