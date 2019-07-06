package com.onfido.d.b.a;

import com.onfido.d.a.b;
import com.onfido.d.b.a.a.a;
import com.onfido.d.d;
import com.onfido.d.f;
import com.onfido.d.h;
import com.onfido.d.m;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;

public final class j {
    private static final a a = new a();

    private static int a(int i) {
        return 2 << i;
    }

    private static int a(f fVar, int i, int i2, boolean z) {
        int i3 = z ? 1 : -1;
        d dVar = null;
        int i4 = i - i3;
        if (a(fVar, i4)) {
            dVar = fVar.a(i4).c(i2);
        }
        if (dVar != null) {
            return z ? dVar.e() : dVar.d();
        }
        d a2 = fVar.a(i).a(i2);
        if (a2 != null) {
            return z ? a2.d() : a2.e();
        }
        if (a(fVar, i4)) {
            a2 = fVar.a(i4).a(i2);
        }
        if (a2 != null) {
            return z ? a2.e() : a2.d();
        }
        int i5 = 0;
        while (true) {
            i -= i3;
            if (!a(fVar, i)) {
                return z ? fVar.e().a() : fVar.e().b();
            }
            d[] b = fVar.a(i).b();
            int length = b.length;
            for (int i6 = 0; i6 < length; i6++) {
                d dVar2 = b[i6];
                if (dVar2 != null) {
                    return (z ? dVar2.e() : dVar2.d()) + (i3 * i5 * (dVar2.e() - dVar2.d()));
                }
            }
            i5++;
        }
    }

    private static int a(int[] iArr) {
        int i = -1;
        for (int max : iArr) {
            i = Math.max(i, max);
        }
        return i;
    }

    private static int a(int[] iArr, int[] iArr2, int i) {
        if ((iArr2 == null || iArr2.length <= (i / 2) + 3) && i >= 0 && i <= 512) {
            return a.a(iArr, i, iArr2);
        }
        throw d.a();
    }

    private static com.onfido.d.a.d a(int i, int[] iArr, int[] iArr2, int[] iArr3, int[][] iArr4) {
        int[] iArr5 = new int[iArr3.length];
        int i2 = 100;
        while (true) {
            int i3 = i2 - 1;
            if (i2 > 0) {
                for (int i4 = 0; i4 < iArr5.length; i4++) {
                    iArr[iArr3[i4]] = iArr4[i4][iArr5[i4]];
                }
                try {
                    return a(iArr, i, iArr2);
                } catch (d unused) {
                    if (iArr5.length == 0) {
                        throw d.a();
                    }
                    int i5 = 0;
                    while (true) {
                        if (i5 >= iArr5.length) {
                            break;
                        } else if (iArr5[i5] < iArr4[i5].length - 1) {
                            iArr5[i5] = iArr5[i5] + 1;
                            break;
                        } else {
                            iArr5[i5] = 0;
                            if (i5 == iArr5.length - 1) {
                                throw d.a();
                            }
                            i5++;
                        }
                    }
                    i2 = i3;
                }
            } else {
                throw d.a();
            }
        }
    }

    public static com.onfido.d.a.d a(b bVar, m mVar, m mVar2, m mVar3, m mVar4, int i, int i2) {
        h hVar;
        g gVar;
        int i3;
        int i4;
        c cVar = new c(bVar, mVar, mVar2, mVar3, mVar4);
        h hVar2 = null;
        f fVar = null;
        h hVar3 = null;
        c cVar2 = cVar;
        int i5 = 0;
        while (true) {
            if (i5 >= 2) {
                hVar = hVar2;
                break;
            }
            if (mVar != null) {
                hVar2 = a(bVar, cVar2, mVar, true, i, i2);
            }
            hVar = hVar2;
            if (mVar3 != null) {
                hVar3 = a(bVar, cVar2, mVar3, false, i, i2);
            }
            fVar = a(hVar, hVar3);
            if (fVar == null) {
                throw h.a();
            } else if (i5 != 0 || fVar.e() == null || (fVar.e().c() >= cVar2.c() && fVar.e().d() <= cVar2.d())) {
                fVar.a(cVar2);
            } else {
                cVar2 = fVar.e();
                i5++;
                hVar2 = hVar;
            }
        }
        int b = fVar.b() + 1;
        fVar.a(0, (g) hVar);
        fVar.a(b, (g) hVar3);
        boolean z = hVar != null;
        int i6 = i;
        int i7 = i2;
        int i8 = 1;
        while (i8 <= b) {
            int i9 = z ? i8 : b - i8;
            if (fVar.a(i9) == null) {
                if (i9 == 0 || i9 == b) {
                    gVar = new h(cVar2, i9 == 0);
                } else {
                    gVar = new g(cVar2);
                }
                fVar.a(i9, gVar);
                int c = cVar2.c();
                int i10 = i7;
                int i11 = i6;
                int i12 = -1;
                while (c <= cVar2.d()) {
                    int a2 = a(fVar, i9, c, z);
                    if (a2 >= 0 && a2 <= cVar2.b()) {
                        i4 = a2;
                    } else if (i12 != -1) {
                        i4 = i12;
                    } else {
                        i3 = i10;
                        i10 = i3;
                        c++;
                    }
                    int i13 = i10;
                    d a3 = a(bVar, cVar2.a(), cVar2.b(), z, i4, c, i11, i13);
                    if (a3 != null) {
                        gVar.a(c, a3);
                        i11 = Math.min(i11, a3.c());
                        i10 = Math.max(i13, a3.c());
                        i12 = i4;
                        c++;
                    } else {
                        i3 = i13;
                        i10 = i3;
                        c++;
                    }
                }
                i6 = i11;
                i7 = i10;
            }
            i8++;
        }
        return a(fVar);
    }

