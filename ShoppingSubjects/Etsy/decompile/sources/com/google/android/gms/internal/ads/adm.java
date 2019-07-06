package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class adm extends aei {
    private static volatile String d;
    private static final Object e = new Object();

    public adm(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 29);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        this.b.o = "E";
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = (String) this.c.invoke(null, new Object[]{this.a.a()});
                }
            }
        }
        synchronized (this.b) {
            this.b.o = abj.a(d.getBytes(), true);
        }
    }
}
