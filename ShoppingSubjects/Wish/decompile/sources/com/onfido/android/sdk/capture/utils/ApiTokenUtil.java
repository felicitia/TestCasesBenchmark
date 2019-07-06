package com.onfido.android.sdk.capture.utils;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.onfido.android.sdk.capture.OnfidoConfig;

public class ApiTokenUtil {
    private static String a(Context context) {
        try {
            Context applicationContext = context.getApplicationContext();
            return applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128).metaData.getString("onfido_api_token");
        } catch (NameNotFoundException unused) {
            return null;
        }
    }

    static String a(Context context, OnfidoConfig onfidoConfig) {
        String onfidoTokenFrom = getOnfidoTokenFrom(context, onfidoConfig);
        if (onfidoTokenFrom == null) {
            a("Please define the meta-data key 'onfido_api_token' in your manifest file.You can also put the token in OnfidoConfig.");
        }
        return onfidoTokenFrom;
    }

    private static void a(String str) {
        throw new IllegalArgumentException(str);
    }

    public static String getOnfidoTokenFrom(Context context, OnfidoConfig onfidoConfig) {
        String token = onfidoConfig.getToken();
        return token == null ? a(context) : token;
    }
}
