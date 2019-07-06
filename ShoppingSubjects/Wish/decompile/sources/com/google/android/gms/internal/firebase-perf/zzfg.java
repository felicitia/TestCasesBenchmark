package com.google.android.gms.internal.firebase-perf;

import java.util.Iterator;

final class zzfg implements Iterator<String> {
    private final /* synthetic */ zzfe zzqj;
    private Iterator<String> zzqk = this.zzqj.zzqg.iterator();

    zzfg(zzfe zzfe) {
        this.zzqj = zzfe;
    }

    public final boolean hasNext() {
        return this.zzqk.hasNext();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzqk.next();
    }
}
