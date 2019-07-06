package com.google.android.gms.internal.measurement;

final class bm implements Runnable {
    private final /* synthetic */ bu a;
    private final /* synthetic */ aq b;

    bm(bl blVar, bu buVar, aq aqVar) {
        this.a = buVar;
        this.b = aqVar;
    }

    public final void run() {
        if (this.a.f() == null) {
            this.b.h_().a("Install Referrer Reporter is null");
        } else {
            this.a.f().a();
        }
    }
}
