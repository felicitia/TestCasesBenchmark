package com.google.android.gms.internal.ads;

final /* synthetic */ class aox implements Runnable {
    private final aow a;
    private final String b;

    aox(aow aow, String str) {
        this.a = aow;
        this.b = str;
    }

    public final void run() {
        this.a.d(this.b);
    }
}
