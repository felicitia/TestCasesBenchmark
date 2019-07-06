package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class uh implements py {
    private final SecretKey a;

    public uh(byte[] bArr) {
        this.a = new SecretKeySpec(bArr, "AES");
    }

    public final byte[] a(byte[] bArr, byte[] bArr2) throws GeneralSecurityException {
        if (bArr.length > 2147483619) {
            throw new GeneralSecurityException("plaintext too long");
        }
        byte[] bArr3 = new byte[(bArr.length + 12 + 16)];
        byte[] a2 = vi.a(12);
        System.arraycopy(a2, 0, bArr3, 0, 12);
        Cipher cipher = (Cipher) uu.a.a("AES/GCM/NoPadding");
        cipher.init(1, this.a, new GCMParameterSpec(128, a2));
        if (bArr2 == null) {
            bArr2 = new byte[0];
        }
        cipher.updateAAD(bArr2);
        cipher.doFinal(bArr, 0, bArr.length, bArr3, 12);
        return bArr3;
    }
}
