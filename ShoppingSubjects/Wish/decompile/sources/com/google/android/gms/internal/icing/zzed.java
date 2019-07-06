package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.List;

final class zzed<E> extends zzbe<E> {
    private static final zzed<Object> zzku;
    private final List<E> zzji;

    static {
        zzed<Object> zzed = new zzed<>();
        zzku = zzed;
        zzed.zzp();
    }

    zzed() {
        this(new ArrayList(10));
    }

    private zzed(List<E> list) {
        this.zzji = list;
    }

    public static <E> zzed<E> zzca() {
        return zzku;
    }

    public final void add(int i, E e) {
        zzq();
        this.zzji.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zzji.get(i);
    }

    public final E remove(int i) {
        zzq();
        E remove = this.zzji.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzq();
        E e2 = this.zzji.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zzji.size();
    }

    public final /* synthetic */ zzcr zzh(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzji);
        return new zzed(arrayList);
    }
}
