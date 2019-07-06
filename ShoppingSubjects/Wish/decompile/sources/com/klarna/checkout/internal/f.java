package com.klarna.checkout.internal;

import android.webkit.WebView;
import com.klarna.checkout.browser.BrowserActivityListener;
import com.klarna.checkout.internal.js.interfaces.JSBridgeBase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class f implements BrowserActivityListener {
    String a = "KCO_NoOpActivityListen";
    private final JSBridgeBase b;

    public f(JSBridgeBase jSBridgeBase) {
        this.b = jSBridgeBase;
    }

    public final void onActivityClosed() {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("event", "sdk:done");
            jSONObject.put("action", "event");
            this.b.controller.a(jSONObject.toString(), "*", false);
        } catch (JSONException e) {
            e.getMessage();
        }
    }

    public final void onExternalBrowserOpened() {
    }

    public final void onIntentOpened(String str) {
    }

    public final void onLinkBlocked(String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("args", jSONArray);
            jSONObject.put("event", "sdk:blocked");
            jSONObject.put("action", "event");
            this.b.controller.a(jSONObject.toString(), "*", false);
        } catch (JSONException e) {
            e.getMessage();
        }
    }

    public final void onPageOpened(WebView webView, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(str);
            jSONObject.put("args", jSONArray);
            jSONObject.put("event", "sdk:opened");
            jSONObject.put("action", "event");
            this.b.controller.a(jSONObject.toString(), "*", false);
        } catch (JSONException e) {
            e.getMessage();
        }
    }
}
