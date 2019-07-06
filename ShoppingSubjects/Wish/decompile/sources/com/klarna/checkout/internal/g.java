package com.klarna.checkout.internal;

public abstract class g {
    private static int b;
    public final int a;

    protected g() {
        int i = b + 1;
        b = i;
        this.a = i;
    }

    public abstract void a(String str);
}
