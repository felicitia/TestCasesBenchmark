package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

final class ci implements Callable<byte[]> {
    private final /* synthetic */ zzex a;
    private final /* synthetic */ String b;
    private final /* synthetic */ zzgp c;

    ci(zzgp zzgp, zzex zzex, String str) {
        this.c = zzgp;
        this.a = zzex;
        this.b = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.c.zzalo.k();
        return this.c.zzalo.b(this.a, this.b);
    }
}
