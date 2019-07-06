package com.google.android.gms.internal.ads;

final class yv {
    private static final yt a = c();
    private static final yt b = new yu();

    static yt a() {
        return a;
    }

    static yt b() {
        return b;
    }

    private static yt c() {
        try {
            return (yt) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
