package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.List;

final class yy<E> extends wa<E> {
    private static final yy<Object> a;
    private final List<E> b;

    static {
        yy<Object> yyVar = new yy<>();
        a = yyVar;
        yyVar.b();
    }

    yy() {
        this(new ArrayList(10));
    }

    private yy(List<E> list) {
        this.b = list;
    }

    public static <E> yy<E> d() {
        return a;
    }

    public final /* synthetic */ xm a(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.b);
        return new yy(arrayList);
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
