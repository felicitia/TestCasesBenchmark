package com.google.android.gms.internal.icing;

import java.util.Map.Entry;

final class zzcx<K> implements Entry<K, Object> {
    private Entry<K, zzcv> zzjb;

    private zzcx(Entry<K, zzcv> entry) {
        this.zzjb = entry;
    }

    public final K getKey() {
        return this.zzjb.getKey();
    }

    public final Object getValue() {
        if (((zzcv) this.zzjb.getValue()) == null) {
            return null;
        }
        return zzcv.zzbf();
    }

    public final Object setValue(Object obj) {
        if (obj instanceof zzdr) {
            return ((zzcv) this.zzjb.getValue()).zzh((zzdr) obj);
        }
        throw new IllegalArgumentException("LazyField now only used for MessageSet, and the value of MessageSet must be an instance of MessageLite");
    }

    public final zzcv zzbg() {
        return (zzcv) this.zzjb.getValue();
    }
}
