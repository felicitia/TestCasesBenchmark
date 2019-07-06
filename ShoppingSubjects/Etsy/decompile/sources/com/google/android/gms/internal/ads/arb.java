package com.google.android.gms.internal.ads;

public final class arb<T> {
    public final T a;
    public final acb b;
    public final zzae c;
    public boolean d;

    private arb(zzae zzae) {
        this.d = false;
        this.a = null;
        this.b = null;
        this.c = zzae;
    }

    private arb(T t, acb acb) {
        this.d = false;
        this.a = t;
        this.b = acb;
        this.c = null;
    }

    public static <T> arb<T> a(zzae zzae) {
        return new arb<>(zzae);
    }

    public static <T> arb<T> a(T t, acb acb) {
        return new arb<>(t, acb);
    }
}
