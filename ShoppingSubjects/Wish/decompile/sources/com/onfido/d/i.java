package com.onfido.d;

public final class i extends g {
    private final byte[] a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;

    public i(int i, int i2, int[] iArr) {
        super(i, i2);
        this.b = i;
        this.c = i2;
        this.d = 0;
        this.e = 0;
        int i3 = i * i2;
        this.a = new byte[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            int i5 = iArr[i4];
            int i6 = (i5 >> 16) & 255;
            int i7 = (i5 >> 7) & 510;
            this.a[i4] = (byte) (((i6 + i7) + (i5 & 255)) / 4);
        }
    }

    private i(byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6) {
        super(i5, i6);
        if (i5 + i3 > i || i6 + i4 > i2) {
            throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
        }
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.d = i3;
        this.e = i4;
    }

    public g a(int i, int i2, int i3, int i4) {
        i iVar = new i(this.a, this.b, this.c, this.d + i, this.e + i2, i3, i4);
        return iVar;
    }

    public byte[] a() {
        int b2 = b();
        int c2 = c();
        if (b2 == this.b && c2 == this.c) {
            return this.a;
        }
        int i = b2 * c2;
        byte[] bArr = new byte[i];
        int i2 = (this.e * this.b) + this.d;
        if (b2 == this.b) {
            System.arraycopy(this.a, i2, bArr, 0, i);
            return bArr;
        }
        for (int i3 = 0; i3 < c2; i3++) {
            System.arraycopy(this.a, i2, bArr, i3 * b2, b2);
            i2 += this.b;
        }
        return bArr;
    }

    public byte[] a(int i, byte[] bArr) {
        if (i < 0 || i >= c()) {
            StringBuilder sb = new StringBuilder("Requested row is outside the image: ");
            sb.append(i);
            throw new IllegalArgumentException(sb.toString());
        }
        int b2 = b();
        if (bArr == null || bArr.length < b2) {
            bArr = new byte[b2];
        }
        System.arraycopy(this.a, ((i + this.e) * this.b) + this.d, bArr, 0, b2);
        return bArr;
    }
}
