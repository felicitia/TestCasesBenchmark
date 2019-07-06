package com.google.android.gms.internal.ads;

import java.io.IOException;

final class wc {
    static int a(int i, byte[] bArr, int i2, int i3, wd wdVar) throws zzbbu {
        if ((i >>> 3) == 0) {
            throw zzbbu.zzado();
        }
        int i4 = i & 7;
        if (i4 == 5) {
            return i2 + 4;
        }
        switch (i4) {
            case 0:
                return b(bArr, i2, wdVar);
            case 1:
                return i2 + 8;
            case 2:
                return a(bArr, i2, wdVar) + wdVar.a;
            case 3:
                int i5 = (i & -8) | 4;
                int i6 = 0;
                while (i2 < i3) {
                    i2 = a(bArr, i2, wdVar);
                    i6 = wdVar.a;
                    if (i6 != i5) {
                        i2 = a(i6, bArr, i2, i3, wdVar);
                    } else if (i2 > i3 && i6 == i5) {
                        return i2;
                    } else {
                        throw zzbbu.zzadr();
                    }
                }
                if (i2 > i3) {
                }
                throw zzbbu.zzadr();
            default:
                throw zzbbu.zzado();
        }
    }

    static int a(int i, byte[] bArr, int i2, int i3, xm<?> xmVar, wd wdVar) {
        xi xiVar = (xi) xmVar;
        int a = a(bArr, i2, wdVar);
        while (true) {
            xiVar.c(wdVar.a);
            if (a >= i3) {
                break;
            }
            int a2 = a(bArr, a, wdVar);
            if (i != wdVar.a) {
                break;
            }
            a = a(bArr, a2, wdVar);
        }
        return a;
    }

    static int a(int i, byte[] bArr, int i2, int i3, zw zwVar, wd wdVar) throws IOException {
        if ((i >>> 3) == 0) {
            throw zzbbu.zzado();
        }
        int i4 = i & 7;
        if (i4 != 5) {
            switch (i4) {
                case 0:
                    int b = b(bArr, i2, wdVar);
                    zwVar.a(i, (Object) Long.valueOf(wdVar.b));
                    return b;
                case 1:
                    zwVar.a(i, (Object) Long.valueOf(b(bArr, i2)));
                    return i2 + 8;
                case 2:
                    int a = a(bArr, i2, wdVar);
                    int i5 = wdVar.a;
                    zwVar.a(i, (Object) i5 == 0 ? zzbah.zzdpq : zzbah.zzc(bArr, a, i5));
                    return a + i5;
                case 3:
                    zw b2 = zw.b();
                    int i6 = (i & -8) | 4;
                    int i7 = 0;
                    while (true) {
                        if (i2 < i3) {
                            int a2 = a(bArr, i2, wdVar);
                            int i8 = wdVar.a;
                            if (i8 != i6) {
                                i7 = i8;
                                i2 = a(i8, bArr, a2, i3, b2, wdVar);
                            } else {
                                i7 = i8;
                                i2 = a2;
                            }
                        }
                    }
                    if (i2 > i3 || i7 != i6) {
                        throw zzbbu.zzadr();
                    }
                    zwVar.a(i, (Object) b2);
                    return i2;
                default:
                    throw zzbbu.zzado();
            }
        } else {
            zwVar.a(i, (Object) Integer.valueOf(a(bArr, i2)));
            return i2 + 4;
        }
    }

