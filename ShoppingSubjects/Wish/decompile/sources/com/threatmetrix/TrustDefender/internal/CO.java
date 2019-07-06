package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.security.KeyChain;
import android.security.keystore.KeyGenParameterSpec.Builder;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Calendar;
import javax.security.auth.x500.X500Principal;

final class CO {

    /* renamed from: do reason: not valid java name */
    private static final String f80do = TL.m331if(NC.class);

    CO() {
    }

    @TargetApi(23)
    /* renamed from: do reason: not valid java name */
    static KeyPair m32do(String str, String str2, String str3, String str4, BigInteger bigInteger, Calendar calendar, Calendar calendar2, boolean z, byte[] bArr) {
        if (!E.m159int() || !KeyChain.isKeyAlgorithmSupported(str)) {
            return null;
        }
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(str, str2);
            Builder certificateNotAfter = new Builder(str3, 4).setDigests(new String[]{"SHA-256"}).setCertificateSerialNumber(bigInteger).setCertificateSubject(new X500Principal("CN=".concat(String.valueOf(str4)))).setCertificateNotBefore(calendar.getTime()).setCertificateNotAfter(calendar2.getTime());
            if (C0012I.f388for >= 24) {
                certificateNotAfter.setAttestationChallenge(bArr);
            }
            if ("RSA".equals(str)) {
                certificateNotAfter.setSignaturePaddings(new String[]{"PKCS1"});
            }
            certificateNotAfter.setUserAuthenticationRequired(true);
            certificateNotAfter.setUserAuthenticationValidityDurationSeconds(60);
            instance.initialize(certificateNotAfter.build());
            KeyPair generateKeyPair = instance.generateKeyPair();
            if (generateKeyPair == null || !z || NC.m200if(generateKeyPair.getPrivate(), str2)) {
                return generateKeyPair;
            }
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            TL.m329for(f80do, "Can't create KeyPair {}", e.toString());
            return null;
        } catch (NoSuchProviderException e2) {
            TL.m329for(f80do, "Can't create KeyPair {}", e2.toString());
            return null;
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (Throwable th) {
            TL.m334if(f80do, "Can't create KeyPair {}", th.toString());
            return null;
        }
    }
}
