package com.google.android.gms.internal.ads;

final class apc implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ aow b;

    apc(aow aow, String str) {
        this.b = aow;
        this.a = str;
    }

    public final void run() {
        this.b.a.loadData(this.a, "text/html", "UTF-8");
    }
}
