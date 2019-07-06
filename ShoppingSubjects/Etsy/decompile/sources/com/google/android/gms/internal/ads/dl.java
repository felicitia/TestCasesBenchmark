package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class dl implements ae<Object> {
    private final /* synthetic */ dk a;

    dl(dk dkVar) {
        this.a = dkVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.a.b) {
            if (!this.a.e.isDone()) {
                if (this.a.c.equals(map.get("request_id"))) {
                    dq dqVar = new dq(1, map);
                    String f = dqVar.f();
                    String valueOf = String.valueOf(dqVar.b());
                    StringBuilder sb = new StringBuilder(24 + String.valueOf(f).length() + String.valueOf(valueOf).length());
                    sb.append("Invalid ");
                    sb.append(f);
                    sb.append(" request error: ");
                    sb.append(valueOf);
                    gv.e(sb.toString());
                    this.a.e.b(dqVar);
                }
            }
        }
    }
}
