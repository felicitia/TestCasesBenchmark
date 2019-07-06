package com.salesforce.marketingcloud.tozny;

import android.os.Build;
import android.os.Build.VERSION;
import android.os.Process;
import android.support.annotation.Keep;
import android.util.Base64;
import android.util.Log;
import com.etsy.android.lib.convos.Draft;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.Provider;
import java.security.SecureRandom;
import java.security.SecureRandomSpi;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

@Keep
public class AesCbcWithIntegrity {
    private static final int AES_KEY_LENGTH_BITS = 128;
    private static final boolean ALLOW_BROKEN_PRNG = false;
    public static final int BASE64_FLAGS = 2;
    private static final String CIPHER = "AES";
    private static final String CIPHER_TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private static final String HMAC_ALGORITHM = "HmacSHA256";
    private static final int HMAC_KEY_LENGTH_BITS = 256;
    private static final int IV_LENGTH_BYTES = 16;
    private static final String PBE_ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int PBE_SALT_LENGTH_BITS = 128;
    static final AtomicBoolean prngFixed = new AtomicBoolean(false);

    @Keep
    public static class CipherTextIvMac {
        private final byte[] cipherText;
        private final byte[] iv;
        private final byte[] mac;

        public CipherTextIvMac(String str) {
            String[] split = str.split(Draft.IMAGE_DELIMITER);
            if (split.length != 3) {
                throw new IllegalArgumentException("Cannot parse iv:ciphertext:mac");
            }
            this.iv = Base64.decode(split[0], 2);
            this.mac = Base64.decode(split[1], 2);
            this.cipherText = Base64.decode(split[2], 2);
        }

        public CipherTextIvMac(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.cipherText = new byte[bArr.length];
            System.arraycopy(bArr, 0, this.cipherText, 0, bArr.length);
            this.iv = new byte[bArr2.length];
            System.arraycopy(bArr2, 0, this.iv, 0, bArr2.length);
            this.mac = new byte[bArr3.length];
            System.arraycopy(bArr3, 0, this.mac, 0, bArr3.length);
        }

        public static byte[] ivCipherConcat(byte[] bArr, byte[] bArr2) {
            byte[] bArr3 = new byte[(bArr.length + bArr2.length)];
            System.arraycopy(bArr, 0, bArr3, 0, bArr.length);
            System.arraycopy(bArr2, 0, bArr3, bArr.length, bArr2.length);
            return bArr3;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            CipherTextIvMac cipherTextIvMac = (CipherTextIvMac) obj;
            return Arrays.equals(this.cipherText, cipherTextIvMac.cipherText) && Arrays.equals(this.iv, cipherTextIvMac.iv) && Arrays.equals(this.mac, cipherTextIvMac.mac);
        }

        public byte[] getCipherText() {
            return this.cipherText;
        }

        public byte[] getIv() {
            return this.iv;
        }

        public byte[] getMac() {
            return this.mac;
        }

        public int hashCode() {
            return (31 * (((Arrays.hashCode(this.cipherText) + 31) * 31) + Arrays.hashCode(this.iv))) + Arrays.hashCode(this.mac);
        }

        public String toString() {
            String encodeToString = Base64.encodeToString(this.iv, 2);
            String encodeToString2 = Base64.encodeToString(this.cipherText, 2);
            String encodeToString3 = Base64.encodeToString(this.mac, 2);
            StringBuilder sb = new StringBuilder();
            sb.append(encodeToString);
            sb.append(Draft.IMAGE_DELIMITER);
            sb.append(encodeToString3);
            sb.append(Draft.IMAGE_DELIMITER);
            sb.append(encodeToString2);
            return String.format(sb.toString(), new Object[0]);
        }
    }

    public static final class PrngFixes {
        private static final byte[] a = g();

        @Keep
        public static class LinuxPRNGSecureRandom extends SecureRandomSpi {
            private static final File URANDOM_FILE = new File("/dev/urandom");
            private static final Object sLock = new Object();
            private static DataInputStream sUrandomIn;
            private static OutputStream sUrandomOut;
            private boolean mSeeded;

