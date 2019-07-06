package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class l implements Iterator<Boolean> {
    public abstract boolean b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Boolean next() {
        return Boolean.valueOf(b());
    }
}
