package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.List;

final class by<E> extends h<E> {
    private static final by<Object> a;
    private final List<E> b;

    static {
        by<Object> byVar = new by<>();
        a = byVar;
        byVar.b();
    }

    by() {
        this(new ArrayList(10));
    }

    private by(List<E> list) {
        this.b = list;
    }

    public static <E> by<E> d() {
        return a;
    }

    public final /* synthetic */ ao a(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.b);
        return new by(arrayList);
    }

    public final void add(int i, E e) {
        c();
        this.b.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.b.get(i);
    }

    public final E remove(int i) {
        c();
        E remove = this.b.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        c();
        E e2 = this.b.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.b.size();
    }
}
