package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class zn implements Comparable<zn>, Entry<K, V> {
    private final K a;
    private V b;
    private final /* synthetic */ zg c;

    zn(zg zgVar, K k, V v) {
        this.c = zgVar;
        this.a = k;
        this.b = v;
    }

    zn(zg zgVar, Entry<K, V> entry) {
        this(zgVar, (Comparable) entry.getKey(), entry.getValue());
    }

    private static boolean a(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public final /* synthetic */ int compareTo(Object obj) {
        return ((Comparable) getKey()).compareTo((Comparable) ((zn) obj).getKey());
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Entry)) {
            return false;
        }
        Entry entry = (Entry) obj;
        return a(this.a, entry.getKey()) && a(this.b, entry.getValue());
    }

    public final /* synthetic */ Object getKey() {
        return this.a;
    }

    public final V getValue() {
        return this.b;
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = this.a == null ? 0 : this.a.hashCode();
        if (this.b != null) {
            i = this.b.hashCode();
        }
        return hashCode ^ i;
    }

    public final V setValue(V v) {
        this.c.f();
        V v2 = this.b;
        this.b = v;
        return v2;
    }

    public final String toString() {
        String valueOf = String.valueOf(this.a);
        String valueOf2 = String.valueOf(this.b);
        StringBuilder sb = new StringBuilder(1 + String.valueOf(valueOf).length() + String.valueOf(valueOf2).length());
        sb.append(valueOf);
        sb.append("=");
        sb.append(valueOf2);
        return sb.toString();
    }
}
