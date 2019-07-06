package com.salesforce.marketingcloud.analytics;

import java.util.List;
import org.json.JSONObject;

abstract class a extends C$$AutoValue_PiCart {
    private static final a a = new a();

    a(List<PiCartItem> list) {
        super(list);
    }

    public JSONObject a() {
        JSONObject jSONObject = new JSONObject();
        a.a(jSONObject, "cart", cartItems());
        return jSONObject;
    }
}
