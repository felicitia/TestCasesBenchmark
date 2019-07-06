package com.google.android.gms.internal.icing;

import java.util.Iterator;

final class cx implements Iterator<String> {
    private Iterator<String> a = this.b.a.iterator();
    private final /* synthetic */ cv b;

    cx(cv cvVar) {
        this.b = cvVar;
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final /* synthetic */ Object next() {
        return (String) this.a.next();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
