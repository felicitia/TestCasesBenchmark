package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aaj {
    private final byte[] a;
    private final int b;
    private final int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h = Integer.MAX_VALUE;
    private int i;
    private int j = 64;
    private int k = 67108864;

    private aaj(byte[] bArr, int i2, int i3) {
        this.a = bArr;
        this.b = i2;
        int i4 = i3 + i2;
        this.d = i4;
        this.c = i4;
        this.f = i2;
    }

    public static aaj a(byte[] bArr, int i2, int i3) {
        return new aaj(bArr, 0, i3);
    }

    private final void f(int i2) throws IOException {
        if (i2 < 0) {
            throw zzbfh.zzagr();
        } else if (this.f + i2 > this.h) {
            f(this.h - this.f);
            throw zzbfh.zzagq();
        } else if (i2 <= this.d - this.f) {
            this.f += i2;
        } else {
            throw zzbfh.zzagq();
        }
    }

    private final void k() {
        this.d += this.e;
        int i2 = this.d;
        if (i2 > this.h) {
            this.e = i2 - this.h;
            this.d -= this.e;
            return;
        }
        this.e = 0;
    }

    private final byte l() throws IOException {
        if (this.f == this.d) {
            throw zzbfh.zzagq();
        }
        byte[] bArr = this.a;
        int i2 = this.f;
        this.f = i2 + 1;
        return bArr[i2];
    }

    public final int a() throws IOException {
        if (this.f == this.d) {
            this.g = 0;
            return 0;
        }
        this.g = g();
        if (this.g != 0) {
            return this.g;
        }
        throw new zzbfh("Protocol message contained an invalid tag (zero).");
    }

    public final void a(int i2) throws zzbfh {
        if (this.g != i2) {
            throw new zzbfh("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final void a(aar aar) throws IOException {
        int g2 = g();
        if (this.i >= this.j) {
            throw new zzbfh("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
        }
        int c2 = c(g2);
        this.i++;
        aar.a(this);
        a(0);
        this.i--;
        d(c2);
    }

    public final byte[] a(int i2, int i3) {
        if (i3 == 0) {
            return aau.e;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.a, this.b + i2, bArr, 0, i3);
        return bArr;
    }

    public final long b() throws IOException {
        return h();
    }

    /* access modifiers changed from: 0000 */
    public final void b(int i2, int i3) {
        if (i2 > this.f - this.b) {
            int i4 = this.f - this.b;
            StringBuilder sb = new StringBuilder(50);
            sb.append("Position ");
            sb.append(i2);
            sb.append(" is beyond current ");
            sb.append(i4);
            throw new IllegalArgumentException(sb.toString());
        } else if (i2 < 0) {
            StringBuilder sb2 = new StringBuilder(24);
            sb2.append("Bad position ");
            sb2.append(i2);
            throw new IllegalArgumentException(sb2.toString());
        } else {
            this.f = this.b + i2;
            this.g = i3;
        }
    }

    public final boolean b(int i2) throws IOException {
        int a2;
        switch (i2 & 7) {
            case 0:
                g();
                return true;
            case 1:
                l();
                l();
                l();
                l();
                l();
                l();
                l();
                l();
                return true;
            case 2:
                f(g());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                l();
                l();
                l();
                l();
                return true;
            default:
                throw new zzbfh("Protocol message tag had invalid wire type.");
        }
        do {
            a2 = a();
            if (a2 != 0) {
            }
            a(((i2 >>> 3) << 3) | 4);
            return true;
        } while (b(a2));
        a(((i2 >>> 3) << 3) | 4);
        return true;
    }

    public final int c() throws IOException {
        return g();
    }

    public final int c(int i2) throws zzbfh {
        if (i2 < 0) {
            throw zzbfh.zzagr();
        }
        int i3 = i2 + this.f;
        int i4 = this.h;
        if (i3 > i4) {
            throw zzbfh.zzagq();
        }
        this.h = i3;
        k();
        return i4;
    }

    public final void d(int i2) {
        this.h = i2;
        k();
    }

    public final boolean d() throws IOException {
        return g() != 0;
    }

    public final String e() throws IOException {
        int g2 = g();
        if (g2 < 0) {
            throw zzbfh.zzagr();
        } else if (g2 > this.d - this.f) {
            throw zzbfh.zzagq();
        } else {
            String str = new String(this.a, this.f, g2, aaq.a);
            this.f += g2;
            return str;
        }
    }

    public final void e(int i2) {
        b(i2, this.g);
    }

    public final byte[] f() throws IOException {
        int g2 = g();
        if (g2 < 0) {
            throw zzbfh.zzagr();
        } else if (g2 == 0) {
            return aau.e;
        } else {
            if (g2 > this.d - this.f) {
                throw zzbfh.zzagq();
            }
            byte[] bArr = new byte[g2];
            System.arraycopy(this.a, this.f, bArr, 0, g2);
            this.f += g2;
            return bArr;
        }
    }

    public final int g() throws IOException {
        int i2;
        byte l = l();
        if (l >= 0) {
            return l;
        }
        byte b2 = l & Byte.MAX_VALUE;
        byte l2 = l();
        if (l2 >= 0) {
            i2 = l2 << 7;
        } else {
            b2 |= (l2 & Byte.MAX_VALUE) << 7;
            byte l3 = l();
            if (l3 >= 0) {
                i2 = l3 << 14;
            } else {
                b2 |= (l3 & Byte.MAX_VALUE) << 14;
                byte l4 = l();
                if (l4 >= 0) {
                    i2 = l4 << 21;
                } else {
                    byte b3 = b2 | ((l4 & Byte.MAX_VALUE) << 21);
                    byte l5 = l();
                    byte b4 = b3 | (l5 << 28);
                    if (l5 >= 0) {
                        return b4;
                    }
                    for (int i3 = 0; i3 < 5; i3++) {
                        if (l() >= 0) {
                            return b4;
                        }
                    }
                    throw zzbfh.zzags();
                }
            }
        }
        return b2 | i2;
    }

    public final long h() throws IOException {
        int i2 = 0;
        long j2 = 0;
        while (i2 < 64) {
            byte l = l();
            long j3 = j2 | (((long) (l & Byte.MAX_VALUE)) << i2);
            if ((l & 128) == 0) {
                return j3;
            }
            i2 += 7;
            j2 = j3;
        }
        throw zzbfh.zzags();
    }

    public final int i() {
        if (this.h == Integer.MAX_VALUE) {
            return -1;
        }
        return this.h - this.f;
    }

    public final int j() {
        return this.f - this.b;
    }
}
