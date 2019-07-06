package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class adw extends aei {
    public adw(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 3);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        synchronized (this.b) {
            acm acm = new acm((String) this.c.invoke(null, new Object[]{this.a.a()}));
            synchronized (this.b) {
                this.b.c = Long.valueOf(acm.a);
                this.b.O = Long.valueOf(acm.b);
            }
        }
    }
}
