package com.paypal.android.sdk.onetouch.core.metadata;

import android.os.Build;
import java.io.File;

public final class p {
    private static e a = new e();

    public static boolean a() {
        return (Build.TAGS != null && Build.TAGS.contains("test-keys")) || c() || d();
    }

    private static boolean c() {
        try {
            return new File(e.a("suFileName")).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    private static boolean d() {
        try {
            return new File(e.a("superUserApk")).exists();
        } catch (Exception unused) {
            return false;
        }
    }
}
