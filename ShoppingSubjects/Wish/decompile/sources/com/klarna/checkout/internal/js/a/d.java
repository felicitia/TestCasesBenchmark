package com.klarna.checkout.internal.js.a;

import android.app.Activity;
import com.klarna.checkout.internal.b.c;
import com.klarna.checkout.internal.g;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONException;
import org.json.JSONObject;

public final class d extends o {
    public d(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "storage", "set");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        try {
            String string = jSONObject.getString("data");
            c cVar = this.a.controller.c;
            Activity activity = this.a.controller.j;
            AnonymousClass1 r1 = new g() {
                public final void a(String str) {
                    str.equals("1");
                }
            };
            cVar.d = string;
            cVar.a(activity, r1);
        } catch (JSONException unused) {
        }
    }
}
