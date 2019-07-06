package com.salesforce.marketingcloud.analytics.b;

import com.salesforce.marketingcloud.e.g.e;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

final class f extends c {
    private static final e a = new e();

    f(boolean z, List<String> list) {
        super(z, list);
    }

    /* access modifiers changed from: 0000 */
    public JSONObject c() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("open_from_push", a());
        } catch (JSONException unused) {
        }
        a.a(jSONObject, "message_ids", b());
        return jSONObject;
    }
}
