package com.google.android.gms.internal.measurement;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class hb {
    static final Charset a = Charset.forName("UTF-8");
    public static final byte[] b;
    private static final Charset c = Charset.forName("ISO-8859-1");
    private static final ByteBuffer d;
    private static final gy e;

    static {
        byte[] bArr = new byte[0];
        b = bArr;
        d = ByteBuffer.wrap(bArr);
        byte[] bArr2 = b;
        e = gy.a(bArr2, 0, bArr2.length, false);
    }

    static int a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }
}
