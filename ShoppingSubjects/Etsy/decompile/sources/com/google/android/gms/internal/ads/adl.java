package com.google.android.gms.internal.ads;

import android.provider.Settings.SettingNotFoundException;
import java.lang.reflect.InvocationTargetException;

public final class adl extends aei {
    public adl(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        super(acy, str, str2, vyVar, i, 49);
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        this.b.F = Integer.valueOf(2);
        try {
            boolean booleanValue = ((Boolean) this.c.invoke(null, new Object[]{this.a.a()})).booleanValue();
            this.b.F = Integer.valueOf(booleanValue ? 1 : 0);
        } catch (InvocationTargetException e) {
            if (!(e.getTargetException() instanceof SettingNotFoundException)) {
                throw e;
            }
        }
    }
}
