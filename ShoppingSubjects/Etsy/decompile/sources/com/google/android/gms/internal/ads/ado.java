package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class ado extends aei {
    public ado(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 5);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        this.b.d = Long.valueOf(-1);
        this.b.e = Long.valueOf(-1);
        int[] iArr = (int[]) this.c.invoke(null, new Object[]{this.a.a()});
        synchronized (this.b) {
            this.b.d = Long.valueOf((long) iArr[0]);
            this.b.e = Long.valueOf((long) iArr[1]);
            if (iArr[2] != Integer.MIN_VALUE) {
                this.b.N = Long.valueOf((long) iArr[2]);
            }
        }
    }
}
