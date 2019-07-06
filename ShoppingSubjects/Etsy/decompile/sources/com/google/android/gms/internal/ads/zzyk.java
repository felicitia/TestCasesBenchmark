package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.l;
import com.google.android.gms.ads.mediation.MediationBannerAdapter;
import com.google.android.gms.ads.mediation.MediationInterstitialAdapter;
import com.google.android.gms.ads.mediation.MediationNativeAdapter;
import com.google.android.gms.ads.mediation.b;
import com.google.android.gms.ads.mediation.f;
import com.google.android.gms.ads.mediation.g;
import com.google.android.gms.ads.mediation.h;
import com.google.android.gms.ads.mediation.j;
import com.google.android.gms.ads.mediation.k;
import com.google.android.gms.ads.mediation.m;
import com.google.android.gms.ads.reward.mediation.InitializableMediationRewardedVideoAdAdapter;
import com.google.android.gms.ads.reward.mediation.MediationRewardedVideoAdAdapter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@bu
public final class zzyk extends zzxr {
    private final b zzbus;
    private arp zzbut;

    public zzyk(b bVar) {
        this.zzbus = bVar;
    }

    private final Bundle zza(String str, zzjj zzjj, String str2) throws RemoteException {
        Bundle bundle;
        String str3 = "Server parameters: ";
        String valueOf = String.valueOf(str);
        ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
        try {
            Bundle bundle2 = new Bundle();
            if (str != null) {
                JSONObject jSONObject = new JSONObject(str);
                bundle = new Bundle();
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str4 = (String) keys.next();
                    bundle.putString(str4, jSONObject.getString(str4));
                }
            } else {
                bundle = bundle2;
            }
            if (this.zzbus instanceof AdMobAdapter) {
                bundle.putString("adJson", str2);
                if (zzjj != null) {
                    bundle.putInt("tagForChildDirectedTreatment", zzjj.zzaqa);
                }
            }
            return bundle;
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    private static boolean zzm(zzjj zzjj) {
        if (!zzjj.zzapz) {
            ajh.a();
            if (!jp.a()) {
                return false;
            }
        }
        return true;
    }

    public final void destroy() throws RemoteException {
        try {
            this.zzbus.onDestroy();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        if (this.zzbus instanceof zzatm) {
            return ((zzatm) this.zzbus).getInterstitialAdapterInfo();
        }
        String str = "Not a v2 MediationInterstitialAdapter: ";
        String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
        ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final zzlo getVideoController() {
        if (!(this.zzbus instanceof m)) {
            return null;
        }
        try {
            return ((m) this.zzbus).getVideoController();
        } catch (Throwable th) {
            ka.b("", th);
            return null;
        }
    }

    public final IObjectWrapper getView() throws RemoteException {
        if (!(this.zzbus instanceof MediationBannerAdapter)) {
            String str = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) this.zzbus).getBannerView());
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            String str = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        ka.b("Check if adapter is initialized.");
        try {
            return ((MediationRewardedVideoAdAdapter) this.zzbus).isInitialized();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void pause() throws RemoteException {
        try {
            this.zzbus.onPause();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void resume() throws RemoteException {
        try {
            this.zzbus.onResume();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void setImmersiveMode(boolean z) throws RemoteException {
        if (!(this.zzbus instanceof k)) {
            String str = "Not an OnImmersiveModeUpdatedListener: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.d(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            return;
        }
        try {
            ((k) this.zzbus).onImmersiveModeUpdated(z);
        } catch (Throwable th) {
            ka.b("", th);
        }
    }

    public final void showInterstitial() throws RemoteException {
        if (!(this.zzbus instanceof MediationInterstitialAdapter)) {
            String str = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        ka.b("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzbus).showInterstitial();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void showVideo() throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            String str = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        ka.b("Show rewarded video ad from adapter.");
        try {
            ((MediationRewardedVideoAdAdapter) this.zzbus).showVideo();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaic zzaic, List<String> list) throws RemoteException {
        if (!(this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter)) {
            String str = "Not an InitializableMediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        ka.b("Initialize rewarded video adapter.");
        try {
            InitializableMediationRewardedVideoAdAdapter initializableMediationRewardedVideoAdAdapter = (InitializableMediationRewardedVideoAdAdapter) this.zzbus;
            ArrayList arrayList = new ArrayList();
            for (String zza : list) {
                arrayList.add(zza(zza, (zzjj) null, (String) null));
            }
            initializableMediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), new fb(zzaic), arrayList);
        } catch (Throwable th) {
            ka.c("Could not initialize rewarded video adapter.", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, zzaic zzaic, String str2) throws RemoteException {
        Bundle bundle;
        aro aro;
        zzjj zzjj2 = zzjj;
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            String str3 = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        ka.b("Initialize rewarded video adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbus;
            Bundle zza = zza(str2, zzjj2, (String) null);
            if (zzjj2 != null) {
                aro aro2 = new aro(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzjj2.zzaql);
                bundle = zzjj2.zzaqg != null ? zzjj2.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName()) : null;
                aro = aro2;
            } else {
                aro = null;
                bundle = null;
            }
            mediationRewardedVideoAdAdapter.initialize((Context) ObjectWrapper.unwrap(iObjectWrapper), aro, str, new fb(zzaic), zza, bundle);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, zzxt zzxt) throws RemoteException {
        zza(iObjectWrapper, zzjj, str, (String) null, zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, String str2, zzxt zzxt) throws RemoteException {
        zzjj zzjj2 = zzjj;
        if (!(this.zzbus instanceof MediationInterstitialAdapter)) {
            String str3 = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        ka.b("Requesting interstitial ad from adapter.");
        try {
            MediationInterstitialAdapter mediationInterstitialAdapter = (MediationInterstitialAdapter) this.zzbus;
            Bundle bundle = null;
            aro aro = new aro(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzjj2.zzaql);
            if (zzjj2.zzaqg != null) {
                bundle = zzjj2.zzaqg.getBundle(mediationInterstitialAdapter.getClass().getName());
            }
            mediationInterstitialAdapter.requestInterstitialAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new arp(zzxt), zza(str, zzjj2, str2), aro, bundle);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, String str2, zzxt zzxt, zzpl zzpl, List<String> list) throws RemoteException {
        zzjj zzjj2 = zzjj;
        if (!(this.zzbus instanceof MediationNativeAdapter)) {
            String str3 = "Not a MediationNativeAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        try {
            MediationNativeAdapter mediationNativeAdapter = (MediationNativeAdapter) this.zzbus;
            Bundle bundle = null;
            arq arq = new arq(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzpl, list, zzjj2.zzaql);
            if (zzjj2.zzaqg != null) {
                bundle = zzjj2.zzaqg.getBundle(mediationNativeAdapter.getClass().getName());
            }
            Bundle bundle2 = bundle;
            this.zzbut = new arp(zzxt);
            mediationNativeAdapter.requestNativeAd((Context) ObjectWrapper.unwrap(iObjectWrapper), this.zzbut, zza(str, zzjj2, str2), arq, bundle2);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn zzjn, zzjj zzjj, String str, zzxt zzxt) throws RemoteException {
        zza(iObjectWrapper, zzjn, zzjj, str, null, zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn zzjn, zzjj zzjj, String str, String str2, zzxt zzxt) throws RemoteException {
        zzjn zzjn2 = zzjn;
        zzjj zzjj2 = zzjj;
        if (!(this.zzbus instanceof MediationBannerAdapter)) {
            String str3 = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        ka.b("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbus;
            Bundle bundle = null;
            aro aro = new aro(zzjj2.zzapw == -1 ? null : new Date(zzjj2.zzapw), zzjj2.zzapx, zzjj2.zzapy != null ? new HashSet(zzjj2.zzapy) : null, zzjj2.zzaqe, zzm(zzjj), zzjj2.zzaqa, zzjj2.zzaql);
            if (zzjj2.zzaqg != null) {
                bundle = zzjj2.zzaqg.getBundle(mediationBannerAdapter.getClass().getName());
            }
            mediationBannerAdapter.requestBannerAd((Context) ObjectWrapper.unwrap(iObjectWrapper), new arp(zzxt), zza(str, zzjj2, str2), l.a(zzjn2.width, zzjn2.height, zzjn2.zzarb), aro, bundle);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(zzjj zzjj, String str, String str2) throws RemoteException {
        if (!(this.zzbus instanceof MediationRewardedVideoAdAdapter)) {
            String str3 = "Not a MediationRewardedVideoAdAdapter: ";
            String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        ka.b("Requesting rewarded video ad from adapter.");
        try {
            MediationRewardedVideoAdAdapter mediationRewardedVideoAdAdapter = (MediationRewardedVideoAdAdapter) this.zzbus;
            Bundle bundle = null;
            aro aro = new aro(zzjj.zzapw == -1 ? null : new Date(zzjj.zzapw), zzjj.zzapx, zzjj.zzapy != null ? new HashSet(zzjj.zzapy) : null, zzjj.zzaqe, zzm(zzjj), zzjj.zzaqa, zzjj.zzaql);
            if (zzjj.zzaqg != null) {
                bundle = zzjj.zzaqg.getBundle(mediationRewardedVideoAdAdapter.getClass().getName());
            }
            mediationRewardedVideoAdAdapter.loadAd(aro, zza(str, zzjj, str2), bundle);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zzc(zzjj zzjj, String str) throws RemoteException {
        zza(zzjj, str, (String) null);
    }

    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
        try {
            ((j) this.zzbus).a((Context) ObjectWrapper.unwrap(iObjectWrapper));
        } catch (Throwable th) {
            ka.c("Failed", th);
        }
    }

    public final zzxz zzmo() {
        f a = this.zzbut.a();
        if (a instanceof g) {
            return new zzym((g) a);
        }
        return null;
    }

    public final zzyc zzmp() {
        f a = this.zzbut.a();
        if (a instanceof h) {
            return new zzyn((h) a);
        }
        return null;
    }

    public final Bundle zzmq() {
        if (this.zzbus instanceof zzatl) {
            return ((zzatl) this.zzbus).zzmq();
        }
        String str = "Not a v2 MediationBannerAdapter: ";
        String valueOf = String.valueOf(this.zzbus.getClass().getCanonicalName());
        ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        return new Bundle();
    }

    public final Bundle zzmr() {
        return new Bundle();
    }

    public final boolean zzms() {
        return this.zzbus instanceof InitializableMediationRewardedVideoAdAdapter;
    }

    public final zzqs zzmt() {
        com.google.android.gms.ads.formats.f c = this.zzbut.c();
        if (c instanceof ame) {
            return ((ame) c).b();
        }
        return null;
    }

    public final zzyf zzmu() {
        com.google.android.gms.ads.mediation.l b = this.zzbut.b();
        if (b != null) {
            return new zzze(b);
        }
        return null;
    }
}
