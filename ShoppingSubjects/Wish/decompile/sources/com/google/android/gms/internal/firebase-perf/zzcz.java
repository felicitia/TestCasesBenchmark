package com.google.android.gms.internal.firebase-perf;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzcz<K> implements Iterator<Entry<K, Object>> {
    private Iterator<Entry<K, Object>> zznr;

    public zzcz(Iterator<Entry<K, Object>> it) {
        this.zznr = it;
    }

    public final boolean hasNext() {
        return this.zznr.hasNext();
    }

    public final void remove() {
        this.zznr.remove();
    }

    public final /* synthetic */ Object next() {
        Entry entry = (Entry) this.zznr.next();
        return entry.getValue() instanceof zzcw ? new zzcy(entry) : entry;
    }
}
