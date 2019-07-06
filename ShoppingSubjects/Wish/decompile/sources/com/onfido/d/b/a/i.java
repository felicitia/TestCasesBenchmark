package com.onfido.d.b.a;

import com.onfido.d.b.a;
import java.lang.reflect.Array;

final class i {
    private static final float[][] a = ((float[][]) Array.newInstance(float.class, new int[]{a.a.length, 8}));

    static {
        int i;
        for (int i2 = 0; i2 < a.a.length; i2++) {
            int i3 = a.a[i2];
            int i4 = i3 & 1;
            int i5 = i3;
            int i6 = 0;
            while (i6 < 8) {
                float f = 0.0f;
                while (true) {
                    i = i5 & 1;
                    if (i != i4) {
                        break;
                    }
                    f += 1.0f;
                    i5 >>= 1;
                }
                a[i2][(8 - i6) - 1] = f / 17.0f;
                i6++;
                i4 = i;
            }
        }
    }

    static int a(int[] iArr) {
        int c = c(b(iArr));
        return c != -1 ? c : e(iArr);
    }

    private static int[] b(int[] iArr) {
        float a2 = (float) com.onfido.d.a.a.a.a(iArr);
        int[] iArr2 = new int[8];
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < 17; i3++) {
            if (((float) (iArr[i2] + i)) <= (a2 / 34.0f) + ((((float) i3) * a2) / 17.0f)) {
                i += iArr[i2];
                i2++;
            }
            iArr2[i2] = iArr2[i2] + 1;
        }
        return iArr2;
    }

    private static int c(int[] iArr) {
        int d = d(iArr);
        if (a.a(d) == -1) {
            return -1;
        }
        return d;
    }

    private static int d(int[] iArr) {
        long j = 0;
        int i = 0;
        while (i < iArr.length) {
            long j2 = j;
            int i2 = 0;
            while (i2 < iArr[i]) {
                int i3 = 1;
                long j3 = j2 << 1;
                if (i % 2 != 0) {
                    i3 = 0;
                }
                i2++;
                j2 = j3 | ((long) i3);
            }
            i++;
            j = j2;
        }
        return (int) j;
    }

    private static int e(int[] iArr) {
        int a2 = com.onfido.d.a.a.a.a(iArr);
        float[] fArr = new float[8];
        if (a2 > 1) {
            for (int i = 0; i < 8; i++) {
                fArr[i] = ((float) iArr[i]) / ((float) a2);
            }
        }
        int i2 = -1;
        float f = Float.MAX_VALUE;
        for (int i3 = 0; i3 < a.length; i3++) {
            float[] fArr2 = a[i3];
            float f2 = 0.0f;
            for (int i4 = 0; i4 < 8; i4++) {
                float f3 = fArr2[i4] - fArr[i4];
                f2 += f3 * f3;
                if (f2 >= f) {
                    break;
                }
            }
            if (f2 < f) {
                i2 = a.a[i3];
                f = f2;
            }
        }
        return i2;
    }
}
