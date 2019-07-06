package com.contextlogic.wish.util;

import com.contextlogic.wish.application.WishApplication;
import java.util.Locale;

public class LocaleUtil {
    public static boolean isJapanese() {
        return isLanguage("ja");
    }

    private static boolean isLanguage(String str) {
        Locale locale = Locale.getDefault();
        return (locale != null) && (locale.getLanguage() != null) && locale.getLanguage().startsWith(str);
    }

    public static int getResIdFromCountryCode(String str) {
        String str2;
        if (str != null) {
            StringBuilder sb = new StringBuilder();
            sb.append("flag_");
            sb.append(str.toLowerCase());
            str2 = sb.toString();
        } else {
            str2 = "";
        }
        return WishApplication.getInstance().getResources().getIdentifier(str2, "drawable", WishApplication.getInstance().getPackageName());
    }
}
