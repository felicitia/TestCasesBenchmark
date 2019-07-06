package com.etsy.android.ui.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.support.annotation.NonNull;
import com.etsy.android.BOEApplication;
import com.etsy.android.R;
import com.etsy.android.util.d;

/* compiled from: LocalLocationDebugOverride */
public class e {
    public static Location a(@NonNull Location location) {
        if (d.b()) {
            Context applicationContext = BOEApplication.get().getApplicationContext();
            SharedPreferences sharedPreferences = applicationContext.getSharedPreferences(applicationContext.getString(R.string.config_prefs_key), 0);
            if (a(applicationContext, sharedPreferences)) {
                location.setLatitude(a(applicationContext, sharedPreferences, location.getLatitude()));
                location.setLongitude(b(applicationContext, sharedPreferences, location.getLongitude()));
            }
        }
        return location;
    }

    private static boolean a(Context context, SharedPreferences sharedPreferences) {
        return d.b() && sharedPreferences != null && sharedPreferences.getBoolean(context.getString(R.string.config_override_location), false);
    }

    private static double a(Context context, SharedPreferences sharedPreferences, double d) {
        try {
            return Double.parseDouble(sharedPreferences.getString(context.getString(R.string.config_latitude), String.valueOf(d)));
        } catch (NumberFormatException unused) {
            return d;
        }
    }

    private static double b(Context context, SharedPreferences sharedPreferences, double d) {
        try {
            return Double.parseDouble(sharedPreferences.getString(context.getString(R.string.config_longitude), String.valueOf(d)));
        } catch (NumberFormatException unused) {
            return d;
        }
    }
}
