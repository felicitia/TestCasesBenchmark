package com.google.android.gms.ads.internal;

import android.app.Activity;
import android.content.Context;
import android.os.RemoteException;
import android.support.annotation.Keep;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.gms.ads.internal.overlay.AdOverlayInfoParcel;
import com.google.android.gms.ads.internal.overlay.zzq;
import com.google.android.gms.ads.internal.overlay.zzr;
import com.google.android.gms.ads.internal.overlay.zzs;
import com.google.android.gms.ads.internal.overlay.zzx;
import com.google.android.gms.ads.internal.overlay.zzy;
import com.google.android.gms.common.GooglePlayServicesUtilLight;
import com.google.android.gms.common.annotation.KeepForSdkWithMembers;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import com.google.android.gms.internal.ads.bu;
import com.google.android.gms.internal.ads.hd;
import com.google.android.gms.internal.ads.zzaap;
import com.google.android.gms.internal.ads.zzaaz;
import com.google.android.gms.internal.ads.zzagq;
import com.google.android.gms.internal.ads.zzagz;
import com.google.android.gms.internal.ads.zzang;
import com.google.android.gms.internal.ads.zzjn;
import com.google.android.gms.internal.ads.zzkn;
import com.google.android.gms.internal.ads.zzks;
import com.google.android.gms.internal.ads.zzle;
import com.google.android.gms.internal.ads.zzlj;
import com.google.android.gms.internal.ads.zzpn;
import com.google.android.gms.internal.ads.zzpp;
import com.google.android.gms.internal.ads.zzqa;
import com.google.android.gms.internal.ads.zzqf;
import com.google.android.gms.internal.ads.zzxn;
import java.util.HashMap;

@Keep
@KeepForSdkWithMembers
@DynamiteApi
@bu
@RetainForClient
public class ClientApi extends zzle {
    public zzkn createAdLoaderBuilder(IObjectWrapper iObjectWrapper, String str, zzxn zzxn, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        ao.e();
        zzak zzak = new zzak(context, str, zzxn, new zzang(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, hd.k(context)), bg.a(context));
        return zzak;
    }

    public zzaap createAdOverlay(IObjectWrapper iObjectWrapper) {
        Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
        AdOverlayInfoParcel zzc = AdOverlayInfoParcel.zzc(activity.getIntent());
        if (zzc == null) {
            return new zzr(activity);
        }
        switch (zzc.zzbyu) {
            case 1:
                return new zzq(activity);
            case 2:
                return new zzx(activity);
            case 3:
                return new zzy(activity);
            case 4:
                return new zzs(activity, zzc);
            default:
                return new zzr(activity);
        }
    }

