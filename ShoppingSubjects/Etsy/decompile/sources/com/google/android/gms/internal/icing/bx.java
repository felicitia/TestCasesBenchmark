package com.google.android.gms.internal.icing;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

final class bx {
    private static final bx a = new bx();
    private final cb b;
    private final ConcurrentMap<Class<?>, ca<?>> c = new ConcurrentHashMap();

    private bx() {
        String[] strArr = {"com.google.protobuf.AndroidProto3SchemaFactory"};
        cb cbVar = null;
        for (int i = 0; i <= 0; i++) {
            cbVar = a(strArr[0]);
            if (cbVar != null) {
                break;
            }
        }
        if (cbVar == null) {
            cbVar = new bc();
        }
        this.b = cbVar;
    }

    public static bx a() {
        return a;
    }

    private static cb a(String str) {
        try {
            return (cb) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Throwable unused) {
            return null;
        }
    }

    public final <T> ca<T> a(Class<T> cls) {
        aj.a(cls, "messageType");
        ca<T> caVar = (ca) this.c.get(cls);
        if (caVar != null) {
            return caVar;
        }
        ca<T> a2 = this.b.a(cls);
        aj.a(cls, "messageType");
        aj.a(a2, "schema");
        ca caVar2 = (ca) this.c.putIfAbsent(cls, a2);
        return caVar2 != null ? caVar2 : a2;
    }

    public final <T> ca<T> a(T t) {
        return a(t.getClass());
    }
}
