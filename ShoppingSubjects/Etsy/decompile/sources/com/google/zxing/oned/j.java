package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.Map;

/* compiled from: EAN8Writer */
public final class j extends q {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_8) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        StringBuilder sb = new StringBuilder("Can only encode EAN_8, but got ");
        sb.append(barcodeFormat);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] a(String str) {
        if (str.length() != 8) {
            StringBuilder sb = new StringBuilder("Requested contents should be 8 digits long, but got ");
            sb.append(str.length());
            throw new IllegalArgumentException(sb.toString());
        }
        boolean[] zArr = new boolean[67];
        int b = b(zArr, 0, p.b, true) + 0;
        int i = 0;
        while (i <= 3) {
            int i2 = i + 1;
            b += b(zArr, b, p.e[Integer.parseInt(str.substring(i, i2))], false);
            i = i2;
        }
        int b2 = b + b(zArr, b, p.c, false);
        int i3 = 4;
        while (i3 <= 7) {
            int i4 = i3 + 1;
            b2 += b(zArr, b2, p.e[Integer.parseInt(str.substring(i3, i4))], true);
            i3 = i4;
        }
        b(zArr, b2, p.b, true);
        return zArr;
    }
}
