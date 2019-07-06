package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;

final class agh implements ValueCallback<String> {
    private final /* synthetic */ agg a;

    agh(agg agg) {
        this.a = agg;
    }

    public final /* synthetic */ void onReceiveValue(Object obj) {
        this.a.d.a(this.a.a, this.a.b, (String) obj, this.a.c);
    }
}
