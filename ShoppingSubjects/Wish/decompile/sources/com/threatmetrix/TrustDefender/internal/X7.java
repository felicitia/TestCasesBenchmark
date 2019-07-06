package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.security.KeyChain;
import android.security.KeyPairGeneratorSpec.Builder;
import com.threatmetrix.TrustDefender.StrongAuth.AuthenticationStatus;
import com.threatmetrix.TrustDefender.StrongAuth.StrongAuthCallback;
import com.threatmetrix.TrustDefender.StrongAuth.StrongAuthPromptCallback;
import com.threatmetrix.TrustDefender.THMStatusCode;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import javax.security.auth.x500.X500Principal;

public class X7 {

    /* renamed from: new reason: not valid java name */
    private static final String f608new = TL.m331if(X7.class);

    public enum E {
        THM_UNKNOWN_METHOD("unknownmethod"),
        THM_USER_PRESENCE("tmxuserpresence"),
        THM_DEVICE_PRESENCE("tmxdevicepresence");
        

        /* renamed from: for reason: not valid java name */
        final String f615for;

        private E(String str) {
            this.f615for = str;
        }

        /* renamed from: int reason: not valid java name */
        public static E m393int(String str) {
            E[] values;
            for (E e : values()) {
                if (str.equals(e.f615for)) {
                    return e;
                }
            }
            return THM_UNKNOWN_METHOD;
        }
    }

    interface I {
        /* renamed from: do reason: not valid java name */
        byte[] m394do();

        /* renamed from: do reason: not valid java name */
        byte[] m395do(byte[] bArr);

        /* renamed from: for reason: not valid java name */
        byte[] m396for();

        /* renamed from: new reason: not valid java name */
        BigInteger m397new();
    }

    public static class L {

        /* renamed from: int reason: not valid java name */
        public final String f616int;

        /* renamed from: new reason: not valid java name */
        public final W f617new;

        L(W w, String str) {
            this.f617new = w;
            this.f616int = str;
        }
    }

    static class O implements I {

        /* renamed from: do reason: not valid java name */
        final byte[] f618do;

        /* renamed from: if reason: not valid java name */
        final BigInteger f619if;

        /* renamed from: new reason: not valid java name */
        final PrivateKey f620new;

        O(PrivateKey privateKey, BigInteger bigInteger, byte[] bArr) {
            this.f620new = privateKey;
            this.f619if = bigInteger;
            this.f618do = bArr;
        }

        /* renamed from: do reason: not valid java name */
        public final byte[] m399do(byte[] bArr) {
            return X7.m388new(this.f620new, bArr);
        }

        /* renamed from: for reason: not valid java name */
        public final byte[] m400for() {
            return X7.m386int(this.f620new);
        }

        /* renamed from: new reason: not valid java name */
        public final BigInteger m401new() {
            return this.f619if;
        }

        /* renamed from: do reason: not valid java name */
        public final byte[] m398do() {
            return this.f618do;
        }
    }

    public enum W {
        MISSING_PARAMETER("MISSING_PARAMETER", THMStatusCode.THM_Internal_Error),
        NOT_SUPPORTED("NOT_SUPPORTED", THMStatusCode.THM_StrongAuth_Unsupported),
        MISSING_FUNCTION("MISSING_FUNCTION", THMStatusCode.THM_Internal_Error),
        REGISTRATION_FAILED("REGISTRATION_FAILED", THMStatusCode.THM_StrongAuth_Failed),
        REGISTRATION_CANCELLED("REGISTRATION_CANCELLED", THMStatusCode.THM_StrongAuth_Cancelled),
        CONTEXT_NOT_FOUND("CONTEXT_NOT_FOUND", THMStatusCode.THM_StrongAuth_Failed),
        STEPUP_FAILED("STEPUP_FAILED", THMStatusCode.THM_StrongAuth_Failed),
        STEPUP_CANCELLED("STEPUP_CANCELLED", THMStatusCode.THM_StrongAuth_Cancelled),
        REGISTERED("REGISTERED", THMStatusCode.THM_OK),
        STEPUP_COMPLETE("STEPUP_COMPLETE", THMStatusCode.THM_OK);
        

        /* renamed from: long reason: not valid java name */
        public final THMStatusCode f632long;

