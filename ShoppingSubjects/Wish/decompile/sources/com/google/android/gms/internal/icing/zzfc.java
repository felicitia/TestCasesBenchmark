package com.google.android.gms.internal.icing;

import java.util.Iterator;

final class zzfc implements Iterator<String> {
    private final /* synthetic */ zzfa zzlr;
    private Iterator<String> zzls = this.zzlr.zzlp.iterator();

    zzfc(zzfa zzfa) {
        this.zzlr = zzfa;
    }

    public final boolean hasNext() {
        return this.zzls.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzls.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
