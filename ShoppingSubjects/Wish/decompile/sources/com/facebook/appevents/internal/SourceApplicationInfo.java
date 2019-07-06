package com.facebook.appevents.internal;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import bolts.AppLinks;
import com.facebook.FacebookSdk;

class SourceApplicationInfo {
    private String callingApplicationPackage;
    private boolean openedByApplink;

    public static class Factory {
        public static SourceApplicationInfo create(Activity activity) {
            ComponentName callingActivity = activity.getCallingActivity();
            if (callingActivity == null) {
                return null;
            }
            String packageName = callingActivity.getPackageName();
            if (packageName.equals(activity.getPackageName())) {
                return null;
            }
            Intent intent = activity.getIntent();
            boolean z = false;
            if (intent != null && !intent.getBooleanExtra("_fbSourceApplicationHasBeenSet", false)) {
                intent.putExtra("_fbSourceApplicationHasBeenSet", true);
                Bundle appLinkData = AppLinks.getAppLinkData(intent);
                if (appLinkData != null) {
                    Bundle bundle = appLinkData.getBundle("referer_app_link");
                    if (bundle != null) {
                        packageName = bundle.getString("package");
                    }
                    z = true;
                }
            }
            intent.putExtra("_fbSourceApplicationHasBeenSet", true);
            return new SourceApplicationInfo(packageName, z);
        }
    }

    private SourceApplicationInfo(String str, boolean z) {
        this.callingApplicationPackage = str;
        this.openedByApplink = z;
    }

    public static SourceApplicationInfo getStoredSourceApplicatioInfo() {
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext());
        if (!defaultSharedPreferences.contains("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage")) {
            return null;
        }
        return new SourceApplicationInfo(defaultSharedPreferences.getString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", null), defaultSharedPreferences.getBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", false));
    }

    public static void clearSavedSourceApplicationInfoFromDisk() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.remove("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage");
        edit.remove("com.facebook.appevents.SourceApplicationInfo.openedByApplink");
        edit.apply();
    }

    public String toString() {
        String str = "Unclassified";
        if (this.openedByApplink) {
            str = "Applink";
        }
        if (this.callingApplicationPackage == null) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("(");
        sb.append(this.callingApplicationPackage);
        sb.append(")");
        return sb.toString();
    }

    public void writeSourceApplicationInfoToDisk() {
        Editor edit = PreferenceManager.getDefaultSharedPreferences(FacebookSdk.getApplicationContext()).edit();
        edit.putString("com.facebook.appevents.SourceApplicationInfo.callingApplicationPackage", this.callingApplicationPackage);
        edit.putBoolean("com.facebook.appevents.SourceApplicationInfo.openedByApplink", this.openedByApplink);
        edit.apply();
    }
}
