package com.google.android.gms.internal.ads;

public final class ve {
    private final byte[] a;

    private ve(byte[] bArr, int i, int i2) {
        this.a = new byte[i2];
        System.arraycopy(bArr, 0, this.a, 0, i2);
    }

    public static ve a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        return new ve(bArr, 0, bArr.length);
    }

    public final byte[] a() {
        byte[] bArr = new byte[this.a.length];
        System.arraycopy(this.a, 0, bArr, 0, this.a.length);
        return bArr;
    }
}
