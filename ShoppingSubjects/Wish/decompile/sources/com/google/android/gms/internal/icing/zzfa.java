package com.google.android.gms.internal.icing;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzfa extends AbstractList<String> implements zzdb, RandomAccess {
    /* access modifiers changed from: private */
    public final zzdb zzlp;

    public zzfa(zzdb zzdb) {
        this.zzlp = zzdb;
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzlp.get(i);
    }

    public final Object getRaw(int i) {
        return this.zzlp.getRaw(i);
    }

    public final Iterator<String> iterator() {
        return new zzfc(this);
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzfb(this, i);
    }

    public final int size() {
        return this.zzlp.size();
    }

    public final List<?> zzbh() {
        return this.zzlp.zzbh();
    }

    public final zzdb zzbi() {
        return this;
    }
}
