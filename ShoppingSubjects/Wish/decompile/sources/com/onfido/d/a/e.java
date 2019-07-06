package com.onfido.d.a;

import com.onfido.d.b;
import com.onfido.d.g;
import com.onfido.d.h;

public class e extends b {
    private static final byte[] a = new byte[0];
    private byte[] b = a;
    private final int[] c = new int[32];

    public e(g gVar) {
        super(gVar);
    }

    private static int a(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4++) {
            if (iArr[i4] > i) {
                i = iArr[i4];
                i3 = i4;
            }
            if (iArr[i4] > i2) {
                i2 = iArr[i4];
            }
        }
        int i5 = 0;
        int i6 = 0;
        for (int i7 = 0; i7 < length; i7++) {
            int i8 = i7 - i3;
            int i9 = iArr[i7] * i8 * i8;
            if (i9 > i5) {
                i6 = i7;
                i5 = i9;
            }
        }
        if (i3 > i6) {
            int i10 = i3;
            i3 = i6;
            i6 = i10;
        }
        if (i6 - i3 <= length / 16) {
            throw h.a();
        }
        int i11 = i6 - 1;
        int i12 = -1;
        int i13 = i11;
        while (i11 > i3) {
            int i14 = i11 - i3;
            int i15 = i14 * i14 * (i6 - i11) * (i2 - iArr[i11]);
            if (i15 > i12) {
                i13 = i11;
                i12 = i15;
            }
            i11--;
        }
        return i13 << 3;
    }

    private void a(int i) {
        if (this.b.length < i) {
            this.b = new byte[i];
        }
        for (int i2 = 0; i2 < 32; i2++) {
            this.c[i2] = 0;
        }
    }

    public b b() {
        g a2 = a();
        int b2 = a2.b();
        int c2 = a2.c();
        b bVar = new b(b2, c2);
        a(b2);
        int[] iArr = this.c;
        for (int i = 1; i < 5; i++) {
            byte[] a3 = a2.a((c2 * i) / 5, this.b);
            int i2 = (b2 << 2) / 5;
            for (int i3 = b2 / 5; i3 < i2; i3++) {
                int i4 = (a3[i3] & 255) >> 3;
                iArr[i4] = iArr[i4] + 1;
            }
        }
        int a4 = a(iArr);
        byte[] a5 = a2.a();
        for (int i5 = 0; i5 < c2; i5++) {
            int i6 = i5 * b2;
            for (int i7 = 0; i7 < b2; i7++) {
                if ((a5[i6 + i7] & 255) < a4) {
                    bVar.b(i7, i5);
                }
            }
        }
        return bVar;
    }
}
