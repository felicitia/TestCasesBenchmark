package com.google.gson.internal;

import com.google.gson.a.a;
import com.google.gson.annotations.d;
import com.google.gson.b;
import com.google.gson.e;
import com.google.gson.q;
import com.google.gson.r;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* compiled from: Excluder */
public final class c implements r, Cloneable {
    public static final c a = new c();
    private double b = -1.0d;
    private int c = 136;
    private boolean d = true;
    private boolean e;
    private List<b> f = Collections.emptyList();
    private List<b> g = Collections.emptyList();

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public c clone() {
        try {
            return (c) super.clone();
        } catch (CloneNotSupportedException e2) {
            throw new AssertionError(e2);
        }
    }

    public <T> q<T> a(e eVar, a<T> aVar) {
        Class a2 = aVar.a();
        final boolean a3 = a(a2, true);
        final boolean a4 = a(a2, false);
        if (!a3 && !a4) {
            return null;
        }
        final e eVar2 = eVar;
        final a<T> aVar2 = aVar;
        AnonymousClass1 r2 = new q<T>() {
            private q<T> f;

            public T b(com.google.gson.stream.a aVar) throws IOException {
                if (!a4) {
                    return b().b(aVar);
                }
                aVar.n();
                return null;
            }

            public void a(com.google.gson.stream.b bVar, T t) throws IOException {
                if (a3) {
                    bVar.f();
                } else {
                    b().a(bVar, t);
                }
            }

            private q<T> b() {
                q<T> qVar = this.f;
                if (qVar != null) {
                    return qVar;
                }
                q<T> a2 = eVar2.a(c.this, aVar2);
                this.f = a2;
                return a2;
            }
        };
        return r2;
    }

    public boolean a(Field field, boolean z) {
        if ((this.c & field.getModifiers()) != 0) {
            return true;
        }
        if ((this.b != -1.0d && !a((com.google.gson.annotations.c) field.getAnnotation(com.google.gson.annotations.c.class), (d) field.getAnnotation(d.class))) || field.isSynthetic()) {
            return true;
        }
        if (this.e) {
            com.google.gson.annotations.a aVar = (com.google.gson.annotations.a) field.getAnnotation(com.google.gson.annotations.a.class);
            if (aVar == null || (!z ? !aVar.b() : !aVar.a())) {
                return true;
            }
        }
        if ((!this.d && b(field.getType())) || a(field.getType())) {
            return true;
        }
        List<b> list = z ? this.f : this.g;
        if (!list.isEmpty()) {
            com.google.gson.c cVar = new com.google.gson.c(field);
            for (b a2 : list) {
                if (a2.a(cVar)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean a(Class<?> cls, boolean z) {
        if (this.b != -1.0d && !a((com.google.gson.annotations.c) cls.getAnnotation(com.google.gson.annotations.c.class), (d) cls.getAnnotation(d.class))) {
            return true;
        }
        if ((!this.d && b(cls)) || a(cls)) {
            return true;
        }
        for (b a2 : z ? this.f : this.g) {
            if (a2.a(cls)) {
                return true;
            }
        }
        return false;
    }

    private boolean a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    private boolean b(Class<?> cls) {
        return cls.isMemberClass() && !c(cls);
    }

    private boolean c(Class<?> cls) {
        return (cls.getModifiers() & 8) != 0;
    }

    private boolean a(com.google.gson.annotations.c cVar, d dVar) {
        return a(cVar) && a(dVar);
    }

    private boolean a(com.google.gson.annotations.c cVar) {
        return cVar == null || cVar.a() <= this.b;
    }

    private boolean a(d dVar) {
        return dVar == null || dVar.a() > this.b;
    }
}
