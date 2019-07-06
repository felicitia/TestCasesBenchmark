package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.ads.AdRequest.ErrorCode;

final class aru implements Runnable {
    private final /* synthetic */ ErrorCode a;
    private final /* synthetic */ arr b;

    aru(arr arr, ErrorCode errorCode) {
        this.b = arr;
        this.a = errorCode;
    }

    public final void run() {
        try {
            this.b.a.onAdFailedToLoad(arv.a(this.a));
        } catch (RemoteException e) {
            ka.d("#007 Could not call remote method.", e);
        }
    }
}
