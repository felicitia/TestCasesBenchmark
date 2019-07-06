package com.onfido.d.b.a;

import java.util.Formatter;

final class f {
    private final a a;
    private final g[] b = new g[(this.d + 2)];
    private c c;
    private final int d;

    f(a aVar, c cVar) {
        this.a = aVar;
        this.d = aVar.a();
        this.c = cVar;
    }

    private static int a(int i, int i2, d dVar) {
        if (dVar == null) {
            return i2;
        }
        if (!dVar.a()) {
            if (dVar.a(i)) {
                dVar.b(i);
                return 0;
            }
            i2++;
        }
        return i2;
    }

    private void a(int i, int i2, d[] dVarArr) {
        d dVar = dVarArr[i2];
        d[] b2 = this.b[i - 1].b();
        int i3 = i + 1;
        d[] b3 = this.b[i3] != null ? this.b[i3].b() : b2;
        d[] dVarArr2 = new d[14];
        dVarArr2[2] = b2[i2];
        dVarArr2[3] = b3[i2];
        int i4 = 0;
        if (i2 > 0) {
            int i5 = i2 - 1;
            dVarArr2[0] = dVarArr[i5];
            dVarArr2[4] = b2[i5];
            dVarArr2[5] = b3[i5];
        }
        if (i2 > 1) {
            int i6 = i2 - 2;
            dVarArr2[8] = dVarArr[i6];
            dVarArr2[10] = b2[i6];
            dVarArr2[11] = b3[i6];
        }
        if (i2 < dVarArr.length - 1) {
            int i7 = i2 + 1;
            dVarArr2[1] = dVarArr[i7];
            dVarArr2[6] = b2[i7];
            dVarArr2[7] = b3[i7];
        }
        if (i2 < dVarArr.length - 2) {
            int i8 = i2 + 2;
            dVarArr2[9] = dVarArr[i8];
            dVarArr2[12] = b2[i8];
            dVarArr2[13] = b3[i8];
        }
        while (i4 < 14 && !a(dVar, dVarArr2[i4])) {
            i4++;
        }
    }

    private void a(g gVar) {
        if (gVar != null) {
            ((h) gVar).a(this.a);
        }
    }

    private static boolean a(d dVar, d dVar2) {
        if (dVar2 == null || !dVar2.a() || dVar2.f() != dVar.f()) {
            return false;
        }
        dVar.b(dVar2.h());
        return true;
    }

    private int f() {
        int g = g();
        if (g == 0) {
            return 0;
        }
        for (int i = 1; i < this.d + 1; i++) {
            d[] b2 = this.b[i].b();
            for (int i2 = 0; i2 < b2.length; i2++) {
                if (b2[i2] != null && !b2[i2].a()) {
                    a(i, i2, b2);
                }
            }
        }
        return g;
    }

    private int g() {
        h();
        return j() + i();
    }

    private void h() {
        if (this.b[0] != null && this.b[this.d + 1] != null) {
            d[] b2 = this.b[0].b();
            d[] b3 = this.b[this.d + 1].b();
            for (int i = 0; i < b2.length; i++) {
                if (!(b2[i] == null || b3[i] == null || b2[i].h() != b3[i].h())) {
                    for (int i2 = 1; i2 <= this.d; i2++) {
                        d dVar = this.b[i2].b()[i];
                        if (dVar != null) {
                            dVar.b(b2[i].h());
                            if (!dVar.a()) {
                                this.b[i2].b()[i] = null;
                            }
                        }
                    }
                }
            }
        }
    }

    private int i() {
        if (this.b[this.d + 1] == null) {
            return 0;
        }
        d[] b2 = this.b[this.d + 1].b();
        int i = 0;
        for (int i2 = 0; i2 < b2.length; i2++) {
            if (b2[i2] != null) {
                int h = b2[i2].h();
                int i3 = i;
                int i4 = 0;
                for (int i5 = this.d + 1; i5 > 0 && i4 < 2; i5--) {
                    d dVar = this.b[i5].b()[i2];
                    if (dVar != null) {
                        i4 = a(h, i4, dVar);
                        if (!dVar.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    private int j() {
        if (this.b[0] == null) {
            return 0;
        }
        d[] b2 = this.b[0].b();
        int i = 0;
        for (int i2 = 0; i2 < b2.length; i2++) {
            if (b2[i2] != null) {
                int h = b2[i2].h();
                int i3 = i;
                int i4 = 0;
                for (int i5 = 1; i5 < this.d + 1 && i4 < 2; i5++) {
                    d dVar = this.b[i5].b()[i2];
                    if (dVar != null) {
                        i4 = a(h, i4, dVar);
                        if (!dVar.a()) {
                            i3++;
                        }
                    }
                }
                i = i3;
            }
        }
        return i;
    }

    /* access modifiers changed from: 0000 */
    public g a(int i) {
        return this.b[i];
    }

    /* access modifiers changed from: 0000 */
    public void a(int i, g gVar) {
        this.b[i] = gVar;
    }

    /* access modifiers changed from: 0000 */
    public void a(c cVar) {
        this.c = cVar;
    }

    /* access modifiers changed from: 0000 */
    public g[] a() {
        a(this.b[0]);
        a(this.b[this.d + 1]);
        int i = 928;
        while (true) {
            int f = f();
            if (f > 0 && f < i) {
                i = f;
            }
        }
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.d;
    }

    /* access modifiers changed from: 0000 */
    public int c() {
        return this.a.c();
    }

    /* access modifiers changed from: 0000 */
    public int d() {
        return this.a.b();
    }

    /* access modifiers changed from: 0000 */
    public c e() {
        return this.c;
    }

    public String toString() {
        Throwable th;
        String str;
        Object[] objArr;
        g gVar = this.b[0];
        if (gVar == null) {
            gVar = this.b[this.d + 1];
        }
        Formatter formatter = new Formatter();
        int i = 0;
        while (i < gVar.b().length) {
            try {
                formatter.format("CW %3d:", new Object[]{Integer.valueOf(i)});
                for (int i2 = 0; i2 < this.d + 2; i2++) {
                    if (this.b[i2] == null) {
                        str = "    |   ";
                        objArr = new Object[0];
                    } else {
                        d dVar = this.b[i2].b()[i];
                        if (dVar == null) {
                            str = "    |   ";
                            objArr = new Object[0];
                        } else {
                            formatter.format(" %3d|%3d", new Object[]{Integer.valueOf(dVar.h()), Integer.valueOf(dVar.g())});
                        }
                    }
                    formatter.format(str, objArr);
                }
                formatter.format("%n", new Object[0]);
                i++;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        }
        String formatter2 = formatter.toString();
        formatter.close();
        return formatter2;
        throw th;
    }
}
