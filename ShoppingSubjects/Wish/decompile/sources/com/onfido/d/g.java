package com.onfido.d;

public abstract class g {
    private final int a;
    private final int b;

    protected g(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public g a(int i, int i2, int i3, int i4) {
        throw new UnsupportedOperationException("This luminance source does not support cropping.");
    }

    public abstract byte[] a();

    public abstract byte[] a(int i, byte[] bArr);

    public final int b() {
        return this.a;
    }

    public final int c() {
        return this.b;
    }

    public final String toString() {
        byte[] bArr = new byte[this.a];
        StringBuilder sb = new StringBuilder(this.b * (this.a + 1));
        byte[] bArr2 = bArr;
        for (int i = 0; i < this.b; i++) {
            bArr2 = a(i, bArr2);
            for (int i2 = 0; i2 < this.a; i2++) {
                byte b2 = bArr2[i2] & 255;
                char c = b2 < 64 ? '#' : b2 < 128 ? '+' : b2 < 192 ? '.' : ' ';
                sb.append(c);
            }
            sb.append(10);
        }
        return sb.toString();
    }
}
