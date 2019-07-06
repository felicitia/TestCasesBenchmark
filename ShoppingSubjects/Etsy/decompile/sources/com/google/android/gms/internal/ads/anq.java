package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class anq implements ant {
    private final /* synthetic */ int a;

    anq(anj anj, int i) {
        this.a = i;
    }

    public final void a(anu anu) throws RemoteException {
        if (anu.f != null) {
            anu.f.onRewardedVideoAdFailedToLoad(this.a);
        }
    }
}
