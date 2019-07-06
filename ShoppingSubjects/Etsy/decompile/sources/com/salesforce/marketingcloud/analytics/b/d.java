package com.salesforce.marketingcloud.analytics.b;

import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.e.g.a;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

final class d extends a {
    private static final a a = new a();

    d(String str, String str2, Date date) {
        super(str, str2, date);
    }

    public JSONObject d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("api_endpoint", a());
        } catch (JSONException unused) {
        }
        try {
            jSONObject.put(ResponseConstants.EVENT_NAME, b());
        } catch (JSONException unused2) {
        }
        a.a(jSONObject, "timestamp", c());
        return jSONObject;
    }
}
