package com.google.android.gms.internal.ads;

import com.etsy.android.lib.convos.Draft;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

final class ada implements ThreadFactory {
    private final ThreadFactory a = Executors.defaultThreadFactory();

    ada() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread newThread = this.a.newThread(runnable);
        newThread.setName(String.valueOf(newThread.getName()).concat(Draft.IMAGE_DELIMITER));
        return newThread;
    }
}
