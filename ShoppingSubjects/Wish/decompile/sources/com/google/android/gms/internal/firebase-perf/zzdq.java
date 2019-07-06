package com.google.android.gms.internal.firebase-perf;

final class zzdq {
    private static final zzdo zzol = zzes();
    private static final zzdo zzom = new zzdp();

    static zzdo zzeq() {
        return zzol;
    }

    static zzdo zzer() {
        return zzom;
    }

    private static zzdo zzes() {
        try {
            return (zzdo) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
