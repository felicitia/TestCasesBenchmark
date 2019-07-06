package com.google.android.gms.internal.ads;

import android.os.Build.VERSION;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.ads.internal.ao;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@bu
public class zzass extends WebView implements pg, pi, pk, pl {
    private final List<pg> zzdew = new CopyOnWriteArrayList();
    private final List<pl> zzdex = new CopyOnWriteArrayList();
    private final List<pi> zzdey = new CopyOnWriteArrayList();
    private final List<pk> zzdez = new CopyOnWriteArrayList();
    private final zzash zzdfa;
    protected final WebViewClient zzdfb;

    public zzass(zzash zzash) {
        super(zzash);
        this.zzdfa = zzash;
        setBackgroundColor(0);
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        settings.setSavePassword(false);
        settings.setSupportMultipleWindows(true);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);
        if (VERSION.SDK_INT >= 21) {
            settings.setMixedContentMode(2);
        }
        ao.g().a(getContext(), settings);
        removeJavascriptInterface("accessibility");
        removeJavascriptInterface("accessibilityTraversal");
        try {
            getSettings().setJavaScriptEnabled(true);
        } catch (NullPointerException e) {
            gv.b("Unable to enable Javascript.", e);
        }
        setLayerType(1, null);
        this.zzdfb = new pd(this, this, this, this);
        super.setWebViewClient(this.zzdfb);
    }

    public void addJavascriptInterface(Object obj, String str) {
        if (VERSION.SDK_INT >= 17) {
            super.addJavascriptInterface(obj, str);
        } else {
            gv.a("Ignore addJavascriptInterface due to low Android version.");
        }
    }

    public void loadUrl(String str) {
        try {
            super.loadUrl(str);
        } catch (Exception | IncompatibleClassChangeError | NoClassDefFoundError e) {
            ao.i().a(e, "CoreWebView.loadUrl");
            gv.d("#007 Could not call remote method.", e);
        }
    }

    public void setWebViewClient(WebViewClient webViewClient) {
    }

    public final void zza(pg pgVar) {
        this.zzdew.add(pgVar);
    }

    public final void zza(pi piVar) {
        this.zzdey.add(piVar);
    }

    public final void zza(pk pkVar) {
        this.zzdez.add(pkVar);
    }

    public final void zza(pl plVar) {
        this.zzdex.add(plVar);
    }

    public final boolean zza(pe peVar) {
        for (pg zza : this.zzdew) {
            if (zza.zza(peVar)) {
                return true;
            }
        }
        return false;
    }

    public final void zzb(pe peVar) {
        for (pi zzb : this.zzdey) {
            zzb.zzb(peVar);
        }
    }

    public void zzbe(String str) {
        ph.a(this, str);
    }

    public void zzc(pe peVar) {
        for (pk zzc : this.zzdez) {
            zzc.zzc(peVar);
        }
    }

    public final WebResourceResponse zzd(pe peVar) {
        for (pl zzd : this.zzdex) {
            WebResourceResponse zzd2 = zzd.zzd(peVar);
            if (zzd2 != null) {
                return zzd2;
            }
        }
        return null;
    }

    /* access modifiers changed from: protected */
    public final zzash zzvv() {
        return this.zzdfa;
    }
}
