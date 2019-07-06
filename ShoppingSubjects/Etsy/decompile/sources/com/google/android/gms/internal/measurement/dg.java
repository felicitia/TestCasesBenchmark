package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class dg implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ cs b;

    dg(cs csVar, AtomicReference atomicReference) {
        this.b = csVar;
        this.a = atomicReference;
    }

    public final void run() {
        synchronized (this.a) {
            try {
                this.a.set(Double.valueOf(this.b.t().c(this.b.g().C(), ak.T)));
                this.a.notify();
            } catch (Throwable th) {
                this.a.notify();
                throw th;
            }
        }
    }
}
