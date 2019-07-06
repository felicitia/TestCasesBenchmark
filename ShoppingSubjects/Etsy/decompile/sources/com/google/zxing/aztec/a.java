package com.google.zxing.aztec;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.aztec.a.c;
import com.google.zxing.common.b;
import com.google.zxing.f;
import java.nio.charset.Charset;
import java.util.Map;

/* compiled from: AztecWriter */
public final class a implements f {
    private static final Charset a = Charset.forName("ISO-8859-1");

    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) {
        Charset charset = a;
        int i3 = 33;
        int i4 = 0;
        if (map != null) {
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                charset = Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString());
            }
            if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                i3 = Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString());
            }
            if (map.containsKey(EncodeHintType.AZTEC_LAYERS)) {
                i4 = Integer.parseInt(map.get(EncodeHintType.AZTEC_LAYERS).toString());
            }
        }
        return a(str, barcodeFormat, i, i2, charset, i3, i4);
    }

    private static b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Charset charset, int i3, int i4) {
        if (barcodeFormat == BarcodeFormat.AZTEC) {
            return a(c.a(str.getBytes(charset), i3, i4), i, i2);
        }
        StringBuilder sb = new StringBuilder("Can only encode AZTEC, but got ");
        sb.append(barcodeFormat);
        throw new IllegalArgumentException(sb.toString());
    }

    private static b a(com.google.zxing.aztec.a.a aVar, int i, int i2) {
        b a2 = aVar.a();
        if (a2 == null) {
            throw new IllegalStateException();
        }
        int b = a2.b();
        int c = a2.c();
        int max = Math.max(i, b);
        int max2 = Math.max(i2, c);
        int min = Math.min(max / b, max2 / c);
        int i3 = (max - (b * min)) / 2;
        int i4 = (max2 - (c * min)) / 2;
        b bVar = new b(max, max2);
        int i5 = 0;
        while (i5 < c) {
            int i6 = 0;
            int i7 = i3;
            while (i6 < b) {
                if (a2.a(i6, i5)) {
                    bVar.a(i7, i4, min, min);
                }
                i6++;
                i7 += min;
            }
            i5++;
            i4 += min;
        }
        return bVar;
    }
}
