package com.google.android.gms.internal.ads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class ft implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    ft(fq fqVar) {
    }

    public final Thread newThread(Runnable runnable) {
        int andIncrement = this.a.getAndIncrement();
        StringBuilder sb = new StringBuilder(42);
        sb.append("AdWorker(SCION_TASK_EXECUTOR) #");
        sb.append(andIncrement);
        return new Thread(runnable, sb.toString());
    }
}
