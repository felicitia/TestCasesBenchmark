package com.google.firebase.perf;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FirebasePerformance {
    private static volatile FirebasePerformance zzm;
    private final Map<String, String> zzn = new ConcurrentHashMap();
    private Boolean zzo = null;

    public static FirebasePerformance getInstance() {
        if (zzm == null) {
            synchronized (FirebasePerformance.class) {
                if (zzm == null) {
                    zzm = (FirebasePerformance) FirebaseApp.getInstance().get(FirebasePerformance.class);
                }
            }
        }
        return zzm;
    }

    public FirebasePerformance(FirebaseApp firebaseApp) {
        if (firebaseApp == null) {
            this.zzo = Boolean.valueOf(false);
            return;
        }
        Context applicationContext = firebaseApp.getApplicationContext();
        this.zzo = zza(applicationContext, applicationContext.getSharedPreferences("FirebasePerfSharedPrefs", 0));
    }

    public boolean isPerformanceCollectionEnabled() {
        if (this.zzo != null) {
            return this.zzo.booleanValue();
        }
        return FirebaseApp.getInstance().isDataCollectionDefaultEnabled();
    }

    public final Map<String, String> getAttributes() {
        return new HashMap(this.zzn);
    }

    private static Boolean zza(Context context, SharedPreferences sharedPreferences) {
        if (zzb(context)) {
            return Boolean.valueOf(false);
        }
        try {
            if (sharedPreferences.contains("isEnabled")) {
                return Boolean.valueOf(sharedPreferences.getBoolean("isEnabled", true));
            }
        } catch (ClassCastException e) {
            String str = "FirebasePerformance";
            String str2 = "Unable to access enable value: ";
            String valueOf = String.valueOf(e.getMessage());
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        return zza(context);
    }

    private static Boolean zza(Context context) {
        try {
            Bundle bundle = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData;
            if (bundle.containsKey("firebase_performance_collection_enabled")) {
                return Boolean.valueOf(bundle.getBoolean("firebase_performance_collection_enabled", true));
            }
        } catch (NameNotFoundException | NullPointerException e) {
            String str = "isEnabled";
            String str2 = "No perf enable meta data found ";
            String valueOf = String.valueOf(e.getMessage());
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
        return null;
    }

    private static boolean zzb(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getBoolean("firebase_performance_collection_deactivated", false);
        } catch (NameNotFoundException | NullPointerException e) {
            String str = "isEnabled";
            String str2 = "No perf enable meta data found ";
            String valueOf = String.valueOf(e.getMessage());
            Log.d(str, valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            return false;
        }
    }
}
