package com.google.android.gms.internal.ads;

final class amj implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ long b;
    private final /* synthetic */ amf c;

    amj(amf amf, String str, long j) {
        this.c = amf;
        this.a = str;
        this.b = j;
    }

    public final void run() {
        this.c.a.a(this.a, this.b);
        this.c.a.a(toString());
    }
}
