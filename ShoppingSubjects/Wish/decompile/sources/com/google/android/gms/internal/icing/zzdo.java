package com.google.android.gms.internal.icing;

final class zzdo {
    private static final zzdm zzju = zzbr();
    private static final zzdm zzjv = new zzdn();

    static zzdm zzbp() {
        return zzju;
    }

    static zzdm zzbq() {
        return zzjv;
    }

    private static zzdm zzbr() {
        try {
            return (zzdm) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
