package com.google.android.gms.internal.firebase-perf;

public enum zzcv {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzbd.class, zzbd.class, zzbd.zzho),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zznm;
    private final Class<?> zznn;
    private final Object zzno;

    private zzcv(Class<?> cls, Class<?> cls2, Object obj) {
        this.zznm = cls;
        this.zznn = cls2;
        this.zzno = obj;
    }

    public final Class<?> zzef() {
        return this.zznn;
    }
}
