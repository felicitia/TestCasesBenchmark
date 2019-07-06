package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.f;
import com.google.zxing.qrcode.a.c;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Map;

/* compiled from: QRCodeWriter */
public final class b implements f {
    public com.google.zxing.common.b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (str.isEmpty()) {
            throw new IllegalArgumentException("Found empty contents");
        } else if (barcodeFormat != BarcodeFormat.QR_CODE) {
            StringBuilder sb = new StringBuilder("Can only encode QR_CODE, but got ");
            sb.append(barcodeFormat);
            throw new IllegalArgumentException(sb.toString());
        } else if (i < 0 || i2 < 0) {
            StringBuilder sb2 = new StringBuilder("Requested dimensions are too small: ");
            sb2.append(i);
            sb2.append('x');
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        } else {
            ErrorCorrectionLevel errorCorrectionLevel = ErrorCorrectionLevel.L;
            int i3 = 4;
            if (map != null) {
                if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                    errorCorrectionLevel = ErrorCorrectionLevel.valueOf(map.get(EncodeHintType.ERROR_CORRECTION).toString());
                }
                if (map.containsKey(EncodeHintType.MARGIN)) {
                    i3 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
                }
            }
            return a(c.a(str, errorCorrectionLevel, map), i, i2, i3);
        }
    }

    private static com.google.zxing.common.b a(com.google.zxing.qrcode.a.f fVar, int i, int i2, int i3) {
        com.google.zxing.qrcode.a.b a = fVar.a();
        if (a == null) {
            throw new IllegalStateException();
        }
        int b = a.b();
        int a2 = a.a();
        int i4 = i3 << 1;
        int i5 = b + i4;
        int i6 = i4 + a2;
        int max = Math.max(i, i5);
        int max2 = Math.max(i2, i6);
        int min = Math.min(max / i5, max2 / i6);
        int i7 = (max - (b * min)) / 2;
        int i8 = (max2 - (a2 * min)) / 2;
        com.google.zxing.common.b bVar = new com.google.zxing.common.b(max, max2);
        int i9 = 0;
        while (i9 < a2) {
            int i10 = 0;
            int i11 = i7;
            while (i10 < b) {
                if (a.a(i10, i9) == 1) {
                    bVar.a(i11, i8, min, min);
                }
                i10++;
                i11 += min;
            }
            i9++;
            i8 += min;
        }
        return bVar;
    }
}
