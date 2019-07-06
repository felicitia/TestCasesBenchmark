package com.threatmetrix.TrustDefender.internal;

import com.threatmetrix.TrustDefender.internal.D2.E;
import java.lang.reflect.Method;

class Q {

    /* renamed from: do reason: not valid java name */
    private static final String f502do = TL.m331if(Q.class);

    /* renamed from: if reason: not valid java name */
    private static final Method f503if;

    /* renamed from: new reason: not valid java name */
    private static final Method f504new;

    Q() {
    }

    static {
        Class cls = D2.m38do(E.FIREBASE_INSTANCE_ID);
        f503if = D2.m44for(cls, "getInstance", new Class[0]);
        f504new = D2.m44for(cls, "getToken", new Class[0]);
    }

    /* renamed from: int reason: not valid java name */
    static boolean m305int() {
        return (f503if == null || f504new == null) ? false : true;
    }

    /* renamed from: new reason: not valid java name */
    static String m306new() {
        if (f503if == null || f504new == null) {
            return null;
        }
        Object obj = D2.m39do((Object) null, f503if, new Object[0]);
        if (obj == null) {
            return null;
        }
        return (String) D2.m39do(obj, f504new, new Object[0]);
    }
}
