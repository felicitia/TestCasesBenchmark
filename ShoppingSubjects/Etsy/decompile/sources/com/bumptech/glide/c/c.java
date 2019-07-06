package com.bumptech.glide.c;

/* compiled from: NeuQuant */
class c {
    protected int a;
    protected byte[] b;
    protected int c;
    protected int d;
    protected int[][] e;
    protected int[] f = new int[256];
    protected int[] g = new int[256];
    protected int[] h = new int[256];
    protected int[] i = new int[32];

    public c(byte[] bArr, int i2, int i3) {
        this.b = bArr;
        this.c = i2;
        this.d = i3;
        this.e = new int[256][];
        for (int i4 = 0; i4 < 256; i4++) {
            this.e[i4] = new int[4];
            int[] iArr = this.e[i4];
            int i5 = (i4 << 12) / 256;
            iArr[2] = i5;
            iArr[1] = i5;
            iArr[0] = i5;
            this.h[i4] = 256;
            this.g[i4] = 0;
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[768];
        int[] iArr = new int[256];
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[this.e[i2][3]] = i2;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < 256) {
            int i5 = iArr[i3];
            int i6 = i4 + 1;
            bArr[i4] = (byte) this.e[i5][0];
            int i7 = i6 + 1;
            bArr[i6] = (byte) this.e[i5][1];
            int i8 = i7 + 1;
            bArr[i7] = (byte) this.e[i5][2];
            i3++;
            i4 = i8;
        }
        return bArr;
    }

