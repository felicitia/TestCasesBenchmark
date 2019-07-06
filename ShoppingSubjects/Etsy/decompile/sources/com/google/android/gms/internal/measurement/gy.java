package com.google.android.gms.internal.measurement;

public abstract class gy {
    private static volatile boolean d = false;
    int a;
    private int b;
    private boolean c;

    private gy() {
        this.a = 100;
        this.b = Integer.MAX_VALUE;
        this.c = false;
    }

    static gy a(byte[] bArr, int i, int i2, boolean z) {
        ha haVar = new ha(bArr, i, i2);
        try {
            haVar.a(i2);
            return haVar;
        } catch (zzzy e) {
            throw new IllegalArgumentException(e);
        }
    }

    public abstract int a();
}
