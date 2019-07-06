package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONException;
import org.json.JSONObject;

public final class h extends o {
    public h(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "frame:fullscreen:show");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        a aVar = this.a.controller;
        if (aVar.k != null) {
            aVar.k.show();
        }
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("action", "event");
            jSONObject2.put("event", "sdk:shown");
            this.a.controller.a(jSONObject2.toString(), "*", this.a.isMessageFromOverlay);
        } catch (JSONException e) {
            e.getMessage();
        }
    }
}
