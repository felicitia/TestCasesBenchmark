package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.gv;
import org.json.JSONObject;

final class ag implements Runnable {
    private final /* synthetic */ JSONObject a;
    private final /* synthetic */ af b;

    ag(af afVar, JSONObject jSONObject) {
        this.b = afVar;
        this.a = jSONObject;
    }

    public final void run() {
        this.b.a.zza("fetchHttpRequestCompleted", this.a);
        gv.b("Dispatched http response.");
    }
}
