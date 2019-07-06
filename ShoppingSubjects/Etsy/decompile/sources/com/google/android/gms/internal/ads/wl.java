package com.google.android.gms.internal.ads;

import java.io.IOException;

public abstract class wl {
    private static volatile boolean f = true;
    int a;
    int b;
    wo c;
    private int d;
    private boolean e;

    private wl() {
        this.b = 100;
        this.d = Integer.MAX_VALUE;
        this.e = false;
    }

    public static long a(long j) {
        return (j >>> 1) ^ (-(j & 1));
    }

    static wl a(byte[] bArr, int i, int i2, boolean z) {
        wn wnVar = new wn(bArr, i, i2, z);
        try {
            wnVar.c(i2);
            return wnVar;
        } catch (zzbbu e2) {
            throw new IllegalArgumentException(e2);
        }
    }

    public static int f(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract int a() throws IOException;

    public abstract void a(int i) throws zzbbu;

    public abstract double b() throws IOException;

    public abstract boolean b(int i) throws IOException;

    public abstract float c() throws IOException;

    public abstract int c(int i) throws zzbbu;

    public abstract long d() throws IOException;

    public abstract void d(int i);

    public abstract long e() throws IOException;

    public abstract void e(int i) throws IOException;

    public abstract int f() throws IOException;

    public abstract long g() throws IOException;

    public abstract int h() throws IOException;

    public abstract boolean i() throws IOException;

    public abstract String j() throws IOException;

    public abstract String k() throws IOException;

    public abstract zzbah l() throws IOException;

    public abstract int m() throws IOException;

    public abstract int n() throws IOException;

    public abstract int o() throws IOException;

    public abstract long p() throws IOException;

    public abstract int q() throws IOException;

    public abstract long r() throws IOException;

    /* access modifiers changed from: 0000 */
    public abstract long s() throws IOException;

    public abstract boolean t() throws IOException;

    public abstract int u();
}
