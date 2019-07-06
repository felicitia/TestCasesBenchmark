package com.paypal.android.sdk.onetouch.core.metadata;

public final class l {
    private static String a;
    private static long b;
    private static long c;

    public static synchronized void a() {
        synchronized (l.class) {
            a = ai.b(Boolean.TRUE.booleanValue());
            b = System.currentTimeMillis();
        }
    }

    public static synchronized void a(long j) {
        synchronized (l.class) {
            c = j;
        }
    }

    public static synchronized String b() {
        String str;
        synchronized (l.class) {
            if (a == null) {
                a();
            }
            str = a;
        }
        return str;
    }

    public static synchronized boolean c() {
        boolean z;
        synchronized (l.class) {
            z = System.currentTimeMillis() - d() <= c;
        }
        return z;
    }

    private static synchronized long d() {
        long j;
        synchronized (l.class) {
            if (b == 0) {
                a();
            }
            j = b;
        }
        return j;
    }
}
