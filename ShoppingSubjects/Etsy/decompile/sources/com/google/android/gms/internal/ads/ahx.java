package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class ahx extends aam<ahx> {
    public String a;
    public long[] b;
    public ahv c;
    public ahq d;
    private Integer e;
    private Integer f;
    private Integer g;
    private aii h;
    private ahw i;
    private aib j;

    public ahx() {
        this.e = null;
        this.a = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.b = aau.b;
        this.c = null;
        this.i = null;
        this.j = null;
        this.d = null;
        this.Y = null;
        this.Z = -1;
    }

    /* access modifiers changed from: private */
    /* renamed from: b */
    public final ahx a(aaj aaj) throws IOException {
        aar aar;
        while (true) {
            int a2 = aaj.a();
            switch (a2) {
                case 0:
                    return this;
                case 72:
                    this.e = Integer.valueOf(aaj.g());
                    continue;
                case 82:
                    this.a = aaj.e();
                    continue;
                case 88:
                    this.f = Integer.valueOf(aaj.g());
                    continue;
                case 96:
                    int j2 = aaj.j();
                    try {
                        this.g = Integer.valueOf(ahp.a(aaj.g()));
                        continue;
                    } catch (IllegalArgumentException unused) {
                        aaj.e(j2);
                        a(aaj, a2);
                        break;
                    }
                case 106:
                    if (this.h == null) {
                        this.h = new aii();
                    }
                    aar = this.h;
                    break;
                case 112:
                    int a3 = aau.a(aaj, 112);
                    int length = this.b == null ? 0 : this.b.length;
                    long[] jArr = new long[(a3 + length)];
                    if (length != 0) {
                        System.arraycopy(this.b, 0, jArr, 0, length);
                    }
                    while (length < jArr.length - 1) {
                        jArr[length] = aaj.h();
                        aaj.a();
                        length++;
                    }
                    jArr[length] = aaj.h();
                    this.b = jArr;
                    continue;
                case 114:
                    int c2 = aaj.c(aaj.g());
                    int j3 = aaj.j();
                    int i2 = 0;
                    while (aaj.i() > 0) {
                        aaj.h();
                        i2++;
                    }
                    aaj.e(j3);
                    int length2 = this.b == null ? 0 : this.b.length;
                    long[] jArr2 = new long[(i2 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.b, 0, jArr2, 0, length2);
                    }
                    while (length2 < jArr2.length) {
                        jArr2[length2] = aaj.h();
                        length2++;
                    }
                    this.b = jArr2;
                    aaj.d(c2);
                    continue;
                case 122:
                    if (this.c == null) {
                        this.c = new ahv();
                    }
                    aar = this.c;
                    break;
                case 130:
                    if (this.i == null) {
                        this.i = new ahw();
                    }
                    aar = this.i;
                    break;
                case 138:
                    if (this.j == null) {
                        this.j = new aib();
                    }
                    aar = this.j;
                    break;
                case 146:
                    if (this.d == null) {
                        this.d = new ahq();
                    }
                    aar = this.d;
                    break;
                default:
                    if (!super.a(aaj, a2)) {
                        return this;
                    }
                    continue;
            }
            aaj.a(aar);
        }
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.e != null) {
            a2 += aal.b(9, this.e.intValue());
        }
        if (this.a != null) {
            a2 += aal.b(10, this.a);
        }
        if (this.f != null) {
            a2 += aal.b(11) + aal.d(this.f.intValue());
        }
        if (this.g != null) {
            a2 += aal.b(12, this.g.intValue());
        }
        if (this.h != null) {
            a2 += aal.b(13, (aar) this.h);
        }
        if (this.b != null && this.b.length > 0) {
            int i2 = 0;
            for (long a3 : this.b) {
                i2 += aal.a(a3);
            }
            a2 = a2 + i2 + (1 * this.b.length);
        }
        if (this.c != null) {
            a2 += aal.b(15, (aar) this.c);
        }
        if (this.i != null) {
            a2 += aal.b(16, (aar) this.i);
        }
        if (this.j != null) {
            a2 += aal.b(17, (aar) this.j);
        }
        return this.d != null ? a2 + aal.b(18, (aar) this.d) : a2;
    }

    public final void a(aal aal) throws IOException {
        if (this.e != null) {
            aal.a(9, this.e.intValue());
        }
        if (this.a != null) {
            aal.a(10, this.a);
        }
        if (this.f != null) {
            int intValue = this.f.intValue();
            aal.c(11, 0);
            aal.c(intValue);
        }
        if (this.g != null) {
            aal.a(12, this.g.intValue());
        }
        if (this.h != null) {
            aal.a(13, (aar) this.h);
        }
        if (this.b != null && this.b.length > 0) {
            for (long a2 : this.b) {
                aal.a(14, a2);
            }
        }
        if (this.c != null) {
            aal.a(15, (aar) this.c);
        }
        if (this.i != null) {
            aal.a(16, (aar) this.i);
        }
        if (this.j != null) {
            aal.a(17, (aar) this.j);
        }
        if (this.d != null) {
            aal.a(18, (aar) this.d);
        }
        super.a(aal);
    }
}
