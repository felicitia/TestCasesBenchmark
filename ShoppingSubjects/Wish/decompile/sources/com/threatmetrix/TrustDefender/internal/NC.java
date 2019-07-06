package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.security.KeyChain;
import android.security.keystore.KeyGenParameterSpec.Builder;
import android.security.keystore.KeyInfo;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;

class NC {

    /* renamed from: do reason: not valid java name */
    private static final String f457do = TL.m331if(NC.class);

    NC() {
    }

    @TargetApi(23)
    /* renamed from: new reason: not valid java name */
    static KeyPair m201new(String str, String str2, String str3) {
        if (!E.m159int() || !KeyChain.isKeyAlgorithmSupported(str)) {
            return null;
        }
        try {
            KeyPairGenerator instance = KeyPairGenerator.getInstance(str, str2);
            Builder digests = new Builder(str3, 5).setDigests(new String[]{"SHA-256"});
            if ("RSA".equals(str)) {
                digests = digests.setSignaturePaddings(new String[]{"PKCS1"});
            }
            instance.initialize(digests.build());
            return instance.generateKeyPair();
        } catch (InvalidAlgorithmParameterException e) {
            TL.m329for(f457do, "Can't create KeyPair {}", e.toString());
            return null;
        } catch (NoSuchProviderException e2) {
            TL.m329for(f457do, "Can't create KeyPair {}", e2.toString());
            return null;
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (Throwable th) {
            TL.m334if(f457do, "Can't create KeyPair (runtime exception) {}", th.toString());
            return null;
        }
    }

    @TargetApi(23)
    /* renamed from: if reason: not valid java name */
    static boolean m200if(PrivateKey privateKey, String str) {
        if (E.m159int() && privateKey != null && C0012I.f388for >= 23) {
            try {
                KeyInfo keyInfo = (KeyInfo) KeyFactory.getInstance(privateKey.getAlgorithm(), str).getKeySpec(privateKey, KeyInfo.class);
                keyInfo.isInsideSecureHardware();
                return keyInfo.isInsideSecureHardware();
            } catch (NoSuchAlgorithmException unused) {
            } catch (NoSuchProviderException e) {
                TL.m329for(f457do, "Can't create KeyPair {}", e.toString());
            } catch (InvalidKeySpecException e2) {
                TL.m329for(f457do, "Can't create KeyPair {}", e2.toString());
            } catch (Throwable th) {
                TL.m334if(f457do, "Can't create KeyPair (runtime exception) {}", th.toString());
            }
        }
        return false;
    }
}
