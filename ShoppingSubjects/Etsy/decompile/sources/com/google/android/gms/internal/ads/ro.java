package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.ud.a;
import java.security.GeneralSecurityException;

public final class ro {
    public static final ud a = ((ud) ud.b().a("TINK_MAC_1_0_0").a(qb.a("TinkMac", "Mac", "HmacKey", 0, true)).c());
    private static final ud b = ((ud) ((a) ud.b().a(a)).a("TINK_MAC_1_1_0").c());

    static {
        try {
            a();
        } catch (GeneralSecurityException e) {
            throw new ExceptionInInitializerError(e);
        }
    }

    public static void a() throws GeneralSecurityException {
        qo.a("TinkMac", (qa<P>) new rn<P>());
    }
}
