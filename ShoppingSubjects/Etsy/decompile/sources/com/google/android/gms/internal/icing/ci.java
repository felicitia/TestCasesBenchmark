package com.google.android.gms.internal.icing;

import java.util.Iterator;
import java.util.NoSuchElementException;

final class ci implements Iterator<Object> {
    ci() {
    }

    public final boolean hasNext() {
        return false;
    }

    public final Object next() {
        throw new NoSuchElementException();
    }

    public final void remove() {
        throw new UnsupportedOperationException();
    }
}
