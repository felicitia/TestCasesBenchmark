package com.klarna.checkout.internal.js.a;

import android.os.Handler;
import android.os.Looper;
import com.klarna.checkout.internal.js.interfaces.JSBridgeBase;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class o {
    protected final JSBridgeBase a;
    protected final String b;
    protected final String c;

    o(JSBridgeBase jSBridgeBase, String str, String str2) {
        this.a = jSBridgeBase;
        this.b = str;
        this.c = str2;
    }

    /* access modifiers changed from: 0000 */
    public abstract void a(String str, JSONObject jSONObject);

    public final boolean a(String str) {
        try {
            final JSONObject jSONObject = new JSONObject(str);
            final String string = jSONObject.getString("action");
            final String string2 = jSONObject.has("event") ? jSONObject.getString("event") : null;
            if (!string.equals(this.b)) {
                return false;
            }
            if (string2 != null && !string2.startsWith(this.c)) {
                return false;
            }
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                public final void run() {
                    o.this.a(string2, jSONObject);
                }
            });
            return true;
        } catch (JSONException e) {
            e.getMessage();
            return false;
        }
    }
}
