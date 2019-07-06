package com.google.android.gms.internal.icing;

final class ab {
    private static final z<?> a = new aa();
    private static final z<?> b = c();

    static z<?> a() {
        return a;
    }

    static z<?> b() {
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static z<?> c() {
        try {
            return (z) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
