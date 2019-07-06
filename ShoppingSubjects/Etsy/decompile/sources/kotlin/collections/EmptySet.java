package kotlin.collections;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import kotlin.jvm.internal.n;
import kotlin.jvm.internal.p;

/* compiled from: Sets.kt */
public final class EmptySet implements Serializable, Set {
    public static final EmptySet INSTANCE = new EmptySet();
    private static final long serialVersionUID = 3406603774387020532L;

    public /* synthetic */ boolean add(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean add(Void voidR) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public void clear() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean contains(Void voidR) {
        p.b(voidR, "element");
        return false;
    }

    public int getSize() {
        return 0;
    }

    public int hashCode() {
        return 0;
    }

    public boolean isEmpty() {
        return true;
    }

    public boolean remove(Object obj) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean removeAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean retainAll(Collection collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public Object[] toArray() {
        return n.a(this);
    }

    public <T> T[] toArray(T[] tArr) {
        return n.a(this, tArr);
    }

    public String toString() {
        return "[]";
    }

    private EmptySet() {
    }

    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof Void) {
            return contains((Void) obj);
        }
        return false;
    }

    public final int size() {
        return getSize();
    }

    public boolean equals(Object obj) {
        return (obj instanceof Set) && ((Set) obj).isEmpty();
    }

    public boolean containsAll(Collection collection) {
        p.b(collection, "elements");
        return collection.isEmpty();
    }

    public Iterator iterator() {
        return aa.a;
    }

    private final Object readResolve() {
        return INSTANCE;
    }
}
