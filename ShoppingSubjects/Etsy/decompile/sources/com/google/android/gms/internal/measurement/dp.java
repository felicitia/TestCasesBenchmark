package com.google.android.gms.internal.measurement;

final class dp implements Runnable {
    private final /* synthetic */ dm a;
    private final /* synthetic */ dn b;

    dp(dn dnVar, dm dmVar) {
        this.b = dnVar;
        this.a = dmVar;
    }

    public final void run() {
        this.b.a(this.a);
        this.b.a = null;
        this.b.h().a((dm) null);
    }
}
