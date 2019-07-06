package com.google.android.gms.internal.ads;

import android.os.RemoteException;

final /* synthetic */ class amp implements Runnable {
    private final amo a;
    private final aml b;
    private final le c;
    private final zzsg d;

    amp(amo amo, aml aml, le leVar, zzsg zzsg) {
        this.a = amo;
        this.b = aml;
        this.c = leVar;
        this.d = zzsg;
    }

    public final void run() {
        amo amo = this.a;
        aml aml = this.b;
        le leVar = this.c;
        try {
            leVar.b(aml.a().zza(this.d));
        } catch (RemoteException e) {
            gv.b("Unable to obtain a cache service instance.", e);
            leVar.a(e);
            amo.a.a();
        }
    }
}
