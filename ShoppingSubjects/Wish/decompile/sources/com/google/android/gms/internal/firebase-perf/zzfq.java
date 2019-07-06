package com.google.android.gms.internal.firebase-perf;

public enum zzfq {
    DOUBLE(zzfv.DOUBLE, 1),
    FLOAT(zzfv.FLOAT, 5),
    INT64(zzfv.LONG, 0),
    UINT64(zzfv.LONG, 0),
    INT32(zzfv.INT, 0),
    FIXED64(zzfv.LONG, 1),
    FIXED32(zzfv.INT, 5),
    BOOL(zzfv.BOOLEAN, 0),
    STRING(zzfv.STRING, 2),
    GROUP(zzfv.MESSAGE, 3),
    MESSAGE(zzfv.MESSAGE, 2),
    BYTES(zzfv.BYTE_STRING, 2),
    UINT32(zzfv.INT, 0),
    ENUM(zzfv.ENUM, 0),
    SFIXED32(zzfv.INT, 5),
    SFIXED64(zzfv.LONG, 1),
    SINT32(zzfv.INT, 0),
    SINT64(zzfv.LONG, 0);
    
    private final zzfv zzry;
    private final int zzrz;

    private zzfq(zzfv zzfv, int i) {
        this.zzry = zzfv;
        this.zzrz = i;
    }

    public final zzfv zzgc() {
        return this.zzry;
    }

    public final int zzgd() {
        return this.zzrz;
    }
}
