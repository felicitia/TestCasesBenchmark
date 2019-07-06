package com.threatmetrix.TrustDefender.internal;

public class I {
    /* renamed from: if reason: not valid java name */
    public static void m79if(byte[] bArr, byte b, long j) {
        for (int i = 0; i < bArr.length; i++) {
            if ((j & (1 << i)) != 0) {
                bArr[i] = (byte) (bArr[i] ^ b);
            }
        }
    }
}
