package com.etsy.android.uikit.webview;

import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import com.etsy.android.lib.config.b;
import com.etsy.android.lib.config.c;
import com.etsy.android.lib.core.http.a;
import com.etsy.android.lib.core.http.request.a.C0065a;
import com.etsy.android.lib.core.http.request.e;
import com.etsy.android.lib.core.v;
import com.etsy.android.lib.logger.f;
import com.etsy.android.lib.util.af;
import com.etsy.android.lib.util.n;
import java.io.IOException;
import java.net.CookieHandler;
import java.net.URI;
import java.util.List;
import java.util.Map;

public class EtsyWebViewClient extends WebViewClient {
    private static final String TAG = f.a(EtsyWebViewClient.class);
    protected c configMap;
    private a hostnameReplacer;
    /* access modifiers changed from: private */
    public boolean mAttemptedAuth;
    private ProgressBar mProgressBar;

    public EtsyWebViewClient(c cVar, ProgressBar progressBar) {
        this.hostnameReplacer = new a(cVar);
        this.mProgressBar = progressBar;
    }

    public boolean shouldOverrideUrlLoading(final WebView webView, String str) {
        b.a(webView);
        if (shouldReplaceHostnameForGCP(str)) {
            webView.loadUrl(replaceHostnameInUrlIfGCP(str));
            return true;
        }
        final Uri parse = Uri.parse(str);
        if (this.mAttemptedAuth || !n.c(parse.getHost()) || parse.getPathSegments().isEmpty() || ((!"signin".equals(parse.getPathSegments().get(0)) && !"join".equals(parse.getPathSegments().get(0)) && (parse.getPathSegments().size() < 2 || (!"signin".equals(parse.getPathSegments().get(1)) && !"join".equals(parse.getPathSegments().get(1))))) || !v.a().e())) {
            return false;
        }
        injectCookies(new C0065a<a>() {
            /* access modifiers changed from: protected */
            public void a(@NonNull a aVar) {
                EtsyWebViewClient.this.mAttemptedAuth = true;
                EtsyWebViewClient.this.goToRedirectUrl(webView, parse.getQueryParameter("from_page"));
            }
        });
        return true;
    }

    @VisibleForTesting
    public void goToRedirectUrl(@NonNull WebView webView, @Nullable String str) {
        Uri uri;
        if (af.b(str)) {
            uri = Uri.parse(replaceHostnameInUrlIfGCP(str));
            if (!af.b(uri.getHost())) {
                if (!str.startsWith("/")) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("/");
                    sb.append(str);
                    str = sb.toString();
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append(com.etsy.android.lib.config.a.a().d().b(b.ck));
                sb2.append(str);
                uri = Uri.parse(sb2.toString());
            }
        } else {
            uri = Uri.parse(com.etsy.android.lib.config.a.a().d().b(b.ck));
        }
        webView.loadUrl(uri.toString());
    }

    public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        super.onPageStarted(webView, str, bitmap);
        if (this.mProgressBar != null && this.mProgressBar.getVisibility() == 8) {
            this.mProgressBar.setVisibility(0);
            this.mProgressBar.setProgress(0);
        }
    }

    public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        String str = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Webview received SSL error: ");
        sb.append(sslError);
        f.d(str, sb.toString());
        if (com.etsy.android.lib.config.a.a().c()) {
            f.b(TAG, "Webview proceeding with SSL error on dev.");
            sslErrorHandler.proceed();
            return;
        }
        sslErrorHandler.cancel();
    }

    public void onReceivedError(WebView webView, int i, String str, String str2) {
        super.onReceivedError(webView, i, str, str2);
        String str3 = TAG;
        StringBuilder sb = new StringBuilder();
        sb.append("Webview received error code: ");
        sb.append(i);
        sb.append(" with error description: ");
        sb.append(str);
        f.d(str3, sb.toString());
        if (this.mProgressBar != null) {
            this.mProgressBar.setVisibility(8);
        }
    }

    private void injectCookies(C0065a<a> aVar) {
        final CookieHandler cookieHandler = CookieHandler.getDefault();
        CookieHandler.setDefault(new CookieHandler() {
            public Map<String, List<String>> get(URI uri, Map<String, List<String>> map) throws IOException {
                return cookieHandler != null ? cookieHandler.get(uri, map) : map;
            }

            public void put(URI uri, Map<String, List<String>> map) throws IOException {
                if (cookieHandler != null) {
                    cookieHandler.put(uri, map);
                }
                if ("/externalredirect".equals(uri.getPath())) {
                    String b2 = com.etsy.android.lib.config.a.a().d().b(b.ck);
                    CookieManager instance = CookieManager.getInstance();
                    instance.setAcceptCookie(true);
                    for (String cookie : (List) map.get("Set-Cookie")) {
                        instance.setCookie(b2, cookie);
                    }
                    CookieHandler.setDefault(cookieHandler);
                }
            }
        });
        v.a().j().a(((e.a) new e.a(b.b()).b(aVar)).c());
    }

    /* access modifiers changed from: protected */
    public String replaceHostnameInUrlIfGCP(String str) {
        return this.hostnameReplacer.b(str);
    }

    /* access modifiers changed from: protected */
    public boolean shouldReplaceHostnameForGCP(String str) {
        return this.hostnameReplacer.a(str);
    }
}
