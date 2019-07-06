package com.klarna.checkout.internal.c;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.klarna.checkout.browser.BrowserActivityListener;
import com.klarna.checkout.internal.a;
import com.klarna.checkout.internal.c;
import com.klarna.checkout.internal.js.interfaces.JSBridgeEvent;
import java.net.URISyntaxException;

@SuppressLint({"SetJavaScriptEnabled", "NewApi"})
public final class e {
    public static WebView a(a aVar, WebView webView) {
        if (webView == null) {
            webView = aVar.g;
        }
        if (webView == null) {
            webView = new c(aVar);
            webView.setWebViewClient(a(aVar));
            a.a(webView, "800");
        } else if (VERSION.SDK_INT >= 11) {
            webView.removeJavascriptInterface("KCO_NATIVE");
            webView.removeJavascriptInterface("KCO_HANDSHAKE");
        }
        a(webView);
        if (aVar.c()) {
            return webView;
        }
        if (aVar.g instanceof c) {
            webView.getSettings().setAppCacheEnabled(true);
            webView.getSettings().setAppCachePath(aVar.j.getApplicationContext().getCacheDir().getPath());
        }
        webView.addJavascriptInterface(new JSBridgeEvent(aVar, false), "KCO_NATIVE");
        webView.addJavascriptInterface(new JSBridgeEvent(aVar, false), "KCO_HANDSHAKE");
        CookieManager.getInstance().setAcceptCookie(true);
        return webView;
    }

    public static WebViewClient a(final a aVar) {
        return new WebViewClient() {
            public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
                if (str.startsWith("mailto:")) {
                    a.a((Context) aVar.j, a.a(str));
                    return true;
                } else if (str.startsWith("http")) {
                    return false;
                } else {
                    aVar.j.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
                    return true;
                }
            }
        };
    }

    public static String a(String str) {
        if (!str.startsWith("//")) {
            return str;
        }
        StringBuilder sb = new StringBuilder("https:");
        sb.append(str);
        return sb.toString();
    }

    public static void a(WebView webView) {
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        if (VERSION.SDK_INT >= 19) {
            WebView.setWebContentsDebuggingEnabled(false);
        }
        if (VERSION.SDK_INT >= 9) {
            webView.setOverScrollMode(2);
        }
    }

    public static void a(String str, WebView webView, Activity activity, BrowserActivityListener browserActivityListener) {
        try {
            Intent parseUri = Intent.parseUri(str, 1);
            if (parseUri.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(parseUri);
                browserActivityListener.onIntentOpened(str);
                return;
            }
            String stringExtra = parseUri.getStringExtra("browser_fallback_url");
            if (stringExtra != null) {
                webView.loadUrl(stringExtra);
                browserActivityListener.onIntentOpened(str);
                return;
            }
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(str));
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
                browserActivityListener.onIntentOpened(str);
                return;
            }
            Intent intent2 = new Intent("android.intent.action.VIEW");
            StringBuilder sb = new StringBuilder("market://details?id=");
            sb.append(parseUri.getPackage());
            Intent data = intent2.setData(Uri.parse(sb.toString()));
            if (data.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(data);
                browserActivityListener.onLinkBlocked(str);
            }
        } catch (URISyntaxException e) {
            StringBuilder sb2 = new StringBuilder("processing intent failed for url : ");
            sb2.append(str);
            sb2.append("\n");
            sb2.append(e.getLocalizedMessage());
        }
    }
}
