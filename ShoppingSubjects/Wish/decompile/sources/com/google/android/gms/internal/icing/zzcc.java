package com.google.android.gms.internal.icing;

final class zzcc {
    private static final zzca<?> zzep = new zzcb();
    private static final zzca<?> zzeq = zzaj();

    private static zzca<?> zzaj() {
        try {
            return (zzca) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }

    static zzca<?> zzak() {
        return zzep;
    }

    static zzca<?> zzal() {
        if (zzeq != null) {
            return zzeq;
        }
        throw new IllegalStateException("Protobuf runtime is not correctly loaded.");
    }
}
