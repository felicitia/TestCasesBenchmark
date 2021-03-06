package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: ArrayIterator.kt */
final class g<T> implements Iterator<T> {
    private int a;
    private final T[] b;

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public g(T[] tArr) {
        p.b(tArr, "array");
        this.b = tArr;
    }

    public boolean hasNext() {
        return this.a < this.b.length;
    }

    public T next() {
        try {
            T[] tArr = this.b;
            int i = this.a;
            this.a = i + 1;
            return tArr[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            this.a--;
            throw new NoSuchElementException(e.getMessage());
        }
    }
}
