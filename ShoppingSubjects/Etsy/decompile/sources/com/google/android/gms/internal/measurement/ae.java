package com.google.android.gms.internal.measurement;

import android.os.Handler;
import com.google.android.gms.common.internal.Preconditions;

abstract class ae {
    private static volatile Handler b;
    private final cq a;
    private final Runnable c;
    /* access modifiers changed from: private */
    public volatile long d;

    ae(cq cqVar) {
        Preconditions.checkNotNull(cqVar);
        this.a = cqVar;
        this.c = new af(this, cqVar);
    }

    private final Handler d() {
        Handler handler;
        if (b != null) {
            return b;
        }
        synchronized (ae.class) {
            if (b == null) {
                b = new Handler(this.a.n().getMainLooper());
            }
            handler = b;
        }
        return handler;
    }

    public abstract void a();

    public final void a(long j) {
        c();
        if (j >= 0) {
            this.d = this.a.m().currentTimeMillis();
            if (!d().postDelayed(this.c, j)) {
                this.a.r().h_().a("Failed to schedule delayed post. time", Long.valueOf(j));
            }
        }
    }

    public final boolean b() {
        return this.d != 0;
    }

    /* access modifiers changed from: 0000 */
    public final void c() {
        this.d = 0;
        d().removeCallbacks(this.c);
    }
}
