package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.zzaxi.zzb;
import java.security.GeneralSecurityException;
import javax.crypto.spec.SecretKeySpec;

final class rl implements qg<qk> {
    rl() {
    }

    private static void a(tg tgVar) throws GeneralSecurityException {
        if (tgVar.b() < 10) {
            throw new GeneralSecurityException("tag size too small");
        }
        switch (tgVar.a()) {
            case SHA1:
                if (tgVar.b() > 20) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA256:
                if (tgVar.b() > 32) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            case SHA512:
                if (tgVar.b() > 64) {
                    throw new GeneralSecurityException("tag size too big");
                }
                return;
            default:
                throw new GeneralSecurityException("unknown hash type");
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: d */
    public final qk a(zzbah zzbah) throws GeneralSecurityException {
        vg vgVar;
        try {
            tc a = tc.a(zzbah);
            if (!(a instanceof tc)) {
                throw new GeneralSecurityException("expected HmacKey proto");
            }
            tc tcVar = a;
            vn.a(tcVar.a(), 0);
            if (tcVar.c().size() < 16) {
                throw new GeneralSecurityException("key too short");
            }
            a(tcVar.b());
            zzaxa a2 = tcVar.b().a();
            SecretKeySpec secretKeySpec = new SecretKeySpec(tcVar.c().toByteArray(), "HMAC");
            int b = tcVar.b().b();
            switch (a2) {
                case SHA1:
                    vgVar = new vg("HMACSHA1", secretKeySpec, b);
                    break;
                case SHA256:
                    vgVar = new vg("HMACSHA256", secretKeySpec, b);
                    break;
                case SHA512:
                    vgVar = new vg("HMACSHA512", secretKeySpec, b);
                    break;
                default:
                    throw new GeneralSecurityException("unknown hash");
            }
            return vgVar;
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized HmacKey proto", e);
        }
    }

    public final int a() {
        return 0;
    }

    public final /* synthetic */ Object a(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof tc)) {
            throw new GeneralSecurityException("expected HmacKey proto");
        }
        tc tcVar = (tc) ykVar;
        vn.a(tcVar.a(), 0);
        if (tcVar.c().size() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        a(tcVar.b());
        zzaxa a = tcVar.b().a();
        SecretKeySpec secretKeySpec = new SecretKeySpec(tcVar.c().toByteArray(), "HMAC");
        int b = tcVar.b().b();
        switch (a) {
            case SHA1:
                return new vg("HMACSHA1", secretKeySpec, b);
            case SHA256:
                return new vg("HMACSHA256", secretKeySpec, b);
            case SHA512:
                return new vg("HMACSHA512", secretKeySpec, b);
            default:
                throw new GeneralSecurityException("unknown hash");
        }
    }

    public final yk b(yk ykVar) throws GeneralSecurityException {
        if (!(ykVar instanceof te)) {
            throw new GeneralSecurityException("expected HmacKeyFormat proto");
        }
        te teVar = (te) ykVar;
        if (teVar.b() < 16) {
            throw new GeneralSecurityException("key too short");
        }
        a(teVar.a());
        return tc.d().a(0).a(teVar.a()).a(zzbah.zzo(vi.a(teVar.b()))).c();
    }

    public final yk b(zzbah zzbah) throws GeneralSecurityException {
        try {
            return b((yk) te.a(zzbah));
        } catch (zzbbu e) {
            throw new GeneralSecurityException("expected serialized HmacKeyFormat proto", e);
        }
    }

    public final zzaxi c(zzbah zzbah) throws GeneralSecurityException {
        return (zzaxi) zzaxi.d().a("type.googleapis.com/google.crypto.tink.HmacKey").a(((tc) b(zzbah)).h()).a(zzb.SYMMETRIC).c();
    }
}