    private static com.onfido.d.a.d a(f fVar) {
        b[][] b = b(fVar);
        a(fVar, b);
        ArrayList arrayList = new ArrayList();
        int[] iArr = new int[(fVar.c() * fVar.b())];
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < fVar.c(); i++) {
            int i2 = 0;
            while (i2 < fVar.b()) {
                int i3 = i2 + 1;
                int[] a2 = b[i][i3].a();
                int b2 = (fVar.b() * i) + i2;
                if (a2.length == 0) {
                    arrayList.add(Integer.valueOf(b2));
                } else if (a2.length == 1) {
                    iArr[b2] = a2[0];
                } else {
                    arrayList3.add(Integer.valueOf(b2));
                    arrayList2.add(a2);
                }
                i2 = i3;
            }
        }
        int[][] iArr2 = new int[arrayList2.size()][];
        for (int i4 = 0; i4 < iArr2.length; i4++) {
            iArr2[i4] = (int[]) arrayList2.get(i4);
        }
        return a(fVar.d(), iArr, com.onfido.d.b.a.a((Collection<Integer>) arrayList), com.onfido.d.b.a.a((Collection<Integer>) arrayList3), iArr2);
    }

    private static com.onfido.d.a.d a(int[] iArr, int i, int[] iArr2) {
        if (iArr.length == 0) {
            throw f.a();
        }
        int i2 = 1 << (i + 1);
        int a2 = a(iArr, iArr2, i2);
        a(iArr, i2);
        com.onfido.d.a.d a3 = e.a(iArr, String.valueOf(i));
        a3.a(Integer.valueOf(a2));
        a3.b(Integer.valueOf(iArr2.length));
        return a3;
    }

    private static c a(h hVar) {
        if (hVar == null) {
            return null;
        }
        int[] c = hVar.c();
        if (c == null) {
            return null;
        }
        int a2 = a(c);
        int i = 0;
        int i2 = 0;
        for (int i3 : c) {
            i2 += a2 - i3;
            if (i3 > 0) {
                break;
            }
        }
        d[] b = hVar.b();
        int i4 = 0;
        while (i2 > 0 && b[i4] == null) {
            i2--;
            i4++;
        }
        for (int length = c.length - 1; length >= 0; length--) {
            i += a2 - c[length];
            if (c[length] > 0) {
                break;
            }
        }
        int length2 = b.length - 1;
        while (i > 0 && b[length2] == null) {
            i--;
            length2--;
        }
        return hVar.a().a(i2, i, hVar.e());
    }

    private static d a(b bVar, int i, int i2, boolean z, int i3, int i4, int i5, int i6) {
        int i7;
        int b = b(bVar, i, i2, z, i3, i4);
        int[] a2 = a(bVar, i, i2, z, b, i4);
        if (a2 == null) {
            return null;
        }
        int a3 = com.onfido.d.a.a.a.a(a2);
        if (z) {
            int i8 = b;
            b += a3;
            i7 = i8;
        } else {
            for (int i9 = 0; i9 < a2.length / 2; i9++) {
                int i10 = a2[i9];
                a2[i9] = a2[(a2.length - 1) - i9];
                a2[(a2.length - 1) - i9] = i10;
            }
            i7 = b - a3;
        }
        if (!a(a3, i5, i6)) {
            return null;
        }
        int a4 = i.a(a2);
        int a5 = com.onfido.d.b.a.a(a4);
        if (a5 == -1) {
            return null;
        }
        return new d(i7, b, c(a4), a5);
    }

    private static f a(h hVar, h hVar2) {
        if (hVar == null && hVar2 == null) {
            return null;
        }
        a b = b(hVar, hVar2);
        if (b == null) {
            return null;
        }
        return new f(b, c.a(a(hVar), a(hVar2)));
    }

    private static h a(b bVar, c cVar, m mVar, boolean z, int i, int i2) {
        boolean z2 = z;
        h hVar = new h(cVar, z2);
        int i3 = 0;
        while (i3 < 2) {
            int i4 = i3 == 0 ? 1 : -1;
            int a2 = (int) mVar.a();
            int b = (int) mVar.b();
            while (b <= cVar.d() && b >= cVar.c()) {
                d a3 = a(bVar, 0, bVar.b(), z2, a2, b, i, i2);
                if (a3 != null) {
                    hVar.a(b, a3);
                    a2 = z2 ? a3.d() : a3.e();
                }
                b += i4;
            }
            i3++;
        }
        return hVar;
    }

    private static void a(f fVar, b[][] bVarArr) {
        b bVar = bVarArr[0][1];
        int[] a2 = bVar.a();
        int b = (fVar.b() * fVar.c()) - a(fVar.d());
        if (a2.length != 0) {
            if (a2[0] != b) {
                bVar.a(b);
            }
        } else if (b <= 0 || b > 928) {
            throw h.a();
        } else {
            bVar.a(b);
        }
    }

    private static void a(int[] iArr, int i) {
        if (iArr.length < 4) {
            throw f.a();
        }
        int i2 = iArr[0];
        if (i2 > iArr.length) {
            throw f.a();
        } else if (i2 != 0) {
        } else {
            if (i < iArr.length) {
                iArr[0] = iArr.length - i;
                return;
            }
            throw f.a();
        }
    }

    private static boolean a(int i, int i2, int i3) {
        return i2 + -2 <= i && i <= i3 + 2;
    }

    private static boolean a(f fVar, int i) {
        return i >= 0 && i <= fVar.b() + 1;
    }

    private static int[] a(b bVar, int i, int i2, boolean z, int i3, int i4) {
        int[] iArr = new int[8];
        int i5 = z ? 1 : -1;
        boolean z2 = z;
        int i6 = 0;
        while (true) {
            if (!z) {
                if (i3 < i) {
                    break;
                }
            } else if (i3 >= i2) {
                break;
            }
            if (i6 >= 8) {
                break;
            } else if (bVar.a(i3, i4) == z2) {
                iArr[i6] = iArr[i6] + 1;
                i3 += i5;
            } else {
                i6++;
                z2 = !z2;
            }
        }
        if (i6 == 8) {
            return iArr;
        }
        if (z) {
            i = i2;
        }
        if (i3 == i && i6 == 7) {
            return iArr;
        }
        return null;
    }

    private static int b(b bVar, int i, int i2, boolean z, int i3, int i4) {
        boolean z2 = z;
        int i5 = z ? -1 : 1;
        int i6 = i3;
        for (int i7 = 0; i7 < 2; i7++) {
            while (true) {
                if (!z2) {
                    if (i6 >= i2) {
                        break;
                    }
                } else if (i6 < i) {
                    break;
                }
                if (z2 != bVar.a(i6, i4)) {
                    break;
                } else if (Math.abs(i3 - i6) > 2) {
                    return i3;
                } else {
                    i6 += i5;
                }
            }
            i5 = -i5;
            z2 = !z2;
        }
        return i6;
    }

    private static int b(int[] iArr) {
        return ((((iArr[0] - iArr[2]) + iArr[4]) - iArr[6]) + 9) % 9;
    }

    private static a b(h hVar, h hVar2) {
        if (hVar != null) {
            a d = hVar.d();
            if (d != null) {
                if (hVar2 != null) {
                    a d2 = hVar2.d();
                    if (!(d2 == null || d.a() == d2.a() || d.b() == d2.b() || d.c() == d2.c())) {
                        return null;
                    }
                }
                return d;
            }
        }
        if (hVar2 == null) {
            return null;
        }
        return hVar2.d();
    }

    private static int[] b(int i) {
        int[] iArr = new int[8];
        int i2 = 0;
        int i3 = 7;
        while (true) {
            int i4 = i & 1;
            if (i4 != i2) {
                i3--;
                if (i3 < 0) {
                    return iArr;
                }
                i2 = i4;
            }
            iArr[i3] = iArr[i3] + 1;
            i >>= 1;
        }
    }

    private static b[][] b(f fVar) {
        g[] a2;
        d[] b;
        b[][] bVarArr = (b[][]) Array.newInstance(b.class, new int[]{fVar.c(), fVar.b() + 2});
        for (int i = 0; i < bVarArr.length; i++) {
            for (int i2 = 0; i2 < bVarArr[i].length; i2++) {
                bVarArr[i][i2] = new b();
            }
        }
        int i3 = 0;
        for (g gVar : fVar.a()) {
            if (gVar != null) {
                for (d dVar : gVar.b()) {
                    if (dVar != null) {
                        int h = dVar.h();
                        if (h >= 0 && h < bVarArr.length) {
                            bVarArr[h][i3].a(dVar.g());
                        }
                    }
                }
            }
            i3++;
        }
        return bVarArr;
    }

    private static int c(int i) {
        return b(b(i));
    }
}
