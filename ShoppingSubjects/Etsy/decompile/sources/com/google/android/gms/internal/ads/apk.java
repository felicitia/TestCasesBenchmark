package com.google.android.gms.internal.ads;

final /* synthetic */ class apk implements Runnable {
    private final aou a;

    private apk(aou aou) {
        this.a = aou;
    }

    static Runnable a(aou aou) {
        return new apk(aou);
    }

    public final void run() {
        this.a.a();
    }
}
