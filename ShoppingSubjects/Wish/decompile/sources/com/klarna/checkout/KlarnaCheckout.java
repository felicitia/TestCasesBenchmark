package com.klarna.checkout;

import android.app.Activity;
import android.webkit.WebView;
import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.c.e;
import java.security.InvalidParameterException;

public final class KlarnaCheckout {
    private final a a;

    public KlarnaCheckout(Activity activity, String str) {
        if (str == null || str.trim().isEmpty()) {
            throw new InvalidParameterException("Please provide return URL to the checkout activity");
        }
        this.a = new a(activity, str);
    }

    public final void setSignalListener(SignalListener signalListener) {
        this.a.i = signalListener;
    }

    public final void setWebView(WebView webView) {
        a aVar = this.a;
        aVar.g = e.a(aVar, webView);
    }
}
