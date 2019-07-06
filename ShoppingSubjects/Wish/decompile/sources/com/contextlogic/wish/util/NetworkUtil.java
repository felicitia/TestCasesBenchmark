package com.contextlogic.wish.util;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.contextlogic.wish.application.WishApplication;

public class NetworkUtil {
    public static boolean isNetworkAvailable() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) WishApplication.getInstance().getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static String getUserAgent() {
        String string = PreferenceUtil.getString("UserAgent");
        if (string == null) {
            string = System.getProperty("http.agent");
        }
        return string == null ? "Wish Application for Android" : string;
    }
}
