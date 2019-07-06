package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bh;
import com.google.android.gms.ads.internal.gmsg.ae;
import com.google.android.gms.ads.internal.gmsg.c;
import com.google.android.gms.ads.internal.zzbc;
import org.json.JSONObject;

@bu
public final class bd implements az<nn> {
    private kt<nn> a;
    private final c b = new c(this.d);
    private final alq c;
    private final Context d;
    private final zzang e;
    private final zzbc f;
    private final ack g;
    /* access modifiers changed from: private */
    public String h;

    public bd(Context context, zzbc zzbc, String str, ack ack, zzang zzang) {
        gv.d("Webview loading for native ads.");
        this.d = context;
        this.f = zzbc;
        this.g = ack;
        this.e = zzang;
        this.h = str;
        ao.f();
        kt a2 = nt.a(this.d, this.e, (String) ajh.f().a(akl.bX), this.g, this.f.zzbi());
        this.c = new alq(zzbc, str);
        this.a = ki.a(a2, (kd<? super A, ? extends B>) new be<Object,Object>(this), kz.b);
        kg.a(this.a, "WebViewNativeAdsUtil.constructor");
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ kt a(nn nnVar) throws Exception {
        gv.d("Javascript has loaded for native ads.");
        nnVar.zzuf().zza(this.f, this.f, this.f, this.f, this.f, false, null, new bh(this.d, null, null), null, null);
        nnVar.zza("/logScionEvent", (ae<? super nn>) this.b);
        nnVar.zza("/logScionEvent", (ae<? super nn>) this.c);
        return ki.a(nnVar);
    }

    public final kt<JSONObject> a(JSONObject jSONObject) {
        return ki.a(this.a, (kd<? super A, ? extends B>) new bf<Object,Object>(this, jSONObject), kz.a);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ kt a(JSONObject jSONObject, nn nnVar) throws Exception {
        jSONObject.put("ads_id", this.h);
        nnVar.zzb("google.afma.nativeAds.handleDownloadedImpressionPing", jSONObject);
        return ki.a(new JSONObject());
    }

    public final void a() {
        ki.a(this.a, (kf<V>) new bn<V>(this), kz.a);
    }

    public final void a(String str, ae<? super nn> aeVar) {
        ki.a(this.a, (kf<V>) new bk<V>(this, str, aeVar), kz.a);
    }

    public final void a(String str, JSONObject jSONObject) {
        ki.a(this.a, (kf<V>) new bm<V>(this, str, jSONObject), kz.a);
    }

    public final kt<JSONObject> b(JSONObject jSONObject) {
        return ki.a(this.a, (kd<? super A, ? extends B>) new bg<Object,Object>(this, jSONObject), kz.a);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ kt b(JSONObject jSONObject, nn nnVar) throws Exception {
        jSONObject.put("ads_id", this.h);
        nnVar.zzb("google.afma.nativeAds.handleImpressionPing", jSONObject);
        return ki.a(new JSONObject());
    }

    public final void b(String str, ae<? super nn> aeVar) {
        ki.a(this.a, (kf<V>) new bl<V>(this, str, aeVar), kz.a);
    }

    public final kt<JSONObject> c(JSONObject jSONObject) {
        return ki.a(this.a, (kd<? super A, ? extends B>) new bh<Object,Object>(this, jSONObject), kz.a);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ kt c(JSONObject jSONObject, nn nnVar) throws Exception {
        jSONObject.put("ads_id", this.h);
        nnVar.zzb("google.afma.nativeAds.handleClickGmsg", jSONObject);
        return ki.a(new JSONObject());
    }

    public final kt<JSONObject> d(JSONObject jSONObject) {
        return ki.a(this.a, (kd<? super A, ? extends B>) new bi<Object,Object>(this, jSONObject), kz.a);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ kt d(JSONObject jSONObject, nn nnVar) throws Exception {
        jSONObject.put("ads_id", this.h);
        le leVar = new le();
        nnVar.zza("/nativeAdPreProcess", (ae<? super nn>) new bj<Object>(this, nnVar, leVar));
        nnVar.zzb("google.afma.nativeAds.preProcessJsonGmsg", jSONObject);
        return leVar;
    }
}
