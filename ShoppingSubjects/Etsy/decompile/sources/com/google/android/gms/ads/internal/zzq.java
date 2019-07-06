package com.google.android.gms.ads.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.ahh;
import com.google.android.gms.internal.ads.ali;
import com.google.android.gms.internal.ads.alj;
import com.google.android.gms.internal.ads.alk;
import com.google.android.gms.internal.ads.alm;
import com.google.android.gms.internal.ads.aln;
import com.google.android.gms.internal.ads.aqz;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gb;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.ka;
import com.google.android.gms.internal.ads.nn;
import com.google.android.gms.internal.ads.zzael;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjj;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzlo;
import com.google.android.gms.internal.ads.zzod;
import com.google.android.gms.internal.ads.zzoo;
import com.google.android.gms.internal.ads.zzoq;
import com.google.android.gms.internal.ads.zzov;
import com.google.android.gms.internal.ads.zzpw;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrc;
import com.google.android.gms.internal.ads.zzxn;
import com.google.android.gms.internal.ads.zzxz;
import com.google.android.gms.internal.ads.zzyc;
import com.google.android.gms.internal.ads.zzyf;
import java.util.List;
import org.json.JSONObject;

@bu
public final class zzq extends zzd implements alm {
    private boolean zzvm;
    /* access modifiers changed from: private */
    public ga zzwr;
    private boolean zzws = false;

    public zzq(Context context, bg bgVar, zzjn zzjn, String str, zzxn zzxn, zzang zzang) {
        super(context, zzjn, str, zzxn, zzang, bgVar);
    }

    private static ga zza(gb gbVar, int i) {
        gb gbVar2 = gbVar;
        zzjj zzjj = gbVar2.a.zzccv;
        List<String> list = gbVar2.b.zzbsn;
        List<String> list2 = gbVar2.b.zzbso;
        List<String> list3 = gbVar2.b.zzces;
        int i2 = gbVar2.b.orientation;
        long j = gbVar2.b.zzbsu;
        String str = gbVar2.a.zzccy;
        boolean z = gbVar2.b.zzceq;
        aqz aqz = gbVar2.c;
        long j2 = gbVar2.b.zzcer;
        zzjn zzjn = gbVar2.d;
        long j3 = j2;
        aqz aqz2 = aqz;
        long j4 = gbVar2.b.zzcep;
        long j5 = gbVar2.f;
        long j6 = gbVar2.g;
        String str2 = gbVar2.b.zzcev;
        JSONObject jSONObject = gbVar2.h;
        zzaig zzaig = gbVar2.b.zzcfe;
        List<String> list4 = gbVar2.b.zzcff;
        List<String> list5 = gbVar2.b.zzcff;
        boolean z2 = gbVar2.b.zzcfh;
        zzael zzael = gbVar2.b.zzcfi;
        List<String> list6 = gbVar2.b.zzbsr;
        String str3 = gbVar2.b.zzcfl;
        long j7 = j6;
        ahh ahh = gbVar2.i;
        boolean z3 = gbVar2.b.zzzl;
        ahh ahh2 = ahh;
        boolean z4 = gbVar2.j;
        String str4 = str3;
        boolean z5 = gbVar2.b.zzcfp;
        List<String> list7 = gbVar2.b.zzbsp;
        zzaig zzaig2 = zzaig;
        List<String> list8 = list4;
        List<String> list9 = list5;
        boolean z6 = z2;
        zzael zzael2 = zzael;
        List<String> list10 = list6;
        String str5 = str4;
        boolean z7 = z5;
        List<String> list11 = list7;
        JSONObject jSONObject2 = jSONObject;
        int i3 = i;
        String str6 = str2;
        boolean z8 = z3;
        aqz aqz3 = aqz2;
        long j8 = j3;
        zzjn zzjn2 = zzjn;
        long j9 = j4;
        long j10 = j5;
        long j11 = j7;
        ahh ahh3 = ahh2;
        boolean z9 = z8;
        boolean z10 = z4;
        ga gaVar = new ga(zzjj, null, list, i3, list2, list3, i2, j, str, z, null, null, null, aqz3, null, j8, zzjn2, j9, j10, j11, str6, jSONObject2, null, zzaig2, list8, list9, z6, zzael2, null, list10, str5, ahh3, z9, z10, z7, list11, gbVar2.b.zzzm, gbVar2.b.zzcfq);
        return gaVar;
    }

