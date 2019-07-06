package com.paypal.android.sdk.onetouch.core.encryption;

import com.paypal.android.sdk.onetouch.core.exception.InvalidEncryptionDataException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.Mac;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class OtcCrypto {
    private byte[] dataDigest(byte[] bArr, byte[] bArr2) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac instance = Mac.getInstance("HmacSHA256");
        instance.init(new SecretKeySpec(bArr2, "HmacSHA256"));
        return instance.doFinal(bArr);
    }

    public byte[] encryptRSAData(byte[] bArr, Certificate certificate) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidEncryptionDataException {
        if (bArr.length > 214) {
            StringBuilder sb = new StringBuilder();
            sb.append("Data is too large for public key encryption: ");
            sb.append(bArr.length);
            sb.append(" > ");
            sb.append(214);
            throw new InvalidEncryptionDataException(sb.toString());
        }
        PublicKey publicKey = certificate.getPublicKey();
        Cipher instance = Cipher.getInstance("RSA/ECB/OAEPWithSHA1AndMGF1Padding");
        instance.init(1, publicKey);
        return instance.doFinal(bArr);
    }

    public byte[] decryptAESCTRData(byte[] bArr, byte[] bArr2) throws IllegalBlockSizeException, InvalidKeyException, NoSuchAlgorithmException, IllegalArgumentException, InvalidAlgorithmParameterException, NoSuchPaddingException, BadPaddingException, InvalidEncryptionDataException {
        if (bArr.length < 48) {
            throw new InvalidEncryptionDataException("data is too small");
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr2, 0, bArr3, 0, 16);
        byte[] bArr4 = new byte[16];
        System.arraycopy(bArr2, 16, bArr4, 0, 16);
        byte[] bArr5 = new byte[32];
        System.arraycopy(bArr, 0, bArr5, 0, 32);
        byte[] bArr6 = new byte[(bArr.length - 32)];
        System.arraycopy(bArr, 32, bArr6, 0, bArr.length - 32);
        if (!EncryptionUtils.isEqual(dataDigest(bArr6, bArr4), bArr5)) {
            throw new IllegalArgumentException("Signature mismatch");
        }
        byte[] bArr7 = new byte[16];
        System.arraycopy(bArr6, 0, bArr7, 0, 16);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(bArr7);
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, "AES");
        Cipher instance = Cipher.getInstance("AES/CTR/NoPadding");
        instance.init(2, secretKeySpec, ivParameterSpec);
        return instance.doFinal(bArr6, 16, bArr6.length - 16);
    }
}
