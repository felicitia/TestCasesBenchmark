package com.google.android.gms.internal.ads;

final class wv {
    private static final Class<?> a = b();

    public static ww a() {
        if (a != null) {
            try {
                return (ww) a.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return ww.a;
    }

    private static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
