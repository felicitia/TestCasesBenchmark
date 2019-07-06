package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.Map;

/* compiled from: UPCEWriter */
public final class s extends q {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.UPC_E) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        StringBuilder sb = new StringBuilder("Can only encode UPC_E, but got ");
        sb.append(barcodeFormat);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] a(String str) {
        if (str.length() != 8) {
            StringBuilder sb = new StringBuilder("Requested contents should be 8 digits long, but got ");
            sb.append(str.length());
            throw new IllegalArgumentException(sb.toString());
        }
        int i = r.a[Integer.parseInt(str.substring(7, 8))];
        boolean[] zArr = new boolean[51];
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
        b(zArr, b, p.d, false);
        return zArr;
    }
}
