package com.stripe.android.util;

public class TextUtils {
    public static boolean hasAnyPrefix(String str, String... strArr) {
        if (str == null) {
            return false;
        }
        for (String startsWith : strArr) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static String nullIfBlank(String str) {
        if (isBlank(str)) {
            return null;
        }
        return str;
    }

    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }
}
