package com.google.android.gms.internal.ads;

public final class vu {
    private final byte[] a = new byte[256];
    private int b;
    private int c;

    public vu(byte[] bArr) {
        for (int i = 0; i < 256; i++) {
            this.a[i] = (byte) i;
        }
        byte b2 = 0;
        for (int i2 = 0; i2 < 256; i2++) {
            b2 = (b2 + this.a[i2] + bArr[i2 % bArr.length]) & 255;
            byte b3 = this.a[i2];
            this.a[i2] = this.a[b2];
            this.a[b2] = b3;
        }
        this.b = 0;
        this.c = 0;
    }

    public final void a(byte[] bArr) {
        int i = this.b;
        int i2 = this.c;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) & 255;
            i2 = (i2 + this.a[i]) & 255;
            byte b2 = this.a[i];
            this.a[i] = this.a[i2];
            this.a[i2] = b2;
            bArr[i3] = (byte) (bArr[i3] ^ this.a[(this.a[i] + this.a[i2]) & 255]);
        }
        this.b = i;
        this.c = i2;
    }
}
