package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.concurrent.Callable;

final /* synthetic */ class fr implements Callable {
    private final fq a;
    private final Context b;

    fr(fq fqVar, Context context) {
        this.a = fqVar;
        this.b = context;
    }

    public final Object call() {
        return this.a.k(this.b);
    }
}
