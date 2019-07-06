package com.google.android.gms.internal.measurement;

public final class zzacf implements Cloneable {
    private static final zzacg zzbzh = new zzacg();
    private int mSize;
    private boolean zzbzi;
    private int[] zzbzj;
    private zzacg[] zzbzk;

    zzacf() {
        this(10);
    }

    private zzacf(int i) {
        this.zzbzi = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzbzj = new int[idealIntArraySize];
        this.zzbzk = new zzacg[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
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

    private final int zzav(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzbzj[i4];
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

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzacf zzacf = new zzacf(i);
        System.arraycopy(this.zzbzj, 0, zzacf.zzbzj, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzbzk[i2] != null) {
                zzacf.zzbzk[i2] = (zzacg) this.zzbzk[i2].clone();
            }
        }
        zzacf.mSize = i;
        return zzacf;
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzacf)) {
            return false;
        }
        zzacf zzacf = (zzacf) obj;
        if (this.mSize != zzacf.mSize) {
            return false;
        }
        int[] iArr = this.zzbzj;
        int[] iArr2 = zzacf.zzbzj;
        int i = this.mSize;
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
            zzacg[] zzacgArr = this.zzbzk;
            zzacg[] zzacgArr2 = zzacf.zzbzk;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzacgArr[i4].equals(zzacgArr2[i4])) {
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
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzbzj[i2]) * 31) + this.zzbzk[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* access modifiers changed from: 0000 */
    public final int size() {
        return this.mSize;
    }

    /* access modifiers changed from: 0000 */
    public final void zza(int i, zzacg zzacg) {
        int zzav = zzav(i);
        if (zzav >= 0) {
            this.zzbzk[zzav] = zzacg;
            return;
        }
        int i2 = zzav ^ -1;
        if (i2 >= this.mSize || this.zzbzk[i2] != zzbzh) {
            if (this.mSize >= this.zzbzj.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzacg[] zzacgArr = new zzacg[idealIntArraySize];
                System.arraycopy(this.zzbzj, 0, iArr, 0, this.zzbzj.length);
                System.arraycopy(this.zzbzk, 0, zzacgArr, 0, this.zzbzk.length);
                this.zzbzj = iArr;
                this.zzbzk = zzacgArr;
            }
            if (this.mSize - i2 != 0) {
                int i3 = i2 + 1;
                System.arraycopy(this.zzbzj, i2, this.zzbzj, i3, this.mSize - i2);
                System.arraycopy(this.zzbzk, i2, this.zzbzk, i3, this.mSize - i2);
            }
            this.zzbzj[i2] = i;
            this.zzbzk[i2] = zzacg;
            this.mSize++;
            return;
        }
        this.zzbzj[i2] = i;
        this.zzbzk[i2] = zzacg;
    }

    /* access modifiers changed from: 0000 */
    public final zzacg zzat(int i) {
        int zzav = zzav(i);
        if (zzav < 0 || this.zzbzk[zzav] == zzbzh) {
            return null;
        }
        return this.zzbzk[zzav];
    }

    /* access modifiers changed from: 0000 */
    public final zzacg zzau(int i) {
        return this.zzbzk[i];
    }
}
