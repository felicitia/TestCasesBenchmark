package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;

final class ip implements kd<Throwable, T> {
    private final /* synthetic */ it a;

    ip(in inVar, it itVar) {
        this.a = itVar;
    }

    public final /* synthetic */ kt a(Object obj) throws Exception {
        Throwable th = (Throwable) obj;
        gv.b("Error occurred while dispatching http response in getter.", th);
        ao.i().a(th, "HttpGetter.deliverResponse.1");
        return ki.a(this.a.a());
    }
}
