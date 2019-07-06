package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class ajs extends zzkl {
    final /* synthetic */ zzmf a;

    private ajs(zzmf zzmf) {
        this.a = zzmf;
    }

    public final String getMediationAdapterClassName() throws RemoteException {
        return null;
    }

    public final boolean isLoading() throws RemoteException {
        return false;
    }

    public final void zza(zzjj zzjj, int i) throws RemoteException {
        ka.c("This app is using a lightweight version of the Google Mobile Ads SDK that requires the latest Google Play services to be installed, but Google Play services is either missing or out of date.");
        jp.a.post(new ajt(this));
    }

    public final String zzck() throws RemoteException {
        return null;
    }

    public final void zzd(zzjj zzjj) throws RemoteException {
        zza(zzjj, 1);
    }
}
