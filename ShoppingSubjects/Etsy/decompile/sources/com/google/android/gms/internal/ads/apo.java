package com.google.android.gms.internal.ads;

final /* synthetic */ class apo implements Runnable {
    private final aou a;

    private apo(aou aou) {
        this.a = aou;
    }

    static Runnable a(aou aou) {
        return new apo(aou);
    }

    public final void run() {
        this.a.a();
    }
}
