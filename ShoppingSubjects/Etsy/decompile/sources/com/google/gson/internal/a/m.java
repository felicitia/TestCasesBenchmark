package com.google.gson.internal.a;

import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.stream.a;
import com.google.gson.stream.b;
import java.io.IOException;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

/* compiled from: TypeAdapterRuntimeTypeWrapper */
final class m<T> extends q<T> {
    private final e a;
    private final q<T> b;
    private final Type c;

    m(e eVar, q<T> qVar, Type type) {
        this.a = eVar;
        this.b = qVar;
        this.c = type;
    }

    public T b(a aVar) throws IOException {
        return this.b.b(aVar);
    }

    public void a(b bVar, T t) throws IOException {
        q<T> qVar = this.b;
        Type a2 = a(this.c, (Object) t);
        if (a2 != this.c) {
            qVar = this.a.a(com.google.gson.a.a.a(a2));
            if ((qVar instanceof i.a) && !(this.b instanceof i.a)) {
                qVar = this.b;
            }
        }
        qVar.a(bVar, t);
    }

    private Type a(Type type, Object obj) {
        if (obj != null) {
            return (type == Object.class || (type instanceof TypeVariable) || (type instanceof Class)) ? obj.getClass() : type;
        }
        return type;
    }
}
