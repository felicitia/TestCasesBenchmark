package com.google.android.gms.internal.icing;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

public final class aj {
    static final Charset a = Charset.forName("UTF-8");
    public static final byte[] b;
    private static final Charset c = Charset.forName("ISO-8859-1");
    private static final ByteBuffer d;
    private static final q e;

    static {
        byte[] bArr = new byte[0];
        b = bArr;
        d = ByteBuffer.wrap(bArr);
        byte[] bArr2 = b;
        e = q.a(bArr2, 0, bArr2.length, false);
    }

    static int a(int i, byte[] bArr, int i2, int i3) {
        int i4 = i;
        for (int i5 = i2; i5 < i2 + i3; i5++) {
            i4 = (i4 * 31) + bArr[i5];
        }
        return i4;
    }

    public static int a(long j) {
        return (int) (j ^ (j >>> 32));
    }

    public static int a(boolean z) {
        return z ? 1231 : 1237;
    }

    static <T> T a(T t) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException();
    }

    static Object a(Object obj, Object obj2) {
        return ((bl) obj).i().a((bl) obj2).b();
    }

    static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    static boolean a(bl blVar) {
        return false;
    }

    public static boolean a(byte[] bArr) {
        return da.a(bArr);
    }

    public static String b(byte[] bArr) {
        return new String(bArr, a);
    }

    public static int c(byte[] bArr) {
        int length = bArr.length;
        int a2 = a(length, bArr, 0, length);
        if (a2 == 0) {
            return 1;
        }
        return a2;
    }
}
