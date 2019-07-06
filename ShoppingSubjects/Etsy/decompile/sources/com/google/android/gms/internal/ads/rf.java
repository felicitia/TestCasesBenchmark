package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class rf implements qa<qf> {
    public final qg<qf> a(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        char c = 65535;
        if ((lowerCase.hashCode() == 1420614889 && lowerCase.equals("hybridencrypt")) ? false : true) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        if (str.hashCode() == 396454335 && str.equals("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPublicKey")) {
            c = 0;
        }
        if (c != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'HybridEncrypt' with key type '%s'.", new Object[]{str}));
        }
        rc rcVar = new rc();
        if (i <= 0) {
            return rcVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
