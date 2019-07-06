package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.net.http.SslError;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.common.internal.GmsClientSupervisor;

@bu
final class pd extends WebViewClient {
    private final pg a;
    private final pl b;
    private final pi c;
    private final pk d;
    private final pm e = new pm();

    pd(pg pgVar, pl plVar, pi piVar, pk pkVar) {
        this.a = pgVar;
        this.b = plVar;
        this.c = piVar;
        this.d = pkVar;
    }

    private final boolean a(pe peVar) {
        return this.a.zza(peVar);
    }

    private final WebResourceResponse b(pe peVar) {
        return this.b.zzd(peVar);
    }

    public final void onLoadResource(WebView webView, String str) {
        if (str != null) {
            String str2 = "Loading resource: ";
            String valueOf = String.valueOf(str);
            gv.a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            this.c.zzb(new pe(str));
        }
    }

    public final void onPageFinished(WebView webView, String str) {
        if (str != null) {
            this.d.zzc(new pe(str));
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        this.e.a(i, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        this.e.a(sslError);
    }

    @TargetApi(24)
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            return null;
        }
        return b(new pe(webResourceRequest));
    }

    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (str == null) {
            return null;
        }
        return b(new pe(str));
    }

    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        int keyCode = keyEvent.getKeyCode();
        if (!(keyCode == 79 || keyCode == 222)) {
            switch (keyCode) {
                case 85:
                case 86:
                case 87:
                case 88:
                case 89:
                case 90:
                case 91:
                    break;
                default:
                    switch (keyCode) {
                        case 126:
                        case 127:
                        case 128:
                        case GmsClientSupervisor.DEFAULT_BIND_FLAGS /*129*/:
                        case 130:
                            break;
                        default:
                            return false;
                    }
            }
        }
        return true;
    }

    @TargetApi(24)
    public final boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest webResourceRequest) {
        if (webResourceRequest == null || webResourceRequest.getUrl() == null) {
            return false;
        }
        return a(new pe(webResourceRequest));
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str == null) {
            return false;
        }
        return a(new pe(str));
    }
}
