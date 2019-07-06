package com.salesforce.marketingcloud.e;

import android.text.TextUtils;

public final class f {
    public static <T extends CharSequence> T a(T t, String str) {
        if (!TextUtils.isEmpty(t)) {
            return t;
        }
        throw new IllegalArgumentException(str);
    }

    public static <T> T a(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean a(boolean z, String str) {
        if (z) {
            return true;
        }
        throw new IllegalArgumentException(str);
    }
}
