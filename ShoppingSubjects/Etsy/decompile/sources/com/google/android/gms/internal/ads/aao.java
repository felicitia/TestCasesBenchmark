package com.google.android.gms.internal.ads;

public final class aao implements Cloneable {
    private static final aap a = new aap();
    private boolean b;
    private int[] c;
    private aap[] d;
    private int e;

    aao() {
        this(10);
    }

    private aao(int i) {
        this.b = false;
        int c2 = c(i);
        this.c = new int[c2];
        this.d = new aap[c2];
        this.e = 0;
    }

    private static int c(int i) {
        int i2 = i << 2;
        int i3 = 4;
        while (true) {
            if (i3 >= 32) {
                break;
            }
            int i4 = (1 << i3) - 12;
            if (i2 <= i4) {
                i2 = i4;
                break;
            }
            i3++;
        }
        return i2 / 4;
    }

    private final int d(int i) {
        int i2 = this.e - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.c[i4];
            if (i5 < i) {
                i3 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i2 = i4 - 1;
            }
        }
        return i3 ^ -1;
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final aap a(int i) {
        int d2 = d(i);
        if (d2 < 0 || this.d[d2] == a) {
            return null;
        }
        return this.d[d2];
    }

    /* access modifiers changed from: 0000 */
    public final void a(int i, aap aap) {
        int d2 = d(i);
        if (d2 >= 0) {
            this.d[d2] = aap;
            return;
        }
        int i2 = d2 ^ -1;
        if (i2 >= this.e || this.d[i2] != a) {
            if (this.e >= this.c.length) {
                int c2 = c(this.e + 1);
                int[] iArr = new int[c2];
                aap[] aapArr = new aap[c2];
                System.arraycopy(this.c, 0, iArr, 0, this.c.length);
                System.arraycopy(this.d, 0, aapArr, 0, this.d.length);
                this.c = iArr;
                this.d = aapArr;
            }
            if (this.e - i2 != 0) {
                int i3 = i2 + 1;
                System.arraycopy(this.c, i2, this.c, i3, this.e - i2);
                System.arraycopy(this.d, i2, this.d, i3, this.e - i2);
            }
            this.c[i2] = i;
            this.d[i2] = aap;
            this.e++;
            return;
        }
        this.c[i2] = i;
        this.d[i2] = aap;
    }

    /* access modifiers changed from: 0000 */
    public final aap b(int i) {
        return this.d[i];
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.e;
        aao aao = new aao(i);
        System.arraycopy(this.c, 0, aao.c, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.d[i2] != null) {
                aao.d[i2] = (aap) this.d[i2].clone();
            }
        }
        aao.e = i;
        return aao;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof aao)) {
            return false;
        }
        aao aao = (aao) obj;
        if (this.e != aao.e) {
            return false;
        }
        int[] iArr = this.c;
        int[] iArr2 = aao.c;
        int i = this.e;
        int i2 = 0;
        while (true) {
            if (i2 >= i) {
                z = true;
                break;
            } else if (iArr[i2] != iArr2[i2]) {
                z = false;
                break;
            } else {
                i2++;
            }
        }
        if (z) {
            aap[] aapArr = this.d;
            aap[] aapArr2 = aao.d;
            int i3 = this.e;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!aapArr[i4].equals(aapArr2[i4])) {
                    z2 = false;
                    break;
                } else {
                    i4++;
                }
            }
            if (z2) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.e; i2++) {
            i = (((i * 31) + this.c[i2]) * 31) + this.d[i2].hashCode();
        }
        return i;
    }
}
