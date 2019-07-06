package com.google.android.gms.internal.icing;

public enum zzcu {
    VOID(Void.class, Void.class, null),
    INT(Integer.TYPE, Integer.class, Integer.valueOf(0)),
    LONG(Long.TYPE, Long.class, Long.valueOf(0)),
    FLOAT(Float.TYPE, Float.class, Float.valueOf(0.0f)),
    DOUBLE(Double.TYPE, Double.class, Double.valueOf(0.0d)),
    BOOLEAN(Boolean.TYPE, Boolean.class, Boolean.valueOf(false)),
    STRING(String.class, String.class, ""),
    BYTE_STRING(zzbi.class, zzbi.class, zzbi.zzdq),
    ENUM(Integer.TYPE, Integer.class, null),
    MESSAGE(Object.class, Object.class, null);
    
    private final Class<?> zzix;
    private final Class<?> zziy;
    private final Object zziz;

    private zzcu(Class<?> cls, Class<?> cls2, Object obj) {
        this.zzix = cls;
        this.zziy = cls2;
        this.zziz = obj;
    }

    public final Class<?> zzbe() {
        return this.zziy;
    }
}
