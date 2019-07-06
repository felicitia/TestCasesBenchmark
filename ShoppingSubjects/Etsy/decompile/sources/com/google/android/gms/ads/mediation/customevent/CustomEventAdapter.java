package com.google.android.gms.ads.mediation.customevent;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.d;
import com.google.android.gms.ads.mediation.e;
import com.google.android.gms.ads.mediation.i;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ka;

@KeepName
@KeepForSdkWithMembers
public final class CustomEventAdapter implements MediationBannerAdapter, MediationInterstitialAdapter, MediationNativeAdapter {
    private View a;
    @VisibleForTesting
    private CustomEventBanner b;
    @VisibleForTesting
    private CustomEventInterstitial c;
    @VisibleForTesting
    private CustomEventNative d;

    @VisibleForTesting
    static final class a implements b {
        private final CustomEventAdapter a;
        private final com.google.android.gms.ads.mediation.c b;

        public a(CustomEventAdapter customEventAdapter, com.google.android.gms.ads.mediation.c cVar) {
            this.a = customEventAdapter;
            this.b = cVar;
        }
    }

    @VisibleForTesting
    class b implements d {
        private final CustomEventAdapter a;
        private final d b;

        public b(CustomEventAdapter customEventAdapter, d dVar) {
            this.a = customEventAdapter;
            this.b = dVar;
        }
    }

    @VisibleForTesting
    static class c implements e {
        private final CustomEventAdapter a;
        private final e b;

        public c(CustomEventAdapter customEventAdapter, e eVar) {
            this.a = customEventAdapter;
            this.b = eVar;
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

    public final View getBannerView() {
        return this.a;
    }

    public final void onDestroy() {
        if (this.b != null) {
            this.b.a();
        }
        if (this.c != null) {
            this.c.a();
        }
        if (this.d != null) {
            this.d.a();
        }
    }

    public final void onPause() {
        if (this.b != null) {
            this.b.b();
        }
        if (this.c != null) {
            this.c.b();
        }
        if (this.d != null) {
            this.d.b();
        }
    }

    public final void onResume() {
        if (this.b != null) {
            this.b.c();
        }
        if (this.c != null) {
            this.c.c();
        }
        if (this.d != null) {
            this.d.c();
        }
    }

    public final void requestBannerAd(Context context, com.google.android.gms.ads.mediation.c cVar, Bundle bundle, com.google.android.gms.ads.d dVar, com.google.android.gms.ads.mediation.a aVar, Bundle bundle2) {
        this.b = (CustomEventBanner) a(bundle.getString("class_name"));
        if (this.b == null) {
            cVar.a(this, 0);
        } else {
            this.b.requestBannerAd(context, new a(this, cVar), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), dVar, aVar, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    public final void requestInterstitialAd(Context context, d dVar, Bundle bundle, com.google.android.gms.ads.mediation.a aVar, Bundle bundle2) {
        this.c = (CustomEventInterstitial) a(bundle.getString("class_name"));
        if (this.c == null) {
            dVar.a(this, 0);
        } else {
            this.c.requestInterstitialAd(context, new b(this, dVar), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), aVar, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    public final void requestNativeAd(Context context, e eVar, Bundle bundle, i iVar, Bundle bundle2) {
        this.d = (CustomEventNative) a(bundle.getString("class_name"));
        if (this.d == null) {
            eVar.a((MediationNativeAdapter) this, 0);
        } else {
            this.d.requestNativeAd(context, new c(this, eVar), bundle.getString(MediationRewardedVideoAdAdapter.CUSTOM_EVENT_SERVER_PARAMETER_FIELD), iVar, bundle2 == null ? null : bundle2.getBundle(bundle.getString("class_name")));
        }
    }

    public final void showInterstitial() {
        this.c.showInterstitial();
    }
}
