package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsIntent;
import android.support.customtabs.CustomTabsIntent.Builder;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzc;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.a;
import com.google.android.gms.ads.mediation.d;
import com.google.android.gms.common.util.PlatformVersion;

@bu
public final class zzzv implements MediationInterstitialAdapter {
    /* access modifiers changed from: private */
    public Activity a;
    /* access modifiers changed from: private */
    public d b;
    private Uri c;

    public final void onDestroy() {
        ka.b("Destroying AdMobCustomTabsAdapter adapter.");
    }

    public final void onPause() {
        ka.b("Pausing AdMobCustomTabsAdapter adapter.");
    }

    public final void onResume() {
        ka.b("Resuming AdMobCustomTabsAdapter adapter.");
    }

    public final void requestInterstitialAd(Context context, d dVar, Bundle bundle, a aVar, Bundle bundle2) {
        this.b = dVar;
        if (this.b == null) {
            ka.e("Listener not set for mediation. Returning.");
        } else if (!(context instanceof Activity)) {
            ka.e("AdMobCustomTabs can only work with Activity context. Bailing out.");
            this.b.a(this, 0);
        } else {
            if (!(PlatformVersion.isAtLeastIceCreamSandwichMR1() && ala.a(context))) {
                ka.e("Default browser does not support custom tabs. Bailing out.");
                this.b.a(this, 0);
                return;
            }
            String string = bundle.getString("tab_url");
            if (TextUtils.isEmpty(string)) {
                ka.e("The tab_url retrieved from mediation metadata is empty. Bailing out.");
                this.b.a(this, 0);
                return;
            }
            this.a = (Activity) context;
            this.c = Uri.parse(string);
            this.b.a(this);
        }
    }

    public final void showInterstitial() {
        CustomTabsIntent build = new Builder().build();
        build.intent.setData(this.c);
        AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(new zzc(build.intent), null, new asb(this), null, new zzang(0, 0, false));
        hd.a.post(new asc(this, adOverlayInfoParcel));
        ao.i().f();
    }
}
