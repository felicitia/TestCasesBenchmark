package com.google.android.gms.internal.measurement;

import java.util.concurrent.Callable;

final class fc implements Callable<String> {
    private final /* synthetic */ zzeb a;
    private final /* synthetic */ ey b;

    fc(ey eyVar, zzeb zzeb) {
        this.b = eyVar;
        this.a = zzeb;
    }

    public final /* synthetic */ Object call() throws Exception {
        t a2 = this.b.b().k(this.a.packageName) ? this.b.e(this.a) : this.b.d().b(this.a.packageName);
        if (a2 != null) {
            return a2.c();
        }
        this.b.r().i().a("App info was null when attempting to get app instance id");
        return null;
    }
}
