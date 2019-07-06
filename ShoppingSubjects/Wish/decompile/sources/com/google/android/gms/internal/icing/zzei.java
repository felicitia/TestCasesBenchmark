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

class zzei<K extends Comparable<K>, V> extends AbstractMap<K, V> {
    private boolean zzes;
    private final int zzkz;
    /* access modifiers changed from: private */
    public List<zzep> zzla;
    /* access modifiers changed from: private */
    public Map<K, V> zzlb;
    private volatile zzer zzlc;
    /* access modifiers changed from: private */
    public Map<K, V> zzld;
    private volatile zzel zzle;

    private zzei(int i) {
        this.zzkz = i;
        this.zzla = Collections.emptyList();
        this.zzlb = Collections.emptyMap();
        this.zzld = Collections.emptyMap();
    }

    /* synthetic */ zzei(int i, zzej zzej) {
        this(i);
    }

    private final int zza(K k) {
        int size = this.zzla.size() - 1;
        if (size >= 0) {
            int compareTo = k.compareTo((Comparable) ((zzep) this.zzla.get(size)).getKey());
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
            int compareTo2 = k.compareTo((Comparable) ((zzep) this.zzla.get(i2)).getKey());
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

    static <FieldDescriptorType extends zzcf<FieldDescriptorType>> zzei<FieldDescriptorType, Object> zzae(int i) {
        return new zzej(i);
    }

    /* access modifiers changed from: private */
    public final V zzag(int i) {
        zzcl();
        V value = ((zzep) this.zzla.remove(i)).getValue();
        if (!this.zzlb.isEmpty()) {
            Iterator it = zzcm().entrySet().iterator();
            this.zzla.add(new zzep(this, (Entry) it.next()));
            it.remove();
        }
        return value;
    }

    /* access modifiers changed from: private */
    public final void zzcl() {
        if (this.zzes) {
            throw new UnsupportedOperationException();
        }
    }

    private final SortedMap<K, V> zzcm() {
        zzcl();
        if (this.zzlb.isEmpty() && !(this.zzlb instanceof TreeMap)) {
            this.zzlb = new TreeMap();
            this.zzld = ((TreeMap) this.zzlb).descendingMap();
        }
        return (SortedMap) this.zzlb;
    }

    public void clear() {
        zzcl();
        if (!this.zzla.isEmpty()) {
            this.zzla.clear();
        }
        if (!this.zzlb.isEmpty()) {
            this.zzlb.clear();
        }
    }

    public boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return zza((K) comparable) >= 0 || this.zzlb.containsKey(comparable);
    }

    public Set<Entry<K, V>> entrySet() {
        if (this.zzlc == null) {
            this.zzlc = new zzer(this, null);
        }
        return this.zzlc;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzei)) {
            return super.equals(obj);
        }
        zzei zzei = (zzei) obj;
        int size = size();
        if (size != zzei.size()) {
            return false;
        }
        int zzci = zzci();
        if (zzci != zzei.zzci()) {
            return entrySet().equals(zzei.entrySet());
        }
        for (int i = 0; i < zzci; i++) {
            if (!zzaf(i).equals(zzei.zzaf(i))) {
                return false;
            }
        }
        if (zzci != size) {
            return this.zzlb.equals(zzei.zzlb);
        }
        return true;
    }

    public V get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        return zza >= 0 ? ((zzep) this.zzla.get(zza)).getValue() : this.zzlb.get(comparable);
    }

    public int hashCode() {
        int i = 0;
        for (int i2 = 0; i2 < zzci(); i2++) {
            i += ((zzep) this.zzla.get(i2)).hashCode();
        }
        return this.zzlb.size() > 0 ? i + this.zzlb.hashCode() : i;
    }

    public final boolean isImmutable() {
        return this.zzes;
    }

    public V remove(Object obj) {
        zzcl();
        Comparable comparable = (Comparable) obj;
        int zza = zza((K) comparable);
        if (zza >= 0) {
            return zzag(zza);
        }
        if (this.zzlb.isEmpty()) {
            return null;
        }
        return this.zzlb.remove(comparable);
    }

    public int size() {
        return this.zzla.size() + this.zzlb.size();
    }

    /* renamed from: zza */
    public final V put(K k, V v) {
        zzcl();
        int zza = zza(k);
        if (zza >= 0) {
            return ((zzep) this.zzla.get(zza)).setValue(v);
        }
        zzcl();
        if (this.zzla.isEmpty() && !(this.zzla instanceof ArrayList)) {
            this.zzla = new ArrayList(this.zzkz);
        }
        int i = -(zza + 1);
        if (i >= this.zzkz) {
            return zzcm().put(k, v);
        }
        if (this.zzla.size() == this.zzkz) {
            zzep zzep = (zzep) this.zzla.remove(this.zzkz - 1);
            zzcm().put((Comparable) zzep.getKey(), zzep.getValue());
        }
        this.zzla.add(i, new zzep(this, k, v));
        return null;
    }

    public final Entry<K, V> zzaf(int i) {
        return (Entry) this.zzla.get(i);
    }

    public final int zzci() {
        return this.zzla.size();
    }

    public final Iterable<Entry<K, V>> zzcj() {
        return this.zzlb.isEmpty() ? zzem.zzco() : this.zzlb.entrySet();
    }

    /* access modifiers changed from: 0000 */
    public final Set<Entry<K, V>> zzck() {
        if (this.zzle == null) {
            this.zzle = new zzel(this, null);
        }
        return this.zzle;
    }

    public void zzp() {
        if (!this.zzes) {
            this.zzlb = this.zzlb.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzlb);
            this.zzld = this.zzld.isEmpty() ? Collections.emptyMap() : Collections.unmodifiableMap(this.zzld);
            this.zzes = true;
        }
    }
}
