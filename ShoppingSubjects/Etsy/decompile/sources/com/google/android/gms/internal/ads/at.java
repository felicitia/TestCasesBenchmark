package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class at implements oq {
    private final nn a;
    private final JSONObject b;

    at(nn nnVar, JSONObject jSONObject) {
        this.a = nnVar;
        this.b = jSONObject;
    }

    public final void a() {
        this.a.zzb("google.afma.nativeAds.renderVideo", this.b);
    }
}
