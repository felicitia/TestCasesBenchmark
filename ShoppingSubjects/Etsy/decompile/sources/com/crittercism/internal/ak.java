package com.crittercism.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import com.android.volley.BuildConfig;
import com.crittercism.app.CrittercismConfig;

public final class ak {
    public String a = BuildConfig.VERSION_NAME;
    public int b = 0;

    public ak(Context context, CrittercismConfig crittercismConfig) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            this.a = packageInfo.versionName;
            this.b = packageInfo.versionCode;
        } catch (NameNotFoundException unused) {
        }
        String customVersionName = crittercismConfig.getCustomVersionName();
        if (customVersionName != null && customVersionName.length() > 0) {
            this.a = customVersionName;
        }
        if (crittercismConfig.isVersionCodeToBeIncludedInVersionString()) {
            StringBuilder sb = new StringBuilder();
            sb.append(this.a);
            sb.append("-");
            sb.append(Integer.toString(this.b));
            this.a = sb.toString();
        }
    }
}
