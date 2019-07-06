package com.google.zxing.qrcode.a;

import com.google.zxing.WriterException;
import com.google.zxing.common.a;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/* compiled from: MatrixUtil */
final class e {
    private static final int[][] a = {new int[]{1, 1, 1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 1, 1, 1, 0, 1}, new int[]{1, 0, 0, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1, 1, 1}};
    private static final int[][] b = {new int[]{1, 1, 1, 1, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 0, 1, 0, 1}, new int[]{1, 0, 0, 0, 1}, new int[]{1, 1, 1, 1, 1}};
    private static final int[][] c = {new int[]{-1, -1, -1, -1, -1, -1, -1}, new int[]{6, 18, -1, -1, -1, -1, -1}, new int[]{6, 22, -1, -1, -1, -1, -1}, new int[]{6, 26, -1, -1, -1, -1, -1}, new int[]{6, 30, -1, -1, -1, -1, -1}, new int[]{6, 34, -1, -1, -1, -1, -1}, new int[]{6, 22, 38, -1, -1, -1, -1}, new int[]{6, 24, 42, -1, -1, -1, -1}, new int[]{6, 26, 46, -1, -1, -1, -1}, new int[]{6, 28, 50, -1, -1, -1, -1}, new int[]{6, 30, 54, -1, -1, -1, -1}, new int[]{6, 32, 58, -1, -1, -1, -1}, new int[]{6, 34, 62, -1, -1, -1, -1}, new int[]{6, 26, 46, 66, -1, -1, -1}, new int[]{6, 26, 48, 70, -1, -1, -1}, new int[]{6, 26, 50, 74, -1, -1, -1}, new int[]{6, 30, 54, 78, -1, -1, -1}, new int[]{6, 30, 56, 82, -1, -1, -1}, new int[]{6, 30, 58, 86, -1, -1, -1}, new int[]{6, 34, 62, 90, -1, -1, -1}, new int[]{6, 28, 50, 72, 94, -1, -1}, new int[]{6, 26, 50, 74, 98, -1, -1}, new int[]{6, 30, 54, 78, 102, -1, -1}, new int[]{6, 28, 54, 80, 106, -1, -1}, new int[]{6, 32, 58, 84, 110, -1, -1}, new int[]{6, 30, 58, 86, 114, -1, -1}, new int[]{6, 34, 62, 90, 118, -1, -1}, new int[]{6, 26, 50, 74, 98, 122, -1}, new int[]{6, 30, 54, 78, 102, 126, -1}, new int[]{6, 26, 52, 78, 104, 130, -1}, new int[]{6, 30, 56, 82, 108, 134, -1}, new int[]{6, 34, 60, 86, 112, 138, -1}, new int[]{6, 30, 58, 86, 114, 142, -1}, new int[]{6, 34, 62, 90, 118, 146, -1}, new int[]{6, 30, 54, 78, 102, 126, 150}, new int[]{6, 24, 50, 76, 102, 128, 154}, new int[]{6, 28, 54, 80, 106, 132, 158}, new int[]{6, 32, 58, 84, 110, 136, 162}, new int[]{6, 26, 54, 82, 110, 138, 166}, new int[]{6, 30, 58, 86, 114, 142, 170}};
    private static final int[][] d = {new int[]{8, 0}, new int[]{8, 1}, new int[]{8, 2}, new int[]{8, 3}, new int[]{8, 4}, new int[]{8, 5}, new int[]{8, 7}, new int[]{8, 8}, new int[]{7, 8}, new int[]{5, 8}, new int[]{4, 8}, new int[]{3, 8}, new int[]{2, 8}, new int[]{1, 8}, new int[]{0, 8}};

    private static boolean b(int i) {
        return i == -1;
    }

    static void a(b bVar) {
        bVar.a(-1);
    }

    static void a(a aVar, ErrorCorrectionLevel errorCorrectionLevel, com.google.zxing.qrcode.decoder.a aVar2, int i, b bVar) throws WriterException {
        a(bVar);
        a(aVar2, bVar);
        a(errorCorrectionLevel, i, bVar);
        b(aVar2, bVar);
        a(aVar, i, bVar);
    }

    static void a(com.google.zxing.qrcode.decoder.a aVar, b bVar) throws WriterException {
        d(bVar);
        c(bVar);
        c(aVar, bVar);
        b(bVar);
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, b bVar) throws WriterException {
        a aVar = new a();
        a(errorCorrectionLevel, i, aVar);
        for (int i2 = 0; i2 < aVar.a(); i2++) {
            boolean a2 = aVar.a((aVar.a() - 1) - i2);
            bVar.a(d[i2][0], d[i2][1], a2);
            if (i2 < 8) {
                bVar.a((bVar.b() - i2) - 1, 8, a2);
            } else {
                bVar.a(8, (bVar.a() - 7) + (i2 - 8), a2);
            }
        }
    }

    static void b(com.google.zxing.qrcode.decoder.a aVar, b bVar) throws WriterException {
        if (aVar.a() >= 7) {
            a aVar2 = new a();
            a(aVar, aVar2);
            int i = 17;
            int i2 = 0;
            while (i2 < 6) {
                int i3 = i;
                for (int i4 = 0; i4 < 3; i4++) {
                    boolean a2 = aVar2.a(i3);
                    i3--;
                    bVar.a(i2, (bVar.a() - 11) + i4, a2);
                    bVar.a((bVar.a() - 11) + i4, i2, a2);
                }
                i2++;
                i = i3;
            }
        }
    }

