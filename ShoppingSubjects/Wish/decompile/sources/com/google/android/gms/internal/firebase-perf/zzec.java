package com.google.android.gms.internal.firebase-perf;

final class zzec {
    private static final zzea zzpg = zzez();
    private static final zzea zzph = new zzeb();

    static zzea zzex() {
        return zzpg;
    }

    static zzea zzey() {
        return zzph;
    }

    private static zzea zzez() {
        try {
            return (zzea) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            return null;
        }
    }
}
