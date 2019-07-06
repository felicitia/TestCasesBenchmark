package com.google.android.gms.internal.firebase-perf;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzep extends zzev {
    private final /* synthetic */ zzem zzpx;

    private zzep(zzem zzem) {
        this.zzpx = zzem;
        super(zzem, null);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzeo(this.zzpx, null);
    }

    /* synthetic */ zzep(zzem zzem, zzen zzen) {
        this(zzem);
    }
}