            private DataInputStream getUrandomInputStream() {
                DataInputStream dataInputStream;
                synchronized (sLock) {
                    if (sUrandomIn == null) {
                        try {
                            sUrandomIn = new DataInputStream(new FileInputStream(URANDOM_FILE));
                        } catch (IOException e) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("Failed to open ");
                            sb.append(URANDOM_FILE);
                            sb.append(" for reading");
                            throw new SecurityException(sb.toString(), e);
                        }
                    }
                    dataInputStream = sUrandomIn;
                }
                return dataInputStream;
            }

            private OutputStream getUrandomOutputStream() {
                OutputStream outputStream;
                synchronized (sLock) {
                    if (sUrandomOut == null) {
                        sUrandomOut = new FileOutputStream(URANDOM_FILE);
                    }
                    outputStream = sUrandomOut;
                }
                return outputStream;
            }

            /* access modifiers changed from: protected */
            public byte[] engineGenerateSeed(int i) {
                byte[] bArr = new byte[i];
                engineNextBytes(bArr);
                return bArr;
            }

            /* access modifiers changed from: protected */
            public void engineNextBytes(byte[] bArr) {
                DataInputStream urandomInputStream;
                if (!this.mSeeded) {
                    engineSetSeed(PrngFixes.e());
                }
                try {
                    synchronized (sLock) {
                        urandomInputStream = getUrandomInputStream();
                    }
                    synchronized (urandomInputStream) {
                        urandomInputStream.readFully(bArr);
                    }
                } catch (IOException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Failed to read from ");
                    sb.append(URANDOM_FILE);
                    throw new SecurityException(sb.toString(), e);
                }
            }

            /* access modifiers changed from: protected */
            public void engineSetSeed(byte[] bArr) {
                OutputStream urandomOutputStream;
                try {
                    synchronized (sLock) {
                        urandomOutputStream = getUrandomOutputStream();
                    }
                    urandomOutputStream.write(bArr);
                    urandomOutputStream.flush();
                } catch (IOException unused) {
                    try {
                        String simpleName = PrngFixes.class.getSimpleName();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Failed to mix seed into ");
                        sb.append(URANDOM_FILE);
                        Log.w(simpleName, sb.toString());
                    } catch (Throwable th) {
                        this.mSeeded = true;
                        throw th;
                    }
                }
                this.mSeeded = true;
            }
        }

        @Keep
        private static class LinuxPRNGSecureRandomProvider extends Provider {
            public LinuxPRNGSecureRandomProvider() {
                super("LinuxPRNG", 1.0d, "A Linux-specific random number provider that uses /dev/urandom");
                put("SecureRandom.SHA1PRNG", LinuxPRNGSecureRandom.class.getName());
                put("SecureRandom.SHA1PRNG ImplementedIn", "Software");
            }
        }

        private PrngFixes() {
        }

        public static void a() {
            c();
            d();
        }

