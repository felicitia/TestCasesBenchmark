package com.google.android.gms.internal.ads;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnAttachStateChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.etsy.android.lib.models.ResponseConstants;
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
import com.google.android.gms.common.internal.GmsClientSupervisor;
import com.google.android.gms.common.util.Predicate;
import com.google.android.gms.common.util.VisibleForTesting;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

@bu
@VisibleForTesting
public class zzaqx extends WebViewClient implements oo {
    private static final String[] zzdbo = {"UNKNOWN", "HOST_LOOKUP", "UNSUPPORTED_AUTH_SCHEME", "AUTHENTICATION", "PROXY_AUTHENTICATION", "CONNECT", "IO", "TIMEOUT", "REDIRECT_LOOP", "UNSUPPORTED_SCHEME", "FAILED_SSL_HANDSHAKE", "BAD_URL", "FILE", "FILE_NOT_FOUND", "TOO_MANY_REQUESTS"};
    private static final String[] zzdbp = {"NOT_YET_VALID", "EXPIRED", "ID_MISMATCH", "UNTRUSTED", "DATE_INVALID", "INVALID"};
    private final Object mLock;
    private boolean zzaek;
    private ait zzapt;
    private k zzbll;
    private m zzblm;
    private ai zzbmu;
    private bh zzbmw;
    private c zzbmx;
    private n zzbmy;
    private com.google.android.gms.ads.internal.overlay.k zzbnb;
    private i zzbnc;
    private nn zzbnd;
    private final HashMap<String, List<ae<? super nn>>> zzdbq;
    private op zzdbr;
    private oq zzdbs;
    private or zzdbt;
    private boolean zzdbu;
    private boolean zzdbv;
    private OnGlobalLayoutListener zzdbw;
    private OnScrollChangedListener zzdbx;
    private boolean zzdby;
    private final l zzdbz;
    private os zzdca;
    private boolean zzdcb;
    private boolean zzdcc;
    private int zzdcd;
    private OnAttachStateChangeListener zzdce;
    @Nullable
    protected fl zzxd;

    public zzaqx(nn nnVar, boolean z) {
        this(nnVar, z, new l(nnVar, nnVar.zzua(), new ajw(nnVar.getContext())), null);
    }

    @VisibleForTesting
    private zzaqx(nn nnVar, boolean z, l lVar, c cVar) {
        this.zzdbq = new HashMap<>();
        this.mLock = new Object();
        this.zzdbu = false;
        this.zzbnd = nnVar;
        this.zzaek = z;
        this.zzdbz = lVar;
        this.zzbmx = null;
    }

    /* access modifiers changed from: private */
    public final void zza(View view, fl flVar, int i) {
        if (flVar.b() && i > 0) {
            flVar.a(view);
            if (flVar.b()) {
                hd.a.postDelayed(new np(this, view, flVar, i), 100);
            }
        }
    }

    private final void zza(AdOverlayInfoParcel adOverlayInfoParcel) {
        boolean z = false;
        boolean a = this.zzbmx != null ? this.zzbmx.a() : false;
        ao.c();
        Context context = this.zzbnd.getContext();
        if (!a) {
            z = true;
        }
        h.a(context, adOverlayInfoParcel, z);
        if (this.zzxd != null) {
            String str = adOverlayInfoParcel.url;
            if (str == null && adOverlayInfoParcel.zzbyl != null) {
                str = adOverlayInfoParcel.zzbyl.url;
            }
            this.zzxd.a(str);
        }
    }