        /* renamed from: this reason: not valid java name */
        public final String f633this;

        private W(String str, THMStatusCode tHMStatusCode) {
            this.f633this = str;
            this.f632long = tHMStatusCode;
        }
    }

    X7() {
    }

    /* renamed from: int reason: not valid java name */
    static /* synthetic */ byte[] m386int(PrivateKey privateKey) {
        return "EC".equalsIgnoreCase(privateKey.getAlgorithm()) ? B.f58new : B.f56if;
    }

    @TargetApi(18)
    /* renamed from: int reason: not valid java name */
    public static L m383int(com.threatmetrix.TrustDefender.internal.P.O o, E e, String str, String str2, String str3, byte[] bArr, StrongAuthCallback strongAuthCallback) {
        AuthenticationStatus authenticationStatus;
        byte[] bArr2;
        com.threatmetrix.TrustDefender.internal.P.O o2 = o;
        StrongAuthCallback strongAuthCallback2 = strongAuthCallback;
        if (e != E.THM_USER_PRESENCE) {
            return new L(W.MISSING_FUNCTION, null);
        }
        if (NK.m215if(str3)) {
            return new L(W.MISSING_PARAMETER, null);
        }
        String str4 = NK.m216int(str3);
        String concat = "TrustDefenderSDKStrongAuth".concat(str3);
        Context context = o2.f487for;
        if (strongAuthCallback2 == null || C0012I.f388for < 21) {
            authenticationStatus = AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE;
        } else {
            authenticationStatus = P3.m274for(context, str, str2, strongAuthCallback2);
        }
        if (authenticationStatus == AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE) {
            TL.m338new(f608new, "Register Failure: Not supported, authentication only possible for API 21+ ");
            return new L(W.NOT_SUPPORTED, null);
        } else if (authenticationStatus == AuthenticationStatus.THM_STRONG_AUTH_CANCELLED) {
            TL.m338new(f608new, "Register Failure: User cancelled authentication");
            return new L(W.REGISTRATION_CANCELLED, null);
        } else if (authenticationStatus != AuthenticationStatus.THM_STRONG_AUTH_OK) {
            TL.m338new(f608new, "Register Failure: User didn't proceed with authentication");
            return new L(W.REGISTRATION_FAILED, null);
        } else {
            try {
                KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                instance.load(null);
                try {
                    instance.deleteEntry(concat);
                } catch (Exception unused) {
                }
                BigInteger bigInteger = new BigInteger(32, new Random());
                Calendar instance2 = Calendar.getInstance();
                Calendar instance3 = Calendar.getInstance();
                instance3.add(1, 5);
                Calendar calendar = instance3;
                KeyPair keyPair = m385int("EC", o2.f487for, "AndroidKeyStore", concat, str4, bigInteger, instance2, instance3, true, bArr);
                if (keyPair == null) {
                    keyPair = m385int("RSA", o2.f487for, "AndroidKeyStore", concat, str4, bigInteger, instance2, calendar, true, bArr);
                }
                if (keyPair == null) {
                    keyPair = m385int("EC", o2.f487for, "AndroidKeyStore", concat, str4, bigInteger, instance2, calendar, false, bArr);
                }
                if (keyPair == null || keyPair.getPrivate() == null || keyPair.getPublic() == null) {
                    return new L(W.REGISTRATION_FAILED, null);
                }
                try {
                    Certificate[] certificateChain = instance.getCertificateChain(concat);
                    if (certificateChain.length == 0) {
                        return new L(W.REGISTRATION_FAILED, null);
                    }
                    if (certificateChain.length == 1 && (certificateChain[0] instanceof X509Certificate)) {
                        X509Certificate x509Certificate = (X509Certificate) certificateChain[0];
                        byte[] signature = x509Certificate.getSignature();
                        if (signature == null || signature.length <= 2) {
                            byte[] tBSCertificate = x509Certificate.getTBSCertificate();
                            byte[] bArr3 = m388new(keyPair.getPrivate(), tBSCertificate);
                            if (bArr3 == null) {
                                return new L(W.REGISTRATION_FAILED, null);
                            }
                            Object[] objArr = new Object[3];
                            objArr[0] = tBSCertificate;
                            byte[][] bArr4 = new byte[1][];
                            if ("EC".equalsIgnoreCase(keyPair.getPrivate().getAlgorithm())) {
                                bArr2 = B.f58new;
                            } else {
                                bArr2 = B.f56if;
                            }
                            bArr4[0] = bArr2;
                            objArr[1] = Arrays.asList(bArr4);
                            objArr[2] = B.m17for(bArr3);
                            return new L(W.REGISTERED, NK.m210do(B.m20if((Object) Arrays.asList(objArr))));
                        }
                    }
                    byte[] encoded = certificateChain[0].getEncoded();
                    StringBuilder sb = new StringBuilder(((encoded.length * 2) + 1) * certificateChain.length);
                    sb.append(NK.m210do(encoded));
                    for (int i = 1; i < certificateChain.length; i++) {
                        sb.append(",");
                        sb.append(NK.m210do(certificateChain[i].getEncoded()));
                    }
                    return new L(W.REGISTERED, sb.toString());
                } catch (CertificateException unused2) {
                    return new L(W.REGISTRATION_FAILED, null);
                } catch (KeyStoreException unused3) {
                    return new L(W.REGISTRATION_FAILED, null);
                }
            } catch (Exception unused4) {
                return new L(W.REGISTRATION_FAILED, null);
            }
        }
    }

