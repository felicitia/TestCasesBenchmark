package com.contextlogic.wish.util;

import android.graphics.Color;
import com.crashlytics.android.Crashlytics;

public class ColorUtil {
    public static int safeParseColor(String str, int i) {
        if (str == null) {
            return i;
        }
        try {
            return Color.parseColor(str);
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder();
            sb.append("Color passed in: ");
            sb.append(str);
            Crashlytics.logException(new IllegalArgumentException(sb.toString(), e.getCause()));
            return i;
        }
    }
}
