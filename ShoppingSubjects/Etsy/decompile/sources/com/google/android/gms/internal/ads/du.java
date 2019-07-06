package com.google.android.gms.internal.ads;

import android.content.Context;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

@bu
public final class du {
    /* access modifiers changed from: private */
    public WeakHashMap<Context, dw> a = new WeakHashMap<>();

    public final Future<ds> a(Context context) {
        return hb.a((Callable<T>) new dv<T>(this, context));
    }
}
