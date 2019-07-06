package com.contextlogic.wish.util;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.os.Build.VERSION;
import com.contextlogic.wish.application.ForegroundWatcher;
import com.contextlogic.wish.application.WishApplication;
import java.util.List;

public class ApplicationUtil {
    @SuppressLint({"NewApi"})
    public static boolean isPackageInForeground(String str) {
        ActivityManager activityManager = (ActivityManager) WishApplication.getInstance().getSystemService("activity");
        if (VERSION.SDK_INT > 20) {
            List<RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
            if (runningAppProcesses != null) {
                for (RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
                    if (runningAppProcessInfo.importance == 100) {
                        for (String equals : runningAppProcessInfo.pkgList) {
                            if (equals.equals(str)) {
                                return true;
                            }
                        }
                        continue;
                    }
                }
            } else if (str.equals(WishApplication.getInstance().getPackageName())) {
                return ForegroundWatcher.getInstance().isForeground();
            }
        } else if (str.equals(WishApplication.getInstance().getPackageName())) {
            return ForegroundWatcher.getInstance().isForeground();
        }
        return false;
    }
}
