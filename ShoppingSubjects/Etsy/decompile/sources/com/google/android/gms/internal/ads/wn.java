package com.google.android.gms.internal.ads;

import java.io.IOException;
import java.util.Arrays;

final class wn extends wl {
    private final byte[] d;
    private final boolean e;
    private int f;
    private int g;
    private int h;
    private int i;
    private int j;
    private int k;

    private wn(byte[] bArr, int i2, int i3, boolean z) {
        super();
        this.k = Integer.MAX_VALUE;
        this.d = bArr;
        this.f = i3 + i2;
        this.h = i2;
        this.i = this.h;
        this.e = z;
    }

    private final byte A() throws IOException {
        if (this.h == this.f) {
            throw zzbbu.zzadl();
        }
        byte[] bArr = this.d;
        int i2 = this.h;
        this.h = i2 + 1;
        return bArr[i2];
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0068, code lost:
        if (r1[r2] >= 0) goto L_0x006a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int v() throws java.io.IOException {
        /*
            r5 = this;
            int r0 = r5.h
            int r1 = r5.f
            if (r1 == r0) goto L_0x006d
            byte[] r1 = r5.d
            int r2 = r0 + 1
            byte r0 = r1[r0]
            if (r0 < 0) goto L_0x0011
            r5.h = r2
            return r0
        L_0x0011:
            int r3 = r5.f
            int r3 = r3 - r2
            r4 = 9
            if (r3 < r4) goto L_0x006d
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 7
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x0024
            r0 = r0 ^ -128(0xffffffffffffff80, float:NaN)
            goto L_0x006a
        L_0x0024:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r3 = r3 << 14
            r0 = r0 ^ r3
            if (r0 < 0) goto L_0x0031
            r0 = r0 ^ 16256(0x3f80, float:2.278E-41)
        L_0x002f:
            r3 = r2
            goto L_0x006a
        L_0x0031:
            int r3 = r2 + 1
            byte r2 = r1[r2]
            int r2 = r2 << 21
            r0 = r0 ^ r2
            if (r0 >= 0) goto L_0x003f
            r1 = -2080896(0xffffffffffe03f80, float:NaN)
            r0 = r0 ^ r1
            goto L_0x006a
        L_0x003f:
            int r2 = r3 + 1
            byte r3 = r1[r3]
            int r4 = r3 << 28
            r0 = r0 ^ r4
            r4 = 266354560(0xfe03f80, float:2.2112565E-29)
            r0 = r0 ^ r4
            if (r3 >= 0) goto L_0x002f
            int r3 = r2 + 1
            byte r2 = r1[r2]
            if (r2 >= 0) goto L_0x006a
            int r2 = r3 + 1
            byte r3 = r1[r3]
            if (r3 >= 0) goto L_0x002f
            int r3 = r2 + 1
            byte r2 = r1[r2]
            if (r2 >= 0) goto L_0x006a
            int r2 = r3 + 1
            byte r3 = r1[r3]
            if (r3 >= 0) goto L_0x002f
            int r3 = r2 + 1
            byte r1 = r1[r2]
            if (r1 < 0) goto L_0x006d
        L_0x006a:
            r5.h = r3
            return r0
        L_0x006d:
            long r0 = r5.s()
            int r0 = (int) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.ads.wn.v():int");
    }

    private final long w() throws IOException {
        long j2;
        int i2;
        long j3;
        long j4;
        byte b;
        long j5;
        int i3 = this.h;
        if (this.f != i3) {
            byte[] bArr = this.d;
            int i4 = i3 + 1;
            byte b2 = bArr[i3];
            if (b2 >= 0) {
                this.h = i4;
                return (long) b2;
            } else if (this.f - i4 >= 9) {
                int i5 = i4 + 1;
                byte b3 = b2 ^ (bArr[i4] << 7);
                if (b3 < 0) {
                    b = b3 ^ Byte.MIN_VALUE;
                } else {
                    int i6 = i5 + 1;
                    byte b4 = b3 ^ (bArr[i5] << 14);
                    if (b4 >= 0) {
                        j5 = (long) (b4 ^ 16256);
                        i2 = i6;
                        j2 = j5;
                        this.h = i2;
                        return j2;
                    }
                    i5 = i6 + 1;
                    byte b5 = b4 ^ (bArr[i6] << 21);
                    if (b5 < 0) {
                        b = b5 ^ -2080896;
                    } else {
                        long j6 = (long) b5;
                        int i7 = i5 + 1;
                        long j7 = j6 ^ (((long) bArr[i5]) << 28);
                        if (j7 >= 0) {
                            j4 = 266354560;
                        } else {
                            int i8 = i7 + 1;
                            long j8 = j7 ^ (((long) bArr[i7]) << 35);
                            if (j8 < 0) {
                                j3 = -34093383808L;
                            } else {
                                i7 = i8 + 1;
                                j7 = j8 ^ (((long) bArr[i8]) << 42);
                                if (j7 >= 0) {
                                    j4 = 4363953127296L;
                                } else {
                                    i8 = i7 + 1;
                                    j8 = j7 ^ (((long) bArr[i7]) << 49);
                                    if (j8 < 0) {
                                        j3 = -558586000294016L;
                                    } else {
                                        int i9 = i8 + 1;
                                        long j9 = (j8 ^ (((long) bArr[i8]) << 56)) ^ 71499008037633920L;
                                        if (j9 < 0) {
                                            int i10 = i9 + 1;
                                            if (((long) bArr[i9]) >= 0) {
                                                i9 = i10;
                                            }
                                        }
                                        j2 = j9;
                                        this.h = i2;
                                        return j2;
                                    }
                                }
                            }
                            j2 = j8 ^ j3;
                            i2 = i8;
                            this.h = i2;
                            return j2;
                        }
                        j2 = j7 ^ j4;
                        this.h = i2;
                        return j2;
                    }
                }
                j5 = (long) b;
                i2 = i5;
                j2 = j5;
                this.h = i2;
                return j2;
            }
        }
        return s();
    }

    private final int x() throws IOException {
        int i2 = this.h;
        if (this.f - i2 < 4) {
            throw zzbbu.zzadl();
        }
        byte[] bArr = this.d;
        this.h = i2 + 4;
        return ((bArr[i2 + 3] & 255) << 24) | (bArr[i2] & 255) | ((bArr[i2 + 1] & 255) << 8) | ((bArr[i2 + 2] & 255) << 16);
    }

    private final long y() throws IOException {
        int i2 = this.h;
        if (this.f - i2 < 8) {
            throw zzbbu.zzadl();
        }
        byte[] bArr = this.d;
        this.h = i2 + 8;
        return (((long) bArr[i2]) & 255) | ((((long) bArr[i2 + 1]) & 255) << 8) | ((((long) bArr[i2 + 2]) & 255) << 16) | ((((long) bArr[i2 + 3]) & 255) << 24) | ((((long) bArr[i2 + 4]) & 255) << 32) | ((((long) bArr[i2 + 5]) & 255) << 40) | ((((long) bArr[i2 + 6]) & 255) << 48) | ((((long) bArr[i2 + 7]) & 255) << 56);
    }

    private final void z() {
        this.f += this.g;
        int i2 = this.f - this.i;
        if (i2 > this.k) {
            this.g = i2 - this.k;
            this.f -= this.g;
            return;
        }
        this.g = 0;
    }

    public final int a() throws IOException {
        if (t()) {
            this.j = 0;
            return 0;
        }
        this.j = v();
        if ((this.j >>> 3) != 0) {
            return this.j;
        }
        throw zzbbu.zzado();
    }

    public final void a(int i2) throws zzbbu {
        if (this.j != i2) {
            throw zzbbu.zzadp();
        }
    }

    public final double b() throws IOException {
        return Double.longBitsToDouble(y());
    }

    public final boolean b(int i2) throws IOException {
        int a;
        int i3 = 0;
        switch (i2 & 7) {
            case 0:
                if (this.f - this.h >= 10) {
                    while (i3 < 10) {
                        byte[] bArr = this.d;
                        int i4 = this.h;
                        this.h = i4 + 1;
                        if (bArr[i4] < 0) {
                            i3++;
                        }
                    }
                    throw zzbbu.zzadn();
                }
                while (i3 < 10) {
                    if (A() < 0) {
                        i3++;
                    }
                }
                throw zzbbu.zzadn();
                return true;
            case 1:
                e(8);
                return true;
            case 2:
                e(v());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                e(4);
                return true;
            default:
                throw zzbbu.zzadq();
        }
        do {
            a = a();
            if (a != 0) {
            }
            a(((i2 >>> 3) << 3) | 4);
            return true;
        } while (b(a));
        a(((i2 >>> 3) << 3) | 4);
        return true;
    }

    public final float c() throws IOException {
        return Float.intBitsToFloat(x());
    }

    public final int c(int i2) throws zzbbu {
        if (i2 < 0) {
            throw zzbbu.zzadm();
        }
        int u = i2 + u();
        int i3 = this.k;
        if (u > i3) {
            throw zzbbu.zzadl();
        }
        this.k = u;
        z();
        return i3;
    }

    public final long d() throws IOException {
        return w();
    }

    public final void d(int i2) {
        this.k = i2;
        z();
    }

    public final long e() throws IOException {
        return w();
    }

    public final void e(int i2) throws IOException {
        if (i2 >= 0 && i2 <= this.f - this.h) {
            this.h += i2;
        } else if (i2 < 0) {
            throw zzbbu.zzadm();
        } else {
            throw zzbbu.zzadl();
        }
    }

    public final int f() throws IOException {
        return v();
    }

    public final long g() throws IOException {
        return y();
    }

    public final int h() throws IOException {
        return x();
    }

    public final boolean i() throws IOException {
        return w() != 0;
    }

    public final String j() throws IOException {
        int v = v();
        if (v > 0 && v <= this.f - this.h) {
            String str = new String(this.d, this.h, v, xj.a);
            this.h += v;
            return str;
        } else if (v == 0) {
            return "";
        } else {
            if (v < 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        }
    }

    public final String k() throws IOException {
        int v = v();
        if (v <= 0 || v > this.f - this.h) {
            if (v == 0) {
                return "";
            }
            if (v <= 0) {
                throw zzbbu.zzadm();
            }
            throw zzbbu.zzadl();
        } else if (!aad.a(this.d, this.h, this.h + v)) {
            throw zzbbu.zzads();
        } else {
            int i2 = this.h;
            this.h += v;
            return new String(this.d, i2, v, xj.a);
        }
    }

    public final zzbah l() throws IOException {
        byte[] bArr;
        int v = v();
        if (v > 0 && v <= this.f - this.h) {
            zzbah zzc = zzbah.zzc(this.d, this.h, v);
            this.h += v;
            return zzc;
        } else if (v == 0) {
            return zzbah.zzdpq;
        } else {
            if (v > 0 && v <= this.f - this.h) {
                int i2 = this.h;
                this.h += v;
                bArr = Arrays.copyOfRange(this.d, i2, this.h);
            } else if (v > 0) {
                throw zzbbu.zzadl();
            } else if (v == 0) {
                bArr = xj.b;
            } else {
                throw zzbbu.zzadm();
            }
            return zzbah.zzp(bArr);
        }
    }

    public final int m() throws IOException {
        return v();
    }

    public final int n() throws IOException {
        return v();
    }

    public final int o() throws IOException {
        return x();
    }

    public final long p() throws IOException {
        return y();
    }

    public final int q() throws IOException {
        return f(v());
    }

    public final long r() throws IOException {
        return a(w());
    }

    /* access modifiers changed from: 0000 */
    public final long s() throws IOException {
        long j2 = 0;
        int i2 = 0;
        while (i2 < 64) {
            byte A = A();
            long j3 = j2 | (((long) (A & Byte.MAX_VALUE)) << i2);
            if ((A & 128) == 0) {
                return j3;
            }
            i2 += 7;
            j2 = j3;
        }
        throw zzbbu.zzadn();
    }

    public final boolean t() throws IOException {
        return this.h == this.f;
    }

    public final int u() {
        return this.h - this.i;
    }
}
