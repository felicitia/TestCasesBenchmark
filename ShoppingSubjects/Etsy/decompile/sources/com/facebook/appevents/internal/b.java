package com.facebook.appevents.internal;

import com.facebook.internal.z;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: AppEventUtility */
public class b {
    public static void a() {
    }

    public static void b() {
    }

    public static double a(String str) {
        try {
            Matcher matcher = Pattern.compile("[-+]*\\d+([\\,\\.]\\d+)*([\\.\\,]\\d+)?", 8).matcher(str);
            if (!matcher.find()) {
                return 0.0d;
            }
            return NumberFormat.getNumberInstance(z.a()).parse(matcher.group(0)).doubleValue();
        } catch (ParseException unused) {
            return 0.0d;
        }
    }
}
