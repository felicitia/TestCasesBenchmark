package com.etsy.android.uikit.webview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build.VERSION;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.etsy.android.lib.config.a;
import com.etsy.android.lib.config.g;
import com.etsy.android.lib.core.http.body.BaseHttpBody;
import com.etsy.android.lib.core.http.body.HttpBody;
import com.etsy.android.lib.core.http.request.BaseHttpRequest;
import com.etsy.android.lib.core.http.request.HttpRequest;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.models.apiv3.cart.AndroidPayDataContract;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map.Entry;

/* compiled from: WebViewUtil */
public class b {
    @Deprecated
    public static String a() {
        StringBuilder sb = new StringBuilder();
        sb.append(a.a().d().b(com.etsy.android.lib.config.b.ck));
        sb.append("/externalredirect");
        return sb.toString();
    }

    public static HttpRequest b() {
        return (HttpRequest) ((HttpRequest.a) ((HttpRequest.a) new HttpRequest.a(a.a().d().b(com.etsy.android.lib.config.b.ck), "/externalredirect").a(BaseHttpRequest.POST)).a((BaseHttpBody) new HttpBody.a(a(new HashMap(), 10)).a("application/x-www-form-urlencoded").a())).d();
    }

    public static String a(AndroidPayDataContract androidPayDataContract) {
        StringBuilder sb = new StringBuilder();
        sb.append(a.a().d().b(com.etsy.android.lib.config.b.ck));
        sb.append(String.format("/cart/%s/android_pay/", new Object[]{Integer.valueOf(androidPayDataContract.getCartId())}));
        return sb.toString();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public static void a(WebView webView, WebViewClient webViewClient, WebChromeClient webChromeClient) {
        c();
        webView.setWebViewClient(webViewClient);
        webView.setWebChromeClient(webChromeClient);
        webView.getSettings().setAllowFileAccess(false);
        webView.getSettings().setAllowContentAccess(false);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setRenderPriority(RenderPriority.HIGH);
        webView.getSettings().setUserAgentString(g.a().a(webView.getSettings().getUserAgentString()));
        webView.requestFocus(130);
        a(webView);
        b(webView);
    }

    public static void a(WebView webView) {
        CookieSyncManager createInstance = CookieSyncManager.createInstance(webView.getContext());
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        String b = a.a().d().b(com.etsy.android.lib.config.b.ck);
        StringBuilder sb = new StringBuilder();
        sb.append("Accept-Language=");
        sb.append(Locale.getDefault().getLanguage());
        instance.setCookie(b, sb.toString());
        createInstance.sync();
    }

    private static void b(WebView webView) {
        CookieSyncManager createInstance = CookieSyncManager.createInstance(webView.getContext());
        CookieManager instance = CookieManager.getInstance();
        instance.setAcceptCookie(true);
        String b = a.a().d().b(com.etsy.android.lib.config.b.ck);
        StringBuilder sb = new StringBuilder();
        sb.append("etala_override=");
        sb.append(com.etsy.android.lib.logger.legacy.b.a().b());
        instance.setCookie(b, sb.toString());
        createInstance.sync();
    }

    @TargetApi(19)
    private static void c() {
        if (VERSION.SDK_INT >= 19 && f.a()) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    public static byte[] a(HashMap<String, String> hashMap, int i) {
        hashMap.put("redirect_id", String.valueOf(i));
        return a(hashMap);
    }

    public static byte[] a(HashMap<String, String> hashMap) {
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        if (v.a().e()) {
            hashMap.put("token", v.a().g().c().getToken());
        }
        hashMap.put("api_key", a.a().d().b(com.etsy.android.lib.config.b.cE));
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Entry entry : hashMap.entrySet()) {
            if (i != 0) {
                sb.append("&");
            }
            sb.append((String) entry.getKey());
            sb.append("=");
            sb.append((String) entry.getValue());
            i++;
        }
        return sb.toString().getBytes(Charset.forName("UTF-8"));
    }
}
