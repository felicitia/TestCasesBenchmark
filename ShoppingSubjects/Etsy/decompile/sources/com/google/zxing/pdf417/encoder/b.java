package com.google.zxing.pdf417.encoder;

/* compiled from: BarcodeRow */
final class b {
    private final byte[] a;
    private int b = 0;

    b(int i) {
        this.a = new byte[i];
    }

    private void a(int i, boolean z) {
        this.a[i] = z ? (byte) 1 : 0;
    }

    /* access modifiers changed from: 0000 */
    public void a(boolean z, int i) {
        for (int i2 = 0; i2 < i; i2++) {
            int i3 = this.b;
            this.b = i3 + 1;
            a(i3, z);
        }
    }

    /* access modifiers changed from: 0000 */
    public byte[] a(int i) {
        byte[] bArr = new byte[(this.a.length * i)];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            bArr[i2] = this.a[i2 / i];
        }
        return bArr;
    }
}
