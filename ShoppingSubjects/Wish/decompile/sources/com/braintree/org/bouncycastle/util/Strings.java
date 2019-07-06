package com.braintree.org.bouncycastle.util;

import java.io.ByteArrayOutputStream;

public final class Strings {
    public static String fromUTF8ByteArray(byte[] bArr) {
        char c;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i2 < bArr.length) {
            i3++;
            if ((bArr[i2] & 240) == 240) {
                i3++;
                i2 += 4;
            } else {
                i2 = (bArr[i2] & 224) == 224 ? i2 + 3 : (bArr[i2] & 192) == 192 ? i2 + 2 : i2 + 1;
            }
        }
        char[] cArr = new char[i3];
        int i4 = 0;
        while (i < bArr.length) {
            if ((bArr[i] & 240) == 240) {
                int i5 = (((((bArr[i] & 3) << 18) | ((bArr[i + 1] & 63) << 12)) | ((bArr[i + 2] & 63) << 6)) | (bArr[i + 3] & 63)) - 65536;
                c = (char) ((i5 & 1023) | 56320);
                int i6 = i4 + 1;
                cArr[i4] = (char) (55296 | (i5 >> 10));
                i += 4;
                i4 = i6;
            } else if ((bArr[i] & 224) == 224) {
                c = (char) (((bArr[i] & 15) << 12) | ((bArr[i + 1] & 63) << 6) | (bArr[i + 2] & 63));
                i += 3;
            } else if ((bArr[i] & 208) == 208) {
                c = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & 63));
                i += 2;
            } else if ((bArr[i] & 192) == 192) {
                c = (char) (((bArr[i] & 31) << 6) | (bArr[i + 1] & 63));
                i += 2;
            } else {
                c = (char) (bArr[i] & 255);
                i++;
            }
            int i7 = i4 + 1;
            cArr[i4] = c;
            i4 = i7;
        }
        return new String(cArr);
    }

    public static byte[] toUTF8ByteArray(String str) {
        return toUTF8ByteArray(str.toCharArray());
    }

    public static byte[] toUTF8ByteArray(char[] cArr) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 0;
        while (i < cArr.length) {
            char c = cArr[i];
            if (c < 128) {
                byteArrayOutputStream.write(c);
            } else if (c < 2048) {
                byteArrayOutputStream.write((c >> 6) | 192);
                byteArrayOutputStream.write((c & '?') | 128);
            } else if (c < 55296 || c > 57343) {
                byteArrayOutputStream.write((c >> 12) | 224);
                byteArrayOutputStream.write(((c >> 6) & 63) | 128);
                byteArrayOutputStream.write((c & '?') | 128);
            } else {
                i++;
                if (i >= cArr.length) {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
                char c2 = cArr[i];
                if (c > 56319) {
                    throw new IllegalStateException("invalid UTF-16 codepoint");
                }
                int i2 = (((c & 1023) << 10) | (c2 & 1023)) + 0;
                byteArrayOutputStream.write((i2 >> 18) | 240);
                byteArrayOutputStream.write(((i2 >> 12) & 63) | 128);
                byteArrayOutputStream.write(((i2 >> 6) & 63) | 128);
                byteArrayOutputStream.write((i2 & 63) | 128);
            }
            i++;
        }
        return byteArrayOutputStream.toByteArray();
    }
}
