package com.paypal.android.sdk.onetouch.core.encryption;

import android.util.Base64;
import java.io.ByteArrayInputStream;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class EncryptionUtils {
    private static final SecureRandom RANDOM = new SecureRandom();

    static {
        PRNGFixes.apply();
    }

    public static X509Certificate getX509CertificateFromBase64String(String str) throws CertificateException {
        return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(Base64.decode(str, 0)));
    }

    public static String byteArrayToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            byte b2 = b & 255;
            if (b2 < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(b2));
        }
        return sb.toString().toUpperCase();
    }

    static boolean isEqual(byte[] bArr, byte[] bArr2) {
        boolean z = false;
        if (bArr.length != bArr2.length) {
            return false;
        }
        byte b = 0;
        for (int i = 0; i < bArr.length; i++) {
            b |= bArr[i] ^ bArr2[i];
        }
        if (b == 0) {
            z = true;
        }
        return z;
    }
}
