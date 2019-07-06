package com.onfido.d.b.b;

import com.onfido.d.a.b;
import com.onfido.d.c;
import com.onfido.d.e;
import com.onfido.d.m;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public final class a {
    private static final int[] a = {0, 4, 1, 5};
    private static final int[] b = {6, 2, 7, 3};
    private static final int[] c = {8, 1, 1, 1, 1, 1, 1, 3};
    private static final int[] d = {7, 1, 1, 3, 1, 1, 1, 2, 1};

    private static float a(int[] iArr, int[] iArr2, float f) {
        int length = iArr.length;
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            i += iArr[i3];
            i2 += iArr2[i3];
        }
        if (i < i2) {
            return Float.POSITIVE_INFINITY;
        }
        float f2 = (float) i;
        float f3 = f2 / ((float) i2);
        float f4 = f * f3;
        float f5 = 0.0f;
        for (int i4 = 0; i4 < length; i4++) {
            float f6 = ((float) iArr2[i4]) * f3;
            float f7 = (float) iArr[i4];
            float f8 = f7 > f6 ? f7 - f6 : f6 - f7;
            if (f8 > f4) {
                return Float.POSITIVE_INFINITY;
            }
            f5 += f8;
        }
        return f5 / f2;
    }

    public static b a(c cVar, Map<e, ?> map, boolean z) {
        b a2 = cVar.a();
        List a3 = a(z, a2);
        if (a3.isEmpty()) {
            a2 = a2.clone();
            a2.a();
            a3 = a(z, a2);
        }
        return new b(a2, a3);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0027, code lost:
        if (r4.hasNext() == false) goto L_0x004f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0029, code lost:
        r5 = (com.onfido.d.m[]) r4.next();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0031, code lost:
        if (r5[1] == null) goto L_0x003f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r3 = (int) java.lang.Math.max((float) r3, r5[1].b());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0041, code lost:
        if (r5[3] == null) goto L_0x0023;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0043, code lost:
        r3 = java.lang.Math.max(r3, (int) r5[3].b());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001d, code lost:
        if (r5 == false) goto L_0x0078;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001f, code lost:
        r4 = r0.iterator();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.List<com.onfido.d.m[]> a(boolean r8, com.onfido.d.a.b r9) {
        /*
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 1
            r2 = 0
            r3 = 0
        L_0x0008:
            r4 = 0
            r5 = 0
        L_0x000a:
            int r6 = r9.c()
            if (r3 >= r6) goto L_0x0078
            com.onfido.d.m[] r4 = a(r9, r3, r4)
            r6 = r4[r2]
            if (r6 != 0) goto L_0x0052
            r6 = 3
            r7 = r4[r6]
            if (r7 != 0) goto L_0x0052
            if (r5 == 0) goto L_0x0078
            java.util.Iterator r4 = r0.iterator()
        L_0x0023:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x004f
            java.lang.Object r5 = r4.next()
            com.onfido.d.m[] r5 = (com.onfido.d.m[]) r5
            r7 = r5[r1]
            if (r7 == 0) goto L_0x003f
            float r3 = (float) r3
            r7 = r5[r1]
            float r7 = r7.b()
            float r3 = java.lang.Math.max(r3, r7)
            int r3 = (int) r3
        L_0x003f:
            r7 = r5[r6]
            if (r7 == 0) goto L_0x0023
            r5 = r5[r6]
            float r5 = r5.b()
            int r5 = (int) r5
            int r3 = java.lang.Math.max(r3, r5)
            goto L_0x0023
        L_0x004f:
            int r3 = r3 + 5
            goto L_0x0008
        L_0x0052:
            r0.add(r4)
            if (r8 == 0) goto L_0x0078
            r3 = 2
            r5 = r4[r3]
            if (r5 == 0) goto L_0x006d
            r5 = r4[r3]
            float r5 = r5.a()
            int r5 = (int) r5
            r3 = r4[r3]
        L_0x0065:
            float r3 = r3.b()
            int r3 = (int) r3
            r4 = r5
            r5 = 1
            goto L_0x000a
        L_0x006d:
            r3 = 4
            r5 = r4[r3]
            float r5 = r5.a()
            int r5 = (int) r5
            r3 = r4[r3]
            goto L_0x0065
        L_0x0078:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.d.b.b.a.a(boolean, com.onfido.d.a.b):java.util.List");
    }

    private static void a(m[] mVarArr, m[] mVarArr2, int[] iArr) {
        for (int i = 0; i < iArr.length; i++) {
            mVarArr[iArr[i]] = mVarArr2[i];
        }
    }

    private static int[] a(b bVar, int i, int i2, int i3, boolean z, int[] iArr, int[] iArr2) {
        Arrays.fill(iArr2, 0, iArr2.length, 0);
        int i4 = 0;
        while (bVar.a(i, i2) && i > 0) {
            int i5 = i4 + 1;
            if (i4 >= 3) {
                break;
            }
            i--;
            i4 = i5;
        }
        int length = iArr.length;
        int i6 = i;
        int i7 = 0;
        while (true) {
            boolean z2 = true;
            if (i < i3) {
                if (bVar.a(i, i2) != z) {
                    iArr2[i7] = iArr2[i7] + 1;
                } else {
                    if (i7 != length - 1) {
                        i7++;
                    } else if (a(iArr2, iArr, 0.8f) < 0.42f) {
                        return new int[]{i6, i};
                    } else {
                        i6 += iArr2[0] + iArr2[1];
                        int i8 = i7 - 1;
                        System.arraycopy(iArr2, 2, iArr2, 0, i8);
                        iArr2[i8] = 0;
                        iArr2[i7] = 0;
                        i7--;
                    }
                    iArr2[i7] = 1;
                    if (z) {
                        z2 = false;
                    }
                    z = z2;
                }
                i++;
            } else if (i7 != length - 1 || a(iArr2, iArr, 0.8f) >= 0.42f) {
                return null;
            } else {
                return new int[]{i6, i - 1};
            }
        }
    }

    private static m[] a(b bVar, int i, int i2) {
        int c2 = bVar.c();
        int b2 = bVar.b();
        m[] mVarArr = new m[8];
        a(mVarArr, a(bVar, c2, b2, i, i2, c), a);
        if (mVarArr[4] != null) {
            i2 = (int) mVarArr[4].a();
            i = (int) mVarArr[4].b();
        }
        a(mVarArr, a(bVar, c2, b2, i, i2, d), b);
        return mVarArr;
    }

    private static m[] a(b bVar, int i, int i2, int i3, int i4, int[] iArr) {
        boolean z;
        int i5;
        int[] iArr2;
        int i6 = i;
        m[] mVarArr = new m[4];
        int[] iArr3 = iArr;
        int[] iArr4 = new int[iArr3.length];
        int i7 = i3;
        while (true) {
            if (i7 >= i6) {
                z = false;
                break;
            }
            int[] a2 = a(bVar, i4, i7, i2, false, iArr3, iArr4);
            if (a2 != null) {
                while (true) {
                    iArr2 = a2;
                    if (i7 <= 0) {
                        break;
                    }
                    i7--;
                    a2 = a(bVar, i4, i7, i2, false, iArr3, iArr4);
                    if (a2 == null) {
                        i7++;
                        break;
                    }
                }
                float f = (float) i7;
                mVarArr[0] = new m((float) iArr2[0], f);
                mVarArr[1] = new m((float) iArr2[1], f);
                z = true;
            } else {
                i7 += 5;
            }
        }
        int i8 = i7 + 1;
        if (z) {
            int[] iArr5 = {(int) mVarArr[0].a(), (int) mVarArr[1].a()};
            int i9 = i8;
            int i10 = 0;
            while (true) {
                if (i9 >= i6) {
                    i5 = i10;
                    break;
                }
                i5 = i10;
                int[] a3 = a(bVar, iArr5[0], i9, i2, false, iArr3, iArr4);
                if (a3 == null || Math.abs(iArr5[0] - a3[0]) >= 5 || Math.abs(iArr5[1] - a3[1]) >= 5) {
                    if (i5 > 25) {
                        break;
                    }
                    i10 = i5 + 1;
                } else {
                    iArr5 = a3;
                    i10 = 0;
                }
                i9++;
            }
            i8 = i9 - (i5 + 1);
            float f2 = (float) i8;
            mVarArr[2] = new m((float) iArr5[0], f2);
            mVarArr[3] = new m((float) iArr5[1], f2);
        }
        if (i8 - i7 < 10) {
            Arrays.fill(mVarArr, null);
        }
        return mVarArr;
    }
}
