package com.google.android.gms.internal.ads;

final /* synthetic */ class ob implements Runnable {
    private final zzarl a;
    private final int b;
    private final int c;
    private final boolean d;
    private final boolean e;

    ob(zzarl zzarl, int i, int i2, boolean z, boolean z2) {
        this.a = zzarl;
        this.b = i;
        this.c = i2;
        this.d = z;
        this.e = z2;
    }

    public final void run() {
        this.a.zza(this.b, this.c, this.d, this.e);
    }
}
