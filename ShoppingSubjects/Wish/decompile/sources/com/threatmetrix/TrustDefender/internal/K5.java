package com.threatmetrix.TrustDefender.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.security.KeyChain;
import android.security.KeyPairGeneratorSpec.Builder;
import com.threatmetrix.TrustDefender.internal.P.O;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStore.Entry;
import java.security.KeyStore.PrivateKeyEntry;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.util.Calendar;
import java.util.Locale;
import javax.security.auth.x500.X500Principal;

class K5 {

    /* renamed from: do reason: not valid java name */
    private static PublicKey f263do = null;

    /* renamed from: for reason: not valid java name */
    static PrivateKey f264for = null;

    /* renamed from: if reason: not valid java name */
    static long f265if = 0;

    /* renamed from: int reason: not valid java name */
    private static String f266int = null;

    /* renamed from: new reason: not valid java name */
    private static final String f267new = TL.m331if(K5.class);

    /* renamed from: try reason: not valid java name */
    private static boolean f268try = false;

    K5() {
    }

    /* renamed from: if reason: not valid java name */
    static DN$L m128if(String str, O o, String str2) {
        DN$L dn$l = new DN$L();
        if (C0012I.f388for < W.f404long) {
            dn$l.f140int = "hardware_sid_unsupported_api";
            return dn$l;
        } else if (o == null || NK.m215if(str)) {
            dn$l.f140int = "hardware_sid_invalid_param";
            return dn$l;
        } else if (f268try || !m130if(o.f487for)) {
            dn$l.f140int = "hardware_sid_failed_to_generate_keypair";
            return dn$l;
        } else if (f264for == null || f263do == null || f266int == null) {
            dn$l.f140int = "hardware_sid_invalid_key";
            return dn$l;
        } else {
            String str3 = NK.m207do(10);
            byte[] bArr = m132int(str3.concat(str).concat(String.valueOf(f265if)).concat(f266int).concat(str2));
            if (bArr != null) {
                dn$l.f139if = NK.m210do(bArr);
                dn$l.f141new = NK.m223new(str3);
                dn$l.f137do = NK.m223new(String.valueOf(f265if));
                dn$l.f138for = NK.m223new(f266int);
                dn$l.f136byte = NK.m210do(f263do.getEncoded());
                dn$l.f140int = null;
            } else {
                dn$l.f140int = "hardware_sid_null_signature";
            }
            return dn$l;
        }
    }

    /* renamed from: if reason: not valid java name */
    private static boolean m130if(Context context) {
        if (!E.m157do() || f268try) {
            return false;
        }
        if (f264for != null && f263do != null) {
            return true;
        }
        try {
            KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
            instance.load(null);
            Entry entry = instance.getEntry("TrustDefenderSDK", null);
            if (entry instanceof PrivateKeyEntry) {
                PrivateKey privateKey = ((PrivateKeyEntry) entry).getPrivateKey();
                if (!m127for(privateKey, "AndroidKeyStore")) {
                    f268try = true;
                    return false;
                }
                f263do = instance.getCertificate("TrustDefenderSDK").getPublicKey();
                f264for = privateKey;
            } else {
                KeyPair keyPair = m129if("EC", context, "AndroidKeyStore", "TrustDefenderSDK");
                if (keyPair == null || !m127for(keyPair.getPrivate(), "AndroidKeyStore")) {
                    keyPair = m129if("RSA", context, "AndroidKeyStore", "TrustDefenderSDK");
                    if (keyPair != null) {
                        if (!m127for(keyPair.getPrivate(), "AndroidKeyStore")) {
                        }
                    }
                    f268try = true;
                    return false;
                }
                f264for = keyPair.getPrivate();
                f263do = keyPair.getPublic();
            }
            if (f264for == null) {
                return false;
            }
            f266int = "strong:".concat("EC".equalsIgnoreCase(f264for.getAlgorithm()) ? "ecdsa" : f264for.getAlgorithm()).toLowerCase(Locale.US);
            f265if = instance.getCreationDate("TrustDefenderSDK").getTime();
            return true;
        } catch (IOException e) {
            TL.m329for(f267new, "Can't load the KeyStore {}", e.toString());
            return false;
        } catch (CertificateException e2) {
            TL.m329for(f267new, "Can't load the KeyStore {}", e2.toString());
            return false;
        } catch (NoSuchAlgorithmException e3) {
            TL.m329for(f267new, "Can't retrieve key from KeyStore {}", e3.toString());
            return false;
        } catch (KeyStoreException e4) {
            TL.m329for(f267new, "Can't retrieve key from KeyStore {}", e4.toString());
            return false;
        } catch (UnrecoverableEntryException e5) {
            TL.m329for(f267new, "KeyEntry is not recoverable {}", e5.toString());
            return false;
        } catch (Throwable th) {
            TL.m334if(f267new, "Can't load the KeyStore (runtime exception) {}", th.toString());
            return false;
        }
    }

