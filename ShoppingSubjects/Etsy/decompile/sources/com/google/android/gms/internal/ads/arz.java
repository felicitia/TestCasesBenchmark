package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class arz implements pn<po, Object> {
    private final /* synthetic */ zzzh a;
    private final /* synthetic */ zzxt b;
    private final /* synthetic */ zzzp c;

    arz(zzzp zzzp, zzzh zzzh, zzxt zzxt) {
        this.c = zzzp;
        this.a = zzzh;
        this.b = zzxt;
    }

    public final void a(String str) {
        try {
            this.a.zzbr(str);
        } catch (RemoteException e) {
            ka.b("", e);
        }
    }
}
