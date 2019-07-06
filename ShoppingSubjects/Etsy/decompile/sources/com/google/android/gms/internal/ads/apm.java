package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.ae;
import java.util.Map;

final class apm implements ae<aqd> {
    private final /* synthetic */ ack a;
    private final /* synthetic */ aou b;
    private final /* synthetic */ jf c;
    private final /* synthetic */ apg d;

    apm(apg apg, ack ack, aou aou, jf jfVar) {
        this.d = apg;
        this.a = ack;
        this.b = aou;
        this.c = jfVar;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        synchronized (this.d.a) {
            gv.d("JS Engine is requesting an update");
            if (this.d.h == 0) {
                gv.d("Starting reload.");
                this.d.h = 2;
                this.d.a(this.a);
            }
            this.b.b("/requestReload", (ae) this.c.a());
        }
    }
}
