package com.google.android.gms.internal.icing;

import java.lang.reflect.Type;

public enum zzcg {
    DOUBLE(0, zzci.SCALAR, zzcu.DOUBLE),
    FLOAT(1, zzci.SCALAR, zzcu.FLOAT),
    INT64(2, zzci.SCALAR, zzcu.LONG),
    UINT64(3, zzci.SCALAR, zzcu.LONG),
    INT32(4, zzci.SCALAR, zzcu.INT),
    FIXED64(5, zzci.SCALAR, zzcu.LONG),
    FIXED32(6, zzci.SCALAR, zzcu.INT),
    BOOL(7, zzci.SCALAR, zzcu.BOOLEAN),
    STRING(8, zzci.SCALAR, zzcu.STRING),
    MESSAGE(9, zzci.SCALAR, zzcu.MESSAGE),
    BYTES(10, zzci.SCALAR, zzcu.BYTE_STRING),
    UINT32(11, zzci.SCALAR, zzcu.INT),
    ENUM(12, zzci.SCALAR, zzcu.ENUM),
    SFIXED32(13, zzci.SCALAR, zzcu.INT),
    SFIXED64(14, zzci.SCALAR, zzcu.LONG),
    SINT32(15, zzci.SCALAR, zzcu.INT),
    SINT64(16, zzci.SCALAR, zzcu.LONG),
    GROUP(17, zzci.SCALAR, zzcu.MESSAGE),
    DOUBLE_LIST(18, zzci.VECTOR, zzcu.DOUBLE),
    FLOAT_LIST(19, zzci.VECTOR, zzcu.FLOAT),
    INT64_LIST(20, zzci.VECTOR, zzcu.LONG),
    UINT64_LIST(21, zzci.VECTOR, zzcu.LONG),
    INT32_LIST(22, zzci.VECTOR, zzcu.INT),
    FIXED64_LIST(23, zzci.VECTOR, zzcu.LONG),
    FIXED32_LIST(24, zzci.VECTOR, zzcu.INT),
    BOOL_LIST(25, zzci.VECTOR, zzcu.BOOLEAN),
    STRING_LIST(26, zzci.VECTOR, zzcu.STRING),
    MESSAGE_LIST(27, zzci.VECTOR, zzcu.MESSAGE),
    BYTES_LIST(28, zzci.VECTOR, zzcu.BYTE_STRING),
    UINT32_LIST(29, zzci.VECTOR, zzcu.INT),
    ENUM_LIST(30, zzci.VECTOR, zzcu.ENUM),
    SFIXED32_LIST(31, zzci.VECTOR, zzcu.INT),
    SFIXED64_LIST(32, zzci.VECTOR, zzcu.LONG),
    SINT32_LIST(33, zzci.VECTOR, zzcu.INT),
    SINT64_LIST(34, zzci.VECTOR, zzcu.LONG),
    DOUBLE_LIST_PACKED(35, zzci.PACKED_VECTOR, zzcu.DOUBLE),
    FLOAT_LIST_PACKED(36, zzci.PACKED_VECTOR, zzcu.FLOAT),
    INT64_LIST_PACKED(37, zzci.PACKED_VECTOR, zzcu.LONG),
    UINT64_LIST_PACKED(38, zzci.PACKED_VECTOR, zzcu.LONG),
    INT32_LIST_PACKED(39, zzci.PACKED_VECTOR, zzcu.INT),
    FIXED64_LIST_PACKED(40, zzci.PACKED_VECTOR, zzcu.LONG),
    FIXED32_LIST_PACKED(41, zzci.PACKED_VECTOR, zzcu.INT),
    BOOL_LIST_PACKED(42, zzci.PACKED_VECTOR, zzcu.BOOLEAN),
    UINT32_LIST_PACKED(43, zzci.PACKED_VECTOR, zzcu.INT),
    ENUM_LIST_PACKED(44, zzci.PACKED_VECTOR, zzcu.ENUM),
    SFIXED32_LIST_PACKED(45, zzci.PACKED_VECTOR, zzcu.INT),
    SFIXED64_LIST_PACKED(46, zzci.PACKED_VECTOR, zzcu.LONG),
    SINT32_LIST_PACKED(47, zzci.PACKED_VECTOR, zzcu.INT),
    SINT64_LIST_PACKED(48, zzci.PACKED_VECTOR, zzcu.LONG),
    GROUP_LIST(49, zzci.VECTOR, zzcu.MESSAGE),
    MAP(50, zzci.MAP, zzcu.VOID);
    
    private static final zzcg[] P = null;
    private static final Type[] Q = null;
    private final int id;
    private final zzcu zzgw;
    private final zzci zzgx;
    private final Class<?> zzgy;
    private final boolean zzgz;

    static {
        int i;
        Q = new Type[0];
        zzcg[] values = values();
        P = new zzcg[values.length];
        for (zzcg zzcg : values) {
            P[zzcg.id] = zzcg;
        }
    }

    private zzcg(int i, zzci zzci, zzcu zzcu) {
        Class<?> zzbe;
        this.id = i;
        this.zzgx = zzci;
        this.zzgw = zzcu;
        switch (zzci) {
            case MAP:
            case VECTOR:
                zzbe = zzcu.zzbe();
                break;
            default:
                zzbe = null;
                break;
        }
        this.zzgy = zzbe;
        boolean z = false;
        if (zzci == zzci.SCALAR) {
            switch (zzcu) {
                case BYTE_STRING:
                case MESSAGE:
                case STRING:
                    break;
                default:
                    z = true;
                    break;
            }
        }
        this.zzgz = z;
    }

    public final int id() {
        return this.id;
    }
}
