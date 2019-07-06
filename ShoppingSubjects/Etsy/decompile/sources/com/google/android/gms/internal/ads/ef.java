package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import org.json.JSONObject;

@bu
public final class ef implements di {
    private aqg<JSONObject, JSONObject> a;
    private aqg<JSONObject, JSONObject> b;

    public ef(Context context) {
        this.a = ao.s().a(context, zzang.zzsl()).a("google.afma.request.getAdDictionary", aql.a, aql.a);
        this.b = ao.s().a(context, zzang.zzsl()).a("google.afma.sdkConstants.getSdkConstants", aql.a, aql.a);
    }

    public final aqg<JSONObject, JSONObject> a() {
        return this.a;
    }

    public final aqg<JSONObject, JSONObject> b() {
        return this.b;
    }
}
