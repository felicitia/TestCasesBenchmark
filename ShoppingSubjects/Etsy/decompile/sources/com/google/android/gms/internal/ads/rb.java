package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECPoint;
import java.security.spec.ECPrivateKeySpec;

final class rb implements qg<qe> {
    rb() {
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final qe a(zzbah zzbah) throws GeneralSecurityException {
        try {
            st a = st.a(zzbah);
            if (!(a instanceof st)) {
                throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
            }
            st stVar = a;
            vn.a(stVar.a(), 0);
            ri.a(stVar.b().b());
            sr b = stVar.b().b();
            sx a2 = b.a();
            zzayv a3 = ri.a(a2.a());
            byte[] byteArray = stVar.c().toByteArray();
            um umVar = new um((ECPrivateKey) ((KeyFactory) uu.e.a("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, byteArray), ur.a(a3))), a2.c().toByteArray(), ri.a(a2.b()), ri.a(b.c()), new rk(b.b().a()));
            return umVar;
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized EciesAeadHkdfPrivateKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof st)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfPrivateKey proto");
        }
        st stVar = (st) ykVar;
        vn.a(stVar.a(), 0);
        ri.a(stVar.b().b());
        sr b = stVar.b().b();
        sx a = b.a();
        zzayv a2 = ri.a(a.a());
        byte[] byteArray = stVar.c().toByteArray();
        um umVar = new um((ECPrivateKey) ((KeyFactory) uu.e.a("EC")).generatePrivate(new ECPrivateKeySpec(new BigInteger(1, byteArray), ur.a(a2))), a.c().toByteArray(), ri.a(a.b()), ri.a(b.c()), new rk(b.b().a()));
        return umVar;
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof sp)) {
            throw new GeneralSecurityException("expected EciesAeadHkdfKeyFormat proto");
        }
        sp spVar = (sp) ykVar;
        ri.a(spVar.a());
        KeyPair a = ur.a(ur.a(ri.a(spVar.a().a().a())));
        ECPublicKey eCPublicKey = (ECPublicKey) a.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) a.getPrivate();
        ECPoint w = eCPublicKey.getW();
        return st.d().a(0).a((sv) sv.e().a(0).a(spVar.a()).a(zzbah.zzo(w.getAffineX().toByteArray())).b(zzbah.zzo(w.getAffineY().toByteArray())).c()).a(zzbah.zzo(eCPrivateKey.getS().toByteArray())).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) sp.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("invalid EciesAeadHkdf key format", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.EciesAeadHkdfPrivateKey").a(((st) b(zzbah)).h()).a(zzb.ASYMMETRIC_PRIVATE).c();
    }
}
