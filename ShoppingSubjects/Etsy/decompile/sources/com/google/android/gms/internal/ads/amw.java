package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class amw implements ant {
    private final /* synthetic */ int a;

    amw(amu amu, int i) {
        this.a = i;
    }

    public final void a(anu anu) throws RemoteException {
        if (anu.a != null) {
            anu.a.onAdFailedToLoad(this.a);
        }
    }
}
