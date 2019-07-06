package com.google.android.gms.internal.firebase-perf;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzaz<E> extends AbstractList<E> implements zzcs<E> {
    private boolean zzhj = true;

    zzaz() {
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        if (!(obj instanceof RandomAccess)) {
            return super.equals(obj);
        }
        List list = (List) obj;
        int size = size();
        if (size != list.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!get(i).equals(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 1;
        for (int i2 = 0; i2 < size(); i2++) {
            i = (i * 31) + get(i2).hashCode();
        }
        return i;
    }

    public boolean add(E e) {
        zzbj();
        return super.add(e);
    }

    public void add(int i, E e) {
        zzbj();
        super.add(i, e);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzbj();
        return super.addAll(collection);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzbj();
        return super.addAll(i, collection);
    }

    public void clear() {
        zzbj();
        super.clear();
    }

    public boolean zzbh() {
        return this.zzhj;
    }

    public final void zzbi() {
        this.zzhj = false;
    }

    public E remove(int i) {
        zzbj();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzbj();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzbj();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzbj();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzbj();
        return super.set(i, e);
    }

    /* access modifiers changed from: protected */
    public final void zzbj() {
        if (!this.zzhj) {
            throw new UnsupportedOperationException();
        }
    }
}
