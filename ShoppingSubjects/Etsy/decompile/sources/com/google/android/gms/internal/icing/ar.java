package com.google.android.gms.internal.icing;

import java.util.Map.Entry;

final class ar<K> implements Entry<K, Object> {
    private Entry<K, ap> a;

    private ar(Entry<K, ap> entry) {
        this.a = entry;
    }

    public final ap a() {
        return (ap) this.a.getValue();
    }

    public final K getKey() {
        return this.a.getKey();
    }

    public final Object getValue() {
        if (((ap) this.a.getValue()) == null) {
            return null;
        }
        return ap.a();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof bl) {
            return ((ap) this.a.getValue()).a((bl) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
