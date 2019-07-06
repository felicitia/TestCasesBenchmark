package com.google.android.gms.ads.internal;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.webkit.WebView;
import com.google.android.gms.ads.d;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.afn;
import com.google.android.gms.internal.ads.ajh;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.fl;
import com.google.android.gms.internal.ads.fp;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.os;
import com.google.android.gms.internal.ads.ot;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzarg;
import com.google.android.gms.internal.ads.zzfp;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Map;

@bu
public final class zzy extends zzi implements OnGlobalLayoutListener, OnScrollChangedListener {
    private boolean zzvm;
    private boolean zzxf;
    private WeakReference<Object> zzxg = new WeakReference<>(null);

    public zzy(Context context, zzjn zzjn, String str, zzxn zzxn, zzang zzang, bg bgVar) {
        super(context, zzjn, str, zzxn, zzang, bgVar);
    }

    private final void zzc(nn nnVar) {
        if (zzcp()) {
            WebView webView = nnVar.getWebView();
            if (webView != null) {
                View view = nnVar.getView();
                if (view != null && ao.u().a(this.zzvw.zzrt)) {
                    int i = this.zzvw.zzacr.zzcve;
                    int i2 = this.zzvw.zzacr.zzcvf;
                    StringBuilder sb = new StringBuilder(23);
                    sb.append(i);
                    sb.append(".");
                    sb.append(i2);
                    this.zzwb = ao.u().a(sb.toString(), webView, "", "javascript", zzbz());
                    if (this.zzwb != null) {
                        ao.u().a(this.zzwb, view);
                        ao.u().a(this.zzwb);
                        this.zzxf = true;
                    }
                }
            }
        }
    }

