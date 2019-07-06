package org.apache.commons.math3.random;

import java.io.Serializable;
import org.apache.commons.math3.util.FastMath;

public class MersenneTwister extends BitsStreamGenerator implements Serializable {
    private static final int M = 397;
    private static final int[] MAG01 = {0, -1727483681};
    private static final int N = 624;
    private static final long serialVersionUID = 8661194735290153518L;
    private int[] mt = new int[N];
    private int mti;

    public MersenneTwister() {
        setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    public MersenneTwister(int i) {
        setSeed(i);
    }

    public MersenneTwister(int[] iArr) {
        setSeed(iArr);
    }

    public MersenneTwister(long j) {
        setSeed(j);
    }

    public void setSeed(int i) {
        long j = (long) i;
        this.mt[0] = (int) j;
        this.mti = 1;
        while (this.mti < N) {
            long j2 = ((1812433253 * (j ^ (j >> 30))) + ((long) this.mti)) & 4294967295L;
            this.mt[this.mti] = (int) j2;
            this.mti++;
            j = j2;
        }
        clear();
    }

    public void setSeed(int[] iArr) {
        int i;
        int[] iArr2 = iArr;
        if (iArr2 == null) {
            setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
            return;
        }
        setSeed(19650218);
        int i2 = 0;
        int i3 = 1;
        for (int max = FastMath.max((int) N, iArr2.length); max != 0; max--) {
            int i4 = i3 - 1;
            long j = (((long) this.mt[i4]) & 2147483647L) | (this.mt[i4] < 0 ? 2147483648L : 0);
            this.mt[i3] = (int) (((((((long) this.mt[i3]) & 2147483647L) | (this.mt[i3] < 0 ? 2147483648L : 0)) ^ ((j ^ (j >> 30)) * 1664525)) + ((long) iArr2[i2]) + ((long) i2)) & 4294967295L);
            i3++;
            i2++;
            if (i3 >= N) {
                this.mt[0] = this.mt[623];
                i3 = 1;
            }
            if (i2 >= iArr2.length) {
                i2 = 0;
            }
        }
        for (int i5 = 623; i5 != 0; i5--) {
            int i6 = i3 - 1;
            long j2 = (((long) this.mt[i6]) & 2147483647L) | (this.mt[i6] < 0 ? 2147483648L : 0);
            this.mt[i3] = (int) (((((((long) this.mt[i3]) & 2147483647L) | (this.mt[i3] < 0 ? 2147483648L : 0)) ^ ((j2 ^ (j2 >> 30)) * 1566083941)) - ((long) i3)) & 4294967295L);
            int i7 = i3 + 1;
            if (i7 >= N) {
                this.mt[0] = this.mt[623];
                i = 1;
            } else {
                i = i7;
            }
        }
        this.mt[0] = Integer.MIN_VALUE;
        clear();
    }

    public void setSeed(long j) {
        setSeed(new int[]{(int) (j >>> 32), (int) (j & 4294967295L)});
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        int i2;
        if (this.mti >= N) {
            int i3 = this.mt[0];
            int i4 = 0;
            while (true) {
                i2 = 227;
                if (i4 >= 227) {
                    break;
                }
                int i5 = i4 + 1;
                int i6 = this.mt[i5];
                int i7 = (i3 & Integer.MIN_VALUE) | (Integer.MAX_VALUE & i6);
                this.mt[i4] = MAG01[i7 & 1] ^ (this.mt[i4 + M] ^ (i7 >>> 1));
                i3 = i6;
                i4 = i5;
            }
            while (i2 < 623) {
                int i8 = i2 + 1;
                int i9 = this.mt[i8];
                int i10 = (i3 & Integer.MIN_VALUE) | (i9 & Integer.MAX_VALUE);
                this.mt[i2] = MAG01[i10 & 1] ^ (this.mt[i2 - 227] ^ (i10 >>> 1));
                i3 = i9;
                i2 = i8;
            }
            int i11 = (i3 & Integer.MIN_VALUE) | (this.mt[0] & Integer.MAX_VALUE);
            this.mt[623] = MAG01[i11 & 1] ^ (this.mt[396] ^ (i11 >>> 1));
            this.mti = 0;
        }
        int[] iArr = this.mt;
        int i12 = this.mti;
        this.mti = i12 + 1;
        int i13 = iArr[i12];
        int i14 = i13 ^ (i13 >>> 11);
        int i15 = i14 ^ ((i14 << 7) & -1658038656);
        int i16 = i15 ^ ((i15 << 15) & -272236544);
        return (i16 ^ (i16 >>> 18)) >>> (32 - i);
    }
}
