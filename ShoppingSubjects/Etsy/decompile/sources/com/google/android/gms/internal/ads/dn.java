package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class dn implements ae<Object> {
    private final /* synthetic */ dk a;

    dn(dk dkVar) {
        this.a = dkVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.a.b) {
            if (!this.a.e.isDone()) {
                dq dqVar = new dq(-2, map);
                if (this.a.c.equals(dqVar.h())) {
                    this.a.e.b(dqVar);
                }
            }
        }
    }
}
