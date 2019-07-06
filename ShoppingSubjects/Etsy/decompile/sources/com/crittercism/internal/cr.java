package com.crittercism.internal;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Message;
import android.util.Base64;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.webkit.ClientCertRequest;
import android.webkit.HttpAuthHandler;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class cr extends WebViewClient {
    private WebViewClient a;
    private c b;
    private final String c;
    private d d;
    private b e;

    public cr(WebViewClient webViewClient, d dVar, c cVar, String str) {
        this.a = webViewClient;
        this.d = dVar;
        this.b = cVar;
        StringBuilder sb = new StringBuilder();
        sb.append("javascript:(function() {");
        sb.append("  if (typeof(Crittercism) !== \"undefined\") {");
        sb.append("    Crittercism.init({");
        sb.append("      \"platform\": \"android\"});");
        sb.append("  } else {");
        sb.append("    (");
        sb.append("      function() {");
        sb.append("        var parent = document.getElementsByTagName('head').item(0);");
        sb.append("        var script = document.createElement('script');");
        sb.append("        script.type = 'text/javascript';");
        sb.append("        script.innerHTML = window.atob('");
        sb.append(Base64.encodeToString(str.getBytes(), 2));
        sb.append("                                     ');");
        sb.append("        parent.appendChild(script)");
        sb.append("      }");
        sb.append("    )();");
        sb.append("    if (typeof(BasicCrittercism) !== \"undefined\") {");
        sb.append("      BasicCrittercism.instrumentOnError({");
        sb.append("        errorCallback: function(errorMsg, stackStr) {");
        sb.append("          _crttr.logError(errorMsg, stackStr);");
        sb.append("          }, ");
        sb.append("        platform: \"android\"");
        sb.append("      });");
        sb.append("      BasicCrittercism.initApm();");
        sb.append("    } ");
        sb.append("  }");
        sb.append("})()");
        this.c = sb.toString();
    }

    @TargetApi(21)
    public final WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        long currentTimeMillis = System.currentTimeMillis();
        try {
            if (VERSION.SDK_INT >= 23 && webResourceRequest != null) {
                if (webResourceRequest.isForMainFrame()) {
                    cm.d("******** shouldInterceptRequest (Lollipop) part 1");
                    synchronized (this) {
                        this.e = new b();
                        this.e.a(webResourceRequest.getUrl().toString());
                        this.e.j = webResourceRequest.getMethod();
                        this.e.c(currentTimeMillis);
                        this.e.o = a.a(this.b.a);
                        if (an.b()) {
                            this.e.a(an.a());
                        }
                    }
                    return r0;
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
        WebResourceResponse webResourceResponse = null;
        if (this.a != null) {
            webResourceResponse = this.a.shouldInterceptRequest(webView, webResourceRequest);
        }
        try {
            if (VERSION.SDK_INT >= 23 && webResourceRequest != null) {
                if (webResourceRequest.isForMainFrame()) {
                    cm.d("******** shouldInterceptRequest (Lollipop) part 2");
                    if (webResourceResponse != null) {
                        synchronized (this) {
                            if (this.e != null) {
                                this.e.i = webResourceResponse.getStatusCode();
                            }
                        }
                    }
                }
            }
        } catch (ThreadDeath e3) {
            throw e3;
        } catch (Throwable th2) {
            cm.b(th2);
        }
        return webResourceResponse;
    }

    public final void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
        if (this.a != null) {
            this.a.doUpdateVisitedHistory(webView, str, z);
        }
    }

    public final void onFormResubmission(WebView webView, Message message, Message message2) {
        if (this.a != null) {
            this.a.onFormResubmission(webView, message, message2);
        }
    }

    public final void onLoadResource(WebView webView, String str) {
        if (this.a != null) {
            this.a.onLoadResource(webView, str);
        }
    }

    public final void onPageStarted(WebView webView, String str, Bitmap bitmap) {
        StringBuilder sb = new StringBuilder("******** onPageStarted: ");
        sb.append(str);
        cm.d(sb.toString());
        if (this.a != null) {
            this.a.onPageStarted(webView, str, bitmap);
        }
    }

    @TargetApi(21)
    public final void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
        if (this.a != null) {
            this.a.onReceivedClientCertRequest(webView, clientCertRequest);
        }
    }

    @TargetApi(23)
    public final void onPageCommitVisible(WebView webView, String str) {
        if (this.a != null) {
            this.a.onPageCommitVisible(webView, str);
        }
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        if (this.a != null) {
            this.a.onReceivedError(webView, i, str, str2);
        }
    }

    public final void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
        if (this.a != null) {
            this.a.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
        }
    }

    @TargetApi(12)
    public final void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
        if (this.a != null) {
            this.a.onReceivedLoginRequest(webView, str, str2, str3);
        }
    }

    @TargetApi(8)
    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (this.a != null) {
            this.a.onReceivedSslError(webView, sslErrorHandler, sslError);
        }
    }

    public final void onScaleChanged(WebView webView, float f, float f2) {
        if (this.a != null) {
            this.a.onScaleChanged(webView, f, f2);
        }
    }

    public final void onTooManyRedirects(WebView webView, Message message, Message message2) {
        if (this.a != null) {
            this.a.onTooManyRedirects(webView, message, message2);
        }
    }

    @TargetApi(21)
    public final void onUnhandledInputEvent(WebView webView, InputEvent inputEvent) {
        if (this.a != null) {
            this.a.onUnhandledInputEvent(webView, inputEvent);
        }
    }

    public final void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
        if (this.a != null) {
            this.a.onUnhandledKeyEvent(webView, keyEvent);
        }
    }

    @TargetApi(11)
    public final WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        if (this.a != null) {
            return this.a.shouldInterceptRequest(webView, str);
        }
        return null;
    }

    public final boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
        if (this.a != null) {
            return this.a.shouldOverrideKeyEvent(webView, keyEvent);
        }
        return false;
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        StringBuilder sb = new StringBuilder("******** shouldOverrideUrlLoading: ");
        sb.append(str);
        cm.d(sb.toString());
        if (this.a != null) {
            return this.a.shouldOverrideUrlLoading(webView, str);
        }
        return false;
    }

    public final void onPageFinished(WebView webView, String str) {
        try {
            StringBuilder sb = new StringBuilder("******** onPageFinished: ");
            sb.append(str);
            cm.d(sb.toString());
            if (VERSION.SDK_INT >= 23) {
                synchronized (this) {
                    if (this.e != null) {
                        this.e.d(System.currentTimeMillis());
                        this.d.a(this.e);
                        this.e = null;
                    }
                }
            }
            if (webView.getSettings().getJavaScriptEnabled()) {
                webView.loadUrl(this.c);
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
        if (this.a != null) {
            this.a.onPageFinished(webView, str);
        }
    }

    @TargetApi(23)
    public final void onReceivedHttpError(WebView webView, WebResourceRequest webResourceRequest, WebResourceResponse webResourceResponse) {
        try {
            if (VERSION.SDK_INT >= 23) {
                cm.d("******** onReceivedHttpError (Marshmallow)");
                boolean isForMainFrame = webResourceRequest.isForMainFrame();
                StringBuilder sb = new StringBuilder();
                sb.append(isForMainFrame ? "" : "not ");
                sb.append("main frame");
                cm.d(sb.toString());
                if (isForMainFrame) {
                    if (webResourceResponse == null) {
                        cm.d("null response (no status code)");
                    } else {
                        synchronized (this) {
                            if (this.e != null) {
                                this.e.i = webResourceResponse.getStatusCode();
                            }
                        }
                    }
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
        if (this.a != null) {
            this.a.onReceivedHttpError(webView, webResourceRequest, webResourceResponse);
        }
    }

    @TargetApi(23)
    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        try {
            if (VERSION.SDK_INT >= 23) {
                cm.d("******** onReceivedError (Marshmallow, no http)");
                if (webResourceRequest == null) {
                    cm.d("null request");
                } else {
                    boolean isForMainFrame = webResourceRequest.isForMainFrame();
                    StringBuilder sb = new StringBuilder();
                    sb.append(isForMainFrame ? "" : "not ");
                    sb.append("main frame");
                    cm.d(sb.toString());
                    if (isForMainFrame) {
                        if (webResourceError == null) {
                            cm.d("null error (no error code)");
                        } else {
                            synchronized (this) {
                                if (this.e != null) {
                                    this.e.k = new bm(bn.e - 1, webResourceError.getErrorCode());
                                }
                            }
                        }
                    }
                }
            }
        } catch (ThreadDeath e2) {
            throw e2;
        } catch (Throwable th) {
            cm.b(th);
        }
        if (this.a != null) {
            this.a.onReceivedError(webView, webResourceRequest, webResourceError);
        }
    }
}
