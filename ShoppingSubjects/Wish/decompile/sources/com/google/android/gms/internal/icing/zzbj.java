package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class zzbj implements Iterator {
    private final int limit = this.zzdt.size();
    private int position = 0;
    private final /* synthetic */ zzbi zzdt;

    zzbj(zzbi zzbi) {
        this.zzdt = zzbi;
    }

    private final byte nextByte() {
        try {
            zzbi zzbi = this.zzdt;
            int i = this.position;
            this.position = i + 1;
            return zzbi.zzi(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
