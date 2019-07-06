package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class cv extends AbstractList<String> implements aw, RandomAccess {
    /* access modifiers changed from: private */
    public final aw a;

    public cv(aw awVar) {
        this.a = awVar;
    }

    public final Object b(int i) {
        return this.a.b(i);
    }

    public final List<?> d() {
        return this.a.d();
    }

    public final aw e() {
        return this;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.a.get(i);
    }

    public final Iterator<String> iterator() {
        return new cx(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new cw(this, i);
    }

    public final int size() {
        return this.a.size();
    }
}
