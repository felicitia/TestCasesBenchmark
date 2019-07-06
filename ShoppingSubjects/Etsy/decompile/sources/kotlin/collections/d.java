package kotlin.collections;

import com.etsy.android.lib.models.ResponseConstants;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.jvm.internal.p;

/* compiled from: AbstractList.kt */
public abstract class d<E> extends a<E> implements List<E> {
    public static final a a = new a(null);

    /* compiled from: AbstractList.kt */
    public static final class a {
        private a() {
        }

        public /* synthetic */ a(o oVar) {
            this();
        }

        public final void a(int i, int i2) {
            if (i < 0 || i >= i2) {
                StringBuilder sb = new StringBuilder();
                sb.append("index: ");
                sb.append(i);
                sb.append(", size: ");
                sb.append(i2);
                throw new IndexOutOfBoundsException(sb.toString());
            }
        }

        public final void b(int i, int i2) {
            if (i < 0 || i > i2) {
                StringBuilder sb = new StringBuilder();
                sb.append("index: ");
                sb.append(i);
                sb.append(", size: ");
                sb.append(i2);
                throw new IndexOutOfBoundsException(sb.toString());
            }
        }

        public final void a(int i, int i2, int i3) {
            if (i < 0 || i2 > i3) {
                StringBuilder sb = new StringBuilder();
                sb.append("fromIndex: ");
                sb.append(i);
                sb.append(", toIndex: ");
                sb.append(i2);
                sb.append(", size: ");
                sb.append(i3);
                throw new IndexOutOfBoundsException(sb.toString());
            } else if (i > i2) {
                StringBuilder sb2 = new StringBuilder();
                sb2.append("fromIndex: ");
                sb2.append(i);
                sb2.append(" > toIndex: ");
                sb2.append(i2);
                throw new IllegalArgumentException(sb2.toString());
            }
        }

        public final int a(Collection<?> collection) {
            p.b(collection, "c");
            int i = 1;
            for (Object next : collection) {
                i = (next != null ? next.hashCode() : 0) + (31 * i);
            }
            return i;
        }

        public final boolean a(Collection<?> collection, Collection<?> collection2) {
            p.b(collection, "c");
            p.b(collection2, ResponseConstants.OTHER);
            if (collection.size() != collection2.size()) {
                return false;
            }
            Iterator it = collection2.iterator();
            for (Object a : collection) {
                if (!p.a(a, it.next())) {
                    return false;
                }
            }
            return true;
        }
    }

    /* compiled from: AbstractList.kt */
    private class b implements Iterator<E> {
        private int b;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public b() {
        }

        /* access modifiers changed from: protected */
        public final int a() {
            return this.b;
        }

        /* access modifiers changed from: protected */
        public final void a(int i) {
            this.b = i;
        }

        public boolean hasNext() {
            return this.b < d.this.size();
        }

        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            d dVar = d.this;
            int i = this.b;
            this.b = i + 1;
            return dVar.get(i);
        }
    }

    /* compiled from: AbstractList.kt */
    private class c extends b implements ListIterator<E> {
        public void add(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public void set(E e) {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        public c(int i) {
            super();
            d.a.b(i, d.this.size());
            a(i);
        }

        public boolean hasPrevious() {
            return a() > 0;
        }

        public int nextIndex() {
            return a();
        }

        public E previous() {
            if (!hasPrevious()) {
                throw new NoSuchElementException();
            }
            d dVar = d.this;
            a(a() - 1);
            return dVar.get(a());
        }

        public int previousIndex() {
            return a() - 1;
        }
    }

    /* renamed from: kotlin.collections.d$d reason: collision with other inner class name */
    /* compiled from: AbstractList.kt */
    private static final class C0192d<E> extends d<E> implements RandomAccess {
        private int b;
        private final d<E> c;
        private final int d;

        public C0192d(d<? extends E> dVar, int i, int i2) {
            p.b(dVar, ResponseConstants.LIST);
            this.c = dVar;
            this.d = i;
            d.a.a(this.d, i2, this.c.size());
            this.b = i2 - this.d;
        }

        public E get(int i) {
            d.a.a(i, this.b);
            return this.c.get(this.d + i);
        }

        public int a() {
            return this.b;
        }
    }

    public void add(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public boolean addAll(int i, Collection<? extends E> collection) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public abstract E get(int i);

    public E remove(int i) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    public E set(int i, E e) {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    protected d() {
    }

    public Iterator<E> iterator() {
        return new b<>();
    }

    public ListIterator<E> listIterator() {
        return new c<>(0);
    }

    public ListIterator<E> listIterator(int i) {
        return new c<>(i);
    }

    public List<E> subList(int i, int i2) {
        return new C0192d<>(this, i, i2);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof List)) {
            return false;
        }
        return a.a((Collection<?>) this, (Collection) obj);
    }

    public int hashCode() {
        return a.a(this);
    }

    public int indexOf(Object obj) {
        int i = 0;
        for (Object a2 : this) {
            if (p.a(a2, obj)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        ListIterator listIterator = listIterator(size());
        while (listIterator.hasPrevious()) {
            if (p.a(listIterator.previous(), obj)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }
}
