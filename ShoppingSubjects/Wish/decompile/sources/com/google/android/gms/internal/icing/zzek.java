package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzek implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzlf;
    private final /* synthetic */ zzei zzlg;

    private zzek(zzei zzei) {
        this.zzlg = zzei;
        this.pos = this.zzlg.zzla.size();
    }

    /* synthetic */ zzek(zzei zzei, zzej zzej) {
        this(zzei);
    }

    private final Iterator<Entry<K, V>> zzcn() {
        if (this.zzlf == null) {
            this.zzlf = this.zzlg.zzld.entrySet().iterator();
        }
        return this.zzlf;
    }

    public final boolean hasNext() {
        return (this.pos > 0 && this.pos <= this.zzlg.zzla.size()) || zzcn().hasNext();
    }

    public final /* synthetic */ Object next() {
        Object obj;
        if (zzcn().hasNext()) {
            obj = zzcn().next();
        } else {
            List zzb = this.zzlg.zzla;
            int i = this.pos - 1;
            this.pos = i;
            obj = zzb.get(i);
        }
        return (Entry) obj;
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