        private static void c() {
            if (VERSION.SDK_INT >= 16 && VERSION.SDK_INT <= 18) {
                try {
                    Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_seed", new Class[]{byte[].class}).invoke(null, new Object[]{e()});
                    int intValue = ((Integer) Class.forName("org.apache.harmony.xnet.provider.jsse.NativeCrypto").getMethod("RAND_load_file", new Class[]{String.class, Long.TYPE}).invoke(null, new Object[]{"/dev/urandom", Integer.valueOf(1024)})).intValue();
                    if (intValue != 1024) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("Unexpected number of bytes read from Linux PRNG: ");
                        sb.append(intValue);
                        throw new IOException(sb.toString());
                    }
                } catch (Exception e) {
                    throw new SecurityException("Failed to seed OpenSSL PRNG", e);
                }
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:18:0x004e A[Catch:{ NoSuchAlgorithmException -> 0x0076, all -> 0x002a }] */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x006d  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private static void d() {
            /*
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 18
                if (r0 <= r1) goto L_0x0007
                return
            L_0x0007:
                java.lang.String r0 = "SecureRandom.SHA1PRNG"
                java.security.Provider[] r0 = java.security.Security.getProviders(r0)
                java.lang.Class<java.security.Security> r1 = java.security.Security.class
                monitor-enter(r1)
                r2 = 1
                if (r0 == 0) goto L_0x002d
                int r3 = r0.length     // Catch:{ all -> 0x002a }
                if (r3 < r2) goto L_0x002d
                r3 = 0
                r0 = r0[r3]     // Catch:{ all -> 0x002a }
                java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x002a }
                java.lang.String r0 = r0.getSimpleName()     // Catch:{ all -> 0x002a }
                java.lang.String r3 = "LinuxPRNGSecureRandomProvider"
                boolean r0 = r0.equals(r3)     // Catch:{ all -> 0x002a }
                if (r0 != 0) goto L_0x0035
                goto L_0x002d
            L_0x002a:
                r0 = move-exception
                goto L_0x00b3
            L_0x002d:
                com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider r0 = new com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity$PrngFixes$LinuxPRNGSecureRandomProvider     // Catch:{ all -> 0x002a }
                r0.<init>()     // Catch:{ all -> 0x002a }
                java.security.Security.insertProviderAt(r0, r2)     // Catch:{ all -> 0x002a }
            L_0x0035:
                java.security.SecureRandom r0 = new java.security.SecureRandom     // Catch:{ all -> 0x002a }
                r0.<init>()     // Catch:{ all -> 0x002a }
                java.security.Provider r2 = r0.getProvider()     // Catch:{ all -> 0x002a }
                java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x002a }
                java.lang.String r2 = r2.getSimpleName()     // Catch:{ all -> 0x002a }
                java.lang.String r3 = "LinuxPRNGSecureRandomProvider"
                boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x002a }
                if (r2 != 0) goto L_0x006d
                java.lang.SecurityException r2 = new java.lang.SecurityException     // Catch:{ all -> 0x002a }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
                r3.<init>()     // Catch:{ all -> 0x002a }
                java.lang.String r4 = "new SecureRandom() backed by wrong Provider: "
                r3.append(r4)     // Catch:{ all -> 0x002a }
                java.security.Provider r0 = r0.getProvider()     // Catch:{ all -> 0x002a }
                java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x002a }
                r3.append(r0)     // Catch:{ all -> 0x002a }
                java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x002a }
                r2.<init>(r0)     // Catch:{ all -> 0x002a }
                throw r2     // Catch:{ all -> 0x002a }
            L_0x006d:
                r0 = 0
                java.lang.String r2 = "SHA1PRNG"
                java.security.SecureRandom r2 = java.security.SecureRandom.getInstance(r2)     // Catch:{ NoSuchAlgorithmException -> 0x0076 }
                r0 = r2
                goto L_0x007e
            L_0x0076:
                r2 = move-exception
                java.lang.SecurityException r3 = new java.lang.SecurityException     // Catch:{ all -> 0x002a }
                java.lang.String r4 = "SHA1PRNG not available"
                r3.<init>(r4, r2)     // Catch:{ all -> 0x002a }
            L_0x007e:
                java.security.Provider r2 = r0.getProvider()     // Catch:{ all -> 0x002a }
                java.lang.Class r2 = r2.getClass()     // Catch:{ all -> 0x002a }
                java.lang.String r2 = r2.getSimpleName()     // Catch:{ all -> 0x002a }
                java.lang.String r3 = "LinuxPRNGSecureRandomProvider"
                boolean r2 = r2.equals(r3)     // Catch:{ all -> 0x002a }
                if (r2 != 0) goto L_0x00b1
                java.lang.SecurityException r2 = new java.lang.SecurityException     // Catch:{ all -> 0x002a }
                java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x002a }
                r3.<init>()     // Catch:{ all -> 0x002a }
                java.lang.String r4 = "SecureRandom.getInstance(\"SHA1PRNG\") backed by wrong Provider: "
                r3.append(r4)     // Catch:{ all -> 0x002a }
                java.security.Provider r0 = r0.getProvider()     // Catch:{ all -> 0x002a }
                java.lang.Class r0 = r0.getClass()     // Catch:{ all -> 0x002a }
                r3.append(r0)     // Catch:{ all -> 0x002a }
                java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x002a }
                r2.<init>(r0)     // Catch:{ all -> 0x002a }
                throw r2     // Catch:{ all -> 0x002a }
            L_0x00b1:
                monitor-exit(r1)     // Catch:{ all -> 0x002a }
                return
            L_0x00b3:
                monitor-exit(r1)     // Catch:{ all -> 0x002a }
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.salesforce.marketingcloud.tozny.AesCbcWithIntegrity.PrngFixes.d():void");
        }

        /* access modifiers changed from: private */
        public static byte[] e() {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
                dataOutputStream.writeLong(System.currentTimeMillis());
                dataOutputStream.writeLong(System.nanoTime());
                dataOutputStream.writeInt(Process.myPid());
                dataOutputStream.writeInt(Process.myUid());
                dataOutputStream.write(a);
                dataOutputStream.close();
                return byteArrayOutputStream.toByteArray();
            } catch (IOException e) {
                throw new SecurityException("Failed to generate seed", e);
            }
        }

        private static String f() {
            try {
                return (String) Build.class.getField("SERIAL").get(null);
            } catch (Exception unused) {
                return null;
            }
        }

        private static byte[] g() {
            StringBuilder sb = new StringBuilder();
            String str = Build.FINGERPRINT;
            if (str != null) {
                sb.append(str);
            }
            String f = f();
            if (f != null) {
                sb.append(f);
            }
            try {
                return sb.toString().getBytes("UTF-8");
            } catch (UnsupportedEncodingException unused) {
                throw new RuntimeException("UTF-8 encoding not supported");
            }
        }
    }

