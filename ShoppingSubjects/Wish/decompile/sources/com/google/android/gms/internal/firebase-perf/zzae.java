package com.google.android.gms.internal.firebase-perf;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import java.net.MalformedURLException;
import java.net.URL;

public final class zzae {
    private static Boolean zzfg;

    public static String zzj(String str) {
        try {
            URL url = new URL(str);
            String query = url.getQuery();
            if (query != null) {
                str = str.substring(0, str.indexOf(query) - 1);
            }
            String userInfo = url.getUserInfo();
            if (userInfo == null) {
                return str;
            }
            return str.replace(String.valueOf(userInfo).concat("@"), "");
        } catch (MalformedURLException unused) {
            return str;
        }
    }

    public static String zzb(String str, int i) {
        if (str.length() <= 2000) {
            return str;
        }
        if (str.charAt(2000) == '/') {
            return str.substring(0, 2000);
        }
        try {
            if (new URL(str).getPath().lastIndexOf(47) >= 0) {
                int lastIndexOf = str.lastIndexOf(47, 1999);
                if (lastIndexOf >= 0) {
                    return str.substring(0, lastIndexOf);
                }
            }
            return str.substring(0, 2000);
        } catch (MalformedURLException unused) {
            return str.substring(0, 2000);
        }
    }

    public static int zza(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 4 && i < bArr.length) {
            i2 |= (bArr[i] & 255) << (i << 3);
            i++;
        }
        return i2;
    }

    public static boolean zzf(Context context) {
        if (zzfg != null) {
            return zzfg.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf(context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("firebase_performance_logcat_enabled", false));
            zzfg = valueOf;
            return valueOf.booleanValue();
        } catch (NameNotFoundException | NullPointerException e) {
            String str = "isEnabled";
            String str2 = "No perf logcat meta data found ";
            String valueOf2 = String.valueOf(e.getMessage());
            Log.d(str, valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2));
            return false;
        }
    }
}
