package com.google.android.gms.internal.measurement;

import java.util.Iterator;

final class aj implements Iterator<String> {
    private Iterator<String> a = this.b.zzaho.keySet().iterator();
    private final /* synthetic */ zzeu b;

    aj(zzeu zzeu) {
        this.b = zzeu;
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.a.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException("Remove not supported");
    }
}
