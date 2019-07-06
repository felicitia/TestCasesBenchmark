package com.google.android.gms.internal.firebase-perf;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzev extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzem zzpx;

    private zzev(zzem zzem) {
        this.zzpx = zzem;
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzeu(this.zzpx, null);
    }

    public int size() {
        return this.zzpx.size();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzpx.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzpx.remove(entry.getKey());
        return true;
    }

    public void clear() {
        this.zzpx.clear();
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzpx.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    /* synthetic */ zzev(zzem zzem, zzen zzen) {
        this(zzem);
    }
}
