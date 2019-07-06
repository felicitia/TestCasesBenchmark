package com.google.gson;

import com.google.gson.internal.a.f;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.a;
import com.google.gson.stream.b;
import java.io.IOException;

/* compiled from: TypeAdapter */
public abstract class q<T> {
    public abstract void a(b bVar, T t) throws IOException;

    public abstract T b(a aVar) throws IOException;

    public final q<T> a() {
        return new q<T>() {
            public void a(b bVar, T t) throws IOException {
                if (t == null) {
                    bVar.f();
                } else {
                    q.this.a(bVar, t);
                }
            }

            public T b(a aVar) throws IOException {
                if (aVar.f() != JsonToken.NULL) {
                    return q.this.b(aVar);
                }
                aVar.j();
                return null;
            }
        };
    }

    public final k a(T t) {
        try {
            f fVar = new f();
            a(fVar, t);
            return fVar.a();
        } catch (IOException e) {
            throw new JsonIOException((Throwable) e);
        }
    }
}
