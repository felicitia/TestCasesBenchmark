package com.salesforce.marketingcloud.analytics;

import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONException;
import org.json.JSONObject;

abstract class c extends C$$AutoValue_PiOrder {
    private static final a a = new a();

    c(PiCart piCart, String str, double d, double d2) {
        super(piCart, str, d, d2);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        a.a(jSONObject, "cart", cart());
        try {
            jSONObject.put("order_number", orderNumber());
        } catch (JSONException unused) {
        }
        try {
            jSONObject.put(ResponseConstants.SHIPPING, shipping());
        } catch (JSONException unused2) {
        }
        try {
            jSONObject.put("discount", discount());
        } catch (JSONException unused3) {
        }
        return jSONObject;
    }
}
