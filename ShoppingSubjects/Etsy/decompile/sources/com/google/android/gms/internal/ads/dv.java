package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import java.util.concurrent.Callable;

final class dv implements Callable<ds> {
    private final /* synthetic */ Context a;
    private final /* synthetic */ du b;

    dv(du duVar, Context context) {
        this.b = duVar;
        this.a = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        ds dsVar;
        dw dwVar = (dw) this.b.a.get(this.a);
        if (dwVar != null) {
            if (!(dwVar.a + ((Long) ajh.f().a(akl.bq)).longValue() < ao.l().currentTimeMillis())) {
                if (((Boolean) ajh.f().a(akl.bp)).booleanValue()) {
                    dsVar = new dt(this.a, dwVar.b).a();
                    this.b.a.put(this.a, new dw(this.b, dsVar));
                    return dsVar;
                }
            }
        }
        dsVar = new dt(this.a).a();
        this.b.a.put(this.a, new dw(this.b, dsVar));
        return dsVar;
    }
}
