package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class ans implements Runnable {
    private final /* synthetic */ ant a;
    private final /* synthetic */ anu b;

    ans(amt amt, ant ant, anu anu) {
        this.a = ant;
        this.b = anu;
    }

    public final void run() {
        try {
            this.a.a(this.b);
        } catch (RemoteException e) {
            gv.c("Could not propagate interstitial ad event.", e);
        }
    }
}
