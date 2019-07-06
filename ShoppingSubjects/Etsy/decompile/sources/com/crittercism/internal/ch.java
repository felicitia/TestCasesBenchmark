package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.crittercism.app.CrashData;

public final class ch {
    public static CrashData a;

    public static void a(Context context, CrashData crashData) {
        boolean z = false;
        Editor edit = context.getSharedPreferences("com.crittercism.usersettings", 0).edit();
        if (crashData != null) {
            z = true;
        }
        edit.putBoolean("crashedOnLastLoad", z);
        if (z) {
            edit.putString("crashName", crashData.getName());
            edit.putString("crashReason", crashData.getReason());
            edit.putLong("crashDate", crashData.getTimeOccurred().getTime());
        }
        edit.commit();
    }
}
