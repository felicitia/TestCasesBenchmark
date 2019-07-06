package com.crittercism.internal;

import android.support.annotation.NonNull;
import com.crittercism.internal.ap.b;
import com.crittercism.internal.ap.d;
import com.crittercism.internal.ap.e;
import com.crittercism.internal.cd.a;
import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONException;
import org.json.JSONObject;

public final class bx implements a {
    private ap a;
    private bo<aw> b;

    public bx(@NonNull ap apVar, @NonNull bo<aw> boVar) {
        this.a = apVar;
        this.b = boVar;
    }

    private void a(JSONObject jSONObject, d dVar, ap.a aVar, ap.a aVar2, b bVar) {
        try {
            this.a.a((e<T>) dVar, Long.valueOf(jSONObject.getLong("interval") * 1000));
        } catch (JSONException unused) {
        }
        try {
            boolean z = jSONObject.getBoolean(ResponseConstants.ENABLED);
            this.a.a((e<T>) aVar2, Boolean.valueOf(z));
            this.a.a((e<T>) aVar, Boolean.valueOf(z));
        } catch (JSONException unused2) {
        }
        try {
            this.a.a((e<T>) bVar, Float.valueOf((float) jSONObject.getDouble(ResponseConstants.RATE)));
        } catch (JSONException unused3) {
        }
    }

    public final void a(cb cbVar) {
        if (cbVar == null || cbVar.b == null) {
            if (this.b != null) {
                this.b.a();
            }
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(new String(cbVar.b));
            try {
                try {
                    this.a.a((e<T>) ap.N, Long.valueOf(jSONObject.getJSONObject("globalConfig").getLong("refreshInterval") * 1000));
                } catch (JSONException unused) {
                    cm.c("error parsing config ttl", new IllegalArgumentException());
                }
            } catch (JSONException unused2) {
                try {
                    try {
                        this.a.a((e<T>) ap.N, Long.valueOf(jSONObject.getJSONObject("global_config").getLong("refresh_interval") * 1000));
                    } catch (JSONException unused3) {
                        cm.c("error parsing config ttl", new IllegalArgumentException());
                    }
                } catch (JSONException unused4) {
                }
            }
            try {
                a(jSONObject.getJSONObject("appLoadConfig"), ap.af, ap.ad, ap.ac, ap.ag);
            } catch (JSONException unused5) {
                try {
                    a(jSONObject.getJSONObject("app_load_config"), ap.af, ap.ad, ap.ac, ap.ag);
                } catch (JSONException unused6) {
                }
            }
            try {
                a(jSONObject.getJSONObject("networkInsightsConfig"), ap.k, ap.i, ap.h, ap.l);
            } catch (JSONException unused7) {
                a(jSONObject.getJSONObject("network_insights_config"), ap.k, ap.i, ap.h, ap.l);
            }
        } catch (JSONException unused8) {
        }
        if (this.b != null) {
            this.b.a();
        }
    }
}
