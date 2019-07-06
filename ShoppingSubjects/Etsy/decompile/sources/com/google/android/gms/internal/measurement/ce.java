package com.google.android.gms.internal.measurement;

import java.util.List;
import java.util.concurrent.Callable;

final class ce implements Callable<List<zzef>> {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;
    private final /* synthetic */ String c;
    private final /* synthetic */ zzgp d;

    ce(zzgp zzgp, String str, String str2, String str3) {
        this.d = zzgp;
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.d.zzalo.k();
        return this.d.zzalo.d().b(this.a, this.b, this.c);
    }
}
