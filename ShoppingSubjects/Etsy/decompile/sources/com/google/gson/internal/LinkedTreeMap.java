package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Comparable> a = new Comparator<Comparable>() {
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> comparator;
    private a entrySet;
    final d<K, V> header;
    private b keySet;
    int modCount;
    d<K, V> root;
    int size;

    class a extends AbstractSet<Entry<K, V>> {
        a() {
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new c<Entry<K, V>>() {
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                /* renamed from: a */
                public Entry<K, V> next() {
                    return b();
                }
            };
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && LinkedTreeMap.this.findByEntry((Entry) obj) != null;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            d findByEntry = LinkedTreeMap.this.findByEntry((Entry) obj);
            if (findByEntry == null) {
                return false;
            }
            LinkedTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    final class b extends AbstractSet<K> {
        b() {
        }

        public int size() {
            return LinkedTreeMap.this.size;
        }

        public Iterator<K> iterator() {
            return new c<K>() {
                {
                    LinkedTreeMap linkedTreeMap = LinkedTreeMap.this;
                }

                public K next() {
                    return b().f;
                }
            };
        }

        public boolean contains(Object obj) {
            return LinkedTreeMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return LinkedTreeMap.this.removeInternalByKey(obj) != null;
        }

        public void clear() {
            LinkedTreeMap.this.clear();
        }
    }

    private abstract class c<T> implements Iterator<T> {
        d<K, V> b = LinkedTreeMap.this.header.d;
        d<K, V> c = null;
        int d = LinkedTreeMap.this.modCount;

        c() {
        }

        public final boolean hasNext() {
            return this.b != LinkedTreeMap.this.header;
        }

        /* access modifiers changed from: 0000 */
        public final d<K, V> b() {
            d<K, V> dVar = this.b;
            if (dVar == LinkedTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedTreeMap.this.modCount != this.d) {
                throw new ConcurrentModificationException();
            } else {
                this.b = dVar.d;
                this.c = dVar;
                return dVar;
            }
        }

        public final void remove() {
            if (this.c == null) {
                throw new IllegalStateException();
            }
            LinkedTreeMap.this.removeInternal(this.c, true);
            this.c = null;
            this.d = LinkedTreeMap.this.modCount;
        }
    }

    static final class d<K, V> implements Entry<K, V> {
        d<K, V> a;
        d<K, V> b;
        d<K, V> c;
        d<K, V> d;
        d<K, V> e;
        final K f;
        V g;
        int h;

        d() {
            this.f = null;
            this.e = this;
            this.d = this;
        }

        d(d<K, V> dVar, K k, d<K, V> dVar2, d<K, V> dVar3) {
            this.a = dVar;
            this.f = k;
            this.h = 1;
            this.d = dVar2;
            this.e = dVar3;
            dVar3.d = this;
            dVar2.e = this;
        }

        public K getKey() {
            return this.f;
        }

        public V getValue() {
            return this.g;
        }

        public V setValue(V v) {
            V v2 = this.g;
            this.g = v;
            return v2;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f != null ? this.f.equals(entry.getKey()) : entry.getKey() == null) {
                if (this.g != null ? this.g.equals(entry.getValue()) : entry.getValue() == null) {
                    z = true;
                }
            }
            return z;
        }

        public int hashCode() {
            int i = 0;
            int hashCode = this.f == null ? 0 : this.f.hashCode();
            if (this.g != null) {
                i = this.g.hashCode();
            }
            return hashCode ^ i;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f);
            sb.append("=");
            sb.append(this.g);
            return sb.toString();
        }

        public d<K, V> a() {
            d dVar = this;
            for (d dVar2 = this.b; dVar2 != null; dVar2 = dVar2.b) {
                dVar = dVar2;
            }
            return dVar;
        }

        public d<K, V> b() {
            d dVar = this;
            for (d dVar2 = this.c; dVar2 != null; dVar2 = dVar2.c) {
                dVar = dVar2;
            }
            return dVar;
        }
    }

    public LinkedTreeMap() {
        this(a);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Comparator<? super K>, code=java.util.Comparator, for r2v0, types: [java.util.Comparator<? super K>, java.util.Comparator] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LinkedTreeMap(java.util.Comparator r2) {
        /*
            r1 = this;
            r1.<init>()
            r0 = 0
            r1.size = r0
            r1.modCount = r0
            com.google.gson.internal.LinkedTreeMap$d r0 = new com.google.gson.internal.LinkedTreeMap$d
            r0.<init>()
            r1.header = r0
            if (r2 == 0) goto L_0x0012
            goto L_0x0014
        L_0x0012:
            java.util.Comparator<java.lang.Comparable> r2 = a
        L_0x0014:
            r1.comparator = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedTreeMap.<init>(java.util.Comparator):void");
    }

    public int size() {
        return this.size;
    }

    public V get(Object obj) {
        d findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.g;
        }
        return null;
    }

    public boolean containsKey(Object obj) {
        return findByObject(obj) != null;
    }

    public V put(K k, V v) {
        if (k == null) {
            throw new NullPointerException("key == null");
        }
        d find = find(k, true);
        V v2 = find.g;
        find.g = v;
        return v2;
    }

    public void clear() {
        this.root = null;
        this.size = 0;
        this.modCount++;
        d<K, V> dVar = this.header;
        dVar.e = dVar;
        dVar.d = dVar;
    }

    public V remove(Object obj) {
        d removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.g;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> find(K k, boolean z) {
        int i;
        d<K, V> dVar;
        d<K, V> dVar2;
        Comparator<? super K> comparator2 = this.comparator;
        d<K, V> dVar3 = this.root;
        if (dVar3 != null) {
            Comparable comparable = comparator2 == a ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(dVar3.f);
                } else {
                    i = comparator2.compare(k, dVar3.f);
                }
                if (i == 0) {
                    return dVar3;
                }
                if (i < 0) {
                    dVar2 = dVar3.b;
                } else {
                    dVar2 = dVar3.c;
                }
                if (dVar2 == null) {
                    break;
                }
                dVar3 = dVar2;
            }
        } else {
            i = 0;
        }
        if (!z) {
            return null;
        }
        d<K, V> dVar4 = this.header;
        if (dVar3 != null) {
            dVar = new d<>(dVar3, k, dVar4, dVar4.e);
            if (i < 0) {
                dVar3.b = dVar;
            } else {
                dVar3.c = dVar;
            }
            a(dVar3, true);
        } else if (comparator2 != a || (k instanceof Comparable)) {
            dVar = new d<>(dVar3, k, dVar4, dVar4.e);
            this.root = dVar;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(k.getClass().getName());
            sb.append(" is not Comparable");
            throw new ClassCastException(sb.toString());
        }
        this.size++;
        this.modCount++;
        return dVar;
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> findByObject(Object obj) {
        d<K, V> dVar;
        if (obj != null) {
            try {
                dVar = find(obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        } else {
            dVar = null;
        }
        return dVar;
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> findByEntry(Entry<?, ?> entry) {
        d<K, V> findByObject = findByObject(entry.getKey());
        if (findByObject != null && a((Object) findByObject.g, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* access modifiers changed from: 0000 */
    public void removeInternal(d<K, V> dVar, boolean z) {
        int i;
        if (z) {
            dVar.e.d = dVar.d;
            dVar.d.e = dVar.e;
        }
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar.a;
        int i2 = 0;
        if (dVar2 == null || dVar3 == null) {
            if (dVar2 != null) {
                a(dVar, dVar2);
                dVar.b = null;
            } else if (dVar3 != null) {
                a(dVar, dVar3);
                dVar.c = null;
            } else {
                a(dVar, null);
            }
            a(dVar4, false);
            this.size--;
            this.modCount++;
            return;
        }
        d<K, V> b2 = dVar2.h > dVar3.h ? dVar2.b() : dVar3.a();
        removeInternal(b2, false);
        d<K, V> dVar5 = dVar.b;
        if (dVar5 != null) {
            i = dVar5.h;
            b2.b = dVar5;
            dVar5.a = b2;
            dVar.b = null;
        } else {
            i = 0;
        }
        d<K, V> dVar6 = dVar.c;
        if (dVar6 != null) {
            i2 = dVar6.h;
            b2.c = dVar6;
            dVar6.a = b2;
            dVar.c = null;
        }
        b2.h = Math.max(i, i2) + 1;
        a(dVar, b2);
    }

    /* access modifiers changed from: 0000 */
    public d<K, V> removeInternalByKey(Object obj) {
        d<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    private void a(d<K, V> dVar, d<K, V> dVar2) {
        d<K, V> dVar3 = dVar.a;
        dVar.a = null;
        if (dVar2 != null) {
            dVar2.a = dVar3;
        }
        if (dVar3 == null) {
            this.root = dVar2;
        } else if (dVar3.b == dVar) {
            dVar3.b = dVar2;
        } else {
            dVar3.c = dVar2;
        }
    }

    private void a(d<K, V> dVar, boolean z) {
        while (dVar != null) {
            d<K, V> dVar2 = dVar.b;
            d<K, V> dVar3 = dVar.c;
            int i = 0;
            int i2 = dVar2 != null ? dVar2.h : 0;
            int i3 = dVar3 != null ? dVar3.h : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                d<K, V> dVar4 = dVar3.b;
                d<K, V> dVar5 = dVar3.c;
                int i5 = dVar5 != null ? dVar5.h : 0;
                if (dVar4 != null) {
                    i = dVar4.h;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    a(dVar);
                } else {
                    b(dVar3);
                    a(dVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                d<K, V> dVar6 = dVar2.b;
                d<K, V> dVar7 = dVar2.c;
                int i7 = dVar7 != null ? dVar7.h : 0;
                if (dVar6 != null) {
                    i = dVar6.h;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    b(dVar);
                } else {
                    a(dVar2);
                    b(dVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                dVar.h = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                dVar.h = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            dVar = dVar.a;
        }
    }

    private void a(d<K, V> dVar) {
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar3.b;
        d<K, V> dVar5 = dVar3.c;
        dVar.c = dVar4;
        if (dVar4 != null) {
            dVar4.a = dVar;
        }
        a(dVar, dVar3);
        dVar3.b = dVar;
        dVar.a = dVar3;
        int i = 0;
        dVar.h = Math.max(dVar2 != null ? dVar2.h : 0, dVar4 != null ? dVar4.h : 0) + 1;
        int i2 = dVar.h;
        if (dVar5 != null) {
            i = dVar5.h;
        }
        dVar3.h = Math.max(i2, i) + 1;
    }

    private void b(d<K, V> dVar) {
        d<K, V> dVar2 = dVar.b;
        d<K, V> dVar3 = dVar.c;
        d<K, V> dVar4 = dVar2.b;
        d<K, V> dVar5 = dVar2.c;
        dVar.b = dVar5;
        if (dVar5 != null) {
            dVar5.a = dVar;
        }
        a(dVar, dVar2);
        dVar2.c = dVar;
        dVar.a = dVar2;
        int i = 0;
        dVar.h = Math.max(dVar3 != null ? dVar3.h : 0, dVar5 != null ? dVar5.h : 0) + 1;
        int i2 = dVar.h;
        if (dVar4 != null) {
            i = dVar4.h;
        }
        dVar2.h = Math.max(i2, i) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        a aVar = this.entrySet;
        if (aVar != null) {
            return aVar;
        }
        a aVar2 = new a<>();
        this.entrySet = aVar2;
        return aVar2;
    }

    public Set<K> keySet() {
        b bVar = this.keySet;
        if (bVar != null) {
            return bVar;
        }
        b bVar2 = new b<>();
        this.keySet = bVar2;
        return bVar2;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
