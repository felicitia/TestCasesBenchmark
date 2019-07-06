package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;

public final class aea extends aei {
    private final StackTraceElement[] d;

    public aea(acy acy, String str, String str2, vy vyVar, int i, int i2, StackTraceElement[] stackTraceElementArr) {
        super(acy, str, str2, vyVar, i, 45);
        this.d = stackTraceElementArr;
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        if (this.d != null) {
            acw acw = new acw((String) this.c.invoke(null, new Object[]{this.d}));
            synchronized (this.b) {
                this.b.B = acw.a;
                if (acw.b.booleanValue()) {
                    this.b.J = Integer.valueOf(acw.c.booleanValue() ^ true ? 1 : 0);
                }
            }
        }
    }
}
