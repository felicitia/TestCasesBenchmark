package com.paypal.android.sdk.onetouch.core.network;

public class EnvironmentManager {
    public static boolean isMock(String str) {
        return str.equals("mock");
    }

    public static boolean isSandbox(String str) {
        return str.equals("sandbox");
    }

    public static boolean isLive(String str) {
        return str.equals("live");
    }

    public static String getEnvironmentUrl(String str) {
        if (isLive(str)) {
            return "https://api-m.paypal.com/v1/";
        }
        if (isSandbox(str)) {
            return "https://api-m.sandbox.paypal.com/v1/";
        }
        if (isMock(str)) {
            return null;
        }
        return str;
    }
}
