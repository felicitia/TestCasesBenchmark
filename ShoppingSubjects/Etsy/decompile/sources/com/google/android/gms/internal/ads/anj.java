package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class anj extends zzahf {
    private final /* synthetic */ amt a;

    anj(amt amt) {
        this.a = amt;
    }

    public final void onRewardedVideoAdClosed() throws RemoteException {
        this.a.a.add(new ann(this));
    }

    public final void onRewardedVideoAdFailedToLoad(int i) throws RemoteException {
        this.a.a.add(new anq(this, i));
    }

    public final void onRewardedVideoAdLeftApplication() throws RemoteException {
        this.a.a.add(new anp(this));
    }

    public final void onRewardedVideoAdLoaded() throws RemoteException {
        this.a.a.add(new ank(this));
    }

    public final void onRewardedVideoAdOpened() throws RemoteException {
        this.a.a.add(new anl(this));
    }

    public final void onRewardedVideoCompleted() throws RemoteException {
        this.a.a.add(new anr(this));
    }

    public final void onRewardedVideoStarted() throws RemoteException {
        this.a.a.add(new anm(this));
    }

    public final void zza(zzagu zzagu) throws RemoteException {
        this.a.a.add(new ano(this, zzagu));
    }
}
