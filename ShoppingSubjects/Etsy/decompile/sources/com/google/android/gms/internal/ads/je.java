package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.ao;

@bu
public final class je {
    private long a;
    private long b = Long.MIN_VALUE;
    private Object c = new Object();

    public je(long j) {
        this.a = j;
    }

    public final boolean a() {
        synchronized (this.c) {
            long elapsedRealtime = ao.l().elapsedRealtime();
            if (this.b + this.a > elapsedRealtime) {
                return false;
            }
            this.b = elapsedRealtime;
            return true;
        }
    }
}
