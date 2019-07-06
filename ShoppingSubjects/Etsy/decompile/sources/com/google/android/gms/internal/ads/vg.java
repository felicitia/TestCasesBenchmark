package com.google.android.gms.internal.ads;

import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;

public final class vg implements qk {
    private Mac a;
    private final int b;
    private final String c;
    private final Key d;

    public vg(String str, Key key, int i) throws GeneralSecurityException {
        if (i < 10) {
            throw new InvalidAlgorithmParameterException("tag size too small, need at least 10 bytes");
        }
        char c2 = 65535;
        int hashCode = str.hashCode();
        if (hashCode != -1823053428) {
            if (hashCode != 392315118) {
                if (hashCode == 392317873 && str.equals("HMACSHA512")) {
                    c2 = 2;
                }
            } else if (str.equals("HMACSHA256")) {
                c2 = 1;
            }
        } else if (str.equals("HMACSHA1")) {
            c2 = 0;
        }
        switch (c2) {
            case 0:
                if (i > 20) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            case 1:
                if (i > 32) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            case 2:
                if (i > 64) {
                    throw new InvalidAlgorithmParameterException("tag size too big");
                }
                break;
            default:
                String str2 = "unknown Hmac algorithm: ";
                String valueOf = String.valueOf(str);
                throw new NoSuchAlgorithmException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        this.c = str;
        this.b = i;
        this.d = key;
        this.a = (Mac) uu.b.a(str);
        this.a.init(key);
    }

    public final byte[] a(byte[] bArr) throws GeneralSecurityException {
        Mac mac;
        try {
            mac = (Mac) this.a.clone();
        } catch (CloneNotSupportedException unused) {
            mac = (Mac) uu.b.a(this.c);
            mac.init(this.d);
        }
        mac.update(bArr);
        byte[] bArr2 = new byte[this.b];
        System.arraycopy(mac.doFinal(), 0, bArr2, 0, this.b);
        return bArr2;
    }
}
