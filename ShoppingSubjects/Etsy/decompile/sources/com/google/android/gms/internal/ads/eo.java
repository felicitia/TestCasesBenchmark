package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.dynamic.ObjectWrapper;

final class eo implements Runnable {
    private final /* synthetic */ zzxq a;
    private final /* synthetic */ zzjj b;
    private final /* synthetic */ zzahv c;
    private final /* synthetic */ em d;

    eo(em emVar, zzxq zzxq, zzjj zzjj, zzahv zzahv) {
        this.d = emVar;
        this.a = zzxq;
        this.b = zzjj;
        this.c = zzahv;
    }

    public final void run() {
        try {
            this.a.zza(ObjectWrapper.wrap(this.d.c), this.b, (String) null, (zzaic) this.c, this.d.g);
        } catch (RemoteException e) {
            String str = "Fail to initialize adapter ";
            String valueOf = String.valueOf(this.d.a);
            gv.c(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
            this.d.a(this.d.a, 0);
        }
    }
}
