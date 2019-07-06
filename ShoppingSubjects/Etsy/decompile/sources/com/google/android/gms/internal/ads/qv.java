package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class qv implements qg<py> {
    qv() {
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final py a(zzbah zzbah) throws GeneralSecurityException {
        try {
            sg a = sg.a(zzbah);
            if (!(a instanceof sg)) {
                throw new GeneralSecurityException("expected AesGcmKey proto");
            }
            sg sgVar = a;
            vn.a(sgVar.a(), 0);
            vn.a(sgVar.b().size());
            return new uh(sgVar.b().toByteArray());
        } catch (zzbbu unused) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof sg)) {
            throw new GeneralSecurityException("expected AesGcmKey proto");
        }
        sg sgVar = (sg) ykVar;
        vn.a(sgVar.a(), 0);
        vn.a(sgVar.b().size());
        return new uh(sgVar.b().toByteArray());
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof si)) {
            throw new GeneralSecurityException("expected AesGcmKeyFormat proto");
        }
        si siVar = (si) ykVar;
        vn.a(siVar.a());
        return sg.c().a(zzbah.zzo(vi.a(siVar.a()))).a(0).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) si.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized AesGcmKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.AesGcmKey").a(((sg) b(zzbah)).h()).a(zzb.SYMMETRIC).c();
    }
}
