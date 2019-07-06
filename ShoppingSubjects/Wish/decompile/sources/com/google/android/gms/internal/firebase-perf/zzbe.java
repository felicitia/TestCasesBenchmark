package com.google.android.gms.internal.firebase-perf;

import java.util.NoSuchElementException;

final class zzbe implements zzbj {
    private final int limit = this.zzhs.size();
    private int position = 0;
    private final /* synthetic */ zzbd zzhs;

    zzbe(zzbd zzbd) {
        this.zzhs = zzbd;
    }

    public final boolean hasNext() {
        return this.position < this.limit;
    }

    public final byte nextByte() {
        try {
            zzbd zzbd = this.zzhs;
            int i = this.position;
            this.position = i + 1;
            return zzbd.zzj(i);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException(e.getMessage());
        }
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }

    public final /* synthetic */ Object next() {
        return Byte.valueOf(nextByte());
    }
}
