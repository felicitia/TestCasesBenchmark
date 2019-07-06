package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.webkit.WebView;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;

@bu
final class ph {
    @VisibleForTesting
    private static Boolean a;

    private ph() {
    }

    @TargetApi(19)
    static void a(WebView webView, String str) {
        if (!PlatformVersion.isAtLeastKitKat() || !a(webView)) {
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            webView.loadUrl(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        webView.evaluateJavascript(str, null);
    }

    @TargetApi(19)
    private static boolean a(WebView webView) {
        boolean booleanValue;
        synchronized (ph.class) {
            if (a == null) {
                try {
                    webView.evaluateJavascript("(function(){})()", null);
                    a = Boolean.valueOf(true);
                } catch (IllegalStateException unused) {
                    a = Boolean.valueOf(false);
                }
            }
            booleanValue = a.booleanValue();
        }
        return booleanValue;
    }
}
