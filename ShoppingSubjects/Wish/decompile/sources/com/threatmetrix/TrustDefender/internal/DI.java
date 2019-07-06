package com.threatmetrix.TrustDefender.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.threatmetrix.TrustDefender.internal.N.Q;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DI {

    /* renamed from: do reason: not valid java name */
    private static final Lock f132do = new ReentrantLock();

    /* renamed from: if reason: not valid java name */
    private static final String f133if = TL.m331if(DI.class);

    /* renamed from: new reason: not valid java name */
    private static volatile WebView f134new;

    private DI() {
    }

    /* renamed from: if reason: not valid java name */
    public static WebView m48if(Context context) {
        if (!Q.m167for() || Looper.getMainLooper() != Looper.myLooper()) {
            return null;
        }
        if (f134new == null) {
            try {
                f132do.lock();
                if (f134new == null) {
                    f134new = new WebView(context);
                }
            } catch (Throwable th) {
                f132do.unlock();
                throw th;
            }
            f132do.unlock();
        } else {
            TL.m338new(f133if, "Reusing webview");
        }
        return f134new;
    }

    /* renamed from: int reason: not valid java name */
    public static boolean m50int() {
        try {
            f132do.lock();
            return f134new != null;
        } finally {
            f132do.unlock();
        }
    }

    /* renamed from: if reason: not valid java name */
    public static void m49if() {
        if (Q.m167for()) {
            try {
                f132do.lock();
                final WebView webView = f134new;
                if (webView != null) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        public final void run() {
                            webView.removeAllViews();
                            webView.destroy();
                        }
                    });
                }
                f134new = null;
            } finally {
                f132do.unlock();
            }
        }
    }
}
