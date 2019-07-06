package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import android.text.TextUtils;

final class akv extends aks {
    akv() {
    }

    @Nullable
    private static String a(@Nullable String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        int i = 0;
        int length = str.length();
        while (i < str.length() && str.charAt(i) == ',') {
            i++;
        }
        while (length > 0 && str.charAt(length - 1) == ',') {
            length--;
        }
        if (length < i) {
            return null;
        }
        return (i == 0 && length == str.length()) ? str : str.substring(i, length);
    }

    public final String a(@Nullable String str, String str2) {
        String a = a(str);
        String a2 = a(str2);
        if (TextUtils.isEmpty(a)) {
            return a2;
        }
        if (TextUtils.isEmpty(a2)) {
            return a;
        }
        StringBuilder sb = new StringBuilder(1 + String.valueOf(a).length() + String.valueOf(a2).length());
        sb.append(a);
        sb.append(",");
        sb.append(a2);
        return sb.toString();
    }
}
