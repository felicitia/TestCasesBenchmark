package com.braintreepayments.api.internal;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;

public class ManifestValidator {
    public static ActivityInfo getActivityInfo(Context context, Class cls) {
        try {
            ActivityInfo[] activityInfoArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 1).activities;
            if (activityInfoArr != null) {
                for (ActivityInfo activityInfo : activityInfoArr) {
                    if (activityInfo.name.equals(cls.getName())) {
                        return activityInfo;
                    }
                }
            }
        } catch (NameNotFoundException unused) {
        }
        return null;
    }
}
