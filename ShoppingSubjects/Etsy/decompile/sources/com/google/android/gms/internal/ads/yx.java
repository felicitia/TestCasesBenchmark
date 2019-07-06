package com.google.android.gms.internal.ads;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class yx {
    private static final yx a = new yx();
    private final ze b;
    private final ConcurrentMap<Class<?>, zd<?>> c = new ConcurrentHashMap();

    private yx() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        ze zeVar = null;
        for (int i = 0; i <= 0; i++) {
            zeVar = a(strArr[0]);
            if (zeVar != null) {
                break;
            }
        }
        if (zeVar == null) {
            zeVar = new ya();
        }
        this.b = zeVar;
    }

    public static yx a() {
        return a;
    }

    private static ze a(String str) {
        try {
            return (ze) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> zd<T> a(Class<T> cls) {
        xj.a(cls, "messageType");
        zd<T> zdVar = (zd) this.c.get(cls);
        if (zdVar != null) {
            return zdVar;
        }
        zd<T> a2 = this.b.a(cls);
        xj.a(cls, "messageType");
        xj.a(a2, "schema");
        zd zdVar2 = (zd) this.c.putIfAbsent(cls, a2);
        return zdVar2 != null ? zdVar2 : a2;
    }

    public final <T> zd<T> a(T t) {
        return a(t.getClass());
    }
}