    /* renamed from: int reason: not valid java name */
    public static L m384int(W w) {
        return new L(w, null);
    }

    @TargetApi(18)
    /* renamed from: int reason: not valid java name */
    private static KeyPair m385int(String str, Context context, String str2, String str3, String str4, BigInteger bigInteger, Calendar calendar, Calendar calendar2, boolean z, byte[] bArr) {
        String str5 = str;
        if (!KeyChain.isKeyAlgorithmSupported(str5)) {
            return null;
        }
        if (E.m159int()) {
            return CO.m32do(str5, str2, str3, str4, bigInteger, calendar, calendar2, z, bArr);
        }
        if (z) {
            try {
                if (!KeyChain.isBoundKeyAlgorithm(str5)) {
                    return null;
                }
            } catch (InvalidAlgorithmParameterException e) {
                TL.m329for(f608new, "Can't create KeyPair {}", e.toString());
            } catch (NoSuchProviderException e2) {
                TL.m329for(f608new, "Can't create KeyPair {}", e2.toString());
            } catch (IllegalStateException | NoSuchAlgorithmException unused) {
            }
        }
        if (C0012I.f388for >= W.f404long && C0012I.f388for < 23 && E.m160new()) {
            Builder endDate = new Builder(context).setAlias(str3).setSubject(new X500Principal("CN=".concat(String.valueOf(str4)))).setSerialNumber(bigInteger).setStartDate(calendar.getTime()).setEndDate(calendar2.getTime());
            if (VERSION.SDK_INT > 18) {
                endDate.setKeyType(str5);
            }
            endDate.setEncryptionRequired();
            KeyPairGenerator instance = KeyPairGenerator.getInstance(str5, str2);
            instance.initialize(endDate.build());
            return instance.generateKeyPair();
        }
        return null;
    }

    /* access modifiers changed from: private */
    /* renamed from: new reason: not valid java name */
    public static byte[] m388new(PrivateKey privateKey, byte[] bArr) {
        try {
            if (!E.m158for()) {
                return null;
            }
            Signature instance = Signature.getInstance("EC".equalsIgnoreCase(privateKey.getAlgorithm()) ? "SHA256withECDSA" : "SHA256withRSA");
            instance.initSign(privateKey);
            instance.update(bArr);
            return instance.sign();
        } catch (NoSuchAlgorithmException e) {
            TL.m329for(f608new, "Can't sign the input {}", e.toString());
            return null;
        } catch (SignatureException e2) {
            TL.m329for(f608new, "Can't sign the input {}", e2.toString());
            return null;
        } catch (InvalidKeyException e3) {
            TL.m329for(f608new, "Can't sign the input {}", e3.toString());
            return null;
        }
    }

