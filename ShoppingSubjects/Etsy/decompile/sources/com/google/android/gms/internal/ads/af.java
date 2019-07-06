package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.n;
import java.util.concurrent.CountDownLatch;

final class af implements Runnable {
    private final /* synthetic */ CountDownLatch a;
    private final /* synthetic */ ae b;

    af(ae aeVar, CountDownLatch countDownLatch) {
        this.b = aeVar;
        this.a = countDownLatch;
    }

    public final void run() {
        synchronized (this.b.d) {
            this.b.m = n.a(this.b.l, this.b.g, this.a);
        }
    }
}
