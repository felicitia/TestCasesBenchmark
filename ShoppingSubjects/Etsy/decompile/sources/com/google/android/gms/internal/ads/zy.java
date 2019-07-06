package com.google.android.gms.internal.ads;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zy extends AbstractList<String> implements xu, RandomAccess {
    /* access modifiers changed from: private */
    public final xu a;

    public zy(xu xuVar) {
        this.a = xuVar;
    }

    public final void a(zzbah zzbah) {
        throw new UnsupportedOperationException();
    }

    public final Object b(int i) {
        return this.a.b(i);
    }

    public final List<?> d() {
        return this.a.d();
    }

    public final xu e() {
        return this;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.a.get(i);
    }

    public final Iterator<String> iterator() {
        return new aaa(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zz(this, i);
    }

    public final int size() {
        return this.a.size();
    }
}
