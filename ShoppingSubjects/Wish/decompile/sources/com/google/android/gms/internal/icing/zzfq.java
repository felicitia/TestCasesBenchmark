package com.google.android.gms.internal.icing;

public enum zzfq {
    INT(Integer.valueOf(0)),
    LONG(Long.valueOf(0)),
    FLOAT(Float.valueOf(0.0f)),
    DOUBLE(Double.valueOf(0.0d)),
    BOOLEAN(Boolean.valueOf(false)),
    STRING(""),
    BYTE_STRING(zzbi.zzdq),
    ENUM(null),
    MESSAGE(null);
    
    private final Object zziz;

    private zzfq(Object obj) {
        this.zziz = obj;
    }
}
