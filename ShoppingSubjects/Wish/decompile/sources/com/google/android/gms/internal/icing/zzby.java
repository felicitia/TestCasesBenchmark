package com.google.android.gms.internal.icing;

final class zzby {
    private static final Class<?> zzek = zzaf();

    private static Class<?> zzaf() {
        try {
            return Class.forName("com.google.protobuf.ExtensionRegistry");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static zzbz zzag() {
        if (zzek != null) {
            try {
                return (zzbz) zzek.getDeclaredMethod("getEmptyRegistry", new Class[0]).invoke(null, new Object[0]);
            } catch (Exception unused) {
            }
        }
        return zzbz.zzen;
    }
}
