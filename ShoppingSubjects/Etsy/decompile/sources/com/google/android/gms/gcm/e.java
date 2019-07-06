package com.google.android.gms.gcm;

import android.support.annotation.NonNull;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

final class e implements ThreadFactory {
    private final AtomicInteger a = new AtomicInteger(1);

    e(GcmTaskService gcmTaskService) {
    }

    public final Thread newThread(@NonNull Runnable runnable) {
        int andIncrement = this.a.getAndIncrement();
        StringBuilder sb = new StringBuilder(20);
        sb.append("gcm-task#");
        sb.append(andIncrement);
        Thread thread = new Thread(runnable, sb.toString());
        thread.setPriority(4);
        return thread;
    }
}
