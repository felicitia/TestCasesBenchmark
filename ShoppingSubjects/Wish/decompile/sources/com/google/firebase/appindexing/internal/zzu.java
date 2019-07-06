package com.google.firebase.appindexing.internal;

import android.util.Log;

public final class zzu {
    public static boolean isLoggable(int i) {
        if (Log.isLoggable("FirebaseAppIndex", i)) {
            return true;
        }
        return Log.isLoggable("FirebaseAppIndex", i);
    }

    public static int zze(String str) {
        if (isLoggable(3)) {
            return Log.d("FirebaseAppIndex", str);
        }
        return 0;
    }
}
