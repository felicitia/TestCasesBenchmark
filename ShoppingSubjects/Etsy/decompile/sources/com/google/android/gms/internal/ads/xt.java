package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class xt extends wa<String> implements xu, RandomAccess {
    private static final xt a;
    private static final xu b = a;
    private final List<Object> c;

    static {
        xt xtVar = new xt();
        a = xtVar;
        xtVar.b();
    }

    public xt() {
        this(10);
    }

    public xt(int i) {
        this(new ArrayList<>(i));
    }

    private xt(ArrayList<Object> arrayList) {
        this.c = arrayList;
    }

    private static String a(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbah ? ((zzbah) obj).zzabd() : xj.b((byte[]) obj);
    }

    public final /* synthetic */ xm a(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.c);
        return new xt(arrayList);
    }

    public final void a(zzbah zzbah) {
        c();
        this.c.add(zzbah);
        this.modCount++;
    }

    public final /* bridge */ /* synthetic */ boolean a() {
        return super.a();
    }

    public final /* synthetic */ void add(int i, Object obj) {
        String str = (String) obj;
        c();
        this.c.add(i, str);
        this.modCount++;
    }

    public final boolean addAll(int i, Collection<? extends String> collection) {
        c();
        if (collection instanceof xu) {
            collection = ((xu) collection).d();
        }
        boolean addAll = this.c.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    public final boolean addAll(Collection<? extends String> collection) {
        return addAll(size(), collection);
    }

    public final Object b(int i) {
        return this.c.get(i);
    }

    public final void clear() {
        c();
        this.c.clear();
        this.modCount++;
    }

    public final List<?> d() {
        return Collections.unmodifiableList(this.c);
    }

    public final xu e() {
        return a() ? new zy(this) : this;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.c.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbah) {
            zzbah zzbah = (zzbah) obj;
            String zzabd = zzbah.zzabd();
            if (zzbah.zzabe()) {
                this.c.set(i, zzabd);
            }
            return zzabd;
        }
        byte[] bArr = (byte[]) obj;
        String b2 = xj.b(bArr);
        if (xj.a(bArr)) {
            this.c.set(i, b2);
        }
        return b2;
    }

    public final /* bridge */ /* synthetic */ int hashCode() {
        return super.hashCode();
    }

    public final /* synthetic */ Object remove(int i) {
        c();
        Object remove = this.c.remove(i);
        this.modCount++;
        return a(remove);
    }

    public final /* bridge */ /* synthetic */ boolean remove(Object obj) {
        return super.remove(obj);
    }

    public final /* bridge */ /* synthetic */ boolean removeAll(Collection collection) {
        return super.removeAll(collection);
    }

    public final /* bridge */ /* synthetic */ boolean retainAll(Collection collection) {
        return super.retainAll(collection);
    }

    public final /* synthetic */ Object set(int i, Object obj) {
        String str = (String) obj;
        c();
        return a(this.c.set(i, str));
    }

    public final int size() {
        return this.c.size();
    }
}
