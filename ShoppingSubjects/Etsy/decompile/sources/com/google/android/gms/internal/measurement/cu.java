package com.google.android.gms.internal.measurement;

final class cu implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ Object c;
    private final /* synthetic */ long d;
    private final /* synthetic */ cs e;

    cu(cs csVar, String str, String str2, Object obj, long j) {
        this.e = csVar;
        this.a = str;
        this.b = str2;
        this.c = obj;
        this.d = j;
    }

    public final void run() {
        this.e.a(this.a, this.b, this.c, this.d);
    }
}
