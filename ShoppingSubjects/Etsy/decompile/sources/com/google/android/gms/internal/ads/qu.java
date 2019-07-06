package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class qu implements qg<py> {
    qu() {
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final py a(zzbah zzbah) throws GeneralSecurityException {
        try {
            rz a = rz.a(zzbah);
            if (!(a instanceof rz)) {
                throw new GeneralSecurityException("expected AesEaxKey proto");
            }
            rz rzVar = a;
            vn.a(rzVar.a(), 0);
            vn.a(rzVar.c().size());
            if (rzVar.b().a() == 12 || rzVar.b().a() == 16) {
                return new ug(rzVar.c().toByteArray(), rzVar.b().a());
            }
            throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesEaxKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof rz)) {
            throw new GeneralSecurityException("expected AesEaxKey proto");
        }
        rz rzVar = (rz) ykVar;
        vn.a(rzVar.a(), 0);
        vn.a(rzVar.c().size());
        if (rzVar.b().a() == 12 || rzVar.b().a() == 16) {
            return new ug(rzVar.c().toByteArray(), rzVar.b().a());
        }
        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof sc)) {
            throw new GeneralSecurityException("expected AesEaxKeyFormat proto");
        }
        sc scVar = (sc) ykVar;
        vn.a(scVar.b());
        if (scVar.a().a() == 12 || scVar.a().a() == 16) {
            return rz.d().a(zzbah.zzo(vi.a(scVar.b()))).a(scVar.a()).a(0).c();
        }
        throw new GeneralSecurityException("invalid IV size; acceptable values have 12 or 16 bytes");
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) sc.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesEaxKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.AesEaxKey").a(((rz) b(zzbah)).h()).a(zzb.SYMMETRIC).c();
    }
}
