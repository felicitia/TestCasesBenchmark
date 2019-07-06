package com.onfido.api.client;

import okhttp3.logging.HttpLoggingInterceptor.Logger;

public class Utils {

    public static class Log {
        public static void e(String str, String str2) {
            Utils.Log("E", str, str2);
        }
    }

    public static void Log(String str, String str2, String str3) {
        Logger logger = Logger.DEFAULT;
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("/");
        sb.append(str2);
        sb.append(":");
        sb.append(str3);
        logger.log(sb.toString());
    }
}
