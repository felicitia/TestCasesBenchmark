package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzcy<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zzjc;

    public zzcy(Iterator<Entry<K, Object>> it) {
        this.zzjc = it;
    }

    public final boolean hasNext() {
        return this.zzjc.hasNext();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zzjc.next();
        return entry.getValue() instanceof zzcv ? new zzcx(entry) : entry;
    }

    public final void remove() {
        this.zzjc.remove();
    }
}
