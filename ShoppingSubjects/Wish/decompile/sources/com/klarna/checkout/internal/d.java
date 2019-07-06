package com.klarna.checkout.internal;

import android.annotation.SuppressLint;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.klarna.checkout.internal.c.c;

public final class d {
    public final String a;
    final WebView b;
    public int c;
    private final String d;
    private final long e = System.currentTimeMillis();

    public d(String str, String str2, WebView webView) {
        this.b = webView;
        this.a = c.a(str);
        this.d = str2;
        StringBuilder sb = new StringBuilder("Preparing to send to ");
        sb.append(webView.getTag());
        sb.append(" ID:");
        sb.append(this.c);
    }

    public final void a() {
        String str = this.a;
        StringBuilder sb = new StringBuilder("javascript:(function(){ window.postMessage('");
        sb.append(str);
        sb.append("','");
        sb.append(this.d);
        sb.append("');})();");
        final String sb2 = sb.toString();
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @SuppressLint({"NewApi"})
            public final void run() {
                try {
                    if (VERSION.SDK_INT >= 19) {
                        d.this.b.evaluateJavascript(sb2, null);
                    } else {
                        d.this.b.loadUrl(sb2);
                    }
                } catch (NullPointerException unused) {
                }
                StringBuilder sb = new StringBuilder("SENT to ");
                sb.append(d.this.b.getTag());
                sb.append(" ID:");
                sb.append(d.this.c);
                sb.append(" ");
                sb.append(sb2);
            }
        });
    }

    public final boolean b() {
        return "fullscreen".equals(this.b.getTag());
    }
}
