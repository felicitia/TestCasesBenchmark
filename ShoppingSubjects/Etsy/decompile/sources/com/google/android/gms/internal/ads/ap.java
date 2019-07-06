package com.google.android.gms.internal.ads;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

final class ap implements Runnable {
    private final /* synthetic */ AtomicInteger a;
    private final /* synthetic */ int b;
    private final /* synthetic */ le c;
    private final /* synthetic */ List d;

    ap(AtomicInteger atomicInteger, int i, le leVar, List list) {
        this.a = atomicInteger;
        this.b = i;
        this.c = leVar;
        this.d = list;
    }

    public final void run() {
        if (this.a.incrementAndGet() >= this.b) {
            try {
                this.c.b(ai.b(this.d));
            } catch (InterruptedException | ExecutionException e) {
                gv.c("Unable to convert list of futures to a future of list", e);
            }
        }
    }
}
