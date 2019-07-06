package com.google.android.gms.common.internal;

public final class GmsLogger {
    public static final int MAX_PII_TAG_LENGTH = 15;
    private static final String zzub = null;
    private final String zzuc;
    private final String zzud;

    public GmsLogger(String str) {
        this(str, null);
    }

    public GmsLogger(String str, String str2) {
        Preconditions.checkNotNull(str, "log tag cannot be null");
        Preconditions.checkArgument(str.length() <= 23, "tag \"%s\" is longer than the %d character maximum", str, Integer.valueOf(23));
        this.zzuc = str;
        if (str2 == null || str2.length() <= 0) {
            this.zzud = null;
        } else {
            this.zzud = str2;
        }
    }
}
