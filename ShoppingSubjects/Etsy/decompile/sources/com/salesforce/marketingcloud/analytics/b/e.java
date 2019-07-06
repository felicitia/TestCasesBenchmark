package com.salesforce.marketingcloud.analytics.b;

import com.etsy.android.lib.models.ResponseConstants;
import com.salesforce.marketingcloud.e.g.a;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

final class e extends b {
    private static final a a = new a();
    private static final b b = new b();

    e(String str, String str2, Date date, a aVar) {
        super(str, str2, date, aVar);
    }

    public JSONObject e() {
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
        b.a(jSONObject, "details", d());
        return jSONObject;
    }
}