    public zzks createBannerAdManager(IObjectWrapper iObjectWrapper, zzjn zzjn, String str, zzxn zzxn, int i) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        ao.e();
        zzy zzy = new zzy(context, zzjn, str, zzxn, new zzang(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, hd.k(context)), bg.a(context));
        return zzy;
    }

    public zzaaz createInAppPurchaseManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    /* JADX INFO: used method not loaded: com.google.android.gms.internal.ads.akj.a(com.google.android.gms.internal.ads.akb):null, types can be incorrect */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0034, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.ajh.f().a((com.google.android.gms.internal.ads.akb) com.google.android.gms.internal.ads.akl.aT)).booleanValue() == false) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0048, code lost:
        if (((java.lang.Boolean) com.google.android.gms.internal.ads.ajh.f().a((com.google.android.gms.internal.ads.akb) com.google.android.gms.internal.ads.akl.aU)).booleanValue() != false) goto L_0x004c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.android.gms.internal.ads.zzks createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper r8, com.google.android.gms.internal.ads.zzjn r9, java.lang.String r10, com.google.android.gms.internal.ads.zzxn r11, int r12) throws android.os.RemoteException {
        /*
            r7 = this;
            java.lang.Object r8 = com.google.android.gms.dynamic.ObjectWrapper.unwrap(r8)
            r1 = r8
            android.content.Context r1 = (android.content.Context) r1
            com.google.android.gms.internal.ads.akl.a(r1)
            com.google.android.gms.internal.ads.zzang r5 = new com.google.android.gms.internal.ads.zzang
            com.google.android.gms.ads.internal.ao.e()
            boolean r8 = com.google.android.gms.internal.ads.hd.k(r1)
            r0 = 1
            r2 = 12451000(0xbdfcb8, float:1.7447567E-38)
            r5.<init>(r2, r12, r0, r8)
            java.lang.String r8 = "reward_mb"
            java.lang.String r12 = r9.zzarb
            boolean r8 = r8.equals(r12)
            if (r8 != 0) goto L_0x0036
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r12 = com.google.android.gms.internal.ads.akl.aT
            com.google.android.gms.internal.ads.akj r2 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r12 = r2.a(r12)
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 != 0) goto L_0x004c
        L_0x0036:
            if (r8 == 0) goto L_0x004b
            com.google.android.gms.internal.ads.akb<java.lang.Boolean> r8 = com.google.android.gms.internal.ads.akl.aU
            com.google.android.gms.internal.ads.akj r12 = com.google.android.gms.internal.ads.ajh.f()
            java.lang.Object r8 = r12.a(r8)
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r0 = 0
        L_0x004c:
            if (r0 == 0) goto L_0x005d
            com.google.android.gms.internal.ads.zzub r8 = new com.google.android.gms.internal.ads.zzub
            com.google.android.gms.ads.internal.bg r9 = com.google.android.gms.ads.internal.bg.a(r1)
            r0 = r8
            r2 = r10
            r3 = r11
            r4 = r5
            r5 = r9
            r0.<init>(r1, r2, r3, r4, r5)
            return r8
        L_0x005d:
            com.google.android.gms.ads.internal.zzal r8 = new com.google.android.gms.ads.internal.zzal
            com.google.android.gms.ads.internal.bg r6 = com.google.android.gms.ads.internal.bg.a(r1)
            r0 = r8
            r2 = r9
            r3 = r10
            r4 = r11
            r0.<init>(r1, r2, r3, r4, r5, r6)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.ads.internal.ClientApi.createInterstitialAdManager(com.google.android.gms.dynamic.IObjectWrapper, com.google.android.gms.internal.ads.zzjn, java.lang.String, com.google.android.gms.internal.ads.zzxn, int):com.google.android.gms.internal.ads.zzks");
    }

    public zzqa createNativeAdViewDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        return new zzpn((FrameLayout) ObjectWrapper.unwrap(iObjectWrapper), (FrameLayout) ObjectWrapper.unwrap(iObjectWrapper2));
    }

    public zzqf createNativeAdViewHolderDelegate(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2, IObjectWrapper iObjectWrapper3) {
        return new zzpp((View) ObjectWrapper.unwrap(iObjectWrapper), (HashMap) ObjectWrapper.unwrap(iObjectWrapper2), (HashMap) ObjectWrapper.unwrap(iObjectWrapper3));
    }

    public zzagz createRewardedVideoAd(IObjectWrapper iObjectWrapper, zzxn zzxn, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        ao.e();
        return new zzagq(context, bg.a(context), zzxn, new zzang(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, hd.k(context)));
    }

    public zzks createSearchAdManager(IObjectWrapper iObjectWrapper, zzjn zzjn, String str, int i) throws RemoteException {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        ao.e();
        return new zzbp(context, zzjn, str, new zzang(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, hd.k(context)));
    }

    @Nullable
    public zzlj getMobileAdsSettingsManager(IObjectWrapper iObjectWrapper) {
        return null;
    }

    public zzlj getMobileAdsSettingsManagerWithClientJarVersion(IObjectWrapper iObjectWrapper, int i) {
        Context context = (Context) ObjectWrapper.unwrap(iObjectWrapper);
        ao.e();
        return zzay.zza(context, new zzang(GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE, i, true, hd.k(context)));
    }
}
