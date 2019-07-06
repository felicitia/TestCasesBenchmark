package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.os.Bundle;
import android.os.RemoteException;
import com.google.ads.a;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.b;
import com.google.ads.mediation.e;
import com.google.android.gms.ads.l;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@bu
public final class zzyp<NETWORK_EXTRAS extends e, SERVER_PARAMETERS extends MediationServerParameters> extends zzxr {
    private final b<NETWORK_EXTRAS, SERVER_PARAMETERS> zzbvb;
    private final NETWORK_EXTRAS zzbvc;

    public zzyp(b<NETWORK_EXTRAS, SERVER_PARAMETERS> bVar, NETWORK_EXTRAS network_extras) {
        this.zzbvb = bVar;
        this.zzbvc = network_extras;
    }

    private final SERVER_PARAMETERS zza(String str, int i, String str2) throws RemoteException {
        HashMap hashMap;
        if (str != null) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                hashMap = new HashMap(jSONObject.length());
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str3 = (String) keys.next();
                    hashMap.put(str3, jSONObject.getString(str3));
                }
            } finally {
                ka.b("", th);
                RemoteException remoteException = new RemoteException();
            }
        } else {
            hashMap = new HashMap(0);
        }
        Class serverParametersType = this.zzbvb.getServerParametersType();
        if (serverParametersType == null) {
            return null;
        }
        SERVER_PARAMETERS server_parameters = (MediationServerParameters) serverParametersType.newInstance();
        server_parameters.a(hashMap);
        return server_parameters;
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
            this.zzbvb.destroy();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final Bundle getInterstitialAdapterInfo() {
        return new Bundle();
    }

    public final zzlo getVideoController() {
        return null;
    }

    public final IObjectWrapper getView() throws RemoteException {
        if (!(this.zzbvb instanceof MediationBannerAdapter)) {
            String str = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        try {
            return ObjectWrapper.wrap(((MediationBannerAdapter) this.zzbvb).getBannerView());
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final boolean isInitialized() {
        return true;
    }

    public final void pause() throws RemoteException {
        throw new RemoteException();
    }

    public final void resume() throws RemoteException {
        throw new RemoteException();
    }

    public final void setImmersiveMode(boolean z) {
    }

    public final void showInterstitial() throws RemoteException {
        if (!(this.zzbvb instanceof MediationInterstitialAdapter)) {
            String str = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            throw new RemoteException();
        }
        ka.b("Showing interstitial from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzbvb).showInterstitial();
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void showVideo() {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzaic zzaic, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, zzaic zzaic, String str2) throws RemoteException {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, zzxt zzxt) throws RemoteException {
        zza(iObjectWrapper, zzjj, str, (String) null, zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, String str2, zzxt zzxt) throws RemoteException {
        if (!(this.zzbvb instanceof MediationInterstitialAdapter)) {
            String str3 = "Not a MediationInterstitialAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        ka.b("Requesting interstitial ad from adapter.");
        try {
            ((MediationInterstitialAdapter) this.zzbvb).requestInterstitialAd(new arr(zzxt), (Activity) ObjectWrapper.unwrap(iObjectWrapper), zza(str, zzjj.zzaqa, str2), arv.a(zzjj, zzm(zzjj)), this.zzbvc);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjj zzjj, String str, String str2, zzxt zzxt, zzpl zzpl, List<String> list) {
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn zzjn, zzjj zzjj, String str, zzxt zzxt) throws RemoteException {
        zza(iObjectWrapper, zzjn, zzjj, str, null, zzxt);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzjn zzjn, zzjj zzjj, String str, String str2, zzxt zzxt) throws RemoteException {
        a aVar;
        if (!(this.zzbvb instanceof MediationBannerAdapter)) {
            String str3 = "Not a MediationBannerAdapter: ";
            String valueOf = String.valueOf(this.zzbvb.getClass().getCanonicalName());
            ka.e(valueOf.length() != 0 ? str3.concat(valueOf) : new String(str3));
            throw new RemoteException();
        }
        ka.b("Requesting banner ad from adapter.");
        try {
            MediationBannerAdapter mediationBannerAdapter = (MediationBannerAdapter) this.zzbvb;
            arr arr = new arr(zzxt);
            Activity activity = (Activity) ObjectWrapper.unwrap(iObjectWrapper);
            MediationServerParameters zza = zza(str, zzjj.zzaqa, str2);
            int i = 0;
            a[] aVarArr = {a.a, a.b, a.c, a.d, a.e, a.f};
            while (true) {
                if (i < 6) {
                    if (aVarArr[i].a() == zzjn.width && aVarArr[i].b() == zzjn.height) {
                        aVar = aVarArr[i];
                        break;
                    }
                    i++;
                } else {
                    aVar = new a(l.a(zzjn.width, zzjn.height, zzjn.zzarb));
                    break;
                }
            }
            mediationBannerAdapter.requestBannerAd(arr, activity, zza, aVar, arv.a(zzjj, zzm(zzjj)), this.zzbvc);
        } catch (Throwable th) {
            ka.b("", th);
            throw new RemoteException();
        }
    }

    public final void zza(zzjj zzjj, String str, String str2) {
    }

    public final void zzc(zzjj zzjj, String str) {
    }

    public final void zzi(IObjectWrapper iObjectWrapper) throws RemoteException {
    }

    public final zzxz zzmo() {
        return null;
    }

    public final zzyc zzmp() {
        return null;
    }

    public final Bundle zzmq() {
        return new Bundle();
    }

    public final Bundle zzmr() {
        return new Bundle();
    }

    public final boolean zzms() {
        return false;
    }

    public final zzqs zzmt() {
        return null;
    }

    public final zzyf zzmu() {
        return null;
    }
}
