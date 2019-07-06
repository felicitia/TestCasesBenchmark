package org.apache.commons.math3.random;

public class Well44497b extends AbstractWell {
    private static final int K = 44497;
    private static final int M1 = 23;
    private static final int M2 = 481;
    private static final int M3 = 229;
    private static final long serialVersionUID = 4032007538246675492L;

    public Well44497b() {
        super(K, 23, M2, M3);
    }

    public Well44497b(int i) {
        super((int) K, 23, (int) M2, (int) M3, i);
    }

    public Well44497b(int[] iArr) {
        super((int) K, 23, (int) M2, (int) M3, iArr);
    }

    public Well44497b(long j) {
        super((int) K, 23, (int) M2, (int) M3, j);
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        int i2 = this.iRm1[this.index];
        int i3 = this.iRm2[this.index];
        int i4 = this.v[this.index];
        int i5 = this.v[this.i1[this.index]];
        int i6 = this.v[this.i2[this.index]];
        int i7 = (this.v[i2] & -32768) ^ (this.v[i3] & 32767);
        int i8 = (i4 ^ (i4 << 24)) ^ (i5 ^ (i5 >>> 30));
        int i9 = ((i6 << 10) ^ i6) ^ (this.v[this.i3[this.index]] << 26);
        int i10 = i8 ^ i9;
        int i11 = ((i9 << 9) ^ (i9 >>> 23)) & -67108865;
        if ((i9 & 131072) != 0) {
            i11 ^= -1221985044;
        }
        int i12 = (((i8 ^ (i8 >>> 20)) ^ i7) ^ i11) ^ i10;
        this.v[this.index] = i10;
        this.v[i2] = i12;
        int[] iArr = this.v;
        iArr[i3] = iArr[i3] & -32768;
        this.index = i2;
        int i13 = ((i12 << 7) & -1814227968) ^ i12;
        return (i13 ^ ((i13 << 15) & -99516416)) >>> (32 - i);
    }
}
