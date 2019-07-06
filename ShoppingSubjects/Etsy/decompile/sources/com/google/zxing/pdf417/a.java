package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import com.google.zxing.f;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.c;
import com.google.zxing.pdf417.encoder.d;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

/* compiled from: PDF417Writer */
public final class a implements f {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.PDF_417) {
            StringBuilder sb = new StringBuilder("Can only encode PDF_417, but got ");
            sb.append(barcodeFormat);
            throw new IllegalArgumentException(sb.toString());
        }
        d dVar = new d();
        int i3 = 30;
        int i4 = 2;
        if (map != null) {
            if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                dVar.a(Boolean.valueOf(map.get(EncodeHintType.PDF417_COMPACT).toString()).booleanValue());
            }
            if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                dVar.a(Compaction.valueOf(map.get(EncodeHintType.PDF417_COMPACTION).toString()));
            }
            if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                c cVar = (c) map.get(EncodeHintType.PDF417_DIMENSIONS);
                dVar.a(cVar.b(), cVar.a(), cVar.d(), cVar.c());
            }
            if (map.containsKey(EncodeHintType.MARGIN)) {
                i3 = Integer.parseInt(map.get(EncodeHintType.MARGIN).toString());
            }
            if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                i4 = Integer.parseInt(map.get(EncodeHintType.ERROR_CORRECTION).toString());
            }
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                dVar.a(Charset.forName(map.get(EncodeHintType.CHARACTER_SET).toString()));
            }
        }
        int i5 = i4;
        return a(dVar, str, i5, i, i2, i3);
    }

    private static b a(d dVar, String str, int i, int i2, int i3, int i4) throws WriterException {
        boolean z;
        dVar.a(str, i);
        byte[][] a = dVar.a().a(1, 4);
        if ((i3 > i2) ^ (a[0].length < a.length)) {
            a = a(a);
            z = true;
        } else {
            z = false;
        }
        int length = i2 / a[0].length;
        int length2 = i3 / a.length;
        if (length >= length2) {
            length = length2;
        }
        if (length <= 1) {
            return a(a, i4);
        }
        byte[][] a2 = dVar.a().a(length, length << 2);
        if (z) {
            a2 = a(a2);
        }
        return a(a2, i4);
    }

    private static b a(byte[][] bArr, int i) {
        int i2 = 2 * i;
        b bVar = new b(bArr[0].length + i2, bArr.length + i2);
        bVar.a();
        int c = (bVar.c() - i) - 1;
        int i3 = 0;
        while (i3 < bArr.length) {
            for (int i4 = 0; i4 < bArr[0].length; i4++) {
                if (bArr[i3][i4] == 1) {
                    bVar.b(i4 + i, c);
                }
            }
            i3++;
            c--;
        }
        return bVar;
    }

    private static byte[][] a(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(byte.class, new int[]{bArr[0].length, bArr.length});
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }
}
