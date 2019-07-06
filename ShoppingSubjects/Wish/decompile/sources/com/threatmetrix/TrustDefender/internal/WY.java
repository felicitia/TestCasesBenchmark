package com.threatmetrix.TrustDefender.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebSettings.PluginState;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.threatmetrix.TrustDefender.internal.N.Q;
import com.threatmetrix.TrustDefender.internal.P.O;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.TreeMap;

public class WY extends D2 {

    /* renamed from: break reason: not valid java name */
    private static final TreeMap<Integer, String> f592break;

    /* renamed from: byte reason: not valid java name */
    private static final Method f593byte;

    /* renamed from: if reason: not valid java name */
    static final Method f594if;

    /* renamed from: int reason: not valid java name */
    static final String f595int = TL.m331if(WY.class);

    /* renamed from: this reason: not valid java name */
    private static final Method f596this;

    /* renamed from: try reason: not valid java name */
    private static final Method f597try;

    /* renamed from: void reason: not valid java name */
    private static final Method f598void;

    /* renamed from: case reason: not valid java name */
    final boolean f599case;

    /* renamed from: char reason: not valid java name */
    final WebSettings f600char;

    /* renamed from: do reason: not valid java name */
    final WebView f601do;

    /* renamed from: else reason: not valid java name */
    boolean f602else;

    /* renamed from: for reason: not valid java name */
    final EF f603for;

    /* renamed from: new reason: not valid java name */
    boolean f604new;

    static {
        Method method = m44for(WebView.class, "evaluateJavascript", String.class, ValueCallback.class);
        f593byte = method;
        if (method == null && C0012I.f388for >= 19) {
            TL.m332if(f595int, "Failed to find expected function: evaluateJavascript");
        }
        Method method2 = m44for(WebSettings.class, "getDefaultUserAgent", Context.class);
        f594if = method2;
        if (method2 == null && C0012I.f388for >= 17) {
            TL.m332if(f595int, "Failed to find expected function: getDefaultUserAgent");
        }
        Method method3 = m44for(WebSettings.class, "setPluginState", PluginState.class);
        f597try = method3;
        if (method3 == null && (C0012I.f388for >= 9 || C0012I.f388for <= 18)) {
            TL.m332if(f595int, "Failed to find expected function: setPluginState");
        }
        Method method4 = m44for(WebView.class, "removeJavascriptInterface", String.class);
        f598void = method4;
        if (method4 == null && C0012I.f388for >= 11) {
            TL.m332if(f595int, "Failed to find expected function: removeJavascriptInterface");
        }
        Method method5 = m44for(WebView.class, "addJavascriptInterface", Object.class, String.class);
        f596this = method5;
        if (method5 == null && C0012I.f388for >= 17) {
            TL.m332if(f595int, "Failed to find expected function: addJavascriptInterface");
        }
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        f592break = treeMap;
        treeMap.put(Integer.valueOf(W.f400for), "533.1");
        f592break.put(Integer.valueOf(W.f403int), "533.1");
        f592break.put(Integer.valueOf(W.f402if), "533.1");
        f592break.put(Integer.valueOf(W.f397do), "533.1");
        f592break.put(Integer.valueOf(W.f391byte), "534.13");
        f592break.put(Integer.valueOf(W.f398else), "534.30");
        f592break.put(Integer.valueOf(W.f407try), "534.30");
        f592break.put(Integer.valueOf(W.f394char), "534.30");
        f592break.put(Integer.valueOf(W.f392case), "534.30");
        f592break.put(Integer.valueOf(W.f404long), "534.30");
        f592break.put(Integer.valueOf(W.f408void), "537.36");
        f592break.put(Integer.valueOf(W.f406this), "537.36");
        f592break.put(Integer.valueOf(W.f401goto), "537.36");
        f592break.put(Integer.valueOf(W.f390break), "537.36");
        f592break.put(Integer.valueOf(W.f395class), "537.36");
        f592break.put(Integer.valueOf(W.f396const), "537.36");
        f592break.put(Integer.valueOf(W.f393catch), "537.36");
    }

    /* renamed from: new reason: not valid java name */
    public static boolean m372new() {
        return f593byte != null;
    }

