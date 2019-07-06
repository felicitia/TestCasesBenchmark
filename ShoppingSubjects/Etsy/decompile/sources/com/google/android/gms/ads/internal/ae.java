package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzqs;
import com.google.android.gms.internal.ads.zzrf;

final class ae implements Runnable {
    private final /* synthetic */ zzqs a;
    private final /* synthetic */ zzbc b;

    ae(zzbc zzbc, zzqs zzqs) {
        this.b = zzbc;
        this.a = zzqs;
    }

    public final void run() {
        try {
            ((zzrf) this.b.zzvw.zzadi.get(this.a.getCustomTemplateId())).zzb(this.a);
        } catch (RemoteException e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
