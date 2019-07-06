package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.Map;

/* compiled from: EAN13Writer */
public final class i extends q {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.EAN_13) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        StringBuilder sb = new StringBuilder("Can only encode EAN_13, but got ");
        sb.append(barcodeFormat);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] a(String str) {
        if (str.length() != 13) {
            StringBuilder sb = new StringBuilder("Requested contents should be 13 digits long, but got ");
            sb.append(str.length());
            throw new IllegalArgumentException(sb.toString());
        }
        try {
            if (!p.a(str)) {
                throw new IllegalArgumentException("Contents do not pass checksum");
            }
            int i = h.a[Integer.parseInt(str.substring(0, 1))];
            boolean[] zArr = new boolean[95];
            int b = b(zArr, 0, p.b, true) + 0;
            int i2 = 1;
            while (i2 <= 6) {
                int i3 = i2 + 1;
                int parseInt = Integer.parseInt(str.substring(i2, i3));
                if (((i >> (6 - i2)) & 1) == 1) {
                    parseInt += 10;
                }
                b += b(zArr, b, p.f[parseInt], false);
                i2 = i3;
            }
            int b2 = b + b(zArr, b, p.c, false);
            int i4 = 7;
            while (i4 <= 12) {
                int i5 = i4 + 1;
                b2 += b(zArr, b2, p.e[Integer.parseInt(str.substring(i4, i5))], true);
                i4 = i5;
            }
            b(zArr, b2, p.b, true);
            return zArr;
        } catch (FormatException unused) {
            throw new IllegalArgumentException("Illegal contents");
        }
    }
}
