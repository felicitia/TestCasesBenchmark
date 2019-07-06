package com.threatmetrix.TrustDefender.internal;

public final class O {
    /* renamed from: int reason: not valid java name */
    static long[] m230int(int i, int i2) {
        long[] jArr = new long[4];
        jArr[0] = ((((long) i) & 4294967295L) << 32) | (((long) i2) & 4294967295L);
        for (int i3 = 1; i3 < 4; i3++) {
            long j = jArr[i3 - 1];
            jArr[i3] = ((j ^ (j >> 30)) * 1812433253) + ((long) i3);
        }
        return jArr;
    }
}
