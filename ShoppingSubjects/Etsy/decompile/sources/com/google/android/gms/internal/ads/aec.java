package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class aec extends aei {
    private final zzdi d;
    private long e;

    public aec(acy acy, String str, String str2, vy vyVar, int i, int i2, zzdi zzdi) {
        super(acy, str, str2, vyVar, i, 53);
        this.d = zzdi;
        if (zzdi != null) {
            this.e = zzdi.zzap();
        }
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        if (this.d != null) {
            this.b.I = (Long) this.c.invoke(null, new Object[]{Long.valueOf(this.e)});
        }
    }
}
