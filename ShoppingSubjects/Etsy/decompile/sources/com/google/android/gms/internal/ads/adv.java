package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class adv extends aei {
    private static volatile String d;
    private static final Object e = new Object();

    public adv(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 1);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        this.b.a = "E";
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = (String) this.c.invoke(null, new Object[0]);
                }
            }
        }
        synchronized (this.b) {
            this.b.a = d;
        }
    }
}
