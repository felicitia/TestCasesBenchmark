package com.google.android.gms.internal.icing;

final class bi {
    private static final bg a = c();
    private static final bg b = new bh();

    static bg a() {
        return a;
    }

    static bg b() {
        return b;
    }

    private static bg c() {
        try {
            return (bg) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
