package com.google.android.gms.internal.firebase-perf;

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

class zzem<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzjg;
    private final int zzpq;
    /* access modifiers changed from: private */
    public List<zzet> zzpr;
    /* access modifiers changed from: private */
    public Map<K, V> zzps;
    private volatile zzev zzpt;
    /* access modifiers changed from: private */
    public Map<K, V> zzpu;
    private volatile zzep zzpv;

    static <FieldDescriptorType extends zzcf<FieldDescriptorType>> zzem<FieldDescriptorType, Object> zzas(int i) {
        return new zzen(i);
    }

    private zzem(int i) {
        this.zzpq = i;
        this.zzpr = Collections.emptyList();
        this.zzps = Collections.emptyMap();
        this.zzpu = Collections.emptyMap();
    }

    public void zzbi() {
        Map<K, V> map;
        Map<K, V> map2;
        if (!this.zzjg) {
            if (this.zzps.isEmpty()) {
                map = Collections.emptyMap();
            } else {
                map = Collections.unmodifiableMap(this.zzps);
            }
            this.zzps = map;
            if (this.zzpu.isEmpty()) {
                map2 = Collections.emptyMap();
            } else {
                map2 = Collections.unmodifiableMap(this.zzpu);
            }
            this.zzpu = map2;
            this.zzjg = true;
        }
    }

    public final boolean isImmutable() {
        return this.zzjg;
    }

    public final int zzfj() {
        return this.zzpr.size();
    }

    public final Entry<K, V> zzat(int i) {
        return (Entry) this.zzpr.get(i);
    }

    public final Iterable<Entry<K, V>> zzfk() {
        if (this.zzps.isEmpty()) {
            return zzeq.zzfp();
        }
        return this.zzps.entrySet();
    }

    public int size() {
        return this.zzpr.size() + this.zzps.size();
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((K) comparable) >= 0 || this.zzps.containsKey(comparable);
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return ((zzet) this.zzpr.get(zza)).getValue();
        }
        return this.zzps.get(comparable);
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzfm();
        int zza = zza(k);
        if (zza >= 0) {
            return ((zzet) this.zzpr.get(zza)).setValue(v);
        }
        zzfm();
        if (this.zzpr.isEmpty() && !(this.zzpr instanceof ArrayList)) {
            this.zzpr = new ArrayList(this.zzpq);
        }
        int i = -(zza + 1);
        if (i >= this.zzpq) {
            return zzfn().put(k, v);
        }
        if (this.zzpr.size() == this.zzpq) {
            zzet zzet = (zzet) this.zzpr.remove(this.zzpq - 1);
            zzfn().put((Comparable) zzet.getKey(), zzet.getValue());
        }
        this.zzpr.add(i, new zzet(this, k, v));
        return null;
    }

    public void clear() {
        zzfm();
        if (!this.zzpr.isEmpty()) {
            this.zzpr.clear();
        }
        if (!this.zzps.isEmpty()) {
            this.zzps.clear();
        }
    }

    public V remove(Object obj) {
        zzfm();
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return zzau(zza);
        }
        if (this.zzps.isEmpty()) {
            return null;
        }
        return this.zzps.remove(comparable);
    }

    /* access modifiers changed from: private */
    public final V zzau(int i) {
        zzfm();
        V value = ((zzet) this.zzpr.remove(i)).getValue();
        if (!this.zzps.isEmpty()) {
            Iterator it = zzfn().entrySet().iterator();
            this.zzpr.add(new zzet(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    private final int zza(K k) {
        int size = this.zzpr.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzet) this.zzpr.get(size)).getKey());
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
            int compareTo2 = k.compareTo((Comparable) ((zzet) this.zzpr.get(i2)).getKey());
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

    public Set<Entry<K, V>> entrySet() {
        if (this.zzpt == null) {
            this.zzpt = new zzev(this, null);
        }
        return this.zzpt;
    }

    /* access modifiers changed from: 0000 */
    public final Set<Entry<K, V>> zzfl() {
        if (this.zzpv == null) {
            this.zzpv = new zzep(this, null);
        }
        return this.zzpv;
    }

    /* access modifiers changed from: private */
    public final void zzfm() {
        if (this.zzjg) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzfn() {
        zzfm();
        if (this.zzps.isEmpty() && !(this.zzps instanceof TreeMap)) {
            this.zzps = new TreeMap();
            this.zzpu = ((TreeMap) this.zzps).descendingMap();
        }
        return (SortedMap) this.zzps;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzem)) {
            return super.equals(obj);
        }
        zzem zzem = (zzem) obj;
        int size = size();
        if (size != zzem.size()) {
            return false;
        }
        int zzfj = zzfj();
        if (zzfj != zzem.zzfj()) {
            return entrySet().equals(zzem.entrySet());
        }
        for (int i = 0; i < zzfj; i++) {
            if (!zzat(i).equals(zzem.zzat(i))) {
                return false;
            }
        }
        if (zzfj != size) {
            return this.zzps.equals(zzem.zzps);
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzfj(); i2++) {
            i += ((zzet) this.zzpr.get(i2)).hashCode();
        }
        return this.zzps.size() > 0 ? i + this.zzps.hashCode() : i;
    }

    /* synthetic */ zzem(int i, zzen zzen) {
        this(i);
    }
}
