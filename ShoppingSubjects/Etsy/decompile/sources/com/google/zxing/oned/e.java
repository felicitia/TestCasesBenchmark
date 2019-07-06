package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.Map;

/* compiled from: Code39Writer */
public final class e extends n {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_39) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        StringBuilder sb = new StringBuilder("Can only encode CODE_39, but got ");
        sb.append(barcodeFormat);
        throw new IllegalArgumentException(sb.toString());
    }

    public boolean[] a(String str) {
        int length = str.length();
        if (length > 80) {
            StringBuilder sb = new StringBuilder("Requested contents should be less than 80 digits long, but got ");
            sb.append(length);
            throw new IllegalArgumentException(sb.toString());
        }
        int[] iArr = new int[9];
        int i = length + 25;
        int i2 = 0;
        while (i2 < length) {
            int indexOf = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i2));
            if (indexOf < 0) {
                StringBuilder sb2 = new StringBuilder("Bad contents: ");
                sb2.append(str);
                throw new IllegalArgumentException(sb2.toString());
            }
            a(d.a[indexOf], iArr);
            int i3 = i;
            for (int i4 = 0; i4 < 9; i4++) {
                i3 += iArr[i4];
            }
            i2++;
            i = i3;
        }
        boolean[] zArr = new boolean[i];
        a(d.b, iArr);
        int b = b(zArr, 0, iArr, true);
        int[] iArr2 = {1};
        int b2 = b + b(zArr, b, iArr2, false);
        for (int i5 = 0; i5 < length; i5++) {
            a(d.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(str.charAt(i5))], iArr);
            int b3 = b2 + b(zArr, b2, iArr, true);
            b2 = b3 + b(zArr, b3, iArr2, false);
        }
        a(d.b, iArr);
        b(zArr, b2, iArr, true);
        return zArr;
    }

    private static void a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) != 0) {
                i3 = 2;
            }
            iArr[i2] = i3;
        }
    }
}
