package com.google.android.gms.internal.icing;

final class zzbf {
    private static final Class<?> zzdm = zzf("libcore.io.Memory");
    private static final boolean zzdn = (zzf("org.robolectric.Robolectric") != null);

    private static <T> Class<T> zzf(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzr() {
        return zzdm != null && !zzdn;
    }

    static Class<?> zzs() {
        return zzdm;
    }
}
