package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.tp.b;
import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

public final class ql {
    @Deprecated
    public static final qh a(byte[] bArr) throws GeneralSecurityException {
        try {
            tp a = tp.a(bArr);
            for (b bVar : a.b()) {
                if (bVar.b().c() == zzb.UNKNOWN_KEYMATERIAL || bVar.b().c() == zzb.SYMMETRIC) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                } else if (bVar.b().c() == zzb.ASYMMETRIC_PRIVATE) {
                    throw new GeneralSecurityException("keyset contains secret key material");
                }
            }
            return qh.a(a);
        } catch (zzbbu unused) {
            throw new GeneralSecurityException("invalid keyset");
        }
    }
}
