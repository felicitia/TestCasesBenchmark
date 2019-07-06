package com.contextlogic.wish.payments.adyen;

import android.util.Base64;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Locale;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class ClientSideEncrypter {
    private Cipher aesCipher;
    private PublicKey pubKey;
    private Cipher rsaCipher;
    private SecureRandom srandom = new SecureRandom();

    public ClientSideEncrypter(String str) throws EncrypterException {
        PRNGFixes.apply();
        String[] split = str.split("\\|");
        try {
            try {
                this.pubKey = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(new BigInteger(split[1].toLowerCase(Locale.getDefault()), 16), new BigInteger(split[0].toLowerCase(Locale.getDefault()), 16)));
                try {
                    this.aesCipher = Cipher.getInstance("AES/CCM/NoPadding", "BC");
                } catch (NoSuchAlgorithmException e) {
                    throw new EncrypterException("Problem instantiation AES Cipher Algorithm", e);
                } catch (NoSuchPaddingException e2) {
                    throw new EncrypterException("Problem instantiation AES Cipher Padding", e2);
                } catch (NoSuchProviderException e3) {
                    e3.printStackTrace();
                }
                try {
                    this.rsaCipher = Cipher.getInstance("RSA/None/PKCS1Padding");
                    this.rsaCipher.init(1, this.pubKey);
                } catch (NoSuchAlgorithmException e4) {
                    throw new EncrypterException("Problem instantiation RSA Cipher Algorithm", e4);
                } catch (NoSuchPaddingException e5) {
                    throw new EncrypterException("Problem instantiation RSA Cipher Padding", e5);
                } catch (InvalidKeyException e6) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Invalid public key: ");
                    sb.append(str);
                    throw new EncrypterException(sb.toString(), e6);
                }
            } catch (InvalidKeySpecException e7) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("Problem reading public key: ");
                sb2.append(str);
                throw new EncrypterException(sb2.toString(), e7);
            }
        } catch (NoSuchAlgorithmException e8) {
            e8.printStackTrace();
        }
    }

    public String encrypt(String str) throws EncrypterException {
        SecretKey generateAESKey = generateAESKey(256);
        byte[] generateIV = generateIV(12);
        try {
            this.aesCipher.init(1, generateAESKey, new IvParameterSpec(generateIV));
            byte[] doFinal = this.aesCipher.doFinal(str.getBytes());
            byte[] bArr = new byte[(generateIV.length + doFinal.length)];
            System.arraycopy(generateIV, 0, bArr, 0, generateIV.length);
            System.arraycopy(doFinal, 0, bArr, generateIV.length, doFinal.length);
            try {
                return String.format("%s%s%s%s%s%s", new Object[]{"adyenan", "0_1_1", "$", Base64.encodeToString(this.rsaCipher.doFinal(generateAESKey.getEncoded()), 2), "$", Base64.encodeToString(bArr, 2)});
            } catch (IllegalBlockSizeException e) {
                throw new EncrypterException("Incorrect RSA Block Size", e);
            } catch (BadPaddingException e2) {
                throw new EncrypterException("Incorrect RSA Padding", e2);
            }
        } catch (IllegalBlockSizeException e3) {
            throw new EncrypterException("Incorrect AES Block Size", e3);
        } catch (BadPaddingException e4) {
            throw new EncrypterException("Incorrect AES Padding", e4);
        } catch (InvalidKeyException e5) {
            throw new EncrypterException("Invalid AES Key", e5);
        } catch (InvalidAlgorithmParameterException e6) {
            throw new EncrypterException("Invalid AES Parameters", e6);
        }
    }

    private SecretKey generateAESKey(int i) throws EncrypterException {
        try {
            KeyGenerator instance = KeyGenerator.getInstance("AES");
            instance.init(i);
            return instance.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new EncrypterException("Unable to get AES algorithm", e);
        }
    }

    private synchronized byte[] generateIV(int i) {
        byte[] bArr;
        bArr = new byte[i];
        this.srandom.nextBytes(bArr);
        return bArr;
    }
}
