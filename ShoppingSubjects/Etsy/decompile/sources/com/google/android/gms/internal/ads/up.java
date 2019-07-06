package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class up {
    private ECPublicKey a;

    public up(ECPublicKey eCPublicKey) {
        this.a = eCPublicKey;
    }

    public final uq a(String str, byte[] bArr, byte[] bArr2, int i, zzayw zzayw) throws GeneralSecurityException {
        byte[] bArr3;
        KeyPair a2 = ur.a(this.a.getParams());
        ECPublicKey eCPublicKey = (ECPublicKey) a2.getPublic();
        ECPrivateKey eCPrivateKey = (ECPrivateKey) a2.getPrivate();
        ECPublicKey eCPublicKey2 = this.a;
        ECParameterSpec params = eCPublicKey2.getParams();
        ECParameterSpec params2 = eCPrivateKey.getParams();
        if (!params.getCurve().equals(params2.getCurve()) || !params.getGenerator().equals(params2.getGenerator()) || !params.getOrder().equals(params2.getOrder()) || params.getCofactor() != params2.getCofactor()) {
            throw new GeneralSecurityException("invalid public key spec");
        }
        byte[] a3 = ur.a(eCPrivateKey, eCPublicKey2.getW());
        EllipticCurve curve = eCPublicKey.getParams().getCurve();
        ECPoint w = eCPublicKey.getW();
        ur.a(w, curve);
        int a4 = ur.a(curve);
        int i2 = 1;
        switch (us.a[zzayw.ordinal()]) {
            case 1:
                int i3 = (2 * a4) + 1;
                byte[] bArr4 = new byte[i3];
                byte[] byteArray = w.getAffineX().toByteArray();
                byte[] byteArray2 = w.getAffineY().toByteArray();
                System.arraycopy(byteArray2, 0, bArr4, i3 - byteArray2.length, byteArray2.length);
                System.arraycopy(byteArray, 0, bArr4, (a4 + 1) - byteArray.length, byteArray.length);
                bArr4[0] = 4;
                bArr3 = bArr4;
                break;
            case 2:
                int i4 = a4 + 1;
                bArr3 = new byte[i4];
                byte[] byteArray3 = w.getAffineX().toByteArray();
                System.arraycopy(byteArray3, 0, bArr3, i4 - byteArray3.length, byteArray3.length);
                bArr3[0] = (byte) (w.getAffineY().testBit(0) ? 3 : 2);
                break;
            default:
                String valueOf = String.valueOf(zzayw);
                StringBuilder sb = new StringBuilder(15 + String.valueOf(valueOf).length());
                sb.append("invalid format:");
                sb.append(valueOf);
                throw new GeneralSecurityException(sb.toString());
        }
        byte[] a5 = ui.a(bArr3, a3);
        Mac mac = (Mac) uu.b.a(str);
        if (i > 255 * mac.getMacLength()) {
            throw new GeneralSecurityException("size too large");
        }
        if (bArr == null || bArr.length == 0) {
            mac.init(new SecretKeySpec(new byte[mac.getMacLength()], str));
        } else {
            mac.init(new SecretKeySpec(bArr, str));
        }
        byte[] doFinal = mac.doFinal(a5);
        byte[] bArr5 = new byte[i];
        mac.init(new SecretKeySpec(doFinal, str));
        byte[] bArr6 = new byte[0];
        int i5 = 0;
        while (true) {
            mac.update(bArr6);
            mac.update(bArr2);
            mac.update((byte) i2);
            bArr6 = mac.doFinal();
            if (bArr6.length + i5 < i) {
                System.arraycopy(bArr6, 0, bArr5, i5, bArr6.length);
                i5 += bArr6.length;
                i2++;
            } else {
                System.arraycopy(bArr6, 0, bArr5, i5, i - i5);
                return new uq(bArr3, bArr5);
            }
        }
    }
}
