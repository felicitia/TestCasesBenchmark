package com.google.firebase.components;

import com.google.firebase.a.c;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class o implements b {
    private final Set<Class<?>> a;
    private final Set<Class<?>> b;
    private final Set<Class<?>> c;
    private final b d;

    static class a implements c {
        private final Set<Class<?>> a;
        private final c b;

        public a(Set<Class<?>> set, c cVar) {
            this.a = set;
            this.b = cVar;
        }
    }

    o(a<?> aVar, b bVar) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        for (f fVar : aVar.b()) {
            if (fVar.c()) {
                hashSet.add(fVar.a());
            } else {
                hashSet2.add(fVar.a());
            }
        }
        if (!aVar.d().isEmpty()) {
            hashSet.add(c.class);
        }
        this.a = Collections.unmodifiableSet(hashSet);
        this.b = Collections.unmodifiableSet(hashSet2);
        this.c = aVar.d();
        this.d = bVar;
    }

    public final <T> T a(Class<T> cls) {
        if (!this.a.contains(cls)) {
            throw new IllegalArgumentException(String.format("Requesting %s is not allowed.", new Object[]{cls}));
        }
        T a2 = this.d.a(cls);
        if (!cls.equals(c.class)) {
            return a2;
        }
        return new a(this.c, (c) a2);
    }

    public final <T> com.google.firebase.b.a<T> b(Class<T> cls) {
        if (this.b.contains(cls)) {
            return this.d.b(cls);
        }
        throw new IllegalArgumentException(String.format("Requesting Provider<%s> is not allowed.", new Object[]{cls}));
    }
}
