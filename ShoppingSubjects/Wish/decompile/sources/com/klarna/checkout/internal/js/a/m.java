package com.klarna.checkout.internal.js.a;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.ViewParent;
import android.webkit.WebView;
import android.widget.ScrollView;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import org.json.JSONException;
import org.json.JSONObject;

public final class m extends o {
    public m(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "event", "sdk:focus");
    }

    private static ScrollView a(WebView webView) {
        ViewParent parent = webView.getParent();
        int i = 20;
        do {
            try {
                if (parent instanceof ScrollView) {
                    return (ScrollView) parent;
                }
                if (parent.getParent() != null) {
                    parent = parent.getParent();
                }
                i--;
            } catch (Exception unused) {
            }
        } while (i > 0);
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        WebView webView = this.a.controller.g;
        if (this.a.isMessageFromOverlay) {
            webView = this.a.controller.d;
        }
        if (jSONObject.has("args")) {
            try {
                JSONObject jSONObject2 = jSONObject.getJSONArray("args").getJSONObject(0);
                int i = jSONObject2.getInt("height");
                int i2 = jSONObject2.getInt("top");
                if (this.a.isMessageFromOverlay || !this.a.controller.d()) {
                    Activity activity = this.a.controller.j;
                    DisplayMetrics displayMetrics = new DisplayMetrics();
                    activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                    int i3 = displayMetrics.heightPixels / 2;
                    float f = displayMetrics.density * ((float) (i2 + i + (i / 2)));
                    int[] iArr = new int[2];
                    webView.getLocationInWindow(iArr);
                    if (((float) iArr[1]) + f > ((float) i3)) {
                        int i4 = (iArr[1] + ((int) f)) - i3;
                        ScrollView a = a(webView);
                        if (a != null) {
                            a.scrollBy(0, i4);
                        }
                    }
                }
            } catch (JSONException e) {
                e.getMessage();
            }
        }
    }
}
