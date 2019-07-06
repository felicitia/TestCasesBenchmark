package com.google.android.gms.dynamic;

import java.util.Iterator;

final class d implements c<T> {
    private final /* synthetic */ a a;

    d(a aVar) {
        this.a = aVar;
    }

    public final void a(T t) {
        this.a.a = t;
        Iterator it = this.a.c.iterator();
        while (it.hasNext()) {
            ((C0137a) it.next()).a(this.a.a);
        }
        this.a.c.clear();
        this.a.b = null;
    }
}
