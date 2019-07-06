package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class qw implements qg<py> {
    qw() {
    }

    private static sk b() throws GeneralSecurityException {
        return (sk) sk.c().a(0).a(zzbah.zzo(vi.a(32))).c();
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final py a(zzbah zzbah) throws GeneralSecurityException {
        try {
            sk a = sk.a(zzbah);
            if (!(a instanceof sk)) {
                throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
            }
            sk skVar = a;
            vn.a(skVar.a(), 0);
            if (skVar.b().size() == 32) {
                return new uk(skVar.b().toByteArray());
            }
            throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("invalid ChaCha20Poly1305 key", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof sk)) {
            throw new GeneralSecurityException("expected ChaCha20Poly1305Key proto");
        }
        sk skVar = (sk) ykVar;
        vn.a(skVar.a(), 0);
        if (skVar.b().size() == 32) {
            return new uk(skVar.b().toByteArray());
        }
        throw new GeneralSecurityException("invalid ChaCha20Poly1305Key: incorrect key length");
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        return b();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        return b();
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.ChaCha20Poly1305Key").a(b().h()).a(zzb.SYMMETRIC).c();
    }
}
