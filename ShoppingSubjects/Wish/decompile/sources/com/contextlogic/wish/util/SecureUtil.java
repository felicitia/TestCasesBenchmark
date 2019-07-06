package com.contextlogic.wish.util;

import android.content.Context;
import android.provider.Settings.Secure;
import android.util.Base64;
import com.contextlogic.wish.application.WishApplication;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class SecureUtil {
    private static byte[] sSecureKey = generateSecureKey();

    private static byte[] generateSecureKey() {
        try {
            String generateAesKeyName = generateAesKeyName(WishApplication.getInstance());
            String string = PreferenceUtil.getString(generateAesKeyName);
            if (string == null) {
                string = generateAesKeyValue();
                PreferenceUtil.setString(generateAesKeyName, string);
            }
            return decode(string);
        } catch (Throwable unused) {
            return new byte[]{0, 1, 2, 3, 4};
        }
    }

    private static String encode(byte[] bArr) {
        return Base64.encodeToString(bArr, 3);
    }

    private static byte[] decode(String str) {
        return Base64.decode(str, 3);
    }

    private static String generateAesKeyName(Context context) throws InvalidKeySpecException, NoSuchAlgorithmException {
        char[] charArray = context.getPackageName().toCharArray();
        String string = Secure.getString(context.getContentResolver(), "android_id");
        if (string == null) {
            string = "9774d56d682e549c";
        }
        return encode(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(charArray, string.getBytes(), 1000, 256)).getEncoded());
    }

    private static String generateAesKeyValue() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        try {
            instance.init(256, secureRandom);
        } catch (Exception unused) {
            try {
                instance.init(192, secureRandom);
            } catch (Exception unused2) {
                instance.init(128, secureRandom);
            }
        }
        return encode(instance.generateKey().getEncoded());
    }

    public static String encrypt(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        try {
            Cipher instance = Cipher.getInstance("AES");
            instance.init(1, new SecretKeySpec(sSecureKey, "AES"));
            return encode(instance.doFinal(str.getBytes("UTF-8")));
        } catch (Exception unused) {
            return null;
        }
    }

    public static String decrypt(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        try {
            Cipher instance = Cipher.getInstance("AES");
            instance.init(2, new SecretKeySpec(sSecureKey, "AES"));
            return new String(instance.doFinal(decode(str)), "UTF-8");
        } catch (Exception unused) {
            return null;
        }
    }
}
