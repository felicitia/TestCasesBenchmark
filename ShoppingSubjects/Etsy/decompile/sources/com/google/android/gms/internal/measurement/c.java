package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class c {
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

    private c(byte[] bArr, int i2, int i3) {
        this.a = bArr;
        this.b = i2;
        int i4 = i3 + i2;
        this.d = i4;
        this.c = i4;
        this.f = i2;
    }

    public static c a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static c a(byte[] bArr, int i2, int i3) {
        return new c(bArr, 0, i3);
    }

    private final void f(int i2) throws IOException {
        if (i2 < 0) {
            throw zzaci.zzvx();
        } else if (this.f + i2 > this.h) {
            f(this.h - this.f);
            throw zzaci.zzvw();
        } else if (i2 <= this.d - this.f) {
            this.f += i2;
        } else {
            throw zzaci.zzvw();
        }
    }

    private final void j() {
        this.d += this.e;
        int i2 = this.d;
        if (i2 > this.h) {
            this.e = i2 - this.h;
            this.d -= this.e;
            return;
        }
        this.e = 0;
    }

    private final byte k() throws IOException {
        if (this.f == this.d) {
            throw zzaci.zzvw();
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
        this.g = d();
        if (this.g != 0) {
            return this.g;
        }
        throw new zzaci("Protocol message contained an invalid tag (zero).");
    }

    public final void a(int i2) throws zzaci {
        if (this.g != i2) {
            throw new zzaci("Protocol message end-group tag did not match expected tag.");
        }
    }

    public final void a(j jVar) throws IOException {
        int d2 = d();
        if (this.i >= this.j) {
            throw zzaci.zzvz();
        }
        int c2 = c(d2);
        this.i++;
        jVar.a(this);
        a(0);
        this.i--;
        d(c2);
    }

    public final void a(j jVar, int i2) throws IOException {
        if (this.i >= this.j) {
            throw zzaci.zzvz();
        }
        this.i++;
        jVar.a(this);
        a((i2 << 3) | 4);
        this.i--;
    }

    public final byte[] a(int i2, int i3) {
        if (i3 == 0) {
            return m.d;
        }
        byte[] bArr = new byte[i3];
        System.arraycopy(this.a, this.b + i2, bArr, 0, i3);
        return bArr;
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

    public final boolean b() throws IOException {
        return d() != 0;
    }

    public final boolean b(int i2) throws IOException {
        int a2;
        switch (i2 & 7) {
            case 0:
                d();
                return true;
            case 1:
                g();
                return true;
            case 2:
                f(d());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                f();
                return true;
            default:
                throw new zzaci("Protocol message tag had invalid wire type.");
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

    public final int c(int i2) throws zzaci {
        if (i2 < 0) {
            throw zzaci.zzvx();
        }
        int i3 = i2 + this.f;
        int i4 = this.h;
        if (i3 > i4) {
            throw zzaci.zzvw();
        }
        this.h = i3;
        j();
        return i4;
    }

    public final String c() throws IOException {
        int d2 = d();
        if (d2 < 0) {
            throw zzaci.zzvx();
        } else if (d2 > this.d - this.f) {
            throw zzaci.zzvw();
        } else {
            String str = new String(this.a, this.f, d2, i.a);
            this.f += d2;
            return str;
        }
    }

    public final int d() throws IOException {
        int i2;
        byte k2 = k();
        if (k2 >= 0) {
            return k2;
        }
        byte b2 = k2 & Byte.MAX_VALUE;
        byte k3 = k();
        if (k3 >= 0) {
            i2 = k3 << 7;
        } else {
            b2 |= (k3 & Byte.MAX_VALUE) << 7;
            byte k4 = k();
            if (k4 >= 0) {
                i2 = k4 << 14;
            } else {
                b2 |= (k4 & Byte.MAX_VALUE) << 14;
                byte k5 = k();
                if (k5 >= 0) {
                    i2 = k5 << 21;
                } else {
                    byte b3 = b2 | ((k5 & Byte.MAX_VALUE) << 21);
                    byte k6 = k();
                    byte b4 = b3 | (k6 << 28);
                    if (k6 >= 0) {
                        return b4;
                    }
                    for (int i3 = 0; i3 < 5; i3++) {
                        if (k() >= 0) {
                            return b4;
                        }
                    }
                    throw zzaci.zzvy();
                }
            }
        }
        return b2 | i2;
    }

    public final void d(int i2) {
        this.h = i2;
        j();
    }

    public final long e() throws IOException {
        int i2 = 0;
        long j2 = 0;
        while (i2 < 64) {
            byte k2 = k();
            long j3 = j2 | (((long) (k2 & Byte.MAX_VALUE)) << i2);
            if ((k2 & 128) == 0) {
                return j3;
            }
            i2 += 7;
            j2 = j3;
        }
        throw zzaci.zzvy();
    }

    public final void e(int i2) {
        b(i2, this.g);
    }

    public final int f() throws IOException {
        return (k() & 255) | ((k() & 255) << 8) | ((k() & 255) << 16) | ((k() & 255) << 24);
    }

    public final long g() throws IOException {
        return (((long) k()) & 255) | ((((long) k()) & 255) << 8) | ((((long) k()) & 255) << 16) | ((((long) k()) & 255) << 24) | ((((long) k()) & 255) << 32) | ((((long) k()) & 255) << 40) | ((((long) k()) & 255) << 48) | ((((long) k()) & 255) << 56);
    }

    public final int h() {
        if (this.h == Integer.MAX_VALUE) {
            return -1;
        }
        return this.h - this.f;
    }

    public final int i() {
        return this.f - this.b;
    }
}
