package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.Map;

/* compiled from: ITFWriter */
public final class l extends n {
    private static final int[] a = {1, 1, 1, 1};
    private static final int[] b = {3, 1, 1};

    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.ITF) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        StringBuilder sb = new StringBuilder("Can only encode ITF, but got ");
        sb.append(barcodeFormat);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] a(String str) {
        int length = str.length();
        if (length % 2 != 0) {
            throw new IllegalArgumentException("The length of the input should be even");
        } else if (length > 80) {
            StringBuilder sb = new StringBuilder("Requested contents should be less than 80 digits long, but got ");
            sb.append(length);
            throw new IllegalArgumentException(sb.toString());
        } else {
            boolean[] zArr = new boolean[(9 + (length * 9))];
            int b2 = b(zArr, 0, a, true);
            for (int i = 0; i < length; i += 2) {
                int digit = Character.digit(str.charAt(i), 10);
                int digit2 = Character.digit(str.charAt(i + 1), 10);
                int[] iArr = new int[18];
                for (int i2 = 0; i2 < 5; i2++) {
                    int i3 = 2 * i2;
                    iArr[i3] = k.a[digit][i2];
                    iArr[i3 + 1] = k.a[digit2][i2];
                }
                b2 += b(zArr, b2, iArr, true);
            }
            b(zArr, b2, b, true);
            return zArr;
        }
    }
}
