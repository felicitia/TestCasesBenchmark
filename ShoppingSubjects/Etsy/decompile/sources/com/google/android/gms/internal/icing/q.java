package com.google.android.gms.internal.icing;

public abstract class q {
    private static volatile boolean d = false;
    private int a;
    private int b;
    private boolean c;

    private q() {
        this.a = 100;
        this.b = Integer.MAX_VALUE;
        this.c = false;
    }

    static q a(byte[] bArr, int i, int i2, boolean z) {
        s sVar = new s(bArr, 0, i2, false);
        try {
            sVar.a(i2);
            return sVar;
        } catch (zzcs e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int a();

    public abstract int a(int i) throws zzcs;
}
