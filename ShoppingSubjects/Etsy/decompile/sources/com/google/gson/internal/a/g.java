package com.google.gson.internal.a;

import com.google.gson.JsonSyntaxException;
import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.b;
import com.google.gson.internal.d;
import com.google.gson.internal.e;
import com.google.gson.k;
import com.google.gson.n;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: MapTypeAdapterFactory */
public final class g implements r {
    final boolean a;
    private final b b;

    /* compiled from: MapTypeAdapterFactory */
    private final class a<K, V> extends q<Map<K, V>> {
        private final q<K> b;
        private final q<V> c;
        private final e<? extends Map<K, V>> d;

        public a(com.google.gson.e eVar, Type type, q<K> qVar, Type type2, q<V> qVar2, e<? extends Map<K, V>> eVar2) {
            this.b = new m(eVar, qVar, type);
            this.c = new m(eVar, qVar2, type2);
            this.d = eVar2;
        }

        /* renamed from: a */
        public Map<K, V> b(com.google.gson.stream.a aVar) throws IOException {
            JsonToken f = aVar.f();
            if (f == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            Map<K, V> map = (Map) this.d.a();
            if (f == JsonToken.BEGIN_ARRAY) {
                aVar.a();
                while (aVar.e()) {
                    aVar.a();
                    Object b2 = this.b.b(aVar);
                    if (map.put(b2, this.c.b(aVar)) != null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("duplicate key: ");
                        sb.append(b2);
                        throw new JsonSyntaxException(sb.toString());
                    }
                    aVar.b();
                }
                aVar.b();
            } else {
                aVar.c();
                while (aVar.e()) {
                    d.a.a(aVar);
                    Object b3 = this.b.b(aVar);
                    if (map.put(b3, this.c.b(aVar)) != null) {
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("duplicate key: ");
                        sb2.append(b3);
                        throw new JsonSyntaxException(sb2.toString());
                    }
                }
                aVar.d();
            }
            return map;
        }

        public void a(com.google.gson.stream.b bVar, Map<K, V> map) throws IOException {
            if (map == null) {
                bVar.f();
            } else if (!g.this.a) {
                bVar.d();
                for (Entry entry : map.entrySet()) {
                    bVar.a(String.valueOf(entry.getKey()));
                    this.c.a(bVar, entry.getValue());
                }
                bVar.e();
            } else {
                ArrayList arrayList = new ArrayList(map.size());
                ArrayList arrayList2 = new ArrayList(map.size());
                int i = 0;
                boolean z = false;
                for (Entry entry2 : map.entrySet()) {
                    k a2 = this.b.a(entry2.getKey());
                    arrayList.add(a2);
                    arrayList2.add(entry2.getValue());
                    z |= a2.g() || a2.h();
                }
                if (z) {
                    bVar.b();
                    int size = arrayList.size();
                    while (i < size) {
                        bVar.b();
                        com.google.gson.internal.g.a((k) arrayList.get(i), bVar);
                        this.c.a(bVar, arrayList2.get(i));
                        bVar.c();
                        i++;
                    }
                    bVar.c();
                } else {
                    bVar.d();
                    int size2 = arrayList.size();
                    while (i < size2) {
                        bVar.a(a((k) arrayList.get(i)));
                        this.c.a(bVar, arrayList2.get(i));
                        i++;
                    }
                    bVar.e();
                }
            }
        }

        private String a(k kVar) {
            if (kVar.i()) {
                n m = kVar.m();
                if (m.p()) {
                    return String.valueOf(m.a());
                }
                if (m.o()) {
                    return Boolean.toString(m.f());
                }
                if (m.q()) {
                    return m.b();
                }
                throw new AssertionError();
            } else if (kVar.j()) {
                return "null";
            } else {
                throw new AssertionError();
            }
        }
    }

    public g(b bVar, boolean z) {
        this.b = bVar;
        this.a = z;
    }

    public <T> q<T> a(com.google.gson.e eVar, com.google.gson.a.a<T> aVar) {
        Type b2 = aVar.b();
        if (!Map.class.isAssignableFrom(aVar.a())) {
            return null;
        }
        Type[] b3 = C$Gson$Types.b(b2, C$Gson$Types.e(b2));
        com.google.gson.e eVar2 = eVar;
        a aVar2 = new a(eVar2, b3[0], a(eVar, b3[0]), b3[1], eVar.a(com.google.gson.a.a.a(b3[1])), this.b.a(aVar));
        return aVar2;
    }

    private q<?> a(com.google.gson.e eVar, Type type) {
        if (type == Boolean.TYPE || type == Boolean.class) {
            return n.f;
        }
        return eVar.a(com.google.gson.a.a.a(type));
    }
}
