package com.contextlogic.wish.activity.cart.billing.paymentform;

import android.webkit.WebView;

public interface PaymentFormServiceProvider {
    void cleanupCachedEmbeddedBillingWebView();

    WebView getCachedEmbeddedBillingWebView();

    void setCachedEmbeddedBillingWebView(WebView webView);
}
