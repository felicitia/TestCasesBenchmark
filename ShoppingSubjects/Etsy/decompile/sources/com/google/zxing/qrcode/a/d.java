package com.google.zxing.qrcode.a;

/* compiled from: MaskUtil */
final class d {
    static int a(b bVar) {
        return a(bVar, true) + a(bVar, false);
    }

    static int b(b bVar) {
        byte[][] c = bVar.c();
        int b = bVar.b();
        int a = bVar.a();
        int i = 0;
        int i2 = 0;
        while (i < a - 1) {
            int i3 = i2;
            int i4 = 0;
            while (i4 < b - 1) {
                byte b2 = c[i][i4];
                int i5 = i4 + 1;
                if (b2 == c[i][i5]) {
                    int i6 = i + 1;
                    if (b2 == c[i6][i4] && b2 == c[i6][i5]) {
                        i3++;
                    }
                }
                i4 = i5;
            }
            i++;
            i2 = i3;
        }
        return 3 * i2;
    }

    static int c(b bVar) {
        byte[][] c = bVar.c();
        int b = bVar.b();
        int a = bVar.a();
        int i = 0;
        int i2 = 0;
        while (i < a) {
            int i3 = i2;
            for (int i4 = 0; i4 < b; i4++) {
                byte[] bArr = c[i];
                int i5 = i4 + 6;
                if (i5 < b && bArr[i4] == 1 && bArr[i4 + 1] == 0 && bArr[i4 + 2] == 1 && bArr[i4 + 3] == 1 && bArr[i4 + 4] == 1 && bArr[i4 + 5] == 0 && bArr[i5] == 1 && (a(bArr, i4 - 4, i4) || a(bArr, i4 + 7, i4 + 11))) {
                    i3++;
                }
                int i6 = i + 6;
                if (i6 < a && c[i][i4] == 1 && c[i + 1][i4] == 0 && c[i + 2][i4] == 1 && c[i + 3][i4] == 1 && c[i + 4][i4] == 1 && c[i + 5][i4] == 0 && c[i6][i4] == 1 && (a(c, i4, i - 4, i) || a(c, i4, i + 7, i + 11))) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        return i2 * 40;
    }

    private static boolean a(byte[] bArr, int i, int i2) {
        int min = Math.min(i2, bArr.length);
        for (int max = Math.max(i, 0); max < min; max++) {
            if (bArr[max] == 1) {
                return false;
            }
        }
        return true;
    }

    private static boolean a(byte[][] bArr, int i, int i2, int i3) {
        int min = Math.min(i3, bArr.length);
        for (int max = Math.max(i2, 0); max < min; max++) {
            if (bArr[max][i] == 1) {
                return false;
            }
        }
        return true;
    }

    static int d(b bVar) {
        byte[][] c = bVar.c();
        int b = bVar.b();
        int a = bVar.a();
        int i = 0;
        int i2 = 0;
        while (i < a) {
            byte[] bArr = c[i];
            int i3 = i2;
            for (int i4 = 0; i4 < b; i4++) {
                if (bArr[i4] == 1) {
                    i3++;
                }
            }
            i++;
            i2 = i3;
        }
        int a2 = bVar.a() * bVar.b();
        return ((Math.abs((i2 << 1) - a2) * 10) / a2) * 10;
    }

    static boolean a(int i, int i2, int i3) {
        int i4;
        switch (i) {
            case 0:
                i4 = (i3 + i2) & 1;
                break;
            case 1:
                i4 = i3 & 1;
                break;
            case 2:
                i4 = i2 % 3;
                break;
            case 3:
                i4 = (i3 + i2) % 3;
                break;
            case 4:
                i4 = ((i3 / 2) + (i2 / 3)) & 1;
                break;
            case 5:
                int i5 = i3 * i2;
                i4 = (i5 & 1) + (i5 % 3);
                break;
            case 6:
                int i6 = i3 * i2;
                i4 = ((i6 & 1) + (i6 % 3)) & 1;
                break;
            case 7:
                i4 = (((i3 * i2) % 3) + ((i3 + i2) & 1)) & 1;
                break;
            default:
                StringBuilder sb = new StringBuilder("Invalid mask pattern: ");
                sb.append(i);
                throw new IllegalArgumentException(sb.toString());
        }
        if (i4 == 0) {
            return true;
        }
        return false;
    }

    private static int a(b bVar, boolean z) {
        int a = z ? bVar.a() : bVar.b();
        int b = z ? bVar.b() : bVar.a();
        byte[][] c = bVar.c();
        int i = 0;
        int i2 = 0;
        while (i < a) {
            int i3 = i2;
            byte b2 = -1;
            int i4 = 0;
            int i5 = 0;
            while (i4 < b) {
                byte b3 = z ? c[i][i4] : c[i4][i];
                if (b3 == b2) {
                    i5++;
                } else {
                    if (i5 >= 5) {
                        i3 += 3 + (i5 - 5);
                    }
                    i5 = 1;
                    b2 = b3;
                }
                i4++;
            }
            if (i5 >= 5) {
                i3 += 3 + (i5 - 5);
            }
            i2 = i3;
            i++;
        }
        return i2;
    }
}
