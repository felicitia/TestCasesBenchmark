package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class de implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ cs b;

    de(cs csVar, AtomicReference atomicReference) {
        this.b = csVar;
        this.a = atomicReference;
    }

    public final void run() {
        synchronized (this.a) {
            try {
                this.a.set(Long.valueOf(this.b.t().a(this.b.g().C(), ak.R)));
                this.a.notify();
            } catch (Throwable th) {
                this.a.notify();
                throw th;
            }
        }
    }
}
