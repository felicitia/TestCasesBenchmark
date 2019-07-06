package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class ap implements Iterator<Short> {
    public abstract short b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Short next() {
        return Short.valueOf(b());
    }
}
