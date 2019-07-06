package com.kount.api;

import java.security.MessageDigest;

class HashUtils {
    public static String convertToSha256Hash(String str) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA256").digest(str.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(Integer.toHexString((b & 255) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
