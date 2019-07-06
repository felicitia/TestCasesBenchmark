package com.google.android.gms.internal.icing;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class cm extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ cd a;

    private cm(cd cdVar) {
        this.a = cdVar;
    }

    /* synthetic */ cm(cd cdVar, ce ceVar) {
        this(cdVar);
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.a.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        this.a.clear();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.a.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public Iterator<Entry<K, V>> iterator() {
        return new cl(this.a, null);
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.a.remove(entry.getKey());
        return true;
    }

    public int size() {
        return this.a.size();
    }
}
