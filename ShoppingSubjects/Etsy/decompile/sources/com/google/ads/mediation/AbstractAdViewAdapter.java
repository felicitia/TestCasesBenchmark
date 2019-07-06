package com.google.ads.mediation;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.formats.NativeAdView;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.android.gms.ads.g;
import com.google.android.gms.ads.i;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.h;
import com.google.android.gms.ads.mediation.k;
import com.google.android.gms.ads.mediation.l;
import com.google.android.gms.ads.mediation.m;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.ait;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.jp;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.zzatm;
import com.google.android.gms.internal.ads.zzlo;
import java.util.Date;
import java.util.Map;
import java.util.Set;

@bu
public abstract class AbstractAdViewAdapter implements MediationBannerAdapter, MediationNativeAdapter, k, m, MediationRewardedVideoAdAdapter, zzatm {
    public static final String AD_UNIT_ID_PARAMETER = "pubid";
    private AdView zzgw;
    private g zzgx;
    private com.google.android.gms.ads.b zzgy;
    private Context zzgz;
    /* access modifiers changed from: private */
    public g zzha;
    /* access modifiers changed from: private */
    public com.google.android.gms.ads.reward.mediation.a zzhb;
    @VisibleForTesting
    private final com.google.android.gms.ads.reward.b zzhc = new f(this);

    static class a extends com.google.android.gms.ads.mediation.g {
        private final com.google.android.gms.ads.formats.d e;

        public a(com.google.android.gms.ads.formats.d dVar) {
            this.e = dVar;
            a(dVar.b().toString());
            a(dVar.c());
            b(dVar.d().toString());
            a(dVar.e());
            c(dVar.f().toString());
            if (dVar.g() != null) {
                a(dVar.g().doubleValue());
            }
            if (dVar.h() != null) {
                d(dVar.h().toString());
            }
            if (dVar.i() != null) {
                e(dVar.i().toString());
            }
            a(true);
            b(true);
            a(dVar.j());
        }

