package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class adq extends aei {
    private long d;

    public adq(acy acy, String str, String str2, vy vyVar, long j, int i, int i2) {
        super(acy, str, str2, vyVar, i, 25);
        this.d = j;
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.c.invoke(null, new Object[0])).longValue();
        synchronized (this.b) {
            this.b.W = Long.valueOf(longValue);
            if (this.d != 0) {
                this.b.j = Long.valueOf(longValue - this.d);
                this.b.m = Long.valueOf(this.d);
            }
        }
    }
}
