package com.google.android.gms.internal.firebase-perf;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzeo implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzpw;
    private final /* synthetic */ zzem zzpx;

    private zzeo(zzem zzem) {
        this.zzpx = zzem;
        this.pos = this.zzpx.zzpr.size();
    }

    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzpx.zzpr.size()) || zzfo().hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    private final Iterator<Entry<K, V>> zzfo() {
        if (this.zzpw == null) {
            this.zzpw = this.zzpx.zzpu.entrySet().iterator();
        }
        return this.zzpw;
    }

    public final /* synthetic */ Object next() {
        if (zzfo().hasNext()) {
            return (Entry) zzfo().next();
        }
        List zzb = this.zzpx.zzpr;
        int i = this.pos - 1;
        this.pos = i;
        return (Entry) zzb.get(i);
    }

    /* synthetic */ zzeo(zzem zzem, zzen zzen) {
        this(zzem);
    }
}
