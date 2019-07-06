package com.google.android.gms.internal.firebase-perf;

import java.util.ListIterator;

final class zzff implements ListIterator<String> {
    private ListIterator<String> zzqh = this.zzqj.zzqg.listIterator(this.zzqi);
    private final /* synthetic */ int zzqi;
    private final /* synthetic */ zzfe zzqj;

    zzff(zzfe zzfe, int i) {
        this.zzqj = zzfe;
        this.zzqi = i;
    }

    public final boolean hasNext() {
        return this.zzqh.hasNext();
    }

    public final boolean hasPrevious() {
        return this.zzqh.hasPrevious();
    }

    public final int nextIndex() {
        return this.zzqh.nextIndex();
    }

    public final int previousIndex() {
        return this.zzqh.previousIndex();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void add(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ void set(Object obj) {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object previous() {
        return (String) this.zzqh.previous();
    }

    public final /* synthetic */ Object next() {
        return (String) this.zzqh.next();
    }
}
