package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ao;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class ge {
    private final long a;
    private final List<String> b = new ArrayList();
    private final List<String> c = new ArrayList();
    private final Map<String, aqz> d = new HashMap();
    private String e;
    private String f;
    private boolean g;

    public ge(String str, long j) {
        this.g = false;
        this.f = str;
        this.a = j;
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.optInt("status", -1) != 1) {
                    this.g = false;
                    gv.e("App settings could not be fetched successfully.");
                    return;
                }
                this.g = true;
                this.e = jSONObject.optString("app_id");
                JSONArray optJSONArray = jSONObject.optJSONArray("ad_unit_id_settings");
                if (optJSONArray != null) {
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                        String optString = jSONObject2.optString(ResponseConstants.FORMAT);
                        String optString2 = jSONObject2.optString("ad_unit_id");
                        if (!TextUtils.isEmpty(optString)) {
                            if (!TextUtils.isEmpty(optString2)) {
                                if ("interstitial".equalsIgnoreCase(optString)) {
                                    this.c.add(optString2);
                                } else if ("rewarded".equalsIgnoreCase(optString)) {
                                    JSONObject optJSONObject = jSONObject2.optJSONObject("mediation_config");
                                    if (optJSONObject != null) {
                                        this.d.put(optString2, new aqz(optJSONObject));
                                    }
                                }
                            }
                        }
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("persistable_banner_ad_unit_ids");
                if (optJSONArray2 != null) {
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        this.b.add(optJSONArray2.optString(i2));
                    }
                }
            } catch (JSONException e2) {
                gv.c("Exception occurred while processing app setting json", e2);
                ao.i().a((Throwable) e2, "AppSettings.parseAppSettingsJson");
            }
        }
    }

    public final long a() {
        return this.a;
    }

    public final boolean b() {
        return this.g;
    }

    public final String c() {
        return this.f;
    }

    public final String d() {
        return this.e;
    }

    public final Map<String, aqz> e() {
        return this.d;
    }
}
