package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class ab implements Iterator<Float> {
    public abstract float b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Float next() {
        return Float.valueOf(b());
    }
}
