package com.google.android.gms.internal.ads;

import java.util.Map.Entry;

final class xp<K> implements Entry<K, Object> {
    private Entry<K, xn> a;

    private xp(Entry<K, xn> entry) {
        this.a = entry;
    }

    public final xn a() {
        return (xn) this.a.getValue();
    }

    public final K getKey() {
        return this.a.getKey();
    }

    public final Object getValue() {
        if (((xn) this.a.getValue()) == null) {
            return null;
        }
        return xn.a();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof yk) {
            return ((xn) this.a.getValue()).a((yk) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
