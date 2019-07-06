package com.google.android.gms.internal.measurement;

import java.util.concurrent.atomic.AtomicReference;

final class cv implements Runnable {
    private final /* synthetic */ AtomicReference a;
    private final /* synthetic */ boolean b;
    private final /* synthetic */ cs c;

    cv(cs csVar, AtomicReference atomicReference, boolean z) {
        this.c = csVar;
        this.a = atomicReference;
        this.b = z;
    }

    public final void run() {
        this.c.h().a(this.a, this.b);
    }
}
