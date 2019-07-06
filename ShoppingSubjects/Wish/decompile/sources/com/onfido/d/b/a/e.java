package com.onfido.d.b.a;

import com.onfido.d.b.c;
import com.onfido.d.f;
import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.Arrays;

final class e {
    private static final char[] a = ";<>@[\\]_`~!\r\t,:\n-.$/\"|*()?{}'".toCharArray();
    private static final char[] b = "0123456789&\r\t,:#-.$/+%*=^".toCharArray();
    private static final BigInteger[] c;

    private enum a {
        ALPHA,
        LOWER,
        MIXED,
        PUNCT,
        ALPHA_SHIFT,
        PUNCT_SHIFT
    }

    static {
        BigInteger[] bigIntegerArr = new BigInteger[16];
        c = bigIntegerArr;
        bigIntegerArr[0] = BigInteger.ONE;
        BigInteger valueOf = BigInteger.valueOf(900);
        c[1] = valueOf;
        for (int i = 2; i < c.length; i++) {
            c[i] = c[i - 1].multiply(valueOf);
        }
    }

    private static int a(int i, int[] iArr, Charset charset, int i2, StringBuilder sb) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = i;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        long j = 900;
        if (i7 == 901) {
            int[] iArr2 = new int[6];
            int i8 = i2 + 1;
            int i9 = iArr[i2];
            boolean z = false;
            while (true) {
                i5 = 0;
                long j2 = 0;
                while (i4 < iArr[0] && !z) {
                    int i10 = i5 + 1;
                    iArr2[i5] = i9;
                    long j3 = (j2 * 900) + ((long) i9);
                    int i11 = i4 + 1;
                    i9 = iArr[i4];
                    if (i9 != 928) {
                        switch (i9) {
                            case 900:
                            case 901:
                            case 902:
                                break;
                            default:
                                switch (i9) {
                                    case 922:
                                    case 923:
                                    case 924:
                                        break;
                                    default:
                                        if (i10 % 5 != 0 || i10 <= 0) {
                                            i4 = i11;
                                            i5 = i10;
                                            j2 = j3;
                                            break;
                                        } else {
                                            for (int i12 = 0; i12 < 6; i12++) {
                                                byteArrayOutputStream.write((byte) ((int) (j3 >> ((5 - i12) * 8))));
                                            }
                                            i8 = i11;
                                        }
                                }
                                break;
                        }
                    }
                    i4 = i11 - 1;
                    i5 = i10;
                    j2 = j3;
                    z = true;
                }
            }
            if (i4 != iArr[0] || i9 >= 900) {
                i6 = i5;
            } else {
                i6 = i5 + 1;
                iArr2[i5] = i9;
            }
            for (int i13 = 0; i13 < i6; i13++) {
                byteArrayOutputStream.write((byte) iArr2[i13]);
            }
            i3 = i4;
        } else if (i7 != 924) {
            i3 = i2;
        } else {
            i3 = i2;
            boolean z2 = false;
            int i14 = 0;
            long j4 = 0;
            while (i3 < iArr[0] && !z2) {
                int i15 = i3 + 1;
                int i16 = iArr[i3];
                if (i16 < 900) {
                    i14++;
                    i3 = i15;
                    j4 = (j4 * j) + ((long) i16);
                } else {
                    if (i16 != 928) {
                        switch (i16) {
                            case 900:
                            case 901:
                            case 902:
                                break;
                            default:
                                switch (i16) {
                                    case 922:
                                    case 923:
                                    case 924:
                                        break;
                                    default:
                                        i3 = i15;
                                        break;
                                }
                        }
                    }
                    i3 = i15 - 1;
                    z2 = true;
                }
                if (i14 % 5 == 0 && i14 > 0) {
                    for (int i17 = 0; i17 < 6; i17++) {
                        byteArrayOutputStream.write((byte) ((int) (j4 >> ((5 - i17) * 8))));
                    }
                    i14 = 0;
                    j4 = 0;
                }
                j = 900;
            }
        }
        sb.append(new String(byteArrayOutputStream.toByteArray(), charset));
        return i3;
    }

    private static int a(int[] iArr, int i, c cVar) {
        if (i + 2 > iArr[0]) {
            throw f.a();
        }
        int[] iArr2 = new int[2];
        int i2 = i;
        int i3 = 0;
        while (i3 < 2) {
            iArr2[i3] = iArr[i2];
            i3++;
            i2++;
        }
        cVar.a(Integer.parseInt(a(iArr2, 2)));
        StringBuilder sb = new StringBuilder();
        int a2 = a(iArr, i2, sb);
        cVar.a(sb.toString());
        switch (iArr[a2]) {
            case 922:
                cVar.a(true);
                return a2 + 1;
            case 923:
                int i4 = a2 + 1;
                int[] iArr3 = new int[(iArr[0] - i4)];
                boolean z = false;
                int i5 = 0;
                while (i4 < iArr[0] && !z) {
                    int i6 = i4 + 1;
                    int i7 = iArr[i4];
                    if (i7 < 900) {
                        int i8 = i5 + 1;
                        iArr3[i5] = i7;
                        i4 = i6;
                        i5 = i8;
                    } else if (i7 != 922) {
                        throw f.a();
                    } else {
                        cVar.a(true);
                        i4 = i6 + 1;
                        z = true;
                    }
                }
                cVar.a(Arrays.copyOf(iArr3, i5));
                return i4;
            default:
                return a2;
        }
    }

    private static int a(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[((iArr[0] - i) << 1)];
        int[] iArr3 = new int[((iArr[0] - i) << 1)];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i4 < 900) {
                iArr2[i2] = i4 / 30;
                iArr2[i2 + 1] = i4 % 30;
                i2 += 2;
            } else if (i4 != 913) {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                            int i5 = i2 + 1;
                            iArr2[i2] = 900;
                            i2 = i5;
                            break;
                        case 901:
                        case 902:
                            break;
                        default:
                            switch (i4) {
                                case 922:
                                case 923:
                                case 924:
                                    break;
                            }
                    }
                }
                i = i3 - 1;
                z = true;
            } else {
                iArr2[i2] = 913;
                i = i3 + 1;
                iArr3[i2] = iArr[i3];
                i2++;
            }
            i = i3;
        }
        a(iArr2, iArr3, i2, sb);
        return i;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x004e, code lost:
        r2 = a(r2, r6, r1, r4, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0023, code lost:
        r2 = a(r6, r4, r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static com.onfido.d.a.d a(int[] r6, java.lang.String r7) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            int r1 = r6.length
            r2 = 1
            int r1 = r1 << r2
            r0.<init>(r1)
            java.nio.charset.Charset r1 = java.nio.charset.StandardCharsets.ISO_8859_1
            r2 = r6[r2]
            com.onfido.d.b.c r3 = new com.onfido.d.b.c
            r3.<init>()
            r4 = 2
        L_0x0012:
            r5 = 0
            r5 = r6[r5]
            if (r4 >= r5) goto L_0x0068
            r5 = 913(0x391, float:1.28E-42)
            if (r2 == r5) goto L_0x0053
            switch(r2) {
                case 900: goto L_0x0023;
                case 901: goto L_0x004e;
                case 902: goto L_0x0049;
                default: goto L_0x001e;
            }
        L_0x001e:
            switch(r2) {
                case 922: goto L_0x0044;
                case 923: goto L_0x0044;
                case 924: goto L_0x004e;
                case 925: goto L_0x0041;
                case 926: goto L_0x003e;
                case 927: goto L_0x002d;
                case 928: goto L_0x0028;
                default: goto L_0x0021;
            }
        L_0x0021:
            int r4 = r4 + -1
        L_0x0023:
            int r2 = a(r6, r4, r0)
            goto L_0x005b
        L_0x0028:
            int r2 = a(r6, r4, r3)
            goto L_0x005b
        L_0x002d:
            int r2 = r4 + 1
            r1 = r6[r4]
            com.onfido.d.a.c r1 = com.onfido.d.a.c.a(r1)
            java.lang.String r1 = r1.name()
            java.nio.charset.Charset r1 = java.nio.charset.Charset.forName(r1)
            goto L_0x005b
        L_0x003e:
            int r2 = r4 + 2
            goto L_0x005b
        L_0x0041:
            int r2 = r4 + 1
            goto L_0x005b
        L_0x0044:
            com.onfido.d.f r6 = com.onfido.d.f.a()
            throw r6
        L_0x0049:
            int r2 = b(r6, r4, r0)
            goto L_0x005b
        L_0x004e:
            int r2 = a(r2, r6, r1, r4, r0)
            goto L_0x005b
        L_0x0053:
            int r2 = r4 + 1
            r4 = r6[r4]
            char r4 = (char) r4
            r0.append(r4)
        L_0x005b:
            int r4 = r6.length
            if (r2 >= r4) goto L_0x0063
            int r4 = r2 + 1
            r2 = r6[r2]
            goto L_0x0012
        L_0x0063:
            com.onfido.d.f r6 = com.onfido.d.f.a()
            throw r6
        L_0x0068:
            int r6 = r0.length()
            if (r6 != 0) goto L_0x0073
            com.onfido.d.f r6 = com.onfido.d.f.a()
            throw r6
        L_0x0073:
            com.onfido.d.a.d r6 = new com.onfido.d.a.d
            java.lang.String r0 = r0.toString()
            r1 = 0
            r6.<init>(r1, r0, r1, r7)
            r6.a(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.d.b.a.e.a(int[], java.lang.String):com.onfido.d.a.d");
    }

    private static String a(int[] iArr, int i) {
        BigInteger bigInteger = BigInteger.ZERO;
        for (int i2 = 0; i2 < i; i2++) {
            bigInteger = bigInteger.add(c[(i - i2) - 1].multiply(BigInteger.valueOf((long) iArr[i2])));
        }
        String bigInteger2 = bigInteger.toString();
        if (bigInteger2.charAt(0) == '1') {
            return bigInteger2.substring(1);
        }
        throw f.a();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0037, code lost:
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003f, code lost:
        r4 = r1;
        r1 = r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        if (r4 != 900) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x006f, code lost:
        r1 = com.onfido.d.b.a.e.a.a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x008f, code lost:
        r4 = (char) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0099, code lost:
        r3 = com.onfido.d.b.a.e.a.f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x009b, code lost:
        r4 = 0;
        r11 = r3;
        r3 = r1;
        r1 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00a0, code lost:
        r1 = com.onfido.d.b.a.e.a.c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x00a3, code lost:
        r1 = com.onfido.d.b.a.e.a.b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00a6, code lost:
        r4 = ' ';
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x00ab, code lost:
        r15.append((char) r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x00af, code lost:
        r4 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x00b0, code lost:
        if (r4 == 0) goto L_0x00b5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x00b2, code lost:
        r15.append(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x00b5, code lost:
        r0 = r0 + 1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(int[] r12, int[] r13, int r14, java.lang.StringBuilder r15) {
        /*
            com.onfido.d.b.a.e$a r0 = com.onfido.d.b.a.e.a.ALPHA
            com.onfido.d.b.a.e$a r1 = com.onfido.d.b.a.e.a.ALPHA
            r2 = 0
            r3 = r1
            r1 = r0
            r0 = 0
        L_0x0008:
            if (r0 >= r14) goto L_0x00b9
            r4 = r12[r0]
            int[] r5 = com.onfido.d.b.a.e.AnonymousClass1.a
            int r6 = r1.ordinal()
            r5 = r5[r6]
            r6 = 32
            r7 = 26
            r8 = 29
            r9 = 913(0x391, float:1.28E-42)
            r10 = 900(0x384, float:1.261E-42)
            switch(r5) {
                case 1: goto L_0x008b;
                case 2: goto L_0x0078;
                case 3: goto L_0x005e;
                case 4: goto L_0x004b;
                case 5: goto L_0x003a;
                case 6: goto L_0x0023;
                default: goto L_0x0021;
            }
        L_0x0021:
            goto L_0x00af
        L_0x0023:
            if (r4 >= r8) goto L_0x002a
            char[] r1 = a
            char r1 = r1[r4]
            goto L_0x003f
        L_0x002a:
            if (r4 == r8) goto L_0x006f
            if (r4 == r10) goto L_0x006f
            if (r4 == r9) goto L_0x0031
            goto L_0x0037
        L_0x0031:
            r1 = r13[r0]
            char r1 = (char) r1
            r15.append(r1)
        L_0x0037:
            r1 = r3
            goto L_0x00af
        L_0x003a:
            if (r4 >= r7) goto L_0x0043
            int r4 = r4 + 65
            char r1 = (char) r4
        L_0x003f:
            r4 = r1
            r1 = r3
            goto L_0x00b0
        L_0x0043:
            if (r4 == r7) goto L_0x0048
            if (r4 == r10) goto L_0x006f
            goto L_0x0037
        L_0x0048:
            r1 = r3
            goto L_0x00a6
        L_0x004b:
            if (r4 >= r8) goto L_0x0053
            char[] r5 = a
            char r4 = r5[r4]
            goto L_0x00b0
        L_0x0053:
            if (r4 == r8) goto L_0x006f
            if (r4 == r10) goto L_0x006f
            if (r4 == r9) goto L_0x005b
            goto L_0x00af
        L_0x005b:
            r4 = r13[r0]
            goto L_0x00ab
        L_0x005e:
            r5 = 25
            if (r4 >= r5) goto L_0x0067
            char[] r5 = b
            char r4 = r5[r4]
            goto L_0x00b0
        L_0x0067:
            if (r4 == r10) goto L_0x006f
            if (r4 == r9) goto L_0x0075
            switch(r4) {
                case 25: goto L_0x0072;
                case 26: goto L_0x00a6;
                case 27: goto L_0x00a3;
                case 28: goto L_0x006f;
                case 29: goto L_0x0099;
                default: goto L_0x006e;
            }
        L_0x006e:
            goto L_0x00af
        L_0x006f:
            com.onfido.d.b.a.e$a r1 = com.onfido.d.b.a.e.a.ALPHA
            goto L_0x00af
        L_0x0072:
            com.onfido.d.b.a.e$a r1 = com.onfido.d.b.a.e.a.PUNCT
            goto L_0x00af
        L_0x0075:
            r4 = r13[r0]
            goto L_0x00ab
        L_0x0078:
            if (r4 >= r7) goto L_0x007d
            int r4 = r4 + 97
            goto L_0x008f
        L_0x007d:
            if (r4 == r10) goto L_0x006f
            if (r4 == r9) goto L_0x0088
            switch(r4) {
                case 26: goto L_0x00a6;
                case 27: goto L_0x0085;
                case 28: goto L_0x00a0;
                case 29: goto L_0x0099;
                default: goto L_0x0084;
            }
        L_0x0084:
            goto L_0x00af
        L_0x0085:
            com.onfido.d.b.a.e$a r3 = com.onfido.d.b.a.e.a.ALPHA_SHIFT
            goto L_0x009b
        L_0x0088:
            r4 = r13[r0]
            goto L_0x00ab
        L_0x008b:
            if (r4 >= r7) goto L_0x0091
            int r4 = r4 + 65
        L_0x008f:
            char r4 = (char) r4
            goto L_0x00b0
        L_0x0091:
            if (r4 == r10) goto L_0x006f
            if (r4 == r9) goto L_0x00a9
            switch(r4) {
                case 26: goto L_0x00a6;
                case 27: goto L_0x00a3;
                case 28: goto L_0x00a0;
                case 29: goto L_0x0099;
                default: goto L_0x0098;
            }
        L_0x0098:
            goto L_0x00af
        L_0x0099:
            com.onfido.d.b.a.e$a r3 = com.onfido.d.b.a.e.a.PUNCT_SHIFT
        L_0x009b:
            r4 = 0
            r11 = r3
            r3 = r1
            r1 = r11
            goto L_0x00b0
        L_0x00a0:
            com.onfido.d.b.a.e$a r1 = com.onfido.d.b.a.e.a.MIXED
            goto L_0x00af
        L_0x00a3:
            com.onfido.d.b.a.e$a r1 = com.onfido.d.b.a.e.a.LOWER
            goto L_0x00af
        L_0x00a6:
            r4 = 32
            goto L_0x00b0
        L_0x00a9:
            r4 = r13[r0]
        L_0x00ab:
            char r4 = (char) r4
            r15.append(r4)
        L_0x00af:
            r4 = 0
        L_0x00b0:
            if (r4 == 0) goto L_0x00b5
            r15.append(r4)
        L_0x00b5:
            int r0 = r0 + 1
            goto L_0x0008
        L_0x00b9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.onfido.d.b.a.e.a(int[], int[], int, java.lang.StringBuilder):void");
    }

    private static int b(int[] iArr, int i, StringBuilder sb) {
        int[] iArr2 = new int[15];
        boolean z = false;
        int i2 = 0;
        while (i < iArr[0] && !z) {
            int i3 = i + 1;
            int i4 = iArr[i];
            if (i3 == iArr[0]) {
                z = true;
            }
            if (i4 < 900) {
                iArr2[i2] = i4;
                i2++;
            } else {
                if (i4 != 928) {
                    switch (i4) {
                        case 900:
                        case 901:
                            break;
                        default:
                            switch (i4) {
                                case 922:
                                case 923:
                                case 924:
                                    break;
                            }
                    }
                }
                i3--;
                z = true;
            }
            if ((i2 % 15 == 0 || i4 == 902 || z) && i2 > 0) {
                sb.append(a(iArr2, i2));
                i2 = 0;
            }
            i = i3;
        }
        return i;
    }
}
