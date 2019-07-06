package com.google.android.gms.internal.icing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

public final class av extends h<String> implements aw, RandomAccess {
    private static final av a;
    private static final aw b = a;
    private final List<Object> c;

    static {
        av avVar = new av();
        a = avVar;
        avVar.b();
    }

    public av() {
        this(10);
    }

    public av(int i) {
        this(new ArrayList<>(i));
    }

    private av(ArrayList<Object> arrayList) {
        this.c = arrayList;
    }

    private static String a(Object obj) {
        return obj instanceof String ? (String) obj : obj instanceof zzbi ? ((zzbi) obj).zzu() : aj.b((byte[]) obj);
    }

    public final /* synthetic */ ao a(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.c);
        return new av(arrayList);
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
        if (collection instanceof aw) {
            collection = ((aw) collection).d();
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

    public final aw e() {
        return a() ? new cv(this) : this;
    }

    public final /* bridge */ /* synthetic */ boolean equals(Object obj) {
        return super.equals(obj);
    }

    public final /* synthetic */ Object get(int i) {
        Object obj = this.c.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzbi) {
            zzbi zzbi = (zzbi) obj;
            String zzu = zzbi.zzu();
            if (zzbi.zzv()) {
                this.c.set(i, zzu);
            }
            return zzu;
        }
        byte[] bArr = (byte[]) obj;
        String b2 = aj.b(bArr);
        if (aj.a(bArr)) {
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
