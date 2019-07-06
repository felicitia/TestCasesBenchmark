package com.klarna.checkout.internal.js.a;

import android.webkit.WebView;
import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.c.b;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class c extends o {
    public c(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "ready", "");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        try {
            if (!this.a.isMessageFromOverlay) {
                if (a.a != null) {
                    JSONObject jSONObject2 = new JSONObject();
                    JSONArray jSONArray = new JSONArray();
                    jSONArray.put(a.a);
                    jSONObject2.put("args", jSONArray);
                    jSONObject2.put("event", "push:token");
                    jSONObject2.put("action", "event");
                    this.a.controller.a(jSONObject2.toString(), "*", false);
                }
                if (!this.a.controller.d()) {
                    WebView webView = this.a.controller.g;
                    StringBuilder sb = new StringBuilder();
                    sb.append(500.0f / (((float) this.a.controller.j.getResources().getDisplayMetrics().densityDpi) / 160.0f));
                    com.klarna.checkout.internal.c.a.a(webView, sb.toString());
                }
            }
            String str2 = this.a.controller.c.d;
            if (str2 != null) {
                JSONObject jSONObject3 = new JSONObject();
                jSONObject3.put("data", str2);
                jSONObject3.put("event", "get");
                jSONObject3.put("action", "storage");
                this.a.controller.a(jSONObject3.toString(), "*", this.a.isMessageFromOverlay);
            }
            b bVar = this.a.controller.e;
            if (this.a.isMessageFromOverlay) {
                bVar.c = true;
                b.a(bVar.a);
                return;
            }
            bVar.d = true;
            b.a(bVar.b);
        } catch (JSONException e) {
            e.getMessage();
        }
    }
}
