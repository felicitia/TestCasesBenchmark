package com.google.zxing.common.reedsolomon;

/* compiled from: GenericGFPoly */
final class b {
    private final a a;
    private final int[] b;

    b(a aVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = aVar;
        int length = iArr.length;
        if (length <= 1 || iArr[0] != 0) {
            this.b = iArr;
            return;
        }
        int i = 1;
        while (i < length && iArr[i] == 0) {
            i++;
        }
        if (i == length) {
            this.b = new int[]{0};
            return;
        }
        this.b = new int[(length - i)];
        System.arraycopy(iArr, i, this.b, 0, this.b.length);
    }

    /* access modifiers changed from: 0000 */
    public int[] a() {
        return this.b;
    }

    /* access modifiers changed from: 0000 */
    public int b() {
        return this.b.length - 1;
    }

    /* access modifiers changed from: 0000 */
    public boolean c() {
        return this.b[0] == 0;
    }

    /* access modifiers changed from: 0000 */
    public int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    /* access modifiers changed from: 0000 */
    public b a(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c()) {
            return bVar;
        } else {
            if (bVar.c()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = bVar.b;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = a.b(iArr[i - length], iArr2[i]);
            }
            return new b(this.a, iArr4);
        }
    }

    /* access modifiers changed from: 0000 */
    public b b(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (c() || bVar.c()) {
            return this.a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = bVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = a.b(iArr3[i4], this.a.c(i2, iArr2[i3]));
                }
            }
            return new b(this.a, iArr3);
        }
    }

    /* access modifiers changed from: 0000 */
    public b a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.a.c(this.b[i3], i2);
            }
            return new b(this.a, iArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public b[] c(b bVar) {
        if (!this.a.equals(bVar.a)) {
            throw new IllegalArgumentException("GenericGFPolys do not have same GenericGF field");
        } else if (bVar.c()) {
            throw new IllegalArgumentException("Divide by 0");
        } else {
            b a2 = this.a.a();
            int c = this.a.c(bVar.a(bVar.b()));
            b bVar2 = a2;
            b bVar3 = this;
            while (bVar3.b() >= bVar.b() && !bVar3.c()) {
                int b2 = bVar3.b() - bVar.b();
                int c2 = this.a.c(bVar3.a(bVar3.b()), c);
                b a3 = bVar.a(b2, c2);
                bVar2 = bVar2.a(this.a.a(b2, c2));
                bVar3 = bVar3.a(a3);
            }
            return new b[]{bVar2, bVar3};
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(8 * b());
        for (int b2 = b(); b2 >= 0; b2--) {
            int a2 = a(b2);
            if (a2 != 0) {
                if (a2 < 0) {
                    sb.append(" - ");
                    a2 = -a2;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (b2 == 0 || a2 != 1) {
                    int b3 = this.a.b(a2);
                    if (b3 == 0) {
                        sb.append('1');
                    } else if (b3 == 1) {
                        sb.append('a');
                    } else {
                        sb.append("a^");
                        sb.append(b3);
                    }
                }
                if (b2 != 0) {
                    if (b2 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(b2);
                    }
                }
            }
        }
        return sb.toString();
    }
}
