package com.google.android.gms.internal.icing;

import java.util.ListIterator;

final class zzfb implements ListIterator<String> {
    private final /* synthetic */ int val$index;
    private ListIterator<String> zzlq = this.zzlr.zzlp.listIterator(this.val$index);
    private final /* synthetic */ zzfa zzlr;

    zzfb(zzfa zzfa, int i) {
        this.zzlr = zzfa;
        this.val$index = i;
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final boolean hasNext() {
        return this.zzlq.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzlq.hasPrevious();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzlq.next();
    }

    public final int nextIndex() {
        return this.zzlq.nextIndex();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.zzlq.previous();
    }

    public final int previousIndex() {
        return this.zzlq.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }
}
