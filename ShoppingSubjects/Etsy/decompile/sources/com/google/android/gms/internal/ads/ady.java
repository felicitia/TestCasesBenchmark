package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class ady extends aei {
    private final boolean d;

    public ady(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 61);
        this.d = acy.j();
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        long longValue = ((Long) this.c.invoke(null, new Object[]{this.a.a(), Boolean.valueOf(this.d)})).longValue();
        synchronized (this.b) {
            this.b.P = Long.valueOf(longValue);
        }
    }
}
