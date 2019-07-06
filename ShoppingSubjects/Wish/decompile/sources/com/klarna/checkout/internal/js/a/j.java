package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.SignalListener;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class j extends o {
    public j(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "merchant_api:signal");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        JSONArray jSONArray = null;
        try {
            if (jSONObject.has("args")) {
                jSONArray = jSONObject.getJSONArray("args");
            }
            SignalListener signalListener = this.a.controller.i;
            if (!(signalListener == null || jSONArray == null || jSONArray.length() <= 0)) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(0);
                for (int i = 0; i < jSONObject2.names().length(); i++) {
                    String string = jSONObject2.names().getString(i);
                    signalListener.onSignal(string, jSONObject2.getJSONObject(string));
                }
            }
        } catch (JSONException e) {
            e.getMessage();
        }
    }
}
