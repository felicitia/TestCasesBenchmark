package com.crittercism.internal;

import java.util.Locale;

public final class bh {
    public static final bh a = new bh();
    private volatile int b = 1;
    private final long c = System.currentTimeMillis();

    private bh() {
    }

    private synchronized int b() {
        int i;
        i = this.b;
        this.b = i + 1;
        return i;
    }

    public final String a() {
        return String.format(Locale.US, "%d.%d.%09d", new Object[]{Integer.valueOf(1), Long.valueOf(this.c), Integer.valueOf(b())});
    }
}
