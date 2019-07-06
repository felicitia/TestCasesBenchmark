package com.google.android.gms.maps.internal;

public final class c {
    public static byte a(Boolean bool) {
        if (bool != null) {
            return bool.booleanValue() ? (byte) 1 : 0;
        }
        return -1;
    }

    public static Boolean a(byte b) {
        switch (b) {
            case 0:
                return Boolean.FALSE;
            case 1:
                return Boolean.TRUE;
            default:
                return null;
        }
    }
}
