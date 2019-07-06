package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class qx implements qg<py> {
    qx() {
    }

    private static py c(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof tt)) {
            throw new GeneralSecurityException("expected KmsAeadKey proto");
        }
        tt ttVar = (tt) ykVar;
        vn.a(ttVar.a(), 0);
        String a = ttVar.b().a();
        return qj.a(a).b(a);
    }

    private static py d(zzbah zzbah) throws GeneralSecurityException {
        try {
            return c((yk) tt.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected KmsAeadKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        return c(ykVar);
    }

    public final /* synthetic */ Object a(zzbah zzbah) throws GeneralSecurityException {
        return d(zzbah);
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof tv)) {
            throw new GeneralSecurityException("expected KmsAeadKeyFormat proto");
        }
        return tt.c().a((tv) ykVar).a(0).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) tv.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmsAeadKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.KmsAeadKey").a(((tt) b(zzbah)).h()).a(zzb.REMOTE).c();
    }
}
