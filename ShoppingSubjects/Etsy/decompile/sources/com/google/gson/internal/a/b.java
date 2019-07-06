package com.google.gson.internal.a;

import com.google.gson.internal.C$Gson$Types;
import com.google.gson.internal.e;
import com.google.gson.q;
import com.google.gson.r;
import com.google.gson.stream.JsonToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

/* compiled from: CollectionTypeAdapterFactory */
public final class b implements r {
    private final com.google.gson.internal.b a;

    /* compiled from: CollectionTypeAdapterFactory */
    private static final class a<E> extends q<Collection<E>> {
        private final q<E> a;
        private final e<? extends Collection<E>> b;

        public a(com.google.gson.e eVar, Type type, q<E> qVar, e<? extends Collection<E>> eVar2) {
            this.a = new m(eVar, qVar, type);
            this.b = eVar2;
        }

        /* renamed from: a */
        public Collection<E> b(com.google.gson.stream.a aVar) throws IOException {
            if (aVar.f() == JsonToken.NULL) {
                aVar.j();
                return null;
            }
            Collection<E> collection = (Collection) this.b.a();
            aVar.a();
            while (aVar.e()) {
                collection.add(this.a.b(aVar));
            }
            aVar.b();
            return collection;
        }

        public void a(com.google.gson.stream.b bVar, Collection<E> collection) throws IOException {
            if (collection == null) {
                bVar.f();
                return;
            }
            bVar.b();
            for (E a2 : collection) {
                this.a.a(bVar, a2);
            }
            bVar.c();
        }
    }

    public b(com.google.gson.internal.b bVar) {
        this.a = bVar;
    }

    public <T> q<T> a(com.google.gson.e eVar, com.google.gson.a.a<T> aVar) {
        Type b = aVar.b();
        Class a2 = aVar.a();
        if (!Collection.class.isAssignableFrom(a2)) {
            return null;
        }
        Type a3 = C$Gson$Types.a(b, a2);
        return new a(eVar, a3, eVar.a(com.google.gson.a.a.a(a3)), this.a.a(aVar));
    }
}
