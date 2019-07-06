package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.ud.a;
import java.security.GeneralSecurityException;

public final class rd {
    public static final ud a = ((ud) ((a) ud.b().a(qr.a)).a(qb.a("TinkHybridDecrypt", "HybridDecrypt", "EciesAeadHkdfPrivateKey", 0, true)).a(qb.a("TinkHybridEncrypt", "HybridEncrypt", "EciesAeadHkdfPublicKey", 0, true)).a("TINK_HYBRID_1_0_0").c());
    private static final ud b = ((ud) ((a) ud.b().a(a)).a("TINK_HYBRID_1_1_0").c());

    static {
        try {
            qo.a("TinkHybridEncrypt", (qa<P>) new rf<P>());
            qo.a("TinkHybridDecrypt", (qa<P>) new re<P>());
            qr.a();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