    private final boolean zzd(@Nullable ga gaVar, ga gaVar2) {
        if (gaVar2.n) {
            View a = n.a(gaVar2);
            if (a == null) {
                gv.e("Could not get mediation view");
                return false;
            }
            View nextView = this.zzvw.zzacs.getNextView();
            if (nextView != null) {
                if (nextView instanceof nn) {
                    ((nn) nextView).destroy();
                }
                this.zzvw.zzacs.removeView(nextView);
            }
            if (!n.b(gaVar2)) {
                try {
                    if (ao.B().b(this.zzvw.zzrt)) {
                        new zzfp(this.zzvw.zzrt, a).zza((afn) new fp(this.zzvw.zzrt, this.zzvw.zzacp));
                    }
                    if (gaVar2.u != null) {
                        this.zzvw.zzacs.setMinimumWidth(gaVar2.u.widthPixels);
                        this.zzvw.zzacs.setMinimumHeight(gaVar2.u.heightPixels);
                    }
                    zzg(a);
                } catch (Exception e) {
                    ao.i().a((Throwable) e, "BannerAdManager.swapViews");
                    gv.c("Could not add mediation view to view hierarchy.", e);
                    return false;
                }
            }
        } else if (!(gaVar2.u == null || gaVar2.b == null)) {
            gaVar2.b.zza(ot.a(gaVar2.u));
            this.zzvw.zzacs.removeAllViews();
            this.zzvw.zzacs.setMinimumWidth(gaVar2.u.widthPixels);
            this.zzvw.zzacs.setMinimumHeight(gaVar2.u.heightPixels);
            zzg(gaVar2.b.getView());
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (gaVar != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 instanceof nn) {
                ((nn) nextView2).destroy();
            } else if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    @Nullable
    public final zzlo getVideoController() {
        Preconditions.checkMainThread("getVideoController must be called from the main thread.");
        if (this.zzvw.zzacw == null || this.zzvw.zzacw.b == null) {
            return null;
        }
        return this.zzvw.zzacw.b.zztm();
    }

    public final void onGlobalLayout() {
        zzd(this.zzvw.zzacw);
    }

    public final void onScrollChanged() {
        zzd(this.zzvw.zzacw);
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is NOT supported by BannerAdManager.");
    }

    /* access modifiers changed from: protected */
    public final nn zza(gb gbVar, @Nullable bh bhVar, @Nullable fl flVar) throws zzarg {
        zzjn zzjn;
        d dVar;
        if (this.zzvw.zzacv.zzard == null && this.zzvw.zzacv.zzarf) {
            zzbw zzbw = this.zzvw;
            if (gbVar.b.zzarf) {
                zzjn = this.zzvw.zzacv;
            } else {
                String str = gbVar.b.zzcet;
                if (str != null) {
                    String[] split = str.split("[xX]");
                    split[0] = split[0].trim();
                    split[1] = split[1].trim();
                    dVar = new d(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
                } else {
                    dVar = this.zzvw.zzacv.zzhy();
                }
                zzjn = new zzjn(this.zzvw.zzrt, dVar);
            }
            zzbw.zzacv = zzjn;
        }
        return super.zza(gbVar, bhVar, flVar);
    }

    /* access modifiers changed from: protected */
    public final void zza(@Nullable ga gaVar, boolean z) {
        if (zzcp()) {
            nn nnVar = gaVar != null ? gaVar.b : null;
            if (nnVar != null) {
                if (!this.zzxf) {
                    zzc(nnVar);
                }
                if (this.zzwb != null) {
                    nnVar.zza("onSdkImpression", (Map<String, ?>) new ArrayMap<String,Object>());
                }
            }
        }
        super.zza(gaVar, z);
        if (n.b(gaVar)) {
            c cVar = new c(this);
            if (gaVar != null && n.b(gaVar)) {
                nn nnVar2 = gaVar.b;
                Object view = nnVar2 != null ? nnVar2.getView() : null;
                if (view == null) {
                    gv.e("AdWebView is null");
                    return;
                }
                try {
                    List list = gaVar.o != null ? gaVar.o.r : null;
                    if (list != null) {
                        if (!list.isEmpty()) {
                            zzxz zzmo = gaVar.p != null ? gaVar.p.zzmo() : null;
                            zzyc zzmp = gaVar.p != null ? gaVar.p.zzmp() : null;
                            if (list.contains("2") && zzmo != null) {
                                zzmo.zzk(ObjectWrapper.wrap(view));
                                if (!zzmo.getOverrideImpressionRecording()) {
                                    zzmo.recordImpression();
                                }
                                nnVar2.zza("/nativeExpressViewClicked", n.a(zzmo, (zzyc) null, cVar));
                                return;
                            } else if (!list.contains("1") || zzmp == null) {
                                gv.e("No matching template id and mapper");
                                return;
                            } else {
                                zzmp.zzk(ObjectWrapper.wrap(view));
                                if (!zzmp.getOverrideImpressionRecording()) {
                                    zzmp.recordImpression();
                                }
                                nnVar2.zza("/nativeExpressViewClicked", n.a((zzxz) null, zzmp, cVar));
                                return;
                            }
                        }
                    }
                    gv.e("No template ids present in mediation response");
                } catch (RemoteException e) {
                    gv.c("Error occurred while recording impression and registering for clicks", e);
                }
            }
        }
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.akj.a(com.google.android.gms.internal.ads.akb):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x007c, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.ajh.f().a((com.google.android.gms.internal.ads.akb) com.google.android.gms.internal.ads.akl.bW)).booleanValue() != false) goto L_0x007e;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.ga r5, com.google.android.gms.internal.ads.ga r6) {
        /*
            r4 = this;
            boolean r0 = super.zza(r5, r6)
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x0025
            boolean r5 = r4.zzd(r5, r6)
            if (r5 != 0) goto L_0x0025
            com.google.android.gms.internal.ads.ahh r5 = r6.K
            if (r5 == 0) goto L_0x0021
            com.google.android.gms.internal.ads.ahh r5 = r6.K
            com.google.android.gms.internal.ads.zzhu$zza$zzb r6 = com.google.android.gms.internal.ads.zzhu.zza.zzb.AD_FAILED_TO_LOAD
            r5.a(r6)
        L_0x0021:
            r4.zzi(r1)
            return r1
        L_0x0025:
            r4.zzb(r6, r1)
            boolean r5 = r6.l
            r0 = 0
            if (r5 == 0) goto L_0x0064
            r4.zzd(r6)
            com.google.android.gms.ads.internal.ao.A()
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzacs
            com.google.android.gms.internal.ads.lm.a(r5, r4)
            com.google.android.gms.ads.internal.ao.A()
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            com.google.android.gms.ads.internal.zzbx r5 = r5.zzacs
            com.google.android.gms.internal.ads.lm.a(r5, r4)
            boolean r5 = r6.m
            if (r5 != 0) goto L_0x0081
            com.google.android.gms.ads.internal.b r5 = new com.google.android.gms.ads.internal.b
            r5.<init>(r4)
            com.google.android.gms.internal.ads.nn r1 = r6.b
            if (r1 == 0) goto L_0x0058
            com.google.android.gms.internal.ads.nn r1 = r6.b
            com.google.android.gms.internal.ads.oo r1 = r1.zzuf()
            goto L_0x0059
        L_0x0058:
            r1 = r0
        L_0x0059:
            if (r1 == 0) goto L_0x0081
            com.google.android.gms.ads.internal.bi r2 = new com.google.android.gms.ads.internal.bi
            r2.<init>(r6, r5)
            r1.zza(r2)
            goto L_0x0081
        L_0x0064:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            boolean r5 = r5.zzfp()
            if (r5 == 0) goto L_0x007e
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r5 = com.google.android.gms.internal.ads.akl.bW
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r5 = r2.a(r5)
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0081
        L_0x007e:
            r4.zza(r6, r1)
        L_0x0081:
            com.google.android.gms.internal.ads.nn r5 = r6.b
            if (r5 == 0) goto L_0x00a5
            com.google.android.gms.internal.ads.nn r5 = r6.b
            com.google.android.gms.internal.ads.zzarl r5 = r5.zztm()
            com.google.android.gms.internal.ads.nn r1 = r6.b
            com.google.android.gms.internal.ads.oo r1 = r1.zzuf()
            if (r1 == 0) goto L_0x0096
            r1.zzuz()
        L_0x0096:
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            com.google.android.gms.internal.ads.zzmu r1 = r1.zzadk
            if (r1 == 0) goto L_0x00a5
            if (r5 == 0) goto L_0x00a5
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            com.google.android.gms.internal.ads.zzmu r1 = r1.zzadk
            r5.zzb(r1)
        L_0x00a5:
            boolean r5 = com.google.android.gms.common.util.PlatformVersion.isAtLeastIceCreamSandwich()
            if (r5 == 0) goto L_0x0142
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            boolean r5 = r5.zzfo()
            if (r5 == 0) goto L_0x011e
            com.google.android.gms.internal.ads.nn r5 = r6.b
            if (r5 == 0) goto L_0x0139
            org.json.JSONObject r5 = r6.k
            if (r5 == 0) goto L_0x00c4
            com.google.android.gms.internal.ads.aen r5 = r4.zzvy
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            com.google.android.gms.internal.ads.zzjn r0 = r0.zzacv
            r5.a(r0, r6)
        L_0x00c4:
            com.google.android.gms.internal.ads.nn r5 = r6.b
            android.view.View r0 = r5.getView()
            com.google.android.gms.internal.ads.zzfp r5 = new com.google.android.gms.internal.ads.zzfp
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            android.content.Context r1 = r1.zzrt
            r5.<init>(r1, r0)
            com.google.android.gms.internal.ads.fq r1 = com.google.android.gms.ads.internal.ao.B()
            com.google.android.gms.ads.internal.zzbw r2 = r4.zzvw
            android.content.Context r2 = r2.zzrt
            boolean r1 = r1.b(r2)
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.internal.ads.zzjj r1 = r6.a
            boolean r1 = zza(r1)
            if (r1 == 0) goto L_0x0103
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            java.lang.String r1 = r1.zzacp
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            if (r1 != 0) goto L_0x0103
            com.google.android.gms.internal.ads.fp r1 = new com.google.android.gms.internal.ads.fp
            com.google.android.gms.ads.internal.zzbw r2 = r4.zzvw
            android.content.Context r2 = r2.zzrt
            com.google.android.gms.ads.internal.zzbw r3 = r4.zzvw
            java.lang.String r3 = r3.zzacp
            r1.<init>(r2, r3)
            r5.zza(r1)
        L_0x0103:
            boolean r1 = r6.a()
            if (r1 == 0) goto L_0x010f
            com.google.android.gms.internal.ads.nn r1 = r6.b
            r5.zza(r1)
            goto L_0x0139
        L_0x010f:
            com.google.android.gms.internal.ads.nn r1 = r6.b
            com.google.android.gms.internal.ads.oo r1 = r1.zzuf()
            com.google.android.gms.ads.internal.a r2 = new com.google.android.gms.ads.internal.a
            r2.<init>(r5, r6)
            r1.zza(r2)
            goto L_0x0139
        L_0x011e:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            android.view.View r5 = r5.zzadu
            if (r5 == 0) goto L_0x0139
            org.json.JSONObject r5 = r6.k
            if (r5 == 0) goto L_0x0139
            com.google.android.gms.internal.ads.aen r5 = r4.zzvy
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            com.google.android.gms.internal.ads.zzjn r0 = r0.zzacv
            com.google.android.gms.ads.internal.zzbw r1 = r4.zzvw
            android.view.View r1 = r1.zzadu
            r5.a(r0, r6, r1)
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            android.view.View r0 = r5.zzadu
        L_0x0139:
            boolean r5 = r6.n
            if (r5 != 0) goto L_0x0142
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            r5.zzj(r0)
        L_0x0142:
            r5 = 1
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzy.zza(com.google.android.gms.internal.ads.ga, com.google.android.gms.internal.ads.ga):boolean");
    }

    public final boolean zzb(zzjj zzjj) {
        zzy zzy = this;
        zzjj zzjj2 = zzjj;
        if (zzjj2.zzaqb != zzy.zzvm) {
            int i = zzjj2.versionCode;
            long j = zzjj2.zzapw;
            Bundle bundle = zzjj2.extras;
            int i2 = zzjj2.zzapx;
            List<String> list = zzjj2.zzapy;
            boolean z = zzjj2.zzapz;
            int i3 = zzjj2.zzaqa;
            zzjj zzjj3 = new zzjj(i, j, bundle, i2, list, z, i3, zzjj2.zzaqb || zzy.zzvm, zzjj2.zzaqc, zzjj2.zzaqd, zzjj2.zzaqe, zzjj2.zzaqf, zzjj2.zzaqg, zzjj2.zzaqh, zzjj2.zzaqi, zzjj2.zzaqj, zzjj2.zzaqk, zzjj2.zzaql);
            zzjj2 = zzjj3;
            zzy = this;
        }
        return super.zzb(zzjj2);
    }

    /* access modifiers changed from: protected */
    public final void zzbq() {
        nn nnVar = this.zzvw.zzacw != null ? this.zzvw.zzacw.b : null;
        if (!this.zzxf && nnVar != null) {
            zzc(nnVar);
        }
        super.zzbq();
    }

    /* access modifiers changed from: protected */
    public final boolean zzca() {
        boolean z;
        ao.e();
        if (!hd.a(this.zzvw.zzrt, "android.permission.INTERNET")) {
            ajh.a().a(this.zzvw.zzacs, this.zzvw.zzacv, "Missing internet permission in AndroidManifest.xml.", "Missing internet permission in AndroidManifest.xml. You must have the following declaration: <uses-permission android:name=\"android.permission.INTERNET\" />");
            z = false;
        } else {
            z = true;
        }
        ao.e();
        if (!hd.a(this.zzvw.zzrt)) {
            ajh.a().a(this.zzvw.zzacs, this.zzvw.zzacv, "Missing AdActivity with android:configChanges in AndroidManifest.xml.", "Missing AdActivity with android:configChanges in AndroidManifest.xml. You must have the following declaration within the <application> element: <activity android:name=\"com.google.android.gms.ads.AdActivity\" android:configChanges=\"keyboard|keyboardHidden|orientation|screenLayout|uiMode|screenSize|smallestScreenSize\" />");
            z = false;
        }
        if (!z && this.zzvw.zzacs != null) {
            this.zzvw.zzacs.setVisibility(0);
        }
        return z;
    }

    public final void zzcz() {
        this.zzvv.d();
    }

    /* access modifiers changed from: 0000 */
    @VisibleForTesting
    public final void zzd(@Nullable ga gaVar) {
        if (gaVar != null && !gaVar.m && this.zzvw.zzacs != null && ao.e().a((View) this.zzvw.zzacs, this.zzvw.zzrt) && this.zzvw.zzacs.getGlobalVisibleRect(new Rect(), null)) {
            if (!(gaVar == null || gaVar.b == null || gaVar.b.zzuf() == null)) {
                gaVar.b.zzuf().zza((os) null);
            }
            zza(gaVar, false);
            gaVar.m = true;
        }
    }
}
