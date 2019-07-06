package com.klarna.checkout.internal.js.a;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.res.Resources;
import android.webkit.WebView;
import com.klarna.checkout.internal.a.e;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import com.klarna.checkout.sdk.R;
import org.json.JSONObject;

public final class a extends o {
    public a(JSBridgeEvent jSBridgeEvent) {
        super(jSBridgeEvent, "handshake", "");
    }

    /* access modifiers changed from: 0000 */
    public final void a(String str, JSONObject jSONObject) {
        try {
            if (jSONObject.has("snippet")) {
                if (!this.a.isMessageFromOverlay) {
                    String string = jSONObject.getString("snippet");
                    try {
                        WebView webView = this.a.controller.d;
                        StringBuilder sb = new StringBuilder("http://fullscreen.klarna.sdk?");
                        sb.append(this.a.controller.b);
                        webView.loadDataWithBaseURL(sb.toString(), string, "text/html", "utf-8", "");
                    } catch (NullPointerException unused) {
                    }
                }
                jSONObject.remove("snippet");
            }
            try {
                Activity activity = this.a.controller.j;
                Resources resources = activity.getResources();
                PackageInfo packageInfo = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 0);
                jSONObject.put("version", resources.getString(R.string.SDK_VERSION_NAME));
                jSONObject.put("platform", resources.getString(R.string.SDK_PLATFORM));
                jSONObject.put("appName", activity.getPackageName());
                jSONObject.put("appVersion", packageInfo.versionName);
                jSONObject.put("interfaces", e.a(this.a.controller));
                if (this.a.controller.m != null) {
                    jSONObject.put("returnURL", this.a.controller.m);
                }
            } catch (Exception e) {
                e.getMessage();
            }
            this.a.controller.b(jSONObject.toString(), "*", this.a.isMessageFromOverlay);
        } catch (Exception e2) {
            e2.getMessage();
        }
    }
}
