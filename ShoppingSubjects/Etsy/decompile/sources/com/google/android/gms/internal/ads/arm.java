package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class arm implements Runnable {
    private final /* synthetic */ arf a;

    arm(arl arl, arf arf) {
        this.a = arf;
    }

    public final void run() {
        try {
            this.a.c.destroy();
        } catch (RemoteException e) {
            gv.c("Could not destroy mediation adapter.", e);
        }
    }
}
