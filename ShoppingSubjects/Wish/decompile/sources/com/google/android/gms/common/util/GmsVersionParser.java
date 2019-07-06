package com.google.android.gms.common.util;

import java.util.regex.Pattern;

public final class GmsVersionParser {
    private static Pattern zzzy;

    public static int parseBuildVersion(int i) {
        if (i == -1) {
            return -1;
        }
        return i / 1000;
    }
}
