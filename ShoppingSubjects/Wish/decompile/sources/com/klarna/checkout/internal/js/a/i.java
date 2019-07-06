package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONException;
import org.json.JSONObject;

public final class i extends o {
    public i(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "fullscreen/");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        try {
            jSONObject.put("event", str.replace(this.c, ""));
            this.a.controller.a(jSONObject.toString(), "*", true);
        } catch (JSONException e) {
            e.getMessage();
        }
    }
}
