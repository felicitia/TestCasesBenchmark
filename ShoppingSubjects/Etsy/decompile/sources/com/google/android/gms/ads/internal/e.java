package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.kd;
import com.google.android.gms.internal.ads.ki;
import com.google.android.gms.internal.ads.kt;
import org.json.JSONObject;

final /* synthetic */ class e implements kd {
    static final kd a = new e();

    private e() {
    }

    public final kt a(Object obj) {
        JSONObject jSONObject = (JSONObject) obj;
        if (jSONObject.optBoolean("isSuccessful", false)) {
            ao.i().l().f(jSONObject.getString("appSettingsJson"));
        }
        return ki.a(null);
    }
}
