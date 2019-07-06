package com.google.android.gms.internal.icing;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class x {
    static final x a = new x(true);
    private static volatile boolean b = false;
    private static final Class<?> c = b();
    private final Map<Object, Object> d;

    x() {
        this.d = new HashMap();
    }

    private x(boolean z) {
        this.d = Collections.emptyMap();
    }

    public static x a() {
        return w.a();
    }

    private static Class<?> b() {
        try {
            return Class.forName("com.google.protobuf.Extension");
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
