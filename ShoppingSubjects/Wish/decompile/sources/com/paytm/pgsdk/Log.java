package com.paytm.pgsdk;

public class Log {
    private static boolean ENABLE_DEBUG_LOG = false;

    public static void d(String str, String str2) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.d(str, str2);
        }
    }

    public static void v(String str, String str2) {
        if (ENABLE_DEBUG_LOG) {
            android.util.Log.v(str, str2);
        }
    }

    public static void setEnableDebugLog(boolean z) {
        ENABLE_DEBUG_LOG = z;
    }
}