    public void b() {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < 256) {
            int[] iArr = this.e[i2];
            int i5 = i2 + 1;
            int i6 = i2;
            int i7 = iArr[1];
            for (int i8 = i5; i8 < 256; i8++) {
                int[] iArr2 = this.e[i8];
                if (iArr2[1] < i7) {
                    i7 = iArr2[1];
                    i6 = i8;
                }
            }
            int[] iArr3 = this.e[i6];
            if (i2 != i6) {
                int i9 = iArr3[0];
                iArr3[0] = iArr[0];
                iArr[0] = i9;
                int i10 = iArr3[1];
                iArr3[1] = iArr[1];
                iArr[1] = i10;
                int i11 = iArr3[2];
                iArr3[2] = iArr[2];
                iArr[2] = i11;
                int i12 = iArr3[3];
                iArr3[3] = iArr[3];
                iArr[3] = i12;
            }
            if (i7 != i3) {
                this.f[i3] = (i4 + i2) >> 1;
                while (true) {
                    i3++;
                    if (i3 >= i7) {
                        break;
                    }
                    this.f[i3] = i2;
                }
                i4 = i2;
                i3 = i7;
            }
            i2 = i5;
        }
        this.f[i3] = (i4 + 255) >> 1;
        for (int i13 = i3 + 1; i13 < 256; i13++) {
            this.f[i13] = 255;
        }
    }

    public void c() {
        int i2;
        int i3 = 1509;
        if (this.c < 1509) {
            this.d = 1;
        }
        this.a = 30 + ((this.d - 1) / 3);
        byte[] bArr = this.b;
        int i4 = this.c;
        int i5 = this.c / (this.d * 3);
        int i6 = i5 / 100;
        for (int i7 = 0; i7 < 32; i7++) {
            this.i[i7] = 1024 * (((1024 - (i7 * i7)) * 256) / 1024);
        }
        if (this.c < 1509) {
            i2 = 3;
        } else {
            if (this.c % 499 != 0) {
                i3 = 1497;
            } else if (this.c % 491 != 0) {
                i3 = 1473;
            } else if (this.c % 487 != 0) {
                i3 = 1461;
            }
            i2 = i3;
        }
        int i8 = i6;
        int i9 = 2048;
        int i10 = 32;
        int i11 = 0;
        int i12 = 1024;
        int i13 = 0;
        while (i13 < i5) {
            int i14 = (bArr[i11 + 0] & 255) << 4;
            int i15 = (bArr[i11 + 1] & 255) << 4;
            int i16 = (bArr[i11 + 2] & 255) << 4;
            int b2 = b(i14, i15, i16);
            int i17 = i16;
            int i18 = i15;
            int i19 = i14;
            b(i12, b2, i14, i15, i17);
            if (i10 != 0) {
                a(i10, b2, i19, i18, i17);
            }
            i11 += i2;
            if (i11 >= i4) {
                i11 -= this.c;
            }
            i13++;
            if (i8 == 0) {
                i8 = 1;
            }
            if (i13 % i8 == 0) {
                i12 -= i12 / this.a;
                i9 -= i9 / 30;
                int i20 = i9 >> 6;
                if (i20 <= 1) {
                    i20 = 0;
                }
                for (int i21 = 0; i21 < i20; i21++) {
                    int i22 = i20 * i20;
                    this.i[i21] = (((i22 - (i21 * i21)) * 256) / i22) * i12;
                }
                i10 = i20;
            }
        }
    }

    public int a(int i2, int i3, int i4) {
        int i5 = this.f[i3];
        int i6 = i5 - 1;
        int i7 = 1000;
        int i8 = -1;
        while (true) {
            if (i5 >= 256 && i6 < 0) {
                return i8;
            }
            if (i5 < 256) {
                int[] iArr = this.e[i5];
                int i9 = iArr[1] - i3;
                if (i9 >= i7) {
                    i5 = 256;
                } else {
                    i5++;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    int i10 = iArr[0] - i2;
                    if (i10 < 0) {
                        i10 = -i10;
                    }
                    int i11 = i9 + i10;
                    if (i11 < i7) {
                        int i12 = iArr[2] - i4;
                        if (i12 < 0) {
                            i12 = -i12;
                        }
                        int i13 = i11 + i12;
                        if (i13 < i7) {
                            i8 = iArr[3];
                            i7 = i13;
                        }
                    }
                }
            }
            if (i6 >= 0) {
                int[] iArr2 = this.e[i6];
                int i14 = i3 - iArr2[1];
                if (i14 >= i7) {
                    i6 = -1;
                } else {
                    i6--;
                    if (i14 < 0) {
                        i14 = -i14;
                    }
                    int i15 = iArr2[0] - i2;
                    if (i15 < 0) {
                        i15 = -i15;
                    }
                    int i16 = i14 + i15;
                    if (i16 < i7) {
                        int i17 = iArr2[2] - i4;
                        if (i17 < 0) {
                            i17 = -i17;
                        }
                        int i18 = i17 + i16;
                        if (i18 < i7) {
                            i8 = iArr2[3];
                            i7 = i18;
                        }
                    }
                }
            }
        }
    }

    public byte[] d() {
        c();
        e();
        b();
        return a();
    }

    public void e() {
        for (int i2 = 0; i2 < 256; i2++) {
            int[] iArr = this.e[i2];
            iArr[0] = iArr[0] >> 4;
            int[] iArr2 = this.e[i2];
            iArr2[1] = iArr2[1] >> 4;
            int[] iArr3 = this.e[i2];
            iArr3[2] = iArr3[2] >> 4;
            this.e[i2][3] = i2;
        }
    }

    /* access modifiers changed from: protected */
    public void a(int i2, int i3, int i4, int i5, int i6) {
        int i7 = i3 - i2;
        if (i7 < -1) {
            i7 = -1;
        }
        int i8 = i3 + i2;
        if (i8 > 256) {
            i8 = 256;
        }
        int i9 = i3 + 1;
        int i10 = i3 - 1;
        int i11 = 1;
        while (true) {
            if (i9 < i8 || i10 > i7) {
                int i12 = i11 + 1;
                int i13 = this.i[i11];
                if (i9 < i8) {
                    int i14 = i9 + 1;
                    int[] iArr = this.e[i9];
                    try {
                        iArr[0] = iArr[0] - (((iArr[0] - i4) * i13) / 262144);
                        iArr[1] = iArr[1] - (((iArr[1] - i5) * i13) / 262144);
                        iArr[2] = iArr[2] - (((iArr[2] - i6) * i13) / 262144);
                    } catch (Exception unused) {
                    }
                    i9 = i14;
                }
                if (i10 > i7) {
                    int i15 = i10 - 1;
                    int[] iArr2 = this.e[i10];
                    try {
                        iArr2[0] = iArr2[0] - (((iArr2[0] - i4) * i13) / 262144);
                        iArr2[1] = iArr2[1] - (((iArr2[1] - i5) * i13) / 262144);
                        iArr2[2] = iArr2[2] - ((i13 * (iArr2[2] - i6)) / 262144);
                    } catch (Exception unused2) {
                    }
                    i11 = i12;
                    i10 = i15;
                } else {
                    i11 = i12;
                }
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public void b(int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.e[i3];
        iArr[0] = iArr[0] - (((iArr[0] - i4) * i2) / 1024);
        iArr[1] = iArr[1] - (((iArr[1] - i5) * i2) / 1024);
        iArr[2] = iArr[2] - ((i2 * (iArr[2] - i6)) / 1024);
    }

    /* access modifiers changed from: protected */
    public int b(int i2, int i3, int i4) {
        int i5 = -1;
        int i6 = Integer.MAX_VALUE;
        int i7 = Integer.MAX_VALUE;
        int i8 = -1;
        for (int i9 = 0; i9 < 256; i9++) {
            int[] iArr = this.e[i9];
            int i10 = iArr[0] - i2;
            if (i10 < 0) {
                i10 = -i10;
            }
            int i11 = iArr[1] - i3;
            if (i11 < 0) {
                i11 = -i11;
            }
            int i12 = i10 + i11;
            int i13 = iArr[2] - i4;
            if (i13 < 0) {
                i13 = -i13;
            }
            int i14 = i12 + i13;
            if (i14 < i6) {
                i5 = i9;
                i6 = i14;
            }
            int i15 = i14 - (this.g[i9] >> 12);
            if (i15 < i7) {
                i8 = i9;
                i7 = i15;
            }
            int i16 = this.h[i9] >> 10;
            int[] iArr2 = this.h;
            iArr2[i9] = iArr2[i9] - i16;
            int[] iArr3 = this.g;
            iArr3[i9] = iArr3[i9] + (i16 << 10);
        }
        int[] iArr4 = this.h;
        iArr4[i5] = iArr4[i5] + 64;
        int[] iArr5 = this.g;
        iArr5[i5] = iArr5[i5] - 65536;
        return i8;
    }
}
