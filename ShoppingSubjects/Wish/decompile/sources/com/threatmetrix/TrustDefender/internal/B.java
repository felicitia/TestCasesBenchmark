package com.threatmetrix.TrustDefender.internal;

import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

final class B {

    /* renamed from: byte reason: not valid java name */
    static final byte[] f50byte = {6, 9, 42, -122, 72, -122, -9, 13, 1, 9, 3};

    /* renamed from: case reason: not valid java name */
    static final byte[] f51case = {6, 9, 42, -122, 72, -122, -9, 13, 1, 7, 2};

    /* renamed from: char reason: not valid java name */
    static final byte[] f52char = {6, 9, 42, -122, 72, -122, -9, 13, 1, 9, 21};

    /* renamed from: do reason: not valid java name */
    static final byte[] f53do = {6, 9, 42, -122, 72, -122, -9, 13, 1, 9, 4};

    /* renamed from: else reason: not valid java name */
    static final byte[] f54else = {6, 9, 42, -122, 72, -122, -9, 13, 1, 7, 1};

    /* renamed from: for reason: not valid java name */
    static final byte[] f55for = {6, 3, 85, 4, 3};

    /* renamed from: if reason: not valid java name */
    static final byte[] f56if = {6, 9, 42, -122, 72, -122, -9, 13, 1, 1, 11};

    /* renamed from: int reason: not valid java name */
    static final byte[] f57int = {6, 10, 42, -122, 72, -122, -9, 13, 1, 9, 25, 3};

    /* renamed from: new reason: not valid java name */
    static final byte[] f58new = {6, 8, 42, -122, 72, -50, 61, 4, 3, 2};

    /* renamed from: try reason: not valid java name */
    static final byte[] f59try = {6, 9, 96, -122, 72, 1, 101, 3, 4, 2, 1};

    B() {
    }

    /* renamed from: do reason: not valid java name */
    private static int m13do(int i, byte[] bArr, int i2) {
        if (i > 128) {
            int i3 = 0;
            while ((i >> (i3 * 8)) != 0) {
                i3++;
            }
            if (bArr != null) {
                int i4 = i2 + 1;
                bArr[i2] = (byte) (i3 | 128);
                int i5 = i3 - 1;
                while (i5 >= 0) {
                    int i6 = i4 + 1;
                    bArr[i4] = (byte) ((i >> (i5 * 8)) & 255);
                    i5--;
                    i4 = i6;
                }
            }
            return i3 + 1;
        }
        if (bArr != null && i2 > 0) {
            bArr[i2] = (byte) i;
        }
        return 1;
    }

    /* renamed from: for reason: not valid java name */
    static byte[] m17for(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length + 1;
        byte[] bArr2 = new byte[(m13do(length, null, -1) + 1 + length)];
        bArr2[0] = 3;
        int i = m13do(length, bArr2, 1) + 1;
        int i2 = i + 1;
        bArr2[i] = 0;
        System.arraycopy(bArr, 0, bArr2, i2, bArr.length);
        return bArr2;
    }

    /* renamed from: if reason: not valid java name */
    static byte[] m21if(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[(m13do(length, null, -1) + 1 + length)];
        bArr2[0] = 4;
        System.arraycopy(bArr, 0, bArr2, m13do(length, bArr2, 1) + 1, bArr.length);
        return bArr2;
    }

