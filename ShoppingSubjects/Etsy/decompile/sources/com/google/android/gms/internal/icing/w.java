package com.google.android.gms.internal.icing;

final class w {
    private static final Class<?> a = b();

    public static x a() {
        if (a != null) {
            try {
                return (x) a.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return x.a;
    }

    private static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
