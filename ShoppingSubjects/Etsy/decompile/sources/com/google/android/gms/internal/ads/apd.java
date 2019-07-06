package com.google.android.gms.internal.ads;

final class apd implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ aow b;

    apd(aow aow, String str) {
        this.b = aow;
        this.a = str;
    }

    public final void run() {
        this.b.a.loadUrl(this.a);
    }
}
