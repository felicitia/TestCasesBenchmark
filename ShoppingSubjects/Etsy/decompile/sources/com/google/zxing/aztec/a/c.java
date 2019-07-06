package com.google.zxing.aztec.a;

import com.google.zxing.common.a;
import com.google.zxing.common.b;

/* compiled from: Encoder */
public final class c {
    private static final int[] a = {4, 6, 6, 8, 8, 8, 8, 8, 8, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12};

    private static int a(int i, boolean z) {
        return ((z ? 88 : 112) + (i << 4)) * i;
    }

    public static a a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        boolean z;
        a aVar;
        int i6;
        a a2 = new d(bArr).a();
        int i7 = 11;
        int a3 = ((a2.a() * i) / 100) + 11;
        int a4 = a2.a() + a3;
        int i8 = 32;
        int i9 = 0;
        if (i2 != 0) {
            boolean z2 = i2 < 0 ? 1 : 0;
            int abs = Math.abs(i2);
            if (z2 != 0) {
                i8 = 4;
            }
            if (abs > i8) {
                throw new IllegalArgumentException(String.format("Illegal value %s for layers", new Object[]{Integer.valueOf(i2)}));
            }
            int a5 = a(abs, z2);
            i5 = a[abs];
            int i10 = a5 - (a5 % i5);
            aVar = a(a2, i5);
            if (aVar.a() + a3 > i10) {
                throw new IllegalArgumentException("Data to large for user specified layer");
            } else if (z2 == 0 || aVar.a() <= (i5 << 6)) {
                i3 = a5;
                i4 = abs;
                z = z2;
            } else {
                throw new IllegalArgumentException("Data to large for user specified layer");
            }
        } else {
            a aVar2 = null;
            int i11 = 0;
            int i12 = 0;
            while (i11 <= 32) {
                boolean z3 = i11 <= 3 ? 1 : i9;
                i4 = z3 != 0 ? i11 + 1 : i11;
                i3 = a(i4, z3);
                if (a4 <= i3) {
                    if (i12 != a[i4]) {
                        i12 = a[i4];
                        aVar2 = a(a2, i12);
                    }
                    int i13 = i3 - (i3 % i12);
                    if ((z3 == 0 || aVar2.a() <= (i12 << 6)) && aVar2.a() + a3 <= i13) {
                        i5 = i12;
                        aVar = aVar2;
                        z = z3;
                    }
                }
                i11++;
                i9 = 0;
            }
            throw new IllegalArgumentException("Data too large for an Aztec code");
        }
        a a6 = a(aVar, i3, i5);
        int a7 = aVar.a() / i5;
        a a8 = a(z, i4, a7);
        if (z == 0) {
            i7 = 14;
        }
        int i14 = i7 + (i4 << 2);
        int[] iArr = new int[i14];
        int i15 = 2;
        if (z != 0) {
            for (int i16 = i9; i16 < iArr.length; i16++) {
                iArr[i16] = i16;
            }
            i6 = i14;
        } else {
            int i17 = i14 / 2;
            i6 = i14 + 1 + (((i17 - 1) / 15) * 2);
            int i18 = i6 / 2;
            for (int i19 = i9; i19 < i17; i19++) {
                int i20 = (i19 / 15) + i19;
                iArr[(i17 - i19) - 1] = (i18 - i20) - 1;
                iArr[i17 + i19] = i20 + i18 + 1;
            }
        }
        b bVar = new b(i6);
        int i21 = i9;
        int i22 = i21;
        while (i21 < i4) {
            int i23 = ((i4 - i21) << i15) + (z != 0 ? 9 : 12);
            int i24 = i9;
            while (i24 < i23) {
                int i25 = i24 << 1;
                while (i9 < i15) {
                    if (a6.a(i22 + i25 + i9)) {
                        int i26 = i21 << 1;
                        bVar.b(iArr[i26 + i9], iArr[i26 + i24]);
                    }
                    if (a6.a((i23 << 1) + i22 + i25 + i9)) {
                        int i27 = i21 << 1;
                        bVar.b(iArr[i27 + i24], iArr[((i14 - 1) - i27) - i9]);
                    }
                    if (a6.a((i23 << 2) + i22 + i25 + i9)) {
                        int i28 = (i14 - 1) - (i21 << 1);
                        bVar.b(iArr[i28 - i9], iArr[i28 - i24]);
                    }
                    if (a6.a((i23 * 6) + i22 + i25 + i9)) {
                        int i29 = i21 << 1;
                        bVar.b(iArr[((i14 - 1) - i29) - i24], iArr[i29 + i9]);
                    }
                    i9++;
                    i15 = 2;
                }
                i24++;
                i9 = 0;
                i15 = 2;
            }
            i22 += i23 << 3;
            i21++;
            i9 = 0;
            i15 = 2;
        }
        a(bVar, z, i6, a8);
        if (z != 0) {
            a(bVar, i6 / 2, 5);
        } else {
            int i30 = i6 / 2;
            a(bVar, i30, 7);
            int i31 = 0;
            int i32 = 0;
            while (i31 < (i14 / 2) - 1) {
                for (int i33 = i30 & 1; i33 < i6; i33 += 2) {
                    int i34 = i30 - i32;
                    bVar.b(i34, i33);
                    int i35 = i30 + i32;
                    bVar.b(i35, i33);
                    bVar.b(i33, i34);
                    bVar.b(i33, i35);
                }
                i31 += 15;
                i32 += 16;
            }
        }
        a aVar3 = new a();
        aVar3.a(z);
        aVar3.a(i6);
        aVar3.b(i4);
        aVar3.c(a7);
        aVar3.a(bVar);
        return aVar3;
    }

    private static void a(b bVar, int i, int i2) {
        for (int i3 = 0; i3 < i2; i3 += 2) {
            int i4 = i - i3;
            int i5 = i4;
            while (true) {
                int i6 = i + i3;
                if (i5 > i6) {
                    break;
                }
                bVar.b(i5, i4);
                bVar.b(i5, i6);
                bVar.b(i4, i5);
                bVar.b(i6, i5);
                i5++;
            }
        }
        int i7 = i - i2;
        bVar.b(i7, i7);
        int i8 = i7 + 1;
        bVar.b(i8, i7);
        bVar.b(i7, i8);
        int i9 = i + i2;
        bVar.b(i9, i7);
        bVar.b(i9, i8);
        bVar.b(i9, i9 - 1);
    }

    static a a(boolean z, int i, int i2) {
        a aVar = new a();
        if (z) {
            aVar.a(i - 1, 2);
            aVar.a(i2 - 1, 6);
            return a(aVar, 28, 4);
        }
        aVar.a(i - 1, 5);
        aVar.a(i2 - 1, 11);
        return a(aVar, 40, 4);
    }

    private static void a(b bVar, boolean z, int i, a aVar) {
        int i2 = i / 2;
        int i3 = 0;
        if (z) {
            while (i3 < 7) {
                int i4 = (i2 - 3) + i3;
                if (aVar.a(i3)) {
                    bVar.b(i4, i2 - 5);
                }
                if (aVar.a(i3 + 7)) {
                    bVar.b(i2 + 5, i4);
                }
                if (aVar.a(20 - i3)) {
                    bVar.b(i4, i2 + 5);
                }
                if (aVar.a(27 - i3)) {
                    bVar.b(i2 - 5, i4);
                }
                i3++;
            }
            return;
        }
        while (i3 < 10) {
            int i5 = (i2 - 5) + i3 + (i3 / 5);
            if (aVar.a(i3)) {
                bVar.b(i5, i2 - 7);
            }
            if (aVar.a(i3 + 10)) {
                bVar.b(i2 + 7, i5);
            }
            if (aVar.a(29 - i3)) {
                bVar.b(i5, i2 + 7);
            }
            if (aVar.a(39 - i3)) {
                bVar.b(i2 - 7, i5);
            }
            i3++;
        }
    }

    private static a a(a aVar, int i, int i2) {
        int a2 = aVar.a() / i2;
        com.google.zxing.common.reedsolomon.c cVar = new com.google.zxing.common.reedsolomon.c(a(i2));
        int i3 = i / i2;
        int[] b = b(aVar, i2, i3);
        cVar.a(b, i3 - a2);
        int i4 = i % i2;
        a aVar2 = new a();
        aVar2.a(0, i4);
        for (int a3 : b) {
            aVar2.a(a3, i2);
        }
        return aVar2;
    }

    private static int[] b(a aVar, int i, int i2) {
        int[] iArr = new int[i2];
        int a2 = aVar.a() / i;
        for (int i3 = 0; i3 < a2; i3++) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                i4 |= aVar.a((i3 * i) + i5) ? 1 << ((i - i5) - 1) : 0;
            }
            iArr[i3] = i4;
        }
        return iArr;
    }

    private static com.google.zxing.common.reedsolomon.a a(int i) {
        if (i == 4) {
            return com.google.zxing.common.reedsolomon.a.d;
        }
        if (i == 6) {
            return com.google.zxing.common.reedsolomon.a.c;
        }
        if (i == 8) {
            return com.google.zxing.common.reedsolomon.a.g;
        }
        if (i == 10) {
            return com.google.zxing.common.reedsolomon.a.b;
        }
        if (i == 12) {
            return com.google.zxing.common.reedsolomon.a.a;
        }
        StringBuilder sb = new StringBuilder("Unsupported word size ");
        sb.append(i);
        throw new IllegalArgumentException(sb.toString());
    }

    static a a(a aVar, int i) {
        a aVar2 = new a();
        int a2 = aVar.a();
        int i2 = (1 << i) - 2;
        int i3 = 0;
        while (i3 < a2) {
            int i4 = 0;
            for (int i5 = 0; i5 < i; i5++) {
                int i6 = i3 + i5;
                if (i6 >= a2 || aVar.a(i6)) {
                    i4 |= 1 << ((i - 1) - i5);
                }
            }
            int i7 = i4 & i2;
            if (i7 == i2) {
                aVar2.a(i7, i);
                i3--;
            } else if (i7 == 0) {
                aVar2.a(i4 | 1, i);
                i3--;
            } else {
                aVar2.a(i4, i);
            }
            i3 += i;
        }
        return aVar2;
    }
}
