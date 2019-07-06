package com.google.firebase.appindexing.internal;

import android.util.Log;

public final class o {
    public static int a(String str) {
        if (a(3)) {
            return Log.d("FirebaseAppIndex", str);
        }
        return 0;
    }

    public static boolean a(int i) {
        if (Log.isLoggable("FirebaseAppIndex", i)) {
            return true;
        }
        return Log.isLoggable("FirebaseAppIndex", i);
    }
}
