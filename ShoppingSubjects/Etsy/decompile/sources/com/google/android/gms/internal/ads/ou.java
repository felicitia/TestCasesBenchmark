package com.google.android.gms.internal.ads;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import com.etsy.android.lib.models.ResponseConstants.Includes;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bh;
import com.google.android.gms.ads.internal.gmsg.a;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.gmsg.ah;
import com.google.android.gms.ads.internal.gmsg.ai;
import com.google.android.gms.ads.internal.gmsg.c;
import com.google.android.gms.ads.internal.gmsg.d;
import com.google.android.gms.ads.internal.gmsg.e;
import com.google.android.gms.ads.internal.gmsg.k;
import com.google.android.gms.ads.internal.gmsg.l;
import com.google.android.gms.ads.internal.gmsg.m;
import com.google.android.gms.ads.internal.gmsg.o;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.h;
import com.google.android.gms.ads.internal.overlay.i;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map.Entry;

@bu
@VisibleForTesting
public final class ou extends aos<nn> implements oo, pg, pi, pk, pl {
    private OnAttachStateChangeListener A;
    private nn a;
    private final Object b;
    private ait c;
    private i d;
    private op e;
    private oq f;
    private k g;
    private m h;
    private or i;
    private boolean j;
    private ai k;
    private boolean l;
    private boolean m;
    private OnGlobalLayoutListener n;
    private OnScrollChangedListener o;
    private boolean p;
    private com.google.android.gms.ads.internal.overlay.k q;
    private final l r;
    private bh s;
    private c t;
    private n u;
    private os v;
    @Nullable
    private fl w;
    private boolean x;
    private boolean y;
    private int z;

    public ou(nn nnVar, boolean z2) {
        this(nnVar, z2, new l(nnVar, nnVar.zzua(), new ajw(nnVar.getContext())), null);
    }

    @VisibleForTesting
    private ou(nn nnVar, boolean z2, l lVar, c cVar) {
        this.b = new Object();
        this.j = false;
        this.a = nnVar;
        this.l = z2;
        this.r = lVar;
        this.t = null;
    }

    private final WebResourceResponse a(pe peVar) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(peVar.a);
        int i2 = 0;
        while (true) {
            i2++;
            if (i2 <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Entry entry : peVar.c.entrySet()) {
                    openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
                if (!(openConnection instanceof HttpURLConnection)) {
                    throw new IOException("Invalid protocol.");
                }
                httpURLConnection = (HttpURLConnection) openConnection;
                ao.e().a(this.a.getContext(), this.a.zztq().zzcw, false, httpURLConnection);
                jt jtVar = new jt();
                jtVar.a(httpURLConnection, (byte[]) null);
                int responseCode = httpURLConnection.getResponseCode();
                jtVar.a(httpURLConnection, responseCode);
                if (responseCode < 300 || responseCode >= 400) {
                    ao.e();
                } else {
                    String headerField = httpURLConnection.getHeaderField(Includes.LOCATION);
                    if (headerField == null) {
                        throw new IOException("Missing Location header in redirect");
                    }
                    URL url2 = new URL(url, headerField);
                    String protocol = url2.getProtocol();
                    if (protocol == null) {
                        gv.e("Protocol is null");
                        return null;
                    } else if (protocol.equals("http") || protocol.equals("https")) {
                        String str = "Redirecting to ";
                        String valueOf = String.valueOf(headerField);
                        gv.b(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                        httpURLConnection.disconnect();
                        url = url2;
                    } else {
                        String str2 = "Unsupported scheme: ";
                        String valueOf2 = String.valueOf(protocol);
                        gv.e(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
                        return null;
                    }
                }
            } else {
                StringBuilder sb = new StringBuilder(32);
                sb.append("Too many redirects (20)");
                throw new IOException(sb.toString());
            }
        }
        ao.e();
        return hd.a(httpURLConnection);
    }

    /* access modifiers changed from: private */
    public final void a(View view, fl flVar, int i2) {
        if (flVar.b() && i2 > 0) {
            flVar.a(view);
            if (flVar.b()) {
                hd.a.postDelayed(new ow(this, view, flVar, i2), 100);
            }
        }
    }

    private final void a(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z2 = false;
        boolean a2 = this.t != null ? this.t.a() : false;
        ao.c();
        Context context = this.a.getContext();
        if (!a2) {
            z2 = true;
        }
        h.a(context, adOverlayInfoParcel, z2);
        if (this.w != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbyl != null) {
                str = adOverlayInfoParcel.zzbyl.url;
            }
            this.w.a(str);
        }
    }

    private final void h() {
        if (this.A != null) {
            this.a.getView().removeOnAttachStateChangeListener(this.A);
        }
    }

    private final void i() {
        if (this.e != null && ((this.x && this.z <= 0) || this.y)) {
            this.e.a(!this.y);
            this.e = null;
        }
        this.a.zzup();
    }

