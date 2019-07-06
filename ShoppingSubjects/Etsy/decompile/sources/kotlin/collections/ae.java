package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class ae implements Iterator<Long> {
    public abstract long b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Long next() {
        return Long.valueOf(b());
    }
}
