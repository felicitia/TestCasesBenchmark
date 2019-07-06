package com.klarna.checkout.internal.js.a;

import com.klarna.checkout.browser.BrowserActivity;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONObject;

public final class l extends o {
    public l(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "sdk:close");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        BrowserActivity.closeRemotely();
    }
}
