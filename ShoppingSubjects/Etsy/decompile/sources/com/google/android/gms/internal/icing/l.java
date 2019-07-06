package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class l implements Iterator {
    private int a = 0;
    private final int b = this.c.size();
    private final /* synthetic */ zzbi c;

    l(zzbi zzbi) {
        this.c = zzbi;
    }

    private final byte a() {
        try {
            zzbi zzbi = this.c;
            int i = this.a;
            this.a = i + 1;
            return zzbi.zzi(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final boolean hasNext() {
        return this.a < this.b;
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(a());
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
