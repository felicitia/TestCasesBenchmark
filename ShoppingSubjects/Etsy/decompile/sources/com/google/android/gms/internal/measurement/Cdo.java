package com.google.android.gms.internal.measurement;

import android.os.Bundle;

/* renamed from: com.google.android.gms.internal.measurement.do reason: invalid class name */
final class Cdo implements Runnable {
    private final /* synthetic */ boolean a;
    private final /* synthetic */ dm b;
    private final /* synthetic */ dm c;
    private final /* synthetic */ dn d;

    Cdo(dn dnVar, boolean z, dm dmVar, dm dmVar2) {
        this.d = dnVar;
        this.a = z;
        this.b = dmVar;
        this.c = dmVar2;
    }

    public final void run() {
        if (this.a && this.d.a != null) {
            this.d.a(this.d.a);
        }
        if (this.b == null || this.b.c != this.c.c || !fg.b(this.b.b, this.c.b) || !fg.b(this.b.a, this.c.a)) {
            Bundle bundle = new Bundle();
            dn.a(this.c, bundle, true);
            if (this.b != null) {
                if (this.b.a != null) {
                    bundle.putString("_pn", this.b.a);
                }
                bundle.putString("_pc", this.b.b);
                bundle.putLong("_pi", this.b.c);
            }
            this.d.f().b("auto", "_vs", bundle);
        }
        this.d.a = this.c;
        this.d.h().a(this.c);
    }
}