    /* renamed from: for reason: not valid java name */
    private static int m14for(int i, byte[] bArr, byte[] bArr2, int i2) {
        int i3 = m13do(bArr.length, null, -1) + 1 + bArr.length;
        if (bArr2 != null && bArr2.length >= i3) {
            int i4 = i2 + 1;
            bArr2[i2] = (byte) i;
            System.arraycopy(bArr, 0, bArr2, i4 + m13do(bArr.length, bArr2, i4), bArr.length);
        }
        return i3;
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.List, code=java.util.List<java.lang.Object>, for r4v0, types: [java.util.List, java.util.List<java.lang.Object>] */
    /* renamed from: if reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m19if(java.util.List<java.lang.Object> r4, byte[] r5, int r6) {
        /*
            if (r5 == 0) goto L_0x0009
            r0 = 48
            r5[r6] = r0
            int r0 = r6 + 2
            goto L_0x000a
        L_0x0009:
            r0 = r6
        L_0x000a:
            r1 = 0
            java.util.Iterator r4 = r4.iterator()
        L_0x000f:
            boolean r2 = r4.hasNext()
            if (r2 == 0) goto L_0x0022
            java.lang.Object r2 = r4.next()
            int r2 = m18if(r2, r5, r0)
            int r1 = r1 + r2
            if (r5 == 0) goto L_0x000f
            int r0 = r0 + r2
            goto L_0x000f
        L_0x0022:
            r4 = 0
            r0 = -1
            int r4 = m13do(r1, r4, r0)
            int r0 = r4 + 1
            int r0 = r0 + r1
            if (r5 == 0) goto L_0x003b
            r2 = 1
            if (r4 <= r2) goto L_0x0037
            int r3 = r6 + 2
            int r4 = r4 + r6
            int r4 = r4 + r2
            java.lang.System.arraycopy(r5, r3, r5, r4, r1)
        L_0x0037:
            int r6 = r6 + r2
            m13do(r1, r5, r6)
        L_0x003b:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.B.m19if(java.util.List, byte[], int):int");
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Set, code=java.util.Set<java.lang.Object>, for r6v0, types: [java.util.Set<java.lang.Object>, java.util.Set] */
    /* renamed from: for reason: not valid java name */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int m15for(java.util.Set<java.lang.Object> r6, byte[] r7, int r8) {
        /*
            r0 = 0
            r1 = -1
            r2 = 0
            if (r7 != 0) goto L_0x0021
            java.util.Iterator r6 = r6.iterator()
        L_0x0009:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x0019
            java.lang.Object r7 = r6.next()
            int r7 = m18if(r7, r2, r1)
            int r0 = r0 + r7
            goto L_0x0009
        L_0x0019:
            int r6 = m13do(r0, r2, r1)
            int r6 = r6 + 1
            int r6 = r6 + r0
            return r6
        L_0x0021:
            java.util.ArrayList r3 = new java.util.ArrayList
            int r4 = r6.size()
            r3.<init>(r4)
            java.util.Iterator r6 = r6.iterator()
            r4 = 0
        L_0x002f:
            boolean r5 = r6.hasNext()
            if (r5 == 0) goto L_0x0043
            java.lang.Object r5 = r6.next()
            byte[] r5 = m20if(r5)
            r3.add(r5)
            int r5 = r5.length
            int r4 = r4 + r5
            goto L_0x002f
        L_0x0043:
            int r6 = m13do(r4, r2, r1)
            int r1 = r6 + 1
            int r1 = r1 + r4
            int r2 = r7.length
            if (r2 < r1) goto L_0x0076
            com.threatmetrix.TrustDefender.internal.B$3 r2 = new com.threatmetrix.TrustDefender.internal.B$3
            r2.<init>()
            java.util.Collections.sort(r3, r2)
            int r2 = r8 + 1
            r5 = 49
            r7[r8] = r5
            m13do(r4, r7, r2)
            int r2 = r2 + r6
            java.util.Iterator r6 = r3.iterator()
        L_0x0063:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0076
            java.lang.Object r8 = r6.next()
            byte[] r8 = (byte[]) r8
            int r3 = r8.length
            java.lang.System.arraycopy(r8, r0, r7, r2, r3)
            int r8 = r8.length
            int r2 = r2 + r8
            goto L_0x0063
        L_0x0076:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.threatmetrix.TrustDefender.internal.B.m15for(java.util.Set, byte[], int):int");
    }

    /* renamed from: if reason: not valid java name */
    private static int m18if(Object obj, byte[] bArr, int i) {
        if (obj == null) {
            if (bArr != null) {
                bArr[i] = 5;
                bArr[i + 1] = 0;
            }
            return 2;
        } else if (obj instanceof List) {
            return m19if((List) obj, bArr, i);
        } else {
            if (obj instanceof Set) {
                return m15for((Set) obj, bArr, i);
            }
            if (obj instanceof String) {
                return m14for(12, String.valueOf(obj).getBytes(Charset.forName("UTF8")), bArr, i);
            }
            if (obj instanceof Integer) {
                return m14for(2, BigInteger.valueOf((long) ((Integer) obj).intValue()).toByteArray(), bArr, i);
            }
            if (obj instanceof BigInteger) {
                return m14for(2, ((BigInteger) obj).toByteArray(), bArr, i);
            }
            if (!(obj instanceof byte[])) {
                return 0;
            }
            byte[] bArr2 = (byte[]) obj;
            if (bArr != null) {
                System.arraycopy(bArr2, 0, bArr, i, bArr2.length);
            }
            return bArr2.length;
        }
    }

    /* renamed from: if reason: not valid java name */
    static byte[] m20if(Object obj) {
        if (obj != null && (obj instanceof byte[])) {
            return (byte[]) ((byte[]) obj).clone();
        }
        byte[] bArr = new byte[m18if(obj, (byte[]) null, -1)];
        m18if(obj, bArr, 0);
        return bArr;
    }

    /* renamed from: for reason: not valid java name */
    static byte[] m16for(Object obj) {
        byte[] bArr = new byte[m18if(obj, (byte[]) null, -1)];
        m18if(obj, bArr, 0);
        bArr[0] = -96;
        return bArr;
    }
}
