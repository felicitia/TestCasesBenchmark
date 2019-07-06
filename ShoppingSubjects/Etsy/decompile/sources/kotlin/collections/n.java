package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class n implements Iterator<Character> {
    public abstract char b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Character next() {
        return Character.valueOf(b());
    }
}
