package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class UUIDHelper {
    public static String getPersistentUUID(Context context) {
        SharedPreferences sharedPreferences = BraintreeSharedPreferences.getSharedPreferences(context);
        String string = sharedPreferences.getString("braintreeUUID", null);
        if (string != null) {
            return string;
        }
        String formattedUUID = getFormattedUUID();
        sharedPreferences.edit().putString("braintreeUUID", formattedUUID).apply();
        return formattedUUID;
    }

    public static String getFormattedUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
