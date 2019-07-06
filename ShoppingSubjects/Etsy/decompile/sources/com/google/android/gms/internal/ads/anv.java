package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.ao;
import java.util.Random;

final class anv extends zzki {
    private final zzkh a;

    anv(zzkh zzkh) {
        this.a = zzkh;
    }

    public final void onAdClicked() throws RemoteException {
        this.a.onAdClicked();
    }

    public final void onAdClosed() throws RemoteException {
        if (aod.a()) {
            int intValue = ((Integer) ajh.f().a(akl.bb)).intValue();
            int intValue2 = ((Integer) ajh.f().a(akl.bc)).intValue();
            if (intValue <= 0 || intValue2 < 0) {
                ao.r().a();
            } else {
                hd.a.postDelayed(anw.a, (long) (intValue + new Random().nextInt(intValue2 + 1)));
            }
        }
        this.a.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.a.onAdFailedToLoad(i);
    }

    public final void onAdImpression() throws RemoteException {
        this.a.onAdImpression();
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.a.onAdLeftApplication();
    }

    public final void onAdLoaded() throws RemoteException {
        this.a.onAdLoaded();
    }

    public final void onAdOpened() throws RemoteException {
        this.a.onAdOpened();
    }
}
