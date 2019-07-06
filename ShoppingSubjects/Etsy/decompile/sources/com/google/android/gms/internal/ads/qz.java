package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;

final class qz implements qg<py> {
    qz() {
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final py a(zzbah zzbah) throws GeneralSecurityException {
        try {
            tx a = tx.a(zzbah);
            if (!(a instanceof tx)) {
                throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
            }
            tx txVar = a;
            vn.a(txVar.a(), 0);
            String a2 = txVar.b().a();
            return new qy(txVar.b().b(), qj.a(a2).b(a2));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmSEnvelopeAeadKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof tx)) {
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKey proto");
        }
        tx txVar = (tx) ykVar;
        vn.a(txVar.a(), 0);
        String a = txVar.b().a();
        return new qy(txVar.b().b(), qj.a(a).b(a));
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof ua)) {
            throw new GeneralSecurityException("expected KmsEnvelopeAeadKeyFormat proto");
        }
        return tx.c().a((ua) ykVar).a(0).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) ua.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized KmsEnvelopeAeadKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.KmsEnvelopeAeadKey").a(((tx) b(zzbah)).h()).a(zzb.REMOTE).c();
    }
}
