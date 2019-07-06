package com.google.zxing.pdf417.encoder;

import java.lang.reflect.Array;

/* compiled from: BarcodeMatrix */
public final class a {
    private final b[] a;
    private int b;
    private final int c;
    private final int d;

    a(int i, int i2) {
        this.a = new b[i];
        int length = this.a.length;
        for (int i3 = 0; i3 < length; i3++) {
            this.a[i3] = new b(((i2 + 4) * 17) + 1);
        }
        this.d = i2 * 17;
        this.c = i;
        this.b = -1;
    }

    /* access modifiers changed from: 0000 */
    public void a() {
        this.b++;
    }

    /* access modifiers changed from: 0000 */
    public b b() {
        return this.a[this.b];
    }

    public byte[][] a(int i, int i2) {
        byte[][] bArr = (byte[][]) Array.newInstance(byte.class, new int[]{this.c * i2, this.d * i});
        int i3 = this.c * i2;
        for (int i4 = 0; i4 < i3; i4++) {
            bArr[(i3 - i4) - 1] = this.a[i4 / i2].a(i);
        }
        return bArr;
    }
}
