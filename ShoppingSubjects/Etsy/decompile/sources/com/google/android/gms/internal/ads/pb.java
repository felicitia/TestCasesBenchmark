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
import org.json.JSONObject;

@bu
@VisibleForTesting
final class pb extends zzasv implements OnGlobalLayoutListener, DownloadListener, aoo, nn {
    private zzd A;
    private jo B;
    private int C = -1;
    private int D = -1;
    private int E = -1;
    private int F = -1;
    private float G;
    private Map<String, mz> H;
    private final WindowManager I;
    @Nullable
    private final ack a;
    private final zzang b;
    private final ai c;
    private final bg d;
    private ou e;
    private zzd f;
    private ot g;
    private String h;
    private boolean i;
    private boolean j;
    private boolean k;
    private int l;
    private boolean m = true;
    private boolean n = false;
    private String o = "";
    private zzarl p;
    private boolean q;
    private boolean r;
    private ali s;
    private int t;
    /* access modifiers changed from: private */
    public int u;
    private akw v;
    private akw w;
    private akw x;
    private akx y;
    private WeakReference<OnClickListener> z;

    @VisibleForTesting
    private pb(zzash zzash, ot otVar, String str, boolean z2, boolean z3, @Nullable ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) {
        super(zzash);
        this.g = otVar;
        this.h = str;
        this.j = z2;
        this.l = -1;
        this.a = ack;
        this.b = zzang;
        this.c = aiVar;
        this.d = bgVar;
        this.I = (WindowManager) getContext().getSystemService("window");
        this.B = new jo(zzvv().zzto(), this, this, null);
        ao.e().a((Context) zzash, zzang.zzcw, getSettings());
        setDownloadListener(this);
        this.G = zzvv().getResources().getDisplayMetrics().density;
        c();
        if (PlatformVersion.isAtLeastJellyBeanMR1()) {
            addJavascriptInterface(oc.a(this), "googleAdsJsInterface");
        }
        g();
        this.y = new akx(new aky(true, "make_wv", this.h));
        this.y.a().a(aky);
        this.w = akr.a(this.y.a());
        this.y.a("native:view_create", this.w);
        this.x = null;
        this.v = null;
        ao.g().b((Context) zzash);
    }

    static pb a(Context context, ot otVar, String str, boolean z2, boolean z3, @Nullable ack ack, zzang zzang, aky aky, ai aiVar, bg bgVar, ahh ahh) {
        pb pbVar = new pb(new zzash(context), otVar, str, z2, z3, ack, zzang, aky, aiVar, bgVar, ahh);
        return pbVar;
    }

    private final void a(boolean z2) {
        HashMap hashMap = new HashMap();
        hashMap.put("isVisible", z2 ? "1" : "0");
        aop.a((aoo) this, "onAdVisibilityChanged", (Map) hashMap);
    }

    private final boolean a() {
        int i2;
        int i3;
        boolean z2 = false;
        if (!this.e.zzfz() && !this.e.a()) {
            return false;
        }
        ao.e();
        DisplayMetrics a2 = hd.a(this.I);
        ajh.a();
        int b2 = jp.b(a2, a2.widthPixels);
        ajh.a();
        int b3 = jp.b(a2, a2.heightPixels);
        Activity zzto = zzvv().zzto();
        if (zzto == null || zzto.getWindow() == null) {
            i3 = b2;
            i2 = b3;
        } else {
            ao.e();
            int[] a3 = hd.a(zzto);
            ajh.a();
            i3 = jp.b(a2, a3[0]);
            ajh.a();
            i2 = jp.b(a2, a3[1]);
        }
        if (this.D == b2 && this.C == b3 && this.E == i3 && this.F == i2) {
            return false;
        }
        if (!(this.D == b2 && this.C == b3)) {
            z2 = true;
        }
        this.D = b2;
        this.C = b3;
        this.E = i3;
        this.F = i2;
        new m(this).a(b2, b3, i3, i2, a2.density, this.I.getDefaultDisplay().getRotation());
        return z2;
    }

    private final void b() {
        akr.a(this.y.a(), this.w, "aeh2");
    }

