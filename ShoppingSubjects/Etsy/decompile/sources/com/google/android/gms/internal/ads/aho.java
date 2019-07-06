package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class aho implements a {
    private final Executor a;

    public aho(Handler handler) {
        this.a = new aip(this, handler);
    }

    public final void a(amf<?> amf, arb<?> arb) {
        a(amf, arb, null);
    }

    public final void a(amf<?> amf, arb<?> arb, Runnable runnable) {
        amf.k();
        amf.b("post-response");
        this.a.execute(new ajf(this, amf, arb, runnable));
    }

    public final void a(amf<?> amf, zzae zzae) {
        amf.b("post-error");
        this.a.execute(new ajf(this, amf, arb.a(zzae), null));
    }
}
