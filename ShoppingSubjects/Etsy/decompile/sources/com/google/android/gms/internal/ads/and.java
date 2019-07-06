package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class and extends zzlb {
    private final /* synthetic */ amt a;

    and(amt amt) {
        this.a = amt;
    }

    public final void onAppEvent(String str, String str2) throws RemoteException {
        this.a.a.add(new ane(this, str, str2));
    }
}