    private final synchronized void c() {
        if (!this.j) {
            if (!this.g.d()) {
                if (VERSION.SDK_INT < 18) {
                    gv.b("Disabling hardware acceleration on an AdView.");
                    d();
                    return;
                }
                gv.b("Enabling hardware acceleration on an AdView.");
                e();
                return;
            }
        }
        gv.b("Enabling hardware acceleration on an overlay.");
        e();
    }

    private final synchronized void d() {
        if (!this.k) {
            ao.g().c((View) this);
        }
        this.k = true;
    }

    private final synchronized void e() {
        if (this.k) {
            ao.g().b((View) this);
        }
        this.k = false;
    }

    private final synchronized void f() {
        this.H = null;
    }

    private final void g() {
        if (this.y != null) {
            aky a2 = this.y.a();
            if (!(a2 == null || ao.i().b() == null)) {
                ao.i().b().a(a2);
            }
        }
    }

    public final void a(ou ouVar) {
        this.e = ouVar;
    }

    public final void a(String str, String str2) {
        aop.a((aoo) this, str, str2);
    }

    public final OnClickListener getOnClickListener() {
        return (OnClickListener) this.z.get();
    }

    public final synchronized int getRequestedOrientation() {
        return this.l;
    }

    public final View getView() {
        return this;
    }

    public final WebView getWebView() {
        return this;
    }

