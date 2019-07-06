package com.google.gson.internal;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Comparator;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;

public final class LinkedHashTreeMap<K, V> extends AbstractMap<K, V> implements Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Comparator<Comparable> a = new Comparator<Comparable>() {
        /* renamed from: a */
        public int compare(Comparable comparable, Comparable comparable2) {
            return comparable.compareTo(comparable2);
        }
    };
    Comparator<? super K> comparator;
    private c entrySet;
    final f<K, V> header;
    private d keySet;
    int modCount;
    int size;
    f<K, V>[] table;
    int threshold;

    static final class a<K, V> {
        private f<K, V> a;
        private int b;
        private int c;
        private int d;

        a() {
        }

        /* access modifiers changed from: 0000 */
        public void a(int i) {
            this.b = ((Integer.highestOneBit(i) * 2) - 1) - i;
            this.d = 0;
            this.c = 0;
            this.a = null;
        }

        /* access modifiers changed from: 0000 */
        public void a(f<K, V> fVar) {
            fVar.c = null;
            fVar.a = null;
            fVar.b = null;
            fVar.i = 1;
            if (this.b > 0 && (this.d & 1) == 0) {
                this.d++;
                this.b--;
                this.c++;
            }
            fVar.a = this.a;
            this.a = fVar;
            this.d++;
            if (this.b > 0 && (this.d & 1) == 0) {
                this.d++;
                this.b--;
                this.c++;
            }
            int i = 4;
            while (true) {
                int i2 = i - 1;
                if ((this.d & i2) == i2) {
                    if (this.c == 0) {
                        f<K, V> fVar2 = this.a;
                        f<K, V> fVar3 = fVar2.a;
                        f<K, V> fVar4 = fVar3.a;
                        fVar3.a = fVar4.a;
                        this.a = fVar3;
                        fVar3.b = fVar4;
                        fVar3.c = fVar2;
                        fVar3.i = fVar2.i + 1;
                        fVar4.a = fVar3;
                        fVar2.a = fVar3;
                    } else if (this.c == 1) {
                        f<K, V> fVar5 = this.a;
                        f<K, V> fVar6 = fVar5.a;
                        this.a = fVar6;
                        fVar6.c = fVar5;
                        fVar6.i = fVar5.i + 1;
                        fVar5.a = fVar6;
                        this.c = 0;
                    } else if (this.c == 2) {
                        this.c = 0;
                    }
                    i *= 2;
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: 0000 */
        public f<K, V> a() {
            f<K, V> fVar = this.a;
            if (fVar.a == null) {
                return fVar;
            }
            throw new IllegalStateException();
        }
    }

    static class b<K, V> {
        private f<K, V> a;

        b() {
        }

        /* access modifiers changed from: 0000 */
        public void a(f<K, V> fVar) {
            f<K, V> fVar2 = null;
            while (true) {
                f<K, V> fVar3 = fVar2;
                fVar2 = fVar;
                f<K, V> fVar4 = fVar3;
                if (fVar2 != null) {
                    fVar2.a = fVar4;
                    fVar = fVar2.b;
                } else {
                    this.a = fVar4;
                    return;
                }
            }
        }

        public f<K, V> a() {
            f<K, V> fVar = this.a;
            if (fVar == null) {
                return null;
            }
            f<K, V> fVar2 = fVar.a;
            fVar.a = null;
            f<K, V> fVar3 = fVar.c;
            while (true) {
                f<K, V> fVar4 = fVar2;
                fVar2 = fVar3;
                f<K, V> fVar5 = fVar4;
                if (fVar2 != null) {
                    fVar2.a = fVar5;
                    fVar3 = fVar2.b;
                } else {
                    this.a = fVar5;
                    return fVar;
                }
            }
        }
    }

    final class c extends AbstractSet<Entry<K, V>> {
        c() {
        }

        public int size() {
            return LinkedHashTreeMap.this.size;
        }

        public Iterator<Entry<K, V>> iterator() {
            return new e<Entry<K, V>>() {
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                /* renamed from: a */
                public Entry<K, V> next() {
                    return b();
                }
            };
        }

        public boolean contains(Object obj) {
            return (obj instanceof Entry) && LinkedHashTreeMap.this.findByEntry((Entry) obj) != null;
        }

        public boolean remove(Object obj) {
            if (!(obj instanceof Entry)) {
                return false;
            }
            f findByEntry = LinkedHashTreeMap.this.findByEntry((Entry) obj);
            if (findByEntry == null) {
                return false;
            }
            LinkedHashTreeMap.this.removeInternal(findByEntry, true);
            return true;
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    final class d extends AbstractSet<K> {
        d() {
        }

        public int size() {
            return LinkedHashTreeMap.this.size;
        }

        public Iterator<K> iterator() {
            return new e<K>() {
                {
                    LinkedHashTreeMap linkedHashTreeMap = LinkedHashTreeMap.this;
                }

                public K next() {
                    return b().f;
                }
            };
        }

        public boolean contains(Object obj) {
            return LinkedHashTreeMap.this.containsKey(obj);
        }

        public boolean remove(Object obj) {
            return LinkedHashTreeMap.this.removeInternalByKey(obj) != null;
        }

        public void clear() {
            LinkedHashTreeMap.this.clear();
        }
    }

    private abstract class e<T> implements Iterator<T> {
        f<K, V> b = LinkedHashTreeMap.this.header.d;
        f<K, V> c = null;
        int d = LinkedHashTreeMap.this.modCount;

        e() {
        }

        public final boolean hasNext() {
            return this.b != LinkedHashTreeMap.this.header;
        }

        /* access modifiers changed from: 0000 */
        public final f<K, V> b() {
            f<K, V> fVar = this.b;
            if (fVar == LinkedHashTreeMap.this.header) {
                throw new NoSuchElementException();
            } else if (LinkedHashTreeMap.this.modCount != this.d) {
                throw new ConcurrentModificationException();
            } else {
                this.b = fVar.d;
                this.c = fVar;
                return fVar;
            }
        }

        public final void remove() {
            if (this.c == null) {
                throw new IllegalStateException();
            }
            LinkedHashTreeMap.this.removeInternal(this.c, true);
            this.c = null;
            this.d = LinkedHashTreeMap.this.modCount;
        }
    }

    static final class f<K, V> implements Entry<K, V> {
        f<K, V> a;
        f<K, V> b;
        f<K, V> c;
        f<K, V> d;
        f<K, V> e;
        final K f;
        final int g;
        V h;
        int i;

        f() {
            this.f = null;
            this.g = -1;
            this.e = this;
            this.d = this;
        }

        f(f<K, V> fVar, K k, int i2, f<K, V> fVar2, f<K, V> fVar3) {
            this.a = fVar;
            this.f = k;
            this.g = i2;
            this.i = 1;
            this.d = fVar2;
            this.e = fVar3;
            fVar3.d = this;
            fVar2.e = this;
        }

        public K getKey() {
            return this.f;
        }

        public V getValue() {
            return this.h;
        }

        public V setValue(V v) {
            V v2 = this.h;
            this.h = v;
            return v2;
        }

        public boolean equals(Object obj) {
            boolean z = false;
            if (!(obj instanceof Entry)) {
                return false;
            }
            Entry entry = (Entry) obj;
            if (this.f != null ? this.f.equals(entry.getKey()) : entry.getKey() == null) {
                if (this.h != null ? this.h.equals(entry.getValue()) : entry.getValue() == null) {
                    z = true;
                }
            }
            return z;
        }

        public int hashCode() {
            int i2 = 0;
            int hashCode = this.f == null ? 0 : this.f.hashCode();
            if (this.h != null) {
                i2 = this.h.hashCode();
            }
            return hashCode ^ i2;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.f);
            sb.append("=");
            sb.append(this.h);
            return sb.toString();
        }

        public f<K, V> a() {
            f fVar = this;
            for (f fVar2 = this.b; fVar2 != null; fVar2 = fVar2.b) {
                fVar = fVar2;
            }
            return fVar;
        }

        public f<K, V> b() {
            f fVar = this;
            for (f fVar2 = this.c; fVar2 != null; fVar2 = fVar2.c) {
                fVar = fVar2;
            }
            return fVar;
        }
    }

    private static int a(int i) {
        int i2 = i ^ ((i >>> 20) ^ (i >>> 12));
        return (i2 >>> 4) ^ ((i2 >>> 7) ^ i2);
    }

    public LinkedHashTreeMap() {
        this(a);
    }

    /* JADX WARNING: Incorrect type for immutable var: ssa=java.util.Comparator<? super K>, code=java.util.Comparator, for r2v0, types: [java.util.Comparator<? super K>, java.util.Comparator] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public LinkedHashTreeMap(java.util.Comparator r2) {
        /*
            r1 = this;
            r1.<init>()
            r0 = 0
            r1.size = r0
            r1.modCount = r0
            if (r2 == 0) goto L_0x000b
            goto L_0x000d
        L_0x000b:
            java.util.Comparator<java.lang.Comparable> r2 = a
        L_0x000d:
            r1.comparator = r2
            com.google.gson.internal.LinkedHashTreeMap$f r2 = new com.google.gson.internal.LinkedHashTreeMap$f
            r2.<init>()
            r1.header = r2
            r2 = 16
            com.google.gson.internal.LinkedHashTreeMap$f[] r2 = new com.google.gson.internal.LinkedHashTreeMap.f[r2]
            r1.table = r2
            com.google.gson.internal.LinkedHashTreeMap$f<K, V>[] r2 = r1.table
            int r2 = r2.length
            int r2 = r2 / 2
            com.google.gson.internal.LinkedHashTreeMap$f<K, V>[] r0 = r1.table
            int r0 = r0.length
            int r0 = r0 / 4
            int r2 = r2 + r0
            r1.threshold = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.internal.LinkedHashTreeMap.<init>(java.util.Comparator):void");
    }

    public int size() {
        return this.size;
    }

    public V get(Object obj) {
        f findByObject = findByObject(obj);
        if (findByObject != null) {
            return findByObject.h;
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
        f find = find(k, true);
        V v2 = find.h;
        find.h = v;
        return v2;
    }

    public void clear() {
        Arrays.fill(this.table, null);
        this.size = 0;
        this.modCount++;
        f<K, V> fVar = this.header;
        f<K, V> fVar2 = fVar.d;
        while (fVar2 != fVar) {
            f<K, V> fVar3 = fVar2.d;
            fVar2.e = null;
            fVar2.d = null;
            fVar2 = fVar3;
        }
        fVar.e = fVar;
        fVar.d = fVar;
    }

    public V remove(Object obj) {
        f removeInternalByKey = removeInternalByKey(obj);
        if (removeInternalByKey != null) {
            return removeInternalByKey.h;
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public f<K, V> find(K k, boolean z) {
        int i;
        f<K, V> fVar;
        f<K, V> fVar2;
        Comparator<? super K> comparator2 = this.comparator;
        f<K, V>[] fVarArr = this.table;
        int a2 = a(k.hashCode());
        int length = a2 & (fVarArr.length - 1);
        f<K, V> fVar3 = fVarArr[length];
        if (fVar3 != null) {
            Comparable comparable = comparator2 == a ? (Comparable) k : null;
            while (true) {
                if (comparable != null) {
                    i = comparable.compareTo(fVar3.f);
                } else {
                    i = comparator2.compare(k, fVar3.f);
                }
                if (i == 0) {
                    return fVar3;
                }
                if (i < 0) {
                    fVar2 = fVar3.b;
                } else {
                    fVar2 = fVar3.c;
                }
                if (fVar2 == null) {
                    break;
                }
                fVar3 = fVar2;
            }
        } else {
            i = 0;
        }
        f<K, V> fVar4 = fVar3;
        int i2 = i;
        if (!z) {
            return null;
        }
        f<K, V> fVar5 = this.header;
        if (fVar4 != null) {
            fVar = new f<>(fVar4, k, a2, fVar5, fVar5.e);
            if (i2 < 0) {
                fVar4.b = fVar;
            } else {
                fVar4.c = fVar;
            }
            a(fVar4, true);
        } else if (comparator2 != a || (k instanceof Comparable)) {
            fVar = new f<>(fVar4, k, a2, fVar5, fVar5.e);
            fVarArr[length] = fVar;
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append(k.getClass().getName());
            sb.append(" is not Comparable");
            throw new ClassCastException(sb.toString());
        }
        int i3 = this.size;
        this.size = i3 + 1;
        if (i3 > this.threshold) {
            a();
        }
        this.modCount++;
        return fVar;
    }

    /* access modifiers changed from: 0000 */
    public f<K, V> findByObject(Object obj) {
        f<K, V> fVar;
        if (obj != null) {
            try {
                fVar = find(obj, false);
            } catch (ClassCastException unused) {
                return null;
            }
        } else {
            fVar = null;
        }
        return fVar;
    }

    /* access modifiers changed from: 0000 */
    public f<K, V> findByEntry(Entry<?, ?> entry) {
        f<K, V> findByObject = findByObject(entry.getKey());
        if (findByObject != null && a((Object) findByObject.h, entry.getValue())) {
            return findByObject;
        }
        return null;
    }

    private boolean a(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    /* access modifiers changed from: 0000 */
    public void removeInternal(f<K, V> fVar, boolean z) {
        int i;
        if (z) {
            fVar.e.d = fVar.d;
            fVar.d.e = fVar.e;
            fVar.e = null;
            fVar.d = null;
        }
        f<K, V> fVar2 = fVar.b;
        f<K, V> fVar3 = fVar.c;
        f<K, V> fVar4 = fVar.a;
        int i2 = 0;
        if (fVar2 == null || fVar3 == null) {
            if (fVar2 != null) {
                a(fVar, fVar2);
                fVar.b = null;
            } else if (fVar3 != null) {
                a(fVar, fVar3);
                fVar.c = null;
            } else {
                a(fVar, null);
            }
            a(fVar4, false);
            this.size--;
            this.modCount++;
            return;
        }
        f<K, V> b2 = fVar2.i > fVar3.i ? fVar2.b() : fVar3.a();
        removeInternal(b2, false);
        f<K, V> fVar5 = fVar.b;
        if (fVar5 != null) {
            i = fVar5.i;
            b2.b = fVar5;
            fVar5.a = b2;
            fVar.b = null;
        } else {
            i = 0;
        }
        f<K, V> fVar6 = fVar.c;
        if (fVar6 != null) {
            i2 = fVar6.i;
            b2.c = fVar6;
            fVar6.a = b2;
            fVar.c = null;
        }
        b2.i = Math.max(i, i2) + 1;
        a(fVar, b2);
    }

    /* access modifiers changed from: 0000 */
    public f<K, V> removeInternalByKey(Object obj) {
        f<K, V> findByObject = findByObject(obj);
        if (findByObject != null) {
            removeInternal(findByObject, true);
        }
        return findByObject;
    }

    private void a(f<K, V> fVar, f<K, V> fVar2) {
        f<K, V> fVar3 = fVar.a;
        fVar.a = null;
        if (fVar2 != null) {
            fVar2.a = fVar3;
        }
        if (fVar3 == null) {
            this.table[fVar.g & (this.table.length - 1)] = fVar2;
        } else if (fVar3.b == fVar) {
            fVar3.b = fVar2;
        } else {
            fVar3.c = fVar2;
        }
    }

    private void a(f<K, V> fVar, boolean z) {
        while (fVar != null) {
            f<K, V> fVar2 = fVar.b;
            f<K, V> fVar3 = fVar.c;
            int i = 0;
            int i2 = fVar2 != null ? fVar2.i : 0;
            int i3 = fVar3 != null ? fVar3.i : 0;
            int i4 = i2 - i3;
            if (i4 == -2) {
                f<K, V> fVar4 = fVar3.b;
                f<K, V> fVar5 = fVar3.c;
                int i5 = fVar5 != null ? fVar5.i : 0;
                if (fVar4 != null) {
                    i = fVar4.i;
                }
                int i6 = i - i5;
                if (i6 == -1 || (i6 == 0 && !z)) {
                    a(fVar);
                } else {
                    b(fVar3);
                    a(fVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 2) {
                f<K, V> fVar6 = fVar2.b;
                f<K, V> fVar7 = fVar2.c;
                int i7 = fVar7 != null ? fVar7.i : 0;
                if (fVar6 != null) {
                    i = fVar6.i;
                }
                int i8 = i - i7;
                if (i8 == 1 || (i8 == 0 && !z)) {
                    b(fVar);
                } else {
                    a(fVar2);
                    b(fVar);
                }
                if (z) {
                    return;
                }
            } else if (i4 == 0) {
                fVar.i = i2 + 1;
                if (z) {
                    return;
                }
            } else {
                fVar.i = Math.max(i2, i3) + 1;
                if (!z) {
                    return;
                }
            }
            fVar = fVar.a;
        }
    }

    private void a(f<K, V> fVar) {
        f<K, V> fVar2 = fVar.b;
        f<K, V> fVar3 = fVar.c;
        f<K, V> fVar4 = fVar3.b;
        f<K, V> fVar5 = fVar3.c;
        fVar.c = fVar4;
        if (fVar4 != null) {
            fVar4.a = fVar;
        }
        a(fVar, fVar3);
        fVar3.b = fVar;
        fVar.a = fVar3;
        int i = 0;
        fVar.i = Math.max(fVar2 != null ? fVar2.i : 0, fVar4 != null ? fVar4.i : 0) + 1;
        int i2 = fVar.i;
        if (fVar5 != null) {
            i = fVar5.i;
        }
        fVar3.i = Math.max(i2, i) + 1;
    }

    private void b(f<K, V> fVar) {
        f<K, V> fVar2 = fVar.b;
        f<K, V> fVar3 = fVar.c;
        f<K, V> fVar4 = fVar2.b;
        f<K, V> fVar5 = fVar2.c;
        fVar.b = fVar5;
        if (fVar5 != null) {
            fVar5.a = fVar;
        }
        a(fVar, fVar2);
        fVar2.c = fVar;
        fVar.a = fVar2;
        int i = 0;
        fVar.i = Math.max(fVar3 != null ? fVar3.i : 0, fVar5 != null ? fVar5.i : 0) + 1;
        int i2 = fVar.i;
        if (fVar4 != null) {
            i = fVar4.i;
        }
        fVar2.i = Math.max(i2, i) + 1;
    }

    public Set<Entry<K, V>> entrySet() {
        c cVar = this.entrySet;
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c<>();
        this.entrySet = cVar2;
        return cVar2;
    }

    public Set<K> keySet() {
        d dVar = this.keySet;
        if (dVar != null) {
            return dVar;
        }
        d dVar2 = new d<>();
        this.keySet = dVar2;
        return dVar2;
    }

    private void a() {
        this.table = doubleCapacity(this.table);
        this.threshold = (this.table.length / 2) + (this.table.length / 4);
    }

    static <K, V> f<K, V>[] doubleCapacity(f<K, V>[] fVarArr) {
        int length = fVarArr.length;
        f<K, V>[] fVarArr2 = new f[(length * 2)];
        b bVar = new b();
        a aVar = new a();
        a aVar2 = new a();
        for (int i = 0; i < length; i++) {
            f<K, V> fVar = fVarArr[i];
            if (fVar != null) {
                bVar.a(fVar);
                int i2 = 0;
                int i3 = 0;
                while (true) {
                    f a2 = bVar.a();
                    if (a2 == null) {
                        break;
                    } else if ((a2.g & length) == 0) {
                        i2++;
                    } else {
                        i3++;
                    }
                }
                aVar.a(i2);
                aVar2.a(i3);
                bVar.a(fVar);
                while (true) {
                    f a3 = bVar.a();
                    if (a3 == null) {
                        break;
                    } else if ((a3.g & length) == 0) {
                        aVar.a(a3);
                    } else {
                        aVar2.a(a3);
                    }
                }
                f<K, V> fVar2 = null;
                fVarArr2[i] = i2 > 0 ? aVar.a() : null;
                int i4 = i + length;
                if (i3 > 0) {
                    fVar2 = aVar2.a();
                }
                fVarArr2[i4] = fVar2;
            }
        }
        return fVarArr2;
    }

    private Object writeReplace() throws ObjectStreamException {
        return new LinkedHashMap(this);
    }
}
