package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.internal.c.a;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONException;
import org.json.JSONObject;

public final class f extends o {
    public f(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "frame:checkout:resize");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        if (!this.a.controller.d()) {
            try {
                a.a(this.a.controller.g, jSONObject.getJSONArray("args").get(0).toString());
            } catch (JSONException e) {
                e.getMessage();
            }
        }
    }
}
