package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class cl implements Callable<List<ff>> {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ zzgp b;

    cl(zzgp zzgp, zzeb zzeb) {
        this.b = zzgp;
        this.a = zzeb;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.b.zzalo.k();
        return this.b.zzalo.d().a(this.a.packageName);
    }
}
