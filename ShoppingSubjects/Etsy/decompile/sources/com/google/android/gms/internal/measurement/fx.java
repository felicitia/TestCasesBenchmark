package com.google.android.gms.internal.measurement;

import java.io.IOException;

public final class fx extends e<fx> {
    private static volatile fx[] K;
    public String A;
    public Boolean B;
    public fs[] C;
    public String D;
    public Integer E;
    public String F;
    public Long G;
    public Long H;
    public String I;
    public Integer J;
    private Integer L;
    private Integer M;
    private String N;
    private String O;
    public Integer c;
    public fu[] d;
    public ga[] e;
    public Long f;
    public Long g;
    public Long h;
    public Long i;
    public Long j;
    public String k;
    public String l;
    public String m;
    public String n;
    public Integer o;
    public String p;
    public String q;
    public String r;
    public Long s;
    public Long t;
    public String u;
    public Boolean v;
    public String w;
    public Long x;
    public Integer y;
    public String z;

    public fx() {
        this.c = null;
        this.d = fu.e();
        this.e = ga.e();
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
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = fs.e();
        this.D = null;
        this.E = null;
        this.L = null;
        this.M = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.N = null;
        this.J = null;
        this.O = null;
        this.a = null;
        this.b = -1;
    }

    public static fx[] e() {
        if (K == null) {
            synchronized (i.b) {
                if (K == null) {
                    K = new fx[0];
                }
            }
        }
        return K;
    }