    @TargetApi(18)
    /* renamed from: if reason: not valid java name */
    private static KeyPair m129if(String str, Context context, String str2, String str3) {
        if (!KeyChain.isKeyAlgorithmSupported(str)) {
            return null;
        }
        try {
            if (E.m159int()) {
                return NC.m201new(str, str2, str3);
            }
            if (C0012I.f388for >= W.f404long && C0012I.f388for < 23 && E.m160new()) {
                Calendar instance = Calendar.getInstance();
                Calendar instance2 = Calendar.getInstance();
                instance2.add(1, 99);
                Builder endDate = new Builder(context).setAlias(str3).setSubject(new X500Principal("CN=TrustDefenderSDK O=ThreatMetrix")).setSerialNumber(BigInteger.TEN).setStartDate(instance.getTime()).setEndDate(instance2.getTime());
                if (VERSION.SDK_INT > 18) {
                    endDate.setKeyType(str);
                }
                KeyPairGenerator instance3 = KeyPairGenerator.getInstance(str, str2);
                instance3.initialize(endDate.build());
                return instance3.generateKeyPair();
            }
            return null;
        } catch (InvalidAlgorithmParameterException e) {
            TL.m329for(f267new, "Can't create KeyPair {}", e.toString());
        } catch (NoSuchProviderException e2) {
            TL.m329for(f267new, "Can't create KeyPair {}", e2.toString());
        } catch (NoSuchAlgorithmException unused) {
        } catch (Throwable th) {
            TL.m334if(f267new, "Can't create KeyPair  (runtime exception) {}", th.toString());
        }
    }

    @TargetApi(18)
    /* renamed from: for reason: not valid java name */
    private static boolean m127for(PrivateKey privateKey, String str) {
        if (privateKey == null) {
            return false;
        }
        try {
            if (E.m159int()) {
                return NC.m200if(privateKey, str);
            }
            if (C0012I.f388for >= W.f404long && C0012I.f388for < 23 && E.m160new()) {
                return KeyChain.isBoundKeyAlgorithm(privateKey.getAlgorithm());
            }
            return false;
        } catch (Throwable th) {
            TL.m334if(f267new, "Can't check key properties (runtime exception) {}", th.toString());
        }
    }

    /* renamed from: int reason: not valid java name */
    private static byte[] m132int(String str) {
        try {
            if (!E.m158for()) {
                return null;
            }
            if (f264for == null) {
                KeyStore instance = KeyStore.getInstance("AndroidKeyStore");
                instance.load(null);
                Entry entry = instance.getEntry("TrustDefenderSDK", null);
                if (!(entry instanceof PrivateKeyEntry)) {
                    return null;
                }
                f264for = ((PrivateKeyEntry) entry).getPrivateKey();
            }
            Signature instance2 = Signature.getInstance("EC".equalsIgnoreCase(f264for.getAlgorithm()) ? "SHA256withECDSA" : "SHA256withRSA");
            instance2.initSign(f264for);
            instance2.update(str.getBytes());
            return instance2.sign();
        } catch (NoSuchAlgorithmException e) {
            TL.m329for(f267new, "Can't sign the input {}", e.toString());
            return null;
        } catch (SignatureException e2) {
            TL.m329for(f267new, "Can't sign the input {}", e2.toString());
            return null;
        } catch (InvalidKeyException e3) {
            TL.m329for(f267new, "Can't sign the input {}", e3.toString());
            return null;
        } catch (CertificateException e4) {
            TL.m329for(f267new, "Can't sign the input {}", e4.toString());
            return null;
        } catch (KeyStoreException e5) {
            TL.m329for(f267new, "Can't sign the input {}", e5.toString());
            return null;
        } catch (UnrecoverableEntryException e6) {
            TL.m329for(f267new, "Can't sign the input {}", e6.toString());
            return null;
        } catch (IOException e7) {
            TL.m329for(f267new, "Can't sign the input {}", e7.toString());
            return null;
        } catch (Throwable th) {
            TL.m334if(f267new, "Can't sign the input (runtime exception) {}", th.toString());
            return null;
        }
    }

    /* renamed from: int reason: not valid java name */
    public static boolean m131int(O o) {
        if (f268try) {
            return false;
        }
        return m130if(o.f487for);
    }
}
