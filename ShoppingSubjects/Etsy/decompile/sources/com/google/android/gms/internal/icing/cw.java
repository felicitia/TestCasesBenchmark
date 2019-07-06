package com.google.android.gms.internal.icing;

import java.util.ListIterator;

final class cw implements ListIterator<String> {
    private ListIterator<String> a = this.c.a.listIterator(this.b);
    private final /* synthetic */ int b;
    private final /* synthetic */ cv c;

    cw(cv cvVar, int i) {
        this.c = cvVar;
        this.b = i;
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.a.hasNext();
    }

    public final boolean hasPrevious() {
        return this.a.hasPrevious();
    }

    public final /* synthetic */ Object next() {
        return (String) this.a.next();
    }

    public final int nextIndex() {
        return this.a.nextIndex();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.a.previous();
    }

    public final int previousIndex() {
        return this.a.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
