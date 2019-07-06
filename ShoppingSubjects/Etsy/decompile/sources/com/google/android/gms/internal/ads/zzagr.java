package com.google.android.gms.internal.ads;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.ads.internal.zzd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Arrays;
import java.util.Collections;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class zzagr extends zzd implements et {
    private static zzagr zzcle;
    private boolean zzclf;
    private final eg zzclg;
    private boolean zzyu;
    @VisibleForTesting
    private final fp zzyv;

    public zzagr(Context context, bg bgVar, zzjn zzjn, zzxn zzxn, zzang zzang) {
        super(context, zzjn, null, zzxn, zzang, bgVar);
        zzcle = this;
        this.zzyv = new fp(context, null);
        eg egVar = new eg(this.zzvw, this.zzwh, this, this, this);
        this.zzclg = egVar;
    }

    private static gb zzc(gb gbVar) {
        gb gbVar2 = gbVar;
        gv.a("Creating mediation ad response for non-mediated rewarded ad.");
        try {
            JSONObject a = dj.a(gbVar2.b);
            a.remove("impression_urls");
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, gbVar2.a.zzacp);
            String jSONObject2 = jSONObject.toString();
            aqy aqy = new aqy(a.toString(), null, Arrays.asList(new String[]{"com.google.ads.mediation.admob.AdMobAdapter"}), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            aqz aqz = new aqz(Arrays.asList(new aqy[]{aqy}), ((Long) ajh.f().a(akl.bB)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), false, "", -1, 0, 1, null, 0, -1, -1, false);
            gb gbVar3 = new gb(gbVar2.a, gbVar2.b, aqz, gbVar2.d, gbVar2.e, gbVar2.f, gbVar2.g, gbVar2.h, gbVar2.i, null);
            return gbVar3;
        } catch (JSONException e) {
            gv.b("Unable to generate ad state for non-mediated rewarded video.", e);
            gb gbVar4 = new gb(gbVar2.a, gbVar2.b, null, gbVar2.d, 0, gbVar2.f, gbVar2.g, gbVar2.h, gbVar2.i, null);
            return gbVar4;
        }
    }

    public static zzagr zzox() {
        return zzcle;
    }

    public final void destroy() {
        this.zzclg.f();
        super.destroy();
    }

    public final boolean isLoaded() {
        Preconditions.checkMainThread("isLoaded must be called on the main UI thread.");
        return this.zzvw.zzact == null && this.zzvw.zzacu == null && this.zzvw.zzacw != null;
    }

    public final void onContextChanged(Context context) {
        this.zzclg.a(context);
    }

    public final void onRewardedVideoAdClosed() {
        if (ao.B().e(this.zzvw.zzrt)) {
            this.zzyv.a(false);
        }
        zzbn();
    }

    public final void onRewardedVideoAdLeftApplication() {
        zzbo();
    }

    public final void onRewardedVideoAdOpened() {
        if (ao.B().e(this.zzvw.zzrt)) {
            this.zzyv.a(true);
        }
        zza(this.zzvw.zzacw, false);
        zzbp();
    }

    public final void onRewardedVideoCompleted() {
        this.zzclg.h();
        zzbu();
    }

    public final void onRewardedVideoStarted() {
        this.zzclg.g();
        zzbt();
    }

    public final void pause() {
        this.zzclg.d();
    }

    public final void resume() {
        this.zzclg.e();
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void zza(gb gbVar, aky aky) {
        if (gbVar.e != -2) {
            hd.a.post(new ei(this, gbVar));
            return;
        }
        this.zzvw.zzacx = gbVar;
        if (gbVar.c == null) {
            this.zzvw.zzacx = zzc(gbVar);
        }
        this.zzclg.c();
    }

    public final void zza(zzahk zzahk) {
        Preconditions.checkMainThread("loadAd must be called on the main UI thread.");
        if (TextUtils.isEmpty(zzahk.zzacp)) {
            gv.e("Invalid ad unit id. Aborting.");
            hd.a.post(new eh(this));
            return;
        }
        this.zzclf = false;
        this.zzvw.zzacp = zzahk.zzacp;
        this.zzyv.a(zzahk.zzacp);
        super.zzb(zzahk.zzccv);
    }

    public final boolean zza(ga gaVar, ga gaVar2) {
        zzb(gaVar2, false);
        return eg.a(gaVar, gaVar2);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzjj zzjj, ga gaVar, boolean z) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void zzbn() {
        this.zzvw.zzacw = null;
        super.zzbn();
    }

    public final void zzc(@Nullable zzaig zzaig) {
        zzaig a = this.zzclg.a(zzaig);
        if (ao.B().e(this.zzvw.zzrt) && a != null) {
            ao.B().a(this.zzvw.zzrt, ao.B().j(this.zzvw.zzrt), this.zzvw.zzacp, a.type, a.zzcmk);
        }
        zza(a);
    }

    @Nullable
    public final fa zzca(String str) {
        return this.zzclg.a(str);
    }

    public final void zzdm() {
        onAdClicked();
    }

    public final void zzoy() {
        Preconditions.checkMainThread("showAd must be called on the main UI thread.");
        if (!isLoaded()) {
            gv.e("The reward video has not loaded.");
        } else {
            this.zzclg.a(this.zzyu);
        }
    }
}
