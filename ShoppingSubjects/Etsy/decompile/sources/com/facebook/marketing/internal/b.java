package com.facebook.marketing.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.support.v4.os.EnvironmentCompat;
import android.util.Log;
import com.facebook.f;

/* compiled from: MarketingUtils */
public class b {
    private static final String a = b.class.getCanonicalName();

    public static String a() {
        Context f = f.f();
        try {
            return f.getPackageManager().getPackageInfo(f.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            Log.e(a, "Failed to get app version.", e);
            return "";
        }
    }

    public static boolean b() {
        return Build.FINGERPRINT.startsWith("generic") || Build.FINGERPRINT.startsWith(EnvironmentCompat.MEDIA_UNKNOWN) || Build.MODEL.contains("google_sdk") || Build.MODEL.contains("Emulator") || Build.MODEL.contains("Android SDK built for x86") || Build.MANUFACTURER.contains("Genymotion") || (Build.BRAND.startsWith("generic") && Build.DEVICE.startsWith("generic")) || "google_sdk".equals(Build.PRODUCT);
    }
}
