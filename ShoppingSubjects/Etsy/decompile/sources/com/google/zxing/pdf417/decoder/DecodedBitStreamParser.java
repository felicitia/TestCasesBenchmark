package com.google.zxing.pdf417.decoder;

import java.math.BigInteger;
import java.nio.charset.Charset;

final class DecodedBitStreamParser {
    private static final char[] a = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] b = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final Charset c = Charset.forName("ISO-8859-1");
    private static final BigInteger[] d;

    private enum Mode {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        d = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        d[1] = valueOf;
        for (int i = 2; i < d.length; i++) {
            d[i] = d[i - 1].multiply(valueOf);
        }
    }
}
