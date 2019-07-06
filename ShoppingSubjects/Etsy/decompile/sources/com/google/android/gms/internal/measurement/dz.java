package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;

final class dz implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ zzef c;
    private final /* synthetic */ zzeb d;
    private final /* synthetic */ zzef e;
    private final /* synthetic */ dq f;

    dz(dq dqVar, boolean z, boolean z2, zzef zzef, zzeb zzeb, zzef zzef2) {
        this.f = dqVar;
        this.a = z;
        this.b = z2;
        this.c = zzef;
        this.d = zzeb;
        this.e = zzef2;
    }

    public final void run() {
        zzfa d2 = this.f.b;
        if (d2 == null) {
            this.f.r().h_().a("Discarding data. Failed to send conditional user property to service");
            return;
        }
        if (this.a) {
            this.f.a(d2, this.b ? null : this.c, this.d);
        } else {
            try {
                if (TextUtils.isEmpty(this.e.packageName)) {
                    d2.zza(this.c, this.d);
                } else {
                    d2.zzb(this.c);
                }
            } catch (RemoteException e2) {
                this.f.r().h_().a("Failed to send conditional user property to the service", e2);
            }
        }
        this.f.J();
    }
}
