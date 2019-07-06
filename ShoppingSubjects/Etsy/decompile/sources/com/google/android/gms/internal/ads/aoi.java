package com.google.android.gms.internal.ads;

final /* synthetic */ class aoi implements Runnable {
    private final aof a;
    private final String b;

    aoi(aof aof, String str) {
        this.a = aof;
        this.b = str;
    }

    public final void run() {
        this.a.f(this.b);
    }
}