    static void a(a aVar, int i, b bVar) throws WriterException {
        boolean z;
        int b2 = bVar.b() - 1;
        int a2 = bVar.a() - 1;
        int i2 = -1;
        int i3 = 0;
        while (b2 > 0) {
            if (b2 == 6) {
                b2--;
            }
            while (a2 >= 0 && a2 < bVar.a()) {
                int i4 = i3;
                for (int i5 = 0; i5 < 2; i5++) {
                    int i6 = b2 - i5;
                    if (b((int) bVar.a(i6, a2))) {
                        if (i4 < aVar.a()) {
                            z = aVar.a(i4);
                            i4++;
                        } else {
                            z = false;
                        }
                        if (i != -1 && d.a(i, i6, a2)) {
                            z = !z;
                        }
                        bVar.a(i6, a2, z);
                    }
                }
                a2 += i2;
                i3 = i4;
            }
            i2 = -i2;
            a2 += i2;
            b2 -= 2;
        }
        if (i3 != aVar.a()) {
            StringBuilder sb = new StringBuilder("Not all bits consumed: ");
            sb.append(i3);
            sb.append('/');
            sb.append(aVar.a());
            throw new WriterException(sb.toString());
        }
    }

    static int a(int i) {
        return 32 - Integer.numberOfLeadingZeros(i);
    }

    static int a(int i, int i2) {
        if (i2 == 0) {
            throw new IllegalArgumentException("0 polynomial");
        }
        int a2 = a(i2);
        int i3 = i << (a2 - 1);
        while (a(i3) >= a2) {
            i3 ^= i2 << (a(i3) - a2);
        }
        return i3;
    }

    static void a(ErrorCorrectionLevel errorCorrectionLevel, int i, a aVar) throws WriterException {
        if (!f.b(i)) {
            throw new WriterException("Invalid mask pattern");
        }
        int bits = (errorCorrectionLevel.getBits() << 3) | i;
        aVar.a(bits, 5);
        aVar.a(a(bits, 1335), 10);
        a aVar2 = new a();
        aVar2.a(21522, 15);
        aVar.b(aVar2);
        if (aVar.a() != 15) {
            StringBuilder sb = new StringBuilder("should not happen but we got: ");
            sb.append(aVar.a());
            throw new WriterException(sb.toString());
        }
    }

    static void a(com.google.zxing.qrcode.decoder.a aVar, a aVar2) throws WriterException {
        aVar2.a(aVar.a(), 6);
        aVar2.a(a(aVar.a(), 7973), 12);
        if (aVar2.a() != 18) {
            StringBuilder sb = new StringBuilder("should not happen but we got: ");
            sb.append(aVar2.a());
            throw new WriterException(sb.toString());
        }
    }

    private static void b(b bVar) {
        int i = 8;
        while (i < bVar.b() - 8) {
            int i2 = i + 1;
            int i3 = i2 % 2;
            if (b((int) bVar.a(i, 6))) {
                bVar.a(i, 6, i3);
            }
            if (b((int) bVar.a(6, i))) {
                bVar.a(6, i, i3);
            }
            i = i2;
        }
    }

    private static void c(b bVar) throws WriterException {
        if (bVar.a(8, bVar.a() - 8) == 0) {
            throw new WriterException();
        }
        bVar.a(8, bVar.a() - 8, 1);
    }

    private static void a(int i, int i2, b bVar) throws WriterException {
        for (int i3 = 0; i3 < 8; i3++) {
            int i4 = i + i3;
            if (!b((int) bVar.a(i4, i2))) {
                throw new WriterException();
            }
            bVar.a(i4, i2, 0);
        }
    }

    private static void b(int i, int i2, b bVar) throws WriterException {
        for (int i3 = 0; i3 < 7; i3++) {
            int i4 = i2 + i3;
            if (!b((int) bVar.a(i, i4))) {
                throw new WriterException();
            }
            bVar.a(i, i4, 0);
        }
    }

    private static void c(int i, int i2, b bVar) {
        for (int i3 = 0; i3 < 5; i3++) {
            for (int i4 = 0; i4 < 5; i4++) {
                bVar.a(i + i4, i2 + i3, b[i3][i4]);
            }
        }
    }

    private static void d(int i, int i2, b bVar) {
        for (int i3 = 0; i3 < 7; i3++) {
            for (int i4 = 0; i4 < 7; i4++) {
                bVar.a(i + i4, i2 + i3, a[i3][i4]);
            }
        }
    }

    private static void d(b bVar) throws WriterException {
        int length = a[0].length;
        d(0, 0, bVar);
        d(bVar.b() - length, 0, bVar);
        d(0, bVar.b() - length, bVar);
        a(0, 7, bVar);
        a(bVar.b() - 8, 7, bVar);
        a(0, bVar.b() - 8, bVar);
        b(7, 0, bVar);
        b((bVar.a() - 7) - 1, 0, bVar);
        b(7, bVar.a() - 7, bVar);
    }

    private static void c(com.google.zxing.qrcode.decoder.a aVar, b bVar) {
        if (aVar.a() >= 2) {
            int a2 = aVar.a() - 1;
            int[] iArr = c[a2];
            int length = c[a2].length;
            for (int i = 0; i < length; i++) {
                for (int i2 = 0; i2 < length; i2++) {
                    int i3 = iArr[i];
                    int i4 = iArr[i2];
                    if (!(i4 == -1 || i3 == -1 || !b((int) bVar.a(i4, i3)))) {
                        c(i4 - 2, i3 - 2, bVar);
                    }
                }
            }
        }
    }
}
