package com.crittercism.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Looper;
import java.util.UUID;

public final class cf {
    public static boolean a = true;
    private SharedPreferences b;
    private SharedPreferences c;
    private Context d;

    public cf(Context context) {
        if (context == null) {
            throw new NullPointerException("context was null");
        }
        this.d = context;
        this.b = context.getSharedPreferences("com.crittercism.usersettings", 0);
        this.c = context.getSharedPreferences("com.crittercism.prefs", 0);
        if (this.b == null) {
            throw new NullPointerException("prefs were null");
        } else if (this.c == null) {
            throw new NullPointerException("legacy prefs were null");
        }
    }

    public final String a() {
        if (a) {
            boolean z = false;
            a = false;
            if (Looper.getMainLooper() == Looper.myLooper()) {
                z = true;
            }
            if (z) {
                cm.b(cl.StrictModeDeviceId.a());
            }
        }
        String string = this.b.getString("hashedDeviceID", null);
        if (string == null) {
            string = this.c.getString("com.crittercism.prefs.did", null);
            if (string != null && a(string)) {
                Editor edit = this.c.edit();
                edit.remove("com.crittercism.prefs.did");
                edit.commit();
            }
        }
        if (string != null) {
            return string;
        }
        String b2 = b();
        a(b2);
        return b2;
    }

    private boolean a(String str) {
        Editor edit = this.b.edit();
        edit.putString("hashedDeviceID", str);
        return edit.commit();
    }

    private static String b() {
        try {
            return UUID.randomUUID().toString();
        } catch (ThreadDeath e) {
            throw e;
        } catch (Throwable th) {
            cm.b(th);
            return null;
        }
    }
}
