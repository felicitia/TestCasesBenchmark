package com.google.android.gms.internal.icing;

final class bv {
    private static final bs a = c();
    private static final bs b = new bt();

    static bs a() {
        return a;
    }

    static bs b() {
        return b;
    }

    private static bs c() {
        try {
            return (bs) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
