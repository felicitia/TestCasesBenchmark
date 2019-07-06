package com.google.android.gms.internal.icing;

final class zzea {
    private static final zzdy zzkp = zzby();
    private static final zzdy zzkq = new zzdz();

    static zzdy zzbw() {
        return zzkp;
    }

    static zzdy zzbx() {
        return zzkq;
    }

    private static zzdy zzby() {
        try {
            return (zzdy) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
