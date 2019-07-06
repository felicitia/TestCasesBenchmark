package com.firebase.jobdispatcher;

/* compiled from: RetryStrategy */
public final class t {
    public static final t a = new t(1, 30, 3600);
    public static final t b = new t(2, 30, 3600);
    private final int c;
    private final int d;
    private final int e;

    /* compiled from: RetryStrategy */
    static final class a {
        private final ValidationEnforcer a;

        a(ValidationEnforcer validationEnforcer) {
            this.a = validationEnforcer;
        }
    }

    t(int i, int i2, int i3) {
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public int a() {
        return this.c;
    }

    public int b() {
        return this.d;
    }

    public int c() {
        return this.e;
    }
}
