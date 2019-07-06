package com.google.android.gms.internal.measurement;

import android.os.RemoteException;
import android.text.TextUtils;

final class dy implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ zzex c;
    private final /* synthetic */ zzeb d;
    private final /* synthetic */ String e;
    private final /* synthetic */ dq f;

    dy(dq dqVar, boolean z, boolean z2, zzex zzex, zzeb zzeb, String str) {
        this.f = dqVar;
        this.a = z;
        this.b = z2;
        this.c = zzex;
        this.d = zzeb;
        this.e = str;
    }

    public final void run() {
        zzfa d2 = this.f.b;
        if (d2 == null) {
            this.f.r().h_().a("Discarding data. Failed to send event to service");
            return;
        }
        if (this.a) {
            this.f.a(d2, this.b ? null : this.c, this.d);
        } else {
            try {
                if (TextUtils.isEmpty(this.e)) {
                    d2.zza(this.c, this.d);
                } else {
                    d2.zza(this.c, this.e, this.f.r().x());
                }
            } catch (RemoteException e2) {
                this.f.r().h_().a("Failed to send event to the service", e2);
            }
        }
        this.f.J();
    }
}
