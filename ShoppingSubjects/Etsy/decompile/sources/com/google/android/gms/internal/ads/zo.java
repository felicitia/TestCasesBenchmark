package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zo implements Iterator<Entry<K, V>> {
    private int a;
    private boolean b;
    private Iterator<Entry<K, V>> c;
    private final /* synthetic */ zg d;

    private zo(zg zgVar) {
        this.d = zgVar;
        this.a = -1;
    }

    /* synthetic */ zo(zg zgVar, zh zhVar) {
        this(zgVar);
    }

    private final Iterator<Entry<K, V>> a() {
        if (this.c == null) {
            this.c = this.d.c.entrySet().iterator();
        }
        return this.c;
    }

    public final boolean hasNext() {
        if (this.a + 1 >= this.d.b.size()) {
            return !this.d.c.isEmpty() && a().hasNext();
        }
        return true;
    }

    public final /* synthetic */ Object next() {
        this.b = true;
        int i = this.a + 1;
        this.a = i;
        return (Entry) (i < this.d.b.size() ? this.d.b.get(this.a) : a().next());
    }

    public final void remove() {
        if (!this.b) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.b = false;
        this.d.f();
        if (this.a < this.d.b.size()) {
            zg zgVar = this.d;
            int i = this.a;
            this.a = i - 1;
            zgVar.c(i);
            return;
        }
        a().remove();
    }
}