    @Keep
    public static class SecretKeys {
        private SecretKey confidentialityKey;
        private SecretKey integrityKey;

        public SecretKeys(SecretKey secretKey, SecretKey secretKey2) {
            setConfidentialityKey(secretKey);
            setIntegrityKey(secretKey2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            SecretKeys secretKeys = (SecretKeys) obj;
            return this.integrityKey.equals(secretKeys.integrityKey) && this.confidentialityKey.equals(secretKeys.confidentialityKey);
        }

        public SecretKey getConfidentialityKey() {
            return this.confidentialityKey;
        }

        public SecretKey getIntegrityKey() {
            return this.integrityKey;
        }

        public int hashCode() {
            return (31 * (this.confidentialityKey.hashCode() + 31)) + this.integrityKey.hashCode();
        }

        public void setConfidentialityKey(SecretKey secretKey) {
            this.confidentialityKey = secretKey;
        }

        public void setIntegrityKey(SecretKey secretKey) {
            this.integrityKey = secretKey;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(Base64.encodeToString(getConfidentialityKey().getEncoded(), 2));
            sb.append(Draft.IMAGE_DELIMITER);
            sb.append(Base64.encodeToString(getIntegrityKey().getEncoded(), 2));
            return sb.toString();
        }
    }

    public static boolean constantTimeEq(byte[] bArr, byte[] bArr2) {
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

    private static byte[] copyOfRange(byte[] bArr, int i, int i2) {
        int i3 = i2 - i;
        byte[] bArr2 = new byte[i3];
        System.arraycopy(bArr, i, bArr2, 0, i3);
        return bArr2;
    }

    public static byte[] decrypt(CipherTextIvMac cipherTextIvMac, SecretKeys secretKeys) {
        if (constantTimeEq(generateMac(CipherTextIvMac.ivCipherConcat(cipherTextIvMac.getIv(), cipherTextIvMac.getCipherText()), secretKeys.getIntegrityKey()), cipherTextIvMac.getMac())) {
            Cipher instance = Cipher.getInstance(CIPHER_TRANSFORMATION);
            instance.init(2, secretKeys.getConfidentialityKey(), new IvParameterSpec(cipherTextIvMac.getIv()));
            return instance.doFinal(cipherTextIvMac.getCipherText());
        }
        throw new GeneralSecurityException("MAC stored in civ does not match computed MAC.");
    }

    public static String decryptString(CipherTextIvMac cipherTextIvMac, SecretKeys secretKeys) {
        return decryptString(cipherTextIvMac, secretKeys, "UTF-8");
    }

    public static String decryptString(CipherTextIvMac cipherTextIvMac, SecretKeys secretKeys, String str) {
        return new String(decrypt(cipherTextIvMac, secretKeys), str);
    }

    public static CipherTextIvMac encrypt(String str, SecretKeys secretKeys) {
        return encrypt(str, secretKeys, "UTF-8");
    }

    public static CipherTextIvMac encrypt(String str, SecretKeys secretKeys, String str2) {
        return encrypt(str.getBytes(str2), secretKeys);
    }

    public static CipherTextIvMac encrypt(byte[] bArr, SecretKeys secretKeys) {
        byte[] generateIv = generateIv();
        Cipher instance = Cipher.getInstance(CIPHER_TRANSFORMATION);
        instance.init(1, secretKeys.getConfidentialityKey(), new IvParameterSpec(generateIv));
        byte[] iv = instance.getIV();
        byte[] doFinal = instance.doFinal(bArr);
        return new CipherTextIvMac(doFinal, iv, generateMac(CipherTextIvMac.ivCipherConcat(iv, doFinal), secretKeys.getIntegrityKey()));
    }

    private static void fixPrng() {
        if (!prngFixed.get()) {
            synchronized (PrngFixes.class) {
                if (!prngFixed.get()) {
                    PrngFixes.a();
                    prngFixed.set(true);
                }
            }
        }
    }

    public static byte[] generateIv() {
        return randomBytes(16);
    }

    public static SecretKeys generateKey() {
        fixPrng();
        KeyGenerator instance = KeyGenerator.getInstance(CIPHER);
        instance.init(128);
        return new SecretKeys(instance.generateKey(), new SecretKeySpec(randomBytes(32), HMAC_ALGORITHM));
    }

    public static SecretKeys generateKeyFromPassword(String str, String str2, int i) {
        return generateKeyFromPassword(str, Base64.decode(str2, 2), i);
    }

    public static SecretKeys generateKeyFromPassword(String str, byte[] bArr, int i) {
        fixPrng();
        byte[] encoded = SecretKeyFactory.getInstance(PBE_ALGORITHM).generateSecret(new PBEKeySpec(str.toCharArray(), bArr, i, 384)).getEncoded();
        return new SecretKeys(new SecretKeySpec(copyOfRange(encoded, 0, 16), CIPHER), new SecretKeySpec(copyOfRange(encoded, 16, 48), HMAC_ALGORITHM));
    }

    public static byte[] generateMac(byte[] bArr, SecretKey secretKey) {
        Mac instance = Mac.getInstance(HMAC_ALGORITHM);
        instance.init(secretKey);
        return instance.doFinal(bArr);
    }

    public static byte[] generateSalt() {
        return randomBytes(128);
    }

    public static String keyString(SecretKeys secretKeys) {
        return secretKeys.toString();
    }

    public static SecretKeys keys(String str) {
        String[] split = str.split(Draft.IMAGE_DELIMITER);
        if (split.length != 2) {
            throw new IllegalArgumentException("Cannot parse aesKey:hmacKey");
        }
        byte[] decode = Base64.decode(split[0], 2);
        if (decode.length != 16) {
            throw new InvalidKeyException("Base64 decoded key is not 128 bytes");
        }
        byte[] decode2 = Base64.decode(split[1], 2);
        if (decode2.length == 32) {
            return new SecretKeys(new SecretKeySpec(decode, 0, decode.length, CIPHER), new SecretKeySpec(decode2, HMAC_ALGORITHM));
        }
        throw new InvalidKeyException("Base64 decoded key is not 256 bytes");
    }

    private static byte[] randomBytes(int i) {
        fixPrng();
        byte[] bArr = new byte[i];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public static String saltString(byte[] bArr) {
        return Base64.encodeToString(bArr, 2);
    }
}
