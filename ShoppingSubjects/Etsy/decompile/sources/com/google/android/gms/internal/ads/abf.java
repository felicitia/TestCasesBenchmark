package com.google.android.gms.internal.ads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.customtabs.CustomTabsService;
import android.text.TextUtils;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public final class abf {
    private static String a;

    public static String a(Context context) {
        String str;
        if (a != null) {
            return a;
        }
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
        ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
        String str2 = resolveActivity != null ? resolveActivity.activityInfo.packageName : null;
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(intent, 0);
        ArrayList arrayList = new ArrayList();
        for (ResolveInfo resolveInfo : queryIntentActivities) {
            Intent intent2 = new Intent();
            intent2.setAction(CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION);
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (packageManager.resolveService(intent2, 0) != null) {
                arrayList.add(resolveInfo.activityInfo.packageName);
            }
        }
        if (arrayList.isEmpty()) {
            a = null;
        } else {
            if (arrayList.size() == 1) {
                str = (String) arrayList.get(0);
            } else if (!TextUtils.isEmpty(str2) && !a(context, intent) && arrayList.contains(str2)) {
                a = str2;
            } else if (arrayList.contains("com.android.chrome")) {
                str = "com.android.chrome";
            } else if (arrayList.contains("com.chrome.beta")) {
                str = "com.chrome.beta";
            } else if (arrayList.contains("com.chrome.dev")) {
                str = "com.chrome.dev";
            } else if (arrayList.contains("com.google.android.apps.chrome")) {
                str = "com.google.android.apps.chrome";
            }
            a = str;
        }
        return a;
    }

    private static boolean a(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 64);
            if (queryIntentActivities == null || queryIntentActivities.size() == 0) {
                return false;
            }
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                IntentFilter intentFilter = resolveInfo.filter;
                if (intentFilter != null && intentFilter.countDataAuthorities() != 0 && intentFilter.countDataPaths() != 0 && resolveInfo.activityInfo != null) {
                    return true;
                }
            }
            return false;
        } catch (RuntimeException unused) {
            Log.e("CustomTabsHelper", "Runtime exception while getting specialized handlers");
        }
    }
}
