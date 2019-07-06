package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class dm implements ae<Object> {
    private final /* synthetic */ dk a;

    dm(dk dkVar) {
        this.a = dkVar;
    }

    public final void zza(Object obj, Map<String, String> map) {
        synchronized (this.a.b) {
            if (!this.a.e.isDone()) {
                dq dqVar = new dq(-2, map);
                if (this.a.c.equals(dqVar.h())) {
                    String e = dqVar.e();
                    if (e == null) {
                        gv.e("URL missing in loadAdUrl GMSG.");
                        return;
                    }
                    if (e.contains("%40mediation_adapters%40")) {
                        String replaceAll = e.replaceAll("%40mediation_adapters%40", gp.a(this.a.a, (String) map.get("check_adapters"), this.a.d));
                        dqVar.a(replaceAll);
                        String str = "Ad request URL modified to ";
                        String valueOf = String.valueOf(replaceAll);
                        gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                    }
                    this.a.e.b(dqVar);
                }
            }
        }
    }
}
