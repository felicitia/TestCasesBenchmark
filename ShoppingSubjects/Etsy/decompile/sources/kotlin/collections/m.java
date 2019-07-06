package kotlin.collections;

import java.util.Iterator;

/* compiled from: Iterators.kt */
public abstract class m implements Iterator<Byte> {
    public abstract byte b();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    /* renamed from: a */
    public final Byte next() {
        return Byte.valueOf(b());
    }
}
