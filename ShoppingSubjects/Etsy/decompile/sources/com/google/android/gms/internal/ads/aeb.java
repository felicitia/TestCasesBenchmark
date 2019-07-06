package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class aeb extends aei {
    public aeb(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 51);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.b) {
            acx acx = new acx((String) this.c.invoke(null, new Object[0]));
            this.b.G = acx.a;
            this.b.H = acx.b;
        }
    }
}
