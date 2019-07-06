package com.braintreegateway.encryption;

import com.braintree.org.bouncycastle.asn1.ASN1InputStream;
import com.braintree.org.bouncycastle.asn1.x509.RSAPublicKeyStructure;
import com.braintree.org.bouncycastle.util.encoders.Base64;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public final class Rsa {
    public static String encrypt(byte[] bArr, String str) throws BraintreeEncryptionException {
        try {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            instance.init(1, publicKey(str));
            return new String(Base64.encode(instance.doFinal(Base64.encode(bArr))));
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
        } catch (InvalidKeyException e3) {
            StringBuilder sb3 = new StringBuilder();
            sb3.append("Invalid Key: ");
            sb3.append(e3.getMessage());
            throw new BraintreeEncryptionException(sb3.toString());
        } catch (IllegalBlockSizeException e4) {
            StringBuilder sb4 = new StringBuilder();
            sb4.append("Illegal Block Size: ");
            sb4.append(e4.getMessage());
            throw new BraintreeEncryptionException(sb4.toString());
        } catch (BadPaddingException e5) {
            StringBuilder sb5 = new StringBuilder();
            sb5.append("Bad Padding: ");
            sb5.append(e5.getMessage());
            throw new BraintreeEncryptionException(sb5.toString());
        }
    }

    private static PublicKey publicKey(String str) throws BraintreeEncryptionException {
        ASN1InputStream aSN1InputStream = null;
        try {
            ASN1InputStream aSN1InputStream2 = new ASN1InputStream(Base64.decode(str));
            try {
                RSAPublicKeyStructure instance = RSAPublicKeyStructure.getInstance(aSN1InputStream2.readObject());
                PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new RSAPublicKeySpec(instance.getModulus(), instance.getPublicExponent()));
                try {
                    aSN1InputStream2.close();
                    return generatePublic;
                } catch (IOException e) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("IO Exception: ");
                    sb.append(e.getMessage());
                    throw new BraintreeEncryptionException(sb.toString());
                }
            } catch (NoSuchAlgorithmException e2) {
                e = e2;
                ASN1InputStream aSN1InputStream3 = aSN1InputStream2;
                StringBuilder sb2 = new StringBuilder();
                sb2.append("No Such Algorithm: ");
                sb2.append(e.getMessage());
                throw new BraintreeEncryptionException(sb2.toString());
            } catch (InvalidKeySpecException e3) {
                e = e3;
                ASN1InputStream aSN1InputStream4 = aSN1InputStream2;
                StringBuilder sb3 = new StringBuilder();
                sb3.append("Invalid Key Spec: ");
                sb3.append(e.getMessage());
                throw new BraintreeEncryptionException(sb3.toString());
            } catch (IOException e4) {
                e = e4;
                aSN1InputStream = aSN1InputStream2;
                StringBuilder sb4 = new StringBuilder();
                sb4.append("IO Exception: ");
                sb4.append(e.getMessage());
                throw new BraintreeEncryptionException(sb4.toString());
            } catch (Throwable th) {
                th = th;
                aSN1InputStream = aSN1InputStream2;
                try {
                    aSN1InputStream.close();
                    throw th;
                } catch (IOException e5) {
                    StringBuilder sb5 = new StringBuilder();
                    sb5.append("IO Exception: ");
                    sb5.append(e5.getMessage());
                    throw new BraintreeEncryptionException(sb5.toString());
                }
            }
        } catch (NoSuchAlgorithmException e6) {
            e = e6;
            StringBuilder sb22 = new StringBuilder();
            sb22.append("No Such Algorithm: ");
            sb22.append(e.getMessage());
            throw new BraintreeEncryptionException(sb22.toString());
        } catch (InvalidKeySpecException e7) {
            e = e7;
            StringBuilder sb32 = new StringBuilder();
            sb32.append("Invalid Key Spec: ");
            sb32.append(e.getMessage());
            throw new BraintreeEncryptionException(sb32.toString());
        } catch (IOException e8) {
            e = e8;
            StringBuilder sb42 = new StringBuilder();
            sb42.append("IO Exception: ");
            sb42.append(e.getMessage());
            throw new BraintreeEncryptionException(sb42.toString());
        } catch (Throwable th2) {
            th = th2;
            aSN1InputStream.close();
            throw th;
        }
    }
}
