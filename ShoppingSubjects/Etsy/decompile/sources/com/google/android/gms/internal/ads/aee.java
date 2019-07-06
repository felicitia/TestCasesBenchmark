package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class aee extends aei {
    public aee(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 48);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        vy vyVar;
        Integer valueOf;
        this.b.E = Integer.valueOf(2);
        boolean booleanValue = ((Boolean) this.c.invoke(null, new Object[]{this.a.a()})).booleanValue();
        synchronized (this.b) {
            if (booleanValue) {
                try {
                    vyVar = this.b;
                    valueOf = Integer.valueOf(1);
                } catch (Throwable th) {
                    throw th;
                }
            } else {
                vyVar = this.b;
                valueOf = Integer.valueOf(0);
            }
            vyVar.E = valueOf;
        }
    }
}
