package com.google.android.gms.internal.ads;

import android.util.DisplayMetrics;
import android.view.View;
import java.lang.reflect.InvocationTargetException;

public final class aef extends aei {
    private final View d;

    public aef(acy acy, String str, String str2, vy vyVar, int i, int i2, View view) {
        super(acy, str, str2, vyVar, i, 57);
        this.d = view;
    }

    /* access modifiers changed from: protected */
    public final void a() throws IllegalAccessException, InvocationTargetException {
        if (this.d != null) {
            DisplayMetrics displayMetrics = this.a.a().getResources().getDisplayMetrics();
            adh adh = new adh((String) this.c.invoke(null, new Object[]{this.d, displayMetrics}));
            xq xqVar = new xq();
            xqVar.a = adh.a;
            xqVar.b = adh.b;
            xqVar.c = adh.c;
            this.b.M = xqVar;
        }
    }
}
