package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class aju implements Runnable {
    private final /* synthetic */ zzmj a;

    aju(zzmj zzmj) {
        this.a = zzmj;
    }

    public final void run() {
        if (this.a.zzxs != null) {
            try {
                this.a.zzxs.onAdFailedToLoad(1);
            } catch (RemoteException e) {
                ka.c("Could not notify onAdFailedToLoad event.", e);
            }
        }
    }
}
