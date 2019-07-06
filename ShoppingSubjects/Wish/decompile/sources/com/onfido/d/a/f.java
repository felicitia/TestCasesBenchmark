package com.onfido.d.a;

import com.onfido.d.g;
import java.lang.reflect.Array;

public final class f extends e {
    private b a;

    public f(g gVar) {
        super(gVar);
    }

    private static int a(int i, int i2, int i3) {
        return i < i2 ? i2 : i > i3 ? i3 : i;
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, b bVar) {
        int i5 = (i2 * i4) + i;
        int i6 = 0;
        while (i6 < 8) {
            for (int i7 = 0; i7 < 8; i7++) {
                if ((bArr[i5 + i7] & 255) <= i3) {
                    bVar.b(i + i7, i2 + i6);
                }
            }
            i6++;
            i5 += i4;
        }
    }

    private static void a(byte[] bArr, int i, int i2, int i3, int i4, int[][] iArr, b bVar) {
        int i5 = i;
        int i6 = i2;
        int i7 = i4 - 8;
        int i8 = i3 - 8;
        for (int i9 = 0; i9 < i6; i9++) {
            int i10 = i9 << 3;
            int i11 = i10 > i7 ? i7 : i10;
            int a2 = a(i9, 2, i6 - 3);
            int i12 = 0;
            while (i12 < i5) {
                int i13 = i12 << 3;
                int i14 = i13 > i8 ? i8 : i13;
                int a3 = a(i12, 2, i5 - 3);
                int i15 = 0;
                for (int i16 = -2; i16 <= 2; i16++) {
                    int[] iArr2 = iArr[a2 + i16];
                    i15 += iArr2[a3 - 2] + iArr2[a3 - 1] + iArr2[a3] + iArr2[a3 + 1] + iArr2[a3 + 2];
                }
                int i17 = i12;
                a(bArr, i14, i11, i15 / 25, i3, bVar);
                i12 = i17 + 1;
            }
        }
    }

    private static int[][] a(byte[] bArr, int i, int i2, int i3, int i4) {
        int i5 = i;
        int i6 = i2;
        int i7 = 8;
        int i8 = i4 - 8;
        int i9 = i3 - 8;
        int[][] iArr = (int[][]) Array.newInstance(int.class, new int[]{i6, i5});
        int i10 = 0;
        while (i10 < i6) {
            int i11 = i10 << 3;
            if (i11 > i8) {
                i11 = i8;
            }
            int i12 = 0;
            while (i12 < i5) {
                int i13 = i12 << 3;
                if (i13 > i9) {
                    i13 = i9;
                }
                int i14 = (i11 * i3) + i13;
                byte b = 255;
                int i15 = 0;
                int i16 = 0;
                byte b2 = 0;
                while (i15 < i7) {
                    byte b3 = b2;
                    byte b4 = b;
                    int i17 = 0;
                    while (i17 < i7) {
                        byte b5 = bArr[i14 + i17] & 255;
                        i16 += b5;
                        if (b5 < b4) {
                            b4 = b5;
                        }
                        if (b5 > b3) {
                            b3 = b5;
                        }
                        i17++;
                        int i18 = i;
                        i7 = 8;
                    }
                    if (b3 - b4 > 24) {
                        while (true) {
                            i15++;
                            i14 += i3;
                            if (i15 >= 8) {
                                break;
                            }
                            int i19 = 0;
                            for (int i20 = 8; i19 < i20; i20 = 8) {
                                i16 += bArr[i14 + i19] & 255;
                                i19++;
                            }
                        }
                    }
                    i15++;
                    i14 += i3;
                    b = b4;
                    int i21 = i;
                    i7 = 8;
                    b2 = b3;
                }
                int i22 = i16 >> 6;
                if (b2 - b <= 24) {
                    i22 = b / 2;
                    if (i10 > 0 && i12 > 0) {
                        int i23 = i10 - 1;
                        int i24 = i12 - 1;
                        int i25 = ((iArr[i23][i12] + (iArr[i10][i24] * 2)) + iArr[i23][i24]) / 4;
                        if (b < i25) {
                            i22 = i25;
                        }
                    }
                }
                iArr[i10][i12] = i22;
                i12++;
                i5 = i;
                i7 = 8;
            }
            i10++;
            i5 = i;
            i7 = 8;
        }
        return iArr;
    }

    public b b() {
        b bVar;
        if (this.a != null) {
            return this.a;
        }
        g a2 = a();
        int b = a2.b();
        int c = a2.c();
        if (b < 40 || c < 40) {
            bVar = super.b();
        } else {
            byte[] a3 = a2.a();
            int i = b >> 3;
            if ((b & 7) != 0) {
                i++;
            }
            int i2 = i;
            int i3 = c >> 3;
            if ((c & 7) != 0) {
                i3++;
            }
            int i4 = i3;
            int[][] a4 = a(a3, i2, i4, b, c);
            bVar = new b(b, c);
            a(a3, i2, i4, b, c, a4, bVar);
        }
        this.a = bVar;
        return this.a;
    }
}
