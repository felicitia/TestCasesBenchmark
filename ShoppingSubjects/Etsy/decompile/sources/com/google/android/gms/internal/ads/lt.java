package com.google.android.gms.internal.ads;

final class lt implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ zzaov c;

    lt(zzaov zzaov, String str, String str2) {
        this.c = zzaov;
        this.a = str;
        this.b = str2;
    }

    public final void run() {
        if (this.c.zzcxd != null) {
            this.c.zzcxd.zzg(this.a, this.b);
        }
    }
}
