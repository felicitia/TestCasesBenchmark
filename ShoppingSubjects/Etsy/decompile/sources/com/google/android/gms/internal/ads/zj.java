package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.Map.Entry;

final class zj extends zp {
    private final /* synthetic */ zg a;

    private zj(zg zgVar) {
        this.a = zgVar;
        super(zgVar, null);
    }

    /* synthetic */ zj(zg zgVar, zh zhVar) {
        this(zgVar);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zi(this.a, null);
    }
}
