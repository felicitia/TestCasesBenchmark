package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.etsy.android.lib.models.ResponseConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class FacebookWebFallbackDialog extends WebDialog {
    private static final int OS_BACK_BUTTON_RESPONSE_TIMEOUT_MILLISECONDS = 1500;
    private static final String TAG = "com.facebook.internal.FacebookWebFallbackDialog";
    private boolean waitingForDialogToClose;

    public static FacebookWebFallbackDialog newInstance(Context context, String str, String str2) {
        WebDialog.initDefaultTheme(context);
        return new FacebookWebFallbackDialog(context, str, str2);
    }

    private FacebookWebFallbackDialog(Context context, String str, String str2) {
        super(context, str);
        setExpectedRedirectUrl(str2);
    }

    /* access modifiers changed from: protected */
    public Bundle parseResponseUri(String str) {
        Bundle c = z.c(Uri.parse(str).getQuery());
        String string = c.getString("bridge_args");
        c.remove("bridge_args");
        if (!z.a(string)) {
            try {
                c.putBundle("com.facebook.platform.protocol.BRIDGE_ARGS", c.a(new JSONObject(string)));
            } catch (JSONException e) {
                z.a(TAG, "Unable to parse bridge_args JSON", (Throwable) e);
            }
        }
        String string2 = c.getString("method_results");
        c.remove("method_results");
        if (!z.a(string2)) {
            if (z.a(string2)) {
                string2 = "{}";
            }
            try {
                c.putBundle("com.facebook.platform.protocol.RESULT_ARGS", c.a(new JSONObject(string2)));
            } catch (JSONException e2) {
                z.a(TAG, "Unable to parse bridge_args JSON", (Throwable) e2);
            }
        }
        c.remove(ResponseConstants.VERSION);
        c.putInt("com.facebook.platform.protocol.PROTOCOL_VERSION", v.a());
        return c;
    }

    public void cancel() {
        WebView webView = getWebView();
        if (!isPageFinished() || isListenerCalled() || webView == null || !webView.isShown()) {
            super.cancel();
        } else if (!this.waitingForDialogToClose) {
            this.waitingForDialogToClose = true;
            StringBuilder sb = new StringBuilder();
            sb.append("javascript:");
            sb.append("(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            webView.loadUrl(sb.toString());
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                public void run() {
                    FacebookWebFallbackDialog.super.cancel();
                }
            }, 1500);
        }
    }
}
