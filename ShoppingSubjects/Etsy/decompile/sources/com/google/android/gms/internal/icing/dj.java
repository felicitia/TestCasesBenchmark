package com.google.android.gms.internal.icing;

public final class dj implements Cloneable {
    private static final dk a = new dk();
    private boolean b;
    private int[] c;
    private dk[] d;
    private int e;

    dj() {
        this(10);
    }

    private dj(int i) {
        this.b = false;
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
        int i5 = i2 / 4;
        this.c = new int[i5];
        this.d = new dk[i5];
        this.e = 0;
    }

    /* access modifiers changed from: 0000 */
    public final int a() {
        return this.e;
    }

    /* access modifiers changed from: 0000 */
    public final dk a(int i) {
        return this.d[i];
    }

    public final boolean b() {
        return this.e == 0;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.e;
        dj djVar = new dj(i);
        System.arraycopy(this.c, 0, djVar.c, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.d[i2] != null) {
                djVar.d[i2] = (dk) this.d[i2].clone();
            }
        }
        djVar.e = i;
        return djVar;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof dj)) {
            return false;
        }
        dj djVar = (dj) obj;
        if (this.e != djVar.e) {
            return false;
        }
        int[] iArr = this.c;
        int[] iArr2 = djVar.c;
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
            dk[] dkVarArr = this.d;
            dk[] dkVarArr2 = djVar.d;
            int i3 = this.e;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!dkVarArr[i4].equals(dkVarArr2[i4])) {
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
