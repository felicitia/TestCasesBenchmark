package com.google.android.gms.internal.ads;

final /* synthetic */ class aj implements Runnable {
    private final ai a;
    private final le b;
    private final String c;

    aj(ai aiVar, le leVar, String str) {
        this.a = aiVar;
        this.b = leVar;
        this.c = str;
    }

    public final void run() {
        this.a.a(this.b, this.c);
    }
}
