package com.google.android.gms.internal.icing;

import java.lang.Comparable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class cd<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private final int a;
    /* access modifiers changed from: private */
    public List<ck> b;
    /* access modifiers changed from: private */
    public Map<K, V> c;
    private boolean d;
    private volatile cm e;
    /* access modifiers changed from: private */
    public Map<K, V> f;
    private volatile cg g;

    private cd(int i) {
        this.a = i;
        this.b = Collections.emptyList();
        this.c = Collections.emptyMap();
        this.f = Collections.emptyMap();
    }

    /* synthetic */ cd(int i, ce ceVar) {
        this(i);
    }

    private final int a(K k) {
        int size = this.b.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((ck) this.b.get(size)).getKey());
            if (compareTo > 0) {
                return -(size + 2);
            }
            if (compareTo == 0) {
                return size;
            }
        }
        int i = 0;
        while (i <= size) {
            int i2 = (i + size) / 2;
            int compareTo2 = k.compareTo((Comparable) ((ck) this.b.get(i2)).getKey());
            if (compareTo2 < 0) {
                size = i2 - 1;
            } else if (compareTo2 <= 0) {
                return i2;
            } else {
                i = i2 + 1;
            }
        }
        return -(i + 1);
    }

    static <FieldDescriptorType extends ae<FieldDescriptorType>> cd<FieldDescriptorType, Object> a(int i) {
        return new ce(i);
    }

    /* access modifiers changed from: private */
    public final V c(int i) {
        f();
        V value = ((ck) this.b.remove(i)).getValue();
        if (!this.c.isEmpty()) {
            Iterator it = g().entrySet().iterator();
            this.b.add(new ck(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    /* access modifiers changed from: private */
    public final void f() {
        if (this.d) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> g() {
        f();
        if (this.c.isEmpty() && !(this.c instanceof TreeMap)) {
            this.c = new TreeMap();
            this.f = ((TreeMap) this.c).descendingMap();
        }
        return (SortedMap) this.c;
    }

    /* renamed from: a */
    public final V put(K k, V v) {
        f();
        int a2 = a(k);
        if (a2 >= 0) {
            return ((ck) this.b.get(a2)).setValue(v);
        }
        f();
        if (this.b.isEmpty() && !(this.b instanceof ArrayList)) {
            this.b = new ArrayList(this.a);
        }
        int i = -(a2 + 1);
        if (i >= this.a) {
            return g().put(k, v);
        }
        if (this.b.size() == this.a) {
            ck ckVar = (ck) this.b.remove(this.a - 1);
            g().put((Comparable) ckVar.getKey(), ckVar.getValue());
        }
        this.b.add(i, new ck(this, k, v));
        return null;
    }

    public void a() {
        if (!this.d) {
            this.c = this.c.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.c);
            this.f = this.f.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.f);
            this.d = true;
        }
    }

    public final Entry<K, V> b(int i) {
        return (Entry) this.b.get(i);
    }

    public final boolean b() {
        return this.d;
    }

    public final int c() {
        return this.b.size();
    }

    public void clear() {
        f();
        if (!this.b.isEmpty()) {
            this.b.clear();
        }
        if (!this.c.isEmpty()) {
            this.c.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a((K) comparable) >= 0 || this.c.containsKey(comparable);
    }

    public final Iterable<Entry<K, V>> d() {
        return this.c.isEmpty() ? ch.a() : this.c.entrySet();
    }

    /* access modifiers changed from: 0000 */
    public final Set<Entry<K, V>> e() {
        if (this.g == null) {
            this.g = new cg(this, null);
        }
        return this.g;
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.e == null) {
            this.e = new cm(this, null);
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof cd)) {
            return super.equals(obj);
        }
        cd cdVar = (cd) obj;
        int size = size();
        if (size != cdVar.size()) {
            return false;
        }
        int c2 = c();
        if (c2 != cdVar.c()) {
            return entrySet().equals(cdVar.entrySet());
        }
        for (int i = 0; i < c2; i++) {
            if (!b(i).equals(cdVar.b(i))) {
                return false;
            }
        }
        if (c2 != size) {
            return this.c.equals(cdVar.c);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int a2 = a((K) comparable);
        return a2 >= 0 ? ((ck) this.b.get(a2)).getValue() : this.c.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < c(); i2++) {
            i += ((ck) this.b.get(i2)).hashCode();
        }
        return this.c.size() > 0 ? i + this.c.hashCode() : i;
    }

    public V remove(Object obj) {
        f();
        Comparable comparable = (Comparable) obj;
        int a2 = a((K) comparable);
        if (a2 >= 0) {
            return c(a2);
        }
        if (this.c.isEmpty()) {
            return null;
        }
        return this.c.remove(comparable);
    }

    public int size() {
        return this.b.size() + this.c.size();
    }
}
