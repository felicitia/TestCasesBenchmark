package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import android.webkit.DownloadListener;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.android.gms.ads.internal.ai;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@bu
@VisibleForTesting
final class nx extends WebView implements OnGlobalLayoutListener, DownloadListener, nn {
    private int A;
    /* access modifiers changed from: private */
    public int B;
    private akw C;
    private akw D;
    private akw E;
    private akx F;
    private WeakReference<OnClickListener> G;
    private zzd H;
    private boolean I;
    private jo J;
    private int K = -1;
    private int L = -1;
    private int M = -1;
    private int N = -1;
    private Map<String, mz> O;
    private final WindowManager P;
    private final ahh Q;
    private final zzash a;
    @Nullable
    private final ack b;
    private final zzang c;
    private final ai d;
    private final bg e;
    private final DisplayMetrics f;
    private final float g;
    private boolean h = false;
    private boolean i = false;
    private zzaqx j;
    private zzd k;
    private ot l;
    private String m;
    private boolean n;
    private boolean o;
    private boolean p;
    private boolean q;
    private Boolean r;
    private int s;
    private boolean t = true;
    private boolean u = false;
    private String v = "";
    private zzarl w;
    private boolean x;
    private boolean y;
    private ali z;

    @VisibleForTesting
    private nx(zzash zzash, ot otVar, String str, boolean z2, boolean z3, @Nullable ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) {
        super(zzash);
        this.a = zzash;
        this.l = otVar;
        this.m = str;
        this.p = z2;
        this.s = -1;
        this.b = ack;
        this.c = zzang;
        this.d = aiVar;
        this.e = bgVar;
        this.P = (WindowManager) getContext().getSystemService("window");
        ao.e();
        this.f = hd.a(this.P);
        this.g = this.f.density;
        this.Q = ahh;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        try {
            settings.setJavaScriptEnabled(true);
        } catch (NullPointerException e2) {
            gv.b("Unable to enable Javascript.", e2);
        }
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        ao.e().a((Context) zzash, zzang.zzcw, settings);
        ao.g().a(getContext(), settings);
        setDownloadListener(this);
        e();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(oc.a(this), "googleAdsJsInterface");
        }
        if (PlatformVersion.isAtLeastHoneycomb()) {
            removeJavascriptInterface("accessibility");
            removeJavascriptInterface("accessibilityTraversal");
        }
        this.J = new jo(this.a.zzto(), this, this, null);
        j();
        this.F = new akx(new aky(true, "make_wv", this.m));
        this.F.a().a(aky);
        this.D = akr.a(this.F.a());
        this.F.a("native:view_create", this.D);
        this.E = null;
        this.C = null;
        ao.g().b((Context) zzash);
        ao.i().i();
    }

    static nx a(Context context, ot otVar, String str, boolean z2, boolean z3, @Nullable ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) {
        nx nxVar = new nx(new zzash(context), otVar, str, z2, z3, ack, zzang, aky, aiVar, bgVar, ahh);
        return nxVar;
    }

    @VisibleForTesting
    private final void a(Boolean bool) {
        synchronized (this) {
            this.r = bool;
        }
        ao.i().a(bool);
    }

    private final synchronized void a(String str) {
        if (!isDestroyed()) {
            loadUrl(str);
        } else {
            gv.e("#004 The webview is destroyed. Ignoring action.");
        }
    }

    @TargetApi(19)
    private final synchronized void a(String str, ValueCallback<String> valueCallback) {
        if (!isDestroyed()) {
            evaluateJavascript(str, null);
        } else {
            gv.e("#004 The webview is destroyed. Ignoring action.");
        }
    }

    private final void a(boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z2 ? "1" : "0");
        zza("onAdVisibilityChanged", (Map<String, ?>) hashMap);
    }

    private final boolean a() {
        int i2;
        int i3;
        boolean z2 = false;
        if (!this.j.zzfz() && !this.j.zzuu()) {
            return false;
        }
        ajh.a();
        int b2 = jp.b(this.f, this.f.widthPixels);
        ajh.a();
        int b3 = jp.b(this.f, this.f.heightPixels);
        Activity zzto = this.a.zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i3 = b2;
            i2 = b3;
        } else {
            ao.e();
            int[] a2 = hd.a(zzto);
            ajh.a();
            int b4 = jp.b(this.f, a2[0]);
            ajh.a();
            i2 = jp.b(this.f, a2[1]);
            i3 = b4;
        }
        if (this.L == b2 && this.K == b3 && this.M == i3 && this.N == i2) {
            return false;
        }
        if (!(this.L == b2 && this.K == b3)) {
            z2 = true;
        }
        this.L = b2;
        this.K = b3;
        this.M = i3;
        this.N = i2;
        new m(this).a(b2, b3, i3, i2, this.f.density, this.P.getDefaultDisplay().getRotation());
        return z2;
    }

    private final synchronized void b() {
        this.r = ao.i().c();
        if (this.r == null) {
            try {
                evaluateJavascript("(function(){})()", null);
                a(Boolean.valueOf(true));
            } catch (IllegalStateException unused) {
                a(Boolean.valueOf(false));
            }
        }
    }

    private final synchronized void b(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError | UnsatisfiedLinkError e2) {
            ao.i().a(e2, "AdWebViewImpl.loadUrlUnsafe");
            gv.c("Could not call loadUrl. ", e2);
        }
    }

    @VisibleForTesting
    private final synchronized Boolean c() {
        return this.r;
    }

    private final void c(String str) {
        if (PlatformVersion.isAtLeastKitKat()) {
            if (c() == null) {
                b();
            }
            if (c().booleanValue()) {
                a(str, null);
                return;
            }
            String str2 = "javascript:";
            String valueOf = String.valueOf(str);
            a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return;
        }
        String str3 = "javascript:";
        String valueOf2 = String.valueOf(str);
        a(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
    }

    private final void d() {
        akr.a(this.F.a(), this.D, "aeh2");
    }

    private final synchronized void e() {
        if (!this.p) {
            if (!this.l.d()) {
                if (VERSION.SDK_INT < 18) {
                    gv.b("Disabling hardware acceleration on an AdView.");
                    f();
                    return;
                }
                gv.b("Enabling hardware acceleration on an AdView.");
                g();
                return;
            }
        }
        gv.b("Enabling hardware acceleration on an overlay.");
        g();
    }

    private final synchronized void f() {
        if (!this.q) {
            ao.g().c((View) this);
        }
        this.q = true;
    }

    private final synchronized void g() {
        if (this.q) {
            ao.g().b((View) this);
        }
        this.q = false;
    }

    private final synchronized void h() {
        if (!this.I) {
            this.I = true;
            ao.i().j();
        }
    }

    private final synchronized void i() {
        this.O = null;
    }

    private final void j() {
        if (this.F != null) {
            aky a2 = this.F.a();
            if (!(a2 == null || ao.i().b() == null)) {
                ao.i().b().a(a2);
            }
        }
    }

    public final synchronized void destroy() {
        j();
        this.J.b();
        if (this.k != null) {
            this.k.close();
            this.k.onDestroy();
            this.k = null;
        }
        this.j.reset();
        if (!this.o) {
            ao.z();
            my.a((mo) this);
            i();
            this.o = true;
            gv.a("Initiating WebView self destruct sequence in 3...");
            gv.a("Loading blank page in WebView, 2...");
            b("about:blank");
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0013, code lost:
        return;
     */
    @android.annotation.TargetApi(19)
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void evaluateJavascript(java.lang.String r2, android.webkit.ValueCallback<java.lang.String> r3) {
        /*
            r1 = this;
            monitor-enter(r1)
            boolean r0 = r1.isDestroyed()     // Catch:{ all -> 0x0019 }
            if (r0 == 0) goto L_0x0014
            java.lang.String r2 = "#004 The webview is destroyed. Ignoring action."
            com.google.android.gms.internal.ads.gv.f(r2)     // Catch:{ all -> 0x0019 }
            if (r3 == 0) goto L_0x0012
            r2 = 0
            r3.onReceiveValue(r2)     // Catch:{ all -> 0x0019 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            super.evaluateJavascript(r2, r3)     // Catch:{ all -> 0x0019 }
            monitor-exit(r1)
            return
        L_0x0019:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.nx.evaluateJavascript(java.lang.String, android.webkit.ValueCallback):void");
    }

    /* access modifiers changed from: protected */
    public final void finalize() throws Throwable {
        try {
            synchronized (this) {
                if (!this.o) {
                    this.j.reset();
                    ao.z();
                    my.a((mo) this);
                    i();
                    h();
                }
            }
            super.finalize();
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
    }

    public final OnClickListener getOnClickListener() {
        return (OnClickListener) this.G.get();
    }

    public final synchronized int getRequestedOrientation() {
        return this.s;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    public final synchronized boolean isDestroyed() {
        return this.o;
    }

    public final synchronized void loadData(String str, String str2, String str3) {
        if (!isDestroyed()) {
            super.loadData(str, str2, str3);
        } else {
            gv.e("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadDataWithBaseURL(String str, String str2, String str3, String str4, String str5) {
        if (!isDestroyed()) {
            super.loadDataWithBaseURL(str, str2, str3, str4, str5);
        } else {
            gv.e("#004 The webview is destroyed. Ignoring action.");
        }
    }

    public final synchronized void loadUrl(String str) {
        if (!isDestroyed()) {
            try {
                super.loadUrl(str);
            } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e2) {
                ao.i().a(e2, "AdWebViewImpl.loadUrl");
                gv.c("Could not call loadUrl. ", e2);
            }
        } else {
            gv.e("#004 The webview is destroyed. Ignoring action.");
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.J.c();
        }
        boolean z2 = this.x;
        if (this.j != null && this.j.zzuu()) {
            if (!this.y) {
                OnGlobalLayoutListener zzuv = this.j.zzuv();
                if (zzuv != null) {
                    ao.A();
                    if (this == null) {
                        throw null;
                    }
                    lm.a((View) this, zzuv);
                }
                OnScrollChangedListener zzuw = this.j.zzuw();
                if (zzuw != null) {
                    ao.A();
                    if (this == null) {
                        throw null;
                    }
                    lm.a((View) this, zzuw);
                }
                this.y = true;
            }
            a();
            z2 = true;
        }
        a(z2);
    }

    /* access modifiers changed from: protected */
    public final void onDetachedFromWindow() {
        synchronized (this) {
            if (!isDestroyed()) {
                this.J.d();
            }
            super.onDetachedFromWindow();
            if (this.y && this.j != null && this.j.zzuu() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                OnGlobalLayoutListener zzuv = this.j.zzuv();
                if (zzuv != null) {
                    ao.g().a(getViewTreeObserver(), zzuv);
                }
                OnScrollChangedListener zzuw = this.j.zzuw();
                if (zzuw != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(zzuw);
                }
                this.y = false;
            }
        }
        a(false);
    }

    public final void onDownloadStart(String str, String str2, String str3, String str4, long j2) {
        try {
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.setDataAndType(Uri.parse(str), str4);
            ao.e();
            hd.a(getContext(), intent);
        } catch (ActivityNotFoundException unused) {
            StringBuilder sb = new StringBuilder(51 + String.valueOf(str).length() + String.valueOf(str4).length());
            sb.append("Couldn't find an Activity to view url/mimetype: ");
            sb.append(str);
            sb.append(" / ");
            sb.append(str4);
            gv.b(sb.toString());
        }
    }

    /* access modifiers changed from: protected */
    @TargetApi(21)
    public final void onDraw(Canvas canvas) {
        if (!isDestroyed()) {
            if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
                super.onDraw(canvas);
                if (!(this.j == null || this.j.zzve() == null)) {
                    this.j.zzve().a();
                }
            }
        }
    }

    public final boolean onGenericMotionEvent(MotionEvent motionEvent) {
        if (((Boolean) ajh.f().a(akl.ay)).booleanValue()) {
            float axisValue = motionEvent.getAxisValue(9);
            float axisValue2 = motionEvent.getAxisValue(10);
            if (motionEvent.getActionMasked() == 8 && ((axisValue > 0.0f && !canScrollVertically(-1)) || ((axisValue < 0.0f && !canScrollVertically(1)) || ((axisValue2 > 0.0f && !canScrollHorizontally(-1)) || (axisValue2 < 0.0f && !canScrollHorizontally(1)))))) {
                return false;
            }
        }
        return super.onGenericMotionEvent(motionEvent);
    }

    public final void onGlobalLayout() {
        boolean a2 = a();
        zzd zzub = zzub();
        if (zzub != null && a2) {
            zzub.zznn();
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x01d0, code lost:
        return;
     */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01ae A[SYNTHETIC, Splitter:B:100:0x01ae] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x00fc  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0118  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0143  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:110:0x01d1=Splitter:B:110:0x01d1, B:52:0x00b7=Splitter:B:52:0x00b7} */
    @android.annotation.SuppressLint({"DrawAllocation"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01d6 }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01d6 }
            if (r0 != 0) goto L_0x01d1
            boolean r0 = r7.p     // Catch:{ all -> 0x01d6 }
            if (r0 != 0) goto L_0x01d1
            com.google.android.gms.internal.ads.ot r0 = r7.l     // Catch:{ all -> 0x01d6 }
            boolean r0 = r0.e()     // Catch:{ all -> 0x01d6 }
            if (r0 == 0) goto L_0x0021
            goto L_0x01d1
        L_0x0021:
            com.google.android.gms.internal.ads.ot r0 = r7.l     // Catch:{ all -> 0x01d6 }
            boolean r0 = r0.f()     // Catch:{ all -> 0x01d6 }
            if (r0 == 0) goto L_0x006b
            com.google.android.gms.internal.ads.zzarl r0 = r7.zztm()     // Catch:{ all -> 0x01d6 }
            r1 = 0
            if (r0 == 0) goto L_0x0035
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01d6 }
            goto L_0x0036
        L_0x0035:
            r0 = r1
        L_0x0036:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x003f
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x003f:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01d6 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01d6 }
            float r1 = (float) r9     // Catch:{ all -> 0x01d6 }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01d6 }
            float r2 = (float) r8     // Catch:{ all -> 0x01d6 }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01d6 }
            if (r9 != 0) goto L_0x0056
            if (r2 == 0) goto L_0x0056
            float r9 = (float) r2     // Catch:{ all -> 0x01d6 }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01d6 }
            r9 = r2
            goto L_0x005e
        L_0x0056:
            if (r8 != 0) goto L_0x005e
            if (r1 == 0) goto L_0x005e
            float r8 = (float) r1     // Catch:{ all -> 0x01d6 }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01d6 }
            r8 = r1
        L_0x005e:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01d6 }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01d6 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x006b:
            com.google.android.gms.internal.ads.ot r0 = r7.l     // Catch:{ all -> 0x01d6 }
            boolean r0 = r0.c()     // Catch:{ all -> 0x01d6 }
            if (r0 == 0) goto L_0x00bc
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.akl.cm     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.akj r1 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x01d6 }
            java.lang.Object r0 = r1.a(r0)     // Catch:{ all -> 0x01d6 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01d6 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01d6 }
            if (r0 != 0) goto L_0x00b7
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR1()     // Catch:{ all -> 0x01d6 }
            if (r0 != 0) goto L_0x008c
            goto L_0x00b7
        L_0x008c:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.ads.ny r1 = new com.google.android.gms.internal.ads.ny     // Catch:{ all -> 0x01d6 }
            r1.<init>(r7)     // Catch:{ all -> 0x01d6 }
            r7.zza(r0, r1)     // Catch:{ all -> 0x01d6 }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.c(r0)     // Catch:{ all -> 0x01d6 }
            android.util.DisplayMetrics r0 = r7.f     // Catch:{ all -> 0x01d6 }
            float r0 = r0.density     // Catch:{ all -> 0x01d6 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01d6 }
            int r1 = r7.B     // Catch:{ all -> 0x01d6 }
            r2 = -1
            if (r1 == r2) goto L_0x00ae
            int r9 = r7.B     // Catch:{ all -> 0x01d6 }
            float r9 = (float) r9     // Catch:{ all -> 0x01d6 }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01d6 }
            goto L_0x00b2
        L_0x00ae:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01d6 }
        L_0x00b2:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x00b7:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x00bc:
            com.google.android.gms.internal.ads.ot r0 = r7.l     // Catch:{ all -> 0x01d6 }
            boolean r0 = r0.d()     // Catch:{ all -> 0x01d6 }
            if (r0 == 0) goto L_0x00d1
            android.util.DisplayMetrics r8 = r7.f     // Catch:{ all -> 0x01d6 }
            int r8 = r8.widthPixels     // Catch:{ all -> 0x01d6 }
            android.util.DisplayMetrics r9 = r7.f     // Catch:{ all -> 0x01d6 }
            int r9 = r9.heightPixels     // Catch:{ all -> 0x01d6 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x00d1:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01d6 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01d6 }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01d6 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01d6 }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x00ef
            if (r0 != r3) goto L_0x00ed
            goto L_0x00ef
        L_0x00ed:
            r0 = r5
            goto L_0x00f0
        L_0x00ef:
            r0 = r8
        L_0x00f0:
            if (r2 == r4) goto L_0x00f4
            if (r2 != r3) goto L_0x00f5
        L_0x00f4:
            r5 = r9
        L_0x00f5:
            com.google.android.gms.internal.ads.ot r2 = r7.l     // Catch:{ all -> 0x01d6 }
            int r2 = r2.b     // Catch:{ all -> 0x01d6 }
            r3 = 1
            if (r2 > r0) goto L_0x0105
            com.google.android.gms.internal.ads.ot r2 = r7.l     // Catch:{ all -> 0x01d6 }
            int r2 = r2.a     // Catch:{ all -> 0x01d6 }
            if (r2 <= r5) goto L_0x0103
            goto L_0x0105
        L_0x0103:
            r2 = r1
            goto L_0x0106
        L_0x0105:
            r2 = r3
        L_0x0106:
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.akl.dh     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.akj r6 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x01d6 }
            java.lang.Object r4 = r6.a(r4)     // Catch:{ all -> 0x01d6 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01d6 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01d6 }
            if (r4 == 0) goto L_0x013e
            com.google.android.gms.internal.ads.ot r4 = r7.l     // Catch:{ all -> 0x01d6 }
            int r4 = r4.b     // Catch:{ all -> 0x01d6 }
            float r4 = (float) r4     // Catch:{ all -> 0x01d6 }
            float r6 = r7.g     // Catch:{ all -> 0x01d6 }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01d6 }
            float r6 = r7.g     // Catch:{ all -> 0x01d6 }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x013a
            com.google.android.gms.internal.ads.ot r0 = r7.l     // Catch:{ all -> 0x01d6 }
            int r0 = r0.a     // Catch:{ all -> 0x01d6 }
            float r0 = (float) r0     // Catch:{ all -> 0x01d6 }
            float r4 = r7.g     // Catch:{ all -> 0x01d6 }
            float r0 = r0 / r4
            float r4 = (float) r5     // Catch:{ all -> 0x01d6 }
            float r5 = r7.g     // Catch:{ all -> 0x01d6 }
            float r4 = r4 / r5
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x013a
            r0 = r3
            goto L_0x013b
        L_0x013a:
            r0 = r1
        L_0x013b:
            if (r2 == 0) goto L_0x013e
            goto L_0x013f
        L_0x013e:
            r0 = r2
        L_0x013f:
            r2 = 8
            if (r0 == 0) goto L_0x01ae
            com.google.android.gms.internal.ads.ot r0 = r7.l     // Catch:{ all -> 0x01d6 }
            int r0 = r0.b     // Catch:{ all -> 0x01d6 }
            float r0 = (float) r0     // Catch:{ all -> 0x01d6 }
            float r4 = r7.g     // Catch:{ all -> 0x01d6 }
            float r0 = r0 / r4
            int r0 = (int) r0     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.ot r4 = r7.l     // Catch:{ all -> 0x01d6 }
            int r4 = r4.a     // Catch:{ all -> 0x01d6 }
            float r4 = (float) r4     // Catch:{ all -> 0x01d6 }
            float r5 = r7.g     // Catch:{ all -> 0x01d6 }
            float r4 = r4 / r5
            int r4 = (int) r4     // Catch:{ all -> 0x01d6 }
            float r8 = (float) r8     // Catch:{ all -> 0x01d6 }
            float r5 = r7.g     // Catch:{ all -> 0x01d6 }
            float r8 = r8 / r5
            int r8 = (int) r8     // Catch:{ all -> 0x01d6 }
            float r9 = (float) r9     // Catch:{ all -> 0x01d6 }
            float r5 = r7.g     // Catch:{ all -> 0x01d6 }
            float r9 = r9 / r5
            int r9 = (int) r9     // Catch:{ all -> 0x01d6 }
            r5 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x01d6 }
            r6.<init>(r5)     // Catch:{ all -> 0x01d6 }
            java.lang.String r5 = "Not enough space to show ad. Needs "
            r6.append(r5)     // Catch:{ all -> 0x01d6 }
            r6.append(r0)     // Catch:{ all -> 0x01d6 }
            java.lang.String r0 = "x"
            r6.append(r0)     // Catch:{ all -> 0x01d6 }
            r6.append(r4)     // Catch:{ all -> 0x01d6 }
            java.lang.String r0 = " dp, but only has "
            r6.append(r0)     // Catch:{ all -> 0x01d6 }
            r6.append(r8)     // Catch:{ all -> 0x01d6 }
            java.lang.String r8 = "x"
            r6.append(r8)     // Catch:{ all -> 0x01d6 }
            r6.append(r9)     // Catch:{ all -> 0x01d6 }
            java.lang.String r8 = " dp."
            r6.append(r8)     // Catch:{ all -> 0x01d6 }
            java.lang.String r8 = r6.toString()     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.gv.e(r8)     // Catch:{ all -> 0x01d6 }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01d6 }
            if (r8 == r2) goto L_0x019c
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01d6 }
        L_0x019c:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01d6 }
            boolean r8 = r7.h     // Catch:{ all -> 0x01d6 }
            if (r8 != 0) goto L_0x01cf
            com.google.android.gms.internal.ads.ahh r8 = r7.Q     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.zzhu$zza$zzb r9 = com.google.android.gms.internal.ads.zzhu.zza.zzb.BANNER_SIZE_INVALID     // Catch:{ all -> 0x01d6 }
            r8.a(r9)     // Catch:{ all -> 0x01d6 }
            r7.h = r3     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x01ae:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01d6 }
            if (r8 == r2) goto L_0x01b7
            r7.setVisibility(r1)     // Catch:{ all -> 0x01d6 }
        L_0x01b7:
            boolean r8 = r7.i     // Catch:{ all -> 0x01d6 }
            if (r8 != 0) goto L_0x01c4
            com.google.android.gms.internal.ads.ahh r8 = r7.Q     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.zzhu$zza$zzb r9 = com.google.android.gms.internal.ads.zzhu.zza.zzb.BANNER_SIZE_VALID     // Catch:{ all -> 0x01d6 }
            r8.a(r9)     // Catch:{ all -> 0x01d6 }
            r7.i = r3     // Catch:{ all -> 0x01d6 }
        L_0x01c4:
            com.google.android.gms.internal.ads.ot r8 = r7.l     // Catch:{ all -> 0x01d6 }
            int r8 = r8.b     // Catch:{ all -> 0x01d6 }
            com.google.android.gms.internal.ads.ot r9 = r7.l     // Catch:{ all -> 0x01d6 }
            int r9 = r9.a     // Catch:{ all -> 0x01d6 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01d6 }
        L_0x01cf:
            monitor-exit(r7)
            return
        L_0x01d1:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01d6 }
            monitor-exit(r7)
            return
        L_0x01d6:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.nx.onMeasure(int, int):void");
    }

    public final void onPause() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onPause();
                }
            } catch (Exception e2) {
                gv.b("Could not pause webview.", e2);
            }
        }
    }

    public final void onResume() {
        if (!isDestroyed()) {
            try {
                if (PlatformVersion.isAtLeastHoneycomb()) {
                    super.onResume();
                }
            } catch (Exception e2) {
                gv.b("Could not resume webview.", e2);
            }
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.j.zzuu()) {
            synchronized (this) {
                if (this.z != null) {
                    this.z.a(motionEvent);
                }
            }
        } else if (this.b != null) {
            this.b.a(motionEvent);
        }
        if (isDestroyed()) {
            return false;
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.G = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void setRequestedOrientation(int i2) {
        this.s = i2;
        if (this.k != null) {
            this.k.setRequestedOrientation(this.s);
        }
    }

    public final void setWebViewClient(WebViewClient webViewClient) {
        super.setWebViewClient(webViewClient);
        if (webViewClient instanceof zzaqx) {
            this.j = (zzaqx) webViewClient;
        }
    }

    public final void stopLoading() {
        if (!isDestroyed()) {
            try {
                super.stopLoading();
            } catch (Exception e2) {
                gv.b("Could not stop loading webview.", e2);
            }
        }
    }

    public final void zza(zzc zzc) {
        this.j.zza(zzc);
    }

    public final synchronized void zza(zzd zzd) {
        this.k = zzd;
    }

    public final void zza(afm afm) {
        synchronized (this) {
            this.x = afm.a;
        }
        a(afm.a);
    }

    public final synchronized void zza(ot otVar) {
        this.l = otVar;
        requestLayout();
    }

    public final synchronized void zza(zzarl zzarl) {
        if (this.w != null) {
            gv.c("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.w = zzarl;
        }
    }

    public final void zza(String str, ae<? super nn> aeVar) {
        if (this.j != null) {
            this.j.zza(str, aeVar);
        }
    }

    public final void zza(String str, Predicate<ae<? super nn>> predicate) {
        if (this.j != null) {
            this.j.zza(str, predicate);
        }
    }

    public final void zza(String str, Map<String, ?> map) {
        try {
            zza(str, ao.e().a(map));
        } catch (JSONException unused) {
            gv.e("Could not convert parameters to JSON.");
        }
    }

    public final void zza(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder();
        sb.append("(window.AFMA_ReceiveMessage || function() {})('");
        sb.append(str);
        sb.append("'");
        sb.append(",");
        sb.append(jSONObject2);
        sb.append(");");
        String str2 = "Dispatching AFMA event: ";
        String valueOf = String.valueOf(sb.toString());
        gv.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        c(sb.toString());
    }

    public final void zza(boolean z2, int i2) {
        this.j.zza(z2, i2);
    }

    public final void zza(boolean z2, int i2, String str) {
        this.j.zza(z2, i2, str);
    }

    public final void zza(boolean z2, int i2, String str, String str2) {
        this.j.zza(z2, i2, str, str2);
    }

    public final void zzah(boolean z2) {
        this.j.zzah(z2);
    }

    public final void zzai(int i2) {
        if (i2 == 0) {
            akr.a(this.F.a(), this.D, "aebb2");
        }
        d();
        if (this.F.a() != null) {
            this.F.a().a("close_type", String.valueOf(i2));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i2));
        hashMap.put(ResponseConstants.VERSION, this.c.zzcw);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final synchronized void zzai(boolean z2) {
        boolean z3 = z2 != this.p;
        this.p = z2;
        e();
        if (z3) {
            new m(this).c(z2 ? "expanded" : "default");
        }
    }

    public final synchronized void zzaj(boolean z2) {
        this.t = z2;
    }

    public final synchronized void zzak(boolean z2) {
        this.A += z2 ? 1 : -1;
        if (this.A <= 0 && this.k != null) {
            this.k.zznq();
        }
    }

    public final synchronized void zzb(zzd zzd) {
        this.H = zzd;
    }

    public final synchronized void zzb(ali ali) {
        this.z = ali;
    }

    public final void zzb(String str, ae<? super nn> aeVar) {
        if (this.j != null) {
            this.j.zzb(str, aeVar);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder sb = new StringBuilder(3 + String.valueOf(str).length() + String.valueOf(jSONObject2).length());
        sb.append(str);
        sb.append("(");
        sb.append(jSONObject2);
        sb.append(");");
        c(sb.toString());
    }

    public final void zzbe(String str) {
        c(str);
    }

    public final bg zzbi() {
        return this.e;
    }

    public final void zzbm(Context context) {
        this.a.setBaseContext(context);
        this.J.a(this.a.zzto());
    }

    public final synchronized void zzc(String str, String str2, @Nullable String str3) {
        if (!isDestroyed()) {
            if (((Boolean) ajh.f().a(akl.aB)).booleanValue()) {
                str2 = oi.a(str2, oi.a());
            }
            super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
            return;
        }
        gv.e("#004 The webview is destroyed. Ignoring action.");
    }

    public final synchronized void zzcl() {
        this.u = true;
        if (this.d != null) {
            this.d.zzcl();
        }
    }

    public final synchronized void zzcm() {
        this.u = false;
        if (this.d != null) {
            this.d.zzcm();
        }
    }

    public final synchronized void zzdr(String str) {
        if (str == null) {
            str = "";
        }
        this.v = str;
    }

    public final void zzno() {
        if (this.C == null) {
            akr.a(this.F.a(), this.D, "aes2");
            this.C = akr.a(this.F.a());
            this.F.a("native:view_show", this.C);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put(ResponseConstants.VERSION, this.c.zzcw);
        zza("onshow", (Map<String, ?>) hashMap);
    }

    public final void zznp() {
        zzd zzub = zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }

    public final synchronized String zzol() {
        return this.v;
    }

    public final mg zztl() {
        return null;
    }

    public final synchronized zzarl zztm() {
        return this.w;
    }

    public final akw zztn() {
        return this.D;
    }

    public final Activity zzto() {
        return this.a.zzto();
    }

    public final akx zztp() {
        return this.F;
    }

    public final zzang zztq() {
        return this.c;
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        d();
        HashMap hashMap = new HashMap(1);
        hashMap.put(ResponseConstants.VERSION, this.c.zzcw);
        zza("onhide", (Map<String, ?>) hashMap);
    }

    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(ao.D().b()));
        hashMap.put("app_volume", String.valueOf(ao.D().a()));
        hashMap.put("device_volume", String.valueOf(hv.a(getContext())));
        zza("volume", (Map<String, ?>) hashMap);
    }

    public final synchronized void zzu(boolean z2) {
        if (this.k != null) {
            this.k.zza(this.j.zzfz(), z2);
        } else {
            this.n = z2;
        }
    }

    public final Context zzua() {
        return this.a.zzua();
    }

    public final synchronized zzd zzub() {
        return this.k;
    }

    public final synchronized zzd zzuc() {
        return this.H;
    }

    public final synchronized ot zzud() {
        return this.l;
    }

    public final synchronized String zzue() {
        return this.m;
    }

    public final /* synthetic */ oo zzuf() {
        return this.j;
    }

    public final WebViewClient zzug() {
        return this.j;
    }

    public final synchronized boolean zzuh() {
        return this.n;
    }

    public final ack zzui() {
        return this.b;
    }

    public final synchronized boolean zzuj() {
        return this.p;
    }

    public final synchronized void zzuk() {
        gv.a("Destroying WebView!");
        h();
        hd.a.post(new nz(this));
    }

    public final synchronized boolean zzul() {
        return this.t;
    }

    public final synchronized boolean zzum() {
        return this.u;
    }

    public final synchronized boolean zzun() {
        return this.A > 0;
    }

    public final void zzuo() {
        this.J.a();
    }

    public final void zzup() {
        if (this.E == null) {
            this.E = akr.a(this.F.a());
            this.F.a("native:view_load", this.E);
        }
    }

    public final synchronized ali zzuq() {
        return this.z;
    }

    public final void zzur() {
        setBackgroundColor(0);
    }

    public final void zzus() {
        gv.a("Cannot add text view to inner AdWebView");
    }
}
