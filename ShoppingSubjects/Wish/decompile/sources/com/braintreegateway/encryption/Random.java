package com.braintreegateway.encryption;

import java.security.SecureRandom;

public final class Random {
    public static byte[] secureRandomBytes(int i) {
        PRNGFixes.apply();
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }
}