    private final void zzd(Context context, String str, String str2, String str3) {
        String str4;
        if (((Boolean) ajh.f().a(akl.bs)).booleanValue()) {
            Bundle bundle = new Bundle();
            bundle.putString(NotificationCompat.CATEGORY_ERROR, str);
            bundle.putString(ResponseConstants.CODE, str2);
            String str5 = "host";
            if (!TextUtils.isEmpty(str3)) {
                Uri parse = Uri.parse(str3);
                if (parse.getHost() != null) {
                    str4 = parse.getHost();
                    bundle.putString(str5, str4);
                    ao.e().a(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
                }
            }
            str4 = "";
            bundle.putString(str5, str4);
            ao.e().a(context, this.zzbnd.zztq().zzcw, "gmob-apps", bundle, true);
        }
    }

    private final WebResourceResponse zze(String str, Map<String, String> map) throws IOException {
        HttpURLConnection httpURLConnection;
        URL url = new URL(str);
        int i = 0;
        while (true) {
            i++;
            if (i <= 20) {
                URLConnection openConnection = url.openConnection();
                openConnection.setConnectTimeout(10000);
                openConnection.setReadTimeout(10000);
                for (Entry entry : map.entrySet()) {
                    openConnection.addRequestProperty((String) entry.getKey(), (String) entry.getValue());
                }
                if (!(openConnection instanceof HttpURLConnection)) {
                    throw new IOException("Invalid protocol.");
                }
                httpURLConnection = (HttpURLConnection) openConnection;
                ao.e().a(this.zzbnd.getContext(), this.zzbnd.zztq().zzcw, false, httpURLConnection);
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
                        String str2 = "Redirecting to ";
                        String valueOf = String.valueOf(headerField);
                        gv.b(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                        httpURLConnection.disconnect();
                        url = url2;
                    } else {
                        String str3 = "Unsupported scheme: ";
                        String valueOf2 = String.valueOf(protocol);
                        gv.e(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
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

    private final void zzi(Uri uri) {
        String path = uri.getPath();
        List<ae> list = (List) this.zzdbq.get(path);
        if (list != null) {
            ao.e();
            Map a = hd.a(uri);
            if (gv.a(2)) {
                String str = "Received GMSG: ";
                String valueOf = String.valueOf(path);
                gv.a(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
                for (String str2 : a.keySet()) {
                    String str3 = (String) a.get(str2);
                    StringBuilder sb = new StringBuilder(4 + String.valueOf(str2).length() + String.valueOf(str3).length());
                    sb.append("  ");
                    sb.append(str2);
                    sb.append(": ");
                    sb.append(str3);
                    gv.a(sb.toString());
                }
            }
            for (ae zza : list) {
                zza.zza(this.zzbnd, a);
            }
            return;
        }
        String valueOf2 = String.valueOf(uri);
        StringBuilder sb2 = new StringBuilder(32 + String.valueOf(valueOf2).length());
        sb2.append("No GMSG handler found for GMSG: ");
        sb2.append(valueOf2);
        gv.a(sb2.toString());
    }

    private final void zzuy() {
        if (this.zzdce != null) {
            this.zzbnd.getView().removeOnAttachStateChangeListener(this.zzdce);
        }
    }

    private final void zzvd() {
        if (this.zzdbr != null && ((this.zzdcb && this.zzdcd <= 0) || this.zzdcc)) {
            this.zzdbr.a(!this.zzdcc);
            this.zzdbr = null;
        }
        this.zzbnd.zzup();
    }

    public final void onLoadResource(WebView webView, String str) {
        String str2 = "Loading resource: ";
        String valueOf = String.valueOf(str);
        gv.a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if ("gmsg".equalsIgnoreCase(parse.getScheme()) && "mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            zzi(parse);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x001d, code lost:
        if (r0.zzdbs == null) goto L_0x0027;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001f, code lost:
        r0.zzdbs.a();
        r0.zzdbs = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0027, code lost:
        zzvd();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0018, code lost:
        r0.zzdcb = true;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onPageFinished(android.webkit.WebView r1, java.lang.String r2) {
        /*
            r0 = this;
            java.lang.Object r1 = r0.mLock
            monitor-enter(r1)
            com.google.android.gms.internal.ads.nn r2 = r0.zzbnd     // Catch:{ all -> 0x002b }
            boolean r2 = r2.isDestroyed()     // Catch:{ all -> 0x002b }
            if (r2 == 0) goto L_0x0017
            java.lang.String r2 = "Blank page loaded, 1..."
            com.google.android.gms.internal.ads.gv.a(r2)     // Catch:{ all -> 0x002b }
            com.google.android.gms.internal.ads.nn r2 = r0.zzbnd     // Catch:{ all -> 0x002b }
            r2.zzuk()     // Catch:{ all -> 0x002b }
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            return
        L_0x0017:
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            r1 = 1
            r0.zzdcb = r1
            com.google.android.gms.internal.ads.oq r1 = r0.zzdbs
            if (r1 == 0) goto L_0x0027
            com.google.android.gms.internal.ads.oq r1 = r0.zzdbs
            r1.a()
            r1 = 0
            r0.zzdbs = r1
        L_0x0027:
            r0.zzvd()
            return
        L_0x002b:
            r2 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x002b }
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.zzaqx.onPageFinished(android.webkit.WebView, java.lang.String):void");
    }

    public final void onReceivedError(WebView webView, int i, String str, String str2) {
        String str3;
        if (i < 0) {
            int i2 = (-i) - 1;
            if (i2 < zzdbo.length) {
                str3 = zzdbo[i2];
                zzd(this.zzbnd.getContext(), "http_err", str3, str2);
                super.onReceivedError(webView, i, str, str2);
            }
        }
        str3 = String.valueOf(i);
        zzd(this.zzbnd.getContext(), "http_err", str3, str2);
        super.onReceivedError(webView, i, str, str2);
    }

    public final void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
        if (sslError != null) {
            int primaryError = sslError.getPrimaryError();
            zzd(this.zzbnd.getContext(), "ssl_err", (primaryError < 0 || primaryError >= zzdbp.length) ? String.valueOf(primaryError) : zzdbp[primaryError], ao.g().a(sslError));
        }
        super.onReceivedSslError(webView, sslErrorHandler, sslError);
    }

    public final void reset() {
        if (this.zzxd != null) {
            this.zzxd.d();
            this.zzxd = null;
        }
        zzuy();
        synchronized (this.mLock) {
            this.zzdbq.clear();
            this.zzapt = null;
            this.zzbnc = null;
            this.zzdbr = null;
            this.zzdbs = null;
            this.zzbll = null;
            this.zzblm = null;
            this.zzdbu = false;
            this.zzaek = false;
            this.zzdbv = false;
            this.zzdby = false;
            this.zzbnb = null;
            this.zzdbt = null;
            if (this.zzbmx != null) {
                this.zzbmx.a(true);
                this.zzbmx = null;
            }
        }
    }

    @Nullable
    @TargetApi(11)
    public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
        return zzd(str, Collections.emptyMap());
    }

    public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
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

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        String str2 = "AdWebView shouldOverrideUrlLoading: ";
        String valueOf = String.valueOf(str);
        gv.a(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        Uri parse = Uri.parse(str);
        if (!"gmsg".equalsIgnoreCase(parse.getScheme()) || !"mobileads.google.com".equalsIgnoreCase(parse.getHost())) {
            if (this.zzdbu && webView == this.zzbnd.getWebView()) {
                String scheme = parse.getScheme();
                if ("http".equalsIgnoreCase(scheme) || "https".equalsIgnoreCase(scheme)) {
                    if (this.zzapt != null) {
                        if (((Boolean) ajh.f().a(akl.aj)).booleanValue()) {
                            this.zzapt.onAdClicked();
                            if (this.zzxd != null) {
                                this.zzxd.a(str);
                            }
                            this.zzapt = null;
                        }
                    }
                    return super.shouldOverrideUrlLoading(webView, str);
                }
            }
            if (!this.zzbnd.getWebView().willNotDraw()) {
                try {
                    ack zzui = this.zzbnd.zzui();
                    if (zzui != null && zzui.a(parse)) {
                        parse = zzui.a(parse, this.zzbnd.getContext(), this.zzbnd.getView(), this.zzbnd.zzto());
                    }
                } catch (zzcj unused) {
                    String str3 = "Unable to append parameter to URL: ";
                    String valueOf2 = String.valueOf(str);
                    gv.e(valueOf2.length() != 0 ? str3.concat(valueOf2) : new String(str3));
                }
                if (this.zzbmw == null || this.zzbmw.b()) {
                    zzc zzc = new zzc("android.intent.action.VIEW", parse.toString(), null, null, null, null, null);
                    zza(zzc);
                    return true;
                }
                this.zzbmw.a(str);
                return true;
            }
            String str4 = "AdWebView unable to handle URL: ";
            String valueOf3 = String.valueOf(str);
            gv.e(valueOf3.length() != 0 ? str4.concat(valueOf3) : new String(str4));
            return true;
        }
        zzi(parse);
        return true;
    }

    public final void zza(int i, int i2, boolean z) {
        this.zzdbz.a(i, i2);
        if (this.zzbmx != null) {
            this.zzbmx.a(i, i2, z);
        }
    }

    public final void zza(OnGlobalLayoutListener onGlobalLayoutListener, OnScrollChangedListener onScrollChangedListener) {
        synchronized (this.mLock) {
            this.zzdbv = true;
            this.zzbnd.zzuo();
            this.zzdbw = onGlobalLayoutListener;
            this.zzdbx = onScrollChangedListener;
        }
    }

    public final void zza(zzc zzc) {
        boolean zzuj = this.zzbnd.zzuj();
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(zzc, (!zzuj || this.zzbnd.zzud().d()) ? this.zzapt : null, zzuj ? null : this.zzbnc, this.zzbnb, this.zzbnd.zztq());
        zza(adOverlayInfoParcel);
    }

    public final void zza(ait ait, k kVar, i iVar, m mVar, com.google.android.gms.ads.internal.overlay.k kVar2, boolean z, @Nullable ai aiVar, bh bhVar, n nVar, @Nullable fl flVar) {
        k kVar3 = kVar;
        m mVar2 = mVar;
        ai aiVar2 = aiVar;
        n nVar2 = nVar;
        fl flVar2 = flVar;
        bh bhVar2 = bhVar == null ? new bh(this.zzbnd.getContext(), flVar2, null) : bhVar;
        this.zzbmx = new c(this.zzbnd, nVar2);
        this.zzxd = flVar2;
        if (((Boolean) ajh.f().a(akl.aF)).booleanValue()) {
            zza("/adMetadata", (ae<? super nn>) new a<Object>(kVar3));
        }
        zza("/appEvent", (ae<? super nn>) new l<Object>(mVar2));
        zza("/backButton", o.j);
        zza("/refresh", o.k);
        zza("/canOpenURLs", o.a);
        zza("/canOpenIntents", o.b);
        zza("/click", o.c);
        zza("/close", o.d);
        zza("/customClose", o.e);
        zza("/instrument", o.n);
        zza("/delayPageLoaded", o.p);
        zza("/delayPageClosed", o.q);
        zza("/getLocationInfo", o.r);
        zza("/httpTrack", o.f);
        zza("/log", o.g);
        zza("/mraid", (ae<? super nn>) new d<Object>(bhVar2, this.zzbmx, nVar2));
        zza("/mraidLoaded", (ae<? super nn>) this.zzdbz);
        e eVar = r1;
        bh bhVar3 = bhVar2;
        e eVar2 = new e(this.zzbnd.getContext(), this.zzbnd.zztq(), this.zzbnd.zzui(), kVar2, ait, kVar3, mVar2, iVar, bhVar2, this.zzbmx);
        zza("/open", (ae<? super nn>) eVar);
        zza("/precache", (ae<? super nn>) new nd<Object>());
        zza("/touch", o.i);
        zza("/video", o.l);
        zza("/videoMeta", o.m);
        if (ao.B().a(this.zzbnd.getContext())) {
            zza("/logScionEvent", (ae<? super nn>) new c<Object>(this.zzbnd.getContext()));
        }
        if (aiVar2 != null) {
            zza("/setInterstitialProperties", (ae<? super nn>) new ah<Object>(aiVar2));
        }
        this.zzapt = ait;
        this.zzbnc = iVar;
        this.zzbll = kVar3;
        this.zzblm = mVar;
        this.zzbnb = kVar2;
        this.zzbmw = bhVar3;
        this.zzbmy = nVar;
        this.zzbmu = aiVar2;
        this.zzdbu = z;
    }

    public final void zza(op opVar) {
        this.zzdbr = opVar;
    }

    public final void zza(oq oqVar) {
        this.zzdbs = oqVar;
    }

    public final void zza(or orVar) {
        this.zzdbt = orVar;
    }

    public final void zza(os osVar) {
        this.zzdca = osVar;
    }

    public final void zza(String str, ae<? super nn> aeVar) {
        synchronized (this.mLock) {
            List list = (List) this.zzdbq.get(str);
            if (list == null) {
                list = new CopyOnWriteArrayList();
                this.zzdbq.put(str, list);
            }
            list.add(aeVar);
        }
    }

    public final void zza(String str, Predicate<ae<? super nn>> predicate) {
        synchronized (this.mLock) {
            List<ae> list = (List) this.zzdbq.get(str);
            if (list != null) {
                ArrayList arrayList = new ArrayList();
                for (ae aeVar : list) {
                    if (predicate.apply(aeVar)) {
                        arrayList.add(aeVar);
                    }
                }
                list.removeAll(arrayList);
            }
        }
    }

    public final void zza(boolean z, int i) {
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((!this.zzbnd.zzuj() || this.zzbnd.zzud().d()) ? this.zzapt : null, this.zzbnc, this.zzbnb, this.zzbnd, z, i, this.zzbnd.zztq());
        zza(adOverlayInfoParcel);
    }

    public final void zza(boolean z, int i, String str) {
        boolean zzuj = this.zzbnd.zzuj();
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((!zzuj || this.zzbnd.zzud().d()) ? this.zzapt : null, zzuj ? null : new ns(this.zzbnd, this.zzbnc), this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, this.zzbnd.zztq());
        zza(adOverlayInfoParcel);
    }

    public final void zza(boolean z, int i, String str, String str2) {
        boolean zzuj = this.zzbnd.zzuj();
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel((!zzuj || this.zzbnd.zzud().d()) ? this.zzapt : null, zzuj ? null : new ns(this.zzbnd, this.zzbnc), this.zzbll, this.zzblm, this.zzbnb, this.zzbnd, z, i, str, str2, this.zzbnd.zztq());
        zza(adOverlayInfoParcel);
    }

    public final void zzah(boolean z) {
        this.zzdbu = z;
    }

    public final void zzb(int i, int i2) {
        if (this.zzbmx != null) {
            this.zzbmx.a(i, i2);
        }
    }

    public final void zzb(String str, ae<? super nn> aeVar) {
        synchronized (this.mLock) {
            List list = (List) this.zzdbq.get(str);
            if (list != null) {
                list.remove(aeVar);
            }
        }
    }

    /* access modifiers changed from: protected */
    @Nullable
    public final WebResourceResponse zzd(String str, Map<String, String> map) {
        try {
            String a = fu.a(str, this.zzbnd.getContext());
            if (!a.equals(str)) {
                return zze(a, map);
            }
            zzhl zzaa = zzhl.zzaa(str);
            if (zzaa != null) {
                zzhi a2 = ao.k().a(zzaa);
                if (a2 != null && a2.zzhi()) {
                    return new WebResourceResponse("", "", a2.zzhj());
                }
            }
            if (jt.c()) {
                if (((Boolean) ajh.f().a(akl.bi)).booleanValue()) {
                    return zze(str, map);
                }
            }
            return null;
        } catch (Exception | NoClassDefFoundError e) {
            ao.i().a(e, "AdWebViewClient.interceptRequest");
            return null;
        }
    }

    public final boolean zzfz() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzaek;
        }
        return z;
    }

    public final void zznk() {
        synchronized (this.mLock) {
            this.zzdbu = false;
            this.zzaek = true;
            kz.a.execute(new no(this));
        }
    }

    public final bh zzut() {
        return this.zzbmw;
    }

    public final boolean zzuu() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdbv;
        }
        return z;
    }

    public final OnGlobalLayoutListener zzuv() {
        OnGlobalLayoutListener onGlobalLayoutListener;
        synchronized (this.mLock) {
            onGlobalLayoutListener = this.zzdbw;
        }
        return onGlobalLayoutListener;
    }

    public final OnScrollChangedListener zzuw() {
        OnScrollChangedListener onScrollChangedListener;
        synchronized (this.mLock) {
            onScrollChangedListener = this.zzdbx;
        }
        return onScrollChangedListener;
    }

    public final boolean zzux() {
        boolean z;
        synchronized (this.mLock) {
            z = this.zzdby;
        }
        return z;
    }

    public final void zzuz() {
        fl flVar = this.zzxd;
        if (flVar != null) {
            WebView webView = this.zzbnd.getWebView();
            if (ViewCompat.isAttachedToWindow(webView)) {
                zza((View) webView, flVar, 10);
                return;
            }
            zzuy();
            this.zzdce = new nr(this, flVar);
            this.zzbnd.getView().addOnAttachStateChangeListener(this.zzdce);
        }
    }

    public final void zzva() {
        synchronized (this.mLock) {
            this.zzdby = true;
        }
        this.zzdcd++;
        zzvd();
    }

    public final void zzvb() {
        this.zzdcd--;
        zzvd();
    }

    public final void zzvc() {
        this.zzdcc = true;
        zzvd();
    }

    public final os zzve() {
        return this.zzdca;
    }

    public final fl zzvf() {
        return this.zzxd;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zzvg() {
        this.zzbnd.zzuo();
        zzd zzub = this.zzbnd.zzub();
        if (zzub != null) {
            zzub.zznk();
        }
        if (this.zzdbt != null) {
            this.zzdbt.a();
            this.zzdbt = null;
        }
    }
}
