package com.google.android.gms.internal.ads;

import com.google.android.gms.internal.ads.xh.e;

final class xg implements yj {
    private static final xg a = new xg();

    private xg() {
    }

    public static xg a() {
        return a;
    }

    public final boolean a(Class<?> cls) {
        return xh.class.isAssignableFrom(cls);
    }

    public final yi b(Class<?> cls) {
        if (!xh.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (yi) xh.a(cls.asSubclass(xh.class)).a(e.c, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
