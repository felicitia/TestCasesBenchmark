package com.klarna.checkout.browser;

import android.webkit.WebView;

public interface BrowserActivityListener {
    void onActivityClosed();

    void onExternalBrowserOpened();

    void onIntentOpened(String str);

    void onLinkBlocked(String str);

    void onPageOpened(WebView webView, String str);
}
