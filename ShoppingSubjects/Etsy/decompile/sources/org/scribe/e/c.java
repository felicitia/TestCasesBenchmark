package org.scribe.e;

import java.util.regex.Pattern;

/* compiled from: Preconditions */
public class c {
    private static final Pattern a = Pattern.compile("^[a-zA-Z][a-zA-Z0-9+.-]*://\\S+");

    public static void a(Object obj, String str) {
        a(obj != null, str);
    }

    public static void a(String str, String str2) {
        a(str != null && !str.trim().equals(""), str2);
    }

    private static void a(boolean z, String str) {
        if (str == null || str.trim().length() <= 0) {
            str = "Received an invalid parameter";
        }
        if (!z) {
            throw new IllegalArgumentException(str);
        }
    }
}
