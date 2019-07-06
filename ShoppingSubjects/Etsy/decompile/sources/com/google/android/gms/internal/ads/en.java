package com.google.android.gms.internal.ads;

final class en implements Runnable {
    private final /* synthetic */ zzjj a;
    private final /* synthetic */ zzxq b;
    private final /* synthetic */ em c;

    en(em emVar, zzjj zzjj, zzxq zzxq) {
        this.c = emVar;
        this.a = zzjj;
        this.b = zzxq;
    }

    public final void run() {
        this.c.a(this.a, this.b);
    }
}
