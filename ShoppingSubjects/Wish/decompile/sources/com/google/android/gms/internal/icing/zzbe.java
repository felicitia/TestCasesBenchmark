package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;

abstract class zzbe<E> extends AbstractList<E> implements zzcr<E> {
    private boolean zzdl = true;

    zzbe() {
    }

    public void add(int i, E e) {
        zzq();
        super.add(i, e);
    }

    public boolean add(E e) {
        zzq();
        return super.add(e);
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        zzq();
        return super.addAll(i, collection);
    }

    public boolean addAll(Collection<? extends E> collection) {
        zzq();
        return super.addAll(collection);
    }

    public void clear() {
        zzq();
        super.clear();
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

    public E remove(int i) {
        zzq();
        return super.remove(i);
    }

    public boolean remove(Object obj) {
        zzq();
        return super.remove(obj);
    }

    public boolean removeAll(Collection<?> collection) {
        zzq();
        return super.removeAll(collection);
    }

    public boolean retainAll(Collection<?> collection) {
        zzq();
        return super.retainAll(collection);
    }

    public E set(int i, E e) {
        zzq();
        return super.set(i, e);
    }

    public boolean zzo() {
        return this.zzdl;
    }

    public final void zzp() {
        this.zzdl = false;
    }

    /* access modifiers changed from: protected */
    public final void zzq() {
        if (!this.zzdl) {
            throw new UnsupportedOperationException();
        }
    }
}
