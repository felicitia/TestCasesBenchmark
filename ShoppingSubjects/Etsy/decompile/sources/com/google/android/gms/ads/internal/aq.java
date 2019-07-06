package com.google.android.gms.ads.internal;

final /* synthetic */ class aq implements Runnable {
    private final af a;

    private aq(af afVar) {
        this.a = afVar;
    }

    static Runnable a(af afVar) {
        return new aq(afVar);
    }

    public final void run() {
        this.a.b();
    }
}
