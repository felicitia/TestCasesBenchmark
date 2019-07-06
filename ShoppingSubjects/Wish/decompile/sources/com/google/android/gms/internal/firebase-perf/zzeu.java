package com.google.android.gms.internal.firebase-perf;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzeu implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzpw;
    private final /* synthetic */ zzem zzpx;
    private boolean zzqb;

    private zzeu(zzem zzem) {
        this.zzpx = zzem;
        this.pos = -1;
    }

    public final boolean hasNext() {
        if (this.pos + 1 < this.zzpx.zzpr.size() || (!this.zzpx.zzps.isEmpty() && zzfo().hasNext())) {
            return true;
        }
        return false;
    }

    public final void remove() {
        if (!this.zzqb) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzqb = false;
        this.zzpx.zzfm();
        if (this.pos < this.zzpx.zzpr.size()) {
            zzem zzem = this.zzpx;
            int i = this.pos;
            this.pos = i - 1;
            zzem.zzau(i);
            return;
        }
        zzfo().remove();
    }

    private final Iterator<Entry<K, V>> zzfo() {
        if (this.zzpw == null) {
            this.zzpw = this.zzpx.zzps.entrySet().iterator();
        }
        return this.zzpw;
    }

    public final /* synthetic */ Object next() {
        this.zzqb = true;
        int i = this.pos + 1;
        this.pos = i;
        if (i < this.zzpx.zzpr.size()) {
            return (Entry) this.zzpx.zzpr.get(this.pos);
        }
        return (Entry) zzfo().next();
    }

    /* synthetic */ zzeu(zzem zzem, zzen zzen) {
        this(zzem);
    }
}
