package com.google.android.gms.internal.ads;

final class al implements Runnable {
    private final /* synthetic */ le a;
    private final /* synthetic */ String b;
    private final /* synthetic */ ai c;

    al(ai aiVar, le leVar, String str) {
        this.c = aiVar;
        this.a = leVar;
        this.b = str;
    }

    public final void run() {
        this.a.b((zzrf) this.c.d.zzdv().get(this.b));
    }
}
