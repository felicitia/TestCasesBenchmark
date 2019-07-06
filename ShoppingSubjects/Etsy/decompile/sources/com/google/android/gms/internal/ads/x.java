package com.google.android.gms.internal.ads;

final class x implements Runnable {
    private final /* synthetic */ ga a;
    private final /* synthetic */ v b;

    x(v vVar, ga gaVar) {
        this.b = vVar;
        this.a = gaVar;
    }

    public final void run() {
        synchronized (this.b.c) {
            v vVar = this.b;
            vVar.a.zzb(this.a);
        }
    }
}
