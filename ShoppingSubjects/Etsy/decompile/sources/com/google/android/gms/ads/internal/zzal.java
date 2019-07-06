package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Window;
import com.etsy.android.lib.models.ResponseConstants;
import com.google.ads.mediation.AbstractAdViewAdapter;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.gmsg.ai;
import com.google.android.gms.ads.internal.gmsg.i;
import com.google.android.gms.ads.internal.gmsg.j;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.h;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.afn;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.akl;
import com.google.android.gms.internal.ads.aky;
import com.google.android.gms.internal.ads.aqy;
import com.google.android.gms.internal.ads.aqz;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.dj;
import com.google.android.gms.internal.ads.eg;
import com.google.android.gms.internal.ads.fl;
import com.google.android.gms.internal.ads.fp;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.hj;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.nt;
import com.google.android.gms.internal.ads.oo;
import com.google.android.gms.internal.ads.or;
import com.google.android.gms.internal.ads.ot;
import com.google.android.gms.internal.ads.zzaef;
import com.google.android.gms.internal.ads.zzaej;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzxn;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@bu
public final class zzal extends zzi implements ai, j {
    private transient boolean zzyq;
    private int zzyr = -1;
    /* access modifiers changed from: private */
    public boolean zzys;
    /* access modifiers changed from: private */
    public float zzyt;
    /* access modifiers changed from: private */
    public boolean zzyu;
    private fp zzyv;
    private String zzyw;
    private final String zzyx;
    private final eg zzyy;

    public zzal(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, bg bgVar) {
        super(context, zzjn, str, zzxn, zzang, bgVar);
        boolean z = false;
        this.zzyq = false;
        if (zzjn != null && "reward_mb".equals(zzjn.zzarb)) {
            z = true;
        }
        this.zzyx = z ? "/Rewarded" : "/Interstitial";
        this.zzyy = z ? new eg(this.zzvw, this.zzwh, new k(this), this, this) : null;
    }

