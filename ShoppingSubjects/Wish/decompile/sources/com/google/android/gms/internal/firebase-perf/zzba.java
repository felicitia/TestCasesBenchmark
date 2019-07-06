package com.google.android.gms.internal.firebase-perf;

final class zzba {
    private static final Class<?> zzhk = zzk("libcore.io.Memory");
    private static final boolean zzhl = (zzk("org.robolectric.Robolectric") != null);

    static boolean zzbk() {
        return zzhk != null && !zzhl;
    }

    static Class<?> zzbl() {
        return zzhk;
    }

    private static <T> Class<T> zzk(String str) {
        try {
            return Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }
}
