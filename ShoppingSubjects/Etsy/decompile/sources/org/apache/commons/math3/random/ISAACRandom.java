package org.apache.commons.math3.random;

import java.io.Serializable;

public class ISAACRandom extends BitsStreamGenerator implements Serializable {
    private static final int GLD_RATIO = -1640531527;
    private static final int H_SIZE = 128;
    private static final int MASK = 1020;
    private static final int SIZE = 256;
    private static final int SIZE_L = 8;
    private static final long serialVersionUID = 7288197941165002400L;
    private final int[] arr = new int[8];
    private int count;
    private int isaacA;
    private int isaacB;
    private int isaacC;
    private int isaacI;
    private int isaacJ;
    private int isaacX;
    private final int[] mem = new int[256];
    private final int[] rsl = new int[256];

    public ISAACRandom() {
        setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
    }

    public ISAACRandom(long j) {
        setSeed(j);
    }

    public ISAACRandom(int[] iArr) {
        setSeed(iArr);
    }

    public void setSeed(int i) {
        setSeed(new int[]{i});
    }

    public void setSeed(long j) {
        setSeed(new int[]{(int) (j >>> 32), (int) (j & 4294967295L)});
    }

    public void setSeed(int[] iArr) {
        if (iArr == null) {
            setSeed(System.currentTimeMillis() + ((long) System.identityHashCode(this)));
            return;
        }
        int length = iArr.length;
        int length2 = this.rsl.length;
        System.arraycopy(iArr, 0, this.rsl, 0, Math.min(length, length2));
        if (length < length2) {
            for (int i = length; i < length2; i++) {
                long j = (long) this.rsl[i - length];
                this.rsl[i] = (int) (((1812433253 * (j ^ (j >> 30))) + ((long) i)) & 4294967295L);
            }
        }
        initState();
    }

    /* access modifiers changed from: protected */
    public int next(int i) {
        if (this.count < 0) {
            isaac();
            this.count = 255;
        }
        int[] iArr = this.rsl;
        int i2 = this.count;
        this.count = i2 - 1;
        return iArr[i2] >>> (32 - i);
    }

    private void isaac() {
        this.isaacI = 0;
        this.isaacJ = 128;
        int i = this.isaacB;
        int i2 = this.isaacC + 1;
        this.isaacC = i2;
        this.isaacB = i + i2;
        while (this.isaacI < 128) {
            isaac2();
        }
        this.isaacJ = 0;
        while (this.isaacJ < 128) {
            isaac2();
        }
    }

    private void isaac2() {
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA << 13;
        int i = this.isaacA;
        int[] iArr = this.mem;
        int i2 = this.isaacJ;
        this.isaacJ = i2 + 1;
        this.isaacA = i + iArr[i2];
        isaac3();
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA >>> 6;
        int i3 = this.isaacA;
        int[] iArr2 = this.mem;
        int i4 = this.isaacJ;
        this.isaacJ = i4 + 1;
        this.isaacA = i3 + iArr2[i4];
        isaac3();
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA << 2;
        int i5 = this.isaacA;
        int[] iArr3 = this.mem;
        int i6 = this.isaacJ;
        this.isaacJ = i6 + 1;
        this.isaacA = i5 + iArr3[i6];
        isaac3();
        this.isaacX = this.mem[this.isaacI];
        this.isaacA ^= this.isaacA >>> 16;
        int i7 = this.isaacA;
        int[] iArr4 = this.mem;
        int i8 = this.isaacJ;
        this.isaacJ = i8 + 1;
        this.isaacA = i7 + iArr4[i8];
        isaac3();
    }

    private void isaac3() {
        this.mem[this.isaacI] = this.mem[(this.isaacX & 1020) >> 2] + this.isaacA + this.isaacB;
        this.isaacB = this.mem[((this.mem[this.isaacI] >> 8) & 1020) >> 2] + this.isaacX;
        int[] iArr = this.rsl;
        int i = this.isaacI;
        this.isaacI = i + 1;
        iArr[i] = this.isaacB;
    }

