package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class df implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ cs b;

    df(cs csVar, AtomicReference atomicReference) {
        this.b = csVar;
        this.a = atomicReference;
    }

    public final void run() {
        synchronized (this.a) {
            try {
                this.a.set(Integer.valueOf(this.b.t().b(this.b.g().C(), ak.S)));
                this.a.notify();
            } catch (Throwable th) {
                this.a.notify();
                throw th;
            }
        }
    }
}
