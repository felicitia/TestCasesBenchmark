package com.google.android.gms.internal.ads;

import java.util.Iterator;

final class aaa implements Iterator<String> {
    private Iterator<String> a = this.b.a.iterator();
    private final /* synthetic */ zy b;

    aaa(zy zyVar) {
        this.b = zyVar;
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