    /* renamed from: for reason: not valid java name */
    private static O m380for(String str) {
        BigInteger bigInteger;
        byte[] bArr;
        String concat = "TrustDefenderSDKStrongAuth".concat(str);
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load(null);
            Entry entry = instance.getEntry(concat, null);
            if (!(entry instanceof PrivateKeyEntry)) {
                return null;
            }
            PrivateKeyEntry privateKeyEntry = (PrivateKeyEntry) entry;
            PrivateKey privateKey = privateKeyEntry.getPrivateKey();
            Certificate certificate = privateKeyEntry.getCertificate();
            if (certificate instanceof X509Certificate) {
                bigInteger = ((X509Certificate) certificate).getSerialNumber();
                bArr = ((X509Certificate) certificate).getSubjectX500Principal().getEncoded();
            } else {
                BigInteger bigInteger2 = BigInteger.ZERO;
                bArr = m382if(str);
                bigInteger = bigInteger2;
            }
            return new O(privateKey, bigInteger, bArr);
        } catch (IOException unused) {
            return null;
        } catch (NoSuchAlgorithmException unused2) {
            return null;
        } catch (CertificateException unused3) {
            return null;
        } catch (UnrecoverableEntryException unused4) {
            return null;
        } catch (KeyStoreException unused5) {
            return null;
        }
    }

    /* access modifiers changed from: private */
    /* renamed from: if reason: not valid java name */
    public static byte[] m382if(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            instance.update(str.getBytes(Charset.forName("utf-8")));
            byte[] digest = instance.digest();
            instance.reset();
            return B.m20if((Object) Arrays.asList(new HashSet[]{new HashSet(Arrays.asList(new Object[]{Arrays.asList(new Serializable[]{B.f55for, NK.m210do(digest)})}))}));
        } catch (NoSuchAlgorithmException unused) {
            return null;
        }
    }

    /* renamed from: for reason: not valid java name */
    private static L m379for(String str, byte[] bArr, E e, I i) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA-256");
            try {
                byte[] bytes = str.getBytes(Charset.forName("utf-8"));
                instance.update(bytes);
                byte[] digest = instance.digest();
                instance.reset();
                HashSet hashSet = new HashSet(3);
                hashSet.add(Arrays.asList(new Object[]{B.f53do, new HashSet(Arrays.asList(new byte[][]{B.m21if(digest)}))}));
                hashSet.add(Arrays.asList(new Object[]{B.f57int, new HashSet(Arrays.asList(new byte[][]{B.m21if(bArr)}))}));
                hashSet.add(Arrays.asList(new Object[]{B.f50byte, new HashSet(Arrays.asList(new byte[][]{B.f54else}))}));
                hashSet.add(Arrays.asList(new Object[]{B.f52char, new HashSet(Arrays.asList(new byte[][]{B.m21if(e.f615for.getBytes())}))}));
                byte[] bArr2 = B.m20if((Object) hashSet);
                NK.m210do(bArr2);
                byte[] bArr3 = i.m395do(bArr2);
                if (bArr3 == null) {
                    return new L(W.STEPUP_FAILED, null);
                }
                return new L(W.STEPUP_COMPLETE, NK.m210do(B.m20if((Object) Arrays.asList(new byte[][]{B.f51case, B.m16for((Object) Collections.singletonList(Arrays.asList(new Object[]{Integer.valueOf(1), new HashSet(Collections.singletonList(Arrays.asList(new byte[][]{B.f59try, null}))), Arrays.asList(new byte[][]{B.f54else, B.m16for((Object) Collections.singletonList(B.m21if(bytes)))}), new HashSet(Collections.singletonList(Arrays.asList(new Object[]{Integer.valueOf(1), Arrays.asList(new Serializable[]{i.m394do(), i.m397new()}), Arrays.asList(new byte[][]{B.f59try, null}), B.m16for((Object) bArr2), Arrays.asList(new byte[][]{i.m396for()}), B.m21if(bArr3)})))})))}))));
            } catch (UnsupportedCharsetException unused) {
                return new L(W.MISSING_PARAMETER, null);
            } catch (IllegalArgumentException unused2) {
                return new L(W.MISSING_PARAMETER, null);
            }
        } catch (NoSuchAlgorithmException unused3) {
            return new L(W.NOT_SUPPORTED, null);
        }
    }

    /* renamed from: new reason: not valid java name */
    private static L m387new(final com.threatmetrix.TrustDefender.internal.P.O o, final String str, String str2, byte[] bArr, E e) {
        if (K5.m131int(o)) {
            PrivateKey privateKey = K5.f264for;
            if (privateKey == null) {
                return new L(W.CONTEXT_NOT_FOUND, null);
            }
            return m379for(str2, bArr, e, new O(privateKey, BigInteger.valueOf(K5.f265if), m382if(str)));
        } else if (PH.m275do().f494char) {
            return m379for(str2, bArr, e, new I() {
                /* renamed from: do reason: not valid java name */
                public final byte[] m390do(byte[] bArr) {
                    return PH.m275do().m294if(bArr, o.f487for.getContentResolver());
                }

                /* renamed from: for reason: not valid java name */
                public final byte[] m391for() {
                    return B.f58new;
                }

                /* renamed from: new reason: not valid java name */
                public final BigInteger m392new() {
                    return BigInteger.ZERO;
                }

                /* renamed from: do reason: not valid java name */
                public final byte[] m389do() {
                    return X7.m382if(str);
                }
            });
        } else {
            try {
                PrivateKey privateKey2 = (PrivateKey) ((Class) WM.m367for(0, 42, 42552)).getDeclaredField("do").get(null);
                if (privateKey2 == null) {
                    return new L(W.CONTEXT_NOT_FOUND, null);
                }
                return m379for(str2, bArr, e, new O(privateKey2, BigInteger.valueOf(((Class) WM.m367for(0, 42, 42552)).getDeclaredField("for").getLong(null)), m382if(str)));
            } catch (Throwable th) {
                TL.m337int(f608new, "Grave problem with strong ID", th);
                return new L(W.NOT_SUPPORTED, null);
            }
        }
    }

    /* renamed from: for reason: not valid java name */
    public static L m378for(com.threatmetrix.TrustDefender.internal.P.O o, E e, String str, String str2, String str3, byte[] bArr, StrongAuthCallback strongAuthCallback) {
        if (e == E.THM_USER_PRESENCE) {
            AuthenticationStatus authenticationStatus = (strongAuthCallback == null || C0012I.f388for < 21) ? AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE : P3.m274for(o.f487for, str, str2, strongAuthCallback);
            if (authenticationStatus == AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE) {
                TL.m338new(f608new, "StepUp Failure: Authentication is only for API 21+");
                return new L(W.NOT_SUPPORTED, null);
            } else if (authenticationStatus == AuthenticationStatus.THM_STRONG_AUTH_CANCELLED) {
                TL.m338new(f608new, "StepUp Failure: User cancelled authentication");
                return new L(W.STEPUP_CANCELLED, null);
            } else if (authenticationStatus != AuthenticationStatus.THM_STRONG_AUTH_OK) {
                TL.m338new(f608new, "StepUp Failure: User didn't proceed with authentication");
                return new L(W.STEPUP_FAILED, null);
            } else {
                O o2 = m380for(str3);
                return o2 == null ? new L(W.CONTEXT_NOT_FOUND, null) : m379for(str2, bArr, e, o2);
            }
        } else if (e != E.THM_DEVICE_PRESENCE) {
            return new L(W.MISSING_FUNCTION, null);
        } else {
            AuthenticationStatus authenticationStatus2 = AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE;
            if (strongAuthCallback instanceof StrongAuthPromptCallback) {
                authenticationStatus2 = ((StrongAuthPromptCallback) strongAuthCallback).prompt(str, str3, str2);
            }
            if (authenticationStatus2 == AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE) {
                authenticationStatus2 = (strongAuthCallback == null || C0012I.f388for < 21) ? AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE : P3.m274for(o.f487for, str, str2, strongAuthCallback);
            }
            if (authenticationStatus2 == AuthenticationStatus.THM_STRONG_AUTH_OK) {
                return m387new(o, str3, str2, bArr, e);
            }
            if (authenticationStatus2 == AuthenticationStatus.THM_STRONG_AUTH_CANCELLED) {
                TL.m338new(f608new, "StepUp Failure: User cancelled authentication");
                return new L(W.STEPUP_CANCELLED, null);
            } else if (authenticationStatus2 != AuthenticationStatus.THM_STRONG_AUTH_NOT_POSSIBLE) {
                return new L(W.STEPUP_FAILED, null);
            } else {
                TL.m338new(f608new, "StepUp Failure: Authentication not possible");
                return new L(W.NOT_SUPPORTED, null);
            }
        }
    }
}
