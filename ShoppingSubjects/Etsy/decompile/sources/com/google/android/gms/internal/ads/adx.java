package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class adx extends aei {
    private List<Long> d = null;

    public adx(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 31);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        this.b.p = Long.valueOf(-1);
        this.b.q = Long.valueOf(-1);
        if (this.d == null) {
            this.d = (List) this.c.invoke(null, new Object[]{this.a.a()});
        }
        if (this.d != null && this.d.size() == 2) {
            synchronized (this.b) {
                this.b.p = Long.valueOf(((Long) this.d.get(0)).longValue());
                this.b.q = Long.valueOf(((Long) this.d.get(1)).longValue());
            }
        }
    }
}