        public final void a(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.e);
            }
            com.google.android.gms.ads.formats.c cVar = (com.google.android.gms.ads.formats.c) com.google.android.gms.ads.formats.c.a.get(view);
            if (cVar != null) {
                cVar.a((com.google.android.gms.ads.formats.a) this.e);
            }
        }
    }

    static class b extends h {
        private final com.google.android.gms.ads.formats.e e;

        public b(com.google.android.gms.ads.formats.e eVar) {
            this.e = eVar;
            a(eVar.b().toString());
            a(eVar.c());
            b(eVar.d().toString());
            if (eVar.e() != null) {
                a(eVar.e());
            }
            c(eVar.f().toString());
            d(eVar.g().toString());
            a(true);
            b(true);
            a(eVar.h());
        }

        public final void a(View view) {
            if (view instanceof NativeAdView) {
                ((NativeAdView) view).setNativeAd(this.e);
            }
            com.google.android.gms.ads.formats.c cVar = (com.google.android.gms.ads.formats.c) com.google.android.gms.ads.formats.c.a.get(view);
            if (cVar != null) {
                cVar.a((com.google.android.gms.ads.formats.a) this.e);
            }
        }
    }

    static class c extends l {
        private final com.google.android.gms.ads.formats.h a;

        public c(com.google.android.gms.ads.formats.h hVar) {
            this.a = hVar;
            a(hVar.a());
            a(hVar.b());
            b(hVar.c());
            a(hVar.d());
            c(hVar.e());
            d(hVar.f());
            a(hVar.g());
            e(hVar.h());
            f(hVar.i());
            a(hVar.l());
            a(true);
            b(true);
            a(hVar.j());
        }

        public final void a(View view, Map<String, View> map, Map<String, View> map2) {
            if (view instanceof UnifiedNativeAdView) {
                ((UnifiedNativeAdView) view).setNativeAd(this.a);
                return;
            }
            com.google.android.gms.ads.formats.c cVar = (com.google.android.gms.ads.formats.c) com.google.android.gms.ads.formats.c.a.get(view);
            if (cVar != null) {
                cVar.a(this.a);
            }
        }
    }

    @VisibleForTesting
    static final class d extends com.google.android.gms.ads.a implements com.google.android.gms.ads.doubleclick.a, ait {
        @VisibleForTesting
        private final AbstractAdViewAdapter a;
        @VisibleForTesting
        private final com.google.android.gms.ads.mediation.c b;

        public d(AbstractAdViewAdapter abstractAdViewAdapter, com.google.android.gms.ads.mediation.c cVar) {
            this.a = abstractAdViewAdapter;
            this.b = cVar;
        }

        public final void a() {
            this.b.a(this.a);
        }

        public final void a(int i) {
            this.b.a(this.a, i);
        }

        public final void a(String str, String str2) {
            this.b.a(this.a, str, str2);
        }

        public final void b() {
            this.b.b(this.a);
        }

        public final void c() {
            this.b.c(this.a);
        }

        public final void d() {
            this.b.d(this.a);
        }

        public final void onAdClicked() {
            this.b.e(this.a);
        }
    }

    @VisibleForTesting
    static final class e extends com.google.android.gms.ads.a implements ait {
        @VisibleForTesting
        private final AbstractAdViewAdapter a;
        @VisibleForTesting
        private final com.google.android.gms.ads.mediation.d b;

        public e(AbstractAdViewAdapter abstractAdViewAdapter, com.google.android.gms.ads.mediation.d dVar) {
            this.a = abstractAdViewAdapter;
            this.b = dVar;
        }

        public final void a() {
            this.b.a(this.a);
        }

        public final void a(int i) {
            this.b.a(this.a, i);
        }

        public final void b() {
            this.b.b(this.a);
        }

        public final void c() {
            this.b.c(this.a);
        }

        public final void d() {
            this.b.d(this.a);
        }

        public final void onAdClicked() {
            this.b.e(this.a);
        }
    }

    @VisibleForTesting
    static final class f extends com.google.android.gms.ads.a implements com.google.android.gms.ads.formats.d.a, com.google.android.gms.ads.formats.e.a, com.google.android.gms.ads.formats.f.a, com.google.android.gms.ads.formats.f.b, com.google.android.gms.ads.formats.h.a {
        @VisibleForTesting
        private final AbstractAdViewAdapter a;
        @VisibleForTesting
        private final com.google.android.gms.ads.mediation.e b;

        public f(AbstractAdViewAdapter abstractAdViewAdapter, com.google.android.gms.ads.mediation.e eVar) {
            this.a = abstractAdViewAdapter;
            this.b = eVar;
        }

        public final void a() {
        }

        public final void a(int i) {
            this.b.a((MediationNativeAdapter) this.a, i);
        }

        public final void a(com.google.android.gms.ads.formats.d dVar) {
            this.b.a((MediationNativeAdapter) this.a, (com.google.android.gms.ads.mediation.f) new a(dVar));
        }

        public final void a(com.google.android.gms.ads.formats.e eVar) {
            this.b.a((MediationNativeAdapter) this.a, (com.google.android.gms.ads.mediation.f) new b(eVar));
        }

        public final void a(com.google.android.gms.ads.formats.f fVar) {
            this.b.a((MediationNativeAdapter) this.a, fVar);
        }

        public final void a(com.google.android.gms.ads.formats.f fVar, String str) {
            this.b.a(this.a, fVar, str);
        }

        public final void a(com.google.android.gms.ads.formats.h hVar) {
            this.b.a((MediationNativeAdapter) this.a, (l) new c(hVar));
        }

        public final void b() {
            this.b.a(this.a);
        }

        public final void c() {
            this.b.b(this.a);
        }

        public final void d() {
            this.b.c(this.a);
        }

        public final void e() {
            this.b.e(this.a);
        }

        public final void onAdClicked() {
            this.b.d(this.a);
        }
    }

    private final com.google.android.gms.ads.c zza(Context context, com.google.android.gms.ads.mediation.a aVar, Bundle bundle, Bundle bundle2) {
        com.google.android.gms.ads.c.a aVar2 = new com.google.android.gms.ads.c.a();
        Date a2 = aVar.a();
        if (a2 != null) {
            aVar2.a(a2);
        }
        int b2 = aVar.b();
        if (b2 != 0) {
            aVar2.a(b2);
        }
        Set<String> c2 = aVar.c();
        if (c2 != null) {
            for (String a3 : c2) {
                aVar2.a(a3);
            }
        }
        Location d2 = aVar.d();
        if (d2 != null) {
            aVar2.a(d2);
        }
        if (aVar.f()) {
            ajh.a();
            aVar2.b(jp.a(context));
        }
        if (aVar.e() != -1) {
            boolean z = true;
            if (aVar.e() != 1) {
                z = false;
            }
            aVar2.a(z);
        }
        aVar2.b(aVar.g());
        aVar2.a(AdMobAdapter.class, zza(bundle, bundle2));
        return aVar2.a();
    }

    public String getAdUnitId(Bundle bundle) {
        return bundle.getString(AD_UNIT_ID_PARAMETER);
    }

    public View getBannerView() {
        return this.zzgw;
    }

    public Bundle getInterstitialAdapterInfo() {
        return new com.google.android.gms.ads.mediation.b.a().a(1).a();
    }

    public zzlo getVideoController() {
        if (this.zzgw != null) {
            i videoController = this.zzgw.getVideoController();
            if (videoController != null) {
                return videoController.a();
            }
        }
        return null;
    }

    public void initialize(Context context, com.google.android.gms.ads.mediation.a aVar, String str, com.google.android.gms.ads.reward.mediation.a aVar2, Bundle bundle, Bundle bundle2) {
        this.zzgz = context.getApplicationContext();
        this.zzhb = aVar2;
        this.zzhb.a((MediationRewardedVideoAdAdapter) this);
    }

    public boolean isInitialized() {
        return this.zzhb != null;
    }

    public void loadAd(com.google.android.gms.ads.mediation.a aVar, Bundle bundle, Bundle bundle2) {
        if (this.zzgz == null || this.zzhb == null) {
            ka.c("AdMobAdapter.loadAd called before initialize.");
            return;
        }
        this.zzha = new g(this.zzgz);
        this.zzha.a(true);
        this.zzha.a(getAdUnitId(bundle));
        this.zzha.a(this.zzhc);
        this.zzha.a((com.google.android.gms.ads.reward.c) new g(this));
        this.zzha.a(zza(this.zzgz, aVar, bundle2, bundle));
    }

    public void onDestroy() {
        if (this.zzgw != null) {
            this.zzgw.destroy();
            this.zzgw = null;
        }
        if (this.zzgx != null) {
            this.zzgx = null;
        }
        if (this.zzgy != null) {
            this.zzgy = null;
        }
        if (this.zzha != null) {
            this.zzha = null;
        }
    }

    public void onImmersiveModeUpdated(boolean z) {
        if (this.zzgx != null) {
            this.zzgx.b(z);
        }
        if (this.zzha != null) {
            this.zzha.b(z);
        }
    }

    public void onPause() {
        if (this.zzgw != null) {
            this.zzgw.pause();
        }
    }

    public void onResume() {
        if (this.zzgw != null) {
            this.zzgw.resume();
        }
    }

    public void requestBannerAd(Context context, com.google.android.gms.ads.mediation.c cVar, Bundle bundle, com.google.android.gms.ads.d dVar, com.google.android.gms.ads.mediation.a aVar, Bundle bundle2) {
        this.zzgw = new AdView(context);
        this.zzgw.setAdSize(new com.google.android.gms.ads.d(dVar.b(), dVar.a()));
        this.zzgw.setAdUnitId(getAdUnitId(bundle));
        this.zzgw.setAdListener(new d(this, cVar));
        this.zzgw.loadAd(zza(context, aVar, bundle2, bundle));
    }

    public void requestInterstitialAd(Context context, com.google.android.gms.ads.mediation.d dVar, Bundle bundle, com.google.android.gms.ads.mediation.a aVar, Bundle bundle2) {
        this.zzgx = new g(context);
        this.zzgx.a(getAdUnitId(bundle));
        this.zzgx.a((com.google.android.gms.ads.a) new e(this, dVar));
        this.zzgx.a(zza(context, aVar, bundle2, bundle));
    }

    public void requestNativeAd(Context context, com.google.android.gms.ads.mediation.e eVar, Bundle bundle, com.google.android.gms.ads.mediation.i iVar, Bundle bundle2) {
        f fVar = new f(this, eVar);
        com.google.android.gms.ads.b.a a2 = new com.google.android.gms.ads.b.a(context, bundle.getString(AD_UNIT_ID_PARAMETER)).a((com.google.android.gms.ads.a) fVar);
        com.google.android.gms.ads.formats.b h = iVar.h();
        if (h != null) {
            a2.a(h);
        }
        if (iVar.j()) {
            a2.a((com.google.android.gms.ads.formats.h.a) fVar);
        }
        if (iVar.i()) {
            a2.a((com.google.android.gms.ads.formats.d.a) fVar);
        }
        if (iVar.k()) {
            a2.a((com.google.android.gms.ads.formats.e.a) fVar);
        }
        if (iVar.l()) {
            for (String str : iVar.m().keySet()) {
                a2.a(str, fVar, ((Boolean) iVar.m().get(str)).booleanValue() ? fVar : null);
            }
        }
        this.zzgy = a2.a();
        this.zzgy.a(zza(context, iVar, bundle2, bundle));
    }

    public void showInterstitial() {
        this.zzgx.a();
    }

    public void showVideo() {
        this.zzha.a();
    }

    /* access modifiers changed from: protected */
    public abstract Bundle zza(Bundle bundle, Bundle bundle2);
}
