package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONException;
import org.json.JSONObject;

public final class e extends o {
    public e(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "checkout/");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        try {
            jSONObject.put("event", str.replace(this.c, ""));
            this.a.controller.a(jSONObject.toString(), "*", false);
        } catch (JSONException e) {
            e.getMessage();
        }
    }
}
