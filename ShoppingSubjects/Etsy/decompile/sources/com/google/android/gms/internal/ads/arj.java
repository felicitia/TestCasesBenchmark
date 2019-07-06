package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

final class arj implements Callable<arf> {
    private final /* synthetic */ arc a;
    private final /* synthetic */ ari b;

    arj(ari ari, arc arc) {
        this.b = ari;
        this.a = arc;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final arf call() throws Exception {
        synchronized (this.b.i) {
            if (this.b.j) {
                return null;
            }
            return this.a.a(this.b.f, this.b.g);
        }
    }
}
