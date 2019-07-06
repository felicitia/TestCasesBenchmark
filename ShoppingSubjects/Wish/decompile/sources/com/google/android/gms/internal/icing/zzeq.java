package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.Map.Entry;

final class zzeq implements Iterator<Entry<K, V>> {
    private int pos;
    private Iterator<Entry<K, V>> zzlf;
    private final /* synthetic */ zzei zzlg;
    private boolean zzlk;

    private zzeq(zzei zzei) {
        this.zzlg = zzei;
        this.pos = -1;
    }

    /* synthetic */ zzeq(zzei zzei, zzej zzej) {
        this(zzei);
    }

    private final Iterator<Entry<K, V>> zzcn() {
        if (this.zzlf == null) {
            this.zzlf = this.zzlg.zzlb.entrySet().iterator();
        }
        return this.zzlf;
    }

    public final boolean hasNext() {
        if (this.pos + 1 >= this.zzlg.zzla.size()) {
            return !this.zzlg.zzlb.isEmpty() && zzcn().hasNext();
        }
        return true;
    }

    public final /* synthetic */ Object next() {
        this.zzlk = true;
        int i = this.pos + 1;
        this.pos = i;
        return (Entry) (i < this.zzlg.zzla.size() ? this.zzlg.zzla.get(this.pos) : zzcn().next());
    }

    public final void remove() {
        if (!this.zzlk) {
            throw new IllegalStateException("remove() was called before next()");
        }
        this.zzlk = false;
        this.zzlg.zzcl();
        if (this.pos < this.zzlg.zzla.size()) {
            zzei zzei = this.zzlg;
            int i = this.pos;
            this.pos = i - 1;
            zzei.zzag(i);
            return;
        }
        zzcn().remove();
    }
}
