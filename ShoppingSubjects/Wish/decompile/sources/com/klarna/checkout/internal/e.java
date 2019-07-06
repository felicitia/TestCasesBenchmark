package com.klarna.checkout.internal;

import android.webkit.WebView;
import com.klarna.checkout.browser.BrowserActivityListener;

public final class e implements BrowserActivityListener {
    String a = "KCO_NoOpActivityListen";

    public final void onActivityClosed() {
    }

    public final void onExternalBrowserOpened() {
    }

    public final void onIntentOpened(String str) {
    }

    public final void onLinkBlocked(String str) {
    }

    public final void onPageOpened(WebView webView, String str) {
        StringBuilder sb = new StringBuilder("NoOp on page opened");
        sb.append(str);
        sb.append(webView);
    }
}
