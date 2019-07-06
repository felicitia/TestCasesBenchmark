package com.google.android.gms.internal.measurement;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class gu implements Iterator {
    private int a = 0;
    private final int b = this.c.size();
    private final /* synthetic */ zzzb c;

    gu(zzzb zzzb) {
        this.c = zzzb;
    }

    private final byte a() {
        try {
            zzzb zzzb = this.c;
            int i = this.a;
            this.a = i + 1;
            return zzzb.zzae(i);
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
