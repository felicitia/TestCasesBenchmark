package com.google.gson.internal.a;

import com.google.gson.a.a;
import com.google.gson.e;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.b;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: ObjectTypeAdapter */
public final class h extends q<Object> {
    public static final r a = new r() {
        public <T> q<T> a(e eVar, a<T> aVar) {
            if (aVar.a() == Object.class) {
                return new h(eVar);
            }
            return null;
        }
    };
    private final e b;

    h(e eVar) {
        this.b = eVar;
    }

    public Object b(com.google.gson.stream.a aVar) throws IOException {
        switch (aVar.f()) {
            case BEGIN_ARRAY:
                ArrayList arrayList = new ArrayList();
                aVar.a();
                while (aVar.e()) {
                    arrayList.add(b(aVar));
                }
                aVar.b();
                return arrayList;
            case BEGIN_OBJECT:
                LinkedTreeMap linkedTreeMap = new LinkedTreeMap();
                aVar.c();
                while (aVar.e()) {
                    linkedTreeMap.put(aVar.g(), b(aVar));
                }
                aVar.d();
                return linkedTreeMap;
            case STRING:
                return aVar.h();
            case NUMBER:
                return Double.valueOf(aVar.k());
            case BOOLEAN:
                return Boolean.valueOf(aVar.i());
            case NULL:
                aVar.j();
                return null;
            default:
                throw new IllegalStateException();
        }
    }

    public void a(b bVar, Object obj) throws IOException {
        if (obj == null) {
            bVar.f();
            return;
        }
        q a2 = this.b.a(obj.getClass());
        if (a2 instanceof h) {
            bVar.d();
            bVar.e();
            return;
        }
        a2.a(bVar, obj);
    }
}
