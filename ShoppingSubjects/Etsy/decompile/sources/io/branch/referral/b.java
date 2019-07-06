package io.branch.referral;

import java.io.UnsupportedEncodingException;

/* compiled from: Base64 */
class b {

    /* compiled from: Base64 */
    static abstract class a {
        public byte[] a;
        public int b;

        a() {
        }
    }

    /* renamed from: io.branch.referral.b$b reason: collision with other inner class name */
    /* compiled from: Base64 */
    static class C0179b extends a {
        private static final int[] c = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private static final int[] d = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -2, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        private int e;
        private int f;
        private final int[] g;

        public C0179b(int i, byte[] bArr) {
            this.a = bArr;
            this.g = (i & 8) == 0 ? c : d;
            this.e = 0;
            this.f = 0;
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0074, code lost:
            r0 = r13;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:45:0x00d8, code lost:
            r6 = r13;
         */
        /* JADX WARNING: Removed duplicated region for block: B:52:0x00e5  */
        /* JADX WARNING: Removed duplicated region for block: B:54:0x00ec  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(byte[] r12, int r13, int r14, boolean r15) {
            /*
                r11 = this;
                int r0 = r11.e
                r1 = 0
                r2 = 6
                if (r0 != r2) goto L_0x0007
                return r1
            L_0x0007:
                int r14 = r14 + r13
                int r0 = r11.e
                int r3 = r11.f
                byte[] r4 = r11.a
                int[] r5 = r11.g
                r6 = r3
                r3 = r1
            L_0x0012:
                r7 = 4
                if (r13 >= r14) goto L_0x00e2
                if (r0 != 0) goto L_0x005c
            L_0x0017:
                int r8 = r13 + 4
                if (r8 > r14) goto L_0x0058
                byte r6 = r12[r13]
                r6 = r6 & 255(0xff, float:3.57E-43)
                r6 = r5[r6]
                int r6 = r6 << 18
                int r9 = r13 + 1
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                int r9 = r9 << 12
                r6 = r6 | r9
                int r9 = r13 + 2
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                int r9 = r9 << r2
                r6 = r6 | r9
                int r9 = r13 + 3
                byte r9 = r12[r9]
                r9 = r9 & 255(0xff, float:3.57E-43)
                r9 = r5[r9]
                r6 = r6 | r9
                if (r6 < 0) goto L_0x0058
                int r13 = r3 + 2
                byte r9 = (byte) r6
                r4[r13] = r9
                int r13 = r3 + 1
                int r9 = r6 >> 8
                byte r9 = (byte) r9
                r4[r13] = r9
                int r13 = r6 >> 16
                byte r13 = (byte) r13
                r4[r3] = r13
                int r3 = r3 + 3
                r13 = r8
                goto L_0x0017
            L_0x0058:
                if (r13 < r14) goto L_0x005c
                goto L_0x00e2
            L_0x005c:
                int r8 = r13 + 1
                byte r13 = r12[r13]
                r13 = r13 & 255(0xff, float:3.57E-43)
                r13 = r5[r13]
                r9 = -2
                r10 = -1
                switch(r0) {
                    case 0: goto L_0x00d4;
                    case 1: goto L_0x00c7;
                    case 2: goto L_0x00ae;
                    case 3: goto L_0x007c;
                    case 4: goto L_0x0070;
                    case 5: goto L_0x006b;
                    default: goto L_0x0069;
                }
            L_0x0069:
                goto L_0x00df
            L_0x006b:
                if (r13 == r10) goto L_0x00df
                r11.e = r2
                return r1
            L_0x0070:
                if (r13 != r9) goto L_0x0077
                int r13 = r0 + 1
            L_0x0074:
                r0 = r13
                goto L_0x00df
            L_0x0077:
                if (r13 == r10) goto L_0x00df
                r11.e = r2
                return r1
            L_0x007c:
                if (r13 < 0) goto L_0x0097
                int r0 = r6 << 6
                r13 = r13 | r0
                int r0 = r3 + 2
                byte r6 = (byte) r13
                r4[r0] = r6
                int r0 = r3 + 1
                int r6 = r13 >> 8
                byte r6 = (byte) r6
                r4[r0] = r6
                int r0 = r13 >> 16
                byte r0 = (byte) r0
                r4[r3] = r0
                int r3 = r3 + 3
                r6 = r13
                r0 = r1
                goto L_0x00df
            L_0x0097:
                if (r13 != r9) goto L_0x00a9
                int r13 = r3 + 1
                int r0 = r6 >> 2
                byte r0 = (byte) r0
                r4[r13] = r0
                int r13 = r6 >> 10
                byte r13 = (byte) r13
                r4[r3] = r13
                int r3 = r3 + 2
                r13 = 5
                goto L_0x0074
            L_0x00a9:
                if (r13 == r10) goto L_0x00df
                r11.e = r2
                return r1
            L_0x00ae:
                if (r13 < 0) goto L_0x00b6
                int r6 = r6 << 6
                r13 = r13 | r6
                int r0 = r0 + 1
                goto L_0x00d8
            L_0x00b6:
                if (r13 != r9) goto L_0x00c2
                int r13 = r3 + 1
                int r0 = r6 >> 4
                byte r0 = (byte) r0
                r4[r3] = r0
                r3 = r13
                r0 = r7
                goto L_0x00df
            L_0x00c2:
                if (r13 == r10) goto L_0x00df
                r11.e = r2
                return r1
            L_0x00c7:
                if (r13 < 0) goto L_0x00cf
                int r6 = r6 << 6
                r13 = r13 | r6
                int r0 = r0 + 1
                goto L_0x00d8
            L_0x00cf:
                if (r13 == r10) goto L_0x00df
                r11.e = r2
                return r1
            L_0x00d4:
                if (r13 < 0) goto L_0x00da
                int r0 = r0 + 1
            L_0x00d8:
                r6 = r13
                goto L_0x00df
            L_0x00da:
                if (r13 == r10) goto L_0x00df
                r11.e = r2
                return r1
            L_0x00df:
                r13 = r8
                goto L_0x0012
            L_0x00e2:
                r12 = 1
                if (r15 != 0) goto L_0x00ec
                r11.e = r0
                r11.f = r6
                r11.b = r3
                return r12
            L_0x00ec:
                switch(r0) {
                    case 0: goto L_0x010e;
                    case 1: goto L_0x010b;
                    case 2: goto L_0x0102;
                    case 3: goto L_0x00f3;
                    case 4: goto L_0x00f0;
                    default: goto L_0x00ef;
                }
            L_0x00ef:
                goto L_0x010e
            L_0x00f0:
                r11.e = r2
                return r1
            L_0x00f3:
                int r13 = r3 + 1
                int r14 = r6 >> 10
                byte r14 = (byte) r14
                r4[r3] = r14
                int r3 = r13 + 1
                int r14 = r6 >> 2
                byte r14 = (byte) r14
                r4[r13] = r14
                goto L_0x010e
            L_0x0102:
                int r13 = r3 + 1
                int r14 = r6 >> 4
                byte r14 = (byte) r14
                r4[r3] = r14
                r3 = r13
                goto L_0x010e
            L_0x010b:
                r11.e = r2
                return r1
            L_0x010e:
                r11.e = r0
                r11.b = r3
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.b.C0179b.a(byte[], int, int, boolean):boolean");
        }
    }

    /* compiled from: Base64 */
    static class c extends a {
        private static final byte[] g = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
        private static final byte[] h = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
        int c;
        public final boolean d;
        public final boolean e;
        public final boolean f;
        private final byte[] i;
        private int j;
        private final byte[] k;

        public c(int i2, byte[] bArr) {
            this.a = bArr;
            boolean z = true;
            this.d = (i2 & 1) == 0;
            this.e = (i2 & 2) == 0;
            if ((i2 & 4) == 0) {
                z = false;
            }
            this.f = z;
            this.k = (i2 & 8) == 0 ? g : h;
            this.i = new byte[2];
            this.c = 0;
            this.j = this.e ? 19 : -1;
        }

        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean a(byte[] r19, int r20, int r21, boolean r22) {
            /*
                r18 = this;
                r0 = r18
                byte[] r3 = r0.k
                byte[] r4 = r0.a
                int r5 = r0.j
                int r6 = r21 + r20
                int r7 = r0.c
                r8 = -1
                r9 = 0
                r10 = 1
                switch(r7) {
                    case 0: goto L_0x004f;
                    case 1: goto L_0x0030;
                    case 2: goto L_0x0013;
                    default: goto L_0x0012;
                }
            L_0x0012:
                goto L_0x004f
            L_0x0013:
                int r7 = r20 + 1
                if (r7 > r6) goto L_0x004f
                byte[] r11 = r0.i
                byte r11 = r11[r9]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                byte[] r12 = r0.i
                byte r12 = r12[r10]
                r12 = r12 & 255(0xff, float:3.57E-43)
                int r12 = r12 << 8
                r11 = r11 | r12
                byte r2 = r19[r20]
                r2 = r2 & 255(0xff, float:3.57E-43)
                r2 = r2 | r11
                r0.c = r9
                goto L_0x0052
            L_0x0030:
                int r7 = r20 + 2
                if (r7 > r6) goto L_0x004f
                byte[] r7 = r0.i
                byte r7 = r7[r9]
                r7 = r7 & 255(0xff, float:3.57E-43)
                int r7 = r7 << 16
                int r11 = r20 + 1
                byte r2 = r19[r20]
                r2 = r2 & 255(0xff, float:3.57E-43)
                int r2 = r2 << 8
                r2 = r2 | r7
                int r7 = r11 + 1
                byte r11 = r19[r11]
                r11 = r11 & 255(0xff, float:3.57E-43)
                r2 = r2 | r11
                r0.c = r9
                goto L_0x0052
            L_0x004f:
                r7 = r20
                r2 = r8
            L_0x0052:
                r12 = 4
                r13 = 13
                r14 = 10
                r15 = 2
                if (r2 == r8) goto L_0x0090
                int r8 = r2 >> 18
                r8 = r8 & 63
                byte r8 = r3[r8]
                r4[r9] = r8
                int r8 = r2 >> 12
                r8 = r8 & 63
                byte r8 = r3[r8]
                r4[r10] = r8
                int r8 = r2 >> 6
                r8 = r8 & 63
                byte r8 = r3[r8]
                r4[r15] = r8
                r2 = r2 & 63
                byte r2 = r3[r2]
                r8 = 3
                r4[r8] = r2
                int r5 = r5 + -1
                if (r5 != 0) goto L_0x008d
                boolean r2 = r0.f
                if (r2 == 0) goto L_0x0085
                r2 = 5
                r4[r12] = r13
                goto L_0x0086
            L_0x0085:
                r2 = r12
            L_0x0086:
                int r5 = r2 + 1
                r4[r2] = r14
                r2 = 19
                goto L_0x0092
            L_0x008d:
                r2 = r5
                r5 = r12
                goto L_0x0092
            L_0x0090:
                r2 = r5
                r5 = r9
            L_0x0092:
                int r8 = r7 + 3
                if (r8 > r6) goto L_0x00eb
                byte r11 = r19[r7]
                r11 = r11 & 255(0xff, float:3.57E-43)
                int r11 = r11 << 16
                int r16 = r7 + 1
                byte r10 = r19[r16]
                r10 = r10 & 255(0xff, float:3.57E-43)
                int r10 = r10 << 8
                r10 = r10 | r11
                int r7 = r7 + 2
                byte r7 = r19[r7]
                r7 = r7 & 255(0xff, float:3.57E-43)
                r7 = r7 | r10
                int r10 = r7 >> 18
                r10 = r10 & 63
                byte r10 = r3[r10]
                r4[r5] = r10
                int r10 = r5 + 1
                int r11 = r7 >> 12
                r11 = r11 & 63
                byte r11 = r3[r11]
                r4[r10] = r11
                int r10 = r5 + 2
                int r11 = r7 >> 6
                r11 = r11 & 63
                byte r11 = r3[r11]
                r4[r10] = r11
                int r10 = r5 + 3
                r7 = r7 & 63
                byte r7 = r3[r7]
                r4[r10] = r7
                int r5 = r5 + 4
                int r2 = r2 + -1
                if (r2 != 0) goto L_0x00e8
                boolean r2 = r0.f
                if (r2 == 0) goto L_0x00df
                int r2 = r5 + 1
                r4[r5] = r13
                goto L_0x00e0
            L_0x00df:
                r2 = r5
            L_0x00e0:
                int r5 = r2 + 1
                r4[r2] = r14
                r7 = r8
                r2 = 19
                goto L_0x00e9
            L_0x00e8:
                r7 = r8
            L_0x00e9:
                r10 = 1
                goto L_0x0092
            L_0x00eb:
                if (r22 == 0) goto L_0x01ca
                int r8 = r0.c
                int r8 = r7 - r8
                int r10 = r6 + -1
                if (r8 != r10) goto L_0x013f
                int r6 = r0.c
                if (r6 <= 0) goto L_0x00ff
                byte[] r1 = r0.i
                byte r1 = r1[r9]
                r9 = 1
                goto L_0x0101
            L_0x00ff:
                byte r1 = r19[r7]
            L_0x0101:
                r1 = r1 & 255(0xff, float:3.57E-43)
                int r1 = r1 << r12
                int r6 = r0.c
                int r6 = r6 - r9
                r0.c = r6
                int r6 = r5 + 1
                int r7 = r1 >> 6
                r7 = r7 & 63
                byte r7 = r3[r7]
                r4[r5] = r7
                int r5 = r6 + 1
                r1 = r1 & 63
                byte r1 = r3[r1]
                r4[r6] = r1
                boolean r1 = r0.d
                if (r1 == 0) goto L_0x0129
                int r1 = r5 + 1
                r3 = 61
                r4[r5] = r3
                int r5 = r1 + 1
                r4[r1] = r3
            L_0x0129:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x013c
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x0136
                int r1 = r5 + 1
                r4[r5] = r13
                goto L_0x0137
            L_0x0136:
                r1 = r5
            L_0x0137:
                int r3 = r1 + 1
                r4[r1] = r14
            L_0x013b:
                r5 = r3
            L_0x013c:
                r6 = 1
                goto L_0x01f9
            L_0x013f:
                int r8 = r0.c
                int r8 = r7 - r8
                int r6 = r6 - r15
                if (r8 != r6) goto L_0x01b0
                int r6 = r0.c
                r8 = 1
                if (r6 <= r8) goto L_0x0151
                byte[] r6 = r0.i
                byte r6 = r6[r9]
                r9 = 1
                goto L_0x015a
            L_0x0151:
                int r6 = r7 + 1
                byte r7 = r19[r7]
                r17 = r7
                r7 = r6
                r6 = r17
            L_0x015a:
                r6 = r6 & 255(0xff, float:3.57E-43)
                int r6 = r6 << r14
                int r8 = r0.c
                if (r8 <= 0) goto L_0x0169
                byte[] r1 = r0.i
                int r7 = r9 + 1
                byte r1 = r1[r9]
                r9 = r7
                goto L_0x016b
            L_0x0169:
                byte r1 = r19[r7]
            L_0x016b:
                r1 = r1 & 255(0xff, float:3.57E-43)
                int r1 = r1 << r15
                r1 = r1 | r6
                int r6 = r0.c
                int r6 = r6 - r9
                r0.c = r6
                int r6 = r5 + 1
                int r7 = r1 >> 12
                r7 = r7 & 63
                byte r7 = r3[r7]
                r4[r5] = r7
                int r5 = r6 + 1
                int r7 = r1 >> 6
                r7 = r7 & 63
                byte r7 = r3[r7]
                r4[r6] = r7
                int r6 = r5 + 1
                r1 = r1 & 63
                byte r1 = r3[r1]
                r4[r5] = r1
                boolean r1 = r0.d
                if (r1 == 0) goto L_0x019b
                int r1 = r6 + 1
                r3 = 61
                r4[r6] = r3
                goto L_0x019c
            L_0x019b:
                r1 = r6
            L_0x019c:
                boolean r3 = r0.e
                if (r3 == 0) goto L_0x01ae
                boolean r3 = r0.f
                if (r3 == 0) goto L_0x01a9
                int r3 = r1 + 1
                r4[r1] = r13
                r1 = r3
            L_0x01a9:
                int r3 = r1 + 1
                r4[r1] = r14
                goto L_0x013b
            L_0x01ae:
                r5 = r1
                goto L_0x013c
            L_0x01b0:
                boolean r1 = r0.e
                if (r1 == 0) goto L_0x013c
                if (r5 <= 0) goto L_0x013c
                r1 = 19
                if (r2 == r1) goto L_0x013c
                boolean r1 = r0.f
                if (r1 == 0) goto L_0x01c3
                int r1 = r5 + 1
                r4[r5] = r13
                goto L_0x01c4
            L_0x01c3:
                r1 = r5
            L_0x01c4:
                int r5 = r1 + 1
                r4[r1] = r14
                goto L_0x013c
            L_0x01ca:
                int r3 = r6 + -1
                if (r7 != r3) goto L_0x01dc
                byte[] r3 = r0.i
                int r4 = r0.c
                int r6 = r4 + 1
                r0.c = r6
                byte r1 = r19[r7]
                r3[r4] = r1
                goto L_0x013c
            L_0x01dc:
                int r6 = r6 - r15
                if (r7 != r6) goto L_0x013c
                byte[] r3 = r0.i
                int r4 = r0.c
                int r6 = r4 + 1
                r0.c = r6
                byte r6 = r19[r7]
                r3[r4] = r6
                byte[] r3 = r0.i
                int r4 = r0.c
                int r6 = r4 + 1
                r0.c = r6
                r6 = 1
                int r7 = r7 + r6
                byte r1 = r19[r7]
                r3[r4] = r1
            L_0x01f9:
                r0.b = r5
                r0.j = r2
                return r6
            */
            throw new UnsupportedOperationException("Method not decompiled: io.branch.referral.b.c.a(byte[], int, int, boolean):boolean");
        }
    }

    public static byte[] a(byte[] bArr, int i) {
        return a(bArr, 0, bArr.length, i);
    }

    public static byte[] a(byte[] bArr, int i, int i2, int i3) {
        C0179b bVar = new C0179b(i3, new byte[((i2 * 3) / 4)]);
        if (!bVar.a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (bVar.b == bVar.a.length) {
            return bVar.a;
        } else {
            byte[] bArr2 = new byte[bVar.b];
            System.arraycopy(bVar.a, 0, bArr2, 0, bVar.b);
            return bArr2;
        }
    }

    public static String b(byte[] bArr, int i) {
        try {
            return new String(c(bArr, i), "US-ASCII");
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError(e);
        }
    }

    public static byte[] c(byte[] bArr, int i) {
        return b(bArr, 0, bArr.length, i);
    }

    public static byte[] b(byte[] bArr, int i, int i2, int i3) {
        c cVar = new c(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!cVar.d) {
            switch (i2 % 3) {
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (cVar.e && i2 > 0) {
            i4 += (((i2 - 1) / 57) + 1) * (cVar.f ? 2 : 1);
        }
        cVar.a = new byte[i4];
        cVar.a(bArr, i, i2, true);
        return cVar.a;
    }
}
