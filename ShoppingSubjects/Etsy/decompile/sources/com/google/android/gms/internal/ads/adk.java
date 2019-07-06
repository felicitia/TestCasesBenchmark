package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class adk extends aei {
    private final Activity d;
    private final View e;

    public adk(acy acy, String str, String str2, vy vyVar, int i, int i2, View view, Activity activity) {
        super(acy, str, str2, vyVar, i, 62);
        this.e = view;
        this.d = activity;
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        if (this.e != null) {
            boolean booleanValue = ((Boolean) ajh.f().a(akl.bF)).booleanValue();
            Object[] objArr = (Object[]) this.c.invoke(null, new Object[]{this.e, this.d, Boolean.valueOf(booleanValue)});
            synchronized (this.b) {
                this.b.Q = Long.valueOf(((Long) objArr[0]).longValue());
                this.b.R = Long.valueOf(((Long) objArr[1]).longValue());
                if (booleanValue) {
                    this.b.S = (String) objArr[2];
                }
            }
        }
    }
}
