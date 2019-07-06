package com.google.android.gms.internal.ads;

final /* synthetic */ class aol implements Runnable {
    private final aof a;
    private final String b;

    aol(aof aof, String str) {
        this.a = aof;
        this.b = str;
    }

    public final void run() {
        this.a.d(this.b);
    }
}