    public final void a(zzc zzc) {
        boolean zzuj = this.a.zzuj();
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(zzc, (!zzuj || this.a.zzud().d()) ? this.c : null, zzuj ? null : this.d, this.q, this.a.zztq());
        a(adOverlayInfoParcel);
    }

    public final void a(boolean z2) {
        this.j = z2;
    }

    public final void a(boolean z2, int i2) {
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((!this.a.zzuj() || this.a.zzud().d()) ? this.c : null, this.d, this.q, this.a, z2, i2, this.a.zztq());
        a(adOverlayInfoParcel);
    }

    public final void a(boolean z2, int i2, String str) {
        boolean zzuj = this.a.zzuj();
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((!zzuj || this.a.zzud().d()) ? this.c : null, zzuj ? null : new oy(this.a, this.d), this.g, this.h, this.q, this.a, z2, i2, str, this.a.zztq());
        a(adOverlayInfoParcel);
    }

    public final void a(boolean z2, int i2, String str, String str2) {
        boolean zzuj = this.a.zzuj();
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((!zzuj || this.a.zzud().d()) ? this.c : null, zzuj ? null : new oy(this.a, this.d), this.g, this.h, this.q, this.a, z2, i2, str, str2, this.a.zztq());
        a(adOverlayInfoParcel);
    }

    public final boolean a() {
        boolean z2;
        synchronized (this.b) {
            z2 = this.m;
        }
        return z2;
    }

