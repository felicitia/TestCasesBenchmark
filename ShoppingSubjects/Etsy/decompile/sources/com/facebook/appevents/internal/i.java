package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import com.facebook.f;

/* compiled from: SourceApplicationInfo */
class i {
    private String a;
    private boolean b;

    /* compiled from: SourceApplicationInfo */
    public static class a {
        public static i a(Activity activity) {
            String str = "";
            ComponentName callingActivity = activity.getCallingActivity();
            if (callingActivity != null) {
                str = callingActivity.getPackageName();
                if (str.equals(activity.getPackageName())) {
                    return null;
                }
            }
            Intent intent = activity.getIntent();
            boolean z = false;
            if (intent != null && !intent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)) {
                intent.putExtra("_fbSourceApplicationHasBeenSet", true);
                Bundle a = bolts.a.a(intent);
                if (a != null) {
                    Bundle bundle = a.getBundle("referer_app_link");
                    if (bundle != null) {
                        str = bundle.getString("package");
                    }
                    z = true;
                }
            }
            intent.putExtra("_fbSourceApplicationHasBeenSet", true);
            return new i(str, z);
        }
    }

    private i(String str, boolean z) {
        this.a = str;
        this.b = z;
    }

    public static i a() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(f.f());
        if (!defaultSharedPreferences.contains("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage")) {
            return null;
        }
        return new i(defaultSharedPreferences.getString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", null), defaultSharedPreferences.getBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", false));
    }

    public static void b() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(f.f()).edit();
        edit.remove("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage");
        edit.remove("com.facebook.appevents.SourceApplicationInfo.openedByApplink");
        edit.apply();
    }

    public String toString() {
        String str = "Unclassified";
        if (this.b) {
            str = "Applink";
        }
        if (this.a == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("(");
        sb.append(this.a);
        sb.append(")");
        return sb.toString();
    }

    public void c() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(f.f()).edit();
        edit.putString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", this.a);
        edit.putBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", this.b);
        edit.apply();
    }
}