    /* renamed from: for reason: not valid java name */
    public static boolean m371for() {
        boolean z = false;
        try {
            String str = C0012I.f389int;
            if (str != null && str.startsWith("2.3")) {
                z = true;
            }
            return z;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: for reason: not valid java name */
    public static String m370for(O o) {
        String str;
        String str2;
        String str3;
        TL.m338new(f595int, "Generating a browser string");
        if (f592break.containsKey(Integer.valueOf(C0012I.f388for))) {
            str = (String) f592break.get(Integer.valueOf(C0012I.f388for));
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append((String) f592break.lastEntry().getValue());
            sb.append("+");
            str = sb.toString();
        }
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        if (!country.isEmpty()) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(language);
            sb2.append("-");
            sb2.append(country);
            sb2.append("; ");
            str2 = sb2.toString();
        } else {
            StringBuilder sb3 = new StringBuilder();
            sb3.append(language);
            sb3.append("; ");
            str2 = sb3.toString();
        }
        String str4 = "Mozilla/5.0 (Linux; U; Android ";
        String str5 = ") AppleWebKit/";
        if (C0012I.f388for >= W.f401goto) {
            str4 = "Mozilla/5.0 (Linux; Android ";
            str2 = "";
            str5 = "; wv) AppleWebKit/";
        }
        StringBuilder sb4 = new StringBuilder();
        sb4.append(str4);
        sb4.append(C0012I.f389int);
        sb4.append("; ");
        sb4.append(str2.toLowerCase(Locale.US));
        sb4.append(I.f374char);
        sb4.append(" Build/");
        sb4.append(I.f385try);
        sb4.append(str5);
        sb4.append(str);
        sb4.append(" (KHTML, like Gecko) Version/4.0");
        Context context = o.f487for;
        B b = new B(context, "com.google.android.webview", 128);
        String str6 = null;
        String str7 = (!W.f428case || b.f363do == null) ? null : b.f363do.versionName;
        if (NK.m203byte(str7)) {
            str3 = " Chrome/".concat(str7);
        } else {
            B b2 = new B(context, "com.android.webview", 128);
            if (W.f428case && b2.f363do != null) {
                str6 = b2.f363do.versionName;
            }
            if (NK.m203byte(str6)) {
                str3 = " Chrome/".concat(str6);
            } else {
                str3 = "";
            }
        }
        sb4.append(str3);
        sb4.append(" Mobile Safari/");
        sb4.append(str);
        sb4.append(" ");
        sb4.append(K7.f270new);
        return sb4.toString();
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    WY(Context context, EF ef) {
        this.f604new = false;
        this.f602else = false;
        this.f602else = m371for();
        String str = f595int;
        StringBuilder sb = new StringBuilder("JSExecutor() Build: ");
        sb.append(C0012I.f389int);
        sb.append(this.f602else ? " busted js interface " : " normal js interface ");
        sb.append(f593byte != null ? " has async interface " : " has no async interface ");
        TL.m338new(str, sb.toString());
        this.f603for = ef;
        this.f599case = true;
        if (!Q.m167for()) {
            this.f601do = null;
            this.f600char = null;
            return;
        }
        boolean z = DI.m50int();
        this.f604new = false;
        this.f601do = DI.m48if(context);
        if (this.f601do == null) {
            this.f600char = null;
            return;
        }
        if (z && !this.f604new) {
            TL.m325do(f595int, "WebView re-used from previous instance. Using a short-lived TrustDefender object is not recommended. Better profiling performance will be achieved by re-using a long-lived TrustDefenderInternal instance");
        }
        String str2 = f595int;
        StringBuilder sb2 = new StringBuilder("Webview ");
        sb2.append(this.f604new ? "init'd" : "un-init'd");
        TL.m338new(str2, sb2.toString());
        WebViewClient webViewClient = new WebViewClient();
        this.f600char = this.f601do.getSettings();
        this.f600char.setJavaScriptEnabled(true);
        m39do((Object) this.f600char, f597try, PluginState.ON);
        this.f601do.setVisibility(4);
        if (!this.f602else) {
            m39do((Object) this.f601do, f598void, "androidJSInterface");
        }
        this.f601do.setWebViewClient(webViewClient);
        if (f593byte != null) {
            if (this.f603for.f155if == null) {
                TL.m332if(f595int, "alternate JS interface but no global latch");
            }
            TL.m338new(f595int, "JSExecutor() alternate JS interface detected");
        } else if (!this.f602else) {
            m39do((Object) this.f601do, f596this, this.f603for, "androidJSInterface");
        } else {
            if (this.f603for.f155if == null) {
                TL.m332if(f595int, "broken JS interface but no global latch");
            }
            TL.m338new(f595int, "JSExecutor() Broken JS interface detected, using workaround");
            this.f601do.setWebChromeClient(new QY(this.f603for));
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x008d  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x0092 A[SYNTHETIC, Splitter:B:31:0x0092] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:50:0x00dd  */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0116  */
    /* renamed from: if reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String m373if(java.lang.String r8) throws java.lang.InterruptedException {
        /*
            r7 = this;
            boolean r0 = r7.f604new
            r1 = 0
            if (r0 != 0) goto L_0x0006
            return r1
        L_0x0006:
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            boolean r0 = r0.isInterrupted()
            if (r0 == 0) goto L_0x0013
            java.lang.String r8 = ""
            return r8
        L_0x0013:
            boolean r0 = r7.f602else
            r2 = 0
            r3 = 1
            if (r0 != 0) goto L_0x002d
            java.lang.reflect.Method r0 = f593byte
            if (r0 == 0) goto L_0x001f
            r0 = 1
            goto L_0x0020
        L_0x001f:
            r0 = 0
        L_0x0020:
            if (r0 != 0) goto L_0x002d
            java.util.concurrent.CountDownLatch r0 = new java.util.concurrent.CountDownLatch
            r0.<init>(r3)
            com.threatmetrix.TrustDefender.internal.EF r4 = r7.f603for
            r4.m52do(r0)
            goto L_0x002e
        L_0x002d:
            r0 = r1
        L_0x002e:
            java.lang.reflect.Method r4 = f593byte
            if (r4 == 0) goto L_0x0034
            r4 = 1
            goto L_0x0035
        L_0x0034:
            r4 = 0
        L_0x0035:
            if (r4 == 0) goto L_0x004b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "javascript:(function(){try{return "
            r4.<init>(r5)
            r4.append(r8)
            java.lang.String r8 = " + \"\";}catch(js_eval_err){return '';}})();"
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            goto L_0x0076
        L_0x004b:
            boolean r4 = r7.f602else
            if (r4 != 0) goto L_0x0063
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "javascript:window.androidJSInterface.getString((function(){try{return "
            r4.<init>(r5)
            r4.append(r8)
            java.lang.String r8 = " + \"\";}catch(js_eval_err){return '';}})());"
            r4.append(r8)
            java.lang.String r8 = r4.toString()
            goto L_0x0076
        L_0x0063:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "javascript:alert((function(){try{return "
            r4.<init>(r5)
            r4.append(r8)
            java.lang.String r8 = " + \"\";}catch(js_eval_err){return '';}})());"
            r4.append(r8)
            java.lang.String r8 = r4.toString()
        L_0x0076:
            java.lang.String r4 = f595int
            java.lang.String r5 = "getJSResult() attempting to execute: "
            java.lang.String r6 = java.lang.String.valueOf(r8)
            java.lang.String r5 = r5.concat(r6)
            com.threatmetrix.TrustDefender.internal.TL.m338new(r4, r5)
            com.threatmetrix.TrustDefender.internal.EF r4 = r7.f603for
            r4.f154for = r1
            java.lang.reflect.Method r4 = f593byte
            if (r4 == 0) goto L_0x008f
            r4 = 1
            goto L_0x0090
        L_0x008f:
            r4 = 0
        L_0x0090:
            if (r4 == 0) goto L_0x00c7
            java.lang.reflect.Method r4 = f593byte     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            android.webkit.WebView r5 = r7.f601do     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            r6 = 2
            java.lang.Object[] r6 = new java.lang.Object[r6]     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            r6[r2] = r8     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            com.threatmetrix.TrustDefender.internal.EF r8 = r7.f603for     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            r6[r3] = r8     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            r4.invoke(r5, r6)     // Catch:{ IllegalAccessException -> 0x00be, IllegalArgumentException -> 0x00b5, InvocationTargetException -> 0x00ac, RuntimeException -> 0x00a3 }
            goto L_0x00d0
        L_0x00a3:
            r8 = move-exception
            java.lang.String r4 = f595int
            java.lang.String r5 = "getJSResult() invoke failed: "
            com.threatmetrix.TrustDefender.internal.TL.m337int(r4, r5, r8)
            goto L_0x00da
        L_0x00ac:
            r8 = move-exception
            java.lang.String r4 = f595int
            java.lang.String r5 = "getJSResult() invoke failed: "
            com.threatmetrix.TrustDefender.internal.TL.m337int(r4, r5, r8)
            goto L_0x00da
        L_0x00b5:
            r8 = move-exception
            java.lang.String r4 = f595int
            java.lang.String r5 = "getJSResult() invoke failed: "
            com.threatmetrix.TrustDefender.internal.TL.m337int(r4, r5, r8)
            goto L_0x00da
        L_0x00be:
            r8 = move-exception
            java.lang.String r4 = f595int
            java.lang.String r5 = "getJSResult() invoke failed: "
            com.threatmetrix.TrustDefender.internal.TL.m337int(r4, r5, r8)
            goto L_0x00da
        L_0x00c7:
            android.webkit.WebView r4 = r7.f601do
            if (r4 == 0) goto L_0x00da
            android.webkit.WebView r4 = r7.f601do     // Catch:{ RuntimeException -> 0x00d2 }
            r4.loadUrl(r8)     // Catch:{ RuntimeException -> 0x00d2 }
        L_0x00d0:
            r8 = 0
            goto L_0x00db
        L_0x00d2:
            r8 = move-exception
            java.lang.String r4 = f595int
            java.lang.String r5 = "getJSResult() invoke failed: "
            com.threatmetrix.TrustDefender.internal.TL.m337int(r4, r5, r8)
        L_0x00da:
            r8 = 1
        L_0x00db:
            if (r8 == 0) goto L_0x0116
            com.threatmetrix.TrustDefender.internal.EF r8 = r7.f603for
            java.util.concurrent.CountDownLatch r8 = r8.f155if
            if (r8 == 0) goto L_0x0115
            java.lang.String r8 = f595int
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "getJSResult countdown for latch: "
            r0.<init>(r2)
            com.threatmetrix.TrustDefender.internal.EF r2 = r7.f603for
            java.util.concurrent.CountDownLatch r2 = r2.f155if
            int r2 = r2.hashCode()
            r0.append(r2)
            java.lang.String r2 = " with count: "
            r0.append(r2)
            com.threatmetrix.TrustDefender.internal.EF r2 = r7.f603for
            java.util.concurrent.CountDownLatch r2 = r2.f155if
            long r2 = r2.getCount()
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r0)
            com.threatmetrix.TrustDefender.internal.EF r8 = r7.f603for
            java.util.concurrent.CountDownLatch r8 = r8.f155if
            r8.countDown()
        L_0x0115:
            return r1
        L_0x0116:
            boolean r8 = r7.f602else
            if (r8 != 0) goto L_0x01a5
            java.lang.reflect.Method r8 = f593byte
            if (r8 == 0) goto L_0x011f
            r2 = 1
        L_0x011f:
            if (r2 != 0) goto L_0x01a5
            if (r0 == 0) goto L_0x0174
            java.lang.String r8 = f595int
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getJSResult waiting for latch: "
            r1.<init>(r2)
            int r2 = r0.hashCode()
            r1.append(r2)
            java.lang.String r2 = " with count: "
            r1.append(r2)
            long r2 = r0.getCount()
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r1)
            r1 = 5
            java.util.concurrent.TimeUnit r8 = java.util.concurrent.TimeUnit.SECONDS
            boolean r8 = r0.await(r1, r8)
            if (r8 != 0) goto L_0x017b
            java.lang.String r8 = f595int
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "getJSResult timeout waiting for latch: "
            r1.<init>(r2)
            int r2 = r0.hashCode()
            r1.append(r2)
            java.lang.String r2 = " with count: "
            r1.append(r2)
            long r2 = r0.getCount()
            r1.append(r2)
            java.lang.String r0 = r1.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r0)
            goto L_0x017b
        L_0x0174:
            java.lang.String r8 = f595int
            java.lang.String r0 = "latch == null"
            com.threatmetrix.TrustDefender.internal.TL.m332if(r8, r0)
        L_0x017b:
            com.threatmetrix.TrustDefender.internal.EF r8 = r7.f603for
            java.lang.String r8 = r8.f154for
            if (r8 != 0) goto L_0x0189
            java.lang.String r8 = f595int
            java.lang.String r0 = "getJSResult() After latch: got null"
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r0)
            goto L_0x01a0
        L_0x0189:
            java.lang.String r8 = f595int
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "getJSResult() After latch: got "
            r0.<init>(r1)
            com.threatmetrix.TrustDefender.internal.EF r1 = r7.f603for
            java.lang.String r1 = r1.f154for
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            com.threatmetrix.TrustDefender.internal.TL.m338new(r8, r0)
        L_0x01a0:
            com.threatmetrix.TrustDefender.internal.EF r8 = r7.f603for
            java.lang.String r8 = r8.f154for
            return r8
        L_0x01a5:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.WY.m373if(java.lang.String):java.lang.String");
    }
}
