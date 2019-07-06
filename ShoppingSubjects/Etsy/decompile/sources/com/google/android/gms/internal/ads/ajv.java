package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class ajv implements Runnable {
    private final /* synthetic */ zzmo a;

    ajv(zzmo zzmo) {
        this.a = zzmo;
    }

    public final void run() {
        if (this.a.zzatl != null) {
            try {
                this.a.zzatl.onRewardedVideoAdFailedToLoad(1);
            } catch (RemoteException e) {
                ka.c("Could not notify onRewardedVideoAdFailedToLoad event.", e);
            }
        }
    }
}
