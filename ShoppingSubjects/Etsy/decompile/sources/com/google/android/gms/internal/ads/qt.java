package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class qt implements qg<vf> {
    qt() {
    }

    private static void a(rx rxVar) throws GeneralSecurityException {
        if (rxVar.a() < 12 || rxVar.a() > 16) {
            throw new GeneralSecurityException("invalid IV size");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final uf a(zzbah zzbah) throws GeneralSecurityException {
        try {
            rt a = rt.a(zzbah);
            if (!(a instanceof rt)) {
                throw new GeneralSecurityException("expected AesCtrKey proto");
            }
            rt rtVar = a;
            vn.a(rtVar.a(), 0);
            vn.a(rtVar.c().size());
            a(rtVar.b());
            return new uf(rtVar.c().toByteArray(), rtVar.b().a());
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof rt)) {
            throw new GeneralSecurityException("expected AesCtrKey proto");
        }
        rt rtVar = (rt) ykVar;
        vn.a(rtVar.a(), 0);
        vn.a(rtVar.c().size());
        a(rtVar.b());
        return new uf(rtVar.c().toByteArray(), rtVar.b().a());
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof rv)) {
            throw new GeneralSecurityException("expected AesCtrKeyFormat proto");
        }
        rv rvVar = (rv) ykVar;
        vn.a(rvVar.b());
        a(rvVar.a());
        return rt.d().a(rvVar.a()).a(zzbah.zzo(vi.a(rvVar.b()))).a(0).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) rv.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.AesCtrKey").a(((rt) b(zzbah)).h()).a(zzb.SYMMETRIC).c();
    }
}
