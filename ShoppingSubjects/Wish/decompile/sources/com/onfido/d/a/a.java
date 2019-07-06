package com.onfido.d.a;

import java.util.Arrays;

public final class a implements Cloneable {
    private int[] a;
    private int b;

    public a() {
        this.b = 0;
        this.a = new int[1];
    }

    public a(int i) {
        this.b = i;
        this.a = b(i);
    }

    a(int[] iArr, int i) {
        this.a = iArr;
        this.b = i;
    }

    private static int[] b(int i) {
        return new int[((i + 31) / 32)];
    }

    public int a() {
        return this.b;
    }

    public void a(int i, int i2) {
        this.a[i / 32] = i2;
    }

    public boolean a(int i) {
        return ((1 << (i & 31)) & this.a[i / 32]) != 0;
    }

    public void b() {
        int length = this.a.length;
        for (int i = 0; i < length; i++) {
            this.a[i] = 0;
        }
    }

    public int[] c() {
        return this.a;
    }

    public void d() {
        int[] iArr = new int[this.a.length];
        int i = (this.b - 1) / 32;
        int i2 = i + 1;
        for (int i3 = 0; i3 < i2; i3++) {
            long j = (long) this.a[i3];
            long j2 = ((j >> 1) & 1431655765) | ((j & 1431655765) << 1);
            long j3 = ((j2 >> 2) & 858993459) | ((j2 & 858993459) << 2);
            long j4 = ((j3 >> 4) & 252645135) | ((j3 & 252645135) << 4);
            long j5 = ((j4 >> 8) & 16711935) | ((j4 & 16711935) << 8);
            iArr[i - i3] = (int) (((j5 >> 16) & 65535) | ((j5 & 65535) << 16));
        }
        int i4 = i2 << 5;
        if (this.b != i4) {
            int i5 = i4 - this.b;
            int i6 = iArr[0] >>> i5;
            for (int i7 = 1; i7 < i2; i7++) {
                int i8 = iArr[i7];
                iArr[i7 - 1] = i6 | (i8 << (32 - i5));
                i6 = i8 >>> i5;
            }
            iArr[i2 - 1] = i6;
        }
        this.a = iArr;
    }

    /* renamed from: e */
    public a clone() {
        return new a((int[]) this.a.clone(), this.b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof a)) {
            return false;
        }
        a aVar = (a) obj;
        return this.b == aVar.b && Arrays.equals(this.a, aVar.a);
    }

    public int hashCode() {
        return (this.b * 31) + Arrays.hashCode(this.a);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(this.b);
        for (int i = 0; i < this.b; i++) {
            if ((i & 7) == 0) {
                sb.append(' ');
            }
            sb.append(a(i) ? 'X' : '.');
        }
        return sb.toString();
    }
}
