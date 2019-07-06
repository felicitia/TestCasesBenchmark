package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzel extends zzer {
    private final /* synthetic */ zzei zzlg;

    private zzel(zzei zzei) {
        this.zzlg = zzei;
        super(zzei, null);
    }

    /* synthetic */ zzel(zzei zzei, zzej zzej) {
        this(zzei);
    }

    public final Iterator<Entry<K, V>> iterator() {
        return new zzek(this.zzlg, null);
    }
}
