package com.google.zxing.common.reedsolomon;

/* compiled from: GenericGF */
public final class a {
    public static final a a = new a(4201, 4096, 1);
    public static final a b = new a(1033, 1024, 1);
    public static final a c = new a(67, 64, 1);
    public static final a d = new a(19, 16, 1);
    public static final a e = new a(285, 256, 0);
    public static final a f;
    public static final a g;
    public static final a h = c;
    private final int[] i;
    private final int[] j;
    private final b k;
    private final b l;
    private final int m;
    private final int n;
    private final int o;

    static int b(int i2, int i3) {
        return i2 ^ i3;
    }

    static {
        a aVar = new a(301, 256, 1);
        f = aVar;
        g = aVar;
    }

    public a(int i2, int i3, int i4) {
        this.n = i2;
        this.m = i3;
        this.o = i4;
        this.i = new int[i3];
        this.j = new int[i3];
        int i5 = 1;
        for (int i6 = 0; i6 < i3; i6++) {
            this.i[i6] = i5;
            i5 <<= 1;
            if (i5 >= i3) {
                i5 = (i5 ^ i2) & (i3 - 1);
            }
        }
        for (int i7 = 0; i7 < i3 - 1; i7++) {
            this.j[this.i[i7]] = i7;
        }
        this.k = new b(this, new int[]{0});
        this.l = new b(this, new int[]{1});
    }

    /* access modifiers changed from: 0000 */
    public b a() {
        return this.k;
    }

    /* access modifiers changed from: 0000 */
    public b a(int i2, int i3) {
        if (i2 < 0) {
            throw new IllegalArgumentException();
        } else if (i3 == 0) {
            return this.k;
        } else {
            int[] iArr = new int[(i2 + 1)];
            iArr[0] = i3;
            return new b(this, iArr);
        }
    }

    /* access modifiers changed from: 0000 */
    public int a(int i2) {
        return this.i[i2];
    }

    /* access modifiers changed from: 0000 */
    public int b(int i2) {
        if (i2 != 0) {
            return this.j[i2];
        }
        throw new IllegalArgumentException();
    }

    /* access modifiers changed from: 0000 */
    public int c(int i2) {
        if (i2 != 0) {
            return this.i[(this.m - this.j[i2]) - 1];
        }
        throw new ArithmeticException();
    }

    /* access modifiers changed from: 0000 */
    public int c(int i2, int i3) {
        if (i2 == 0 || i3 == 0) {
            return 0;
        }
        return this.i[(this.j[i2] + this.j[i3]) % (this.m - 1)];
    }

    public int b() {
        return this.o;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("GF(0x");
        sb.append(Integer.toHexString(this.n));
        sb.append(',');
        sb.append(this.m);
        sb.append(')');
        return sb.toString();
    }
}
