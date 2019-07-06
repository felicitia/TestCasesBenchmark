package com.google.firebase.components;

import com.google.firebase.b.a;

final class m<T> implements a<T> {
    private static final Object a = new Object();
    private volatile Object b = a;
    private volatile a<T> c;

    m(d<T> dVar, b bVar) {
        this.c = new n(dVar, bVar);
    }

    public final T a() {
        T t = this.b;
        if (t == a) {
            synchronized (this) {
                t = this.b;
                if (t == a) {
                    t = this.c.a();
                    this.b = t;
                    this.c = null;
                }
            }
        }
        return t;
    }
}
