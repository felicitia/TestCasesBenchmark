package com.google.android.gms.internal.firebase-perf;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import java.net.URI;

public final class zzac {
    private static String[] zzfe;

    public static boolean zza(URI uri, Context context) {
        Resources resources = context.getResources();
        int identifier = resources.getIdentifier("firebase_performance_whitelisted_domains", "array", context.getPackageName());
        if (identifier <= 0) {
            return true;
        }
        Log.i("FirebasePerformance", "Detected domain whitelist, only whitelisted domains will be measured.");
        if (zzfe == null) {
            zzfe = resources.getStringArray(identifier);
        }
        for (String contains : zzfe) {
            if (uri.getHost().contains(contains)) {
                return true;
            }
        }
        return false;
    }
}
