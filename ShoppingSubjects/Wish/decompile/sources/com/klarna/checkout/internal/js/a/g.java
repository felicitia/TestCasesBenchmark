package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONObject;

public final class g extends o {
    public g(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "frame:fullscreen:hide");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        int i;
        if (jSONObject.has("args")) {
            try {
                i = Integer.parseInt(jSONObject.getJSONArray("args").getString(0));
            } catch (Exception e) {
                e.getMessage();
            }
            this.a.controller.a(i);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("action", "event");
            jSONObject2.put("event", "sdk:hidden");
            this.a.controller.a(jSONObject2.toString(), "*", this.a.isMessageFromOverlay);
        }
        i = 0;
        this.a.controller.a(i);
        try {
            JSONObject jSONObject22 = new JSONObject();
            jSONObject22.put("action", "event");
            jSONObject22.put("event", "sdk:hidden");
            this.a.controller.a(jSONObject22.toString(), "*", this.a.isMessageFromOverlay);
        } catch (Exception e2) {
            e2.getMessage();
        }
    }
}
