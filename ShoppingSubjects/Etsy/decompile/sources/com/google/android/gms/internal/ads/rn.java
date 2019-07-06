package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class rn implements qa<qk> {
    public final qg<qk> a(String str, String str2, int i) throws GeneralSecurityException {
        String lowerCase = str2.toLowerCase();
        char c = 65535;
        if ((lowerCase.hashCode() == 107855 && lowerCase.equals("mac")) ? false : true) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        if (str.hashCode() == 836622442 && str.equals("type.googleapis.com/google.crypto.tink.HmacKey")) {
            c = 0;
        }
        if (c != 0) {
            throw new GeneralSecurityException(String.format("No support for primitive 'Mac' with key type '%s'.", new Object[]{str}));
        }
        rl rlVar = new rl();
        if (i <= 0) {
            return rlVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
