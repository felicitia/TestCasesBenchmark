package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class adp extends aei {
    private static volatile Long d;
    private static final Object e = new Object();

    public adp(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 44);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = (Long) this.c.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.b) {
            this.b.A = d;
        }
    }
}
