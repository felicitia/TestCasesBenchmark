package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;

public final class ci {
    public static boolean a = true;
    public SharedPreferences b;

    public ci(Context context) {
        if (a) {
            a = false;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                cm.b(cl.StrictModeSessionId.a());
            }
        }
        this.b = context.getSharedPreferences("com.crittercism.usersettings", 0);
        if (!this.b.contains("sessionIDSetting")) {
            this.b.edit().putInt("sessionIDSetting", 0).commit();
        }
    }
}
