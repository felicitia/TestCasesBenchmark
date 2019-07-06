package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class amu extends zzki {
    private final /* synthetic */ amt a;

    amu(amt amt) {
        this.a = amt;
    }

    public final void onAdClicked() throws RemoteException {
        this.a.a.add(new anc(this));
    }

    public final void onAdClosed() throws RemoteException {
        this.a.a.add(new amv(this));
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.a.a.add(new amw(this, i));
        gv.a("Pooled interstitial failed to load.");
    }

    public final void onAdImpression() throws RemoteException {
        this.a.a.add(new anb(this));
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.a.a.add(new amx(this));
    }

    public final void onAdLoaded() throws RemoteException {
        this.a.a.add(new amy(this));
        gv.a("Pooled interstitial loaded.");
    }

    public final void onAdOpened() throws RemoteException {
        this.a.a.add(new amz(this));
    }
}
