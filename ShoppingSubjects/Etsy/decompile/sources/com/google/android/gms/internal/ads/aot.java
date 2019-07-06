package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final /* synthetic */ class aot implements Runnable {
    private final aos a;
    private final ae b;
    private final Map c;

    aot(aos aos, ae aeVar, Map map) {
        this.a = aos;
        this.b = aeVar;
        this.c = map;
    }

    public final void run() {
        aos aos = this.a;
        this.b.zza(aos.f(), this.c);
    }
}
