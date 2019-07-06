package com.google.android.gms.internal.firebase-perf;

public final class zzgc implements Cloneable {
    private static final zzgd zzsu = new zzgd();
    private int mSize;
    private boolean zzsv;
    private int[] zzsw;
    private zzgd[] zzsx;

    zzgc() {
        this(10);
    }

    private zzgc(int i) {
        this.zzsv = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzsw = new int[idealIntArraySize];
        this.zzsx = new zzgd[idealIntArraySize];
        this.mSize = 0;
    }

    /* access modifiers changed from: 0000 */
    public final zzgd zzba(int i) {
        int zzbc = zzbc(i);
        if (zzbc < 0 || this.zzsx[zzbc] == zzsu) {
            return null;
        }
        return this.zzsx[zzbc];
    }

    /* access modifiers changed from: 0000 */
    public final void zza(int i, zzgd zzgd) {
        int zzbc = zzbc(i);
        if (zzbc >= 0) {
            this.zzsx[zzbc] = zzgd;
            return;
        }
        int i2 = zzbc ^ -1;
        if (i2 >= this.mSize || this.zzsx[i2] != zzsu) {
            if (this.mSize >= this.zzsw.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                int[] iArr = new int[idealIntArraySize];
                zzgd[] zzgdArr = new zzgd[idealIntArraySize];
                System.arraycopy(this.zzsw, 0, iArr, 0, this.zzsw.length);
                System.arraycopy(this.zzsx, 0, zzgdArr, 0, this.zzsx.length);
                this.zzsw = iArr;
                this.zzsx = zzgdArr;
            }
            if (this.mSize - i2 != 0) {
                int i3 = i2 + 1;
                System.arraycopy(this.zzsw, i2, this.zzsw, i3, this.mSize - i2);
                System.arraycopy(this.zzsx, i2, this.zzsx, i3, this.mSize - i2);
            }
            this.zzsw[i2] = i;
            this.zzsx[i2] = zzgd;
            this.mSize++;
            return;
        }
        this.zzsw[i2] = i;
        this.zzsx[i2] = zzgd;
    }

    /* access modifiers changed from: 0000 */
    public final int size() {
        return this.mSize;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    /* access modifiers changed from: 0000 */
    public final zzgd zzbb(int i) {
        return this.zzsx[i];
    }

    public final boolean equals(Object obj) {
        boolean z;
        boolean z2;
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzgc)) {
            return false;
        }
        zzgc zzgc = (zzgc) obj;
        if (this.mSize != zzgc.mSize) {
            return false;
        }
        int[] iArr = this.zzsw;
        int[] iArr2 = zzgc.zzsw;
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
            zzgd[] zzgdArr = this.zzsx;
            zzgd[] zzgdArr2 = zzgc.zzsx;
            int i3 = this.mSize;
            int i4 = 0;
            while (true) {
                if (i4 >= i3) {
                    z2 = true;
                    break;
                } else if (!zzgdArr[i4].equals(zzgdArr2[i4])) {
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
            i = (((i * 31) + this.zzsw[i2]) * 31) + this.zzsx[i2].hashCode();
        }
        return i;
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

    private final int zzbc(int i) {
        int i2 = this.mSize - 1;
        int i3 = 0;
        while (i3 <= i2) {
            int i4 = (i3 + i2) >>> 1;
            int i5 = this.zzsw[i4];
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
        zzgc zzgc = new zzgc(i);
        System.arraycopy(this.zzsw, 0, zzgc.zzsw, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzsx[i2] != null) {
                zzgc.zzsx[i2] = (zzgd) this.zzsx[i2].clone();
            }
        }
        zzgc.mSize = i;
        return zzgc;
    }
}
