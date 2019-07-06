package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class ajt implements Runnable {
    private final /* synthetic */ ajs a;

    ajt(ajs ajs) {
        this.a = ajs;
    }

    public final void run() {
        if (this.a.a.zzxs != null) {
            try {
                this.a.a.zzxs.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                ka.c("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
