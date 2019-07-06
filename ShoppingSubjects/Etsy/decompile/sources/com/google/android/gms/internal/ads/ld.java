package com.google.android.gms.internal.ads;

@bu
public final class ld<T> extends le<T> {
    private final T a;

    private ld(T t) {
        this.a = t;
    }

    public static <T> ld<T> a(T t) {
        return new ld<>(t);
    }

    public final void a() {
        b(this.a);
    }
}
