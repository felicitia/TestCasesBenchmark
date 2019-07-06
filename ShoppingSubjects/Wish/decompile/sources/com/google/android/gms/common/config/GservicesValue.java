package com.google.android.gms.common.config;

public abstract class GservicesValue<T> {
    private static final Object sLock = new Object();
    private static zza zzmu = null;
    private static int zzmv = 0;
    private static String zzmx = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    protected final T mDefaultValue;
    protected final String mKey;
    private T zzmz = null;

    private interface zza {
    }

    protected GservicesValue(String str, T t) {
        this.mKey = str;
        this.mDefaultValue = t;
    }

    public static GservicesValue<Float> value(String str, Float f) {
        return new zze(str, f);
    }

    public static GservicesValue<Integer> value(String str, Integer num) {
        return new zzc(str, num);
    }

    public static GservicesValue<Long> value(String str, Long l) {
        return new zzb(str, l);
    }

    public static GservicesValue<String> value(String str, String str2) {
        return new zzf(str, str2);
    }

    public static GservicesValue<Boolean> value(String str, boolean z) {
        return new zza(str, Boolean.valueOf(z));
    }
}
