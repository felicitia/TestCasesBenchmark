package com.google.android.gms.internal.ads;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.internal.ao;
import com.google.android.gms.ads.internal.bg;
import com.google.android.gms.ads.internal.zzal;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.dynamic.IObjectWrapper;

@bu
public final class zzub extends zzkt {
    private final ams zzbom;
    @Nullable
    private zzal zzbor;
    private final anu zzbpd;
    private final String zzye;
    private boolean zzyu;

    public zzub(Context context, String str, zzxn zzxn, zzang zzang, bg bgVar) {
        this(str, new ams(context, zzxn, zzang, bgVar));
    }

    @VisibleForTesting
    private zzub(String str, ams ams) {
        this.zzye = str;
        this.zzbom = ams;
        this.zzbpd = new anu();
        ao.r().a(ams);
    }

    @VisibleForTesting
    private final void abort() {
        if (this.zzbor == null) {
            this.zzbor = this.zzbom.a(this.zzye);
            this.zzbpd.a(this.zzbor);
        }
    }

    public final void destroy() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.destroy();
        }
    }

    public final String getAdUnitId() {
        throw new IllegalStateException("getAdUnitId not implemented");
    }

    @Nullable
    public final String getMediationAdapterClassName() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.getMediationAdapterClassName();
        }
        return null;
    }

    public final zzlo getVideoController() {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final boolean isLoading() throws RemoteException {
        return this.zzbor != null && this.zzbor.isLoading();
    }

    public final boolean isReady() throws RemoteException {
        return this.zzbor != null && this.zzbor.isReady();
    }

    public final void pause() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.pause();
        }
    }

    public final void resume() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.resume();
        }
    }

    public final void setImmersiveMode(boolean z) {
        this.zzyu = z;
    }

    public final void setManualImpressionsEnabled(boolean z) throws RemoteException {
        abort();
        if (this.zzbor != null) {
            this.zzbor.setManualImpressionsEnabled(z);
        }
    }

    public final void setUserId(String str) {
    }

    public final void showInterstitial() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.setImmersiveMode(this.zzyu);
            this.zzbor.showInterstitial();
            return;
        }
        gv.e("Interstitial ad must be loaded before showInterstitial().");
    }

    public final void stopLoading() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.stopLoading();
        }
    }

    public final void zza(zzaaw zzaaw) throws RemoteException {
        gv.e("setInAppPurchaseListener is deprecated and should not be called.");
    }

    public final void zza(zzabc zzabc, String str) throws RemoteException {
        gv.e("setPlayStorePurchaseParams is deprecated and should not be called.");
    }

    public final void zza(zzahe zzahe) {
        this.zzbpd.f = zzahe;
        if (this.zzbor != null) {
            this.zzbpd.a(this.zzbor);
        }
    }

    public final void zza(zzjn zzjn) throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.zza(zzjn);
        }
    }

    public final void zza(zzke zzke) throws RemoteException {
        this.zzbpd.e = zzke;
        if (this.zzbor != null) {
            this.zzbpd.a(this.zzbor);
        }
    }

    public final void zza(zzkh zzkh) throws RemoteException {
        this.zzbpd.a = zzkh;
        if (this.zzbor != null) {
            this.zzbpd.a(this.zzbor);
        }
    }

    public final void zza(zzkx zzkx) throws RemoteException {
        this.zzbpd.b = zzkx;
        if (this.zzbor != null) {
            this.zzbpd.a(this.zzbor);
        }
    }

    public final void zza(zzla zzla) throws RemoteException {
        this.zzbpd.c = zzla;
        if (this.zzbor != null) {
            this.zzbpd.a(this.zzbor);
        }
    }

    public final void zza(zzlg zzlg) throws RemoteException {
        abort();
        if (this.zzbor != null) {
            this.zzbor.zza(zzlg);
        }
    }

    public final void zza(zzlu zzlu) {
        throw new IllegalStateException("Unused method");
    }

    public final void zza(zzmu zzmu) {
        throw new IllegalStateException("getVideoController not implemented for interstitials");
    }

    public final void zza(zzod zzod) throws RemoteException {
        this.zzbpd.d = zzod;
        if (this.zzbor != null) {
            this.zzbpd.a(this.zzbor);
        }
    }

    public final boolean zzb(zzjj zzjj) throws RemoteException {
        if (!anx.a(zzjj).contains("gw")) {
            abort();
        }
        if (anx.a(zzjj).contains("_skipMediation")) {
            abort();
        }
        if (zzjj.zzaqd != null) {
            abort();
        }
        if (this.zzbor != null) {
            return this.zzbor.zzb(zzjj);
        }
        anx r = ao.r();
        if (anx.a(zzjj).contains("_ad")) {
            r.b(zzjj, this.zzye);
        }
        aoa a = r.a(zzjj, this.zzye);
        if (a != null) {
            if (!a.e) {
                a.a();
                aob.a().e();
            } else {
                aob.a().d();
            }
            this.zzbor = a.a;
            a.c.a(this.zzbpd);
            this.zzbpd.a(this.zzbor);
            return a.f;
        }
        abort();
        aob.a().e();
        return this.zzbor.zzb(zzjj);
    }

    public final Bundle zzba() throws RemoteException {
        return this.zzbor != null ? this.zzbor.zzba() : new Bundle();
    }

    @Nullable
    public final IObjectWrapper zzbj() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzbj();
        }
        return null;
    }

    @Nullable
    public final zzjn zzbk() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzbk();
        }
        return null;
    }

    public final void zzbm() throws RemoteException {
        if (this.zzbor != null) {
            this.zzbor.zzbm();
        } else {
            gv.e("Interstitial ad must be loaded before pingManualTrackingUrl().");
        }
    }

    public final zzla zzbw() {
        throw new IllegalStateException("getIAppEventListener not implemented");
    }

    public final zzkh zzbx() {
        throw new IllegalStateException("getIAdListener not implemented");
    }

    @Nullable
    public final String zzck() throws RemoteException {
        if (this.zzbor != null) {
            return this.zzbor.zzck();
        }
        return null;
    }
}
