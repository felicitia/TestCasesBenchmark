package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class uf implements vf {
    private final SecretKeySpec a;
    private final int b;
    private final int c = ((Cipher) uu.a.a("AES/CTR/NoPadding")).getBlockSize();

    public uf(byte[] bArr, int i) throws GeneralSecurityException {
        this.a = new SecretKeySpec(bArr, "AES");
        if (i < 12 || i > this.c) {
            throw new GeneralSecurityException("invalid IV size");
        }
        this.b = i;
    }

    public final byte[] a(byte[] bArr) throws GeneralSecurityException {
        if (bArr.length > Integer.MAX_VALUE - this.b) {
            int i = Integer.MAX_VALUE - this.b;
            StringBuilder sb = new StringBuilder(43);
            sb.append("plaintext length can not exceed ");
            sb.append(i);
            throw new GeneralSecurityException(sb.toString());
        }
        byte[] bArr2 = new byte[(this.b + bArr.length)];
        byte[] a2 = vi.a(this.b);
        System.arraycopy(a2, 0, bArr2, 0, this.b);
        int length = bArr.length;
        int i2 = this.b;
        Cipher cipher = (Cipher) uu.a.a("AES/CTR/NoPadding");
        byte[] bArr3 = new byte[this.c];
        System.arraycopy(a2, 0, bArr3, 0, this.b);
        cipher.init(1, this.a, new IvParameterSpec(bArr3));
        if (cipher.doFinal(bArr, 0, length, bArr2, i2) == length) {
            return bArr2;
        }
        throw new GeneralSecurityException("stored output's length does not match input's length");
    }
}
