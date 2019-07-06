package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.sg.a;
import java.security.GeneralSecurityException;
import java.util.Arrays;

final class rk implements ul {
    private final String a;
    private final int b;
    private sg c;
    private rp d;
    private int e;

    rk(tl tlVar) throws GeneralSecurityException {
        this.a = tlVar.a();
        if (this.a.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            try {
                si a2 = si.a(tlVar.b());
                this.c = (sg) qo.b(tlVar);
                this.b = a2.a();
            } catch (zzbbu e2) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e2);
            }
        } else if (this.a.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            try {
                rr a3 = rr.a(tlVar.b());
                this.d = (rp) qo.b(tlVar);
                this.e = a3.a().b();
                this.b = this.e + a3.b().b();
            } catch (zzbbu e3) {
                throw new GeneralSecurityException("invalid KeyFormat protobuf, expected AesGcmKeyFormat", e3);
            }
        } else {
            String str = "unsupported AEAD DEM key type: ";
            String valueOf = String.valueOf(this.a);
            throw new GeneralSecurityException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
    }

    public final int a() {
        return this.b;
    }

    public final py a(byte[] bArr) throws GeneralSecurityException {
        yk ykVar;
        if (bArr.length != this.b) {
            throw new GeneralSecurityException("Symmetric key has incorrect length");
        }
        if (this.a.equals("type.googleapis.com/google.crypto.tink.AesGcmKey")) {
            ykVar = (sg) ((a) sg.c().a(this.c)).a(zzbah.zzc(bArr, 0, this.b)).c();
        } else if (this.a.equals("type.googleapis.com/google.crypto.tink.AesCtrHmacAeadKey")) {
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 0, this.e);
            tc tcVar = (tc) ((tc.a) tc.d().a(this.d.c())).a(zzbah.zzo(Arrays.copyOfRange(bArr, this.e, this.b))).c();
            ykVar = (rp) rp.d().a(this.d.a()).a((rt) ((rt.a) rt.d().a(this.d.b())).a(zzbah.zzo(copyOfRange)).c()).a(tcVar).c();
        } else {
            throw new GeneralSecurityException("unknown DEM key type");
        }
        return (py) qo.b(this.a, ykVar);
    }
}
