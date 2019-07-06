package com.threatmetrix.TrustDefender.internal;

import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

class QY extends WebChromeClient {

    /* renamed from: if reason: not valid java name */
    private final EF f505if;

    /* renamed from: int reason: not valid java name */
    private final String f506int = TL.m331if(QY.class);

    public QY(EF ef) {
        this.f505if = ef;
    }

    public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
        TL.m338new(this.f506int, "onJsAlert() -".concat(String.valueOf(str2)));
        this.f505if.getString(str2);
        jsResult.confirm();
        return true;
    }
}
