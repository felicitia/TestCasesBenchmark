package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.c;
import com.google.ads.mediation.d;
import com.google.ads.mediation.e;

@bu
public final class arr<NETWORK_EXTRAS extends e, SERVER_PARAMETERS extends MediationServerParameters> implements c, d {
    /* access modifiers changed from: private */
    public final zzxt a;

    public arr(zzxt zzxt) {
        this.a = zzxt;
    }

    public final void a(MediationBannerAdapter<?, ?> mediationBannerAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(47 + String.valueOf(valueOf).length());
        sb.append("Adapter called onFailedToReceiveAd with error. ");
        sb.append(valueOf);
        ka.b(sb.toString());
        ajh.a();
        if (!jp.b()) {
            ka.d("#008 Must be called on the main UI thread.", null);
            jp.a.post(new ars(this, errorCode));
            return;
        }
        try {
            this.a.onAdFailedToLoad(arv.a(errorCode));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }

    public final void a(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, ErrorCode errorCode) {
        String valueOf = String.valueOf(errorCode);
        StringBuilder sb = new StringBuilder(47 + String.valueOf(valueOf).length());
        sb.append("Adapter called onFailedToReceiveAd with error ");
        sb.append(valueOf);
        sb.append(".");
        ka.b(sb.toString());
        ajh.a();
        if (!jp.b()) {
            ka.d("#008 Must be called on the main UI thread.", null);
            jp.a.post(new aru(this, errorCode));
            return;
        }
        try {
            this.a.onAdFailedToLoad(arv.a(errorCode));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }
}
