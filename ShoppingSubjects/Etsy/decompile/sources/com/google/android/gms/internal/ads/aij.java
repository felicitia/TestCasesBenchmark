package com.google.android.gms.internal.ads;

import java.io.IOException;

public final class aij extends aam<aij> {
    private static volatile aij[] a;
    private ain b;
    private aiq c;
    private air d;
    private ais e;
    private aik f;
    private aio g;
    private aim h;
    private Integer i;
    private Integer j;
    private aih k;
    private Integer l;
    private Integer m;
    private Integer n;
    private Integer o;
    private Integer p;
    private Long q;

    public aij() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.Y = null;
        this.Z = -1;
    }

    public static aij[] b() {
        if (a == null) {
            synchronized (aaq.b) {
                if (a == null) {
                    a = new aij[0];
                }
            }
        }
        return a;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a2 = super.a();
        if (this.b != null) {
            a2 += aal.b(5, (aar) this.b);
        }
        if (this.c != null) {
            a2 += aal.b(6, (aar) this.c);
        }
        if (this.d != null) {
            a2 += aal.b(7, (aar) this.d);
        }
        if (this.e != null) {
            a2 += aal.b(8, (aar) this.e);
        }
        if (this.f != null) {
            a2 += aal.b(9, (aar) this.f);
        }
        if (this.g != null) {
            a2 += aal.b(10, (aar) this.g);
        }
        if (this.h != null) {
            a2 += aal.b(11, (aar) this.h);
        }
        if (this.i != null) {
            a2 += aal.b(12, this.i.intValue());
        }
        if (this.j != null) {
            a2 += aal.b(13, this.j.intValue());
        }
        if (this.k != null) {
            a2 += aal.b(14, (aar) this.k);
        }
        if (this.l != null) {
            a2 += aal.b(15, this.l.intValue());
        }
        if (this.m != null) {
            a2 += aal.b(16, this.m.intValue());
        }
        if (this.n != null) {
            a2 += aal.b(17, this.n.intValue());
        }
        if (this.o != null) {
            a2 += aal.b(18, this.o.intValue());
        }
        if (this.p != null) {
            a2 += aal.b(19, this.p.intValue());
        }
        return this.q != null ? a2 + aal.c(20, this.q.longValue()) : a2;
    }

    public final /* synthetic */ aar a(aaj aaj) throws IOException {
        aar aar;
        while (true) {
            int a2 = aaj.a();
            switch (a2) {
                case 0:
                    return this;
                case 42:
                    if (this.b == null) {
                        this.b = new ain();
                    }
                    aar = this.b;
                    break;
                case 50:
                    if (this.c == null) {
                        this.c = new aiq();
                    }
                    aar = this.c;
                    break;
                case 58:
                    if (this.d == null) {
                        this.d = new air();
                    }
                    aar = this.d;
                    break;
                case 66:
                    if (this.e == null) {
                        this.e = new ais();
                    }
                    aar = this.e;
                    break;
                case 74:
                    if (this.f == null) {
                        this.f = new aik();
                    }
                    aar = this.f;
                    break;
                case 82:
                    if (this.g == null) {
                        this.g = new aio();
                    }
                    aar = this.g;
                    break;
                case 90:
                    if (this.h == null) {
                        this.h = new aim();
                    }
                    aar = this.h;
                    break;
                case 96:
                    this.i = Integer.valueOf(aaj.g());
                    continue;
                case 104:
                    this.j = Integer.valueOf(aaj.g());
                    continue;
                case 114:
                    if (this.k == null) {
                        this.k = new aih();
                    }
                    aar = this.k;
                    break;
                case 120:
                    this.l = Integer.valueOf(aaj.g());
                    continue;
                case 128:
                    this.m = Integer.valueOf(aaj.g());
                    continue;
                case 136:
                    this.n = Integer.valueOf(aaj.g());
                    continue;
                case 144:
                    this.o = Integer.valueOf(aaj.g());
                    continue;
                case 152:
                    this.p = Integer.valueOf(aaj.g());
                    continue;
                case 160:
                    this.q = Long.valueOf(aaj.h());
                    continue;
                default:
                    if (!super.a(aaj, a2)) {
                        return this;
                    }
                    continue;
            }
            aaj.a(aar);
        }
    }

    public final void a(aal aal) throws IOException {
        if (this.b != null) {
            aal.a(5, (aar) this.b);
        }
        if (this.c != null) {
            aal.a(6, (aar) this.c);
        }
        if (this.d != null) {
            aal.a(7, (aar) this.d);
        }
        if (this.e != null) {
            aal.a(8, (aar) this.e);
        }
        if (this.f != null) {
            aal.a(9, (aar) this.f);
        }
        if (this.g != null) {
            aal.a(10, (aar) this.g);
        }
        if (this.h != null) {
            aal.a(11, (aar) this.h);
        }
        if (this.i != null) {
            aal.a(12, this.i.intValue());
        }
        if (this.j != null) {
            aal.a(13, this.j.intValue());
        }
        if (this.k != null) {
            aal.a(14, (aar) this.k);
        }
        if (this.l != null) {
            aal.a(15, this.l.intValue());
        }
        if (this.m != null) {
            aal.a(16, this.m.intValue());
        }
        if (this.n != null) {
            aal.a(17, this.n.intValue());
        }
        if (this.o != null) {
            aal.a(18, this.o.intValue());
        }
        if (this.p != null) {
            aal.a(19, this.p.intValue());
        }
        if (this.q != null) {
            aal.a(20, this.q.longValue());
        }
        super.a(aal);
    }
}
