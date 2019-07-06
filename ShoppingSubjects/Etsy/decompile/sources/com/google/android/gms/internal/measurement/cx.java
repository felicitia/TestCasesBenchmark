package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class cx implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ cs b;

    cx(cs csVar, AtomicReference atomicReference) {
        this.b = csVar;
        this.a = atomicReference;
    }

    public final void run() {
        this.b.h().a(this.a);
    }
}
