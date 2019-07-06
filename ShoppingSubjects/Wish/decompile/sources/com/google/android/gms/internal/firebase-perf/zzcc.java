package com.google.android.gms.internal.firebase-perf;

final class zzcc {
    private static final zzca<?> zzjd = new zzcb();
    private static final zzca<?> zzje = zzdc();

    private static zzca<?> zzdc() {
        try {
            return (zzca) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzca<?> zzdd() {
        return zzjd;
    }

    static zzca<?> zzde() {
        if (zzje != null) {
            return zzje;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
