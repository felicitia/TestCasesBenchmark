package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.ud.a;
import java.security.GeneralSecurityException;

public final class qr {
    public static final ud a = ((ud) ((a) ud.b().a(ro.a)).a(qb.a("TinkAead", "Aead", "AesCtrHmacAeadKey", 0, true)).a(qb.a("TinkAead", "Aead", "AesEaxKey", 0, true)).a(qb.a("TinkAead", "Aead", "AesGcmKey", 0, true)).a(qb.a("TinkAead", "Aead", "ChaCha20Poly1305Key", 0, true)).a(qb.a("TinkAead", "Aead", "KmsAeadKey", 0, true)).a(qb.a("TinkAead", "Aead", "KmsEnvelopeAeadKey", 0, true)).a("TINK_AEAD_1_0_0").c());
    private static final ud b = ((ud) ((a) ud.b().a(a)).a("TINK_AEAD_1_1_0").c());

    static {
        try {
            a();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void a() throws GeneralSecurityException {
        qo.a("TinkAead", (qa<P>) new qq<P>());
        ro.a();
    }
}
