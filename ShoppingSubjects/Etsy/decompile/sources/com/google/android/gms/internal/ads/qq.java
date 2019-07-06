package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class qq implements qa<py> {
    public final qg<py> a(String str, String str2, int i) throws GeneralSecurityException {
        qg<py> qgVar;
        String lowerCase = str2.toLowerCase();
        char c = 65535;
        if ((lowerCase.hashCode() == 2989895 && lowerCase.equals("aead")) ? false : true) {
            throw new GeneralSecurityException(String.format("No support for primitive '%s'.", new Object[]{str2}));
        }
        switch (str.hashCode()) {
            case 360753376:
                if (str.equals("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key")) {
                    c = 3;
                    break;
                }
                break;
            case 1215885937:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
                    c = 0;
                    break;
                }
                break;
            case 1469984853:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsAeadKey")) {
                    c = 4;
                    break;
                }
                break;
            case 1797113348:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesEaxKey")) {
                    c = 1;
                    break;
                }
                break;
            case 1855890991:
                if (str.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
                    c = 2;
                    break;
                }
                break;
            case 2079211877:
                if (str.equals("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey")) {
                    c = 5;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                qgVar = new qs<>();
                break;
            case 1:
                qgVar = new qu<>();
                break;
            case 2:
                qgVar = new qv<>();
                break;
            case 3:
                qgVar = new qw<>();
                break;
            case 4:
                qgVar = new qx<>();
                break;
            case 5:
                qgVar = new qz<>();
                break;
            default:
                throw new GeneralSecurityException(String.format("No support for primitive 'Aead' with key type '%s'.", new Object[]{str}));
        }
        if (qgVar.a() >= i) {
            return qgVar;
        }
        throw new GeneralSecurityException(String.format("No key manager for key type '%s' with version at least %d.", new Object[]{str, Integer.valueOf(i)}));
    }
}
