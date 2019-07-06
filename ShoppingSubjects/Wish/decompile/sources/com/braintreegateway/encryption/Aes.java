package com.braintreegateway.encryption;

import com.braintree.org.bouncycastle.util.encoders.Base64;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public final class Aes {
    public static String encrypt(String str, byte[] bArr, byte[] bArr2) throws BraintreeEncryptionException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, "AES");
        Cipher aesCipher = aesCipher();
        try {
            aesCipher.init(1, secretKeySpec, new IvParameterSpec(bArr2));
            byte[] doFinal = aesCipher.doFinal(str.getBytes());
            byte[] copyOf = Arrays.copyOf(bArr2, bArr2.length + doFinal.length);
            System.arraycopy(doFinal, 0, copyOf, bArr2.length, doFinal.length);
            return new String(Base64.encode(copyOf));
        } catch (InvalidKeyException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Invalid Key: ");
            sb.append(e.getMessage());
            throw new BraintreeEncryptionException(sb.toString());
        } catch (BadPaddingException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("Bad Padding: ");
            sb2.append(e2.getMessage());
            throw new BraintreeEncryptionException(sb2.toString());
        } catch (IllegalBlockSizeException e3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Illegal Block Size: ");
            sb3.append(e3.getMessage());
            throw new BraintreeEncryptionException(sb3.toString());
        } catch (InvalidAlgorithmParameterException e4) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Invalid Algorithm: ");
            sb4.append(e4.getMessage());
            throw new BraintreeEncryptionException(sb4.toString());
        }
    }

    private static Cipher aesCipher() throws BraintreeEncryptionException {
        try {
            return Cipher.getInstance("AES/CBC/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            StringBuilder sb = new StringBuilder();
            sb.append("No Such Algorithm: ");
            sb.append(e.getMessage());
            throw new BraintreeEncryptionException(sb.toString());
        } catch (NoSuchPaddingException e2) {
            StringBuilder sb2 = new StringBuilder();
            sb2.append("No Such Padding: ");
            sb2.append(e2.getMessage());
            throw new BraintreeEncryptionException(sb2.toString());
        }
    }
}
