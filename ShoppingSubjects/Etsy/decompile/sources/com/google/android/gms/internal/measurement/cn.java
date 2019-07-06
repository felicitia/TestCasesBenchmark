package com.google.android.gms.internal.measurement;

final class cn implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ long d;
    private final /* synthetic */ zzgp e;

    cn(zzgp zzgp, String str, String str2, String str3, long j) {
        this.e = zzgp;
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = j;
    }

    public final void run() {
        if (this.a == null) {
            this.e.zzalo.o().s().a(this.b, (dm) null);
            return;
        }
        this.e.zzalo.o().s().a(this.b, new dm(this.c, this.a, this.d));
    }
}
