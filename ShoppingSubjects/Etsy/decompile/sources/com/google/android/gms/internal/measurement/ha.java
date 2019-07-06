package com.google.android.gms.internal.measurement;

final class ha extends gy {
    private final byte[] b;
    private final boolean c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;

    private ha(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.h = Integer.MAX_VALUE;
        this.b = bArr;
        this.d = i2 + i;
        this.f = i;
        this.g = this.f;
        this.c = z;
    }

    private final void b() {
        this.d += this.e;
        int i = this.d - this.g;
        if (i > this.h) {
            this.e = i - this.h;
            this.d -= this.e;
            return;
        }
        this.e = 0;
    }

    public final int a() {
        return this.f - this.g;
    }

    public final int a(int i) throws zzzy {
        if (i < 0) {
            throw zzzy.zzuc();
        }
        int a = i + a();
        int i2 = this.h;
        if (a > i2) {
            throw zzzy.zzub();
        }
        this.h = a;
        b();
        return i2;
    }
}
