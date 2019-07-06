package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
public final class aqo {
    @VisibleForTesting
    private static final ii<aou> a = new aqp();
    @VisibleForTesting
    private static final ii<aou> b = new aqq();
    private final apg c;

    public aqo(Context context, zzang zzang, String str) {
        apg apg = new apg(context, zzang, str, a, b);
        this.c = apg;
    }

    public final <I, O> aqg<I, O> a(String str, aqj<I> aqj, aqi<O> aqi) {
        return new aqr(this.c, str, aqj, aqi);
    }
}
