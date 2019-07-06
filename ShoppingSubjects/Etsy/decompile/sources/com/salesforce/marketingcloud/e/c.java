package com.salesforce.marketingcloud.e;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.Locale;
import java.util.UUID;

public final class c {
    public static String a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("_et_default_shared_preferences", 0);
        String string = sharedPreferences.getString("id", null);
        if (!TextUtils.isEmpty(string)) {
            return string;
        }
        String e = g.e(String.format(Locale.ENGLISH, "%s%d", new Object[]{UUID.randomUUID().toString(), Long.valueOf(System.currentTimeMillis())}));
        sharedPreferences.edit().putString("id", e).apply();
        return e;
    }
}
