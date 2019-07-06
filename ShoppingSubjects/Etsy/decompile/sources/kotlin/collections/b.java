package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

/* compiled from: AbstractIterator.kt */
public abstract class b<T> implements Iterator<T> {
    private State a = State.NotReady;
    private T b;

    /* access modifiers changed from: protected */
    public abstract void a();

    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean hasNext() {
        if (!(this.a != State.Failed)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        switch (c.a[this.a.ordinal()]) {
            case 1:
                return false;
            case 2:
                return true;
            default:
                return c();
        }
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        this.a = State.NotReady;
        return this.b;
    }

    private final boolean c() {
        this.a = State.Failed;
        a();
        return this.a == State.Ready;
    }

    /* access modifiers changed from: protected */
    public final void a(T t) {
        this.b = t;
        this.a = State.Ready;
    }

    /* access modifiers changed from: protected */
    public final void b() {
        this.a = State.Done;
    }
}
