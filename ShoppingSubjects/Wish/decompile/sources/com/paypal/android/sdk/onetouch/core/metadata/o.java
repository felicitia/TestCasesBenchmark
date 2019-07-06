package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Build;
import android.os.Environment;
import java.io.File;

public final class o {
    public static boolean a() {
        if (!(Build.MANUFACTURER.equals("unknown") || Build.MANUFACTURER.equals("Genymotion") || Build.MANUFACTURER.contains("AndyOS"))) {
            if (!(Build.BRAND.equals("generic") || Build.BRAND.equals("generic_x86") || Build.BRAND.equals("Android") || Build.BRAND.equals("AndyOS"))) {
                if (!(Build.DEVICE.equals("AndyOSX") || Build.DEVICE.equals("Droid4X") || Build.DEVICE.equals("generic") || Build.DEVICE.equals("generic_x86") || Build.DEVICE.equals("vbox86p"))) {
                    if (!(Build.HARDWARE.equals("goldfish") || Build.HARDWARE.equals("vbox86") || Build.HARDWARE.equals("andy"))) {
                        if (!(Build.MODEL.equals("sdk") || Build.MODEL.equals("google_sdk") || Build.MODEL.equals("Android SDK built for x86")) && !Build.FINGERPRINT.startsWith("generic")) {
                            if (!(Build.PRODUCT.matches(".*_?sdk_?.*") || Build.PRODUCT.equals("vbox86p") || Build.PRODUCT.equals("Genymotion") || Build.PRODUCT.equals("Driod4X") || Build.PRODUCT.equals("AndyOSX"))) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(Environment.getExternalStorageDirectory().toString());
                                sb.append(File.separatorChar);
                                sb.append("windows");
                                sb.append(File.separatorChar);
                                sb.append("BstSharedFolder");
                                return new File(sb.toString()).exists();
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
