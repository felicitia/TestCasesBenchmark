package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class ane implements ant {
    private final /* synthetic */ String a;
    private final /* synthetic */ String b;

    ane(and and, String str, String str2) {
        this.a = str;
        this.b = str2;
    }

    public final void a(anu anu) throws RemoteException {
        if (anu.c != null) {
            anu.c.onAppEvent(this.a, this.b);
        }
    }
}
