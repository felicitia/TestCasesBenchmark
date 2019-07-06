package com.google.android.gms.internal.ads;

import java.util.concurrent.Callable;

public final class ads implements Callable {
    private final acy a;
    private final vy b;

    public ads(acy acy, vy vyVar) {
        this.a = acy;
        this.b = vyVar;
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public final Void call() throws Exception {
        if (this.a.l() != null) {
            this.a.l().get();
        }
        vy k = this.a.k();
        if (k != null) {
            try {
                synchronized (this.b) {
                    aar.a(this.b, aar.a((aar) k));
                }
            } catch (zzbfh unused) {
            }
        }
        return null;
    }
}
