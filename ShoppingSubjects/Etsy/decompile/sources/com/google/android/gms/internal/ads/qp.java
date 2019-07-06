package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.tp.b;
import com.google.android.gms.internal.ads.tr.a;
import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;

final class qp {
    private static final Charset a = Charset.forName("UTF-8");

    public static tr a(tp tpVar) {
        a a2 = tr.a().a(tpVar.a());
        for (b bVar : tpVar.b()) {
            a2.a((tr.b) tr.b.a().a(bVar.b().a()).a(bVar.c()).a(bVar.e()).a(bVar.d()).c());
        }
        return (tr) a2.c();
    }

    public static void b(tp tpVar) throws GeneralSecurityException {
        if (tpVar.c() == 0) {
            throw new GeneralSecurityException("empty keyset");
        }
        int a2 = tpVar.a();
        boolean z = true;
        boolean z2 = false;
        for (b bVar : tpVar.b()) {
            if (!bVar.a()) {
                throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(bVar.d())}));
            } else if (bVar.e() == zzayd.UNKNOWN_PREFIX) {
                throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(bVar.d())}));
            } else if (bVar.c() == zzaxl.UNKNOWN_STATUS) {
                throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(bVar.d())}));
            } else {
                if (bVar.c() == zzaxl.ENABLED && bVar.d() == a2) {
                    if (z2) {
                        throw new GeneralSecurityException("keyset contains multiple primary keys");
                    }
                    z2 = true;
                }
                if (bVar.b().c() != zzb.ASYMMETRIC_PUBLIC) {
                    z = false;
                }
            }
        }
        if (!z2 && !z) {
            throw new GeneralSecurityException("keyset doesn't contain a valid primary key");
        }
    }
}