    /* access modifiers changed from: protected */
    public final synchronized void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isDestroyed()) {
            this.B.c();
        }
        boolean z2 = this.q;
        if (this.e != null && this.e.a()) {
            if (!this.r) {
                OnGlobalLayoutListener b2 = this.e.b();
                if (b2 != null) {
                    ao.A();
                    if (this == null) {
                        throw null;
                    }
                    lm.a((View) this, b2);
                }
                OnScrollChangedListener c2 = this.e.c();
                if (c2 != null) {
                    ao.A();
                    if (this == null) {
                        throw null;
                    }
                    lm.a((View) this, c2);
                }
                this.r = true;
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
                this.B.d();
            }
            super.onDetachedFromWindow();
            if (this.r && this.e != null && this.e.a() && getViewTreeObserver() != null && getViewTreeObserver().isAlive()) {
                OnGlobalLayoutListener b2 = this.e.b();
                if (b2 != null) {
                    ao.g().a(getViewTreeObserver(), b2);
                }
                OnScrollChangedListener c2 = this.e.c();
                if (c2 != null) {
                    getViewTreeObserver().removeOnScrollChangedListener(c2);
                }
                this.r = false;
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
        if (VERSION.SDK_INT != 21 || !canvas.isHardwareAccelerated() || isAttachedToWindow()) {
            super.onDraw(canvas);
            if (!(this.e == null || this.e.e() == null)) {
                this.e.e().a();
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
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0104  */
    /* JADX WARNING: Removed duplicated region for block: B:81:0x0120  */
    /* JADX WARNING: Removed duplicated region for block: B:91:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x01a7 A[SYNTHETIC, Splitter:B:97:0x01a7] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:52:0x00b5=Splitter:B:52:0x00b5, B:104:0x01bd=Splitter:B:104:0x01bd} */
    @android.annotation.SuppressLint({"DrawAllocation"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized void onMeasure(int r8, int r9) {
        /*
            r7 = this;
            monitor-enter(r7)
            boolean r0 = r7.isDestroyed()     // Catch:{ all -> 0x01c2 }
            r1 = 0
            if (r0 == 0) goto L_0x000d
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x000d:
            boolean r0 = r7.isInEditMode()     // Catch:{ all -> 0x01c2 }
            if (r0 != 0) goto L_0x01bd
            boolean r0 = r7.j     // Catch:{ all -> 0x01c2 }
            if (r0 != 0) goto L_0x01bd
            com.google.android.gms.internal.ads.ot r0 = r7.g     // Catch:{ all -> 0x01c2 }
            boolean r0 = r0.e()     // Catch:{ all -> 0x01c2 }
            if (r0 == 0) goto L_0x0021
            goto L_0x01bd
        L_0x0021:
            com.google.android.gms.internal.ads.ot r0 = r7.g     // Catch:{ all -> 0x01c2 }
            boolean r0 = r0.f()     // Catch:{ all -> 0x01c2 }
            if (r0 == 0) goto L_0x006b
            com.google.android.gms.internal.ads.zzarl r0 = r7.zztm()     // Catch:{ all -> 0x01c2 }
            r1 = 0
            if (r0 == 0) goto L_0x0035
            float r0 = r0.getAspectRatio()     // Catch:{ all -> 0x01c2 }
            goto L_0x0036
        L_0x0035:
            r0 = r1
        L_0x0036:
            int r1 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x003f
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x003f:
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01c2 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01c2 }
            float r1 = (float) r9     // Catch:{ all -> 0x01c2 }
            float r1 = r1 * r0
            int r1 = (int) r1     // Catch:{ all -> 0x01c2 }
            float r2 = (float) r8     // Catch:{ all -> 0x01c2 }
            float r2 = r2 / r0
            int r2 = (int) r2     // Catch:{ all -> 0x01c2 }
            if (r9 != 0) goto L_0x0056
            if (r2 == 0) goto L_0x0056
            float r9 = (float) r2     // Catch:{ all -> 0x01c2 }
            float r9 = r9 * r0
            int r1 = (int) r9     // Catch:{ all -> 0x01c2 }
            r9 = r2
            goto L_0x005e
        L_0x0056:
            if (r8 != 0) goto L_0x005e
            if (r1 == 0) goto L_0x005e
            float r8 = (float) r1     // Catch:{ all -> 0x01c2 }
            float r8 = r8 / r0
            int r2 = (int) r8     // Catch:{ all -> 0x01c2 }
            r8 = r1
        L_0x005e:
            int r8 = java.lang.Math.min(r1, r8)     // Catch:{ all -> 0x01c2 }
            int r9 = java.lang.Math.min(r2, r9)     // Catch:{ all -> 0x01c2 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x006b:
            com.google.android.gms.internal.ads.ot r0 = r7.g     // Catch:{ all -> 0x01c2 }
            boolean r0 = r0.c()     // Catch:{ all -> 0x01c2 }
            if (r0 == 0) goto L_0x00ba
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r0 = com.google.android.gms.internal.ads.akl.cm     // Catch:{ all -> 0x01c2 }
            com.google.android.gms.internal.ads.akj r1 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x01c2 }
            java.lang.Object r0 = r1.a(r0)     // Catch:{ all -> 0x01c2 }
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch:{ all -> 0x01c2 }
            boolean r0 = r0.booleanValue()     // Catch:{ all -> 0x01c2 }
            if (r0 != 0) goto L_0x00b5
            boolean r0 = com.google.android.gms.common.util.PlatformVersion.isAtLeastJellyBeanMR1()     // Catch:{ all -> 0x01c2 }
            if (r0 != 0) goto L_0x008c
            goto L_0x00b5
        L_0x008c:
            java.lang.String r0 = "/contentHeight"
            com.google.android.gms.internal.ads.pc r1 = new com.google.android.gms.internal.ads.pc     // Catch:{ all -> 0x01c2 }
            r1.<init>(r7)     // Catch:{ all -> 0x01c2 }
            r7.zza(r0, r1)     // Catch:{ all -> 0x01c2 }
            java.lang.String r0 = "(function() {  var height = -1;  if (document.body) {    height = document.body.offsetHeight;  } else if (document.documentElement) {    height = document.documentElement.offsetHeight;  }  var url = 'gmsg://mobileads.google.com/contentHeight?';  url += 'height=' + height;  try {    window.googleAdsJsInterface.notify(url);  } catch (e) {    var frame = document.getElementById('afma-notify-fluid');    if (!frame) {      frame = document.createElement('IFRAME');      frame.id = 'afma-notify-fluid';      frame.style.display = 'none';      var body = document.body || document.documentElement;      body.appendChild(frame);    }    frame.src = url;  }})();"
            r7.zzbe(r0)     // Catch:{ all -> 0x01c2 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01c2 }
            int r0 = r7.u     // Catch:{ all -> 0x01c2 }
            r1 = -1
            if (r0 == r1) goto L_0x00ac
            int r9 = r7.u     // Catch:{ all -> 0x01c2 }
            float r9 = (float) r9     // Catch:{ all -> 0x01c2 }
            float r0 = r7.G     // Catch:{ all -> 0x01c2 }
            float r9 = r9 * r0
            int r9 = (int) r9     // Catch:{ all -> 0x01c2 }
            goto L_0x00b0
        L_0x00ac:
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01c2 }
        L_0x00b0:
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x00b5:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x00ba:
            com.google.android.gms.internal.ads.ot r0 = r7.g     // Catch:{ all -> 0x01c2 }
            boolean r0 = r0.d()     // Catch:{ all -> 0x01c2 }
            if (r0 == 0) goto L_0x00d9
            android.util.DisplayMetrics r8 = new android.util.DisplayMetrics     // Catch:{ all -> 0x01c2 }
            r8.<init>()     // Catch:{ all -> 0x01c2 }
            android.view.WindowManager r9 = r7.I     // Catch:{ all -> 0x01c2 }
            android.view.Display r9 = r9.getDefaultDisplay()     // Catch:{ all -> 0x01c2 }
            r9.getMetrics(r8)     // Catch:{ all -> 0x01c2 }
            int r9 = r8.widthPixels     // Catch:{ all -> 0x01c2 }
            int r8 = r8.heightPixels     // Catch:{ all -> 0x01c2 }
            r7.setMeasuredDimension(r9, r8)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x00d9:
            int r0 = android.view.View.MeasureSpec.getMode(r8)     // Catch:{ all -> 0x01c2 }
            int r8 = android.view.View.MeasureSpec.getSize(r8)     // Catch:{ all -> 0x01c2 }
            int r2 = android.view.View.MeasureSpec.getMode(r9)     // Catch:{ all -> 0x01c2 }
            int r9 = android.view.View.MeasureSpec.getSize(r9)     // Catch:{ all -> 0x01c2 }
            r3 = 1073741824(0x40000000, float:2.0)
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r5 = 2147483647(0x7fffffff, float:NaN)
            if (r0 == r4) goto L_0x00f7
            if (r0 != r3) goto L_0x00f5
            goto L_0x00f7
        L_0x00f5:
            r0 = r5
            goto L_0x00f8
        L_0x00f7:
            r0 = r8
        L_0x00f8:
            if (r2 == r4) goto L_0x00fc
            if (r2 != r3) goto L_0x00fd
        L_0x00fc:
            r5 = r9
        L_0x00fd:
            com.google.android.gms.internal.ads.ot r2 = r7.g     // Catch:{ all -> 0x01c2 }
            int r2 = r2.b     // Catch:{ all -> 0x01c2 }
            r3 = 1
            if (r2 > r0) goto L_0x010d
            com.google.android.gms.internal.ads.ot r2 = r7.g     // Catch:{ all -> 0x01c2 }
            int r2 = r2.a     // Catch:{ all -> 0x01c2 }
            if (r2 <= r5) goto L_0x010b
            goto L_0x010d
        L_0x010b:
            r2 = r1
            goto L_0x010e
        L_0x010d:
            r2 = r3
        L_0x010e:
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r4 = com.google.android.gms.internal.ads.akl.dh     // Catch:{ all -> 0x01c2 }
            com.google.android.gms.internal.ads.akj r6 = com.google.android.gms.internal.ads.ajh.f()     // Catch:{ all -> 0x01c2 }
            java.lang.Object r4 = r6.a(r4)     // Catch:{ all -> 0x01c2 }
            java.lang.Boolean r4 = (java.lang.Boolean) r4     // Catch:{ all -> 0x01c2 }
            boolean r4 = r4.booleanValue()     // Catch:{ all -> 0x01c2 }
            if (r4 == 0) goto L_0x0145
            com.google.android.gms.internal.ads.ot r4 = r7.g     // Catch:{ all -> 0x01c2 }
            int r4 = r4.b     // Catch:{ all -> 0x01c2 }
            float r4 = (float) r4     // Catch:{ all -> 0x01c2 }
            float r6 = r7.G     // Catch:{ all -> 0x01c2 }
            float r4 = r4 / r6
            float r0 = (float) r0     // Catch:{ all -> 0x01c2 }
            float r6 = r7.G     // Catch:{ all -> 0x01c2 }
            float r0 = r0 / r6
            int r0 = (r4 > r0 ? 1 : (r4 == r0 ? 0 : -1))
            if (r0 > 0) goto L_0x0141
            com.google.android.gms.internal.ads.ot r0 = r7.g     // Catch:{ all -> 0x01c2 }
            int r0 = r0.a     // Catch:{ all -> 0x01c2 }
            float r0 = (float) r0     // Catch:{ all -> 0x01c2 }
            float r4 = r7.G     // Catch:{ all -> 0x01c2 }
            float r0 = r0 / r4
            float r4 = (float) r5     // Catch:{ all -> 0x01c2 }
            float r5 = r7.G     // Catch:{ all -> 0x01c2 }
            float r4 = r4 / r5
            int r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r0 > 0) goto L_0x0141
            goto L_0x0142
        L_0x0141:
            r3 = r1
        L_0x0142:
            if (r2 == 0) goto L_0x0145
            r2 = r3
        L_0x0145:
            r0 = 8
            if (r2 == 0) goto L_0x01a7
            com.google.android.gms.internal.ads.ot r2 = r7.g     // Catch:{ all -> 0x01c2 }
            int r2 = r2.b     // Catch:{ all -> 0x01c2 }
            float r2 = (float) r2     // Catch:{ all -> 0x01c2 }
            float r3 = r7.G     // Catch:{ all -> 0x01c2 }
            float r2 = r2 / r3
            int r2 = (int) r2     // Catch:{ all -> 0x01c2 }
            com.google.android.gms.internal.ads.ot r3 = r7.g     // Catch:{ all -> 0x01c2 }
            int r3 = r3.a     // Catch:{ all -> 0x01c2 }
            float r3 = (float) r3     // Catch:{ all -> 0x01c2 }
            float r4 = r7.G     // Catch:{ all -> 0x01c2 }
            float r3 = r3 / r4
            int r3 = (int) r3     // Catch:{ all -> 0x01c2 }
            float r8 = (float) r8     // Catch:{ all -> 0x01c2 }
            float r4 = r7.G     // Catch:{ all -> 0x01c2 }
            float r8 = r8 / r4
            int r8 = (int) r8     // Catch:{ all -> 0x01c2 }
            float r9 = (float) r9     // Catch:{ all -> 0x01c2 }
            float r4 = r7.G     // Catch:{ all -> 0x01c2 }
            float r9 = r9 / r4
            int r9 = (int) r9     // Catch:{ all -> 0x01c2 }
            r4 = 103(0x67, float:1.44E-43)
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x01c2 }
            r5.<init>(r4)     // Catch:{ all -> 0x01c2 }
            java.lang.String r4 = "Not enough space to show ad. Needs "
            r5.append(r4)     // Catch:{ all -> 0x01c2 }
            r5.append(r2)     // Catch:{ all -> 0x01c2 }
            java.lang.String r2 = "x"
            r5.append(r2)     // Catch:{ all -> 0x01c2 }
            r5.append(r3)     // Catch:{ all -> 0x01c2 }
            java.lang.String r2 = " dp, but only has "
            r5.append(r2)     // Catch:{ all -> 0x01c2 }
            r5.append(r8)     // Catch:{ all -> 0x01c2 }
            java.lang.String r8 = "x"
            r5.append(r8)     // Catch:{ all -> 0x01c2 }
            r5.append(r9)     // Catch:{ all -> 0x01c2 }
            java.lang.String r8 = " dp."
            r5.append(r8)     // Catch:{ all -> 0x01c2 }
            java.lang.String r8 = r5.toString()     // Catch:{ all -> 0x01c2 }
            com.google.android.gms.internal.ads.gv.e(r8)     // Catch:{ all -> 0x01c2 }
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01c2 }
            if (r8 == r0) goto L_0x01a2
            r8 = 4
            r7.setVisibility(r8)     // Catch:{ all -> 0x01c2 }
        L_0x01a2:
            r7.setMeasuredDimension(r1, r1)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x01a7:
            int r8 = r7.getVisibility()     // Catch:{ all -> 0x01c2 }
            if (r8 == r0) goto L_0x01b0
            r7.setVisibility(r1)     // Catch:{ all -> 0x01c2 }
        L_0x01b0:
            com.google.android.gms.internal.ads.ot r8 = r7.g     // Catch:{ all -> 0x01c2 }
            int r8 = r8.b     // Catch:{ all -> 0x01c2 }
            com.google.android.gms.internal.ads.ot r9 = r7.g     // Catch:{ all -> 0x01c2 }
            int r9 = r9.a     // Catch:{ all -> 0x01c2 }
            r7.setMeasuredDimension(r8, r9)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x01bd:
            super.onMeasure(r8, r9)     // Catch:{ all -> 0x01c2 }
            monitor-exit(r7)
            return
        L_0x01c2:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.pb.onMeasure(int, int):void");
    }

    public final void onPause() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onPause();
            }
        } catch (Exception e2) {
            gv.b("Could not pause webview.", e2);
        }
    }

    public final void onResume() {
        try {
            if (PlatformVersion.isAtLeastHoneycomb()) {
                super.onResume();
            }
        } catch (Exception e2) {
            gv.b("Could not resume webview.", e2);
        }
    }

    public final boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.e.a()) {
            synchronized (this) {
                if (this.s != null) {
                    this.s.a(motionEvent);
                }
            }
        } else if (this.a != null) {
            this.a.a(motionEvent);
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnClickListener(OnClickListener onClickListener) {
        this.z = new WeakReference<>(onClickListener);
        super.setOnClickListener(onClickListener);
    }

    public final synchronized void setRequestedOrientation(int i2) {
        this.l = i2;
        if (this.f != null) {
            this.f.setRequestedOrientation(this.l);
        }
    }

    public final void stopLoading() {
        try {
            super.stopLoading();
        } catch (Exception e2) {
            gv.b("Could not stop loading webview.", e2);
        }
    }

    public final void zza(zzc zzc) {
        this.e.a(zzc);
    }

    public final synchronized void zza(zzd zzd) {
        this.f = zzd;
    }

    public final void zza(afm afm) {
        synchronized (this) {
            this.q = afm.a;
        }
        a(afm.a);
    }

    public final synchronized void zza(ot otVar) {
        this.g = otVar;
        requestLayout();
    }

    public final synchronized void zza(zzarl zzarl) {
        if (this.p != null) {
            gv.c("Attempt to create multiple AdWebViewVideoControllers.");
        } else {
            this.p = zzarl;
        }
    }

    public final void zza(String str, ae<? super nn> aeVar) {
        if (this.e != null) {
            this.e.a(str, aeVar);
        }
    }

    public final void zza(String str, Predicate<ae<? super nn>> predicate) {
        if (this.e != null) {
            this.e.a(str, predicate);
        }
    }

    public final void zza(String str, Map map) {
        aop.a((aoo) this, str, map);
    }

    public final void zza(String str, JSONObject jSONObject) {
        aop.b(this, str, jSONObject);
    }

    public final void zza(boolean z2, int i2) {
        this.e.a(z2, i2);
    }

    public final void zza(boolean z2, int i2, String str) {
        this.e.a(z2, i2, str);
    }

    public final void zza(boolean z2, int i2, String str, String str2) {
        this.e.a(z2, i2, str, str2);
    }

    public final void zzah(boolean z2) {
        this.e.a(z2);
    }

    public final void zzai(int i2) {
        if (i2 == 0) {
            akr.a(this.y.a(), this.w, "aebb2");
        }
        b();
        if (this.y.a() != null) {
            this.y.a().a("close_type", String.valueOf(i2));
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("closetype", String.valueOf(i2));
        hashMap.put(ResponseConstants.VERSION, this.b.zzcw);
        aop.a((aoo) this, "onhide", (Map) hashMap);
    }

    public final synchronized void zzai(boolean z2) {
        boolean z3 = z2 != this.j;
        this.j = z2;
        c();
        if (z3) {
            new m(this).c(z2 ? "expanded" : "default");
        }
    }

    public final synchronized void zzaj(boolean z2) {
        this.m = z2;
    }

    public final synchronized void zzak(boolean z2) {
        this.t += z2 ? 1 : -1;
        if (this.t <= 0 && this.f != null) {
            this.f.zznq();
        }
    }

    /* access modifiers changed from: protected */
    public final synchronized void zzam(boolean z2) {
        if (!z2) {
            try {
                g();
                this.B.b();
                if (this.f != null) {
                    this.f.close();
                    this.f.onDestroy();
                    this.f = null;
                }
            } finally {
            }
        }
        this.e.d();
        ao.z();
        my.a((mo) this);
        f();
    }

    public final synchronized void zzb(zzd zzd) {
        this.A = zzd;
    }

    public final synchronized void zzb(ali ali) {
        this.s = ali;
    }

    public final void zzb(String str, ae<? super nn> aeVar) {
        if (this.e != null) {
            this.e.b(str, aeVar);
        }
    }

    public final void zzb(String str, JSONObject jSONObject) {
        aop.a((aoo) this, str, jSONObject);
    }

    public final synchronized void zzbe(String str) {
        if (!isDestroyed()) {
            super.zzbe(str);
        } else {
            gv.e("The webview is destroyed. Ignoring action.");
        }
    }

    public final bg zzbi() {
        return this.d;
    }

    public final void zzbm(Context context) {
        zzvv().setBaseContext(context);
        this.B.a(zzvv().zzto());
    }

    public final synchronized void zzc(String str, String str2, @Nullable String str3) {
        if (((Boolean) ajh.f().a(akl.aB)).booleanValue()) {
            str2 = oi.a(str2, oi.a());
        }
        super.loadDataWithBaseURL(str, str2, "text/html", "UTF-8", str3);
    }

    public final synchronized void zzcl() {
        this.n = true;
        if (this.c != null) {
            this.c.zzcl();
        }
    }

    public final synchronized void zzcm() {
        this.n = false;
        if (this.c != null) {
            this.c.zzcm();
        }
    }

    public final synchronized void zzdr(String str) {
        if (str == null) {
            str = "";
        }
        this.o = str;
    }

    public final void zzno() {
        if (this.v == null) {
            akr.a(this.y.a(), this.w, "aes2");
            this.v = akr.a(this.y.a());
            this.y.a("native:view_show", this.v);
        }
        HashMap hashMap = new HashMap(1);
        hashMap.put(ResponseConstants.VERSION, this.b.zzcw);
        aop.a((aoo) this, "onshow", (Map) hashMap);
    }

    public final void zznp() {
        zzd zzub = zzub();
        if (zzub != null) {
            zzub.zznp();
        }
    }

    public final synchronized String zzol() {
        return this.o;
    }

    public final mg zztl() {
        return null;
    }

    public final synchronized zzarl zztm() {
        return this.p;
    }

    public final akw zztn() {
        return this.w;
    }

    public final Activity zzto() {
        return zzvv().zzto();
    }

    public final akx zztp() {
        return this.y;
    }

    public final zzang zztq() {
        return this.b;
    }

    public final int zztr() {
        return getMeasuredHeight();
    }

    public final int zzts() {
        return getMeasuredWidth();
    }

    public final void zzty() {
        b();
        HashMap hashMap = new HashMap(1);
        hashMap.put(ResponseConstants.VERSION, this.b.zzcw);
        aop.a((aoo) this, "onhide", (Map) hashMap);
    }

    public final void zztz() {
        HashMap hashMap = new HashMap(3);
        hashMap.put("app_muted", String.valueOf(ao.D().b()));
        hashMap.put("app_volume", String.valueOf(ao.D().a()));
        hashMap.put("device_volume", String.valueOf(hv.a(getContext())));
        aop.a((aoo) this, "volume", (Map) hashMap);
    }

    public final synchronized void zzu(boolean z2) {
        if (this.f != null) {
            this.f.zza(this.e.zzfz(), z2);
        } else {
            this.i = z2;
        }
    }

    public final Context zzua() {
        return zzvv().zzua();
    }

    public final synchronized zzd zzub() {
        return this.f;
    }

    public final synchronized zzd zzuc() {
        return this.A;
    }

    public final synchronized ot zzud() {
        return this.g;
    }

    public final synchronized String zzue() {
        return this.h;
    }

    public final /* synthetic */ oo zzuf() {
        return this.e;
    }

    public final WebViewClient zzug() {
        return this.zzdfb;
    }

    public final synchronized boolean zzuh() {
        return this.i;
    }

    public final ack zzui() {
        return this.a;
    }

    public final synchronized boolean zzuj() {
        return this.j;
    }

    public final synchronized boolean zzul() {
        return this.m;
    }

    public final synchronized boolean zzum() {
        return this.n;
    }

    public final synchronized boolean zzun() {
        return this.t > 0;
    }

    public final void zzuo() {
        this.B.a();
    }

    public final void zzup() {
        if (this.x == null) {
            this.x = akr.a(this.y.a());
            this.y.a("native:view_load", this.x);
        }
    }

    public final synchronized ali zzuq() {
        return this.s;
    }

    public final void zzur() {
        setBackgroundColor(0);
    }

    public final void zzus() {
        gv.a("Cannot add text view to inner AdWebView");
    }
}
