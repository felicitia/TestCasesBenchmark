package com.google.android.gms.internal.firebase-perf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.RandomAccess;

public final class zzfe extends AbstractList<String> implements zzdc, RandomAccess {
    /* access modifiers changed from: private */
    public final zzdc zzqg;

    public zzfe(zzdc zzdc) {
        this.zzqg = zzdc;
    }

    public final zzdc zzej() {
        return this;
    }

    public final Object getRaw(int i) {
        return this.zzqg.getRaw(i);
    }

    public final int size() {
        return this.zzqg.size();
    }

    public final void zzc(zzbd zzbd) {
        throw new UnsupportedOperationException();
    }

    public final ListIterator<String> listIterator(int i) {
        return new zzff(this, i);
    }

    public final Iterator<String> iterator() {
        return new zzfg(this);
    }

    public final List<?> zzei() {
        return this.zzqg.zzei();
    }

    public final /* synthetic */ Object get(int i) {
        return (String) this.zzqg.get(i);
    }
}
