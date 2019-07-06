package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.TypeCastException;
import kotlin.jvm.internal.p;

/* compiled from: Sequences.kt */
final class b<T> implements c<T> {
    /* access modifiers changed from: private */
    public final kotlin.jvm.a.a<T> a;
    /* access modifiers changed from: private */
    public final kotlin.jvm.a.b<T, T> b;

    /* compiled from: Sequences.kt */
    public static final class a implements Iterator<T> {
        final /* synthetic */ b a;
        private T b;
        private int c = -2;

        public void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }

        a(b bVar) {
            this.a = bVar;
        }

        private final void a() {
            T t;
            if (this.c == -2) {
                t = this.a.a.invoke();
            } else {
                kotlin.jvm.a.b b2 = this.a.b;
                T t2 = this.b;
                if (t2 == null) {
                    p.a();
                }
                t = b2.invoke(t2);
            }
            this.b = t;
            this.c = this.b == null ? 0 : 1;
        }

        public T next() {
            if (this.c < 0) {
                a();
            }
            if (this.c == 0) {
                throw new NoSuchElementException();
            }
            T t = this.b;
            if (t == null) {
                throw new TypeCastException("null cannot be cast to non-null type T");
            }
            this.c = -1;
            return t;
        }

        public boolean hasNext() {
            if (this.c < 0) {
                a();
            }
            return this.c == 1;
        }
    }

    public b(kotlin.jvm.a.a<? extends T> aVar, kotlin.jvm.a.b<? super T, ? extends T> bVar) {
        p.b(aVar, "getInitialValue");
        p.b(bVar, "getNextValue");
        this.a = aVar;
        this.b = bVar;
    }

    public Iterator<T> a() {
        return new a<>(this);
    }
}
