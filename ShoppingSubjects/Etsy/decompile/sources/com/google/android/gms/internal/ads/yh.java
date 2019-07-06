package com.google.android.gms.internal.ads;

final class yh {
    private static final yf a = c();
    private static final yf b = new yg();

    static yf a() {
        return a;
    }

    static yf b() {
        return b;
    }

    private static yf c() {
        try {
            return (yf) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
