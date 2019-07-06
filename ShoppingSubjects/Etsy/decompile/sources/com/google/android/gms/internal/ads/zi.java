package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zi implements Iterator<Entry<K, V>> {
    private int a;
    private Iterator<Entry<K, V>> b;
    private final /* synthetic */ zg c;

    private zi(zg zgVar) {
        this.c = zgVar;
        this.a = this.c.b.size();
    }

    /* synthetic */ zi(zg zgVar, zh zhVar) {
        this(zgVar);
    }

    private final Iterator<Entry<K, V>> a() {
        if (this.b == null) {
            this.b = this.c.f.entrySet().iterator();
        }
        return this.b;
    }

    public final boolean hasNext() {
        return (this.a > 0 && this.a <= this.c.b.size()) || a().hasNext();
    }

    public final /* synthetic */ Object next() {
        Object obj;
        if (a().hasNext()) {
            obj = a().next();
        } else {
            List b2 = this.c.b;
            int i = this.a - 1;
            this.a = i;
            obj = b2.get(i);
        }
        return (Entry) obj;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
