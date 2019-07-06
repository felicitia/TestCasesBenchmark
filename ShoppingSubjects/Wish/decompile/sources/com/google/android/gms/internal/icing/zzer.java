package com.google.android.gms.internal.icing;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.Map.Entry;

class zzer extends AbstractSet<Entry<K, V>> {
    private final /* synthetic */ zzei zzlg;

    private zzer(zzei zzei) {
        this.zzlg = zzei;
    }

    /* synthetic */ zzer(zzei zzei, zzej zzej) {
        this(zzei);
    }

    public /* synthetic */ boolean add(Object obj) {
        Entry entry = (Entry) obj;
        if (contains(entry)) {
            return false;
        }
        this.zzlg.put((Comparable) entry.getKey(), entry.getValue());
        return true;
    }

    public void clear() {
        this.zzlg.clear();
    }

    public boolean contains(Object obj) {
        Entry entry = (Entry) obj;
        Object obj2 = this.zzlg.get(entry.getKey());
        Object value = entry.getValue();
        return obj2 == value || (obj2 != null && obj2.equals(value));
    }

    public Iterator<Entry<K, V>> iterator() {
        return new zzeq(this.zzlg, null);
    }

    public boolean remove(Object obj) {
        Entry entry = (Entry) obj;
        if (!contains(entry)) {
            return false;
        }
        this.zzlg.remove(entry.getKey());
        return true;
    }

    public int size() {
        return this.zzlg.size();
    }
}
