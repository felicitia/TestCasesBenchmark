package com.google.android.gms.internal.firebase-perf;

import java.util.Map.Entry;

final class zzcy<K> implements Entry<K, Object> {
    private Entry<K, zzcw> zznq;

    private zzcy(Entry<K, zzcw> entry) {
        this.zznq = entry;
    }

    public final K getKey() {
        return this.zznq.getKey();
    }

    public final Object getValue() {
        if (((zzcw) this.zznq.getValue()) == null) {
            return null;
        }
        return zzcw.zzeg();
    }

    public final zzcw zzeh() {
        return (zzcw) this.zznq.getValue();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzdt) {
            return ((zzcw) this.zznq.getValue()).zzi((zzdt) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }
}
