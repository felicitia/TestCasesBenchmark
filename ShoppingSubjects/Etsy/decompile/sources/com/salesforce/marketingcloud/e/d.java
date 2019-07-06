package com.salesforce.marketingcloud.e;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import com.salesforce.marketingcloud.j;

public final class d {
    private static final String a = j.a(d.class);
    private static String b;

    private d() {
    }

    @NonNull
    public static String a(Context context) {
        if (!TextUtils.isEmpty(b)) {
            return b;
        }
        b = "";
        if (context == null) {
            return b;
        }
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            b = String.format("%s : %s", new Object[]{packageInfo.versionName, Integer.valueOf(packageInfo.versionCode)});
        } catch (NameNotFoundException e) {
            j.c(a, e, "Failed to get Application Version from the PackageManager.", new Object[0]);
        }
        return b;
    }

    public static boolean a(@NonNull Context context, @NonNull String str) {
        return ContextCompat.checkSelfPermission(context, str) == 0;
    }

    public static boolean a(PackageManager packageManager, Intent intent) {
        return packageManager.queryIntentServices(intent, 65536).size() > 0;
    }

    public static boolean b(PackageManager packageManager, Intent intent) {
        return packageManager.queryBroadcastReceivers(intent, 131072).size() > 0;
    }
}
