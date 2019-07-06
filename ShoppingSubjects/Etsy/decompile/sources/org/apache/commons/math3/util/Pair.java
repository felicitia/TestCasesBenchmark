package org.apache.commons.math3.util;

public class Pair<K, V> {
    private final K key;
    private final V value;

    public Pair(K k, V v) {
        this.key = k;
        this.value = v;
    }

    public Pair(Pair<? extends K, ? extends V> pair) {
        this(pair.getKey(), pair.getValue());
    }

    public K getKey() {
        return this.key;
    }

    public V getValue() {
        return this.value;
    }

    public K getFirst() {
        return this.key;
    }

    public V getSecond() {
        return this.value;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair pair = (Pair) obj;
        if (this.key != null ? this.key.equals(pair.key) : pair.key == null) {
            if (this.value != null) {
            }
        }
        z = false;
        return z;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = this.key == null ? 0 : this.key.hashCode();
        if (this.value != null) {
            i = this.value.hashCode();
        }
        return (i >>> 16) ^ ((37 * hashCode) + i);
    }
}