    public final OnGlobalLayoutListener b() {
        OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.b) {
            onGlobalLayoutListener = this.n;
        }
        return onGlobalLayoutListener;
    }

    public final OnScrollChangedListener c() {
        OnScrollChangedListener onScrollChangedListener;
        synchronized (this.b) {
            onScrollChangedListener = this.o;
        }
        return onScrollChangedListener;
    }

    public final void d() {
        if (this.w != null) {
            this.w.d();
            this.w = null;
        }
        h();
        super.d();
        synchronized (this.b) {
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            this.g = null;
            this.h = null;
            this.j = false;
            this.l = false;
            this.m = false;
            this.p = false;
            this.q = null;
            this.i = null;
            if (this.t != null) {
                this.t.a(true);
                this.t = null;
            }
        }
    }

    public final os e() {
        return this.v;
    }

    public final /* synthetic */ Object f() {
        return this.a;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void g() {
        this.a.zzuo();
        zzd zzub = this.a.zzub();
        if (zzub != null) {
            zzub.zznk();
        }
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
    }

    public final void zza(int i2, int i3, boolean z2) {
        this.r.a(i2, i3);
        if (this.t != null) {
            this.t.a(i2, i3, z2);
        }
    }

    public final void zza(OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.b) {
            this.m = true;
            this.a.zzuo();
            this.n = onGlobalLayoutListener;
            this.o = onScrollChangedListener;
        }
    }

    public final void zza(ait ait, k kVar, i iVar, m mVar, com.google.android.gms.ads.internal.overlay.k kVar2, boolean z2, @Nullable ai aiVar, bh bhVar, n nVar, @Nullable fl flVar) {
        k kVar3 = kVar;
        m mVar2 = mVar;
        ai aiVar2 = aiVar;
        n nVar2 = nVar;
        fl flVar2 = flVar;
        bh bhVar2 = bhVar == null ? new bh(this.a.getContext(), flVar2, null) : bhVar;
        this.t = new c(this.a, nVar2);
        this.w = flVar2;
        if (((Boolean) ajh.f().a(akl.aF)).booleanValue()) {
            a("/adMetadata", (ae<? super ReferenceT>) new a<Object>(kVar3));
        }
        a("/appEvent", (ae<? super ReferenceT>) new l<Object>(mVar2));
        a("/backButton", o.j);
        a("/refresh", o.k);
        a("/canOpenURLs", o.a);
        a("/canOpenIntents", o.b);
        a("/click", o.c);
        a("/close", o.d);
        a("/customClose", o.e);
        a("/instrument", o.n);
        a("/delayPageLoaded", o.p);
        a("/delayPageClosed", o.q);
        a("/getLocationInfo", o.r);
        a("/httpTrack", o.f);
        a("/log", o.g);
        a("/mraid", (ae<? super ReferenceT>) new d<Object>(bhVar2, this.t, nVar2));
        a("/mraidLoaded", (ae<? super ReferenceT>) this.r);
        e eVar = r1;
        bh bhVar3 = bhVar2;
        e eVar2 = new e(this.a.getContext(), this.a.zztq(), this.a.zzui(), kVar2, ait, kVar3, mVar2, iVar, bhVar2, this.t);
        a("/open", (ae<? super ReferenceT>) eVar);
        a("/precache", (ae<? super ReferenceT>) new nd<Object>());
        a("/touch", o.i);
        a("/video", o.l);
        a("/videoMeta", o.m);
        if (ao.B().a(this.a.getContext())) {
            a("/logScionEvent", (ae<? super ReferenceT>) new c<Object>(this.a.getContext()));
        }
        if (aiVar2 != null) {
            a("/setInterstitialProperties", (ae<? super ReferenceT>) new ah<Object>(aiVar2));
        }
        this.c = ait;
        this.d = iVar;
        this.g = kVar3;
        this.h = mVar;
        this.q = kVar2;
        this.s = bhVar3;
        this.u = nVar;
        this.k = aiVar2;
        this.j = z2;
    }

    public final void zza(op opVar) {
        this.e = opVar;
    }

    public final void zza(oq oqVar) {
        this.f = oqVar;
    }

    public final void zza(or orVar) {
        this.i = orVar;
    }

    public final void zza(os osVar) {
        this.v = osVar;
    }

    public final boolean zza(pe peVar) {
        String str = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(peVar.a);
        gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        Uri uri = peVar.b;
        if (a(uri)) {
            return true;
        }
        if (this.j) {
            String scheme = uri.getScheme();
            if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                if (this.c != null) {
                    if (((Boolean) ajh.f().a(akl.aj)).booleanValue()) {
                        this.c.onAdClicked();
                        if (this.w != null) {
                            this.w.a(peVar.a);
                        }
                        this.c = null;
                    }
                }
                return false;
            }
        }
        if (!this.a.getWebView().willNotDraw()) {
            try {
                ack zzui = this.a.zzui();
                if (zzui != null && zzui.a(uri)) {
                    uri = zzui.a(uri, this.a.getContext(), this.a.getView(), this.a.zzto());
                }
            } catch (zzcj unused) {
                String str2 = "Unable to append parameter to URL: ";
                String valueOf2 = String.valueOf(peVar.a);
                gv.e(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
            }
            if (this.s == null || this.s.b()) {
                zzc zzc = new zzc("android.intent.action.VIEW", uri.toString(), null, null, null, null, null);
                a(zzc);
                return true;
            }
            this.s.a(peVar.a);
            return true;
        }
        String str3 = "AdWebView unable to handle URL: ";
        String valueOf3 = String.valueOf(peVar.a);
        gv.e(valueOf3.length() != 0 ? str3.concat(valueOf3) : new String(str3));
        return true;
    }

    public final void zzb(int i2, int i3) {
        if (this.t != null) {
            this.t.a(i2, i3);
        }
    }

    public final void zzb(pe peVar) {
        a(peVar.b);
    }

    public final void zzc(pe peVar) {
        this.x = true;
        if (this.f != null) {
            this.f.a();
            this.f = null;
        }
        i();
    }

    @Nullable
    public final WebResourceResponse zzd(pe peVar) {
        WebResourceResponse webResourceResponse;
        if (this.w != null) {
            this.w.a(peVar.a, peVar.c, 1);
        }
        if (!"mraid.js".equalsIgnoreCase(new File(peVar.a).getName())) {
            webResourceResponse = null;
        } else {
            zznk();
            akb<String> akb = this.a.zzud().d() ? akl.K : this.a.zzuj() ? akl.J : akl.I;
            String str = (String) ajh.f().a(akb);
            ao.e();
            webResourceResponse = hd.c(this.a.getContext(), this.a.zztq().zzcw, str);
        }
        if (webResourceResponse != null) {
            return webResourceResponse;
        }
        try {
            if (!fu.a(peVar.a, this.a.getContext()).equals(peVar.a)) {
                return a(peVar);
            }
            zzhl zzaa = zzhl.zzaa(peVar.a);
            if (zzaa != null) {
                zzhi a2 = ao.k().a(zzaa);
                if (a2 != null && a2.zzhi()) {
                    return new WebResourceResponse("", "", a2.zzhj());
                }
            }
            if (jt.c()) {
                if (((Boolean) ajh.f().a(akl.bi)).booleanValue()) {
                    return a(peVar);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e2) {
            ao.i().a(e2, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    public final boolean zzfz() {
        boolean z2;
        synchronized (this.b) {
            z2 = this.l;
        }
        return z2;
    }

    public final void zznk() {
        synchronized (this.b) {
            this.j = false;
            this.l = true;
            kz.a.execute(new ov(this));
        }
    }

    public final bh zzut() {
        return this.s;
    }

    public final boolean zzux() {
        boolean z2;
        synchronized (this.b) {
            z2 = this.p;
        }
        return z2;
    }

    public final void zzuz() {
        fl flVar = this.w;
        if (flVar != null) {
            WebView webView = this.a.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                a((View) webView, flVar, 10);
                return;
            }
            h();
            this.A = new ox(this, flVar);
            this.a.getView().addOnAttachStateChangeListener(this.A);
        }
    }

    public final void zzva() {
        synchronized (this.b) {
            this.p = true;
        }
        this.z++;
        i();
    }

    public final void zzvb() {
        this.z--;
        i();
    }

    public final void zzvc() {
        this.y = true;
        i();
    }

    public final fl zzvf() {
        return this.w;
    }
}
