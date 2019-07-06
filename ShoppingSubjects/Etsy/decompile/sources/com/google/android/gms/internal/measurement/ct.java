package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class ct implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ cs b;

    ct(cs csVar, AtomicReference atomicReference) {
        this.b = csVar;
        this.a = atomicReference;
    }

    public final void run() {
        synchronized (this.a) {
            try {
                this.a.set(Boolean.valueOf(this.b.t().h(this.b.g().C())));
                this.a.notify();
            } catch (Throwable th) {
                this.a.notify();
                throw th;
            }
        }
    }
}
