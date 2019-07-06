package com.google.android.gms.internal.ads;

final class apb implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ aow b;

    apb(aow aow, String str) {
        this.b = aow;
        this.a = str;
    }

    public final void run() {
        this.b.a.loadData(this.a, "text/html", "UTF-8");
    }
}