    private void initState() {
        this.isaacA = 0;
        this.isaacB = 0;
        this.isaacC = 0;
        for (int i = 0; i < this.arr.length; i++) {
            this.arr[i] = GLD_RATIO;
        }
        for (int i2 = 0; i2 < 4; i2++) {
            shuffle();
        }
        for (int i3 = 0; i3 < 256; i3 += 8) {
            int[] iArr = this.arr;
            iArr[0] = iArr[0] + this.rsl[i3];
            int[] iArr2 = this.arr;
            iArr2[1] = iArr2[1] + this.rsl[i3 + 1];
            int[] iArr3 = this.arr;
            iArr3[2] = iArr3[2] + this.rsl[i3 + 2];
            int[] iArr4 = this.arr;
            iArr4[3] = iArr4[3] + this.rsl[i3 + 3];
            int[] iArr5 = this.arr;
            iArr5[4] = iArr5[4] + this.rsl[i3 + 4];
            int[] iArr6 = this.arr;
            iArr6[5] = iArr6[5] + this.rsl[i3 + 5];
            int[] iArr7 = this.arr;
            iArr7[6] = iArr7[6] + this.rsl[i3 + 6];
            int[] iArr8 = this.arr;
            iArr8[7] = iArr8[7] + this.rsl[i3 + 7];
            shuffle();
            setState(i3);
        }
        for (int i4 = 0; i4 < 256; i4 += 8) {
            int[] iArr9 = this.arr;
            iArr9[0] = iArr9[0] + this.mem[i4];
            int[] iArr10 = this.arr;
            iArr10[1] = iArr10[1] + this.mem[i4 + 1];
            int[] iArr11 = this.arr;
            iArr11[2] = iArr11[2] + this.mem[i4 + 2];
            int[] iArr12 = this.arr;
            iArr12[3] = iArr12[3] + this.mem[i4 + 3];
            int[] iArr13 = this.arr;
            iArr13[4] = iArr13[4] + this.mem[i4 + 4];
            int[] iArr14 = this.arr;
            iArr14[5] = iArr14[5] + this.mem[i4 + 5];
            int[] iArr15 = this.arr;
            iArr15[6] = iArr15[6] + this.mem[i4 + 6];
            int[] iArr16 = this.arr;
            iArr16[7] = iArr16[7] + this.mem[i4 + 7];
            shuffle();
            setState(i4);
        }
        isaac();
        this.count = 255;
        clear();
    }

    private void shuffle() {
        int[] iArr = this.arr;
        iArr[0] = iArr[0] ^ (this.arr[1] << 11);
        int[] iArr2 = this.arr;
        iArr2[3] = iArr2[3] + this.arr[0];
        int[] iArr3 = this.arr;
        iArr3[1] = iArr3[1] + this.arr[2];
        int[] iArr4 = this.arr;
        iArr4[1] = iArr4[1] ^ (this.arr[2] >>> 2);
        int[] iArr5 = this.arr;
        iArr5[4] = iArr5[4] + this.arr[1];
        int[] iArr6 = this.arr;
        iArr6[2] = iArr6[2] + this.arr[3];
        int[] iArr7 = this.arr;
        iArr7[2] = iArr7[2] ^ (this.arr[3] << 8);
        int[] iArr8 = this.arr;
        iArr8[5] = iArr8[5] + this.arr[2];
        int[] iArr9 = this.arr;
        iArr9[3] = iArr9[3] + this.arr[4];
        int[] iArr10 = this.arr;
        iArr10[3] = iArr10[3] ^ (this.arr[4] >>> 16);
        int[] iArr11 = this.arr;
        iArr11[6] = iArr11[6] + this.arr[3];
        int[] iArr12 = this.arr;
        iArr12[4] = iArr12[4] + this.arr[5];
        int[] iArr13 = this.arr;
        iArr13[4] = iArr13[4] ^ (this.arr[5] << 10);
        int[] iArr14 = this.arr;
        iArr14[7] = iArr14[7] + this.arr[4];
        int[] iArr15 = this.arr;
        iArr15[5] = iArr15[5] + this.arr[6];
        int[] iArr16 = this.arr;
        iArr16[5] = (this.arr[6] >>> 4) ^ iArr16[5];
        int[] iArr17 = this.arr;
        iArr17[0] = iArr17[0] + this.arr[5];
        int[] iArr18 = this.arr;
        iArr18[6] = iArr18[6] + this.arr[7];
        int[] iArr19 = this.arr;
        iArr19[6] = iArr19[6] ^ (this.arr[7] << 8);
        int[] iArr20 = this.arr;
        iArr20[1] = iArr20[1] + this.arr[6];
        int[] iArr21 = this.arr;
        iArr21[7] = iArr21[7] + this.arr[0];
        int[] iArr22 = this.arr;
        iArr22[7] = iArr22[7] ^ (this.arr[0] >>> 9);
        int[] iArr23 = this.arr;
        iArr23[2] = iArr23[2] + this.arr[7];
        int[] iArr24 = this.arr;
        iArr24[0] = iArr24[0] + this.arr[1];
    }

    private void setState(int i) {
        this.mem[i] = this.arr[0];
        this.mem[i + 1] = this.arr[1];
        this.mem[i + 2] = this.arr[2];
        this.mem[i + 3] = this.arr[3];
        this.mem[i + 4] = this.arr[4];
        this.mem[i + 5] = this.arr[5];
        this.mem[i + 6] = this.arr[6];
        this.mem[i + 7] = this.arr[7];
    }
}
