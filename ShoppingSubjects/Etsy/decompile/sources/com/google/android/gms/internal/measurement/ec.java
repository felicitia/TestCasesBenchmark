package com.google.android.gms.internal.measurement;

final class ec implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ zzka b;
    private final /* synthetic */ zzeb c;
    private final /* synthetic */ dq d;

    ec(dq dqVar, boolean z, zzka zzka, zzeb zzeb) {
        this.d = dqVar;
        this.a = z;
        this.b = zzka;
        this.c = zzeb;
    }

    public final void run() {
        zzfa d2 = this.d.b;
        if (d2 == null) {
            this.d.r().h_().a("Discarding data. Failed to set user attribute");
            return;
        }
        this.d.a(d2, this.a ? null : this.b, this.c);
        this.d.J();
    }
}
