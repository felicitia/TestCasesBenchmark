package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;

final class rc implements qg<qf> {
    rc() {
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final qf a(zzbah zzbah) throws GeneralSecurityException {
        try {
            sv a = sv.a(zzbah);
            if (!(a instanceof sv)) {
                throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
            }
            sv svVar = a;
            vn.a(svVar.a(), 0);
            ri.a(svVar.b());
            sr b = svVar.b();
            sx a2 = b.a();
            un unVar = new un(ur.a(ri.a(a2.a()), svVar.c().toByteArray(), svVar.d().toByteArray()), a2.c().toByteArray(), ri.a(a2.b()), ri.a(b.c()), new rk(b.b().a()));
            return unVar;
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPublicKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof sv)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfPublicKey proto");
        }
        sv svVar = (sv) ykVar;
        vn.a(svVar.a(), 0);
        ri.a(svVar.b());
        sr b = svVar.b();
        sx a = b.a();
        un unVar = new un(ur.a(ri.a(a.a()), svVar.c().toByteArray(), svVar.d().toByteArray()), a.c().toByteArray(), ri.a(a.b()), ri.a(b.c()), new rk(b.b().a()));
        return unVar;
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        throw new GeneralSecurityException("Not implemented.");
    }
}
