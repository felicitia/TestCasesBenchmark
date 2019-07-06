package com.google.android.gms.ads.internal;

import android.os.RemoteException;
import com.google.android.gms.internal.ads.ga;
import com.google.android.gms.internal.ads.gv;
import com.google.android.gms.internal.ads.zzos;
import com.google.android.gms.internal.ads.zzrf;

final class ad implements Runnable {
    private final /* synthetic */ String a;
    private final /* synthetic */ ga b;
    private final /* synthetic */ zzbc c;

    ad(zzbc zzbc, String str, ga gaVar) {
        this.c = zzbc;
        this.a = str;
        this.b = gaVar;
    }

    public final void run() {
        try {
            ((zzrf) this.c.zzvw.zzadi.get(this.a)).zzb((zzos) this.b.C);
        } catch (RemoteException e) {
            gv.d("#007 Could not call remote method.", e);
        }
    }
}
