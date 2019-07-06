package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;
import java.util.logging.Logger;

class qs implements qg<py> {
    private static final Logger a = Logger.getLogger(qs.class.getName());

    qs() throws GeneralSecurityException {
        qo.a("type.googleapis.com/google.crypto.tink.AesCtrKey", (qg<P>) new qt<P>());
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final py a(zzbah zzbah) throws GeneralSecurityException {
        try {
            rp a2 = rp.a(zzbah);
            if (!(a2 instanceof rp)) {
                throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
            }
            rp rpVar = a2;
            vn.a(rpVar.a(), 0);
            return new ut((vf) qo.b("type.googleapis.com/google.crypto.tink.AesCtrKey", rpVar.b()), (qk) qo.b("type.googleapis.com/google.crypto.tink.HmacKey", rpVar.c()), rpVar.c().b().b());
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof rp)) {
            throw new GeneralSecurityException("expected AesCtrHmacAeadKey proto");
        }
        rp rpVar = (rp) ykVar;
        vn.a(rpVar.a(), 0);
        return new ut((vf) qo.b("type.googleapis.com/google.crypto.tink.AesCtrKey", rpVar.b()), (qk) qo.b("type.googleapis.com/google.crypto.tink.HmacKey", rpVar.c()), rpVar.c().b().b());
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof rr)) {
            throw new GeneralSecurityException("expected AesCtrHmacAeadKeyFormat proto");
        }
        rr rrVar = (rr) ykVar;
        rt rtVar = (rt) qo.a("type.googleapis.com/google.crypto.tink.AesCtrKey", (yk) rrVar.a());
        return rp.d().a(rtVar).a((tc) qo.a("type.googleapis.com/google.crypto.tink.HmacKey", (yk) rrVar.b())).a(0).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) rr.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesCtrHmacAeadKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey").a(((rp) b(zzbah)).h()).a(zzb.SYMMETRIC).c();
    }
}
