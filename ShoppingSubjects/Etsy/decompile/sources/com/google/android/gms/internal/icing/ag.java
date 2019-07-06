package com.google.android.gms.internal.icing;

import com.google.android.gms.internal.icing.ah.d;

final class ag implements bk {
    private static final ag a = new ag();

    private ag() {
    }

    public static ag a() {
        return a;
    }

    public final boolean a(Class<?> cls) {
        return ah.class.isAssignableFrom(cls);
    }

    public final bj b(Class<?> cls) {
        if (!ah.class.isAssignableFrom(cls)) {
            String str = "Unsupported message type: ";
            String valueOf = String.valueOf(cls.getName());
            throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
        }
        try {
            return (bj) ah.a(cls.asSubclass(ah.class)).a(d.c, (Object) null, (Object) null);
        } catch (Exception e) {
            String str2 = "Unable to get message info for ";
            String valueOf2 = String.valueOf(cls.getName());
            throw new RuntimeException(valueOf2.length() != 0 ? str2.concat(valueOf2) : new String(str2), e);
        }
    }
}
