package com.onfido.d.b.a.a;

final class c {
    private final b a;
    private final int[] b;

    c(b bVar, int[] iArr) {
        if (iArr.length == 0) {
            throw new IllegalArgumentException();
        }
        this.a = bVar;
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
    public int a() {
        return this.b.length - 1;
    }

    /* access modifiers changed from: 0000 */
    public int a(int i) {
        return this.b[(this.b.length - 1) - i];
    }

    /* access modifiers changed from: 0000 */
    public c a(int i, int i2) {
        if (i < 0) {
            throw new IllegalArgumentException();
        } else if (i2 == 0) {
            return this.a.a();
        } else {
            int length = this.b.length;
            int[] iArr = new int[(i + length)];
            for (int i3 = 0; i3 < length; i3++) {
                iArr[i3] = this.a.d(this.b[i3], i2);
            }
            return new c(this.a, iArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public c a(c cVar) {
        if (!this.a.equals(cVar.a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (b()) {
            return cVar;
        } else {
            if (cVar.b()) {
                return this;
            }
            int[] iArr = this.b;
            int[] iArr2 = cVar.b;
            if (iArr.length > iArr2.length) {
                int[] iArr3 = iArr;
                iArr = iArr2;
                iArr2 = iArr3;
            }
            int[] iArr4 = new int[iArr2.length];
            int length = iArr2.length - iArr.length;
            System.arraycopy(iArr2, 0, iArr4, 0, length);
            for (int i = length; i < iArr2.length; i++) {
                iArr4[i] = this.a.b(iArr[i - length], iArr2[i]);
            }
            return new c(this.a, iArr4);
        }
    }

    /* access modifiers changed from: 0000 */
    public int b(int i) {
        if (i == 0) {
            return a(0);
        }
        if (i == 1) {
            int i2 = 0;
            for (int b2 : this.b) {
                i2 = this.a.b(i2, b2);
            }
            return i2;
        }
        int i3 = this.b[0];
        int length = this.b.length;
        for (int i4 = 1; i4 < length; i4++) {
            i3 = this.a.b(this.a.d(i, i3), this.b[i4]);
        }
        return i3;
    }

    /* access modifiers changed from: 0000 */
    public c b(c cVar) {
        if (this.a.equals(cVar.a)) {
            return cVar.b() ? this : a(cVar.c());
        }
        throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
    }

    /* access modifiers changed from: 0000 */
    public boolean b() {
        return this.b[0] == 0;
    }

    /* access modifiers changed from: 0000 */
    public c c() {
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i = 0; i < length; i++) {
            iArr[i] = this.a.c(0, this.b[i]);
        }
        return new c(this.a, iArr);
    }

    /* access modifiers changed from: 0000 */
    public c c(int i) {
        if (i == 0) {
            return this.a.a();
        }
        if (i == 1) {
            return this;
        }
        int length = this.b.length;
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = this.a.d(this.b[i2], i);
        }
        return new c(this.a, iArr);
    }

    /* access modifiers changed from: 0000 */
    public c c(c cVar) {
        if (!this.a.equals(cVar.a)) {
            throw new IllegalArgumentException("ModulusPolys do not have same ModulusGF field");
        } else if (b() || cVar.b()) {
            return this.a.a();
        } else {
            int[] iArr = this.b;
            int length = iArr.length;
            int[] iArr2 = cVar.b;
            int length2 = iArr2.length;
            int[] iArr3 = new int[((length + length2) - 1)];
            for (int i = 0; i < length; i++) {
                int i2 = iArr[i];
                for (int i3 = 0; i3 < length2; i3++) {
                    int i4 = i + i3;
                    iArr3[i4] = this.a.b(iArr3[i4], this.a.d(i2, iArr2[i3]));
                }
            }
            return new c(this.a, iArr3);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(a() * 8);
        for (int a2 = a(); a2 >= 0; a2--) {
            int a3 = a(a2);
            if (a3 != 0) {
                if (a3 < 0) {
                    sb.append(" - ");
                    a3 = -a3;
                } else if (sb.length() > 0) {
                    sb.append(" + ");
                }
                if (a2 == 0 || a3 != 1) {
                    sb.append(a3);
                }
                if (a2 != 0) {
                    if (a2 == 1) {
                        sb.append('x');
                    } else {
                        sb.append("x^");
                        sb.append(a2);
                    }
                }
            }
        }
        return sb.toString();
    }
}