    /* access modifiers changed from: protected */
    public final int a() {
        int a = super.a();
        if (this.c != null) {
            a += d.b(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            int i2 = a;
            for (fu fuVar : this.d) {
                if (fuVar != null) {
                    i2 += d.b(2, (j) fuVar);
                }
            }
            a = i2;
        }
        if (this.e != null && this.e.length > 0) {
            int i3 = a;
            for (ga gaVar : this.e) {
                if (gaVar != null) {
                    i3 += d.b(3, (j) gaVar);
                }
            }
            a = i3;
        }
        if (this.f != null) {
            a += d.c(4, this.f.longValue());
        }
        if (this.g != null) {
            a += d.c(5, this.g.longValue());
        }
        if (this.h != null) {
            a += d.c(6, this.h.longValue());
        }
        if (this.j != null) {
            a += d.c(7, this.j.longValue());
        }
        if (this.k != null) {
            a += d.b(8, this.k);
        }
        if (this.l != null) {
            a += d.b(9, this.l);
        }
        if (this.m != null) {
            a += d.b(10, this.m);
        }
        if (this.n != null) {
            a += d.b(11, this.n);
        }
        if (this.o != null) {
            a += d.b(12, this.o.intValue());
        }
        if (this.p != null) {
            a += d.b(13, this.p);
        }
        if (this.q != null) {
            a += d.b(14, this.q);
        }
        if (this.r != null) {
            a += d.b(16, this.r);
        }
        if (this.s != null) {
            a += d.c(17, this.s.longValue());
        }
        if (this.t != null) {
            a += d.c(18, this.t.longValue());
        }
        if (this.u != null) {
            a += d.b(19, this.u);
        }
        if (this.v != null) {
            this.v.booleanValue();
            a += d.b(20) + 1;
        }
        if (this.w != null) {
            a += d.b(21, this.w);
        }
        if (this.x != null) {
            a += d.c(22, this.x.longValue());
        }
        if (this.y != null) {
            a += d.b(23, this.y.intValue());
        }
        if (this.z != null) {
            a += d.b(24, this.z);
        }
        if (this.A != null) {
            a += d.b(25, this.A);
        }
        if (this.i != null) {
            a += d.c(26, this.i.longValue());
        }
        if (this.B != null) {
            this.B.booleanValue();
            a += d.b(28) + 1;
        }
        if (this.C != null && this.C.length > 0) {
            for (fs fsVar : this.C) {
                if (fsVar != null) {
                    a += d.b(29, (j) fsVar);
                }
            }
        }
        if (this.D != null) {
            a += d.b(30, this.D);
        }
        if (this.E != null) {
            a += d.b(31, this.E.intValue());
        }
        if (this.L != null) {
            a += d.b(32, this.L.intValue());
        }
        if (this.M != null) {
            a += d.b(33, this.M.intValue());
        }
        if (this.F != null) {
            a += d.b(34, this.F);
        }
        if (this.G != null) {
            a += d.c(35, this.G.longValue());
        }
        if (this.H != null) {
            a += d.c(36, this.H.longValue());
        }
        if (this.I != null) {
            a += d.b(37, this.I);
        }
        if (this.N != null) {
            a += d.b(38, this.N);
        }
        if (this.J != null) {
            a += d.b(39, this.J.intValue());
        }
        return this.O != null ? a + d.b(41, this.O) : a;
    }

    public final /* synthetic */ j a(c cVar) throws IOException {
        while (true) {
            int a = cVar.a();
            switch (a) {
                case 0:
                    return this;
                case 8:
                    this.c = Integer.valueOf(cVar.d());
                    break;
                case 18:
                    int a2 = m.a(cVar, 18);
                    int length = this.d == null ? 0 : this.d.length;
                    fu[] fuVarArr = new fu[(a2 + length)];
                    if (length != 0) {
                        System.arraycopy(this.d, 0, fuVarArr, 0, length);
                    }
                    while (length < fuVarArr.length - 1) {
                        fuVarArr[length] = new fu();
                        cVar.a((j) fuVarArr[length]);
                        cVar.a();
                        length++;
                    }
                    fuVarArr[length] = new fu();
                    cVar.a((j) fuVarArr[length]);
                    this.d = fuVarArr;
                    break;
                case 26:
                    int a3 = m.a(cVar, 26);
                    int length2 = this.e == null ? 0 : this.e.length;
                    ga[] gaVarArr = new ga[(a3 + length2)];
                    if (length2 != 0) {
                        System.arraycopy(this.e, 0, gaVarArr, 0, length2);
                    }
                    while (length2 < gaVarArr.length - 1) {
                        gaVarArr[length2] = new ga();
                        cVar.a((j) gaVarArr[length2]);
                        cVar.a();
                        length2++;
                    }
                    gaVarArr[length2] = new ga();
                    cVar.a((j) gaVarArr[length2]);
                    this.e = gaVarArr;
                    break;
                case 32:
                    this.f = Long.valueOf(cVar.e());
                    break;
                case 40:
                    this.g = Long.valueOf(cVar.e());
                    break;
                case 48:
                    this.h = Long.valueOf(cVar.e());
                    break;
                case 56:
                    this.j = Long.valueOf(cVar.e());
                    break;
                case 66:
                    this.k = cVar.c();
                    break;
                case 74:
                    this.l = cVar.c();
                    break;
                case 82:
                    this.m = cVar.c();
                    break;
                case 90:
                    this.n = cVar.c();
                    break;
                case 96:
                    this.o = Integer.valueOf(cVar.d());
                    break;
                case 106:
                    this.p = cVar.c();
                    break;
                case 114:
                    this.q = cVar.c();
                    break;
                case 130:
                    this.r = cVar.c();
                    break;
                case 136:
                    this.s = Long.valueOf(cVar.e());
                    break;
                case 144:
                    this.t = Long.valueOf(cVar.e());
                    break;
                case 154:
                    this.u = cVar.c();
                    break;
                case 160:
                    this.v = Boolean.valueOf(cVar.b());
                    break;
                case 170:
                    this.w = cVar.c();
                    break;
                case 176:
                    this.x = Long.valueOf(cVar.e());
                    break;
                case 184:
                    this.y = Integer.valueOf(cVar.d());
                    break;
                case 194:
                    this.z = cVar.c();
                    break;
                case 202:
                    this.A = cVar.c();
                    break;
                case 208:
                    this.i = Long.valueOf(cVar.e());
                    break;
                case 224:
                    this.B = Boolean.valueOf(cVar.b());
                    break;
                case 234:
                    int a4 = m.a(cVar, 234);
                    int length3 = this.C == null ? 0 : this.C.length;
                    fs[] fsVarArr = new fs[(a4 + length3)];
                    if (length3 != 0) {
                        System.arraycopy(this.C, 0, fsVarArr, 0, length3);
                    }
                    while (length3 < fsVarArr.length - 1) {
                        fsVarArr[length3] = new fs();
                        cVar.a((j) fsVarArr[length3]);
                        cVar.a();
                        length3++;
                    }
                    fsVarArr[length3] = new fs();
                    cVar.a((j) fsVarArr[length3]);
                    this.C = fsVarArr;
                    break;
                case 242:
                    this.D = cVar.c();
                    break;
                case 248:
                    this.E = Integer.valueOf(cVar.d());
                    break;
                case 256:
                    this.L = Integer.valueOf(cVar.d());
                    break;
                case 264:
                    this.M = Integer.valueOf(cVar.d());
                    break;
                case 274:
                    this.F = cVar.c();
                    break;
                case 280:
                    this.G = Long.valueOf(cVar.e());
                    break;
                case 288:
                    this.H = Long.valueOf(cVar.e());
                    break;
                case 298:
                    this.I = cVar.c();
                    break;
                case 306:
                    this.N = cVar.c();
                    break;
                case 312:
                    this.J = Integer.valueOf(cVar.d());
                    break;
                case 330:
                    this.O = cVar.c();
                    break;
                default:
                    if (super.a(cVar, a)) {
                        break;
                    } else {
                        return this;
                    }
            }
        }
    }

    public final void a(d dVar) throws IOException {
        if (this.c != null) {
            dVar.a(1, this.c.intValue());
        }
        if (this.d != null && this.d.length > 0) {
            for (fu fuVar : this.d) {
                if (fuVar != null) {
                    dVar.a(2, (j) fuVar);
                }
            }
        }
        if (this.e != null && this.e.length > 0) {
            for (ga gaVar : this.e) {
                if (gaVar != null) {
                    dVar.a(3, (j) gaVar);
                }
            }
        }
        if (this.f != null) {
            dVar.b(4, this.f.longValue());
        }
        if (this.g != null) {
            dVar.b(5, this.g.longValue());
        }
        if (this.h != null) {
            dVar.b(6, this.h.longValue());
        }
        if (this.j != null) {
            dVar.b(7, this.j.longValue());
        }
        if (this.k != null) {
            dVar.a(8, this.k);
        }
        if (this.l != null) {
            dVar.a(9, this.l);
        }
        if (this.m != null) {
            dVar.a(10, this.m);
        }
        if (this.n != null) {
            dVar.a(11, this.n);
        }
        if (this.o != null) {
            dVar.a(12, this.o.intValue());
        }
        if (this.p != null) {
            dVar.a(13, this.p);
        }
        if (this.q != null) {
            dVar.a(14, this.q);
        }
        if (this.r != null) {
            dVar.a(16, this.r);
        }
        if (this.s != null) {
            dVar.b(17, this.s.longValue());
        }
        if (this.t != null) {
            dVar.b(18, this.t.longValue());
        }
        if (this.u != null) {
            dVar.a(19, this.u);
        }
        if (this.v != null) {
            dVar.a(20, this.v.booleanValue());
        }
        if (this.w != null) {
            dVar.a(21, this.w);
        }
        if (this.x != null) {
            dVar.b(22, this.x.longValue());
        }
        if (this.y != null) {
            dVar.a(23, this.y.intValue());
        }
        if (this.z != null) {
            dVar.a(24, this.z);
        }
        if (this.A != null) {
            dVar.a(25, this.A);
        }
        if (this.i != null) {
            dVar.b(26, this.i.longValue());
        }
        if (this.B != null) {
            dVar.a(28, this.B.booleanValue());
        }
        if (this.C != null && this.C.length > 0) {
            for (fs fsVar : this.C) {
                if (fsVar != null) {
                    dVar.a(29, (j) fsVar);
                }
            }
        }
        if (this.D != null) {
            dVar.a(30, this.D);
        }
        if (this.E != null) {
            dVar.a(31, this.E.intValue());
        }
        if (this.L != null) {
            dVar.a(32, this.L.intValue());
        }
        if (this.M != null) {
            dVar.a(33, this.M.intValue());
        }
        if (this.F != null) {
            dVar.a(34, this.F);
        }
        if (this.G != null) {
            dVar.b(35, this.G.longValue());
        }
        if (this.H != null) {
            dVar.b(36, this.H.longValue());
        }
        if (this.I != null) {
            dVar.a(37, this.I);
        }
        if (this.N != null) {
            dVar.a(38, this.N);
        }
        if (this.J != null) {
            dVar.a(39, this.J.intValue());
        }
        if (this.O != null) {
            dVar.a(41, this.O);
        }
        super.a(dVar);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof fx)) {
            return false;
        }
        fx fxVar = (fx) obj;
        if (this.c == null) {
            if (fxVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(fxVar.c)) {
            return false;
        }
        if (!i.a((Object[]) this.d, (Object[]) fxVar.d) || !i.a((Object[]) this.e, (Object[]) fxVar.e)) {
            return false;
        }
        if (this.f == null) {
            if (fxVar.f != null) {
                return false;
            }
        } else if (!this.f.equals(fxVar.f)) {
            return false;
        }
        if (this.g == null) {
            if (fxVar.g != null) {
                return false;
            }
        } else if (!this.g.equals(fxVar.g)) {
            return false;
        }
        if (this.h == null) {
            if (fxVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(fxVar.h)) {
            return false;
        }
        if (this.i == null) {
            if (fxVar.i != null) {
                return false;
            }
        } else if (!this.i.equals(fxVar.i)) {
            return false;
        }
        if (this.j == null) {
            if (fxVar.j != null) {
                return false;
            }
        } else if (!this.j.equals(fxVar.j)) {
            return false;
        }
        if (this.k == null) {
            if (fxVar.k != null) {
                return false;
            }
        } else if (!this.k.equals(fxVar.k)) {
            return false;
        }
        if (this.l == null) {
            if (fxVar.l != null) {
                return false;
            }
        } else if (!this.l.equals(fxVar.l)) {
            return false;
        }
        if (this.m == null) {
            if (fxVar.m != null) {
                return false;
            }
        } else if (!this.m.equals(fxVar.m)) {
            return false;
        }
        if (this.n == null) {
            if (fxVar.n != null) {
                return false;
            }
        } else if (!this.n.equals(fxVar.n)) {
            return false;
        }
        if (this.o == null) {
            if (fxVar.o != null) {
                return false;
            }
        } else if (!this.o.equals(fxVar.o)) {
            return false;
        }
        if (this.p == null) {
            if (fxVar.p != null) {
                return false;
            }
        } else if (!this.p.equals(fxVar.p)) {
            return false;
        }
        if (this.q == null) {
            if (fxVar.q != null) {
                return false;
            }
        } else if (!this.q.equals(fxVar.q)) {
            return false;
        }
        if (this.r == null) {
            if (fxVar.r != null) {
                return false;
            }
        } else if (!this.r.equals(fxVar.r)) {
            return false;
        }
        if (this.s == null) {
            if (fxVar.s != null) {
                return false;
            }
        } else if (!this.s.equals(fxVar.s)) {
            return false;
        }
        if (this.t == null) {
            if (fxVar.t != null) {
                return false;
            }
        } else if (!this.t.equals(fxVar.t)) {
            return false;
        }
        if (this.u == null) {
            if (fxVar.u != null) {
                return false;
            }
        } else if (!this.u.equals(fxVar.u)) {
            return false;
        }
        if (this.v == null) {
            if (fxVar.v != null) {
                return false;
            }
        } else if (!this.v.equals(fxVar.v)) {
            return false;
        }
        if (this.w == null) {
            if (fxVar.w != null) {
                return false;
            }
        } else if (!this.w.equals(fxVar.w)) {
            return false;
        }
        if (this.x == null) {
            if (fxVar.x != null) {
                return false;
            }
        } else if (!this.x.equals(fxVar.x)) {
            return false;
        }
        if (this.y == null) {
            if (fxVar.y != null) {
                return false;
            }
        } else if (!this.y.equals(fxVar.y)) {
            return false;
        }
        if (this.z == null) {
            if (fxVar.z != null) {
                return false;
            }
        } else if (!this.z.equals(fxVar.z)) {
            return false;
        }
        if (this.A == null) {
            if (fxVar.A != null) {
                return false;
            }
        } else if (!this.A.equals(fxVar.A)) {
            return false;
        }
        if (this.B == null) {
            if (fxVar.B != null) {
                return false;
            }
        } else if (!this.B.equals(fxVar.B)) {
            return false;
        }
        if (!i.a((Object[]) this.C, (Object[]) fxVar.C)) {
            return false;
        }
        if (this.D == null) {
            if (fxVar.D != null) {
                return false;
            }
        } else if (!this.D.equals(fxVar.D)) {
            return false;
        }
        if (this.E == null) {
            if (fxVar.E != null) {
                return false;
            }
        } else if (!this.E.equals(fxVar.E)) {
            return false;
        }
        if (this.L == null) {
            if (fxVar.L != null) {
                return false;
            }
        } else if (!this.L.equals(fxVar.L)) {
            return false;
        }
        if (this.M == null) {
            if (fxVar.M != null) {
                return false;
            }
        } else if (!this.M.equals(fxVar.M)) {
            return false;
        }
        if (this.F == null) {
            if (fxVar.F != null) {
                return false;
            }
        } else if (!this.F.equals(fxVar.F)) {
            return false;
        }
        if (this.G == null) {
            if (fxVar.G != null) {
                return false;
            }
        } else if (!this.G.equals(fxVar.G)) {
            return false;
        }
        if (this.H == null) {
            if (fxVar.H != null) {
                return false;
            }
        } else if (!this.H.equals(fxVar.H)) {
            return false;
        }
        if (this.I == null) {
            if (fxVar.I != null) {
                return false;
            }
        } else if (!this.I.equals(fxVar.I)) {
            return false;
        }
        if (this.N == null) {
            if (fxVar.N != null) {
                return false;
            }
        } else if (!this.N.equals(fxVar.N)) {
            return false;
        }
        if (this.J == null) {
            if (fxVar.J != null) {
                return false;
            }
        } else if (!this.J.equals(fxVar.J)) {
            return false;
        }
        if (this.O == null) {
            if (fxVar.O != null) {
                return false;
            }
        } else if (!this.O.equals(fxVar.O)) {
            return false;
        }
        return (this.a == null || this.a.b()) ? fxVar.a == null || fxVar.a.b() : this.a.equals(fxVar.a);
    }

    public final int hashCode() {
        int i2 = 0;
        int hashCode = (((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((((527 + getClass().getName().hashCode()) * 31) + (this.c == null ? 0 : this.c.hashCode())) * 31) + i.a((Object[]) this.d)) * 31) + i.a((Object[]) this.e)) * 31) + (this.f == null ? 0 : this.f.hashCode())) * 31) + (this.g == null ? 0 : this.g.hashCode())) * 31) + (this.h == null ? 0 : this.h.hashCode())) * 31) + (this.i == null ? 0 : this.i.hashCode())) * 31) + (this.j == null ? 0 : this.j.hashCode())) * 31) + (this.k == null ? 0 : this.k.hashCode())) * 31) + (this.l == null ? 0 : this.l.hashCode())) * 31) + (this.m == null ? 0 : this.m.hashCode())) * 31) + (this.n == null ? 0 : this.n.hashCode())) * 31) + (this.o == null ? 0 : this.o.hashCode())) * 31) + (this.p == null ? 0 : this.p.hashCode())) * 31) + (this.q == null ? 0 : this.q.hashCode())) * 31) + (this.r == null ? 0 : this.r.hashCode())) * 31) + (this.s == null ? 0 : this.s.hashCode())) * 31) + (this.t == null ? 0 : this.t.hashCode())) * 31) + (this.u == null ? 0 : this.u.hashCode())) * 31) + (this.v == null ? 0 : this.v.hashCode())) * 31) + (this.w == null ? 0 : this.w.hashCode())) * 31) + (this.x == null ? 0 : this.x.hashCode())) * 31) + (this.y == null ? 0 : this.y.hashCode())) * 31) + (this.z == null ? 0 : this.z.hashCode())) * 31) + (this.A == null ? 0 : this.A.hashCode())) * 31) + (this.B == null ? 0 : this.B.hashCode())) * 31) + i.a((Object[]) this.C)) * 31) + (this.D == null ? 0 : this.D.hashCode())) * 31) + (this.E == null ? 0 : this.E.hashCode())) * 31) + (this.L == null ? 0 : this.L.hashCode())) * 31) + (this.M == null ? 0 : this.M.hashCode())) * 31) + (this.F == null ? 0 : this.F.hashCode())) * 31) + (this.G == null ? 0 : this.G.hashCode())) * 31) + (this.H == null ? 0 : this.H.hashCode())) * 31) + (this.I == null ? 0 : this.I.hashCode())) * 31) + (this.N == null ? 0 : this.N.hashCode())) * 31) + (this.J == null ? 0 : this.J.hashCode())) * 31) + (this.O == null ? 0 : this.O.hashCode())) * 31;
        if (this.a != null && !this.a.b()) {
            i2 = this.a.hashCode();
        }
        return hashCode + i2;
    }
}
