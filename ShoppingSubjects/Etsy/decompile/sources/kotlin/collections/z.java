package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class z implements Iterator<Double> {
    public abstract double b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Double next() {
        return Double.valueOf(b());
    }
}
