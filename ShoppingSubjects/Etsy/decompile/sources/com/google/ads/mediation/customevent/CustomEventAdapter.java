package com.google.ads.mediation.customevent;

import android.app.Activity;
import android.view.View;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.d;
import com.google.android.gms.ads.mediation.customevent.c;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ka;

@KeepName
public final class CustomEventAdapter implements MediationBannerAdapter<c, d>, MediationInterstitialAdapter<c, d> {
    private View a;
    @VisibleForTesting
    private CustomEventBanner b;
    @VisibleForTesting
    private CustomEventInterstitial c;

    @VisibleForTesting
    static final class a implements b {
        private final CustomEventAdapter a;
        private final com.google.ads.mediation.c b;

        public a(CustomEventAdapter customEventAdapter, com.google.ads.mediation.c cVar) {
            this.a = customEventAdapter;
            this.b = cVar;
        }
    }

    @VisibleForTesting
    class b implements c {
        private final CustomEventAdapter a;
        private final d b;

        public b(CustomEventAdapter customEventAdapter, d dVar) {
            this.a = customEventAdapter;
            this.b = dVar;
        }
    }

    private static <T> T a(String str) {
        try {
            return Class.forName(str).newInstance();
        } catch (Throwable th) {
            String message = th.getMessage();
            StringBuilder sb = new StringBuilder(46 + String.valueOf(str).length() + String.valueOf(message).length());
            sb.append("Could not instantiate custom event adapter: ");
            sb.append(str);
            sb.append(". ");
            sb.append(message);
            ka.e(sb.toString());
            return null;
        }
    }

    public final void destroy() {
        if (this.b != null) {
            this.b.a();
        }
        if (this.c != null) {
            this.c.a();
        }
    }

    public final Class<c> getAdditionalParametersType() {
        return c.class;
    }

    public final View getBannerView() {
        return this.a;
    }

    public final Class<d> getServerParametersType() {
        return d.class;
    }

    public final void requestBannerAd(com.google.ads.mediation.c cVar, Activity activity, d dVar, com.google.ads.a aVar, com.google.ads.mediation.a aVar2, c cVar2) {
        this.b = (CustomEventBanner) a(dVar.b);
        if (this.b == null) {
            cVar.a(this, ErrorCode.INTERNAL_ERROR);
        } else {
            this.b.requestBannerAd(new a(this, cVar), activity, dVar.a, dVar.c, aVar, aVar2, cVar2 == null ? null : cVar2.a(dVar.a));
        }
    }

    public final void requestInterstitialAd(d dVar, Activity activity, d dVar2, com.google.ads.mediation.a aVar, c cVar) {
        this.c = (CustomEventInterstitial) a(dVar2.b);
        if (this.c == null) {
            dVar.a(this, ErrorCode.INTERNAL_ERROR);
        } else {
            this.c.requestInterstitialAd(new b(this, dVar), activity, dVar2.a, dVar2.c, aVar, cVar == null ? null : cVar.a(dVar2.a));
        }
    }

    public final void showInterstitial() {
        this.c.showInterstitial();
    }
}
