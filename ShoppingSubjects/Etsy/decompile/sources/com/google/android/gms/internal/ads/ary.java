package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final class ary implements pn<Object, Object> {
    private final /* synthetic */ zzzf a;
    private final /* synthetic */ zzxt b;

    ary(zzzp zzzp, zzzf zzzf, zzxt zzxt) {
        this.a = zzzf;
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
