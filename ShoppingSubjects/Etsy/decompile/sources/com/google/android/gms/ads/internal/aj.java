package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.gv;

final class aj extends WebViewClient {
    private final /* synthetic */ zzbp a;

    aj(zzbp zzbp) {
        this.a = zzbp;
    }

    public final void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        if (this.a.zzxs != null) {
            try {
                this.a.zzxs.onAdFailedToLoad(0);
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final boolean shouldOverrideUrlLoading(WebView webView, String str) {
        if (str.startsWith(this.a.zzeb())) {
            return false;
        }
        if (str.startsWith((String) ajh.f().a(akl.cu))) {
            if (this.a.zzxs != null) {
                try {
                    this.a.zzxs.onAdFailedToLoad(3);
                } catch (RemoteException e) {
                    gv.d("#007 Could not call remote method.", e);
                }
            }
            this.a.zzk(0);
            return true;
        }
        if (str.startsWith((String) ajh.f().a(akl.cv))) {
            if (this.a.zzxs != null) {
                try {
                    this.a.zzxs.onAdFailedToLoad(0);
                } catch (RemoteException e2) {
                    gv.d("#007 Could not call remote method.", e2);
                }
            }
            this.a.zzk(0);
            return true;
        }
        if (str.startsWith((String) ajh.f().a(akl.cw))) {
            if (this.a.zzxs != null) {
                try {
                    this.a.zzxs.onAdLoaded();
                } catch (RemoteException e3) {
                    gv.d("#007 Could not call remote method.", e3);
                }
            }
            this.a.zzk(this.a.zzu(str));
            return true;
        } else if (str.startsWith("gmsg://")) {
            return true;
        } else {
            if (this.a.zzxs != null) {
                try {
                    this.a.zzxs.onAdLeftApplication();
                } catch (RemoteException e4) {
                    gv.d("#007 Could not call remote method.", e4);
                }
            }
            this.a.zzw(this.a.zzv(str));
            return true;
        }
    }
}
