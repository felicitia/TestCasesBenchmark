package com.google.android.gms.internal.ads;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public abstract class aei implements Callable {
    protected final acy a;
    protected final vy b;
    protected Method c;
    private final String d = getClass().getSimpleName();
    private final String e;
    private final String f;
    private final int g;
    private final int h;

    public aei(acy acy, String str, String str2, vy vyVar, int i, int i2) {
        this.a = acy;
        this.e = str;
        this.f = str2;
        this.b = vyVar;
        this.g = i;
        this.h = i2;
    }

    /* access modifiers changed from: protected */
    public abstract void a() throws IllegalAccessException, InvocationTargetException;

    /* renamed from: b */
    public Void call() throws Exception {
        try {
            long nanoTime = System.nanoTime();
            this.c = this.a.a(this.e, this.f);
            if (this.c == null) {
                return null;
            }
            a();
            ace h2 = this.a.h();
            if (!(h2 == null || this.g == Integer.MIN_VALUE)) {
                h2.a(this.h, this.g, (System.nanoTime() - nanoTime) / 1000);
            }
            return null;
        } catch (IllegalAccessException | InvocationTargetException unused) {
        }
    }
}
