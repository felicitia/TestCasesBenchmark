package com.google.android.gms.internal.ads;

final class xa {
    private static final wy<?> a = new wz();
    private static final wy<?> b = c();

    static wy<?> a() {
        return a;
    }

    static wy<?> b() {
        if (b != null) {
            return b;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }

    private static wy<?> c() {
        try {
            return (wy) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
