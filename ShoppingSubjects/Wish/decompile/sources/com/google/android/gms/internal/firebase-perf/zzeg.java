package com.google.android.gms.internal.firebase-perf;

import java.util.ArrayList;
import java.util.List;

final class zzeg<E> extends zzaz<E> {
    private static final zzeg<Object> zzpl;
    private final List<E> zznx;

    public static <E> zzeg<E> zzfb() {
        return zzpl;
    }

    zzeg() {
        this(new ArrayList(10));
    }

    private zzeg(List<E> list) {
        this.zznx = list;
    }

    public final void add(int i, E e) {
        zzbj();
        this.zznx.add(i, e);
        this.modCount++;
    }

    public final E get(int i) {
        return this.zznx.get(i);
    }

    public final E remove(int i) {
        zzbj();
        E remove = this.zznx.remove(i);
        this.modCount++;
        return remove;
    }

    public final E set(int i, E e) {
        zzbj();
        E e2 = this.zznx.set(i, e);
        this.modCount++;
        return e2;
    }

    public final int size() {
        return this.zznx.size();
    }

    public final /* synthetic */ zzcs zzi(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zznx);
        return new zzeg(arrayList);
    }

    static {
        zzeg<Object> zzeg = new zzeg<>();
        zzpl = zzeg;
        zzeg.zzbi();
    }
}