    private final void zza(zzov zzov) {
        hd.a.post(new bc(this, zzov));
    }

    private final boolean zzb(ga gaVar, ga gaVar2) {
        Handler handler;
        Runnable beVar;
        zzov zzov;
        ga gaVar3 = gaVar2;
        View view = null;
        zzd(null);
        if (!this.zzvw.zzfo()) {
            gv.e("Native ad does not have custom rendering mode.");
        } else {
            try {
                zzyf zzmu = gaVar3.p != null ? gaVar3.p.zzmu() : null;
                zzxz zzmo = gaVar3.p != null ? gaVar3.p.zzmo() : null;
                zzyc zzmp = gaVar3.p != null ? gaVar3.p.zzmp() : null;
                zzqs zzmt = gaVar3.p != null ? gaVar3.p.zzmt() : null;
                String zzc = zzc(gaVar2);
                if (zzmu != null && this.zzvw.zzadg != null) {
                    String headline = zzmu.getHeadline();
                    List images = zzmu.getImages();
                    String body = zzmu.getBody();
                    zzpw zzjz = zzmu.zzjz() != null ? zzmu.zzjz() : null;
                    String callToAction = zzmu.getCallToAction();
                    String advertiser = zzmu.getAdvertiser();
                    double starRating = zzmu.getStarRating();
                    String store = zzmu.getStore();
                    String price = zzmu.getPrice();
                    zzlo videoController = zzmu.getVideoController();
                    if (zzmu.zzmw() != null) {
                        view = (View) ObjectWrapper.unwrap(zzmu.zzmw());
                    }
                    zzov = new zzov(headline, images, body, zzjz, callToAction, advertiser, starRating, store, price, null, videoController, view, zzmu.zzke(), zzc, zzmu.getExtras());
                    alj alj = new alj(this.zzvw.zzrt, (alm) this, this.zzvw.zzacq, zzmu, (aln) zzov);
                    zzov.zzb((alk) alj);
                } else if (zzmo == null || this.zzvw.zzadg == null) {
                    if (zzmo != null && this.zzvw.zzade != null) {
                        String headline2 = zzmo.getHeadline();
                        List images2 = zzmo.getImages();
                        String body2 = zzmo.getBody();
                        zzpw zzjz2 = zzmo.zzjz() != null ? zzmo.zzjz() : null;
                        String callToAction2 = zzmo.getCallToAction();
                        double starRating2 = zzmo.getStarRating();
                        String store2 = zzmo.getStore();
                        String price2 = zzmo.getPrice();
                        Bundle extras = zzmo.getExtras();
                        zzlo videoController2 = zzmo.getVideoController();
                        if (zzmo.zzmw() != null) {
                            view = (View) ObjectWrapper.unwrap(zzmo.zzmw());
                        }
                        zzoo zzoo = new zzoo(headline2, images2, body2, zzjz2, callToAction2, starRating2, store2, price2, null, extras, videoController2, view, zzmo.zzke(), zzc);
                        alj alj2 = new alj(this.zzvw.zzrt, (alm) this, this.zzvw.zzacq, zzmo, (aln) zzoo);
                        zzoo.zzb((alk) alj2);
                        handler = hd.a;
                        beVar = new bd(this, zzoo);
                    } else if (zzmp != null && this.zzvw.zzadg != null) {
                        String headline3 = zzmp.getHeadline();
                        List images3 = zzmp.getImages();
                        String body3 = zzmp.getBody();
                        zzpw zzkg = zzmp.zzkg() != null ? zzmp.zzkg() : null;
                        String callToAction3 = zzmp.getCallToAction();
                        String advertiser2 = zzmp.getAdvertiser();
                        zzlo videoController3 = zzmp.getVideoController();
                        if (zzmp.zzmw() != null) {
                            view = (View) ObjectWrapper.unwrap(zzmp.zzmw());
                        }
                        zzov zzov2 = new zzov(headline3, images3, body3, zzkg, callToAction3, advertiser2, -1.0d, null, null, null, videoController3, view, zzmp.zzke(), zzc, zzmp.getExtras());
                        zzyc zzyc = zzmp;
                        zzov = zzov2;
                        alj alj3 = new alj(this.zzvw.zzrt, (alm) this, this.zzvw.zzacq, zzyc, (aln) zzov2);
                        zzov.zzb((alk) alj3);
                    } else if (zzmp != null && this.zzvw.zzadf != null) {
                        String headline4 = zzmp.getHeadline();
                        List images4 = zzmp.getImages();
                        String body4 = zzmp.getBody();
                        zzpw zzkg2 = zzmp.zzkg() != null ? zzmp.zzkg() : null;
                        String callToAction4 = zzmp.getCallToAction();
                        String advertiser3 = zzmp.getAdvertiser();
                        Bundle extras2 = zzmp.getExtras();
                        zzlo videoController4 = zzmp.getVideoController();
                        if (zzmp.zzmw() != null) {
                            view = (View) ObjectWrapper.unwrap(zzmp.zzmw());
                        }
                        zzoq zzoq = new zzoq(headline4, images4, body4, zzkg2, callToAction4, advertiser3, null, extras2, videoController4, view, zzmp.zzke(), zzc);
                        zzyc zzyc2 = zzmp;
                        zzoq zzoq2 = zzoq;
                        alj alj4 = new alj(this.zzvw.zzrt, (alm) this, this.zzvw.zzacq, zzyc2, (aln) zzoq);
                        zzoq2.zzb((alk) alj4);
                        handler = hd.a;
                        beVar = new be(this, zzoq2);
                    } else if (zzmt == null || this.zzvw.zzadi == null || this.zzvw.zzadi.get(zzmt.getCustomTemplateId()) == null) {
                        gv.e("No matching mapper/listener for retrieved native ad template.");
                        zzi(0);
                        return false;
                    } else {
                        hd.a.post(new bf(this, zzmt));
                        return super.zza(gaVar, gaVar2);
                    }
                    handler.post(beVar);
                    return super.zza(gaVar, gaVar2);
                } else {
                    String headline5 = zzmo.getHeadline();
                    List images5 = zzmo.getImages();
                    String body5 = zzmo.getBody();
                    zzpw zzjz3 = zzmo.zzjz() != null ? zzmo.zzjz() : null;
                    String callToAction5 = zzmo.getCallToAction();
                    double starRating3 = zzmo.getStarRating();
                    String store3 = zzmo.getStore();
                    String price3 = zzmo.getPrice();
                    zzlo videoController5 = zzmo.getVideoController();
                    if (zzmo.zzmw() != null) {
                        view = (View) ObjectWrapper.unwrap(zzmo.zzmw());
                    }
                    zzov = new zzov(headline5, images5, body5, zzjz3, callToAction5, null, starRating3, store3, price3, null, videoController5, view, zzmo.zzke(), zzc, zzmo.getExtras());
                    alj alj5 = new alj(this.zzvw.zzrt, (alm) this, this.zzvw.zzacq, zzmo, (aln) zzov);
                    zzov.zzb((alk) alj5);
                }
                zza(zzov);
                return super.zza(gaVar, gaVar2);
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
        zzi(0);
        return false;
    }

    private final boolean zzc(ga gaVar, ga gaVar2) {
        View a = n.a(gaVar2);
        if (a == null) {
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
                zzg(a);
            } catch (Throwable th) {
                ao.i().a(th, "AdLoaderManager.swapBannerViews");
                gv.c("Could not add mediation view to view hierarchy.", th);
                return false;
            }
        }
        if (this.zzvw.zzacs.getChildCount() > 1) {
            this.zzvw.zzacs.showNext();
        }
        if (gaVar != null) {
            View nextView2 = this.zzvw.zzacs.getNextView();
            if (nextView2 != null) {
                this.zzvw.zzacs.removeView(nextView2);
            }
            this.zzvw.zzfn();
        }
        this.zzvw.zzacs.setMinimumWidth(zzbk().widthPixels);
        this.zzvw.zzacs.setMinimumHeight(zzbk().heightPixels);
        this.zzvw.zzacs.requestLayout();
        this.zzvw.zzacs.setVisibility(0);
        return true;
    }

    @Nullable
    private final aqz zzcw() {
        if (this.zzvw.zzacw == null || !this.zzvw.zzacw.n) {
            return null;
        }
        return this.zzvw.zzacw.r;
    }

    @Nullable
    public final zzlo getVideoController() {
        return null;
    }

    public final void pause() {
        if (!this.zzws) {
            throw new IllegalStateException("Native Ad does not support pause().");
        }
        super.pause();
    }

    public final void resume() {
        if (!this.zzws) {
            throw new IllegalStateException("Native Ad does not support resume().");
        }
        super.resume();
    }

    public final void setManualImpressionsEnabled(boolean z) {
        Preconditions.checkMainThread("setManualImpressionsEnabled must be called from the main thread.");
        this.zzvm = z;
    }

    public final void showInterstitial() {
        throw new IllegalStateException("Interstitial is not supported by AdLoaderManager.");
    }

    public final void zza(ali ali) {
        ka.d("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zza(alk alk) {
        ka.d("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void zza(com.google.android.gms.internal.ads.gb r11, com.google.android.gms.internal.ads.aky r12) {
        /*
            r10 = this;
            r0 = 0
            r10.zzwr = r0
            int r0 = r11.e
            r1 = 0
            r2 = -2
            if (r0 == r2) goto L_0x0012
            int r0 = r11.e
            com.google.android.gms.internal.ads.ga r0 = zza(r11, r0)
        L_0x000f:
            r10.zzwr = r0
            goto L_0x0022
        L_0x0012:
            com.google.android.gms.internal.ads.zzaej r0 = r11.b
            boolean r0 = r0.zzceq
            if (r0 != 0) goto L_0x0022
            java.lang.String r0 = "partialAdState is not mediation"
            com.google.android.gms.internal.ads.gv.e(r0)
            com.google.android.gms.internal.ads.ga r0 = zza(r11, r1)
            goto L_0x000f
        L_0x0022:
            com.google.android.gms.internal.ads.ga r0 = r10.zzwr
            if (r0 == 0) goto L_0x0031
            android.os.Handler r11 = com.google.android.gms.internal.ads.hd.a
            com.google.android.gms.ads.internal.bb r12 = new com.google.android.gms.ads.internal.bb
            r12.<init>(r10)
            r11.post(r12)
            return
        L_0x0031:
            com.google.android.gms.internal.ads.zzjn r0 = r11.d
            if (r0 == 0) goto L_0x003b
            com.google.android.gms.ads.internal.zzbw r0 = r10.zzvw
            com.google.android.gms.internal.ads.zzjn r2 = r11.d
            r0.zzacv = r2
        L_0x003b:
            com.google.android.gms.ads.internal.zzbw r0 = r10.zzvw
            r0.zzadv = r1
            com.google.android.gms.ads.internal.zzbw r0 = r10.zzvw
            com.google.android.gms.ads.internal.ao.d()
            com.google.android.gms.ads.internal.zzbw r1 = r10.zzvw
            android.content.Context r2 = r1.zzrt
            com.google.android.gms.ads.internal.zzbw r1 = r10.zzvw
            com.google.android.gms.internal.ads.ack r5 = r1.zzacq
            r6 = 0
            com.google.android.gms.internal.ads.zzxn r7 = r10.zzwh
            r3 = r10
            r4 = r11
            r8 = r10
            r9 = r12
            com.google.android.gms.internal.ads.hw r11 = com.google.android.gms.internal.ads.y.a(r2, r3, r4, r5, r6, r7, r8, r9)
            r0.zzacu = r11
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzq.zza(com.google.android.gms.internal.ads.gb, com.google.android.gms.internal.ads.aky):void");
    }

    public final void zza(zzod zzod) {
        throw new IllegalStateException("CustomRendering is not supported by AdLoaderManager.");
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean zza(@android.support.annotation.Nullable com.google.android.gms.internal.ads.ga r5, com.google.android.gms.internal.ads.ga r6) {
        /*
            r4 = this;
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            boolean r0 = r0.zzfo()
            if (r0 != 0) goto L_0x0010
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "AdLoader API does not support custom rendering."
            r5.<init>(r6)
            throw r5
        L_0x0010:
            boolean r0 = r6.n
            r1 = 0
            if (r0 != 0) goto L_0x001e
            r4.zzi(r1)
            java.lang.String r5 = "newState is not mediation."
        L_0x001a:
            com.google.android.gms.internal.ads.gv.e(r5)
            return r1
        L_0x001e:
            com.google.android.gms.internal.ads.aqy r0 = r6.o
            r2 = 1
            if (r0 == 0) goto L_0x0072
            com.google.android.gms.internal.ads.aqy r0 = r6.o
            boolean r0 = r0.a()
            if (r0 == 0) goto L_0x0072
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x0046
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            com.google.android.gms.ads.internal.zzbx r0 = r0.zzacs
            if (r0 == 0) goto L_0x0046
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            com.google.android.gms.ads.internal.zzbx r0 = r0.zzacs
            com.google.android.gms.internal.ads.hx r0 = r0.zzfr()
            java.lang.String r3 = r6.A
            r0.c(r3)
        L_0x0046:
            boolean r0 = super.zza(r5, r6)
            if (r0 != 0) goto L_0x004e
        L_0x004c:
            r5 = r1
            goto L_0x006c
        L_0x004e:
            com.google.android.gms.ads.internal.zzbw r0 = r4.zzvw
            boolean r0 = r0.zzfo()
            if (r0 == 0) goto L_0x0060
            boolean r5 = r4.zzc(r5, r6)
            if (r5 != 0) goto L_0x0060
            r4.zzi(r1)
            goto L_0x004c
        L_0x0060:
            com.google.android.gms.ads.internal.zzbw r5 = r4.zzvw
            boolean r5 = r5.zzfp()
            if (r5 != 0) goto L_0x006b
            super.zza(r6, r1)
        L_0x006b:
            r5 = r2
        L_0x006c:
            if (r5 != 0) goto L_0x006f
            return r1
        L_0x006f:
            r4.zzws = r2
            goto L_0x0085
        L_0x0072:
            com.google.android.gms.internal.ads.aqy r0 = r6.o
            if (r0 == 0) goto L_0x009b
            com.google.android.gms.internal.ads.aqy r0 = r6.o
            boolean r0 = r0.b()
            if (r0 == 0) goto L_0x009b
            boolean r5 = r4.zzb(r5, r6)
            if (r5 != 0) goto L_0x0085
            return r1
        L_0x0085:
            java.util.ArrayList r5 = new java.util.ArrayList
            java.lang.Integer[] r6 = new java.lang.Integer[r2]
            r0 = 2
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            r6[r1] = r0
            java.util.List r6 = java.util.Arrays.asList(r6)
            r5.<init>(r6)
            r4.zze(r5)
            return r2
        L_0x009b:
            r4.zzi(r1)
            java.lang.String r5 = "Response is neither banner nor native."
            goto L_0x001a
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.zzq.zza(com.google.android.gms.internal.ads.ga, com.google.android.gms.internal.ads.ga):boolean");
    }

    /* access modifiers changed from: protected */
    public final boolean zza(zzjj zzjj, ga gaVar, boolean z) {
        return false;
    }

    /* access modifiers changed from: protected */
    public final void zzb(@Nullable IObjectWrapper iObjectWrapper) {
        Object unwrap = iObjectWrapper != null ? ObjectWrapper.unwrap(iObjectWrapper) : null;
        if (unwrap instanceof alk) {
            ((alk) unwrap).d();
        }
        super.zzb(this.zzvw.zzacw, false);
    }

    public final boolean zzb(zzjj zzjj) {
        zzq zzq = this;
        zzjj zzjj2 = zzjj;
        if (zzq.zzvw.zzadn != null && zzq.zzvw.zzadn.size() == 1 && ((Integer) zzq.zzvw.zzadn.get(0)).intValue() == 2) {
            gv.c("Requesting only banner Ad from AdLoader or calling loadAd on returned banner is not yet supported");
            zzq.zzi(0);
            return false;
        } else if (zzq.zzvw.zzadm == null) {
            return super.zzb(zzjj);
        } else {
            if (zzjj2.zzaqb != zzq.zzvm) {
                int i = zzjj2.versionCode;
                long j = zzjj2.zzapw;
                Bundle bundle = zzjj2.extras;
                int i2 = zzjj2.zzapx;
                List<String> list = zzjj2.zzapy;
                boolean z = zzjj2.zzapz;
                int i3 = zzjj2.zzaqa;
                boolean z2 = zzjj2.zzaqb || zzq.zzvm;
                zzjj zzjj3 = new zzjj(i, j, bundle, i2, list, z, i3, z2, zzjj2.zzaqc, zzjj2.zzaqd, zzjj2.zzaqe, zzjj2.zzaqf, zzjj2.zzaqg, zzjj2.zzaqh, zzjj2.zzaqi, zzjj2.zzaqj, zzjj2.zzaqk, zzjj2.zzaql);
                zzjj2 = zzjj3;
                zzq = this;
            }
            return super.zzb(zzjj2);
        }
    }

    /* access modifiers changed from: protected */
    public final void zzbq() {
        super.zzbq();
        ga gaVar = this.zzvw.zzacw;
        if (!(gaVar == null || gaVar.o == null || !gaVar.o.a() || this.zzvw.zzadm == null)) {
            try {
                this.zzvw.zzadm.zza(this, ObjectWrapper.wrap(this.zzvw.zzrt));
                super.zzb(this.zzvw.zzacw, false);
            } catch (RemoteException e) {
                gv.d("#007 Could not call remote method.", e);
            }
        }
    }

    public final void zzce() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.q) || this.zzvw.zzacw.o == null || !this.zzvw.zzacw.o.b()) {
            super.zzce();
        } else {
            zzbs();
        }
    }

    public final void zzcj() {
        if (this.zzvw.zzacw == null || !"com.google.ads.mediation.admob.AdMobAdapter".equals(this.zzvw.zzacw.q) || this.zzvw.zzacw.o == null || !this.zzvw.zzacw.o.b()) {
            super.zzcj();
        } else {
            zzbr();
        }
    }

    public final void zzcr() {
        ka.d("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzcs() {
        ka.d("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final void zzct() {
        ka.d("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    public final boolean zzcu() {
        if (zzcw() != null) {
            return zzcw().p;
        }
        return false;
    }

    public final boolean zzcv() {
        if (zzcw() != null) {
            return zzcw().q;
        }
        return false;
    }

    public final void zzd(@Nullable List<String> list) {
        Preconditions.checkMainThread("setNativeTemplates must be called on the main UI thread.");
        this.zzvw.zzads = list;
    }

    public final void zze(List<Integer> list) {
        Preconditions.checkMainThread("setAllowedAdTypes must be called on the main UI thread.");
        this.zzvw.zzadn = list;
    }

    public final void zzi(View view) {
        ka.d("#005 Unexpected call to an abstract (unimplemented) method.", null);
    }

    @Nullable
    public final zzrc zzr(String str) {
        Preconditions.checkMainThread("getOnCustomClickListener must be called on the main UI thread.");
        return (zzrc) this.zzvw.zzadh.get(str);
    }
}
