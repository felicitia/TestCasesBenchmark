package com.onfido.d.b.a;

import com.onfido.d.m;

final class h extends g {
    private final boolean a;

    h(c cVar, boolean z) {
        super(cVar);
        this.a = z;
    }

    private void a(d[] dVarArr, a aVar) {
        for (int i = 0; i < dVarArr.length; i++) {
            d dVar = dVarArr[i];
            if (dVarArr[i] != null) {
                int g = dVar.g() % 30;
                int h = dVar.h();
                if (h <= aVar.c()) {
                    if (!this.a) {
                        h += 2;
                    }
                    switch (h % 3) {
                        case 0:
                            if ((g * 3) + 1 == aVar.d()) {
                                break;
                            } else {
                                dVarArr[i] = null;
                                break;
                            }
                        case 1:
                            if (g / 3 != aVar.b() || g % 3 != aVar.e()) {
                                dVarArr[i] = null;
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (g + 1 == aVar.a()) {
                                break;
                            } else {
                                dVarArr[i] = null;
                                break;
                            }
                    }
                } else {
                    dVarArr[i] = null;
                }
            }
        }
    }

    private void b(a aVar) {
        c a2 = a();
        m e = this.a ? a2.e() : a2.f();
        m g = this.a ? a2.g() : a2.h();
        int b = b((int) g.b());
        d[] b2 = b();
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        for (int b3 = b((int) e.b()); b3 < b; b3++) {
            if (b2[b3] != null) {
                d dVar = b2[b3];
                dVar.b();
                int h = dVar.h() - i;
                if (h == 0) {
                    i2++;
                } else {
                    if (h == 1) {
                        i3 = Math.max(i3, i2);
                        i = dVar.h();
                    } else if (dVar.h() >= aVar.c()) {
                        b2[b3] = null;
                    } else {
                        i = dVar.h();
                    }
                    i2 = 1;
                }
            }
        }
    }

    private void f() {
        d[] b;
        for (d dVar : b()) {
            if (dVar != null) {
                dVar.b();
            }
        }
    }

    /* access modifiers changed from: 0000 */
    public void a(a aVar) {
        d[] b = b();
        f();
        a(b, aVar);
        c a2 = a();
        m e = this.a ? a2.e() : a2.f();
        m g = this.a ? a2.g() : a2.h();
        int b2 = b((int) e.b());
        int b3 = b((int) g.b());
        int i = -1;
        int i2 = 0;
        int i3 = 1;
        while (b2 < b3) {
            if (b[b2] != null) {
                d dVar = b[b2];
                int h = dVar.h() - i;
                if (h == 0) {
                    i2++;
                } else {
                    if (h == 1) {
                        i3 = Math.max(i3, i2);
                        i = dVar.h();
                    } else if (h < 0 || dVar.h() >= aVar.c() || h > b2) {
                        b[b2] = null;
                    } else {
                        if (i3 > 2) {
                            h *= i3 - 2;
                        }
                        boolean z = h >= b2;
                        for (int i4 = 1; i4 <= h && !z; i4++) {
                            z = b[b2 - i4] != null;
                        }
                        if (z) {
                            b[b2] = null;
                        } else {
                            i = dVar.h();
                        }
                    }
                    i2 = 1;
                }
            }
            b2++;
        }
    }

    /* access modifiers changed from: 0000 */
    public int[] c() {
        d[] b;
        a d = d();
        if (d == null) {
            return null;
        }
        b(d);
        int[] iArr = new int[d.c()];
        for (d dVar : b()) {
            if (dVar != null) {
                int h = dVar.h();
                if (h < iArr.length) {
                    iArr[h] = iArr[h] + 1;
                }
            }
        }
        return iArr;
    }

    /* access modifiers changed from: 0000 */
    public a d() {
        d[] b = b();
        b bVar = new b();
        b bVar2 = new b();
        b bVar3 = new b();
        b bVar4 = new b();
        for (d dVar : b) {
            if (dVar != null) {
                dVar.b();
                int g = dVar.g() % 30;
                int h = dVar.h();
                if (!this.a) {
                    h += 2;
                }
                switch (h % 3) {
                    case 0:
                        bVar2.a((g * 3) + 1);
                        break;
                    case 1:
                        bVar4.a(g / 3);
                        bVar3.a(g % 3);
                        break;
                    case 2:
                        bVar.a(g + 1);
                        break;
                }
            }
        }
        if (bVar.a().length == 0 || bVar2.a().length == 0 || bVar3.a().length == 0 || bVar4.a().length == 0 || bVar.a()[0] <= 0 || bVar2.a()[0] + bVar3.a()[0] < 3 || bVar2.a()[0] + bVar3.a()[0] > 90) {
            return null;
        }
        a aVar = new a(bVar.a()[0], bVar2.a()[0], bVar3.a()[0], bVar4.a()[0]);
        a(b, aVar);
        return aVar;
    }

    /* access modifiers changed from: 0000 */
    public boolean e() {
        return this.a;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("IsLeft: ");
        sb.append(this.a);
        sb.append(10);
        sb.append(super.toString());
        return sb.toString();
    }
}
