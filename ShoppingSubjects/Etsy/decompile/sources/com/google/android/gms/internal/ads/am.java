package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class am implements ae<Object> {
    private final /* synthetic */ zzos a;
    private final /* synthetic */ ai b;

    am(ai aiVar, zzos zzos) {
        this.b = aiVar;
        this.a = zzos;
    }

    public final void zza(Object obj, Map<String, String> map) {
        this.b.a((zzqs) this.a, (String) map.get("asset"));
    }
}
