package com.contextlogic.wish.util;

import com.contextlogic.wish.application.WishApplication;

public class AppVersionUtil {
    public static void markAppVersion() {
        String versionNumber = WishApplication.getInstance().getVersionNumber();
        String string = PreferenceUtil.getString("InstalledAppVersion");
        if (string == null) {
            string = PreferenceUtil.getString("PromptInstalledAppVersion");
        }
        if (string == null || !string.equals(versionNumber)) {
            if (string != null) {
                PreferenceUtil.setBoolean("ForceRelogin", true);
                PreferenceUtil.setString("LastInstalledAppVersion", string);
                PreferenceUtil.setBoolean("ServerAdvertisingPingSent", true);
            }
            PreferenceUtil.setString("InstalledAppVersion", versionNumber);
        }
    }
}
