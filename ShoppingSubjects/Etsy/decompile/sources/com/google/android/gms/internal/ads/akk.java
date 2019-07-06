package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class akk implements Callable<T> {
    private final /* synthetic */ akb a;
    private final /* synthetic */ akj b;

    akk(akj akj, akb akb) {
        this.b = akj;
        this.a = akb;
    }

    public final T call() {
        return this.a.a(this.b.d);
    }
}
