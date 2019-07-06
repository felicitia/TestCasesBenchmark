package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class cb implements Callable<List<ff>> {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ zzgp d;

    cb(zzgp zzgp, zzeb zzeb, String str, String str2) {
        this.d = zzgp;
        this.a = zzeb;
        this.b = str;
        this.c = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.d.zzalo.k();
        return this.d.zzalo.d().a(this.a.packageName, this.b, this.c);
    }
}
