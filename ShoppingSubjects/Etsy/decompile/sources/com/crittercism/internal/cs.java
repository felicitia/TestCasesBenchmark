package com.crittercism.internal;

import android.os.Build.VERSION;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public final class cs {
    public cs() {
        if (VERSION.SDK_INT < 14 || VERSION.SDK_INT > 23) {
            StringBuilder sb = new StringBuilder("API Level ");
            sb.append(VERSION.SDK_INT);
            sb.append(" does not supportWebView monitoring. Skipping instrumentation.");
            throw new bk(sb.toString());
        }
    }

    public static WebViewClient a(WebView webView) {
        try {
            Object obj = e.a(WebView.class, Class.forName("android.webkit.CallbackProxy"), true).get(webView);
            return (WebViewClient) obj.getClass().getMethod("getWebViewClient", new Class[0]).invoke(obj, new Object[0]);
        } catch (ClassNotFoundException e) {
            throw new bk((Throwable) e);
        } catch (InvocationTargetException e2) {
            throw new bk((Throwable) e2);
        } catch (NoSuchMethodException e3) {
            throw new bk((Throwable) e3);
        } catch (IllegalAccessException e4) {
            throw new bk((Throwable) e4);
        } catch (SecurityException e5) {
            throw new bk((Throwable) e5);
        }
    }

    public static WebViewClient b(WebView webView) {
        try {
            Object invoke = WebView.class.getMethod("getWebViewProvider", new Class[0]).invoke(webView, new Object[0]);
            return (WebViewClient) invoke.getClass().getMethod("getWebViewClient", new Class[0]).invoke(invoke, new Object[0]);
        } catch (InvocationTargetException e) {
            throw new bk((Throwable) e);
        } catch (NoSuchMethodException e2) {
            throw new bk((Throwable) e2);
        } catch (IllegalAccessException e3) {
            throw new bk((Throwable) e3);
        } catch (SecurityException e4) {
            throw new bk((Throwable) e4);
        }
    }

    public static WebViewClient c(WebView webView) {
        try {
            Object invoke = WebView.class.getMethod("getWebViewProvider", new Class[0]).invoke(webView, new Object[0]);
            Field declaredField = invoke.getClass().getDeclaredField("mContentsClientAdapter");
            declaredField.setAccessible(true);
            Object obj = declaredField.get(invoke);
            Field declaredField2 = obj.getClass().getDeclaredField("mWebViewClient");
            declaredField2.setAccessible(true);
            return (WebViewClient) declaredField2.get(obj);
        } catch (InvocationTargetException e) {
            throw new bk((Throwable) e);
        } catch (NoSuchMethodException e2) {
            throw new bk((Throwable) e2);
        } catch (NoSuchFieldException e3) {
            throw new bk((Throwable) e3);
        } catch (IllegalAccessException e4) {
            throw new bk((Throwable) e4);
        } catch (SecurityException e5) {
            throw new bk((Throwable) e5);
        }
    }
}
