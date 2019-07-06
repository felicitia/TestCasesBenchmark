package com.paypal.android.sdk.data.collector;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.UUID;

public class InstallationIdentifier {
    public static String getInstallationGUID(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("PayPalOTC", 0);
        String string = sharedPreferences.getString("InstallationGUID", null);
        if (string != null) {
            return string;
        }
        String uuid = UUID.randomUUID().toString();
        sharedPreferences.edit().putString("InstallationGUID", uuid).apply();
        return uuid;
    }
}