    @VisibleForTesting
    private static gb zzb(gb gbVar) {
        gb gbVar2 = gbVar;
        try {
            String jSONObject = dj.a(gbVar2.b).toString();
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(AbstractAdViewAdapter.AD_UNIT_ID_PARAMETER, gbVar2.a.zzacp);
            aqy aqy = new aqy(jSONObject, null, Collections.singletonList("com.google.ads.mediation.admob.AdMobAdapter"), null, null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), jSONObject2.toString(), null, Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), null, null, null, null, null, Collections.emptyList(), null, -1);
            zzaej zzaej = gbVar2.b;
            aqz aqz = new aqz(Collections.singletonList(aqy), ((Long) ajh.f().a(akl.bB)).longValue(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), Collections.emptyList(), zzaej.zzbsr, zzaej.zzbss, "", -1, 0, 1, null, 0, -1, -1, false);
            zzaef zzaef = gbVar2.a;
            String str = zzaej.zzbyq;
            String str2 = zzaej.zzceo;
            List emptyList = Collections.emptyList();
            List emptyList2 = Collections.emptyList();
            long j = zzaej.zzcep;
            long j2 = zzaej.zzcer;
            List emptyList3 = Collections.emptyList();
            long j3 = zzaej.zzbsu;
            int i = zzaej.orientation;
            String str3 = zzaej.zzcet;
            long j4 = zzaej.zzceu;
            String str4 = zzaej.zzcev;
            boolean z = zzaej.zzcew;
            String str5 = zzaej.zzcex;
            boolean z2 = zzaej.zzcez;
            boolean z3 = zzaej.zzare;
            boolean z4 = zzaej.zzcdd;
            boolean z5 = zzaej.zzcfa;
            boolean z6 = zzaej.zzcfb;
            String str6 = zzaej.zzamj;
            boolean z7 = zzaej.zzarf;
            boolean z8 = zzaej.zzarg;
            List emptyList4 = Collections.emptyList();
            List emptyList5 = Collections.emptyList();
            boolean z9 = z8;
            boolean z10 = zzaej.zzcfh;
            zzael zzael = zzaej.zzcfi;
            boolean z11 = zzaej.zzcdr;
            String str7 = zzaej.zzcds;
            List<String> list = zzaej.zzbsr;
            boolean z12 = zzaej.zzbss;
            String str8 = zzaej.zzcfj;
            String str9 = zzaej.zzcfl;
            boolean z13 = zzaej.zzcfm;
            boolean z14 = zzaej.zzced;
            boolean z15 = zzaej.zzzl;
            boolean z16 = zzaej.zzcfp;
            boolean z17 = z16;
            zzaej zzaej2 = new zzaej(zzaef, str, str2, emptyList, emptyList2, j, true, j2, emptyList3, j3, i, str3, j4, str4, z, str5, null, z2, z3, z4, z5, z6, str6, z7, z9, null, emptyList4, emptyList5, z10, zzael, z11, str7, list, z12, str8, null, str9, z13, z14, z15, 0, z17, Collections.emptyList(), zzaej.zzzm, zzaej.zzcfq);
            gb gbVar3 = gbVar;
            gb gbVar4 = new gb(gbVar3.a, zzaej2, aqz, gbVar3.d, gbVar3.e, gbVar3.f, gbVar3.g, null, gbVar3.i, null);
            return gbVar4;
        } catch (JSONException e) {
            gb gbVar5 = gbVar2;
            gv.b("Unable to generate ad state for an interstitial ad with pooling.", e);
            return gbVar5;
        }
    }

    private final void zzb(Bundle bundle) {
        ao.e().b(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, "gmob-apps", bundle, false);
    }

    private final boolean zzc(boolean z) {
        return this.zzyy != null && z;
    }

    public final void setImmersiveMode(boolean z) {
        Preconditions.checkMainThread("setImmersiveMode must be called on the main UI thread.");
        this.zzyu = z;
    }

    public final void showInterstitial() {
        Bitmap bitmap;
        Preconditions.checkMainThread("showInterstitial must be called on the main UI thread.");
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.n)) {
            this.zzyy.a(this.zzyu);
            return;
        }
        if (ao.B().d(this.zzvw.zzrt)) {
            this.zzyw = ao.B().g(this.zzvw.zzrt);
            String valueOf = String.valueOf(this.zzyw);
            String valueOf2 = String.valueOf(this.zzyx);
            this.zzyw = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        if (this.zzvw.zzacw == null) {
            gv.e("The interstitial has not loaded.");
            return;
        }
        if (((Boolean) ajh.f().a(akl.br)).booleanValue()) {
            String packageName = (this.zzvw.zzrt.getApplicationContext() != null ? this.zzvw.zzrt.getApplicationContext() : this.zzvw.zzrt).getPackageName();
            if (!this.zzyq) {
                gv.e("It is not recommended to show an interstitial before onAdLoaded completes.");
                Bundle bundle = new Bundle();
                bundle.putString("appid", packageName);
                bundle.putString(ResponseConstants.ACTION, "show_interstitial_before_load_finish");
                zzb(bundle);
            }
            ao.e();
            if (!hd.g(this.zzvw.zzrt)) {
                gv.e("It is not recommended to show an interstitial when app is not in foreground.");
                Bundle bundle2 = new Bundle();
                bundle2.putString("appid", packageName);
                bundle2.putString(ResponseConstants.ACTION, "show_interstitial_app_not_in_foreground");
                zzb(bundle2);
            }
        }
        if (!this.zzvw.zzfp()) {
            if (this.zzvw.zzacw.n && this.zzvw.zzacw.p != null) {
                try {
                    if (((Boolean) ajh.f().a(akl.aQ)).booleanValue()) {
                        this.zzvw.zzacw.p.setImmersiveMode(this.zzyu);
                    }
                    this.zzvw.zzacw.p.showInterstitial();
                } catch (RemoteException e) {
                    gv.c("Could not show interstitial.", e);
                    zzdj();
                }
            } else if (this.zzvw.zzacw.b == null) {
                gv.e("The interstitial failed to load.");
            } else if (this.zzvw.zzacw.b.zzuj()) {
                gv.e("The interstitial is already showing.");
            } else {
                this.zzvw.zzacw.b.zzai(true);
                this.zzvw.zzj(this.zzvw.zzacw.b.getView());
                if (this.zzvw.zzacw.k != null) {
                    this.zzvy.a(this.zzvw.zzacv, this.zzvw.zzacw);
                }
                if (PlatformVersion.isAtLeastIceCreamSandwich()) {
                    ga gaVar = this.zzvw.zzacw;
                    if (gaVar.a()) {
                        new zzfp(this.zzvw.zzrt, gaVar.b.getView()).zza((afn) gaVar.b);
                    } else {
                        gaVar.b.zzuf().zza((or) new j(this, gaVar));
                    }
                }
                if (this.zzvw.zzze) {
                    ao.e();
                    bitmap = hd.h(this.zzvw.zzrt);
                } else {
                    bitmap = null;
                }
                this.zzyr = ao.y().a(bitmap);
                if (!((Boolean) ajh.f().a(akl.bR)).booleanValue() || bitmap == null) {
                    zzaq zzaq = new zzaq(this.zzvw.zzze, zzdi(), false, 0.0f, -1, this.zzyu, this.zzvw.zzacw.L, this.zzvw.zzacw.O);
                    int requestedOrientation = this.zzvw.zzacw.b.getRequestedOrientation();
                    if (requestedOrientation == -1) {
                        requestedOrientation = this.zzvw.zzacw.h;
                    }
                    AdOverlayInfoParcel adOverlayInfoParcel = new AdOverlayInfoParcel(this, this, this, this.zzvw.zzacw.b, requestedOrientation, this.zzvw.zzacr, this.zzvw.zzacw.A, zzaq);
                    ao.c();
                    h.a(this.zzvw.zzrt, adOverlayInfoParcel, true);
                    return;
                }
                new l(this, this.zzyr).h();
            }
        }
    }

    /* access modifiers changed from: protected */
    public final nn zza(gb gbVar, @Nullable bh bhVar, @Nullable fl flVar) throws zzarg {
        ao.f();
        nn a = nt.a(this.zzvw.zzrt, ot.a(this.zzvw.zzacv), this.zzvw.zzacv.zzarb, false, false, this.zzvw.zzacq, this.zzvw.zzacr, this.zzvr, this, this.zzwc, gbVar.i);
        a.zzuf().zza(this, this, null, this, this, ((Boolean) ajh.f().a(akl.ai)).booleanValue(), this, bhVar, this, flVar);
        zza(a);
        a.zzdr(gbVar.a.zzcdi);
        a.zza("/reward", (ae<? super nn>) new i<Object>(this));
        return a;
    }

    public final void zza(gb gbVar, aky aky) {
        if (gbVar.e != -2) {
            super.zza(gbVar, aky);
            return;
        }
        if (zzc(gbVar.c != null)) {
            this.zzyy.c();
            return;
        }
        if (!((Boolean) ajh.f().a(akl.aT)).booleanValue()) {
            super.zza(gbVar, aky);
            return;
        }
        boolean z = !gbVar.b.zzceq;
        if (zza(gbVar.a.zzccv) && z) {
            this.zzvw.zzacx = zzb(gbVar);
        }
        super.zza(this.zzvw.zzacx, aky);
    }

    public final void zza(boolean z, float f) {
        this.zzys = z;
        this.zzyt = f;
    }

    public final boolean zza(@Nullable ga gaVar, ga gaVar2) {
        if (zzc(gaVar2.n)) {
            return eg.a(gaVar, gaVar2);
        }
        if (!super.zza(gaVar, gaVar2)) {
            return false;
        }
        if (!(this.zzvw.zzfo() || this.zzvw.zzadu == null || gaVar2.k == null)) {
            this.zzvy.a(this.zzvw.zzacv, gaVar2, this.zzvw.zzadu);
        }
        zzb(gaVar2, false);
        return true;
    }

    public final boolean zza(zzjj zzjj, aky aky) {
        if (this.zzvw.zzacw != null) {
            gv.e("An interstitial is already loading. Aborting.");
            return false;
        }
        if (this.zzyv == null && zza(zzjj) && ao.B().d(this.zzvw.zzrt) && !TextUtils.isEmpty(this.zzvw.zzacp)) {
            this.zzyv = new fp(this.zzvw.zzrt, this.zzvw.zzacp);
        }
        return super.zza(zzjj, aky);
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzjj zzjj, ga gaVar, boolean z) {
        if (this.zzvw.zzfo() && gaVar.b != null) {
            ao.g();
            hj.a(gaVar.b);
        }
        return this.zzvv.e();
    }

    public final void zzb(zzaig zzaig) {
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.n)) {
            zza(this.zzyy.a(zzaig));
            return;
        }
        if (this.zzvw.zzacw != null) {
            if (this.zzvw.zzacw.x != null) {
                ao.e();
                hd.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.x);
            }
            if (this.zzvw.zzacw.v != null) {
                zzaig = this.zzvw.zzacw.v;
            }
        }
        zza(zzaig);
    }

    /* access modifiers changed from: protected */
    public final void zzbn() {
        zzdj();
        super.zzbn();
    }

    /* access modifiers changed from: protected */
    public final void zzbq() {
        nn nnVar = this.zzvw.zzacw != null ? this.zzvw.zzacw.b : null;
        gb gbVar = this.zzvw.zzacx;
        if (!(gbVar == null || gbVar.b == null || !gbVar.b.zzcfp || nnVar == null || !ao.u().a(this.zzvw.zzrt))) {
            int i = this.zzvw.zzacr.zzcve;
            int i2 = this.zzvw.zzacr.zzcvf;
            StringBuilder sb = new StringBuilder(23);
            sb.append(i);
            sb.append(".");
            sb.append(i2);
            this.zzwb = ao.u().a(sb.toString(), nnVar.getWebView(), "", "javascript", zzbz());
            if (!(this.zzwb == null || nnVar.getView() == null)) {
                ao.u().a(this.zzwb, nnVar.getView());
                ao.u().a(this.zzwb);
            }
        }
        super.zzbq();
        this.zzyq = true;
    }

    public final void zzcb() {
        super.zzcb();
        this.zzvy.a(this.zzvw.zzacw);
        if (this.zzyv != null) {
            this.zzyv.a(false);
        }
        zzby();
    }

    public final void zzcc() {
        recordImpression();
        super.zzcc();
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.b == null)) {
            oo zzuf = this.zzvw.zzacw.b.zzuf();
            if (zzuf != null) {
                zzuf.zzuz();
            }
        }
        if (!(!ao.B().d(this.zzvw.zzrt) || this.zzvw.zzacw == null || this.zzvw.zzacw.b == null)) {
            ao.B().c(this.zzvw.zzacw.b.getContext(), this.zzyw);
        }
        if (this.zzyv != null) {
            this.zzyv.a(true);
        }
        if (this.zzwb != null && this.zzvw.zzacw != null && this.zzvw.zzacw.b != null) {
            this.zzvw.zzacw.b.zza("onSdkImpression", (Map<String, ?>) new HashMap<String,Object>());
        }
    }

    public final void zzcz() {
        zzd zzub = this.zzvw.zzacw.b.zzub();
        if (zzub != null) {
            zzub.close();
        }
    }

    public final void zzd(boolean z) {
        this.zzvw.zzze = z;
    }

    /* access modifiers changed from: protected */
    public final boolean zzdi() {
        if (!(this.zzvw.zzrt instanceof Activity)) {
            return false;
        }
        Window window = ((Activity) this.zzvw.zzrt).getWindow();
        if (window == null || window.getDecorView() == null) {
            return false;
        }
        Rect rect = new Rect();
        Rect rect2 = new Rect();
        window.getDecorView().getGlobalVisibleRect(rect, null);
        window.getDecorView().getWindowVisibleDisplayFrame(rect2);
        if (!(rect.bottom == 0 || rect2.bottom == 0 || rect.top != rect2.top)) {
            return true;
        }
        return false;
    }

    public final void zzdj() {
        ao.y().b(Integer.valueOf(this.zzyr));
        if (this.zzvw.zzfo()) {
            this.zzvw.zzfm();
            this.zzvw.zzacw = null;
            this.zzvw.zzze = false;
            this.zzyq = false;
        }
    }

    public final void zzdk() {
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.n)) {
            this.zzyy.g();
            zzbt();
            return;
        }
        if (!(this.zzvw.zzacw == null || this.zzvw.zzacw.w == null)) {
            ao.e();
            hd.a(this.zzvw.zzrt, this.zzvw.zzacr.zzcw, this.zzvw.zzacw.w);
        }
        zzbt();
    }

    public final void zzdl() {
        if (zzc(this.zzvw.zzacw != null && this.zzvw.zzacw.n)) {
            this.zzyy.h();
        }
        zzbu();
    }
}
