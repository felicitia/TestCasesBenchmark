package com.google.android.gms.internal.ads;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class wg implements Iterator {
    private int a = 0;
    private final int b = this.c.size();
    private final /* synthetic */ zzbah c;

    wg(zzbah zzbah) {
        this.c = zzbah;
    }

    private final byte a() {
        try {
            zzbah zzbah = this.c;
            int i = this.a;
            this.a = i + 1;
            return zzbah.zzbn(i);
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
