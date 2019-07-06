package com.google.android.gms.internal.firebase-perf;

import java.lang.reflect.Type;

public enum zzcg {
    DOUBLE(0, zzci.SCALAR, zzcv.DOUBLE),
    FLOAT(1, zzci.SCALAR, zzcv.FLOAT),
    INT64(2, zzci.SCALAR, zzcv.LONG),
    UINT64(3, zzci.SCALAR, zzcv.LONG),
    INT32(4, zzci.SCALAR, zzcv.INT),
    FIXED64(5, zzci.SCALAR, zzcv.LONG),
    FIXED32(6, zzci.SCALAR, zzcv.INT),
    BOOL(7, zzci.SCALAR, zzcv.BOOLEAN),
    STRING(8, zzci.SCALAR, zzcv.STRING),
    MESSAGE(9, zzci.SCALAR, zzcv.MESSAGE),
    BYTES(10, zzci.SCALAR, zzcv.BYTE_STRING),
    UINT32(11, zzci.SCALAR, zzcv.INT),
    ENUM(12, zzci.SCALAR, zzcv.ENUM),
    SFIXED32(13, zzci.SCALAR, zzcv.INT),
    SFIXED64(14, zzci.SCALAR, zzcv.LONG),
    SINT32(15, zzci.SCALAR, zzcv.INT),
    SINT64(16, zzci.SCALAR, zzcv.LONG),
    GROUP(17, zzci.SCALAR, zzcv.MESSAGE),
    DOUBLE_LIST(18, zzci.VECTOR, zzcv.DOUBLE),
    FLOAT_LIST(19, zzci.VECTOR, zzcv.FLOAT),
    INT64_LIST(20, zzci.VECTOR, zzcv.LONG),
    UINT64_LIST(21, zzci.VECTOR, zzcv.LONG),
    INT32_LIST(22, zzci.VECTOR, zzcv.INT),
    FIXED64_LIST(23, zzci.VECTOR, zzcv.LONG),
    FIXED32_LIST(24, zzci.VECTOR, zzcv.INT),
    BOOL_LIST(25, zzci.VECTOR, zzcv.BOOLEAN),
    STRING_LIST(26, zzci.VECTOR, zzcv.STRING),
    MESSAGE_LIST(27, zzci.VECTOR, zzcv.MESSAGE),
    BYTES_LIST(28, zzci.VECTOR, zzcv.BYTE_STRING),
    UINT32_LIST(29, zzci.VECTOR, zzcv.INT),
    ENUM_LIST(30, zzci.VECTOR, zzcv.ENUM),
    SFIXED32_LIST(31, zzci.VECTOR, zzcv.INT),
    SFIXED64_LIST(32, zzci.VECTOR, zzcv.LONG),
    SINT32_LIST(33, zzci.VECTOR, zzcv.INT),
    SINT64_LIST(34, zzci.VECTOR, zzcv.LONG),
    DOUBLE_LIST_PACKED(35, zzci.PACKED_VECTOR, zzcv.DOUBLE),
    FLOAT_LIST_PACKED(36, zzci.PACKED_VECTOR, zzcv.FLOAT),
    INT64_LIST_PACKED(37, zzci.PACKED_VECTOR, zzcv.LONG),
    UINT64_LIST_PACKED(38, zzci.PACKED_VECTOR, zzcv.LONG),
    INT32_LIST_PACKED(39, zzci.PACKED_VECTOR, zzcv.INT),
    FIXED64_LIST_PACKED(40, zzci.PACKED_VECTOR, zzcv.LONG),
    FIXED32_LIST_PACKED(41, zzci.PACKED_VECTOR, zzcv.INT),
    BOOL_LIST_PACKED(42, zzci.PACKED_VECTOR, zzcv.BOOLEAN),
    UINT32_LIST_PACKED(43, zzci.PACKED_VECTOR, zzcv.INT),
    ENUM_LIST_PACKED(44, zzci.PACKED_VECTOR, zzcv.ENUM),
    SFIXED32_LIST_PACKED(45, zzci.PACKED_VECTOR, zzcv.INT),
    SFIXED64_LIST_PACKED(46, zzci.PACKED_VECTOR, zzcv.LONG),
    SINT32_LIST_PACKED(47, zzci.PACKED_VECTOR, zzcv.INT),
    SINT64_LIST_PACKED(48, zzci.PACKED_VECTOR, zzcv.LONG),
    GROUP_LIST(49, zzci.VECTOR, zzcv.MESSAGE),
    MAP(50, zzci.MAP, zzcv.VOID);
    
    private static final zzcg[] zzln = null;
    private static final Type[] zzlo = null;
    private final int id;
    private final zzcv zzlj;
    private final zzci zzlk;
    private final Class<?> zzll;
    private final boolean zzlm;

    private zzcg(int i, zzci zzci, zzcv zzcv) {
        this.id = i;
        this.zzlk = zzci;
        this.zzlj = zzcv;
        switch (zzci) {
            case MAP:
                this.zzll = zzcv.zzef();
                break;
            case VECTOR:
                this.zzll = zzcv.zzef();
                break;
            default:
                this.zzll = null;
                break;
        }
        boolean z = false;
        if (zzci == zzci.SCALAR) {
            switch (zzcv) {
                case BYTE_STRING:
                case MESSAGE:
                case STRING:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzlm = z;
    }

    public final int id() {
        return this.id;
    }

    static {
        int i;
        zzlo = new Type[0];
        zzcg[] values = values();
        zzln = new zzcg[values.length];
        for (zzcg zzcg : values) {
            zzln[zzcg.id] = zzcg;
        }
    }
}
