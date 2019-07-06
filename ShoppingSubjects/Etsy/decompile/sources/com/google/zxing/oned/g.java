package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.b;
import java.util.Map;

/* compiled from: Code93Writer */
public class g extends n {
    public b a(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat == BarcodeFormat.CODE_93) {
            return super.a(str, barcodeFormat, i, i2, map);
        }
        StringBuilder sb = new StringBuilder("Can only encode CODE_93, but got ");
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
        boolean[] zArr = new boolean[(((str.length() + 2 + 2) * 9) + 1)];
        a(f.a[47], iArr);
        int a = a(zArr, 0, iArr, true);
        for (int i = 0; i < length; i++) {
            a(f.a["0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(i))], iArr);
            a += a(zArr, a, iArr, true);
        }
        int a2 = a(str, 20);
        a(f.a[a2], iArr);
        int a3 = a + a(zArr, a, iArr, true);
        StringBuilder sb2 = new StringBuilder();
        sb2.append(str);
        sb2.append("0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".charAt(a2));
        a(f.a[a(sb2.toString(), 15)], iArr);
        int a4 = a3 + a(zArr, a3, iArr, true);
        a(f.a[47], iArr);
        zArr[a4 + a(zArr, a4, iArr, true)] = true;
        return zArr;
    }

    private static void a(int i, int[] iArr) {
        for (int i2 = 0; i2 < 9; i2++) {
            int i3 = 1;
            if (((1 << (8 - i2)) & i) == 0) {
                i3 = 0;
            }
            iArr[i2] = i3;
        }
    }

    protected static int a(boolean[] zArr, int i, int[] iArr, boolean z) {
        int length = iArr.length;
        int i2 = i;
        int i3 = 0;
        while (i3 < length) {
            int i4 = i2 + 1;
            zArr[i2] = iArr[i3] != 0;
            i3++;
            i2 = i4;
        }
        return 9;
    }

    private static int a(String str, int i) {
        int i2 = 0;
        int i3 = 1;
        for (int length = str.length() - 1; length >= 0; length--) {
            i2 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%abcd*".indexOf(str.charAt(length)) * i3;
            i3++;
            if (i3 > i) {
                i3 = 1;
            }
        }
        return i2 % 47;
    }
}
