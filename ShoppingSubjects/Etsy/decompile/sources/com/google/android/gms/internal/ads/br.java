package com.google.android.gms.internal.ads;

final class br implements Runnable {
    private final /* synthetic */ kb a;
    private final /* synthetic */ String b;

    br(bo boVar, kb kbVar, String str) {
        this.a = kbVar;
        this.b = str;
    }

    public final void run() {
        this.a.a(this.b);
    }
}
