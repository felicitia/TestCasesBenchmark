package com.google.gson.internal.a;

import com.google.gson.e;
import com.google.gson.i;
import com.google.gson.internal.g;
import com.google.gson.j;
import com.google.gson.k;
import com.google.gson.o;
import com.google.gson.p;
import com.google.gson.q;
import com.google.gson.r;
import java.io.IOException;

/* compiled from: TreeTypeAdapter */
public final class l<T> extends q<T> {
    final e a;
    private final p<T> b;
    private final j<T> c;
    private final com.google.gson.a.a<T> d;
    private final r e;
    private final a f = new a<>();
    private q<T> g;

    /* compiled from: TreeTypeAdapter */
    private final class a implements i, o {
        private a() {
        }
    }

    /* compiled from: TreeTypeAdapter */
    private static final class b implements r {
        private final com.google.gson.a.a<?> a;
        private final boolean b;
        private final Class<?> c;
        private final p<?> d;
        private final j<?> e;

        b(Object obj, com.google.gson.a.a<?> aVar, boolean z, Class<?> cls) {
            j<?> jVar = null;
            this.d = obj instanceof p ? (p) obj : null;
            if (obj instanceof j) {
                jVar = (j) obj;
            }
            this.e = jVar;
            com.google.gson.internal.a.a((this.d == null && this.e == null) ? false : true);
            this.a = aVar;
            this.b = z;
            this.c = cls;
        }

        public <T> q<T> a(e eVar, com.google.gson.a.a<T> aVar) {
            boolean z = this.a != null ? this.a.equals(aVar) || (this.b && this.a.b() == aVar.a()) : this.c.isAssignableFrom(aVar.a());
            if (!z) {
                return null;
            }
            l lVar = new l(this.d, this.e, eVar, aVar, this);
            return lVar;
        }
    }

    public l(p<T> pVar, j<T> jVar, e eVar, com.google.gson.a.a<T> aVar, r rVar) {
        this.b = pVar;
        this.c = jVar;
        this.a = eVar;
        this.d = aVar;
        this.e = rVar;
    }

    public T b(com.google.gson.stream.a aVar) throws IOException {
        if (this.c == null) {
            return b().b(aVar);
        }
        k a2 = g.a(aVar);
        if (a2.j()) {
            return null;
        }
        return this.c.a(a2, this.d.b(), this.f);
    }

    public void a(com.google.gson.stream.b bVar, T t) throws IOException {
        if (this.b == null) {
            b().a(bVar, t);
        } else if (t == null) {
            bVar.f();
        } else {
            g.a(this.b.a(t, this.d.b(), this.f), bVar);
        }
    }

    private q<T> b() {
        q<T> qVar = this.g;
        if (qVar != null) {
            return qVar;
        }
        q<T> a2 = this.a.a(this.e, this.d);
        this.g = a2;
        return a2;
    }

    public static r a(com.google.gson.a.a<?> aVar, Object obj) {
        return new b(obj, aVar, aVar.b() == aVar.a(), null);
    }
}
