package com.google.android.gms.internal.ads;

import java.util.Map;

final /* synthetic */ class oa implements Runnable {
    private final zzarl a;
    private final Map b;

    oa(zzarl zzarl, Map map) {
        this.a = zzarl;
        this.b = map;
    }

    public final void run() {
        this.a.zzo(this.b);
    }
}
