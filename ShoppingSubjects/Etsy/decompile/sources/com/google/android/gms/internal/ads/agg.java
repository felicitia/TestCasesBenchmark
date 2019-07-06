package com.google.android.gms.internal.ads;

import android.webkit.ValueCallback;
import android.webkit.WebView;

final class agg implements Runnable {
    final /* synthetic */ afy a;
    final /* synthetic */ WebView b;
    final /* synthetic */ boolean c;
    final /* synthetic */ age d;
    private ValueCallback<String> e = new agh(this);

    agg(age age, afy afy, WebView webView, boolean z) {
        this.d = age;
        this.a = afy;
        this.b = webView;
        this.c = z;
    }

    public final void run() {
        if (this.b.getSettings().getJavaScriptEnabled()) {
            try {
                this.b.evaluateJavascript("(function() { return  {text:document.body.innerText}})();", this.e);
            } catch (Throwable unused) {
                this.e.onReceiveValue("");
            }
        }
    }
}
