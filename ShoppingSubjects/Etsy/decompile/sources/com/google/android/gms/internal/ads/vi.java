package com.google.android.gms.internal.ads;

import java.security.SecureRandom;

public final class vi {
    private static final ThreadLocal<SecureRandom> a = new vj();

    public static byte[] a(int i) {
        byte[] bArr = new byte[i];
        ((SecureRandom) a.get()).nextBytes(bArr);
        return bArr;
    }

    /* access modifiers changed from: private */
    public static SecureRandom b() {
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextLong();
        return secureRandom;
    }
}
