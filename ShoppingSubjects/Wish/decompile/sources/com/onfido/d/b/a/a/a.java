package com.onfido.d.b.a.a;

import com.onfido.d.d;

public final class a {
    private final b a = b.a;

    private int[] a(c cVar) {
        int a2 = cVar.a();
        int[] iArr = new int[a2];
        int i = 0;
        for (int i2 = 1; i2 < this.a.c() && i < a2; i2++) {
            if (cVar.b(i2) == 0) {
                iArr[i] = this.a.c(i2);
                i++;
            }
        }
        if (i == a2) {
            return iArr;
        }
        throw d.a();
    }

    private int[] a(c cVar, c cVar2, int[] iArr) {
        int a2 = cVar2.a();
        int[] iArr2 = new int[a2];
        for (int i = 1; i <= a2; i++) {
            iArr2[a2 - i] = this.a.d(i, cVar2.a(i));
        }
        c cVar3 = new c(this.a, iArr2);
        int length = iArr.length;
        int[] iArr3 = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            int c = this.a.c(iArr[i2]);
            iArr3[i2] = this.a.d(this.a.c(0, cVar.b(c)), this.a.c(cVar3.b(c)));
        }
        return iArr3;
    }

    private c[] a(c cVar, c cVar2, int i) {
        if (cVar.a() < cVar2.a()) {
            c cVar3 = cVar2;
            cVar2 = cVar;
            cVar = cVar3;
        }
        c a2 = this.a.a();
        c b = this.a.b();
        c cVar4 = cVar2;
        c cVar5 = cVar;
        c cVar6 = cVar4;
        while (cVar6.a() >= i / 2) {
            if (cVar6.b()) {
                throw d.a();
            }
            c a3 = this.a.a();
            int c = this.a.c(cVar6.a(cVar6.a()));
            while (cVar5.a() >= cVar6.a() && !cVar5.b()) {
                int a4 = cVar5.a() - cVar6.a();
                int d = this.a.d(cVar5.a(cVar5.a()), c);
                a3 = a3.a(this.a.a(a4, d));
                cVar5 = cVar5.b(cVar6.a(a4, d));
            }
            c cVar7 = cVar5;
            cVar5 = cVar6;
            cVar6 = cVar7;
            c cVar8 = b;
            b = a3.c(b).b(a2).c();
            a2 = cVar8;
        }
        int a5 = b.a(0);
        if (a5 == 0) {
            throw d.a();
        }
        int c2 = this.a.c(a5);
        return new c[]{b.c(c2), cVar6.c(c2)};
    }

    public int a(int[] iArr, int i, int[] iArr2) {
        c cVar = new c(this.a, iArr);
        int[] iArr3 = new int[i];
        boolean z = false;
        for (int i2 = i; i2 > 0; i2--) {
            int b = cVar.b(this.a.a(i2));
            iArr3[i - i2] = b;
            if (b != 0) {
                z = true;
            }
        }
        if (!z) {
            return 0;
        }
        c b2 = this.a.b();
        if (iArr2 != null) {
            c cVar2 = b2;
            for (int length : iArr2) {
                cVar2 = cVar2.c(new c(this.a, new int[]{this.a.c(0, this.a.a((iArr.length - 1) - length)), 1}));
            }
        }
        c[] a2 = a(this.a.a(i, 1), new c(this.a, iArr3), i);
        c cVar3 = a2[0];
        c cVar4 = a2[1];
        int[] a3 = a(cVar3);
        int[] a4 = a(cVar4, cVar3, a3);
        for (int i3 = 0; i3 < a3.length; i3++) {
            int length2 = (iArr.length - 1) - this.a.b(a3[i3]);
            if (length2 < 0) {
                throw d.a();
            }
            iArr[length2] = this.a.c(iArr[length2], a4[i3]);
        }
        return a3.length;
    }
}