    static int a(int i, byte[] bArr, int i2, wd wdVar) {
        int i3;
        int i4;
        int i5 = i & 127;
        int i6 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            i4 = b << 7;
        } else {
            int i7 = i5 | ((b & Byte.MAX_VALUE) << 7);
            int i8 = i6 + 1;
            byte b2 = bArr[i6];
            if (b2 >= 0) {
                i3 = b2 << 14;
            } else {
                i5 = i7 | ((b2 & Byte.MAX_VALUE) << 14);
                i6 = i8 + 1;
                byte b3 = bArr[i8];
                if (b3 >= 0) {
                    i4 = b3 << 21;
                } else {
                    i7 = i5 | ((b3 & Byte.MAX_VALUE) << 21);
                    i8 = i6 + 1;
                    byte b4 = bArr[i6];
                    if (b4 >= 0) {
                        i3 = b4 << 28;
                    } else {
                        int i9 = i7 | ((b4 & Byte.MAX_VALUE) << 28);
                        while (true) {
                            int i10 = i8 + 1;
                            if (bArr[i8] >= 0) {
                                wdVar.a = i9;
                                return i10;
                            }
                            i8 = i10;
                        }
                    }
                }
            }
            wdVar.a = i7 | i3;
            return i8;
        }
        wdVar.a = i5 | i4;
        return i6;
    }

    static int a(byte[] bArr, int i) {
        return ((bArr[i + 3] & 255) << 24) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16);
    }

    static int a(byte[] bArr, int i, wd wdVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b < 0) {
            return a((int) b, bArr, i2, wdVar);
        }
        wdVar.a = b;
        return i2;
    }

    static int a(byte[] bArr, int i, xm<?> xmVar, wd wdVar) throws IOException {
        xi xiVar = (xi) xmVar;
        int a = a(bArr, i, wdVar);
        int i2 = wdVar.a + a;
        while (a < i2) {
            a = a(bArr, a, wdVar);
            xiVar.c(wdVar.a);
        }
        if (a == i2) {
            return a;
        }
        throw zzbbu.zzadl();
    }

    static int b(byte[] bArr, int i, wd wdVar) {
        int i2 = i + 1;
        long j = (long) bArr[i];
        if (j >= 0) {
            wdVar.b = j;
            return i2;
        }
        long j2 = j & 127;
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j3 = j2 | (((long) (b & Byte.MAX_VALUE)) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            byte b2 = bArr[i3];
            i4 += 7;
            j3 |= ((long) (b2 & Byte.MAX_VALUE)) << i4;
            int i6 = i5;
            b = b2;
            i3 = i6;
        }
        wdVar.b = j3;
        return i3;
    }

    static long b(byte[] bArr, int i) {
        return (((long) bArr[i]) & 255) | ((((long) bArr[i + 1]) & 255) << 8) | ((((long) bArr[i + 2]) & 255) << 16) | ((((long) bArr[i + 3]) & 255) << 24) | ((((long) bArr[i + 4]) & 255) << 32) | ((((long) bArr[i + 5]) & 255) << 40) | ((((long) bArr[i + 6]) & 255) << 48) | ((((long) bArr[i + 7]) & 255) << 56);
    }

    static double c(byte[] bArr, int i) {
        return Double.longBitsToDouble(b(bArr, i));
    }

    static int c(byte[] bArr, int i, wd wdVar) {
        int a = a(bArr, i, wdVar);
        int i2 = wdVar.a;
        if (i2 == 0) {
            wdVar.c = "";
            return a;
        }
        wdVar.c = new String(bArr, a, i2, xj.a);
        return a + i2;
    }

    static float d(byte[] bArr, int i) {
        return Float.intBitsToFloat(a(bArr, i));
    }

    static int d(byte[] bArr, int i, wd wdVar) throws IOException {
        int a = a(bArr, i, wdVar);
        int i2 = wdVar.a;
        if (i2 == 0) {
            wdVar.c = "";
            return a;
        }
        int i3 = a + i2;
        if (!aad.a(bArr, a, i3)) {
            throw zzbbu.zzads();
        }
        wdVar.c = new String(bArr, a, i2, xj.a);
        return i3;
    }

    static int e(byte[] bArr, int i, wd wdVar) {
        int a = a(bArr, i, wdVar);
        int i2 = wdVar.a;
        if (i2 == 0) {
            wdVar.c = zzbah.zzdpq;
            return a;
        }
        wdVar.c = zzbah.zzc(bArr, a, i2);
        return a + i2;
    }
}
