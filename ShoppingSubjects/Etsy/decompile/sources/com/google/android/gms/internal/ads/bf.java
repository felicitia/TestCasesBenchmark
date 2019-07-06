package com.google.android.gms.internal.ads;

import org.json.JSONObject;

final /* synthetic */ class bf implements kd {
    private final bd a;
    private final JSONObject b;

    bf(bd bdVar, JSONObject jSONObject) {
        this.a = bdVar;
        this.b = jSONObject;
    }

    public final kt a(Object obj) {
        return this.a.d(this.b, (nn) obj);
    }
}
