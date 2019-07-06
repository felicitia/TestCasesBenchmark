package com.google.android.gms.internal.ads;

final class cv implements Runnable {
    private final /* synthetic */ gb a;
    private final /* synthetic */ cu b;

    cv(cu cuVar, gb gbVar) {
        this.b = cuVar;
        this.a = gbVar;
    }

    public final void run() {
        this.b.h.zza(this.a);
        if (this.b.l != null) {
            this.b.l.c();
            this.b.l = null;
        }
    }
}
